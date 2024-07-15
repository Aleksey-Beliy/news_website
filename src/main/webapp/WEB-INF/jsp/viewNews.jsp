<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<c:url value="/css/style.css"/>">
<title>TopNews</title>
</head>
<style>
</style>
<body>

	<fmt:setLocale value="${sessionScope.locale }" />
	<fmt:setBundle basename="localization.messages" />
	<jsp:include page="header.jsp"></jsp:include>

	<main>
		<div class="news-container">
			<section class="news-block">
				<div class="article-content">
					<%-- <p class="content">${requestScope.article.title}</p> --%>
					<img src="${requestScope.article.imgPath}" alt="Images"
						class="news-image">
					<p class="category">${requestScope.article.category}</p>
					<p>${requestScope.article.content}</p>
				</div>
			</section>
		</div>
	</main>
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
