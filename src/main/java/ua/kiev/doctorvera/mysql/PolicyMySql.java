package ua.kiev.doctorvera.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ua.kiev.doctorvera.dao.PersistException;
import ua.kiev.doctorvera.entity.Policy;
import ua.kiev.doctorvera.entity.PolicyHasUserTypes;
import ua.kiev.doctorvera.entity.Users;

public class PolicyMySql extends AbstractMySql<Policy, Integer> {
	private Connection connection;
	private final String TABLE_NAME = "Policy";

	private UsersMySql usersDao = (UsersMySql)new MySqlDaoFactory().getDao(connection, Users.class);
	private PolicyHasUserTypesMySql policyHasUserTypesDao = (PolicyHasUserTypesMySql)new MySqlDaoFactory().getDao(connection, PolicyHasUserTypes.class);
	
	public PolicyMySql(Connection connection) {
		super(connection);
		this.connection = connection;
	}
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public Policy add() throws PersistException {
		Policy policy = new Policy();
		return persist(policy);
	}

	
	@Override
	protected List<Policy> parseResultSet(ResultSet rs) throws PersistException{
    LinkedList<Policy> result = new LinkedList<Policy>();
    try {
        while (rs.next()) {
        	Policy policy = new Policy();
        	policy.setId(rs.getInt("PolicyId"));
        	policy.setName(rs.getString("Name"));
        	policy.setDescription(rs.getString("Name"));
        	policy.setUserTypesCollection(policyHasUserTypesDao.findByPolicy(policy));
        	policy.setUserCreated(usersDao.findByPK(rs.getInt("UserCreated")));
        	policy.setDateCreated(rs.getDate("DateCreated"));
        	policy.setDeleted(rs.getBoolean("Deleted"));
            result.add(policy);
        }
    } catch (Exception e) {
        throw new PersistException(e);
    }
    return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Policy policy) throws PersistException {
        try {
        	statement.setString(1, policy.getName());
        	statement.setString(2, policy.getDescription());
        	statement.setInt(3, policy.getUserCreated().getId());
            statement.setDate(4, new java.sql.Date( policy.getDateCreated().getTime()));
            statement.setBoolean(5, policy.getDeleted());
        } catch (Exception e) {
            throw new PersistException(e);
        }
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Policy policy) throws PersistException {
        try {
        	statement.setString(1, policy.getName());
        	statement.setString(2, policy.getDescription());
        	statement.setInt(3, policy.getUserCreated().getId());
            statement.setDate(4, new java.sql.Date( policy.getDateCreated().getTime()));
            statement.setBoolean(5, policy.getDeleted());
            statement.setInt(6, policy.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}
	

	public Collection<Policy> findByName(String name) throws PersistException{	
		ArrayList<Policy> policyList = new ArrayList<Policy>(); 
		policyList.add( findByNeedle("Name", name));
		return policyList;
	}
	
	public Collection<Policy> findByDescription(String description) throws PersistException{	
		ArrayList<Policy> policyList = new ArrayList<Policy>(); 
		policyList.add( findByNeedle("Description", "%" + description + "%"));
		return policyList;
	}
	
}
