// 파일 업로드 함수
function photoView(event) {
	$('#output').attr('src', URL.createObjectURL(event.target.files[0]));
}

function updateValue(value) {
    document.getElementById("limitValue").textContent = value;
}

function photoView(event) {
   $('#output').attr('src', URL.createObjectURL(event.target.files[0]));
}

function updateValue(value) {
    document.getElementById("limitValue").textContent = value;
}

//기본 이미지
function defaultImg() {
    // 기본 이미지의 파일 경로를 설정합니다.
     let defaultImagePath = 'upload/memberphoto/default.jpg';
     let defaultImagePath = 'upload/memberphoto/https://ssl.pstatic.net/static/pwe/address/img_profile.png';
     let defaultImgCheck = $('input[name=defaultImgCheck]').val();
     $('input[name=defaultImgCheck]').val('N');
     
     // 이미지 요소의 src 속성에 기본 이미지의 경로를 설정합니다.
     document.getElementById('output').src = defaultImagePath;
     
     
     $('#fileInput').val('');
     
     
 }
//   이메일 입력 ===================================
function selectEmail() {
   $('input[name=email2').val($('select').val());
   
   if($('select').eq(1).val() == '직접 입력하기'){
      $('input[name=email2]').val('');
      $('input[name=email2]').focus();
   }
}

//   form check 
//   ================================================================================
function formCheck() {

   let j1 = $('input[name=jumin1]').val().trim();
   let j2 = $('input[name=jumin2]').val().trim();
   let email1 = $('input[name=email1]').val().trim();
   let email2 = $('input[name=email2]').val().trim();
   let telephone1 = $('#telephone').val().trim();   
   let telephone2 = $('select').eq(1).val();
   let postcode = $('#postcode').val();
   let address = $('#address').val();
   let detailAddress = $('#detailAddress').val();
   let nickname = $('#nickname').val().trim();
   let jumin = j1 + j2;
   let email = email1 + '@' + email2;
   let telephone = telephone2 + telephone1;
   
   $('input[name=jumin]').val(jumin);
   $('input[name=email]').val(email);
   $('input[name=telephone]').val(telephone);
   
//   공백 체크 ===================================
   if(nickname.length == 0 || nickname == '' ){
      alert('닉네임을 입력하세요')
      return false;
   }
   if(email1.length == 0 || email1 == '' ){
      alert('이메일을 입력하세요')
      return false;
   }
   if(email2.length == 0 || email2 == '' ){
      alert('이메일을 입력하세요')
      return false;
   }
   if(telephone1.length == 0 || telephone1 == ''){
      alert('전화번호를 입력하세요')
      return false;
   }
   if(address.lenght == 0 || address == ''){
      alert('주소를 입력하세요')
      return false;
   }
   if(detailAddress.lenght == 0 || detailAddress == ''){
      alert('상세주소를 입력하세요')
      return false;
   }
   

//   성별 나이 체크 ===================================
   let date = new Date();

   let year = j1.substr(0,2);
//   console.log(year);
   let currentYear = date.getFullYear();
//   console.log(currentYear);
   let gender = j2.charAt(0);
   
   if (gender == '1' || gender == '3' || gender == '5') {
      $('input[name=gender]').val('남자');
   } else if (gender == '2' || gender == '4' ||  gender == '6') {
      $('input[name=gender]').val('여자');
   }
   
   if (gender == '1' || gender == '2' || gender == '5') {
      year = parseInt(year) + 1900;
   } else if (gender == '3' || gender == '4' ||  gender == '6') {
      year = parseInt(year) + 2000;
   }
   let age = currentYear - year + 1;
   $('input[name=age]').val(age);

   
//   아이디 비밀번호 체크 ==============================

   idCheck();
   pwdCheck1();
   pwdCheck2();
   
   return true;
}

// 다음 주소 API
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
        	// 주소창 초기화
        	document.getElementById("address").value = '';
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                    // 조합된 참고항목을 주소변수에 넣는다.
                    addr += extraAddr;
                }
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").value = '';
            document.getElementById("detailAddress").focus();
        }
    }).open();
}



function loginCheck (){
   alert('로그인이 필요한 서비스입니다.');
}