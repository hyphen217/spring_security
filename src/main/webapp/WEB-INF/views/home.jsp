<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!-- jsp파일에서 로그인 세션 정보 가져와서 〇〇님 환영합니다 -->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>메인페이지</title>
</head>
<body>
	<h1>메인페이지</h1>
	<sec:authorize access="isAnonymous()"> <!-- 로그인이 안된 사람 -->
		<p>
			<a href="<c:url value="/login/loginForm" />">로그인</a>
		</p>
	</sec:authorize>

	<sec:authorize access="isAuthenticated()">
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="로그아웃" />
		</form:form>
		<p><a href="<c:url value="/loginInfo" />">로그인 정보 확인 방법3 가지</a></p>
		<p>principal: <sec:authentication property="principal"/></p> <!-- property="principal" ⇒ getPrincipal() : principal: edu.global.ex.vo.UserDetailsVO@12022b7b -->
		
		
		
		<p><sec:authentication property="principal.username"/>님 환영합니다.</p>
		<p><sec:authentication property="principal.authorities"/></p>
		<p><sec:authentication property="principal.cart"/></p>
		
<sec:authorize access="hasRole('ADMIN')">
        <p>당신은 관리자 입니다.</p>
   </sec:authorize>
   
   <sec:authorize access="hasRole('USER')">
        당신은 일반 유저 입니다.
   </sec:authorize>
   
</sec:authorize>

	<h3>
		[<a href="<c:url value="/add/addForm" />">회원가입</a>] 
		[<a href="<c:url value="/user/userHome" />">유저 홈</a>]
		[<a href="<c:url value="/admin/adminHome" />">관리자 홈</a>]
	</h3>
</body>
</html>