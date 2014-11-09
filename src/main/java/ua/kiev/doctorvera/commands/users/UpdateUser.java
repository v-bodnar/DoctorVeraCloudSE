package ua.kiev.doctorvera.commands.users;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kiev.doctorvera.commands.ICommand;
import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.Address;
import ua.kiev.doctorvera.entity.UserTypes;
import ua.kiev.doctorvera.entity.Users;
import ua.kiev.doctorvera.logic.Validator;
import ua.kiev.doctorvera.manager.Mapping;
import ua.kiev.doctorvera.mysql.AddressMySql;
import ua.kiev.doctorvera.mysql.MySqlDaoFactory;
import ua.kiev.doctorvera.mysql.UserTypesMySql;
import ua.kiev.doctorvera.mysql.UsersMySql;

public class UpdateUser implements ICommand {
     
	private final UsersMySql usersDao = (UsersMySql) MySqlDaoFactory.getInstance().getDao(Users.class);
	private final AddressMySql addressDao = (AddressMySql) MySqlDaoFactory.getInstance().getDao(Address.class);
	private final UserTypesMySql userTypesDao = (UserTypesMySql) MySqlDaoFactory.getInstance().getDao(UserTypes.class);
    private Users currentUser;
	private Users incomingUser = new Users();
	private Address incomingAddress = new Address();
	private final LinkedHashMap <String, String> errors = new LinkedHashMap<String, String>();
	private HttpServletRequest request;
	 
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //Get current User from session
		try {
			currentUser = usersDao.findByPK((Integer)request.getSession(false).getAttribute("authorizedUserId"));
		} catch (PersistException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		//Validating & creating entities
		validateUserFormFields();
		validateAddressFormFields();
		cleanErrors();
		//Checking if there are validating errors
        if(errors.size() == 0) {
        	try {
				addressDao.update(incomingAddress);
				usersDao.update(incomingUser);
		    	List<Users> allUsers = null;
				allUsers = usersDao.findAll();
		    	request.setAttribute("allUsers", allUsers);
			} catch (PersistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("error", e.getLocalizedMessage());
				return Mapping.getInstance().getProperty(Mapping.Page.LOGIN_ERROR);
			}
        	return Mapping.getInstance().getProperty(Mapping.Page.SHOW_USERS_PAGE);
        } else {
            request.setAttribute("errors", errors);
            request.setAttribute("incomingUser", incomingUser);
            request.setAttribute("incomingAddress", incomingAddress);
            return Mapping.getInstance().getProperty(Mapping.Page.EDIT_USER_PAGE);
        }
    }
	private void cleanErrors(){
		   Iterator<Entry<String,String>> it = errors.entrySet().iterator();
		   while (it.hasNext()){
		      Entry<String,String> entry = (Entry<String,String>)it.next();
		      if(entry.getValue().equals("")) it.remove();
		   }
	}
	private void validateUserFormFields(){
		DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date birthDate = null;
       
        //Validating form fields and setting params to Users entity
        //login
    	if(request.getParameter("login")!=null && !request.getParameter("login").equals("")){
    		errors.put("login", Validator.checkUsername(request.getParameter("login")));
    		if (errors.containsKey("login") && errors.get("login").equals("")) 
    			incomingUser.setUsername(request.getParameter("login"));
    	}
    	//password
    	if(request.getParameter("password")!=null && !request.getParameter("password").equals("")){
    		errors.put("password", Validator.checkPassword(request.getParameter("password")));
    		if (errors.containsKey("password") && errors.get("password").equals("")) 
    			incomingUser.setPassword(request.getParameter("password"));
    	}
    	//firstName
    	if(request.getParameter("firstName")!=null && !request.getParameter("firstName").equals("")){
    		errors.put("firstName", Validator.checkName(request.getParameter("firstName")));
    		if (errors.containsKey("firstName") && errors.get("firstName").equals("")) 
    			incomingUser.setFirstName(request.getParameter("firstName"));
    		}
    	//middleName
    	if(request.getParameter("middleName")!=null && !request.getParameter("middleName").equals("")){
        	errors.put("middleName", Validator.checkName(request.getParameter("middleName")));
        	if (errors.containsKey("middleName") && errors.get("middleName").equals("")) 
        		incomingUser.setMiddleName(request.getParameter("middleName"));
        	}
    	//lastName
    	if(request.getParameter("lastName")!=null && !request.getParameter("lastName").equals("")){
    		errors.put("lastName", Validator.checkName(request.getParameter("lastName")));
    		if (errors.containsKey("lastName") && errors.get("lastName").equals("")) 
    			incomingUser.setLastName(request.getParameter("lastName"));
    	}
    	//phoneNumberMobile
    	if(request.getParameter("phoneNumberMobile")!=null && !request.getParameter("phoneNumberMobile").equals("")){
    		errors.put("phoneNumberMobile", Validator.checkPhone(request.getParameter("phoneNumberMobile")));
    		if (errors.containsKey("phoneNumberMobile") && errors.get("phoneNumberMobile").equals("")) 
    			incomingUser.setPhoneNumberMobile(request.getParameter("phoneNumberMobile"));
    	}
    	//phoneNumberHome
        if(request.getParameter("phoneNumberHome")!=null && !request.getParameter("phoneNumberHome").equals("")){
        	errors.put("phoneNumberHome", Validator.checkPhone(request.getParameter("phoneNumberHome")));
        	if (errors.containsKey("phoneNumberHome") && errors.get("phoneNumberHome").equals("")) 
        		incomingUser.setPhoneNumberHome(request.getParameter("phoneNumberHome"));
        }
    	//userType
        if(request.getParameter("userTypes")!=null && !request.getParameter("userTypes").equals("")){
    		try {
    			userTypesDao.findByPK(Integer.parseInt(request.getParameter("userTypes")));
    		} catch (PersistException e1) {
    			errors.put("userTypes", e1.getLocalizedMessage());
    			e1.printStackTrace();
    		}
        	if (!errors.containsKey("userTypes")) 
        		incomingUser.setUserTypeId(Integer.parseInt(request.getParameter("userTypes")));
        	if (errors.containsKey("userTypes") && errors.get("userTypes").equals("")) 
        		incomingUser.setUserTypeId(Integer.parseInt(request.getParameter("userTypes")));
        }
    	//birthDate
        if(request.getParameter("birthDate")!=null && !request.getParameter("birthDate").equals("")){
    		try {
    			birthDate = formatter.parse(request.getParameter("birthDate"));
    		} catch (ParseException e) {
    			errors.put("birthDate", e.getLocalizedMessage());
    			e.printStackTrace();
    		}
    		if (!errors.containsKey("birthDate"))
				incomingUser.setBirthDate(birthDate);
    		if (errors.containsKey("birthDate") && errors.get("birthDate").equals("")) {
    				incomingUser.setBirthDate(birthDate);
    		}

        }
        incomingUser.setDateCreated(new Date());
        incomingUser.setUserCreated(currentUser);
        incomingUser.setDeleted(false);
	}
	private void validateAddressFormFields(){
        //Validating form fields and setting params to Address entity
        //country
        if(request.getParameter("country")!=null && !request.getParameter("country").equals("")){
        	errors.put("country", Validator.checkCyrText(request.getParameter("country")));
        	if (errors.containsKey("country") && errors.get("country").equals("")) 
        		incomingAddress.setCountry(request.getParameter("country"));
        }
        //region
        if(request.getParameter("region")!=null && !request.getParameter("region").equals("")){
        	errors.put("region", Validator.checkCyrText(request.getParameter("region")));
        	if (errors.containsKey("region") && errors.get("region").equals("")) 
        		incomingAddress.setRegion(request.getParameter("region"));
        }
        //city
        if(request.getParameter("city")!=null && !request.getParameter("city").equals("")){
        	errors.put("city", Validator.checkCyrText(request.getParameter("city")));
        	if (errors.containsKey("city") && errors.get("city").equals("")) 
        		incomingAddress.setCity(request.getParameter("city"));
        }
        //address
        if(request.getParameter("address")!=null && !request.getParameter("address").equals("")){
        	errors.put("address", Validator.checkNull(request.getParameter("address")));
        	if (errors.containsKey("address") && errors.get("address").equals("")) 
        		incomingAddress.setAddress(request.getParameter("address"));
        }
        //index
        if(request.getParameter("index")!=null && !request.getParameter("index").equals("")){
        	errors.put("index", Validator.checkNumeric(request.getParameter("index")));
        	if (errors.containsKey("index") && errors.get("index").equals("")) 
        		incomingAddress.setPostIndex(Integer.parseInt(request.getParameter("index")));
        }      
        incomingAddress.setDateCreated(new Date());
		incomingAddress.setUserCreated(currentUser);
		incomingAddress.setDeleted(false);
	}
	
}