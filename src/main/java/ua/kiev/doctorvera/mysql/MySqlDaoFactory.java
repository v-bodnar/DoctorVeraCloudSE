package ua.kiev.doctorvera.mysql;

import ua.kiev.doctorvera.dao.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MySqlDaoFactory implements DaoFactory<Connection> {

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public GenericDao getDao(Class dtoClass) {
    	GenericDao daoObject = null;
		try {
			Constructor<?>ctor = dtoClass.getConstructor(String.class);
			daoObject = (GenericDao) ctor.newInstance();
			if(daoObject == null) throw new InstantiationException(" daoObject is NULL!");
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println(e.getLocalizedMessage());
		}

        return daoObject;
    }

	@Override
	public Connection getConnection() {
		try {
			InitialContext ic = new InitialContext();
			Context initialContext = (Context) ic.lookup("java:comp/env");
			DataSource datasource = (DataSource) initialContext.lookup("jdbc/MySQLDS");
			return datasource.getConnection();
		} catch (NamingException | SQLException e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

}
