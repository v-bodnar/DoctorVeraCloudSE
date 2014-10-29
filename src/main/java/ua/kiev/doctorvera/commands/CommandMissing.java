/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.commands;

import ua.kiev.doctorvera.manager.Config;
import ua.kiev.doctorvera.manager.Message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MAXIM
 */
public class CommandMissing implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
    	request.setAttribute("error", Message.getInstance().getProperty(Message.LOGIN_ERROR));
    	request.setAttribute("command", Message.getInstance().getProperty(Message.COMMAND_MISSING));
    	return Config.getInstance().getProperty(Config.LOGIN_PAGE);
    }
}
