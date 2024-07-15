<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>


<fmt:setLocale value="${sessionScope.locale }" />
<fmt:setBundle basename="localization.messages" />
<header>
	<div>
		<a href="Controller?command=change_locale&lang=en">EN</a> | <a
			href="Controller?command=change_locale&lang=ru">RU</a>
	</div>

	<nav>
		<ul>

			<li><a href="Controller?command=go_to_start_page"><fmt:message
						key="local.header.news" /></a></li>
			<li><a
				href="Controller?command=get_news_by_category&category=Спорт"><fmt:message
						key="local.footer.sport" /></a></li>
			<li><a
				href="Controller?command=get_news_by_category&category=Технологии"><fmt:message
						key="local.footer.technology" /></a></li>
			<li><a
				href="Controller?command=get_news_by_category&category=Экономика"><fmt:message
						key="local.footer.economy" /></a></li>
			<li><a href="Controller?command=go_to_authentication_page"><fmt:message
						key="local.header.login" /></a></li>
			<li><a href="Controller?command=logout"><fmt:message
						key="local.header.logout" /></a></li>
			<c:if
				test="${sessionScope.user.role == 'admin' or sessionScope.user.role == 'moderator'}">
				<li><a href="Controller?command=go_to_admin_page"><fmt:message
							key="local.header.add.article" /></a></li>

			</c:if>
			<c:if test="${sessionScope.user.role == 'admin'}">
				<li><a href="Controller?command=go_to_assign_role_page"><fmt:message
							key="local.header.role.managment" /></a></li>
			</c:if>

		</ul>
	</nav>
</header>