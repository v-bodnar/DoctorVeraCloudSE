package ua.kiev.doctorvera.commands.users;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kiev.doctorvera.commands.ICommand;
import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.Address;
import ua.kiev.doctorvera.entity.Users;
import ua.kiev.doctorvera.logic.Validator;
import ua.kiev.doctorvera.manager.Mapping;
import ua.kiev.doctorvera.mysql.AddressMySql;
import ua.kiev.doctorvera.mysql.MySqlDaoFactory;
import ua.kiev.doctorvera.mysql.UsersMySql;

public class PersistUser implements ICommand {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        UsersMySql usersDao = (UsersMySql) MySqlDaoFactory.getInstance().getDao(Users.class);
        AddressMySql addressDao = (AddressMySql) MySqlDaoFactory.getInstance().getDao(Address.class);
        Users incomingUser = new Users();
        Address incomingAddress = new Address();
              
        LinkedHashMap <String, String> errors = new LinkedHashMap<String, String>();
        errors.put("login", Validator.checkUsername(request.getParameter("login")));
        errors.put("password", Validator.checkPassword(request.getParameter("password")));
        errors.put("firstName", Validator.checkName(request.getParameter("firstName")));
        if(request.getParameter("middleName")!=null && !request.getParameter("middleName").equals(""))
        	errors.put("middleName", Validator.checkName(request.getParameter("middleName")));
        errors.put("lastName", Validator.checkName(request.getParameter("lastName")));
        if(request.getParameter("birthDate")!=null && !request.getParameter("birthDate").equals(""))
        errors.put("birthDate", Validator.checkCyrText(request.getParameter("birthDate")));
        if(request.getParameter("phoneNumberHome")!=null && !request.getParameter("phoneNumberHome").equals(""))
        	errors.put("phoneNumberHome", Validator.checkPhone(request.getParameter("phoneNumberHome")));
        	errors.put("phoneNumberMobile", Validator.checkPhone(request.getParameter("phoneNumberMobile")));
        if(request.getParameter("country")!=null && !request.getParameter("country").equals(""))
        	errors.put("country", Validator.checkCyrText(request.getParameter("country")));
        if(request.getParameter("city")!=null && !request.getParameter("city").equals(""))
        	errors.put("city", Validator.checkCyrText(request.getParameter("city")));
        if(request.getParameter("region")!=null && !request.getParameter("region").equals(""))
        	errors.put("region", Validator.checkCyrText(request.getParameter("region")));
        if(request.getParameter("address")!=null && !request.getParameter("address").equals(""))
        	errors.put("address", Validator.checkCyrText(request.getParameter("address")));
        if(request.getParameter("index")!=null && !request.getParameter("index").equals(""))
        	errors.put("index", Validator.checkNumeric(request.getParameter("index")));
        /*
        address
        birthDate
        city
        country
        description
        firstName
        index
        lastName
        login
        middleName
        password
        phoneNumberHome
        phoneNumberMobile
        region
        userTypes
        */
        
        DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date birthDate = null;
		try {
			birthDate = formatter.parse(request.getParameter("birthDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
        if (errors.containsKey("login") && !errors.get("login").equals("")) incomingUser.setUsername(request.getParameter("login"));
        if (errors.containsKey("password") && !errors.get("password").equals("")) incomingUser.setPassword(request.getParameter("password"));
        if (errors.containsKey("firstName") && !errors.get("firstName").equals("")) incomingUser.setFirstName(request.getParameter("firstName"));
        if (errors.containsKey("middleName") && !errors.get("middleName").equals("")) incomingUser.setMiddleName(request.getParameter("middleName"));
        if (errors.containsKey("lastName") && !errors.get("lastName").equals("")) incomingUser.setLastName(request.getParameter("lastName"));
        if (errors.containsKey("birthDate") && !errors.get("birthDate").equals("")) incomingUser.setBirthDate(birthDate);
        if (errors.containsKey("phoneNumberHome") && !errors.get("phoneNumberHome").equals("")) incomingUser.setPhoneNumberHome(request.getParameter("phoneNumberHome"));
        if (errors.containsKey("phoneNumberMobile") && !errors.get("phoneNumberMobile").equals("")) incomingUser.setPhoneNumberMobile(request.getParameter("phoneNumberMobile"));
        if (errors.containsKey("country") && !errors.get("country").equals("")) incomingAddress.setCountry(request.getParameter("country"));
        if (errors.containsKey("region") && !errors.get("region").equals("")) incomingAddress.setRegion(request.getParameter("region"));
        if (errors.containsKey("city") && !errors.get("city").equals("")) incomingAddress.setCity(request.getParameter("city"));
        if (errors.containsKey("address") && !errors.get("address").equals("")) incomingAddress.setAddress(request.getParameter("address"));
        if (!request.getParameter("index").equals("") && request.getParameter("index")!=null)
        if (errors.containsKey("index") && !errors.get("index").equals("")) incomingAddress.setIndex(Integer.parseInt(request.getParameter("index")));

        incomingUser.setDateCreated(new Date());
        incomingAddress.setDateCreated(new Date());
        try {
			incomingUser.setUserCreated(usersDao.findByPK((Integer)request.getSession(false).getAttribute("authorizedUserId")));
			incomingAddress.setUserCreated(usersDao.findByPK((Integer)request.getSession(false).getAttribute("authorizedUserId")));
        } catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println(errors);
        System.out.println(incomingUser);
        System.out.println(incomingAddress);
        
        if(errors.size() == 0) {

            page = Mapping.getInstance().getProperty(Mapping.Page.SHOW_USERS_PAGE);
        } else {
            request.setAttribute("error", errors);
            page = Mapping.getInstance().getProperty(Mapping.Page.EDIT_USER_PAGE);
        }
        
        return page;
    }
}
