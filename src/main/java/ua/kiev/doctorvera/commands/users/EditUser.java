package ua.kiev.doctorvera.commands.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kiev.doctorvera.commands.ICommand;
import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.Address;
import ua.kiev.doctorvera.entity.Users;
import ua.kiev.doctorvera.manager.Mapping;
import ua.kiev.doctorvera.mysql.MySqlDaoFactory;
import ua.kiev.doctorvera.mysql.UsersMySql;

public class EditUser implements ICommand {

		@Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	UsersMySql usersDao = (UsersMySql) MySqlDaoFactory.getInstance().getDao(Users.class);
	    	Users incomingUser = null;
	    	Address incomingAddress = null;
	    	try {
	    		incomingUser = usersDao.findByPK(Integer.parseInt(request.getParameter("userIdToEdit")));
	    		incomingAddress = incomingUser.getAddress();
			} catch (PersistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            request.setAttribute("incomingUser", incomingUser);
            request.setAttribute("incomingAddress", incomingAddress);
	        return Mapping.getInstance().getProperty(Mapping.Page.EDIT_USER_PAGE);
	    }
}


