<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>같이먹장</title>

<!-- common -->
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>			<!-- device-width -->
<link rel="icon" href="images/favicon.png">										<!--  favicon -->
<script type="text/javascript" src="common/jquery-3.7.0.js"></script>			<!-- jQury -->
<link rel="stylesheet" href="common/bootstrap/bootstrap.min.css">				<!-- bootstrap -->
<script src="common/bootstrap/bootstrap.min.js"></script>						<!-- bootstrap -->
<script src="common/bootstrap/popper.min.js"></script>							<!-- bootstrap -->
<script type="text/javascript" src="common/custom.js" defer="defer"></script>	<!-- template -->
<link rel="stylesheet" href="common/style.css"/>								<!-- template -->

<link rel="stylesheet" href="common/slick-carousel/slick-theme.css">			<!-- slick -->
<link rel="stylesheet" href="common/slick-carousel/slick.css">					<!-- slick -->
<script src="common/slick-carousel/slick.min.js"></script>						<!-- slick -->
<script type="text/javascript" src="js/loginCheck.js" defer="defer"></script>

<!-- private -->
<script type="text/javascript" src="js/partyInsert.js" defer="defer"></script>


</head>
<body>
<!-- header -->
<jsp:include page="../common/header.jsp"></jsp:include>

<!-- 메인 시작 -->
<section class="section-padding">
  <div class="row mb-3">
    <div class="col-lg-1"></div>
    <div class="col-lg-10 text-center">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>아이디</th>
                    <th>비밀번호</th>
                    <th>닉네임</th>
                    <th>이름</th>
                    <th>나이</th>
                    <th>성별</th>
                    <th>주민번호</th>
                    <th>이메일</th>
                    <th>전화번호</th>
                    <th>주소</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${memberList.list}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.password}</td>
                        <td>${user.nickName}</td>
                        <td>${user.name}</td>
                        <td>${user.age}</td>
                        <td>${user.gender}</td>
                        <td>${user.jumin}</td>
                        <td>${user.email}</td>
                        <td>${user.telephone}</td>
                        <td>${user.address}</td>
                        <td>
                            <input type="button" class="btn btn-primary" value="수정"
                         	   onclick="location.href='selectByIdx?idx=${vo.idx}&currentPage=${currentPage}&job=userUpdate'"/>
                        </td>
                        <td>
                            <input type="button" class="btn btn-danger" onclick="userDelete"/>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <input class="btn btn-primary" type="button" value="돌아가기" onclick="history.back()"/>
    </div>
    <div class="col-lg-1"></div>
</div>
</section>
<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>