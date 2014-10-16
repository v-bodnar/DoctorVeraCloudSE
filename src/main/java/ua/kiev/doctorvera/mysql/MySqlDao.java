package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.kiev.doctorvera.dao.*;

public abstract class MySqlDao<T extends Identified<PK>, PK extends Integer> extends AbstractJDBCDao<Identified<Integer>, Integer> {
	
	Connection connection;
	private final String SCHEMA = "DrVera";
	
    public MySqlDao(Connection connection) {
		super(connection);
		this.connection = connection;
	}
    
    /**
    * Возвращает название таблицы соответствующей сущности.
    */
    public abstract String getTableName();
    
    /**
     * Возвращает sql запрос для получения всех записей.
     * <p/>
     * SELECT * FROM [Table] WHERE Delete = 0;
     */
    public String getSelectQuery(){
    	return "SELECT * FROM " + getTableName() + " WHERE Deleted = 0;";
    }
    /**
     * Возвращает sql запрос для получения всех записей.
     * <p/>
     * SELECT * FROM [Table] WHERE Delete = 0 AND PK=?;
     */
    public String getSelectByPKQuery(){
    	return "SELECT * FROM " + getTableName() + " WHERE Deleted = 0 AND " + getPrimaryKeyName() + " = ? ;";
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
    protected String getPrimaryKeyName(){
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
    
	
}
