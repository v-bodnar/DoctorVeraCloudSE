/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public class Message {

	private static Message instance;
	private ResourceBundle resource;
	private static final String BUNDLE_NAME = "ua.kiev.doctorvera.manager.message";

	public static enum Messages {
		LOGIN_ERROR, 
		IO_EXCEPTION, 
		SERVLET_EXCEPTION, 
		COMMAND_MISSING
	}
	
	public static enum Menu{
		MENU_HEADER,
		ADMIN_BLOCK_HEADER,
		MANAGER_BLOCK_HEADER,
		FINANCIAL_BLOCK_HEADER,
		STATISTICS_BLOCK_HEADER,
		MENU_ITEM_USERS,
		MENU_ITEM_ADD_USER,
		MENU_ITEM_USER_GROUPS,
		MENU_ITEM_ACCESS_RIGHTS,
		MENU_ITEM_SETTINGS,
		MENU_ITEM_PLAN,
		MENU_ITEM_ADD_PLAN,
		MENU_ITEM_SCHEDULE,
		MENU_ITEM_ADD_SCHEDULE,
		MENU_ITEM_METHODS,
		MENU_ITEM_METHOD_TYPES,
		MENU_ITEM_ROOMS,
		MENU_ITEM_CASH,
		MENU_ITEM_PAYMENTS,
		MENU_ITEM_CREATE_PAYMENT,
		MENU_ITEM_SALARY,
		MENU_ITEM_APPOINTMENTS,
		MENU_ITEM_FINANCE,
		MENU_ITEM_TIME
	}

	public static Message getInstance() {
		if (instance == null) {
			instance = new Message();
			instance.resource = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault(), new ExtendedControl());
		}
		return instance;
	}

	@SuppressWarnings("rawtypes")
	public String getMessage(Enum key) {
		return (String) resource.getObject(key.toString());
	}
}