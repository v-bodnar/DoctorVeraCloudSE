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
import ua.kiev.doctorvera.entity.Users;

public class AddressMySql extends AbstractMySql<Address, Integer> {
	// private Connection connection;
	private final String TABLE_NAME = "Address";

	public AddressMySql(Connection connection) {
		super(connection);
		// this.connection = connection;
		System.setProperty("file.encoding" , "UTF-8");
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public Address add() throws PersistException {
		Address address = new Address();
		return persist(address);
	}

	@Override
	protected List<Address> parseResultSet(ResultSet rs)
			throws PersistException {
		LinkedList<Address> result = new LinkedList<Address>();
		UsersMySql usersDao = (UsersMySql) MySqlDaoFactory.getInstance()
				.getDao(Users.class);
		try {
			while (rs.next()) {
				Address address = new Address();
				address.setId(rs.getInt("AddressId"));
				address.setCountry(rs.getString("Country"));
				address.setRegion(rs.getString("Region"));
				address.setCity(rs.getString("City"));
				address.setAddress(rs.getString("Address"));
				address.setPostIndex(rs.getInt("PostIndex"));
				//address.setUsersCollection(usersDao.findByAddress(address));
				address.setUserCreated(usersDao.findByPK(rs.getInt("UserCreated")));
				address.setDateCreated(rs.getDate("DateCreated"));
				address.setDeleted(rs.getBoolean("Deleted"));
				result.add(address);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Address address) throws PersistException {
		try {
			statement.setString(1, address.getCountry());
			statement.setString(2, address.getRegion());
			statement.setString(3, address.getCity());
			statement.setString(4, address.getAddress());
			statement.setInt(5, address.getPostIndex());
			statement.setInt(6, address.getUserCreated().getId());
			statement.setDate(7, new java.sql.Date(address.getDateCreated().getTime()));
			statement.setBoolean(8, address.getDeleted());
		} catch (Exception e) {
			throw new PersistException(e);
		}

	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Address address) throws PersistException {
		try {
			statement.setString(1, address.getCountry());
			statement.setString(2, address.getRegion());
			statement.setString(3, address.getCity());
			statement.setString(4, address.getAddress());
			statement.setInt(5, address.getPostIndex());
			statement.setInt(6, address.getUserCreated().getId());
			statement.setDate(7, new java.sql.Date(address.getDateCreated().getTime()));
			statement.setBoolean(8, address.getDeleted());
			statement.setInt(9, address.getId());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	public Collection<Address> findByCountry(String country) {
		ArrayList<Address> addressList = new ArrayList<Address>();
		try {
			addressList.add(findByNeedle("Country", country));
		} catch (PersistException e) {
			return null;
		}
		return addressList;
	}

	public Collection<Address> findByCity(String city) {
		ArrayList<Address> addressList = new ArrayList<Address>();
		try {
			addressList.add(findByNeedle("City", city));
		} catch (PersistException e) {
			return null;
		}
		return addressList;
	}

	public Collection<Address> findByRegion(String region) {
		ArrayList<Address> addressList = new ArrayList<Address>();
		try {
			addressList.add(findByNeedle("Region", region));
		} catch (PersistException e) {
			return null;
		}
		return addressList;
	}

	public Collection<Address> findByStreet(String street) {
		ArrayList<Address> addressList = new ArrayList<Address>();
		try {
			addressList.add(findByNeedle("Street", "%" + street + "%"));
		} catch (PersistException e) {
			return null;
		}
		return addressList;
	}

	public Collection<Address> findByIndex(Integer index) {

		ArrayList<Address> addressList = new ArrayList<Address>();
		try {
			addressList.add(findByNeedle("Index", index.toString()));
		} catch (PersistException e) {
			return null;
		}
		return addressList;
	}

	public Collection<Address> findByDateCreated(java.sql.Date dateCreated) {
		ArrayList<Address> addressList = new ArrayList<Address>();
		try {
			addressList
					.add(findByNeedle("DateCreated", dateCreated.toString()));
		} catch (PersistException e) {
			return null;
		}
		return addressList;
	}

}
