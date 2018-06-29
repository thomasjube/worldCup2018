<%@ tag trimDirectiveWhitespaces="true"%>
<%@ attribute name="path" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:bind path="${path }"><c:set var="pathValue" value="${status.value}" /></spring:bind>
<form:select path="${path}">
	<form:option value="YELLOW_CARD" label="Carton jaune"/>
	<form:option value="RED_CARD" label="Carton rouge"/>
</form:select>
