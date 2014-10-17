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

    private Map<Class, DaoCreator> creators = new HashMap<Class, DaoCreator>();
    
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
	
    /*
    public MySqlDaoFactory() {
        creators.put(Address.class, new AddressMySql(getConnection()));
        creators.put(Method.class, new MethodMySql(getConnection()));
        creators.put(Payments.class, new PaymentsMySql(getConnection()));
        creators.put(Plan.class, new PlanMySql(getConnection()));
        creators.put(Policy.class, new PolicyMySql(getConnection()));
        creators.put(Price.class, new PriceMySql(getConnection()));
        creators.put(Rooms.class, new RoomsMySql(getConnection()));
        creators.put(Schedule.class, new ScheduleMySql(getConnection()));
        creators.put(Share.class, new ShareMySql(getConnection()));
        creators.put(Users.class, new UsersMySql(getConnection()));
        creators.put(UserTypes.class, new UserTypesMySql(getConnection()));
    }
   
    
    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) {
        return creators.get(dtoClass);
    }
     */    
	
	
    @Override
     public GenericDao getDao(Connection connection, Class dtoClass){
         DaoCreator creator = creators.get(dtoClass);
         return creator.create(connection);
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
