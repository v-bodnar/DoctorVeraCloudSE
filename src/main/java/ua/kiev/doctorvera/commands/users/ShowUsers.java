package ua.kiev.doctorvera.commands.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kiev.doctorvera.commands.ICommand;
import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.Users;
import ua.kiev.doctorvera.mysql.MySqlDaoFactory;
import ua.kiev.doctorvera.mysql.UsersMySql;

public class ShowUsers implements ICommand {

		@Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	String page ="/jsp/empty.jsp";
	    	String content ="";
	    	UsersMySql usersDao = (UsersMySql) MySqlDaoFactory.getInstance().getDao(Users.class);
	    	List<Users> allUsers = null;
	    	try {
				allUsers = usersDao.findAll();
			} catch (PersistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	content += "<table>";
	    	content += "<th><td>Id</td>"
	    			+ "<td>Имя</td>"
	    			+ "<td>Фамилия</td>"
	    			+ "<td>Моб. тел.</td>"
	    			+ "<td>Адрес</td>"
	    			+ "<td>Дата создания</td>"
	    			+ "<td>Создал</td>"
	    			+ "<td>Группа</td></th>";
	        for(Users user : allUsers){
	        	content += "<tr>";
	        	content += "<td>" + user.getId() + "</td>"+
	        			"<td>" + user.getFirstName() + "</td>"+
	        			"<td>" + user.getLastName() + "</td>"+
	        			"<td>" + user.getPhoneNumberMobile() + "</td>"+
	        			"<td>" + user.getAddress() + "</td>"+
	        			"<td>" + user.getDateCreated() + "</td>"+
	        			"<td>" + user.getUserCreated() + "</td>"+
	        			"<td>" + user.getUserType() + "</td>";
	        	content += "</tr>";
	        }
	        content += "</table>";
	        request.setAttribute("content", content);
	        return page;
	    }
}


