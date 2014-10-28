/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.kiev.doctorvera.entity.Users;
import ua.kiev.doctorvera.logic.LoginLogic;
import ua.kiev.doctorvera.manager.Config;
import ua.kiev.doctorvera.manager.Message;

/**
 *
 * @author MAXIM
 */
public class CommandLogin implements ICommand {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        Users user = new Users();
        user.setUsername(request.getParameter(LOGIN));
        user.setPassword(request.getParameter(PASSWORD));
        Users currentUser = new LoginLogic().checkLogin(user);
        
        if(currentUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", currentUser);
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30*60);
            Cookie userName = new Cookie("user", currentUser.getUsername());
            userName.setMaxAge(30*60);
            response.addCookie(userName);
            page = Config.getInstance().getProperty(Config.MAIN);
        } else {
            request.setAttribute("error", Message.getInstance().getProperty(Message.LOGIN_ERROR));
            page = Config.getInstance().getProperty(Config.ERROR);
        }
        
        return page;
    }
}
