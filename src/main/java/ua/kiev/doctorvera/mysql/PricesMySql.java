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
import ua.kiev.doctorvera.entity.Prices;
import ua.kiev.doctorvera.entity.Users;

public class PricesMySql extends AbstractMySql<Prices, Integer> {
	private Connection connection;
	private final String TABLE_NAME = "Prices";
	private UsersMySql usersDao = (UsersMySql)MySqlDaoFactory.getInstance().getDao(Users.class);
	private MethodsMySql methodDao = (MethodsMySql)MySqlDaoFactory.getInstance().getDao(Methods.class);
	public PricesMySql(Connection connection) {
		super(connection);
		this.connection = connection;
	}
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public Prices add() throws PersistException {
		Prices prices = new Prices();
		return persist(prices);
	}

	
	@Override
	protected List<Prices> parseResultSet(ResultSet rs) throws PersistException{
    LinkedList<Prices> result = new LinkedList<Prices>();
    try {
        while (rs.next()) {
        	Prices price = new Prices();
        	price.setId(rs.getInt("PriceId"));
        	price.setTotal(rs.getFloat("Total"));
        	price.setDateTime(rs.getDate("DateTime"));
        	price.setMethod(methodDao.findByPK(rs.getInt("Method")));
        	price.setUserCreated(usersDao.findByPK(rs.getInt("UserCreated")));
        	price.setDateCreated(rs.getDate("DateCreated"));
        	price.setDeleted(rs.getBoolean("Deleted"));
            result.add(price);
        }
    } catch (Exception e) {
        throw new PersistException(e);
    }
    return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Prices price) throws PersistException {
        try {
            statement.setFloat(1, price.getTotal());
            statement.setDate(2, new java.sql.Date( price.getDateTime().getTime()));
            statement.setInt(3, price.getMethod().getId());
            statement.setInt(4, price.getUserCreated().getId());
            statement.setDate(5, new java.sql.Date( price.getDateCreated().getTime()));
            statement.setBoolean(6, price.getDeleted());
        } catch (Exception e) {
            throw new PersistException(e);
        }
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Prices price) throws PersistException {
        try {
            statement.setFloat(1, price.getTotal());
            statement.setDate(2, new java.sql.Date( price.getDateTime().getTime()));
            statement.setInt(3, price.getMethod().getId());
            statement.setInt(4, price.getUserCreated().getId());
            statement.setDate(5, new java.sql.Date( price.getDateCreated().getTime()));
            statement.setBoolean(6, price.getDeleted());
            statement.setInt(7, price.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}
	
	public Collection<Prices> findByMethod(Methods method) throws PersistException{
		ArrayList<Prices> pricesList = new ArrayList<Prices>(); 
		pricesList.add( findByNeedle("Name", method.getId().toString()));
		return pricesList;
	}
	
	public Collection<Prices> findByTotalBetween(Float min, Float max) throws PersistException{
		List<Prices> pricesList = findAll();
		ArrayList<Prices> result = new ArrayList<Prices>();
		for (Prices price : pricesList){
			if(min <= price.getTotal() && price.getTotal() <= max) result.add(price);
		}
		return result;
	}
	
	public Collection<Prices> findDateTimeBetween(Date min, Date max) throws PersistException{
		List<Prices> entitysList = findAll();
		ArrayList<Prices> result = new ArrayList<Prices>();
		for (Prices entity : entitysList){
			if(min.before(entity.getDateCreated()) && entity.getDateCreated().before(max)) result.add(entity);
		}
		return result;
	}

}
