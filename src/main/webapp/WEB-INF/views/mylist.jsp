<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>         <!-- jstl c -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>      <!-- jstl fmt -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   <!-- jstl fn -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파티 관리</title>

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
<!-- private -->

</head>
<body>

<!-- header -->
<jsp:include page="common/header.jsp"></jsp:include>


<section class="section-padding">
	<div class="container">
		<div class="row">
		<div class="col-lg-8 col-md-12 col-sm-12 col-xs-12">
			<!-- 내가 만든 방 -->
			<div class="sidebar-widget mb-5 ">
			<h4 class="text-center widget-title mb-4" style="font-size: 25px;">내가 만든 방</h4>
			</div>
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="row">
				<c:set var="view" value="${partyList.list}"></c:set>
				<!-- 생성 리스트 반복 -->
				<c:forEach var="vo" items="${view}">
				<!-- 신고 정상범위 -->
				<c:if test="${vo.deleteReport < 10}">
					<div class="col-lg-3 col-md-6">
						<article class="post-grid mb-5">
							<a class="post-thumb mb-4 d-block" href="selectByIdx?idx=${vo.idx}&currentPage=${currentPage}&job=reple">
							<img src="upload/thumbnail/${vo.thumbnail}" class="img-fluid" style="height: 210px; width: 250px; border-radius: 50px;">
							<span class="text-color font-lg font-extra text-uppercase letter-spacing" style="display: block">${vo.category}</span><br/>
							<span class="text-muted letter-spacing font-sm">
								<fmt:formatDate value="${vo.mealDate}" pattern="yy.MM.dd  HH:mm" />&nbsp;
								<fmt:formatDate value="${vo.mealDate}" pattern="HH:mm" />
							</span><br/>
							<span class="post-title mt-1">${vo.subject}</span>
							</a>
						</article>
					</div>
				</c:if>
				<!-- 신고받은 방 -->
				<c:if test="${vo.deleteReport >= 10}">
					<div class="col-lg-3 col-md-6">
						<article class="post-grid mb-5">
						<img src="./images/report.png" class="img-fluid w-100" style="height: 210px; width: 210px;" >
						<span class="text-color mt-2 letter-spacing"> </span><br/>
						<span class="text-muted letter-spacing text-uppercase font-sm">
							<fmt:formatDate value="${vo.mealDate}" pattern=" " />&nbsp;
							<fmt:formatDate value="${vo.mealDate}" pattern=" " />
						</span><br/>
						<div class="post-title text-center"><b style="color: red; font-size: 20px;">신고 누적된 방입니다.</b></div>
						</article>
					</div>
				</c:if>
				</c:forEach>
				</div>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="sidebar-widget mb-5 ">
					<h4 class="text-center widget-title mb-4" style="font-size: 25px;">참여한 방</h4>
				</div>
				<div class="row">
				<!-- 참여 리스트 반복 -->
				<c:set var="view" value="${joinList}"></c:set>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="row">
					<c:forEach var="vo" items="${view}">
						<div class="col-lg-3 col-md-6">
							<article class="post-grid mb-5">
								<a class="post-thumb mb-4 d-block" href="selectByIdx?idx=${vo.idx}&currentPage=${currentPage}&job=reple">
								<img src="./upload/thumbnail/${vo.thumbnail}" class="img-fluid w-100" style="height: 210px; width: 210px; border-radius: 50px;">
								<span class="cat-name text-color font-lg font-extra text-uppercase letter-spacing">${vo.category}</span><br/>
								<span class="text-muted letter-spacing text-uppercase font-sm">
									<fmt:formatDate value="${vo.mealDate}" pattern="yy.MM.dd  HH:mm" />&nbsp;
									<fmt:formatDate value="${vo.mealDate}" pattern="HH:mm" />
								</span><br/>
								<span class="post-title mt-1">${vo.subject}</span>
								</a>
							</article>
						</div>
					</c:forEach>
					</div>
				</div>
				<!-- 참여 리스트 반복 끝 -->
				</div>
			</div>
			</div>
			</div>
			 <!-- 프로필 -->
			 <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				<div class="sidebar sidebar-right">
					<div class="sidebar-wrap mt-5 mt-lg-0">
			            <div class="sidebar-widget about mb-5 text-center p-3">
			               <div class="about-author">
			                  <img src="upload/memberphoto/${mo.photo}" class="img-fluid">
			               </div>
				               <h4 class="mb-0 mt-4"></h4>
				               <p>${mo.nickName}</p>
				               <p>${mo.age}</p>
				               <p>${mo.gender}</p>
				               <h4 class="mb-0 mt-4">내 평점</h4>
				               <p>오점</p>
			            </div>
					</div>
				</div>
			</div>
		</div>
		</div>
</section>
<!-- footer -->
<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>