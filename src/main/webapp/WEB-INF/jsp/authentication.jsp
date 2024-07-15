
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Авторизация</title>

<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="<c:url value="/css/style.css"/>">
<style>
footer {
	background-color: #333;
	color: #fff;
	padding: 20px;
	text-align: center;
	position: fixed;
	bottom: 0;
	left: 0;
	width: 100%;
	z-index: 9999;
}

.text-java {
	color: #00796b;
}

.container {
	max-width: 400px;
	padding: 70px;
	margin: 100px;
	background-color: white;
	border-radius: 7px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}
</style>
</head>
<body>
	<fmt:setLocale value="${sessionScope.locale }" />
	<fmt:setBundle basename="localization.messages" />
	<jsp:include page="header.jsp"></jsp:include>



	<form action="Controller" method="post">
		<input type="hidden" name="command" value="do_auth">
		<div class="container text-center">
			<h2 class="text-java">
				<fmt:message key="local.auth.enter.site" />
			</h2>

		 	<div class="error-message" id="error-message">
			<%-- 	<c:if test="${not (authMessage eq null)}">
					<c:out value="${authMessage }" />
				</c:if>  --%>


  <c:if test="${not empty sessionScope.authMessage}">
            <div class="${sessionScope.messageType}">
                ${sessionScope.authMessage}
            </div>
            <!-- Удаление сообщения после его отображения -->
            <c:remove var="message" scope="session"/>
            <c:remove var="messageType" scope="session"/>
        </c:if>


				<%-- <c:if test="${not empty sessionScope.authMessage}">
					<div class="error-message">${sessionScope.authMessage}</div>
					<c:remove var="registrationMessage" scope="session" />
				</c:if> --%>

			</div>

			<div class="form-group">
				<label for="login"></label> <input class="form-control" type="email"
					name="login" placeholder="Email" required>
			</div>
			<div class="form-group">
				<label for="password"></label> <input class="form-control"
					type="password" name="password" placeholder="Password" required>
			</div>
			<p>
				<button class="button" type="submit">
					<fmt:message key="local.auth.enter" />
				</button>
			</p>
			<input type="hidden" name="command" value="go_to_registration_page">
			<div class="checkbox mb-3">
				<label><input type="checkbox" value="remember-me"
					name="remember-me">
				<fmt:message key="local.auth.remember.me" /></label>
			</div>
			<a href="Controller?command=go_to_registration_page"><fmt:message
					key="local.auth.registration" /></a>

		</div>
	</form>

	<footer>
		<div class="footer-content">
			<div class="about-us">
				<h3><fmt:message key="local.footer.about.us" /></h3>
				<p><fmt:message key="local.footer.text.about.us" /></p>
			</div>
			<div class="contact">
				<h3><fmt:message key="local.footer.contacts" /></h3>
				<p>Email: info@newswebsite.com</p>
				<p><fmt:message key="local.footer.phone.number" /> +7 (123) 456-7890</p>
			</div>
			<div class="footer-links">
				<h3><fmt:message key="local.footer.navigation" /></h3>
				<ul>
					<li><a
				href="Controller?command=get_news_by_category&category=Спорт"><fmt:message
						key="local.footer.sport" /></a></li>
			<li><a
				href="Controller?command=get_news_by_category&category=Технологии"><fmt:message
						key="local.footer.technology" /></a></li>
			<li><a
				href="Controller?command=get_news_by_category&category=Экономика"><fmt:message
						key="local.footer.economy" /></a></li>
				</ul>
			</div>
		</div>
		<div class="footer-bottom">&copy; 2024 <fmt:message key="local.footer.copy" />.</div>
	</footer>
</body>
</html>