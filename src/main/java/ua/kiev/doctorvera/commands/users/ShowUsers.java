package ua.kiev.doctorvera.commands.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kiev.doctorvera.commands.ICommand;
import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.Users;
import ua.kiev.doctorvera.manager.Mapping;
import ua.kiev.doctorvera.mysql.MySqlDaoFactory;
import ua.kiev.doctorvera.mysql.UsersMySql;

public class ShowUsers implements ICommand {

		@Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	UsersMySql usersDao = (UsersMySql) MySqlDaoFactory.getInstance().getDao(Users.class);
	    	List<Users> allUsers = null;
	    	try {
				allUsers = usersDao.findAll();
			} catch (PersistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	request.setAttribute("allUsers", allUsers);
	        return Mapping.getInstance().getProperty(Mapping.Page.SHOW_USERS_PAGE);
	    }
}


