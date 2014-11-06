package ua.kiev.doctorvera.commands.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kiev.doctorvera.commands.ICommand;
import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.UserTypes;
import ua.kiev.doctorvera.manager.Mapping;
import ua.kiev.doctorvera.mysql.MySqlDaoFactory;
import ua.kiev.doctorvera.mysql.UserTypesMySql;

public class AddUser implements ICommand {

		@Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	UserTypesMySql usersDao = (UserTypesMySql) MySqlDaoFactory.getInstance().getDao(UserTypes.class);
	    	List<UserTypes> allUserTypes = null;
	    	try {
	    		allUserTypes = usersDao.findAll();
			} catch (PersistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	request.setAttribute("allUserTypes", allUserTypes);
	        return Mapping.getInstance().getProperty(Mapping.Page.ADD_USER_PAGE);
	    }
}


