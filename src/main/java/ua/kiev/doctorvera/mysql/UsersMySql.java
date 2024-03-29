package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.Address;
import ua.kiev.doctorvera.entity.UserTypes;
import ua.kiev.doctorvera.entity.Users;

public class UsersMySql extends AbstractMySql<Users, Integer> {

	// private Connection connection;
	private final String TABLE_NAME = "Users";

	public UsersMySql(Connection connection) {
		super(connection);
		// this.connection = connection;
		System.setProperty("file.encoding" , "UTF-8");
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
	protected List<Users> parseResultSet(ResultSet rs) throws PersistException {
		LinkedList<Users> result = new LinkedList<Users>();
		//AddressMySql addressDao = (AddressMySql) MySqlDaoFactory.getInstance().getDao(Address.class);
		//UserTypesMySql userTypeDao = (UserTypesMySql) MySqlDaoFactory.getInstance().getDao(UserTypes.class);
		try {
			while (rs.next()) {
				Users user = new Users();
				user.setId(rs.getInt("UserId"));
				user.setUsername(rs.getString("Username"));
				user.setPasswordHash(rs.getString("Password"));
				user.setFirstName(rs.getString("FirstName"));
				user.setMiddleName(rs.getString("MiddleName"));
				user.setLastName(rs.getString("LastName"));
				user.setAddressId(rs.getInt("Address"));
				user.setBirthDate(rs.getDate("BirthDate"));
				user.setPhoneNumberHome(rs.getString("PhoneNumberHome"));
				user.setPhoneNumberMobile(rs.getString("PhoneNumberMobile"));
				user.setDescription(rs.getString("Description"));
				user.setUserTypeId(rs.getInt("UserType"));
				user.setUserCreatedId(rs.getInt("UserCreated"));
				user.setDateCreated(rs.getDate("DateCreated"));
				user.setDeleted(rs.getBoolean("Deleted"));
				result.add(user);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement, Users user)
			throws PersistException {
		try {
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getMiddleName());
			statement.setString(5, user.getLastName());
			statement.setInt(6, user.getAddressId());
			statement.setDate(7, new java.sql.Date(user.getBirthDate().getTime()));
			statement.setString(8, user.getPhoneNumberHome());
			statement.setString(9, user.getPhoneNumberMobile());
			statement.setString(10, user.getDescription());
			statement.setInt(11, user.getUserType().getId());
			statement.setInt(12, user.getUserCreatedId());
			statement.setDate(13, new java.sql.Date(user.getDateCreated().getTime()));
			statement.setBoolean(14, user.getDeleted());
		} catch (Exception e) {
			throw new PersistException(e);
		}

	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Users user)
			throws PersistException {
		try {
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getMiddleName());
			statement.setString(5, user.getLastName());
			statement.setInt(6, user.getAddressId());
			statement.setDate(7, new java.sql.Date(user.getBirthDate().getTime()));
			statement.setString(8, user.getPhoneNumberHome());
			statement.setString(9, user.getPhoneNumberMobile());
			statement.setString(10, user.getDescription());
			statement.setInt(11, user.getUserType().getId());
			statement.setInt(12, user.getUserCreatedId());
			statement.setDate(13, new java.sql.Date(user.getDateCreated().getTime()));
			statement.setBoolean(14, user.getDeleted());
			statement.setInt(15, user.getId());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	public Users findByUsername(String username) {
		Users user;
		try {
			user=findByNeedle("UserName", username);
		} catch (PersistException e) {
			return null;
		}
		return user;
	}

	public Collection<Users> findByFirstName(String firstName) {
		ArrayList<Users> usersList = new ArrayList<Users>();
		try {
			usersList.add(findByNeedle("FirstName", firstName));
		} catch (PersistException e) {
			return null;
		}
		return usersList;
	}

	public Collection<Users> findByMiddleName(String middleName) {
		ArrayList<Users> usersList = new ArrayList<Users>();
		try {
			usersList.add(findByNeedle("MiddleName", middleName));
		} catch (PersistException e) {
			return null;
		}
		return usersList;
	}

	public Collection<Users> findByLastName(String lastName) {
		ArrayList<Users> usersList = new ArrayList<Users>();
		try {
			usersList.add(findByNeedle("LastName", lastName));
		} catch (PersistException e) {
			return null;
		}
		return usersList;
	}

	public Collection<Users> findByPhoneNumberMobile(String phoneNumberMobile)
			throws PersistException {
		ArrayList<Users> usersList = new ArrayList<Users>();
		try {
			usersList.add(findByNeedle("PhoneNumberMobile", phoneNumberMobile));
		} catch (PersistException e) {
			return null;
		}
		return usersList;
	}

	public Collection<Users> findByPhoneNumberHome(String phoneNumberHome) {
		ArrayList<Users> usersList = new ArrayList<Users>();
		try {
			usersList.add(findByNeedle("PhoneNumberHome", phoneNumberHome));
		} catch (PersistException e) {
			return null;
		}
		return usersList;
	}

	public Collection<Users> findByDescription(String description) {
		ArrayList<Users> usersList = new ArrayList<Users>();
		try {
			usersList.add(findByNeedle("Description", "%" + description + "%"));
		} catch (PersistException e) {
			return null;
		}
		return usersList;
	}

	public Collection<Users> findByUserType(UserTypes userType) {
		ArrayList<Users> usersList = new ArrayList<Users>();
		try {
			usersList.add(findByNeedle("UserType", userType.getId().toString()));
		} catch (Exception e) {
			return null;
		}
		return usersList;
	}

	public Collection<Users> findByAddress(Address address) throws PersistException {
		ArrayList<Users> usersList = new ArrayList<Users>();
		try {
			usersList.add(findByNeedle("Address", address.getId().toString()));
		} catch (PersistException e) {
			return null;
		}
		return usersList;
	}

}
