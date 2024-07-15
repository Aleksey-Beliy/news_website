<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="styles.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<c:url value="css/style.css"/>">
<title>TopNews</title>
<style>
.btn-delete {
	display: inline-block;
	padding: 10px 20px;
	background-color: #dc3545; /* красный цвет */
	color: #fff;
	text-decoration: none;
	border: none;
	border-radius: 5px;
}

.btn-edit {
	display: inline-block;
	padding: 10px 20px;
	background-color: #000; /* черный цвет */
	color: #fff;
	text-decoration: none;
	border: none;
	border-radius: 5px;
}
</style>
</head>
<body>
	<fmt:setLocale value="${sessionScope.locale }" />
	<fmt:setBundle basename="localization.messages" />
	<jsp:include page="header.jsp"></jsp:include>

	<div class="news-container">
		<c:forEach var="news" items="${requestScope.mainNews}">
			<section class="news-block">
				<div class="article-content">
					<img src="${news.imgPath}" alt="Images" class="news-image">
					<h2>${news.title}</h2>
					<p class="category">${news.category}</p>
					<c:choose>
						<c:when
							test="${sessionScope.user.role == 'reader' || sessionScope.user.role == 'admin' || sessionScope.user.role == 'moderator'}">
							<p>
								<a href="Controller?command=view_news&id=${news.id}"
									class="read-more"><fmt:message
										key="local.article.read.more" /></a>
							</p>
						</c:when>
						<c:otherwise>
							<p>
								<a
									href="${pageContext.request.contextPath}/Controller?command=go_to_authentication_page"
									class="read-more"><fmt:message
										key="local.article.read.more" /></a>
							</p>
						</c:otherwise>
					</c:choose>
					<!-- Добавляем кнопки удаления и изменения новости -->
					<c:if
						test="${sessionScope.user.role == 'admin' or sessionScope.user.role == 'moderator'}">
						<p>
							<a href="Controller?command=go_to_edit_page&id=${news.id}"
								class="btn-edit"><fmt:message key="local.article.edit" /></a>
						</p>
						<%--  <p><a href="Controller?command=edit_news&id=${news.id}" class="btn-edit">Изменить</a></p> --%>
						<p>
							<a href="Controller?command=delete_news&id=${news.id}"
								class="btn-delete"><fmt:message key="local.article.delete" /></a>
						</p>
					</c:if>
				</div>
			</section>
		</c:forEach>
	</div>

	<footer>
		<div class="footer-content">
			<div class="about-us">
				<h3>
					<fmt:message key="local.footer.about.us" />
				</h3>
				<p>
					<fmt:message key="local.footer.text.about.us" />
				</p>
			</div>
			<div class="contact">
				<h3>
					<fmt:message key="local.footer.contacts" />
				</h3>
				<p>Email: info@newswebsite.com</p>
				<p>
					<fmt:message key="local.footer.phone.number" />
					+7 (123) 456-7890
				</p>
			</div>
			<div class="footer-links">
				<h3>
					<fmt:message key="local.footer.navigation" />
				</h3>
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
		<div class="footer-bottom">
			&copy; 2024
			<fmt:message key="local.footer.copy" />
			.
		</div>
	</footer>
</body>
</html>
