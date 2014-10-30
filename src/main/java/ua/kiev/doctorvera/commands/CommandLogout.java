/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.kiev.doctorvera.manager.Mapping;

/**
 *
 * @author oper4
 */
public class CommandLogout implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	session.removeAttribute("user");
    	session.invalidate();
    	String page = Mapping.getInstance().getProperty(Mapping.Key.LOGIN_PAGE);
    	request.setAttribute("command", "logout");
    	return page;
    }
    
}
