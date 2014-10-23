package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.DoctorsHasMethod;
import ua.kiev.doctorvera.entity.MethodTypes;
import ua.kiev.doctorvera.entity.Methods;
import ua.kiev.doctorvera.entity.Prices;
import ua.kiev.doctorvera.entity.Users;

public class MethodsMySql extends AbstractMySql<Methods, Integer> {
	private Connection connection;
	private final String TABLE_NAME = "Methods";
	private UsersMySql usersDao = (UsersMySql)new MySqlDaoFactory().getDao(connection, Users.class);
	private PricesMySql priceDao = (PricesMySql)new MySqlDaoFactory().getDao(connection, Prices.class);
	private DoctorsHasMethodMySql doctorsHasMethodDao = (DoctorsHasMethodMySql)new MySqlDaoFactory().getDao(connection, DoctorsHasMethod.class);
	private MethodTypesMySql methodTypesDao = (MethodTypesMySql)new MySqlDaoFactory().getDao(connection, MethodTypes.class);
	
	public MethodsMySql(Connection connection) {
		super(connection);
		this.connection = connection;
	}
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public Methods add() throws PersistException {
		Methods methodss = new Methods();
		return persist(methodss);
	}

	
	@Override
	protected List<Methods> parseResultSet(ResultSet rs) throws PersistException{
    LinkedList<Methods> result = new LinkedList<Methods>();
    try {
        while (rs.next()) {
        	Methods methods = new Methods();
        	methods.setMethodId(rs.getInt("MethodId"));
        	methods.setMethodType(methodTypesDao.findByPK(rs.getInt("MethodType")));
        	methods.setShortName(rs.getString("ShortName"));
        	methods.setFullName(rs.getString("FullName"));
        	methods.setShortDescription(rs.getString("ShortDescription"));
        	methods.setFullDescription(rs.getString("FullDescription"));
        	methods.setTimeInMinutes(rs.getInt("TimeInMinutes"));
        	methods.setDoctorsCollection(doctorsHasMethodDao.findByMethod(methods));
        	methods.setPricesCollection(priceDao.findByMethod(methods));
        	methods.setUserCreated(usersDao.findByPK(rs.getInt("UserCreated")));
        	methods.setDateCreated(rs.getDate("DateCreated"));
        	methods.setDeleted(rs.getBoolean("Deleted"));
            result.add(methods);
        }
    } catch (Exception e) {
        throw new PersistException(e);
    }
    return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Methods methods) throws PersistException {
        try {
        	statement.setInt(1, methods.getMethodType().getId());
            statement.setString(2, methods.getShortName());
            statement.setString(3, methods.getFullName());
            statement.setString(4, methods.getShortDescription());
            statement.setString(5, methods.getFullDescription());
            statement.setInt(6, methods.getTimeInMinutes());
            statement.setInt(7, methods.getUserCreated().getId());
            statement.setDate(8, new java.sql.Date( methods.getDateCreated().getTime()));
            statement.setBoolean(9, methods.getDeleted());
        } catch (Exception e) {
            throw new PersistException(e);
        }
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Methods methods) throws PersistException {
        try {
        	statement.setInt(1, methods.getMethodType().getId());
            statement.setString(2, methods.getShortName());
            statement.setString(3, methods.getFullName());
            statement.setString(4, methods.getShortDescription());
            statement.setString(5, methods.getFullDescription());
            statement.setInt(6, methods.getTimeInMinutes());
            statement.setInt(7, methods.getUserCreated().getId());
            statement.setDate(8, new java.sql.Date( methods.getDateCreated().getTime()));
            statement.setBoolean(9, methods.getDeleted());
            statement.setInt(10, methods.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}
	
	public Collection<Methods> findByMethodTypes(MethodTypes methodType ) throws PersistException{
		ArrayList<Methods> methodList = new ArrayList<Methods>(); 
		methodList.add( findByNeedle("MethodType", methodType.getId().toString()));
		return methodList;
	}
	
	public Collection<Methods> findByShortName(String shortName ) throws PersistException{
		ArrayList<Methods> methodList = new ArrayList<Methods>(); 
		methodList.add( findByNeedle("ShortName", shortName));
		return methodList;
	}
	
	public Collection<Methods> findByFullName(String fullName ) throws PersistException{
		ArrayList<Methods> methodList = new ArrayList<Methods>(); 
		methodList.add( findByNeedle("FullName", fullName));
		return methodList;
	}
	public Collection<Methods> findByShortDescription(String shortDescription ) throws PersistException{
		ArrayList<Methods> methodList = new ArrayList<Methods>(); 
		methodList.add( findByNeedle("ShortDescription", "%" + shortDescription + "%"));
		return methodList;
	}
	public Collection<Methods> findByFullDescription(String fullDescription ) throws PersistException{
		ArrayList<Methods> methodList = new ArrayList<Methods>(); 
		methodList.add( findByNeedle("FullDescription", "%" + fullDescription + "%"));
		return methodList;
	}
	public Collection<Methods> findByTime(Integer minutes ) throws PersistException{
		ArrayList<Methods> methodList = new ArrayList<Methods>(); 
		methodList.add( findByNeedle("TimeInMinutes", minutes.toString()));
		return methodList;
	}
	public Collection<Methods> findByTimeBetween(Integer min, Integer max ) throws PersistException{
		List<Methods> methodList = findAll();
		ArrayList<Methods> result = new ArrayList<Methods>();
		for(Methods method : methodList){
			if(min <= method.getTimeInMinutes() && method.getTimeInMinutes() <= max)result.add(method);
		}
		return result;
	}
	


}
