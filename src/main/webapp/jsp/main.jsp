<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="menu" prefix="menu" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="ua.kiev.doctorvera.manager.Message"  var ="messages"/>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
        <head>
        	<link rel="stylesheet" type="text/css" href="jsp/styles.css">
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
						Тут будет контент
					</div>
				</div>
				<div class="push"></div>            
            </div>
            <div id="footer"><div id="footer-content"></div></div>
        </body>
    </html>
