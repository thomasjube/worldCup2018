<%@ tag trimDirectiveWhitespaces="true"%>
<%@ attribute name="path" required="true" %>
<%@ attribute name="game" required="true" type="com.tjube.model.Game"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:bind path="${path }"><c:set var="pathValue" value="${status.value}" /></spring:bind>
<form:select path="${path}" class="carton-team">
	<form:option data-index="1" value="${game.team1.id }" label="${game.team1.name }"/>
	<form:option data-index="2" value="${game.team2.id }" label="${game.team2.name }"/>
</form:select>
