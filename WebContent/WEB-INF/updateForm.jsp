<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>서웅기 전화번호 리스트</h1>

	<p>
		수정 화면 입니다.
		아래 항목을 수정하고 "수정" 버튼을 클릭하세요
	</p>
	
	<form action="/phonebook2/pbc" method="get">
		이름(name): <input type="text" name="name" value=""><br>
		핸드폰(hp): <input type="text" name="hp" value=""><br>
		회사(company): <input type="text" name="company" value=""><br>
		
		코드(id):<input type="hidden" name="id" value=""><br>
		action: <input type="text" name="action" value="update">
		<button type="submit">수정</button>
	</form>
	
	<br>
	<a href="/phonebook2/pbc?action=list">리스트 바로 가기</a>
	
</body>
</html>