package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.Payments;
import ua.kiev.doctorvera.entity.Schedule;
import ua.kiev.doctorvera.entity.Users;

public class PaymentsMySql extends AbstractMySql<Payments, Integer> {
	// private Connection connection;
	private final String TABLE_NAME = "Payments";

	public PaymentsMySql(Connection connection) {
		super(connection);
		// this.connection = connection;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public Payments add() throws PersistException {
		Payments payments = new Payments();
		return persist(payments);
	}

	@Override
	protected List<Payments> parseResultSet(ResultSet rs) throws PersistException {
		LinkedList<Payments> result = new LinkedList<Payments>();
		UsersMySql usersDao = (UsersMySql) MySqlDaoFactory.getInstance().getDao(Users.class);
		ScheduleMySql scheduleDao = (ScheduleMySql) MySqlDaoFactory.getInstance().getDao(
				Schedule.class);
		try {
			while (rs.next()) {
				Payments payment = new Payments();
				payment.setId(rs.getInt("PaymentId"));
				payment.setDataTime(rs.getDate("DataTime"));
				payment.setTotal(rs.getFloat("Total"));
				payment.setDescription(rs.getString("Description"));
				payment.setSchedule(scheduleDao.findByPK(rs.getInt("Schedule")));
				payment.setUserCreated(usersDao.findByPK(rs.getInt("UserCreated")));
				payment.setDateCreated(rs.getDate("DateCreated"));
				payment.setDeleted(rs.getBoolean("Deleted"));
				result.add(payment);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement, Payments payment)
			throws PersistException {
		try {
			statement.setDate(1, new java.sql.Date(payment.getDataTime().getTime()));
			statement.setFloat(2, payment.getTotal());
			statement.setString(3, payment.getDescription());
			statement.setInt(4, payment.getSchedule().getId());
			statement.setInt(5, payment.getRecipient().getId());
			statement.setInt(6, payment.getUserCreated().getId());
			statement.setDate(7, new java.sql.Date(payment.getDateCreated().getTime()));
			statement.setBoolean(8, payment.getDeleted());
		} catch (Exception e) {
			throw new PersistException(e);
		}

	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Payments payment)
			throws PersistException {
		try {
			statement.setDate(1, new java.sql.Date(payment.getDataTime().getTime()));
			statement.setFloat(2, payment.getTotal());
			statement.setString(3, payment.getDescription());
			statement.setInt(4, payment.getSchedule().getId());
			statement.setInt(5, payment.getRecipient().getId());
			statement.setInt(6, payment.getUserCreated().getId());
			statement.setDate(7, new java.sql.Date(payment.getDateCreated().getTime()));
			statement.setBoolean(8, payment.getDeleted());
			statement.setInt(9, payment.getId());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	public Collection<Payments> findByTotal(Float total) {
		ArrayList<Payments> paymentsList = new ArrayList<Payments>();
		try {
			paymentsList.add(findByNeedle("Total", total.toString()));
		} catch (PersistException e) {
			return null;
		}
		return paymentsList;
	}

	public Collection<Payments> findByTotalBetween(Float min, Float max) {
		ArrayList<Payments> result = new ArrayList<Payments>();
		List<Payments> paymentsList;
		try {
			paymentsList = findAll();
		} catch (PersistException e) {
			return null;
		}
		for (Payments payment : paymentsList) {
			if (min <= payment.getTotal() && payment.getTotal() <= max)
				result.add(payment);
		}
		return result;
	}

	public Collection<Payments> findByDescription(String description) {
		ArrayList<Payments> paymentsList = new ArrayList<Payments>();
		try {
			paymentsList.add(findByNeedle("Description", description));
		} catch (PersistException e) {
			return null;
		}
		return paymentsList;
	}

	public Collection<Payments> findBySchedule(Schedule schedule) {
		ArrayList<Payments> paymentsList = new ArrayList<Payments>();
		try {
			paymentsList.add(findByNeedle("Schedule", schedule.getId().toString()));
		} catch (PersistException e) {
			return null;
		}
		return paymentsList;
	}

	public Collection<Payments> findByRecipient(Users recipient) {
		ArrayList<Payments> paymentsList = new ArrayList<Payments>();
		try {
			paymentsList.add(findByNeedle("Recipient", recipient.getId().toString()));
		} catch (PersistException e) {
			return null;
		}
		return paymentsList;
	}

}
