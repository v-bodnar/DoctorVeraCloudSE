package ua.kiev.doctorvera.servlets;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import ua.kiev.doctorvera.commands.CommandLogin;
import ua.kiev.doctorvera.commands.CommandMissing;
import ua.kiev.doctorvera.commands.ICommand;

/**
 * 
 * @author Artem
 */
public class ControllerHelper {
	private static ControllerHelper instance = null;
	HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

	private ControllerHelper() {
		commands.put("login", new CommandLogin());
	}

	public ICommand getCommand(HttpServletRequest request) {
		ICommand command = commands.get(request.getParameter("command"));
		System.out.println(command);
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