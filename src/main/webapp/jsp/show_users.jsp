<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="menu" prefix="menu" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
        <head>
        	<link rel="stylesheet" type="text/css" href="jsp/styles.css">
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
				<jsp:useBean id="message" class="ua.kiev.doctorvera.manager.Message" scope = "session"/>
				<table border="1">
					<tr>
					    <th>"${message.message}"</th>
					    <th>Имя</th>
					    <th>Отчество</th>
					    <th>Логин</th>
					    <th>Дата рождения</th>
					    <th>Номер телефона дом.</th>
					    <th>Номер телефона моб.</th>
					    <th>Адрес</th>
					    <th>Группа</th>
					    <th>Дата создания</th>
					    <th>Создал</th>
					    <th>Описание</th>
					    <th>Действия</th>
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
				</div>
				<div id = "footer">
				</div>	            
            </div>
        </body>
    </html>
