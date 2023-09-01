// (1) 회원정보 수정
function update(userId, event) {
	
//	console.log(event); // 이벤트.. 최유정
    event.preventDefault(); //form 태그 action 막기
	
	
	let data = $("#profileUpdate").serialize();
	
	console.log(data);
	
	$.ajax({
		type : "PUT",
		url : `/api/user/${userId}`,
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		dataType : "json"
	}).done(res =>{ // HttpStatus 상태코드 200번대 일때
		console.log("update 성공", res);
		
		//location.href = `/user/${userId}`
	}).fail(error =>{ // HttpStatus 상태코드 200번대가 아닐때
		console.log("update 실패", error);
		console.log("update 실패", error.responseJSON.data);
		console.log("update 실패", error.responseJSON.data.website);
		
	});
	
}// function end