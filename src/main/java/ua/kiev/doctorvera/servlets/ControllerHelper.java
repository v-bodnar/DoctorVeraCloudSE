package ua.kiev.doctorvera.servlets;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import ua.kiev.doctorvera.commands.CommandLogin;
import ua.kiev.doctorvera.commands.CommandLogout;
import ua.kiev.doctorvera.commands.CommandMissing;
import ua.kiev.doctorvera.commands.ICommand;
import ua.kiev.doctorvera.commands.users.ShowUsers;

public class ControllerHelper {
	private static ControllerHelper instance = null;
	HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

	private ControllerHelper() {
		commands.put("login", new CommandLogin());
		commands.put("logout", new CommandLogout());
		commands.put("showUsers", new ShowUsers());
		
	}

	public ICommand getCommand(HttpServletRequest request) {
		System.out.println("command:" + request.getParameter("command"));
		ICommand command = commands.get(request.getParameter("command"));
		if (command == null) {
			command = new CommandMissing();
		}
		return command;
	}

	public static ControllerHelper getInstance() {
		if (instance == null) {
			instance = new ControllerHelper();
		}
		return instance;
	}
}