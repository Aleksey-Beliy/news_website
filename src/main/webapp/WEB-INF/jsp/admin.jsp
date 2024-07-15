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

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

table, th, td {
	border: 1px solid #ddd;
}

th, td {
	padding: 15px;
	text-align: left;
}

th {
	background-color: #333;
	color: white;
}

.actions {
	display: flex;
	gap: 10px;
}

.actions button {
	padding: 5px 10px;
	border: none;
	border-radius: 3px;
	cursor: pointer;
}

.actions .edit-btn {
	background-color: #4CAF50;
	color: white;
}

.actions .delete-btn {
	background-color: #f44336;
	color: white;
}
</style>
<body>
	<fmt:setLocale value="${sessionScope.locale }" />
	<fmt:setBundle basename="localization.messages" />
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<div class="content">
			<h2>
				<fmt:message key="local.admin.add.article" />
			</h2>
			<form action="Controller" method="get" enctype="multipart/form-data">
				<input type="hidden" name="command" value="add_news">
				<div class="error-message" id="error-message">
					<c:if test="${not (addMessage eq null)}">
						<c:out value="${addMessage }" />
					</c:if>

				</div>
				<div class="form-group">
					<label for="title"><fmt:message key="local.admin.title" /></label>
					<input type="text" name="title" id="title" required>
				</div>
				<div class="form-group">
					<label for="category"><fmt:message
							key="local.admin.category" /></label> <input type="text" name="category"
						id="category" required>
				</div>
				<div class="form-group">
					<label for="content"><fmt:message key="local.admin.content" /></label>
					<textarea name="content" id="content" rows="5" required></textarea>
				</div>
				<div class="form-group">
					<label for="imgPath"><fmt:message key="local.admin.image" /></label>
					<input type="file" name="imgPath" id="imgPath">
				</div>
				<div class="form-group">
					<button type="submit">
						<fmt:message key="local.admin.add" />
					</button>
				</div>
			</form>

		</div>
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