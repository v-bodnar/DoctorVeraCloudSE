package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.Rooms;
import ua.kiev.doctorvera.entity.Users;

public class RoomsMySql extends AbstractMySql<Rooms, Integer> {
	private Connection connection;
	private final String TABLE_NAME = "Rooms";

	private UsersMySql usersDao = (UsersMySql)MySqlDaoFactory.getInstance().getDao(Users.class);
	
	public RoomsMySql(Connection connection) {
		super(connection);
		this.connection = connection;
	}
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public Rooms add() throws PersistException {
		Rooms rooms = new Rooms();
		return persist(rooms);
	}

	
	@Override
	protected List<Rooms> parseResultSet(ResultSet rs) throws PersistException{
    LinkedList<Rooms> result = new LinkedList<Rooms>();
    try {
        while (rs.next()) {
        	Rooms room = new Rooms();
        	room.setId(rs.getInt("RoomId"));
        	room.setName(rs.getString("Name"));
        	room.setDescription(rs.getString("Name"));
        	room.setUserCreated(usersDao.findByPK(rs.getInt("UserCreated")));
        	room.setDateCreated(rs.getDate("DateCreated"));
        	room.setDeleted(rs.getBoolean("Deleted"));
            result.add(room);
        }
    } catch (Exception e) {
        throw new PersistException(e);
    }
    return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Rooms room) throws PersistException {
        try {
        	statement.setString(1, room.getName());
        	statement.setString(2, room.getDescription());
        	statement.setInt(3, room.getUserCreated().getId());
            statement.setDate(4, new java.sql.Date( room.getDateCreated().getTime()));
            statement.setBoolean(5, room.getDeleted());
        } catch (Exception e) {
            throw new PersistException(e);
        }
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Rooms room) throws PersistException {
        try {
        	statement.setString(1, room.getName());
        	statement.setString(2, room.getDescription());
        	statement.setInt(3, room.getUserCreated().getId());
            statement.setDate(4, new java.sql.Date( room.getDateCreated().getTime()));
            statement.setBoolean(5, room.getDeleted());
            statement.setInt(6, room.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}
	
	public Collection<Rooms> findByName(String name) throws PersistException{	
		ArrayList<Rooms> roomsList = new ArrayList<Rooms>(); 
		roomsList.add( findByNeedle("Name", name));
		return roomsList;
	}
	
	public Collection<Rooms> findByDescription(String description) throws PersistException{	
		ArrayList<Rooms> roomsList = new ArrayList<Rooms>(); 
		roomsList.add( findByNeedle("Description", "%" + description + "%"));
		return roomsList;
	}
	

}
