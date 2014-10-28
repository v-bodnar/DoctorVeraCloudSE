/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.commands;

import ua.kiev.doctorvera.manager.Config;
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
        return Config.getInstance().getProperty(Config.LOGIN_PAGE);
    }
}
