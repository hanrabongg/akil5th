<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>AK IL 5TH</title>
    <link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.css">

</head>
<body>

Hello, Spring Boot App pp

    <div class="form-group">
      <label for="exampleSelect1" class="form-label mt-4">상품구분</label>
      <select class="form-select" id="exampleSelect1">
        <option>세탁세제</option>
        <option>주방세제</option>
        <option>치약</option>
        <option>팩트</option>
        <option>샴푸</option>
      </select>
      <label for="exampleSelect2" class="form-label mt-4">용량</label>
        <select class="form-select" id="exampleSelect2">
          <option>10g</option>
          <option>30g</option>
          <option>50g</option>
          <option>100g</option>
        </select>
    </div>
    <div class="form-group">
      <label for="exampleSelect2" class="form-label mt-4">호수</label>
      <select multiple="" class="form-select" id="exampleSelect2">
        <option>15호</option>
        <option>20호</option>
        <option>21호</option>
        <option>24호</option>
      </select>
    </div>

    <div class="form-group">
      <label class="col-form-label mt-4" for="inputDefault">상품명</lbel>
      <input type="text" class="form-control" placeholder="Default input" id="inputDefault">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</body>
</html>
