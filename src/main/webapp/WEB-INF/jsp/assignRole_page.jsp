<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Find User</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
<style>
.content-container {
	margin-top: 2cm; 
}
</style>
</head>
<body>
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.messages" />
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container mt-5 content-container">
		<div class="container mt-5">
			<div class="error-message" id="error-message">
				<c:if test="${not (error eq null)}">
					<c:out value="${error}" />
				</c:if>

			</div>
			<h2><fmt:message
							key="local.role.managment.find" /></h2>
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="find_user_by_email">
				<div class="form-group">
					<label for="email">Email:</label> <input type="email"
						class="form-control" id="email" name="email" required>
				</div>
				<button type="submit" class="btn btn-primary"><fmt:message
							key="local.role.managment.search" /></button>
			</form>

			<c:if test="${not empty user}">
				<div class="mt-5">
					<h3><fmt:message
							key="local.role.managment.details" /></h3>
					<p>Email: ${user.name}</p>
					<p><fmt:message
							key="local.role.managment.current.role" />: ${user.role}</p>

					<form action="Controller" method="post">
						<input type="hidden" name="command" value="assign_role"> <input
							type="hidden" name="email" value="${user.name}">
						<div class="form-group">
							<label for="role"><fmt:message
							key="local.role.managment.assign" />:</label> <select
								class="form-control" id="role" name="role">
								<option value="reader"
									<c:if test="${user.role == 'reader'}">selected</c:if>>Reader</option>
								<option value="moderator"
									<c:if test="${user.role == 'moderator'}">selected</c:if>>Moderator</option>
							</select>
						</div>
						<button type="submit" class="btn btn-success"><fmt:message
							key="local.role.managment.assign" /></button>
					</form>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>
