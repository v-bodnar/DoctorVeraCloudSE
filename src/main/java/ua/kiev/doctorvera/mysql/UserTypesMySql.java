package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import ua.kiev.doctorvera.dao.AbstractJDBCDao;
import ua.kiev.doctorvera.dao.PersistException;
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
	  
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
   
	
	@Override
	public UserTypes create() throws PersistException {
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
        	userType.setCreatedUserId(usersDao.getByPK(rs.getInt("CreatedUserId")));
        	userType.setDeleted(rs.getBoolean("Deleted"));
        	userType.setUserCollection(usersDao.getByType(userType));
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
            statement.setInt(3, userType.getCreatedUserId().getId());
            statement.setBoolean(4, userType.getDeleted());
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
            statement.setInt(3, userType.getCreatedUserId().getId());
            statement.setBoolean(4, userType.getDeleted());
            statement.setInt(5, userType.getUserTypeId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}

}
