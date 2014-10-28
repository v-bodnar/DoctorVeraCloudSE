package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import ua.kiev.doctorvera.dao.AbstractJDBCDao;
import ua.kiev.doctorvera.dao.Identified;
import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.Users;

public abstract class AbstractMySql<T extends Identified<PK>, PK extends Integer> extends AbstractJDBCDao<T, PK> {
	
	Connection connection;
	private final String SCHEMA = "DrVera";
	
	public AbstractMySql(Connection connection) {
		super(connection);
		this.connection = connection;
	}
	
	public Collection<T> findByUserCreated(Users userCreated) throws PersistException{	
		ArrayList<T> entityList = new ArrayList<T>(); 
		entityList.add( findByNeedle("UserCreated", userCreated.getId().toString()));
		return entityList;
	}
	
	public Collection<T> findDateCreatedBetween(Date min, Date max) throws PersistException{
		List<T> entitysList = findAll();
		ArrayList<T> result = new ArrayList<T>();
		for (T entity : entitysList){
			if(min.before(entity.getDateCreated()) && entity.getDateCreated().before(max)) result.add(entity);
		}
		return result;
	}
	
	public T find(T entity) throws PersistException{
			return findByPK(entity.getId());
	}
	
	@Override
    protected String getSelectQuery(){
    	return "SELECT * FROM " + getTableName() + " WHERE Deleted = 0;";
    }

	@Override
    protected String getSelectByPKQuery(){
    	return "SELECT * FROM " + getTableName() + " WHERE Deleted = 0 AND " + getPrimaryKeyName() + " = ? ;";
    }
    
	@Override
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
    
    @Override
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

    @Override
    protected String getDeleteQuery() {
		return "UPDATE " + getTableName() +" SET DELETED = 1 WHERE " + getPrimaryKeyName() + " = ?;";
    };
    
    @Override
    protected String getEntityQuery(String column, String needle){
    	return "SELECT * FROM " + getTableName() + " WHERE " + column + " = '"+ needle+"' AND Deleted = 0";
    };
    
    @Override
    protected String getPrimaryKeyName(){
		try {
			DatabaseMetaData meta = connection.getMetaData();
			ResultSet result = meta.getPrimaryKeys(null, SCHEMA, getTableName());
			result.next();
			return result.getString(4);
				
				 
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			return e.getLocalizedMessage();
		}
	}

}
