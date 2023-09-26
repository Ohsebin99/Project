<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>         <!-- jstl c -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>      <!-- jstl fmt -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   <!-- jstl fn -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>먹장 - 회원가입</title>

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
<script type="text/javascript" src="js/member.js"></script>
<link rel="stylesheet" href="css/member.css"/>
<script type="text/javascript" src="js/loginCheck.js" defer="defer"></script>

</head>
<body>
<!-- header -->
<jsp:include page="common/header.jsp"></jsp:include>

<!-- 메인 시작 -->
<div class="container" style="margin-top: 20px;">
	<form action="member" method="post" onsubmit="return formCheck()" enctype="multipart/form-data">
		<table class="table table-bordered" style="width: 500px; margin-left: auto; margin-right: auto;">
			<thead>
				<tr class="success">
					<th colspan="3" style="text-align: center; font-size: 25px;">회원가입</th>
				</tr>
			</thead>
		<tbody>
			<div class="col-lg-12">
				<div class="row mb-3">
			    	<div class="col-md-4"></div>
			    		<div class="col-md-4">
			         		<h5 class="mb-3 text-center">파일 업로드</h5>
			         				<input class="form-control" type="file" accept="image/*" name="fileName" onchange="photoView(event)"/>
			      		</div>
			      	<div class="col-md-4"></div>
			   	</div>
			   	<div class="row mb-3">
			    	<div class="col-lg-3"></div>
			      	<div class="col-lg-6">
			      		<div class="text-center">
			        		<img id="output" class="img-fluid" style="height: 130px; width: 130px; border-radius: 30px;"/><br/>
			        	</div>
			      	</div>
			      	<div class="col-lg-3"></div>
			   	</div>
			</div>
            <!-- 아이디 -->
            <tr>
               <td colspan="2">
                  <input id="id" class="form-control" type="text" name="id" value="shjy177"
                     placeholder="아이디" autocomplete="off"   onkeyup="idCheck()"/>
                  <!-- 아이디 오류 문구 출력 -->
                  <h5 id="idChkMessage1" style="color: red; font-weight: bold"></h5>
                  <h5 id="idChkMessage2" style="color: lime; font-weight: bold"></h5>
               </td>
            </tr>
            <!-- 비밀번호 -->
            <tr>
               <td colspan="2">
                  <input id="password" class="form-control" type="password" name="password" value="123456a!"
                     placeholder="비밀번호" autocomplete="off" onkeyup="pwdCheck1()"/>
                  <!-- 비밀번호 오류 문구 출력 -->
                  <h5 id="pwdChkMessage1" style="color: red; font-weight: bold"></h5>
            <!-- 비밀번호 확인 -->
                  <input id="password2" class="form-control" type="password" name="password2" value="123456a!"
                     placeholder="비밀번호 확인" autocomplete="off" onkeyup="pwdCheck2()"/>
                  <h5 id="pwdChkMessage2" style="color: red; font-weight: bold"></h5>
               </td>
            </tr>
            <!-- 닉네임 -->
            <tr>
               <td colspan="2">
                  <input id="nickname" class="form-control" type="text" name="nickName" value="무우명"
                     placeholder="닉네임" autocomplete="off"/>
               </td>
            <!-- 이름 -->
            <tr>
               <td colspan="2">
                  <input id="name" class="form-control" type="text" name="name" value="고대일"
                     placeholder="이름" autocomplete="off"/>
               </td>
            </tr>
            <!-- 주민번호 -->
            <tr>
               <td colspan="3" style="display: flex;">
                  <input style="width: 230px;" type="text" class="form-control" name="jumin1" maxlength="6"  placeholder="주민번호 앞자리" value="950504"/>
                  <b style="margin-top: 10px;">&nbsp;&nbsp;-&nbsp;&nbsp;</b>
                  <input style="width: 230px;" type="password" class="form-control" name="jumin2" maxlength="7"  placeholder="뒷자리" value="1057834"/> 
               </td>
            </tr>
            <!-- 이메일 -->
            <tr>
               <td colspan="2" style="display: flex;">
                  <input  type="text" name="email1" class="form-control" style="width: 130px;" value="shjy177"
                     autocomplete="off" placeholder="이메일"/>
                  <b style="margin-top: 10px; margin-left: 15px;">@</b>
                  <select style="width: 160px; margin-left: 10px; margin-bottom: 15px;" class="form-control" onchange="selectEmail()">
                        <option>gmail.com</option>
                        <option>naver.com</option>
                        <option>daum.net</option>
                        <option>nate.net</option>
                        <option selected="selected">직접 입력하기</option>
                  </select>
                  <input type="text" name="email2" class="form-control" autocomplete="off" placeholder="직접 입력하기" style="width: 130px; height: 46px; margin-left: 20px;"/>
               </td>
            </tr>
            <!-- 전화번호 -->
            <tr>
               <td>
                  <div style="display: flex;">
                     <select class="form-control" style="width: 100px; margin-bottom: 15px;">
                        <option>010</option>
                        <option>011</option>
                        <option>016</option>
                        <option>017</option>
                        <option>018</option>
                        <option>019</option>
                     </select>
                     &nbsp;&nbsp;&nbsp;
                     <input id="telephone" class="form-control" type="text" value="63415131"
                        placeholder="휴대폰 번호('-'없이 입력하세요)" maxlength="8" autocomplete="off"/>
                  </div>
               </td>
            </tr>
            <!-- 주소 -->
            <tr>
               <td>
                  <input type="button" onclick="execDaumPostcode()" value="주소 찾기"><br>
				  <input type="text" class="form-control" id="postcode" name="postcode" placeholder="우편번호" readonly="readonly" value="02807">
                  <input type="text" class="form-control" id="address" name="address" placeholder="주소" readonly="readonly" value="성동구 용답19길 15 - 1">
                  <input type="text" class="form-control" id="detailAddress" name="detailAddress" placeholder="상세주소" value="1층">
                  
               </td>
            </tr>
         </tbody>
      </table>
      <table style="margin-left: auto; margin-right: auto;">   
         <tr>
            <td>
               <input class="btn btn-success" type="submit" value="가입완료"
               style="width: 400px; margin-left: auto; margin-right: auto;" />
            </td>
         </tr>
      </table>
      <!-- hidden 속성 -->
         <input type="hidden" id="IDCheckOK"/>
         <input type="hidden" name="jumin">
         <input type="hidden" name="email"/>
         <input type="hidden" name="telephone"/>
         <input type="hidden" name="gender"/>
         <input type="hidden" name="age"/>
         <input type="hidden" name="address"/>
   </form>
</div>

<!-- 회원 저장 모달 창 -->
<div id="messageModal" class="modal fade" role="dialog" aria-hidden="true">
   <div class="vertical-alignment-helper">
      <div class="modal-dialog vertival-center">
         <!-- 모달 창의 종류(색상)를 설정한다. -->
         <!-- messageCheck라는 id를 추가하고 class를 제거한다. -->
         <div id="messageCheck" class="modal-content panel-warning">
            <!-- 헤더 -->
            <div class="modal-header panel-heading">
               <!-- 헤더 오른쪽 상단에 "X" 버튼 표시 -->
               <button class="close btn btn-lg" type="button" data-dismiss="modal">
                  <span aria-hidden="true">&times;</span>
                  <span class="sr-only">Close</span>
               </button>
               <!-- messageType이라는 id를 추가한다. -->
               <h4 id="messageType" class="modal-title">
                  에러 메시지
               </h4>
            </div>
            <!-- 바디 -->
            <!-- messageContent이라는 id를 추가한다. -->
            <div id="messageContent" class="modal-boby">
               <%-- ${messageContent} --%>
            </div>
            <!-- 풋터 -->
            <div class="modal-footer">
               <button class="btn btn-primary" type="button" data-dismiss="modal">닫기</button>
            </div>
         </div>
      </div>
   </div>
</div>
<!-- footer -->
<jsp:include page="common/footer.jsp"></jsp:include>
   
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>

