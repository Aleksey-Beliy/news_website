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
<link rel="stylesheet" href="<c:url value='/css/style.css' />">

<title>Edit Article</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}

.container {
	width: 80%;
	margin: auto;
	overflow: hidden;
}

.content {
	padding: 20px;
	background: #fff;
	border-radius: 5px;
	margin-top: 20px;
}

.content h2 {
	margin-top: 0;
}

.form-group {
	margin-bottom: 15px;
}

.form-group label {
	display: block;
	margin-bottom: 5px;
}

.form-group input, .form-group textarea {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.form-group button {
	padding: 10px 15px;
	background: #333;
	color: #fff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.form-group button:hover {
	background: #555;
}
</style>
</head>
<body>
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.messages" />
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">
		<div class="content">
			<h2>Edit Article</h2>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="edit_news"> <input
					type="hidden" name="id" value="${article.id}">

				<div class="form-group">
					<label for="title">Title:</label><br> <input type="text"
						id="title" name="title" value="${article.title}" required><br>
					<br>
				</div>

				<div class="form-group">
					<label for="category">Category:</label><br> <input type="text"
						id="category" name="category" value="${article.category}" required><br>
					<br>
				</div>

				<div class="form-group">
					<label for="content">Content:</label><br>
					<textarea id="content" name="content" rows="10" cols="50" required>${article.content}</textarea>
					<br> <br>
				</div>

				<div class="form-group">
					<button type="submit">Save Changes</button>
				</div>
			</form>
		</div>
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
