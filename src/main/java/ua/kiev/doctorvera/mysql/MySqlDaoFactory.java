package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ua.kiev.doctorvera.dao.DaoFactory;
import ua.kiev.doctorvera.dao.GenericDao;
import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.UserTypes;
import ua.kiev.doctorvera.entity.Users;


public class MySqlDaoFactory implements DaoFactory<Connection> {
/*
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public GenericDao getDao(Class dtoClass) {
    	GenericDao daoObject = null;
		try {
			Constructor<?>ctor = dtoClass.getConstructor(String.class);
			daoObject = (GenericDao) ctor.newInstance(getConnection());
			if(daoObject == null) throw new InstantiationException(" daoObject is NULL!");
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println(e.getLocalizedMessage());
		}

        return daoObject;
    }
*/
    private Map<Class, DaoCreator> creators;
    

    
    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
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
	
    public MySqlDaoFactory() {


        creators = new HashMap<Class, DaoCreator>();
        creators.put(Users.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new UsersMySql(connection);
            }
        });
        creators.put(UserTypes.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new UserTypesMySql(connection);
            }
        });
    }

}
