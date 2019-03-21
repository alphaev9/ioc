package com.alpha.ioc.di.util;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * 1、从某个目录下，获得所有class后缀的文件
 * 2、获得所有类名称
 */
public class PackageScanner {
    public static Set<String> scanPackage(File baseDir) {
        Set<String> set = new HashSet<>();
        getPath(baseDir, set);
        Set<String> collect = new HashSet<>();
        Set<String> classes = set.stream()
                .filter(f -> f.endsWith("class"))
                .map(f -> {
                    String s = f.substring(baseDir.getPath().length() + 1).substring(16);
                    String replace = s.replace("\\", ".");
                    int i = replace.lastIndexOf(".class");
                    String result = replace.substring(0, i);
                    return result;
                })
                .collect(Collectors.toSet());
        collect.addAll(classes);

        List<Set<String>> list = set.stream()
                .filter(f -> f.endsWith("jar"))
                .map(f -> {
                    try {
                        String s = "jar:file:" + f.replace('\\', '/') + "!/";
                        URL url = new URL(s);
                        return scanPackage(url);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());

        Set<String> jars = new HashSet<>();
        for (Set<String> strings : list) {
            jars.addAll(strings);
        }

        collect.addAll(jars);
        return collect;
    }

    public static Set<String> scanPackage(File baseDir, Predicate<String> filter) {
        Set<String> set = new HashSet<>();
        scanPackage(baseDir).stream()
                .filter(filter)
                .forEach(f -> set.add(f));
        return set;
    }

    private static void getPath(File baseDir, Set<String> set) {
        File[] files = baseDir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                String path = file.getPath();
                if (path.endsWith(".class") || path.endsWith(".jar")) {
                    set.add(path);
                }
            } else {
                getPath(file, set);
            }
        }
    }

    private static Set<String> scanPackage(URL url) throws IOException {
        Set<String> set = new HashSet<>();
        JarURLConnection jarUrlConnection = (JarURLConnection) url.openConnection();
        JarFile jarFile = jarUrlConnection.getJarFile();
        Enumeration<JarEntry> jarEntries = jarFile.entries();

        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            String jarName = jarEntry.getName();
            if (jarEntry.isDirectory() || !jarName.endsWith(".class")) {
                continue;
            }
            String className = jarName.replace(".class", "").replaceAll("/", ".");
            /*try {
                Class<?> klass = Class.forName(className);
                if (klass.isAnnotation()
                        || klass.isInterface()
                        || klass.isEnum()
                        || klass.isPrimitive()) {
                    continue;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }*/
            set.add(className);
        }
        return set;
    }
}
