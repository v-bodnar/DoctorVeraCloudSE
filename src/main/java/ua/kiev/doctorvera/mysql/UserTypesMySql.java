package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ua.kiev.doctorvera.dao.AbstractJDBCDao;
import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.UserTypes;

public class UserTypesMySql extends AbstractJDBCDao<UserTypes, Integer>{
	
	Connection connection;
	private final String TABLE_NAME = "UserTypes";
	private final String SCHEMA = "DrVera";

	
	//@SuppressWarnings("unchecked")
	//private final GenericDao<Users, Integer> userDao = new MySqlDaoFactory().getDao(connection, Users.class);

    /**
     * Возвращает sql запрос для получения всех записей.
     * <p/>
     * SELECT * FROM [Table] WHERE Delete = 0;
     */
    public String getSelectQuery(){
    	return "SELECT * FROM " + getTableName() + " WHERE Delete = 0;";
    }

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
	public UserTypesMySql(Connection connection) {
		super(connection);
		this.connection = connection;
	}
	
	@Override
	public UserTypes create() throws PersistException {
		UserTypes userTypes = new UserTypes();
		return persist(userTypes);
	}

	
	@Override
	protected List<UserTypes> parseResultSet(ResultSet rs) throws PersistException{
    LinkedList<UserTypes> result = new LinkedList<UserTypes>();
    try {
        while (rs.next()) {
        	UserTypes userType = new UserTypes();
        	userType.setId(rs.getInt("UserTypeId"));
        	userType.setName(rs.getString("Name"));
        	userType.setDescription(rs.getString("Description"));
        	//userType.setCreatedUserId(userDao.getByPK(rs.getInt("CreatedUserId")));
        	userType.setDeleted(rs.getBoolean("Deleted"));
        	//userType.setUserCollection(userDao.getByGroup(userType));
            result.add(userType);
        }
    } catch (Exception e) {
        throw new PersistException(e);
    }
    return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			UserTypes userType) throws PersistException {
        try {
            statement.setString(1, userType.getName());
            statement.setString(2, userType.getDescription());
            statement.setInt(3, userType.getCreatedUserId().getId());
            statement.setBoolean(4, userType.getDeleted());
        } catch (Exception e) {
            throw new PersistException(e);
        }
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			UserTypes userType) throws PersistException {
        try {
            statement.setString(1, userType.getName());
            statement.setString(2, userType.getDescription());
            statement.setInt(3, userType.getCreatedUserId().getId());
            statement.setBoolean(4, userType.getDeleted());
            statement.setInt(5, userType.getUserTypeId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}



	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	



}
