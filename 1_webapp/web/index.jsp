<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="alpha" uri="http://alpha.com" %>
<html>
<head>
    <title>$Title$</title>
    <style>
        .hide {
            display: none;
        }

        .show {
            display: block;
        }

        .warn {
            color: red;
        }
    </style>
</head>
<body>

<h1>Book Query:</h1>
<form action="query" method="get">
    <input name="author" placeholder="please input author" autofocus>
    <button type="submit">Query</button>
</form>
<hr>
<c:if test="${display==null}">
    <c:set var="display" value="hide" scope="page"></c:set>
</c:if>

<div class="${display}">
    <h1>The books written by ${author}</h1>
    <alpha:myTable items="books" var="book" heads="heads">
        <tr>
            <td>${book.title}</td>
            <td>${book.press}</td>
        </tr>
    </alpha:myTable>

</div>
<hr>
</body>
</html>
