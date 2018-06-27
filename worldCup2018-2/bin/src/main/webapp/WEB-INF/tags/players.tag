<%@ tag trimDirectiveWhitespaces="true"%>
<%@ attribute name="path" required="true" %>
<%@ attribute name="index" required="true" %>
<%@ attribute name="isCarton" required="true" type="java.lang.Boolean"%>
<%@ attribute name="items" required="true" type="java.util.Collection"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:bind path="${path }"><c:set var="pathValue" value="${status.value}" /></spring:bind>
<form:select id="select-${isCarton ? 'carton-' : '' }player${index}" path="${path}">
	<form:option value="" label="Aucun"/>
	<c:forEach items="${items}" var="player">
		<form:option value="${player.id}" label="${player.number} - ${player.firstName} ${player.name}"/>
	</c:forEach>
	<form:option value="" label="CSC"/>
</form:select>
