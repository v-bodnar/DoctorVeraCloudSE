package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.Methods;
import ua.kiev.doctorvera.entity.Share;
import ua.kiev.doctorvera.entity.Users;

public class ShareMySql extends AbstractMySql<Share, Integer> {
	// private Connection connection;
	private final String TABLE_NAME = "Share";

	public ShareMySql(Connection connection) {
		super(connection);
		// this.connection = connection;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public Share add() throws PersistException {
		Share share = new Share();
		return persist(share);
	}

	@Override
	protected List<Share> parseResultSet(ResultSet rs) throws PersistException {
		LinkedList<Share> result = new LinkedList<Share>();
		UsersMySql usersDao = (UsersMySql) MySqlDaoFactory.getInstance().getDao(Users.class);
		MethodsMySql methodsDao = (MethodsMySql) MySqlDaoFactory.getInstance()
				.getDao(Methods.class);
		try {
			while (rs.next()) {
				Share share = new Share();
				share.setId(rs.getInt("ShareId"));
				share.setSalaryDoctor(rs.getFloat("SalaryDoctor"));
				share.setSalaryAssistant(rs.getFloat("SalaryAssistant"));
				share.setPercentageDoctor(rs.getFloat("PercentageDoctor"));
				share.setPercentageAssistant(rs.getFloat("PercentageAssistant"));
				share.setDateTime(rs.getDate("DateTime"));
				share.setMethod(methodsDao.findByPK(rs.getInt("Method")));
				share.setDoctor(usersDao.findByPK(rs.getInt("Doctor")));
				share.setAssistant(usersDao.findByPK(rs.getInt("Assistant")));
				share.setUserCreated(usersDao.findByPK(rs.getInt("UserCreated")));
				share.setDateCreated(rs.getDate("DateCreated"));
				share.setDeleted(rs.getBoolean("Deleted"));
				result.add(share);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement, Share share)
			throws PersistException {
		try {
			statement.setFloat(1, share.getSalaryDoctor());
			statement.setFloat(2, share.getSalaryAssistant());
			statement.setFloat(3, share.getPercentageDoctor());
			statement.setFloat(4, share.getPercentageAssistant());
			statement.setDate(5, new java.sql.Date(share.getDateTime().getTime()));
			statement.setInt(6, share.getMethod().getId());
			statement.setInt(7, share.getDoctor().getId());
			statement.setInt(8, share.getAssistant().getId());
			statement.setInt(9, share.getUserCreated().getId());
			statement.setDate(10, new java.sql.Date(share.getDateCreated().getTime()));
			statement.setBoolean(11, share.getDeleted());
		} catch (Exception e) {
			throw new PersistException(e);
		}

	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Share share)
			throws PersistException {
		try {
			statement.setFloat(1, share.getSalaryDoctor());
			statement.setFloat(2, share.getSalaryAssistant());
			statement.setFloat(3, share.getPercentageDoctor());
			statement.setFloat(4, share.getPercentageAssistant());
			statement.setDate(5, new java.sql.Date(share.getDateTime().getTime()));
			statement.setInt(6, share.getMethod().getId());
			statement.setInt(7, share.getDoctor().getId());
			statement.setInt(8, share.getAssistant().getId());
			statement.setInt(9, share.getUserCreated().getId());
			statement.setDate(10, new java.sql.Date(share.getDateCreated().getTime()));
			statement.setBoolean(11, share.getDeleted());
			statement.setInt(12, share.getId());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	public Collection<Share> findByDoctor(Users doctor) throws PersistException {
		ArrayList<Share> ShareList = new ArrayList<Share>();
		try {
			ShareList.add(findByNeedle("Doctor", doctor.getId().toString()));
		} catch (PersistException e) {
			return null;
		}
		return ShareList;
	}

	public Collection<Share> findByAssistant(Users assistant) throws PersistException {
		ArrayList<Share> ShareList = new ArrayList<Share>();
		try {
			ShareList.add(findByNeedle("Assistant", assistant.getId().toString()));
		} catch (PersistException e) {
			return null;
		}
		return ShareList;
	}

	public Collection<Share> findByMethod(Methods method) throws PersistException {
		ArrayList<Share> ShareList = new ArrayList<Share>();
		try {
			ShareList.add(findByNeedle("Method", method.getId().toString()));
		} catch (PersistException e) {
			return null;
		}
		return ShareList;
	}

	public Collection<Share> findByDescription(String description) throws PersistException {
		ArrayList<Share> ShareList = new ArrayList<Share>();
		try {
			ShareList.add(findByNeedle("Description", "%" + description + "%"));
		} catch (PersistException e) {
			return null;
		}
		return ShareList;
	}
}
