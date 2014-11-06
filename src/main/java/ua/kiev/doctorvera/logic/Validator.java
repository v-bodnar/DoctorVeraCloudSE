package ua.kiev.doctorvera.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.kiev.doctorvera.entity.Users;
import ua.kiev.doctorvera.manager.Message;
import ua.kiev.doctorvera.mysql.MySqlDaoFactory;
import ua.kiev.doctorvera.mysql.UsersMySql;

public class Validator {
	private static final String startLine = "<li>";
	private static final String endLine = "</li>";

	public static Boolean isCyrillic(String string) {
		final Pattern WORD = Pattern.compile("[А-Яа-яїъьёыі]",Pattern.CASE_INSENSITIVE);
		Matcher wordM = WORD.matcher(string);
		return wordM.find();
	}
	
	public static Boolean containsLatin(String string) {
		final Pattern WORD = Pattern.compile("[A-Za-z]",Pattern.CASE_INSENSITIVE);
		Matcher wordM = WORD.matcher(string);
		return wordM.find();
	}
	
	public static Boolean containsNumber(String string) {
		final Pattern NUMBERS = Pattern.compile("\\d");
		Matcher wordM = NUMBERS.matcher(string);
		return wordM.find();
	}
	public static Boolean isNull(String string) {
		return (string == null || string.trim().length() == 0);
	}
	
	public static Boolean containsPunct(String string) {
		final Pattern PUNCT = Pattern.compile("[\\.?,:;\"\\|/<>-_='~`!@#$%^&*(){}]",Pattern.CASE_INSENSITIVE);
		Matcher wordM = PUNCT.matcher(string);
		return wordM.find();
	}
	
	public static String checkName(String name) {
		String note = "";
		if (isNull(name))
			note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_REQUIRED) + endLine;
		else{
			if (!isCyrillic(name))
				note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_CYRILLIC) + endLine;
			if (containsNumber(name))
				note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_NOT_NUMBERS) + endLine;
			if (containsPunct(name))
				note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_NOT_PUNCT) + endLine;
		}
		return note;
	}
	
	public static String checkUsername(String login) {
		String note = "";
		UsersMySql usersDao = (UsersMySql) MySqlDaoFactory.getInstance().getDao(Users.class);
		Users user = usersDao.findByUsername(login);

		if (isNull(login))
			note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_REQUIRED) + endLine;
		else{
			if (containsNumber(login))
				note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_NOT_NUMBERS) + endLine;
			else if (user != null)
				note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_LOGIN_IN_USE) + endLine;
		}
		return note;
	}
	
	public static String checkPassword(String password) {
		String note = "";
		if (isNull(password))
			note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_REQUIRED) + endLine;
		else{
			if (password.length() < 6)
				note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_PASSWORD_LESS_SIX) + endLine;
			if (containsNumber(password))
				note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_NUMBERS) + endLine;
			if (containsLatin(password))
				note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_PASSWORD_LATIN) + endLine;
		}
		return note;
	}
	
	public static String checkEmail(String email) {
		String note = "";
		final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
				"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher emailM = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

		if (isNull(email))
			note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_REQUIRED) + endLine;
		else if (!emailM.find())
			note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_EMAIL) + endLine;
		return note;
	}
	public static String preparePhoneNumber(String phone){
		System.out.println("phone:" + phone);
		phone = phone.trim();
		phone = phone.replaceAll("[\\.?,:;\"\\|/<>-_='~`!@#$%^&*(){}]", "");
		
		System.out.println("phone:" + phone + "len:" + phone.length());
		if (phone.contains("+") && phone.length()==13) return phone;
		else if (phone.length()==10) return "+38" + phone;
		
		return null;		
	}

	public static String checkPhone(String phone) {
		String note = "";
		System.out.println("phone:" + phone);
		phone = preparePhoneNumber(phone);

		final Pattern VALID_PHONE_REGEX = Pattern.compile("\\+[0-9]{1,13}", Pattern.CASE_INSENSITIVE);
		Matcher emailM = VALID_PHONE_REGEX.matcher(phone);

		if (isNull(phone))
			note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_REQUIRED) + endLine;
		else if (!emailM.find())
			note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_EMAIL) + endLine;
		return note;
	}
	
	public static String checkCyrText(String text) {
		String note = "";
		if (isNull(text))
			note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_REQUIRED) + endLine;
		else if (!isCyrillic(text))
				note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_CYRILLIC) + endLine;
		
		return note;
	}
	
	public static String checkNumeric(String text) {
		String note = "";
		if (isNull(text))
			note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_REQUIRED) + endLine;
		else 
			if(Pattern.matches("\\d", text)) 
				note += startLine + Message.getInstance().getMessage(Message.Validator.VALIDATOR_NUMBERS) + endLine;
		
		return note;
	}
}
