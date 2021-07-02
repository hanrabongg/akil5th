<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>AK IL 5TH</title>
    <link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.css">
</head>
<body>
    <h1>INDEX</h1>

    <div class="col-md-12">
        <div class="col-md-4">
            <form>
                <div class="form-group">
                    <label for="productType">상품코드</label>
                    <input type="text" class="form-control" id="productType" placeholder="">
                </div>
                <div class="form-group">
                    <label for="productName">상품종류</label>
                    <input type="text" class="form-control" id="productName" placeholder="">
                </div>
                <div class="form-group">
                    <label for="productOrder">표출순서</label>
                    <input type="text" class="form-control" id="productOrder" placeholder="">
                </div>
            </form>
            <a href="/" role="button" class="btn btn-secondary">취소</a>
            <button type="button" class="btn btn-primary" id="btn-save">등록</button>
        </div>
    </div>


<script src="resources/static/js/jquery-3.6.0.min.js"></script>
<script src="resources/static/js/bootstrap.min.js"></script>
<script src="resources/static/js/app/index.js"></script>
</body>
</html>