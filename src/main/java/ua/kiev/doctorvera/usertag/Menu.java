package ua.kiev.doctorvera.usertag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import ua.kiev.doctorvera.manager.Config;
import ua.kiev.doctorvera.manager.Mapping;
import ua.kiev.doctorvera.manager.Message;
@SuppressWarnings("rawtypes")
public class Menu extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private final Message messages = Message.getInstance();
	private final Mapping mapper = Mapping.getInstance();
	private final String projectName = Config.getInstance().getProperty(Config.Key.PROJECT_NAME);
	private final HashMap<Enum, Enum> adminItems = new HashMap<Enum, Enum>();
	private final HashMap<Enum, Enum> managerItems = new HashMap<Enum, Enum>();
	private final HashMap<Enum, Enum> financeItems = new HashMap<Enum, Enum>();
	private final HashMap<Enum, Enum> statItems = new HashMap<Enum, Enum>();
	{
	adminItems.put(Mapping.Key.USERS_PAGE, Message.Menu.MENU_ITEM_USERS);
	adminItems.put(Mapping.Key.ADD_USER_PAGE, Message.Menu.MENU_ITEM_ADD_USER);
	adminItems.put(Mapping.Key.USER_GROUPS_PAGE, Message.Menu.MENU_ITEM_USER_GROUPS);
	}
	
	public int doStartTag() throws JspException {
		try { 
			pageContext.getOut().write("<h3>" + messages.getMessage(Message.Menu.MENU_HEADER) + "</h3>");
			pageContext.getOut().write(messages.getMessage(Message.Menu.ADMIN_BLOCK_HEADER));
			pageContext.getOut().write("<ul>");
			for(Entry<Enum, Enum> menuItem : adminItems.entrySet()) {
				pageContext.getOut().write("<li>");
				pageContext.getOut().write("<a href = /" + projectName + mapper.getProperty(menuItem.getKey()) + ">" + messages.getMessage(menuItem.getValue()) + "</a>");
				pageContext.getOut().write("</li>");
			}
			pageContext.getOut().write("</ul>");
			
			pageContext.getOut().write(messages.getMessage(Message.Menu.MANAGER_BLOCK_HEADER));
			pageContext.getOut().write("<ul>");
			for(Entry<Enum, Enum> menuItem : managerItems.entrySet()) {
				pageContext.getOut().write("<li>");
				pageContext.getOut().write("<a href = /" + projectName + mapper.getProperty(menuItem.getKey()) + ">" + messages.getMessage(menuItem.getValue()) + "</a>");
				pageContext.getOut().write("</li>");
			}
			pageContext.getOut().write("</ul>");
			
			pageContext.getOut().write(messages.getMessage(Message.Menu.FINANCIAL_BLOCK_HEADER));
			pageContext.getOut().write("<ul>");
			for(Entry<Enum, Enum> menuItem : financeItems.entrySet()) {
				pageContext.getOut().write("<li>");
				pageContext.getOut().write("<a href = /" + projectName + mapper.getProperty(menuItem.getKey()) + ">" + messages.getMessage(menuItem.getValue()) + "</a>");
				pageContext.getOut().write("</li>");
			}
			pageContext.getOut().write("</ul>");
			
			pageContext.getOut().write(messages.getMessage(Message.Menu.STATISTICS_BLOCK_HEADER));
			pageContext.getOut().write("<ul>");
			for(Entry<Enum, Enum> menuItem : statItems.entrySet()) {
				pageContext.getOut().write("<li>");
				pageContext.getOut().write("<a href = /" + projectName + mapper.getProperty(menuItem.getKey()) + ">" + messages.getMessage(menuItem.getValue()) + "</a>");
				pageContext.getOut().write("</li>");
			}
			pageContext.getOut().write("</ul>");
			
			
			
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		return SKIP_BODY;
	}
	
}