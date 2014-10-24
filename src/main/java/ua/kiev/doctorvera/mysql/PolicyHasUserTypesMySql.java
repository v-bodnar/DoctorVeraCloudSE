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
import ua.kiev.doctorvera.entity.UserTypes;
import ua.kiev.doctorvera.entity.Users;


public class PolicyHasUserTypesMySql extends AbstractMySql<PolicyHasUserTypes, Integer> {
	//private Connection connection;
	private final String TABLE_NAME = "PolicyHasUserTypes";


	public PolicyHasUserTypesMySql(Connection connection) {
		super(connection);
		//this.connection = connection;
	}
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public PolicyHasUserTypes add() throws PersistException {
		PolicyHasUserTypes policyHasUserTypes = new PolicyHasUserTypes();
		return persist(policyHasUserTypes);
	}

	
	@Override
	protected List<PolicyHasUserTypes> parseResultSet(ResultSet rs) throws PersistException{
    LinkedList<PolicyHasUserTypes> result = new LinkedList<PolicyHasUserTypes>();
	UsersMySql usersDao = (UsersMySql)MySqlDaoFactory.getInstance().getDao(Users.class);
	UserTypesMySql userTypeDao = (UserTypesMySql)MySqlDaoFactory.getInstance().getDao(UserTypes.class);
	PolicyMySql policyDao = (PolicyMySql)MySqlDaoFactory.getInstance().getDao(Policy.class);
	
    try {
        while (rs.next()) {	
        	PolicyHasUserTypes policyHasUserTypes = new PolicyHasUserTypes();
        	policyHasUserTypes.setId(rs.getInt("PolicyHasUserTypesId"));
        	policyHasUserTypes.setUserType(userTypeDao.findByPK(rs.getInt("UserType")));
        	policyHasUserTypes.setPolicy(policyDao.findByPK(rs.getInt("Policy")));
        	policyHasUserTypes.setUserCreated(usersDao.findByPK(rs.getInt("UserCreated")));
        	policyHasUserTypes.setDateCreated(rs.getDate("DateCreated"));
        	policyHasUserTypes.setDeleted(rs.getBoolean("Deleted"));
            result.add(policyHasUserTypes);
        }
    } catch (Exception e) {
        throw new PersistException(e);
    }
    return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			PolicyHasUserTypes policyHasUserTypes) throws PersistException {
        try {
        	statement.setInt(1, policyHasUserTypes.getPolicy().getId());
        	statement.setInt(2, policyHasUserTypes.getUserType().getId());
        	statement.setInt(3, policyHasUserTypes.getUserCreated().getId());
            statement.setDate(4, new java.sql.Date( policyHasUserTypes.getDateCreated().getTime()));
            statement.setBoolean(5, policyHasUserTypes.getDeleted());
        } catch (Exception e) {
            throw new PersistException(e);
        }
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			PolicyHasUserTypes policyHasUserTypes) throws PersistException {
        try {
        	statement.setInt(1, policyHasUserTypes.getPolicy().getId());
        	statement.setInt(2, policyHasUserTypes.getUserType().getId());
        	statement.setInt(3, policyHasUserTypes.getUserCreated().getId());
            statement.setDate(4, new java.sql.Date( policyHasUserTypes.getDateCreated().getTime()));
            statement.setBoolean(5, policyHasUserTypes.getDeleted());
            statement.setInt(6, policyHasUserTypes.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}
	
	
	public Collection<UserTypes> findByPolicy(Policy policy) throws PersistException{
		ArrayList<PolicyHasUserTypes> policyHasUserTypeList = new ArrayList<PolicyHasUserTypes>(); 		
		policyHasUserTypeList.add( findByNeedle("Policy", policy.getId().toString()));
		
		ArrayList<UserTypes> userTypesList = new ArrayList<UserTypes>();
		for (PolicyHasUserTypes entry: policyHasUserTypeList){
			userTypesList.add(entry.getUserType());
		}	
		return userTypesList;
	}
	
	public Collection<Policy> findByUserType(UserTypes userType){
		try{
			ArrayList<PolicyHasUserTypes> policyHasUserTypeList = new ArrayList<PolicyHasUserTypes>(); 
			policyHasUserTypeList.add( findByNeedle("UserType", userType.getId().toString()));
	
			ArrayList<Policy> policyList = new ArrayList<Policy>();
			for (PolicyHasUserTypes entry: policyHasUserTypeList){
				policyList.add(entry.getPolicy());
			}	
			return policyList;
		}catch(PersistException e){
			return null;
		}
	}
}
