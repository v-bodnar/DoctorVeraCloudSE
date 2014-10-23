package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.PolicyHasUserTypes;
import ua.kiev.doctorvera.entity.UserTypes;
import ua.kiev.doctorvera.entity.Users;

public class UserTypesMySql extends AbstractMySql<UserTypes, Integer>{
	
	Connection connection = new MySqlDaoFactory().getConnection();
	private final String TABLE_NAME = "UserTypes";
	
	public UserTypesMySql(Connection connection) {
		super(connection);
		this.connection = connection;
	}
	
	UsersMySql usersDao = (UsersMySql)new MySqlDaoFactory().getDao(connection, Users.class);
	PolicyHasUserTypesMySql policyHasUserTypesDao = (PolicyHasUserTypesMySql)new MySqlDaoFactory().getDao(connection, PolicyHasUserTypes.class);
	  
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
   
	
	@Override
	public UserTypes add() throws PersistException {
		UserTypes userTypes = new UserTypes();
		return persist(userTypes);
	}

	
	@Override
	protected List<UserTypes> parseResultSet(ResultSet rs) throws PersistException{
    LinkedList<UserTypes> result = new LinkedList<UserTypes>();
    try {
        while (rs.next()) {
        	UserTypes userType = new UserTypes();
        	userType.setId(rs.getInt("UserTypeId"));
        	userType.setName(rs.getString("Name"));
        	userType.setDescription(rs.getString("Description"));
        	userType.setUsersCollection(usersDao.findByUserType(userType));
        	userType.setPolicyCollection(policyHasUserTypesDao.findByUserType(userType));
        	userType.setUserCreated(usersDao.findByPK(rs.getInt("UserCreated")));
        	userType.setDateCreated(rs.getDate("DateCreated"));
        	userType.setDeleted(rs.getBoolean("Deleted"));
            result.add(userType);
        }
    } catch (Exception e) {
        throw new PersistException(e);
    }
    return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			UserTypes userType) throws PersistException {
        try {
        	statement.setString(1, userType.getName());
        	statement.setString(2, userType.getDescription());
        	statement.setInt(3, userType.getUserCreated().getId());
            statement.setDate(4, new java.sql.Date( userType.getDateCreated().getTime()));
            statement.setBoolean(5, userType.getDeleted());
        } catch (Exception e) {
            throw new PersistException(e);
        }
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			UserTypes userType) throws PersistException {
        try {
        	statement.setString(1, userType.getName());
        	statement.setString(2, userType.getDescription());
        	statement.setInt(3, userType.getUserCreated().getId());
            statement.setDate(4, new java.sql.Date( userType.getDateCreated().getTime()));
            statement.setBoolean(5, userType.getDeleted());
            statement.setInt(6, userType.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}
	
	public Collection<UserTypes> findByName(UserTypes userType) throws PersistException{	
		ArrayList<UserTypes> userTypes = new ArrayList<UserTypes>(); 
		userTypes.add( findByNeedle("Name", userType.getName()));
		return userTypes;
	}
	
	public Collection<UserTypes> findByDescription(UserTypes userType) throws PersistException{	
		ArrayList<UserTypes> userTypes = new ArrayList<UserTypes>(); 
		userTypes.add( findByNeedle("Description", "%" + userType.getDescription() + "%"));
		return userTypes;
	}
	
	

}
