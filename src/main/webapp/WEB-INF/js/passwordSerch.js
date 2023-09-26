function passwordSerch() {
	let ID = $('input[name=id]').val().trim();
	let name = $('input[name=name]').val().trim();
	let jumin1 = $('input[name=jumin1]').val().trim();
	let jumin2 = $('input[name=jumin2]').val().trim();
	
	
	let jumin = jumin1 + jumin2;
	$('input[name=jumin]').val(jumin);
	
	
	if(ID.length == 0 || ID == ''){
		alert('아이디를 입력하세요');
		return false;
	}
	
	if(name.length == 0 || name == ''){
		alert('이름을 입력하세요');
		return false;
	}
	
	if(jumin.length == 0 || jumin == ''){
		alert('주민번호를 입력하세요');
		return false;
	}
	
	if(isNaN(jumin1) || isNaN(jumin2)) {
		alert('잘못된 주민번호 입니다.');
		return false
	}
	
}