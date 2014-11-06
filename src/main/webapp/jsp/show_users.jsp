<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="menu" prefix="menu" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="ua.kiev.doctorvera.manager.Message"  var ="messages"/>	
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
				<c:set var="projectName" value="test" scope="session" />
						
				
					<h3><fmt:message key="SHOW_USERS_TITLE" bundle="${messages}"/></h3>
					<table class="DVTable">
					<thead>
						<tr>
						    <th><fmt:message key="USERS_LAST_NAME" bundle="${messages}"/></th>
						    <th><fmt:message key="USERS_FIRST_NAME" bundle="${messages}"/></th>
						    <th><fmt:message key="USERS_MIDDLE_NAME" bundle="${messages}"/></th>
						    <th><fmt:message key="USERS_LOGIN" bundle="${messages}"/></th>
						    <th><fmt:message key="USERS_BIRTH_DATE" bundle="${messages}"/></th>
						    <th><fmt:message key="USERS_PHONE_NUMBER_HOME" bundle="${messages}"/></th>
						    <th><fmt:message key="USERS_PHONE_NUMBER_MOBILE" bundle="${messages}"/></th>
						    <th><fmt:message key="USERS_ADDRESS" bundle="${messages}"/></th>
						    <th><fmt:message key="USERS_USER_TYPE" bundle="${messages}"/></th>
						    <th><fmt:message key="ENTITY_DATE_CREATED" bundle="${messages}"/></th>
						    <th><fmt:message key="ENTITY_USER_CREATED" bundle="${messages}"/></th>
						    <th><fmt:message key="ENTITY_DESCRIPTION" bundle="${messages}"/></th>
						    <th><fmt:message key="SHOW_USERS_ACTIONS" bundle="${messages}"/></th>
						</tr>
					</thead>
					<c:forEach var="users" items="${allUsers}">
					<tbody>
						<tr>
						<td><c:out value="${users.lastName}"/></td>
					    <td><c:out value="${users.firstName}"/></td>
					    <td><c:out value="${users.middleName}"/></td>
					    <td><c:out value="${users.username}"/></td>
					    <td><c:out value="${users.birthDate}"/></td>
					    <td><c:out value="${users.phoneNumberHome}"/></td>
					    <td><c:out value="${users.phoneNumberMobile}"/></td>
					    <td><c:out value="${users.address.country}"/>,&nbsp;
					    <c:out value="${users.address.region}"/>,&nbsp;
					    <c:out value="${users.address.city}"/>,&nbsp;
					    <c:out value="${users.address.address}"/>,&nbsp;
					    <c:out value="${users.address.index}"/></td>
					    <td><c:out value="${users.userType.name}"/></td>
					    <td><c:out value="${users.dateCreated}"/></td>
					    <td><c:out value="${users.userCreated.lastName}"/>&nbsp;
					    <c:out value="${users.userCreated.firstName}"/>
					    </td>
					    <td><c:out value="${users.description}"/></td>
					    <td><a href = "/${projectName}/Controller?command=editUser&userToEdit=${users}">Edit</a>
					    	<a href = "/${projectName}/Controller?command=deleteUser&userToDelete=${users}">Delete</a>
					    </td>
					    </tr>
					</tbody>    
					</c:forEach>
					</table>
				
				</div>
			</div>
			<div class="push"></div>               
            </div>
            <div id="footer"><div id="footer-content"></div></div>
        </body>
    </html>
