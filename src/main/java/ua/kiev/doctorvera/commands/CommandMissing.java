/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kiev.doctorvera.manager.Mapping;
import ua.kiev.doctorvera.manager.Message;

/**
 *
 * @author MAXIM
 */
public class CommandMissing implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
    	request.setAttribute("error", Message.getInstance().getMessage(Message.Messages.LOGIN_ERROR));
    	request.setAttribute("command", Message.getInstance().getMessage(Message.Messages.COMMAND_MISSING));
    	return Mapping.getInstance().getProperty(Mapping.Key.LOGIN_PAGE);
    }
}
