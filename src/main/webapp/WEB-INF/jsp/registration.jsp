<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Регистрация</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="<c:url value="/css/style.css"/>">
<style>
.text-java {
	color: #00796b; /* Teal color */
}

.container {
	max-width: 400px;
	padding: 70px;
	margin: auto;
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
	<div class="container text-center">


		<h1 class="text-java"><fmt:message key="local.registration.registration.site"/></h1>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="do_registration">
			  <c:if test="${not empty sessionScope.registrationMessage}">
            <div class="${sessionScope.messageType}">
                ${sessionScope.registrationMessage}
            </div>
            <!-- Удаление сообщения после его отображения -->
            <c:remove var="message" scope="session"/>
            <c:remove var="messageType" scope="session"/>
        </c:if>
			<div class="form-group">
				<label for="username"></label> <input class="form-control"
					type="email" name="email" placeholder="Почта" required>
			</div>
			<div class="form-group">
				<label for="username"></label> <input class="form-control"
					type="text" name="name" placeholder="Имя" required>
			</div>
			<div class="form-group">
				<label for="username"></label> <input class="form-control"
					type="password" name="password" placeholder="Пароль" required>
			</div>
			<!-- <div class="form-group">
				<label for="password"></label> <input class="form-control"
					type="password" name="password" placeholder="Подтвердите пароль"
					required>
			</div> -->
			<p>
				<button class="btn btn-lg btn-success btn-block" type="submit"><fmt:message key="local.registration.registration"/></button>
			</p>
		</form>
	</div>
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