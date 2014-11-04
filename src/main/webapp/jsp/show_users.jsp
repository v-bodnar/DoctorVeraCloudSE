<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="menu" prefix="menu" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
        <head>
        	<link rel="stylesheet" type="text/css" href="/test/jsp/styles.css">
            <title> Doctor Vera Cloud Storage </title>
        </head>
        <body>
        	<div id="main_container">
        		<div id="header">
		        	<h2>Doctor Vera Cloud Storage</h2>
		            <hr/>
		            <div id = "login">
			            <c:out value="Добро пожаловать, ${sessionScope.user.firstName}!"/>
			            <a href ="/test/Controller?command=logout"> Logout</a>
		            	<hr/>
	            	</div>
	            </div>
		        <div id = "menu"> 
					<menu:menu/>
	            </div>
				<div id = "content">
				<c:set var="projectName" value="test" scope="session" />
				<fmt:bundle basename="ua.kiev.doctorvera.manager.Message">			
				
					<h3><fmt:message key="SHOW_USERS_TITLE"/></h3>
					<table>
						<tr>
						    <th><fmt:message key="USERS_LAST_NAME"/></th>
						    <th><fmt:message key="USERS_FIRST_NAME"/></th>
						    <th><fmt:message key="USERS_MIDDLE_NAME"/></th>
						    <th><fmt:message key="USERS_LOGIN"/></th>
						    <th><fmt:message key="USERS_BIRTH_DATE"/></th>
						    <th><fmt:message key="USERS_PHONE_NUMBER_HOME"/></th>
						    <th><fmt:message key="USERS_PHONE_NUMBER_MOBILE"/></th>
						    <th><fmt:message key="USERS_ADDRESS"/></th>
						    <th><fmt:message key="USERS_USER_TYPE"/></th>
						    <th><fmt:message key="ENTITY_DATE_CREATED"/></th>
						    <th><fmt:message key="ENTITY_USER_CREATED"/></th>
						    <th><fmt:message key="ENTITY_DESCRIPTION"/></th>
						    <th><fmt:message key="SHOW_USERS_ACTIONS"/></th>
						</tr>
					<c:forEach var="users" items="${allUsers}">
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
					</c:forEach>
					</table>
				</fmt:bundle>
				
				</div>
				<div id = "footer">
				</div>	            
            </div>
        </body>
    </html>
