package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.DoctorsHasMethod;
import ua.kiev.doctorvera.entity.Methods;
import ua.kiev.doctorvera.entity.Users;

public class DoctorsHasMethodMySql  extends AbstractMySql<DoctorsHasMethod, Integer> {
	private Connection connection;
	private final String TABLE_NAME = "DoctorsHasMethod";

	private UsersMySql usersDao = (UsersMySql)new MySqlDaoFactory().getDao(connection, Users.class);
	private MethodsMySql methodsDao = (MethodsMySql)new MySqlDaoFactory().getDao(connection, Methods.class);
	
	public DoctorsHasMethodMySql(Connection connection) {
		super(connection);
		this.connection = connection;
	}
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public DoctorsHasMethod add() throws PersistException {
		DoctorsHasMethod doctorsHasMethod = new DoctorsHasMethod();
		return persist(doctorsHasMethod);
	}

	
	@Override
	protected List<DoctorsHasMethod> parseResultSet(ResultSet rs) throws PersistException{
    LinkedList<DoctorsHasMethod> result = new LinkedList<DoctorsHasMethod>();
    try {
        while (rs.next()) {	
        	DoctorsHasMethod doctorsHasMethod = new DoctorsHasMethod();
        	doctorsHasMethod.setId(rs.getInt("DoctorsHasMethodId"));
        	doctorsHasMethod.setMethod(methodsDao.findByPK(rs.getInt("Method")));
        	doctorsHasMethod.setDoctor(usersDao.findByPK(rs.getInt("Doctor")));
        	doctorsHasMethod.setUserCreated(usersDao.findByPK(rs.getInt("UserCreated")));
        	doctorsHasMethod.setDateCreated(rs.getDate("DateCreated"));
        	doctorsHasMethod.setDeleted(rs.getBoolean("Deleted"));
            result.add(doctorsHasMethod);
        }
    } catch (Exception e) {
        throw new PersistException(e);
    }
    return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			DoctorsHasMethod doctorsHasMethod) throws PersistException {
        try {
        	statement.setInt(1, doctorsHasMethod.getMethod().getId());
        	statement.setInt(2, doctorsHasMethod.getDoctor().getId());
        	statement.setInt(3, doctorsHasMethod.getUserCreated().getId());
            statement.setDate(4, new java.sql.Date( doctorsHasMethod.getDateCreated().getTime()));
            statement.setBoolean(5, doctorsHasMethod.getDeleted());
        } catch (Exception e) {
            throw new PersistException(e);
        }
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			DoctorsHasMethod doctorsHasMethod) throws PersistException {
        try {
        	statement.setInt(1, doctorsHasMethod.getMethod().getId());
        	statement.setInt(2, doctorsHasMethod.getDoctor().getId());
        	statement.setInt(3, doctorsHasMethod.getUserCreated().getId());
            statement.setDate(4, new java.sql.Date( doctorsHasMethod.getDateCreated().getTime()));
            statement.setBoolean(5, doctorsHasMethod.getDeleted());
            statement.setInt(6, doctorsHasMethod.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}
	
	
	public Collection<Users> findByMethod(Methods method) throws PersistException{
		ArrayList<DoctorsHasMethod> doctorsHasMethodList = new ArrayList<DoctorsHasMethod>(); 		
		doctorsHasMethodList.add( findByNeedle("Method", method.getId().toString()));
		
		ArrayList<Users> doctorsList = new ArrayList<Users>();
		for (DoctorsHasMethod entry: doctorsHasMethodList){
			doctorsList.add(entry.getDoctor());
		}	
		return doctorsList;
	}
	
	public Collection<Methods> findByDoctor(Users doctor) throws PersistException{
		ArrayList<DoctorsHasMethod> doctorsHasMethodList = new ArrayList<DoctorsHasMethod>(); 
		doctorsHasMethodList.add( findByNeedle("Doctor", doctor.getId().toString()));

		ArrayList<Methods> methodList = new ArrayList<Methods>();
		for (DoctorsHasMethod entry: doctorsHasMethodList){
			methodList.add(entry.getMethod());
		}	
		return methodList;
	}
}
