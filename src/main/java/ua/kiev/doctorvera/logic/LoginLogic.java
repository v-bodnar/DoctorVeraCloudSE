/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.logic;

import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.Users;
import ua.kiev.doctorvera.mysql.MySqlDaoFactory;
import ua.kiev.doctorvera.mysql.UsersMySql;

/**
 *
 * @author Vova
 */
public class LoginLogic {
    public Users checkLogin(Users currentUser){
		UsersMySql usersDao = (UsersMySql) MySqlDaoFactory.getInstance().getDao(Users.class);
        try {
        	Users user = usersDao.findByNeedle("Username", currentUser.getUsername());
        	if (currentUser.getPassword().equals(user.getPassword())) return user;
        	else return null;
		} catch (PersistException e) {
			return null;
		}
    }
}
