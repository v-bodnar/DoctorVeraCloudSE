/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.Users;
import ua.kiev.doctorvera.manager.Mapping;
import ua.kiev.doctorvera.manager.Message;
import ua.kiev.doctorvera.mysql.MySqlDaoFactory;
import ua.kiev.doctorvera.mysql.UsersMySql;

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
        UsersMySql usersDao = (UsersMySql) MySqlDaoFactory.getInstance().getDao(Users.class);
        Users incomingUser = new Users();
        Users authorizedUser = null;
        incomingUser.setUsername(request.getParameter(LOGIN));
        incomingUser.setPassword(request.getParameter(PASSWORD));
        System.out.println(incomingUser.getPassword());
        
        try {
        	Users identifiedUser = usersDao.findByNeedle("Username", incomingUser.getUsername());
        	if (incomingUser.getPassword().equals(identifiedUser.getPassword())) authorizedUser = identifiedUser;
		} catch (PersistException e) {
			e.printStackTrace();
		}
        
        if(authorizedUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", authorizedUser);
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30*60);
            //Cookie userName = new Cookie("user", currentUser.getUsername());
            //userName.setMaxAge(30*60);
            //response.addCookie(userName);
            page = Mapping.getInstance().getProperty(Mapping.Key.MAIN);
        } else {
            request.setAttribute("error", Message.getInstance().getMessage(Message.Messages.LOGIN_ERROR));
            page = Mapping.getInstance().getProperty(Mapping.Key.LOGIN_ERROR);
        }
        
        return page;
    }
}
