<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
좁은의미의 JavaBean 규칙 : 
생성자가 없거나 빈생성자를 가지며
getter/setter를 가진 클래스의 객체
-->
<h2>SAMPLE DTO ${sampleDTO }</h2>
<!-- 
해당 타입은 JavaBean 규칙에 맞지 않기때문에 화면에 전달 되지 않음 
화면에 전달하고 싶을 경우 @ModelAttribute를 사용하여 강제로 모델에 담아 전달하도록해야 함
꼭 값을 지정해줘야함
-->
<h2>PAGE ${page }</h2>
</body>
</html>