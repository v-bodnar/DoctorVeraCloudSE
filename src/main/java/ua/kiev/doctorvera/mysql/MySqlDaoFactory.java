package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ua.kiev.doctorvera.dao.DaoFactory;
import ua.kiev.doctorvera.dao.GenericDao;


public class MySqlDaoFactory implements DaoFactory<Connection> {

    private Map<Class, GenericDao> creators;
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

    	creators = new HashMap<Class, GenericDao>();
        //creators.put(Address.class, new AddressMySql(connection));
        //creators.put(Method.class, new MethodMySql(connection));
        //creators.put(Payments.class, new PaymentsMySql(connection));
        //creators.put(Plan.class, new PlanMySql(connection));
        //creators.put(Policy.class, new PolicyMySql(connection));
        //creators.put(Price.class, new PriceMySql(connection));
        //creators.put(Rooms.class, new RoomsMySql(connection));
        //creators.put(Schedule.class, new ScheduleMySql(connection));
        //creators.put(Share.class, new ShareMySql(connection));
        creators.put(Users.class, new UsersMySql(connection));
        creators.put(UserTypes.class, new UserTypesMySql(connection));
    }
   
    */
    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) {
    	
    	
    	switch(dtoClass.getSimpleName()){
    	case ("Address"): return new AddressMySql(connection);
    	case ("DoctorsHasMethod"): return new DoctorsHasMethodMySql(connection);
    	case ("Methods"): return new MethodsMySql(connection);
    	case ("MethodTypes"): return new MethodTypesMySql(connection);
    	case ("Payments"): return new PaymentsMySql(connection);
    	case ("Plan"): return new PlanMySql(connection);
    	case ("Policy"): return new PolicyMySql(connection);
    	case ("PolicyHasUserTypes"): return new PolicyHasUserTypesMySql(connection);
    	case ("Price"): return new PricesMySql(connection);
    	case ("Rooms"): return new RoomsMySql(connection);
    	case ("Schedule"): return new ScheduleMySql(connection);
    	case ("Share"): return new ShareMySql(connection);
    	case ("Users"): return new UsersMySql(connection);
    	case ("UserTypes"): return new UserTypesMySql(connection);
    	
    	
    	}
    	
    	
        return null;
    }
	
	/*
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
    */

}
