<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="menu" prefix="menu" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
        <head>
        	<link rel="stylesheet" type="text/css" href="styles.css">
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
					Тут будет контент
				</div>
				<div id = "footer">
				</div>	            
            </div>
        </body>
    </html>
