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
   <div class="container">
      <div class="row">
         <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="col-lg-12">
               <div class="row mb-3">
                  <div class="col-lg-2"></div>
                  <div class="col-lg-8">
                  </div>
                  <div class="col-lg-2"></div>
               </div>
            <div class="row mb-3">
			   <div class="col-lg-2"></div>
				   <div class="col-lg-8 text-center">
				   		<a href="userPage" class="btn btn-primary mr-2">회원 관리</a>
				   		<a href="userPage" class="btn btn-primary mr-2">방 관리</a>
				      <input class="btn btn-primary" type="button" value="" onclick="history.back()"/>
				   </div>
			   <div class="col-lg-2"></div>
			</div>
                  </div>
               </div>
           </div>
         </div>
</section>
<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>