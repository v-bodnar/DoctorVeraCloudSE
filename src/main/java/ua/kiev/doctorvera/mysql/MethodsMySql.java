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

public class MethodsMySql extends AbstractMySql<Methods, Integer> {
	// private Connection connection;
	private final String TABLE_NAME = "Methods";

	public MethodsMySql(Connection connection) {
		super(connection);
		// this.connection = connection;
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
	protected List<Methods> parseResultSet(ResultSet rs) throws PersistException {
		LinkedList<Methods> result = new LinkedList<Methods>();
		UsersMySql usersDao = (UsersMySql) MySqlDaoFactory.getInstance().getDao(Users.class);
		//PricesMySql priceDao = (PricesMySql) MySqlDaoFactory.getInstance().getDao(Prices.class);
		//DoctorsHasMethodMySql doctorsHasMethodDao = (DoctorsHasMethodMySql) MySqlDaoFactory.getInstance().getDao(DoctorsHasMethod.class);
		MethodTypesMySql methodTypesDao = (MethodTypesMySql) MySqlDaoFactory.getInstance().getDao(
				MethodTypes.class);

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
				//methods.setDoctorsCollection(doctorsHasMethodDao.findByMethod(methods));
				//methods.setPricesCollection(priceDao.findByMethod(methods));
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
	protected void prepareStatementForInsert(PreparedStatement statement, Methods methods)
			throws PersistException {
		try {
			statement.setInt(1, methods.getMethodType().getId());
			statement.setString(2, methods.getShortName());
			statement.setString(3, methods.getFullName());
			statement.setString(4, methods.getShortDescription());
			statement.setString(5, methods.getFullDescription());
			statement.setInt(6, methods.getTimeInMinutes());
			statement.setInt(7, methods.getUserCreated().getId());
			statement.setDate(8, new java.sql.Date(methods.getDateCreated().getTime()));
			statement.setBoolean(9, methods.getDeleted());
		} catch (Exception e) {
			throw new PersistException(e);
		}

	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Methods methods)
			throws PersistException {
		try {
			statement.setInt(1, methods.getMethodType().getId());
			statement.setString(2, methods.getShortName());
			statement.setString(3, methods.getFullName());
			statement.setString(4, methods.getShortDescription());
			statement.setString(5, methods.getFullDescription());
			statement.setInt(6, methods.getTimeInMinutes());
			statement.setInt(7, methods.getUserCreated().getId());
			statement.setDate(8, new java.sql.Date(methods.getDateCreated().getTime()));
			statement.setBoolean(9, methods.getDeleted());
			statement.setInt(10, methods.getId());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	public Collection<Methods> findByMethodTypes(MethodTypes methodType) {
		ArrayList<Methods> methodList = new ArrayList<Methods>();
		try {
			methodList.add(findByNeedle("MethodType", methodType.getId().toString()));
		} catch (PersistException e) {
			return null;
		}
		return methodList;
	}

	public Collection<Methods> findByShortName(String shortName) {
		ArrayList<Methods> methodList = new ArrayList<Methods>();
		try {
			methodList.add(findByNeedle("ShortName", shortName));
		} catch (PersistException e) {
			return null;
		}
		return methodList;
	}

	public Collection<Methods> findByFullName(String fullName) {
		ArrayList<Methods> methodList = new ArrayList<Methods>();
		try {
			methodList.add(findByNeedle("FullName", fullName));
		} catch (PersistException e) {
			return null;
		}
		return methodList;
	}

	public Collection<Methods> findByShortDescription(String shortDescription) {
		ArrayList<Methods> methodList = new ArrayList<Methods>();
		try {
			methodList.add(findByNeedle("ShortDescription", "%" + shortDescription + "%"));
		} catch (PersistException e) {
			return null;
		}
		return methodList;
	}

	public Collection<Methods> findByFullDescription(String fullDescription) {
		ArrayList<Methods> methodList = new ArrayList<Methods>();
		try {
			methodList.add(findByNeedle("FullDescription", "%" + fullDescription + "%"));
		} catch (PersistException e) {
			return null;
		}
		return methodList;
	}

	public Collection<Methods> findByTime(Integer minutes) {
		ArrayList<Methods> methodList = new ArrayList<Methods>();
		try {
			methodList.add(findByNeedle("TimeInMinutes", minutes.toString()));
		} catch (PersistException e) {
			return null;
		}
		return methodList;
	}

	public Collection<Methods> findByTimeBetween(Integer min, Integer max) {
		List<Methods> methodList;
		try {
			methodList = findAll();
		} catch (PersistException e) {
			return null;
		}
		ArrayList<Methods> result = new ArrayList<Methods>();
		for (Methods method : methodList) {
			if (min <= method.getTimeInMinutes() && method.getTimeInMinutes() <= max)
				result.add(method);
		}
		return result;
	}

}
