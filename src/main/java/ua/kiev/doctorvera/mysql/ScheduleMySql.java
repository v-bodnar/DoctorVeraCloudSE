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
import ua.kiev.doctorvera.entity.Methods;
import ua.kiev.doctorvera.entity.Rooms;
import ua.kiev.doctorvera.entity.Schedule;
import ua.kiev.doctorvera.entity.Users;

public class ScheduleMySql extends AbstractMySql<Schedule, Integer> {
	private Connection connection;
	private final String TABLE_NAME = "Schedule";

	private UsersMySql usersDao = (UsersMySql)new MySqlDaoFactory().getDao(connection, Users.class);
	private MethodsMySql methodsDao = (MethodsMySql)new MySqlDaoFactory().getDao(connection, Methods.class);
	private RoomsMySql roomsDao = (RoomsMySql)new MySqlDaoFactory().getDao(connection, Rooms.class);
	
	public ScheduleMySql(Connection connection) {
		super(connection);
		this.connection = connection;
	}
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public Schedule add() throws PersistException {
		Schedule schedules = new Schedule();
		return persist(schedules);
	}

	
	@Override
	protected List<Schedule> parseResultSet(ResultSet rs) throws PersistException{
    LinkedList<Schedule> result = new LinkedList<Schedule>();
    try {
        while (rs.next()) {
        	Schedule schedule = new Schedule();
        	schedule.setId(rs.getInt("SceduleId"));
        	schedule.setDoctor(usersDao.findByPK(rs.getInt("Doctor")));
        	schedule.setPatient(usersDao.findByPK(rs.getInt("Patient")));
        	schedule.setAssistant(usersDao.findByPK(rs.getInt("Assistant")));
        	schedule.setDoctorDirected(usersDao.findByPK(rs.getInt("DoctorDirected")));
        	schedule.setRoom(roomsDao.findByPK(rs.getInt("Room")));
        	schedule.setMethod(methodsDao.findByPK(rs.getInt("Method")));
        	schedule.setDateTimeStart(rs.getDate("DateTimeStart"));
        	schedule.setDateTimeEnd(rs.getDate("DateTimeEnd"));
        	schedule.setDescription(rs.getString("Description"));
        	schedule.setUserCreated(usersDao.findByPK(rs.getInt("UserCreated")));
        	schedule.setDateCreated(rs.getDate("DateCreated"));
        	schedule.setDeleted(rs.getBoolean("Deleted"));
            result.add(schedule);
        }
    } catch (Exception e) {
        throw new PersistException(e);
    }
    return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Schedule schedule) throws PersistException {
        try {
        	statement.setInt(1, schedule.getDoctor().getId());
        	statement.setInt(2, schedule.getPatient().getId());
        	statement.setInt(3, schedule.getAssistant().getId());
        	statement.setInt(4, schedule.getDoctorDirected().getId());
        	statement.setInt(5, schedule.getRoom().getId());
        	statement.setInt(6, schedule.getMethod().getId());
        	statement.setDate(7, new java.sql.Date( schedule.getDateTimeStart().getTime()));
        	statement.setDate(8, new java.sql.Date( schedule.getDateTimeEnd().getTime()));
        	statement.setString(9, schedule.getDescription());
            statement.setInt(10, schedule.getUserCreated().getId());
            statement.setDate(11, new java.sql.Date( schedule.getDateCreated().getTime()));
            statement.setBoolean(12, schedule.getDeleted());
        } catch (Exception e) {
            throw new PersistException(e);
        }
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Schedule schedule) throws PersistException {
        try {
        	statement.setInt(1, schedule.getDoctor().getId());
        	statement.setInt(2, schedule.getPatient().getId());
        	statement.setInt(3, schedule.getAssistant().getId());
        	statement.setInt(4, schedule.getDoctorDirected().getId());
        	statement.setInt(5, schedule.getRoom().getId());
        	statement.setInt(6, schedule.getMethod().getId());
        	statement.setDate(7, new java.sql.Date( schedule.getDateTimeStart().getTime()));
        	statement.setDate(8, new java.sql.Date( schedule.getDateTimeEnd().getTime()));
        	statement.setString(9, schedule.getDescription());
            statement.setInt(10, schedule.getUserCreated().getId());
            statement.setDate(11, new java.sql.Date( schedule.getDateCreated().getTime()));
            statement.setBoolean(12, schedule.getDeleted());
            statement.setInt(13, schedule.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}
	
	
	
	public Collection<Schedule> getByDoctor(Users doctor) throws PersistException{	
		ArrayList<Schedule> scheduleList = new ArrayList<Schedule>(); 
		scheduleList.add( findByNeedle("Doctor", doctor.getId().toString()));
		return scheduleList;
	}
	public Collection<Schedule> getByPatient(Users patient) throws PersistException{	
		ArrayList<Schedule> scheduleList = new ArrayList<Schedule>(); 
		scheduleList.add( findByNeedle("Patient", patient.getId().toString()));
		return scheduleList;
	}
	public Collection<Schedule> getByAssistant(Users assistant) throws PersistException{	
		ArrayList<Schedule> scheduleList = new ArrayList<Schedule>(); 
		scheduleList.add( findByNeedle("Assistant", assistant.getId().toString()));
		return scheduleList;
	}
	public Collection<Schedule> getByDoctorDirected(Users doctorDirected) throws PersistException{	
		ArrayList<Schedule> scheduleList = new ArrayList<Schedule>(); 
		scheduleList.add( findByNeedle("DoctorDirected", doctorDirected.getId().toString()));
		return scheduleList;
	}
	public Collection<Schedule> getByRoom(Rooms room) throws PersistException{	
		ArrayList<Schedule> scheduleList = new ArrayList<Schedule>(); 
		scheduleList.add( findByNeedle("Room", room.getId().toString()));
		return scheduleList;
	}
	public Collection<Schedule> getByMethod(Methods method) throws PersistException{	
		ArrayList<Schedule> scheduleList = new ArrayList<Schedule>(); 
		scheduleList.add( findByNeedle("Method", method.getId().toString()));
		return scheduleList;
	}
	public Collection<Schedule> getByTime(Date date) throws PersistException{	
		List<Schedule> scheduleList = findAll();
		ArrayList<Schedule> result = new ArrayList<Schedule>();
		for (Schedule schedule : scheduleList){
			if(schedule.getDateTimeStart().before(date) && schedule.getDateTimeEnd().before(date)) result.add(schedule);
		}
		return result;
	}
	
	public Collection<Schedule> getByTimeBetween(Date min, Date max) throws PersistException{	
		List<Schedule> scheduleList = findAll();
		ArrayList<Schedule> result = new ArrayList<Schedule>();
		for (Schedule schedule : scheduleList){
			if(
					(schedule.getDateTimeStart().after(min) && schedule.getDateTimeStart().before(max)) ||
					(schedule.getDateTimeEnd().after(min) && schedule.getDateTimeEnd().before(max)) ||
					(schedule.getDateTimeStart().before(min) && schedule.getDateTimeEnd().after(max))
					) result.add(schedule);
		}
		return result;
	}
	public Collection<Schedule> getByDescription(String description) throws PersistException{	
		ArrayList<Schedule> scheduleList = new ArrayList<Schedule>(); 
		scheduleList.add( findByNeedle("Description", description));
		return scheduleList;
	}
	
	

}
