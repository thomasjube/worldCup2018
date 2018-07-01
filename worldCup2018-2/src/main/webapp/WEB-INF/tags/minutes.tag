<%@ tag trimDirectiveWhitespaces="true"%>
<%@ attribute name="path" required="true" %>
<%@ attribute name="isProlong" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:bind path="${path }"><c:set var="pathValue" value="${status.value}" /></spring:bind>
<form:select path="${path}">
	<c:forEach begin="1" end="${not empty isProlong && isProlong ? 120 : 90 }" var="minute">
		<form:option value="${minute}" label="${minute}"/>
	</c:forEach>
</form:select>
