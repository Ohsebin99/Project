<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>         <!-- jstl c -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>      <!-- jstl fmt -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   <!-- jstl fn -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보</title>

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
<link rel="stylesheet" href="css/member.css"/>
<script type="text/javascript" src="js/myProfile.js"></script>

</head>
<body>

<!-- header -->
<jsp:include page="common/header.jsp"></jsp:include>

<!-- 메인 시작 -->
<div class="container" style="margin-top: 20px;">
   <form action="myProfileOK" method="post" onsubmit="return formCheck()" enctype="multipart/form-data">
      <table class="table table-bordered" style="width: 500px; margin-left: auto; margin-right: auto;">
         <thead>
            <tr class="success">
               <th colspan="3" style="text-align: center; font-size: 25px;">내 정보 관리</th>
            </tr>
         </thead>
         <tbody>
			<div class="col-lg-12">
				<div class="row mb-3">
		    		<div class="col-md-12">
		         		<h5 class="mb-3 text-center">파일 업로드</h5>
		         		<div class="text-center">
			        	<img id="output" class="img-fluid" src="upload/memberphoto/${mo.photo}" style="height: 130px; width: 130px; border-radius: 30px;"/><br/>
			        	</div>
		      		</div>
			   	</div>
			   	<div class="row mb-3">
			      	<div class="col-lg-8">
		         		<input class="form-control" type="file" accept="image/*" name="fileName" onchange="photoView(event)"/>
		         		<input type="hidden" name="defaultImgCheck"><br>
			      	</div>
			      	<div class="col-lg-2">
		         		<input class="btn btn-success" type="button" value="사진 초기화" onclick="defaultImg()"/>
		         	</div>
			      	<div class="col-lg-2"></div>
			   	</div>
			</div>
            <!-- 아이디 -->
            <tr>
               <td colspan="2">
                  <input id="id" class="form-control" type="text" style="background-color: #636363; color: white;" name="id" value="${mo.id}"
                     placeholder="아이디" autocomplete="off" onkeyup="idCheck()" readonly="readonly"/>
                  <!-- 아이디 오류 문구 출력 -->
                  <h5 id="idChkMessage1" style="color: red; font-weight: bold"></h5>
                  <h5 id="idChkMessage2" style="color: lime; font-weight: bold"></h5>
               </td>
            </tr>
            <!-- 닉네임 -->
            <tr>
               <td colspan="2">
                  <input id="nickname" class="form-control" type="text" name="nickName" value="${mo.nickName}"
                     placeholder="닉네임" autocomplete="off"/>
               </td>
            <!-- 이름 -->
            <tr>
               <td colspan="2">
                  <input id="name" class="form-control" type="text" style="background-color: #636363; color: white;" name="name" value="${mo.name}"
                     readonly="readonly"/>
               </td>
            </tr>
			<!-- 주민번호 -->
			<tr>
			   <td colspan="3" style="display: flex;">
			       <!-- 주민번호 입력란의 플레이스홀더 -->
			       <c:choose>
			           <c:when test="${empty mo.jumin or fn:length(mo.jumin) != 13}">
			               <input style="width: 235px; background-color: #636363; color: white;" type="text" class="form-control" name="jumin1" readonly="readonly" placeholder="주민등록번호를 입력하세요"/>
			               <b style="margin-top: 10px;"> - </b>
			               <input  style="width: 235px; background-color: #636363; color: white;"  type="password" class="form-control" name="jumin2" readonly="readonly"/>
			           </c:when>
			           <c:otherwise>
			               <c:set var="jumin1" value="${mo.jumin.substring(0, 6)}"/>
			               <input style="width: 235px; background-color: #636363; color: white;" type="text" class="form-control" name="jumin1" value="${jumin1}" readonly="readonly"/>
			               <b style="margin-top: 10px;"> - </b>
			               <c:set var="jumin2" value="${mo.jumin.substring(6, 13)}"/>
			               <input  style="width: 235px; background-color: #636363; color: white;"  type="password" class="form-control" name="jumin2" value="${jumin2}" readonly="readonly"/> 
			           </c:otherwise>
			       </c:choose>
			   </td>
			</tr>
            <!-- 이메일 -->
            <tr>
               <td colspan="2" style="display: flex;">
                  <c:set var="email" value="${mo.email.split('@')}"/>
                  <input  type="text" name="email1" class="form-control" style="width: 130px;" value="${email[0]}"/>
                  <b style="margin-top: 10px; margin-left: 15px; ">@</b>
                  <input  type="text" name="email2" value="${email[1]}" class="form-control"
                     style="width: 130px; height: 46px; margin-left: 20px;"/>
                  <select style="width: 160px; margin-left: 10px; margin-bottom: 15px;" class="form-control" onchange="selectEmail()">
                        <option>직접 입력하기</option>
                        <option>gmail.com</option>
                        <option>naver.com</option>
                        <option>daum.net</option>
                        <option>nate.net</option>
                        <option>koma.kr</option>
                        <option>yahoo.com</option>
                        <option>freechal.com</option>
                        <option>lycos.co.kr</option>
                  </select>
               </td>
            </tr>
            <!-- 전화번호 -->
            <tr>
				<td>
					<div style="display: flex;">
	                    <select class="form-control" style="width: 100px; margin-bottom: 14px; border-right-color: #b7b7b7; border-radius: 8px 0 0 8px;">
	                    	<option>010</option>
	                        <option>011</option>
	                        <option>016</option>
	                        <option>017</option>
	                        <option>018</option>
	                        <option>019</option>
						</select>
	                    <c:set var="telephone" value="${mo.telephone.substring(3, 11)}"/>
	                    <input id="telephone" class="form-control" type="text" style="border-radius: 0 8px 8px 0;"
	                          placeholder="휴대폰 번호('-'없이 입력하세요)" 
	                          maxlength="8" value="${telephone}"/>
					</div>
               	</td>
            </tr>
            <!-- 주소 -->
            <tr>
               	<td>
                  	<input type="text" class="form-control"  id="postcode" name="postcode" value="${mo.postcode}">
                  	<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
                  	<input type="text" class="form-control" id="address" name="address" value="${mo.address}"><br>
					<input type="text" class="form-control" id="detailAddress" name="detailAddress" value="${mo.detailAddress}">
               	</td>
            </tr>
         </tbody>
      </table>
      <table style="margin-left: auto; margin-right: auto;">   
         <tr>
            <td>
               <input class="btn btn-success" type="submit" value="내 정보 수정하기" style="width: 400px; margin-left: auto; margin-right: auto;" />
            </td>
         </tr>
      </table>
      <table style="margin-left: auto; margin-right: auto;">   
         <tr>
            <td>
               <a class="btn btn-success" style="width: 400px; margin-left: auto; margin-right: auto;" href="pwdChangePage">비밀번호 변경</a>
            </td>
         </tr>
      </table>
      <!-- hidden 속성 -->
         <input type="hidden" name="password" value="${mo.password}"/>
         <input type="hidden" id="IDCheckOK"/>
         <input type="hidden" name="jumin">
         <input type="hidden" name="email"/>
         <input type="hidden" name="telephone"/>
         <input type="hidden" name="gender"/>
         <input type="hidden" name="age"/>
   </form>
</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- footer -->
<jsp:include page="common/footer.jsp"></jsp:include>

</body>
</html>