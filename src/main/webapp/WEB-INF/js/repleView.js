/* 다음 API */
// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places(); 

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

document.addEventListener("DOMContentLoaded", function() {
   
    var searchButton = document.getElementById("searchButton");
    searchButton.addEventListener("click", function() {
        var searchValue = document.getElementById("searchInput").value;
		console.log("Search value: " + searchValue);
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(searchValue, function(result, status) {

		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {

		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });

		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">모임 장소</div>'
		        });
		        infowindow.open(map, marker);

		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});    
    });
});





$(() => {
  let idx = $('input[name=originIdx]').val();
   $.ajax({
      type: "post",             // 요청 방식
      url: "repleServlet",      // 요청할 서블릿
      data: {   				// 서블릿으로 전송할 데이터
         "idx" : idx
      },
      dataType: "text",
      success: res => {
           console.log('요청 성공: '+ res);
           
           if(res != '0'){
        	  $('form[action=repleInsert]').attr('hidden', 'hidden');
           }
        },
        error: e => console.log('요청 실패: ', e.status)
   });
});


//	방장의 승인 및 거절 버튼 확인 메시지
function join(word, idx, currentPage, repleIdx){
	var result = confirm(word + "하시겠습니까?");
	if(result){
		if(word =="승인"){
			location.href = "joinOK?idx=" +idx+ "&currentPage=" +currentPage+ "&repleIdx=" +repleIdx;
		} else if(word =="거절"){
			location.href = "joinNO?idx=" +idx+ "&currentPage=" +currentPage+ "&repleIdx=" +repleIdx;
		} else if(word =="파티 취소"){
			location.href = "joinCancel?idx=" +idx+ "&currentPage=" +currentPage+ "&repleIdx=" +repleIdx;
		}
	}
}
