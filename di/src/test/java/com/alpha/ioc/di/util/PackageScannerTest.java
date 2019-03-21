package com.alpha.ioc.di.util;

import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class PackageScannerTest {

    @Test
    public void scanPackage() {

        Set<String> set = new HashSet<>();
        File file = new File("d:\\queryBook");
        Set<String> set1 = PackageScanner.scanPackage(file);
    }

    @Test
    public void scanPackage1() {
    }
}