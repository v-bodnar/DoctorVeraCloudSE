<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="menu" prefix="menu" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="ua.kiev.doctorvera.manager.Message"  var ="messages"/>	
<c:set var="projectName" value="test" scope="session" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
        <head>
        	<link rel="stylesheet" type="text/css" href="/test/jsp/styles.css">
            <title><fmt:message key="APPLICATION_TITLE" bundle="${messages}"/></title>
        </head>
        <body>
			<div id="wrapper">
        		<div id="header"><div id="header-content">
		        	<h2><fmt:message key="APPLICATION_TITLE" bundle="${messages}"/></h2>
		            <hr/>
		            <div id = "login">
			            <fmt:message key="HEADER_WELCOME" bundle="${messages}"/>
			            <c:out value=" ${sessionScope.user.firstName}"/>
			            <a href ="/test/Controller?command=logout"> 
			            	<fmt:message key="LOGOUT" bundle="${messages}"/>
			            </a>
		            	<hr/>
	            	</div>
	            </div></div>
	            <div id="content">
			        <div id = "sidebar"> 
						<menu:menu/>
						<div class="clear"></div>
		            </div>
					<div id = "main">
							
					<h3><fmt:message key="UPDATE_USER_TITLE" bundle="${messages}"/></h3>
					
					<!-- Setting variables for next form  -->
						<fmt:message key="ADD_USER_FORM_LEGEND_DETAILS" bundle="${messages}" var="legendUsers"/>
						<fmt:message key="ADD_USER_FORM_LEGEND_ADDRESS" bundle="${messages}" var="legendAddress"/>
						<fmt:message key="ADD_USER_BUTTON_ADD" bundle="${messages}" var="addUser"/>
						<fmt:message key="UPDATE_USER_BUTTON_UPDATE" bundle="${messages}" var="updateUser"/>
						<fmt:message key="USERS_LOGIN" bundle="${messages}" var="login"/>
						<fmt:message key="USERS_PASSWORD" bundle="${messages}" var="password"/>
						<fmt:message key="USERS_FIRST_NAME" bundle="${messages}" var="firstName"/>
						<fmt:message key="USERS_MIDDLE_NAME" bundle="${messages}" var="middleName"/>
						<fmt:message key="USERS_LAST_NAME" bundle="${messages}" var="lastName"/>
						<fmt:message key="USERS_BIRTH_DATE" bundle="${messages}" var="birthDate"/>
						<fmt:message key="USERS_PHONE_NUMBER_HOME" bundle="${messages}" var="phoneNumberHome"/>
						<fmt:message key="USERS_PHONE_NUMBER_MOBILE" bundle="${messages}" var="phoneNumberMobile"/>
						<fmt:message key="ENTITY_DESCRIPTION" bundle="${messages}" var="description"/>
						<fmt:message key="USERS_USER_TYPE" bundle="${messages}" var="userTypes"/>
						<fmt:message key="ADDRESS_COUNTRY" bundle="${messages}" var="country"/>
						<fmt:message key="ADDRESS_REGION" bundle="${messages}" var="region"/>
						<fmt:message key="ADDRESS_CITY" bundle="${messages}" var="city"/>
						<fmt:message key="ADDRESS_ADDRESS" bundle="${messages}" var="address"/>
						<fmt:message key="ADDRESS_STREET" bundle="${messages}" var="street"/>
						<fmt:message key="ADDRESS_INDEX" bundle="${messages}" var="postIndex"/>
						
						<fmt:message key="USERS_PLACEHOLDER_LOGIN" bundle="${messages}" var="loginPlaceholder"/>
						<fmt:message key="USERS_PLACEHOLDER_PASSWORD" bundle="${messages}" var="passwordPlaceholder"/>
						<fmt:message key="USERS_PLACEHOLDER_FIRST_NAME" bundle="${messages}" var="firstNamePlaceholder"/>
						<fmt:message key="USERS_PLACEHOLDER_MIDDLE_NAME" bundle="${messages}" var="middleNamePlaceholder"/>
						<fmt:message key="USERS_PLACEHOLDER_LAST_NAME" bundle="${messages}" var="lastNamePlaceholder"/>
						<fmt:message key="USERS_PLACEHOLDER_BIRTH_DATE" bundle="${messages}" var="birthDatePlaceholder"/>
						<fmt:message key="USERS_PLACEHOLDER_PHONE_NUMBER_HOME" bundle="${messages}" var="phoneNumberHomePlaceholder"/>
						<fmt:message key="USERS_PLACEHOLDER_PHONE_NUMBER_MOBILE" bundle="${messages}" var="phoneNumberMobilePlaceholder"/>
						<fmt:message key="ADDRESS_PLACEHOLDER_COUNTRY" bundle="${messages}" var="countryPlaceholder"/>
						<fmt:message key="ADDRESS_PLACEHOLDER_REGION" bundle="${messages}" var="regionPlaceholder"/>
						<fmt:message key="ADDRESS_PLACEHOLDER_CITY" bundle="${messages}" var="cityPlaceholder"/>
						<fmt:message key="ADDRESS_PLACEHOLDER_ADDRESS" bundle="${messages}" var="addressPlaceholder"/>
						<fmt:message key="ADDRESS_PLACEHOLDER_INDEX" bundle="${messages}" var="postIndexPlaceholder"/>
					<!--  -->
					<form id="DVForm" name="addUserForm" method="POST" action="/test/Controller">
					<c:choose>
								<c:when test="${incomingUser.userId == null}">
							        <input type="hidden" name="command" value ="persistUser"/>
							    </c:when>
							    <c:otherwise>
							        <input type="hidden" name="command" value ="updateUser"/>
							    </c:otherwise>
					</c:choose>

						<fieldset>
							<legend><c:out value=" ${legendUsers}"/></legend>
							<ol>
								<li>
									<label for="login"><c:out value=" ${login}"/>: </label>
									<input id="login" name="login" type="text" placeholder="${loginPlaceholder}" value = "${incomingUser.username}" required autofocus/>
									<ul class = "error"><c:out value=" ${errors.login}"/></ul>
								</li>
								<li>
									<label for="password"><c:out value=" ${password}"/>: </label>
									<input id="password" name="password" type="text" placeholder="${passwordPlaceholder}" required/>
									<ul class = "error"><c:out value=" ${errors.password}"/></ul>
								</li>
								<li>
									<label for="lastName"><c:out value=" ${lastName}"/>: </label>
									<input id="lastName" name="lastName" type="text" placeholder="${lastNamePlaceholder}" value = "${incomingUser.lastName}" required/>
									<ul class = "error"><c:out value=" ${errors.lastName}"/></ul>
								</li>
								<li>
									<label for="firstName"><c:out value=" ${firstName}"/>: </label>
									<input id="firstName" name="firstName" type="text" placeholder="${firstNamePlaceholder}" value = "${incomingUser.firstName}" required/>
									<ul class = "error"><c:out value=" ${errors.firstName}"/></ul>
								</li>
								<li>
									<label for="middleName"><c:out value=" ${middleName}"/>: </label>
									<input id="middleName" name="middleName" type="text" placeholder="${middleNamePlaceholder}" value = "${incomingUser.middleName}" required/>
									<ul class = "error"><c:out value=" ${errors.middleName}"/></ul>
								</li>
								<li>
									<label for="birthDate"><c:out value=" ${birthDate}"/>: </label>
									<input id="birthDate" name="birthDate" type="text" placeholder="${birthDatePlaceholder}" value = "${incomingUser.birthDate}"/>
									<ul class = "error"><c:out value=" ${errors.birthDate}"/></ul>
								</li>
								<li>
									<label for="phoneNumberHome"><c:out value=" ${phoneNumberHome}"/>: </label>
									<input id="phoneNumberHome" name="phoneNumberHome" type="text" placeholder="${phoneNumberHomePlaceholder}" value = "${incomingUser.phoneNumberHome}"/>
									<ul class = "error"><c:out value=" ${errors.phoneNumberHome}"/></ul>
								</li>
								<li>
									<label for="phoneNumberMobile"><c:out value=" ${phoneNumberMobile}"/>: </label>
									<input id="phoneNumberMobile" name="phoneNumberMobile" type="text" placeholder="${phoneNumberMobilePlaceholder}" value = "${incomingUser.phoneNumberMobile}" required/>
									<ul class = "error"><c:out value=" ${errors.phoneNumberMobile}"/></ul>
								</li>
								<li>					
									<label for="userTypes"><c:out value=" ${userTypes}"/>: </label>
									<select id="userTypes" name="userTypes" required>
										<option value = "${incomingUser.userType.userTypeId}" selected="selected"><c:out value = "${incomingUser.userType.name}"/></option>
										<c:forEach var="userType" items="${allUserTypes}">
											<option value="${userType.userTypeId}"><c:out value=" ${userType.name}"/></option>
										</c:forEach>	
									</select>
									<ul class = "error"><c:out value=" ${errors.userTypes}"/></ul>
								</li>
								<li>
									<label for="description"><c:out value=" ${description}"/>: </label>
									<textarea id="description" name="description" placeholder="${description}" ><c:out value=" ${incomingUser.description}"/></textarea>
									<ul class = "error"><c:out value=" ${errors.description}"/></ul>
								</li>
							</ol>
						</fieldset>
						<fieldset>
							<legend><c:out value=" ${legendAddress}"/></legend>
							<ol>
								<li>
									<label for="country"><c:out value=" ${country}"/>: </label>
									<input id="country" type="text" name="country" placeholder="${countryPlaceholder}" value = "${incomingAddress.country}"/>
									<ul class = "error"><c:out value=" ${errors.country}"/></ul>
								</li>
								<li>
									<label for="region"><c:out value=" ${region}"/>: </label>
									<input id="region" type="text" name="region" placeholder="${regionPlaceholder}" value = "${incomingAddress.region}"/>
									<ul class = "error"><c:out value=" ${errors.region}"/></ul>
								</li>
								<li>
									<label for="city"><c:out value=" ${city}"/>: </label>
									<input id="city" type="text" name="city" placeholder="${cityPlaceholder}" value = "${incomingAddress.city}"/>
									<ul class = "error"><c:out value=" ${errors.city}"/></ul>
								</li>
								<li>
									<label for="address"><c:out value=" ${address}"/>: </label>
									<input id="address" type="text" name="address" placeholder="${addressPlaceholder}" value = "${incomingAddress.address}"/>
									<ul class = "error"><c:out value=" ${errors.address}"/></ul>
								</li>
								<li>
									<label for=postIndex><c:out value=" ${postIndex}"/>: </label>
									<input id="postIndex" type="text" name="postIndex" placeholder="${postIndexPlaceholder}" value = "${incomingAddress.postIndex}"/>
									<ul class = "error"><c:out value=" ${errors.postIndex}"/></ul>
								</li>
							</ol>
						</fieldset>
						<fieldset>
						<c:choose>
							    <c:when test="${incomingUser.userId == null}">
							        <button type="submit"><c:out value=" ${addUser}"/></button>
							    </c:when>
							    <c:otherwise>
							        <button type="submit"><c:out value=" ${updateUser}"/></button>
							    </c:otherwise>
						</c:choose>
							
						</fieldset>
					</form>
				</div>
			</div>
			<div class="push"></div>               
            </div>
            <div id="footer"><div id="footer-content"></div></div>
        </body>
    </html>
