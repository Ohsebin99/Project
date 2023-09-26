<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>         <!-- jstl c -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>      <!-- jstl fmt -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   <!-- jstl fn -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제할 글 보기</title>

<!-- common -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />		<!-- device-width -->
<link rel="icon" href="images/favicon.png">										<!--  favicon -->
<script type="text/javascript" src="common/jquery-3.7.0.js"></script>			<!-- jQury -->
<link rel="stylesheet" href="common/bootstrap/bootstrap.min.css">				<!-- bootstrap -->
<script src="common/bootstrap/bootstrap.min.js"></script>						<!-- bootstrap -->
<script src="common/bootstrap/popper.min.js"></script>							<!-- bootstrap -->
<script type="text/javascript" src="common/custom.js" defer="defer"></script>	<!-- template -->
<link rel="stylesheet" href="common/style.css" />								<!-- template -->

<link rel="stylesheet" href="common/slick-carousel/slick-theme.css">			<!-- slick -->
<link rel="stylesheet" href="common/slick-carousel/slick.css">					<!-- slick -->
<script src="common/slick-carousel/slick.min.js"></script>						<!-- slick -->
<script type="text/javascript" src="js/loginCheck.js" defer="defer"></script>
<!-- private -->

</head>
<body>
<fmt:requestEncoding value="UTF-8"/>

<!-- header -->
	<jsp:include page="common/header.jsp"></jsp:include>

<section class="single-block-wrapper section-padding">
<div class="container">
<div class="row">

   <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
      
         <div class="single-post">
            <div class="post-header mb-5 text-center">
               <div class="post-featured-image mt-5">
                  <img src="upload/thumbnail/${vo.thumbnail}" class="img-fluid w-100" style="height: 420px;"/>
               </div>
            </div>
            <div class="post-body">
                        <div class="container bg-grey comment-form">
               <div class="row">
                  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                     <div class="col-lg-12">
                           <div class="row">
                           <!-- 삭제할 글 보기 -->
                              <%-- <div align="center" class="col-lg-12 row">
                                  <c:set var="subject" value="${fn:replace(vo.subject, '<', '&lt;')}"/>
                                  <c:set var="subject" value="${fn:replace(subject, '>', '&gt;')}"/>
                                  <input type="text" name="subject" class="form-control text-center mt-3 mb-3" style="margin-left: auto; margin-right: auto;" value="삭제할 글 보기" readonly>
                              </div> --%>
                              <!-- 파티명 -->
                              <div class="col-md-6" style="margin-top: 40px;">
                              <h5 class="mb-3 text-center">파티명</h5>
                                 <input type="text" class="form-control-plaintext text-center mt-3 mb-3" readonly="readonly"
                                 value="${vo.subject}"/>
                              </div>
                              <!-- 장소 -->
                              <div class="col-md-6" style="margin-top: 40px;">
                                 <h5 class="mb-3 text-center">장소</h5>
                                 <input type="text" class="form-control-plaintext text-center mt-3 mb-3" readonly="readonly"
                                 value="${vo.place}"/>
                              </div>
                              <!-- 음식 카테고리 -->
                              <div class="col-md-4">
                                 <h5 class="mb-3 text-center">카테고리</h5>
                                 <input type="text" class="form-control-plaintext text-center mb-3" readonly="readonly" value="${vo.category}"/>
                              </div>
                              <!-- 성별 -->
                              <div class="col-md-4">
                                 <h5 class="mb-3 text-center">성별</h5>
                                 <input type="text" class="form-control-plaintext text-center mb-3" readonly="readonly" value="${vo.partyGender}"/>
                              </div>
                                 <!-- 식사 시간 -->
                              <div class="col-md-4">
                                 <h5 class="mb-3 text-center">식사 시간</h5>
                                 <input type="text" class="form-control-plaintext text-center mb-3" readonly="readonly"
                                    value="<fmt:formatDate value="${vo.mealDate}" pattern="MM월 dd일 HH:mm"/>"/>
                              </div>
                              <!-- 음주 -->
                              <div class="col-lg-4 col-md-6">
                                 <h5 class="mb-3 text-center">술 여부</h5>
                                 <input type="text" class="form-control-plaintext text-center mb-3" readonly="readonly" value="${vo.acholchk}"/>
                              </div>
                              <!-- 인원 -->
                              <div class="col-lg-4 col-md-6">
                                 <h5 class="mb-3 text-center">인원 제한</h5>
                                 <input type="text" class="form-control-plaintext text-center mb-3" readonly="readonly"
                                    value="${vo.limitNum}명"/>
                              </div>
                              <!-- 나이 -->
                              <div class="col-lg-4 col-md-6">
                                 <h5 class="mb-3 text-center">나이 제한</h5>
                                 <input type="text" class="form-control-plaintext text-center mb-3" readonly="readonly"
                                 value="${vo.minLimitAge}살 ~ ${vo.maxLimitAge}살"/>
                              </div>
                              
                              <!-- 글 내용 -->
                              <div class="col-lg-12">
                                 <c:set var="content" value="${fn:replace(vo.content, '<', '&lt;')}"/>
                                 <c:set var="content" value="${fn:replace(content, '>', '&gt;')}"/>
                                 <c:set var="content" value="${fn:replace(content, enter, '<br/>')}"/>
                                 <textarea class="form-control mb-3" name="content" rows="15" cols="30" style="resize: none;" readonly="readonly">${content}</textarea>
                              </div>
                           </div>
                           <form class="m-3" action="partyDelete" method="post">
	                           <!-- 히든 속성 -->
	                           <input type="hidden" name="idx" value="${vo.idx}"/>
	                           <input type="hidden" name="currentPage" value="${currentPage}"/>
	                           <div class="row justify-content-center mt-3">
	                              <div class="col-lg-12 mb-4 text-center">
	                                 <div class="btn-group">
	                                    <input class="btn btn-primary" type="submit" value="삭제하기"/>
	                                    <input class="btn btn-primary" type="button" value="돌아가기" onclick="history.back()"/>
	                                    <input class="btn btn-primary" type="button" value="목록보기" onclick="location.href='list.jsp?currentPage=${currentPage}'"/>
	                                 </div>
	                              </div>
	                           </div>
                           </form>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>   
   </div>
</div>
</div>
</section>

</body>
</html>