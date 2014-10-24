package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.MethodTypes;
import ua.kiev.doctorvera.entity.Methods;
import ua.kiev.doctorvera.entity.Users;

public class MethodTypesMySql extends AbstractMySql<MethodTypes, Integer> {
	//private Connection connection;
	private final String TABLE_NAME = "MethodTypes";

	
	public MethodTypesMySql(Connection connection) {
		super(connection);
		//this.connection = connection;
	}
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public MethodTypes add() throws PersistException {
		MethodTypes methodTypess = new MethodTypes();
		return persist(methodTypess);
	}

	
	@Override
	protected List<MethodTypes> parseResultSet(ResultSet rs) throws PersistException{
    LinkedList<MethodTypes> result = new LinkedList<MethodTypes>();
	UsersMySql usersDao = (UsersMySql)MySqlDaoFactory.getInstance().getDao(Users.class);
	MethodsMySql methodDao = (MethodsMySql)MySqlDaoFactory.getInstance().getDao(Methods.class);
	
    try {
        while (rs.next()) {
        	MethodTypes methodTypes = new MethodTypes();
        	methodTypes.setMethodTypeId(rs.getInt("MethodTypeId"));
        	methodTypes.setShortName(rs.getString("ShortName"));
        	methodTypes.setFullName(rs.getString("FullName"));
        	methodTypes.setUserCreated(usersDao.findByPK(rs.getInt("UserCreated")));
        	methodTypes.setDateCreated(rs.getDate("DateCreated"));
        	methodTypes.setDeleted(rs.getBoolean("Deleted"));
        	methodTypes.setMethodsCollection(methodDao.findByMethodTypes(methodTypes));
            result.add(methodTypes);
        }
    } catch (Exception e) {
        throw new PersistException(e);
    }
    return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			MethodTypes methodTypes) throws PersistException {
        try {
            statement.setString(1, methodTypes.getShortName());
            statement.setString(2, methodTypes.getFullName());
            statement.setInt(3, methodTypes.getUserCreated().getId());
            statement.setDate(4, new java.sql.Date( methodTypes.getDateCreated().getTime()));
            statement.setBoolean(5, methodTypes.getDeleted());
        } catch (Exception e) {
            throw new PersistException(e);
        }
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			MethodTypes methodTypes) throws PersistException {
        try {
            statement.setString(1, methodTypes.getShortName());
            statement.setString(2, methodTypes.getFullName());
            statement.setInt(3, methodTypes.getUserCreated().getId());
            statement.setDate(4, new java.sql.Date( methodTypes.getDateCreated().getTime()));
            statement.setBoolean(5, methodTypes.getDeleted());
            statement.setInt(6, methodTypes.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}
	
	public Collection<MethodTypes> findByName(String name) throws PersistException{	
		ArrayList<MethodTypes> methodTypeList = new ArrayList<MethodTypes>(); 
		methodTypeList.add( findByNeedle("Name", name));
		return methodTypeList;
	}
	
	public Collection<MethodTypes> findByDescription(String description) throws PersistException{	
		ArrayList<MethodTypes> methodTypeList = new ArrayList<MethodTypes>(); 
		methodTypeList.add( findByNeedle("Description", "%" + description + "%"));
		return methodTypeList;
	}
}
