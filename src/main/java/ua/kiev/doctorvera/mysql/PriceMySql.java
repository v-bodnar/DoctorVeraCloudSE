package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ua.kiev.doctorvera.dao.AbstractJDBCDao;
import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.UserTypes;
import ua.kiev.doctorvera.entity.Users;

public class PriceMySql extends AbstractMySql<Users, Integer> {
	private Connection connection;
	private final String TABLE_NAME = "Users";

	//@SuppressWarnings("unchecked")
	//private final GenericDao<Users, Integer> userDao = new MySqlDaoFactory().getDao(Users.class);
	
	public PriceMySql(Connection connection) {
		super(connection);
		this.connection = connection;
	}
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public Users add() throws PersistException {
		Users users = new Users();
		return persist(users);
	}

	
	@Override
	protected List<Users> parseResultSet(ResultSet rs) throws PersistException{
    LinkedList<Users> result = new LinkedList<Users>();
    try {
        while (rs.next()) {
        	Users user = new Users();
        	user.setId(rs.getInt("UserId"));
        	user.setUsername(rs.getString("Username"));
        	user.setPassword(rs.getString("Password"));
        	user.setFirstName(rs.getString("FirstName"));
        	user.setMiddleName(rs.getString("MiddleName"));
        	user.setLastName(rs.getString("LastName"));
        	//user.setAddress(rs.getString("Address"));
        	//user.setBirthDate(rs.getDate("BirthDate"));
        	user.setPhoneNumberHome(rs.getString("PhoneNumberHome"));
        	user.setPhoneNumberMobile(rs.getString("PhoneNumberMobile"));
        	user.setDescription(rs.getString("Description"));
        	//user.setUserTypeId(rs.getString("UserTypeId"));
        	//user.setId(rs.getInt("CreatedUserId"));
        	user.setDeleted(rs.getBoolean("Deleted"));
            result.add(user);
        }
    } catch (Exception e) {
        throw new PersistException(e);
    }
    return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Users user) throws PersistException {
        try {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getMiddleName());
            statement.setString(5, user.getLastName());
            //statement.setInt(6, user.getAddressId().getId());
            //statement.setDate(7, user.getBirthDate());
            statement.setString(8, user.getPhoneNumberHome());
            statement.setString(9, user.getPhoneNumberMobile());
            statement.setString(10, user.getDescription());
            //statement.setInt(11, user.getUserTypeId().getId());
            statement.setInt(12, user.getCreatedUserId());
            statement.setBoolean(13, user.getDeleted());
        } catch (Exception e) {
            throw new PersistException(e);
        }
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Users user) throws PersistException {
        try {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getMiddleName());
            statement.setString(5, user.getLastName());
            //statement.setInt(6, user.getAddressId().getId());
            //statement.setDate(7, user.getBirthDate());
            statement.setString(8, user.getPhoneNumberHome());
            statement.setString(9, user.getPhoneNumberMobile());
            statement.setString(10, user.getDescription());
            //statement.setInt(11, user.getUserTypeId().getId());
            statement.setInt(12, user.getCreatedUserId());
            statement.setBoolean(13, user.getDeleted());
            statement.setInt(14, user.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}
	
	
	
	public Collection<Users> getByType(UserTypes userType) throws PersistException{	
		ArrayList<Users> usersList = new ArrayList<Users>(); 
		usersList.add( findByNeedle("userTypeId", userType.getId().toString()));
		return usersList;
	}

}
