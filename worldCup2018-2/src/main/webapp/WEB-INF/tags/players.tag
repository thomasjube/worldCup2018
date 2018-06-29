<%@ tag trimDirectiveWhitespaces="true"%>
<%@ attribute name="path" required="true" %>
<%@ attribute name="index" required="true" %>
<%@ attribute name="isGoal" required="false" type="java.lang.Boolean"%>
<%@ attribute name="isCarton" required="true" type="java.lang.Boolean"%>
<%@ attribute name="items" required="true" type="java.util.Collection"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:bind path="${path }"><c:set var="pathValue" value="${status.value}" /></spring:bind>
<form:select id="select-${isCarton ? 'carton-' : '' }player${index}" path="${path}">
	<c:if test="${empty isGoal or !isGoal }">
		<form:option value="" label="Aucun"/>
	</c:if>
	<c:if test="${not empty isGoal and isGoal }">
		<form:option value="" label="CSC"/>
	</c:if>
	<c:forEach items="${items}" var="player">
		<form:option value="${player.id}" label="${player.number} - ${player.firstName} ${player.name}"/>
	</c:forEach>
</form:select>
