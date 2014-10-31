package ua.kiev.doctorvera.logic;

import java.util.HashMap;

import javax.servlet.jsp.tagext.TagSupport;

import ua.kiev.doctorvera.manager.Mapping;
import ua.kiev.doctorvera.manager.Message;
public class Menu extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private final Message messages = Message.getInstance();
	private final Mapping mapper = Mapping.getInstance();
	private final HashMap<String, String> adminItems = new HashMap<String, String>();
	private final HashMap<String, String> managerItems = new HashMap<String, String>();
	private final HashMap<String, String> financeItems = new HashMap<String, String>();
	private final HashMap<String, String> statItems = new HashMap<String, String>();
	{
	adminItems.put(mapper.getProperty(Mapping.Key.USERS_PAGE), messages.getMessage(Message.Menu.MENU_ITEM_USERS));
	adminItems.put(mapper.getProperty(Mapping.Key.ADD_USER_PAGE), messages.getMessage(Message.Menu.MENU_ITEM_ADD_USER));
	adminItems.put(mapper.getProperty(Mapping.Key.USER_GROUPS_PAGE), messages.getMessage(Message.Menu.MENU_ITEM_USER_GROUPS));
	}
	public HashMap<String, String> getAdminItems() {
		return adminItems;
	}
	public HashMap<String, String> getManagerItems() {
		return managerItems;
	}
	public HashMap<String, String> getFinanceItems() {
		return financeItems;
	}
	public HashMap<String, String> getStatItems() {
		return statItems;
	}
	
	
}