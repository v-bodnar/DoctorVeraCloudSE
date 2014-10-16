package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ua.kiev.doctorvera.dao.AbstractJDBCDao;
import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.Users;

public class UsersMySql extends AbstractJDBCDao<Users, Integer>{
	
	Connection connection;
	private final String TABLE_NAME = "Users";
	private final String SCHEMA = "DrVera";

	//@SuppressWarnings("unchecked")
	//private final GenericDao<Users, Integer> userDao = new MySqlDaoFactory().getDao(Users.class);
	
	public UsersMySql(Connection connection) {
		super(connection);
		this.connection = connection;
	}
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
    public String getSelectByPKQuery(){
    	return "SELECT UserTypeId, Name FROM " + getTableName() + " WHERE 'Delete' = '0' AND " + getPrimaryKeyName() + " = ? ;";
    }
	 /**
     * Возвращает sql запрос для получения всех записей.
     * <p/>
     * SELECT * FROM [Table] WHERE Delete = 0;
     */
    public String getSelectQuery(){
    	return "SELECT * FROM " + getTableName() + " WHERE Delete = 0;";
    }

    /**
     * Возвращает sql запрос для вставки новой записи в базу данных.
     * <p/>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    protected String getCreateQuery() {
		String query="INSERT INTO " + getTableName() +" (" ;
		try {
			DatabaseMetaData meta = connection.getMetaData();
			ResultSet rs = meta.getColumns(null,SCHEMA,getTableName(),null);
			String columnName;
			int i=0;
			while (rs.next()){
				columnName = rs.getString("COLUMN_NAME");
				if(getPrimaryKeyName().equals(columnName)) continue;
				query += columnName + ",";
				i++;
			}
			query = query.substring(0,(query.length()-1)) + ") VALUES (";
			while(i!=0){
				query += "?,";
				i--;
			}
			query = query.substring(0,(query.length()-1)) + ");";
			return query;
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			return e.getLocalizedMessage();
		}
    };
    /**
     * Возвращает sql запрос для обновления записи.
     * <p/>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    protected String getUpdateQuery() {
		String query="UPDATE " + getTableName() +" SET " ;
		try {
			DatabaseMetaData meta = connection.getMetaData();
			ResultSet rs = meta.getColumns(null,SCHEMA,getTableName(),null);
			String columnName;
			while (rs.next()){
				columnName = rs.getString("COLUMN_NAME");
				if(getPrimaryKeyName().equals(columnName)) continue;
				query += columnName + " = ?, ";
			}
			query = query.substring(0,(query.length()-2)) + "WHERE " + getPrimaryKeyName() + " = ?;";

			return query;
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			return e.getLocalizedMessage();
		}
    };

    /**
     * Возвращает sql запрос для удаления записи из базы данных.
     * <p/>
	 * "UPDATE " + TABLE_NAME + " SET Deleted = 1 WHERE Id = ?;";
     */
    protected String getDeleteQuery() {
		return "UPDATE " + getTableName() +" SET DELETED = 1 WHERE " + getPrimaryKeyName() + " = ?;";
    };
    
    /**
     * Возвращает PrimaryKey таблицы соответствующей сущности
     */
	private String getPrimaryKeyName(){
		try {
			DatabaseMetaData meta = connection.getMetaData();
			ResultSet result = meta.getPrimaryKeys(null, SCHEMA, "UserTypes");
			result.next();
			return result.getString(4);
				
				 
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			return e.getLocalizedMessage();
		}
	}
	
	@Override
	public Users create() throws PersistException {
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
	
	public Collection<Users> getByGroup(Users user){
		
		return null;
	}

}
