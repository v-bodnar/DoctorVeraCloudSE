/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import ua.kiev.doctorvera.dao.Identified;

/**
 *
 * @author Vova
 */
@Entity
@Table(name = "PolicyHasUserTypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PolicyHasUserTypes.findAll", query = "SELECT p FROM PolicyHasUserTypes p"),
    @NamedQuery(name = "PolicyHasUserTypes.findByPolicyHasUserTypesId", query = "SELECT p FROM PolicyHasUserTypes p WHERE p.policyHasUserTypesId = :policyHasUserTypesId"),
    @NamedQuery(name = "PolicyHasUserTypes.findByDateCreated", query = "SELECT p FROM PolicyHasUserTypes p WHERE p.dateCreated = :dateCreated"),
    @NamedQuery(name = "PolicyHasUserTypes.findByDeleted", query = "SELECT p FROM PolicyHasUserTypes p WHERE p.deleted = :deleted")})
public class PolicyHasUserTypes implements Serializable, Identified<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PolicyHasUserTypesId")
    private Integer policyHasUserTypesId;
    @Basic(optional = false)
    @Column(name = "DateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "Deleted")
    private boolean deleted;
    @JoinColumn(name = "UserType", referencedColumnName = "UserTypeId")
    @ManyToOne(optional = false)
    private UserTypes userType;
    @JoinColumn(name = "Policy", referencedColumnName = "PolicyId")
    @ManyToOne(optional = false)
    private Policy policy;
    @JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users userCreated;

    public PolicyHasUserTypes() {
    }

    public PolicyHasUserTypes(Integer policyHasUserTypesId) {
        this.policyHasUserTypesId = policyHasUserTypesId;
    }

    public PolicyHasUserTypes(Integer policyHasUserTypesId, Date dateCreated, boolean deleted) {
        this.policyHasUserTypesId = policyHasUserTypesId;
        this.dateCreated = dateCreated;
        this.deleted = deleted;
    }

    public Integer getPolicyHasUserTypesId() {
        return policyHasUserTypesId;
    }

    public void setPolicyHasUserTypesId(Integer policyHasUserTypesId) {
        this.policyHasUserTypesId = policyHasUserTypesId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public UserTypes getUserType() {
        return userType;
    }

    public void setUserType(UserTypes userType) {
        this.userType = userType;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public Users getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Users userCreated) {
        this.userCreated = userCreated;
    }

	@Override
	public Integer getId() {
		return getPolicyHasUserTypesId();
	}

	@Override
	public void setId(Integer id) {
		setPolicyHasUserTypesId(id);
	}

    
}
