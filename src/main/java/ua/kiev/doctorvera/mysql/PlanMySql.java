package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.Plan;
import ua.kiev.doctorvera.entity.Rooms;
import ua.kiev.doctorvera.entity.Users;

public class PlanMySql extends AbstractMySql<Plan, Integer> {
	private Connection connection;
	private final String TABLE_NAME = "Plan";

	private UsersMySql usersDao = (UsersMySql)new MySqlDaoFactory().getDao(connection, Users.class);
	private RoomsMySql roomsDao = (RoomsMySql)new MySqlDaoFactory().getDao(connection, Rooms.class);
	
	public PlanMySql(Connection connection) {
		super(connection);
		this.connection = connection;
	}
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public Plan add() throws PersistException {
		Plan plans = new Plan();
		return persist(plans);
	}

	
	@Override
	protected List<Plan> parseResultSet(ResultSet rs) throws PersistException{
    LinkedList<Plan> result = new LinkedList<Plan>();
    try {
        while (rs.next()) {
        	Plan plan = new Plan();
        	plan.setId(rs.getInt("PlanId"));
        	plan.setDoctor(usersDao.findByPK(rs.getInt("Doctor")));
        	plan.setRoom(roomsDao.findByPK(rs.getInt("Room")));
        	plan.setDateTimeStart(rs.getDate("DateTimeStart"));
        	plan.setDateTimeEnd(rs.getDate("DateTimeEnd"));
        	plan.setDescription(rs.getString("Description"));
        	plan.setUserCreated(usersDao.findByPK(rs.getInt("UserCreated")));
        	plan.setDateCreated(rs.getDate("DateCreated"));
        	plan.setDeleted(rs.getBoolean("Deleted"));
            result.add(plan);
        }
    } catch (Exception e) {
        throw new PersistException(e);
    }
    return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Plan plan) throws PersistException {
        try {
        	statement.setDate(1, new java.sql.Date( plan.getDateTimeStart().getTime()));
        	statement.setDate(2, new java.sql.Date( plan.getDateTimeEnd().getTime()));
        	statement.setString(3, plan.getDescription());
        	statement.setInt(4, plan.getDoctor().getId());
        	statement.setInt(5, plan.getRoom().getId());
            statement.setInt(6, plan.getUserCreated().getId());
            statement.setDate(7, new java.sql.Date( plan.getDateCreated().getTime()));
            statement.setBoolean(8, plan.getDeleted());
        } catch (Exception e) {
            throw new PersistException(e);
        }
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Plan plan) throws PersistException {
        try {
        	statement.setDate(1, new java.sql.Date( plan.getDateTimeStart().getTime()));
        	statement.setDate(2, new java.sql.Date( plan.getDateTimeEnd().getTime()));
        	statement.setString(3, plan.getDescription());
        	statement.setInt(4, plan.getDoctor().getId());
        	statement.setInt(5, plan.getRoom().getId());
            statement.setInt(6, plan.getUserCreated().getId());
            statement.setDate(7, new java.sql.Date( plan.getDateCreated().getTime()));
            statement.setBoolean(8, plan.getDeleted());
            statement.setInt(9, plan.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}
	
	public Collection<Plan> findByDoctor(Users doctor) throws PersistException{	
		ArrayList<Plan> planList = new ArrayList<Plan>(); 
		planList.add( findByNeedle("Doctor", doctor.getId().toString()));
		return planList;
	}
	public Collection<Plan> findByRoom(Rooms room) throws PersistException{	
		ArrayList<Plan> planList = new ArrayList<Plan>(); 
		planList.add( findByNeedle("Room", room.getId().toString()));
		return planList;
	}
	public Collection<Plan> findByTime(Date date) throws PersistException{	
		List<Plan> planList = findAll();
		ArrayList<Plan> result = new ArrayList<Plan>();
		for (Plan plan : planList){
			if(plan.getDateTimeStart().before(date) && plan.getDateTimeEnd().before(date)) result.add(plan);
		}
		return result;
	}
	public Collection<Plan> findByTimeBetween(Date min, Date max) throws PersistException{	
		List<Plan> planList = findAll();
		ArrayList<Plan> result = new ArrayList<Plan>();
		for (Plan plan : planList){
			if(
					(plan.getDateTimeStart().after(min) && plan.getDateTimeStart().before(max)) ||
					(plan.getDateTimeEnd().after(min) && plan.getDateTimeEnd().before(max)) ||
					(plan.getDateTimeStart().before(min) && plan.getDateTimeEnd().after(max))
					) result.add(plan);
		}
		return result;
	}
	public Collection<Plan> findByDescription(String description) throws PersistException{	
		ArrayList<Plan> planList = new ArrayList<Plan>(); 
		planList.add( findByNeedle("Description", description));
		return planList;
	}
	

}
