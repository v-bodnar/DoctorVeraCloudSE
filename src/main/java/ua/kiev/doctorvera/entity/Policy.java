/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.entity;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "Policy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Policy.findAll", query = "SELECT p FROM Policy p"),
    @NamedQuery(name = "Policy.findByPolicyId", query = "SELECT p FROM Policy p WHERE p.policyId = :policyId"),
    @NamedQuery(name = "Policy.findByName", query = "SELECT p FROM Policy p WHERE p.name = :name"),
    @NamedQuery(name = "Policy.findByDescription", query = "SELECT p FROM Policy p WHERE p.description = :description"),
    @NamedQuery(name = "Policy.findByDateCreated", query = "SELECT p FROM Policy p WHERE p.dateCreated = :dateCreated"),
    @NamedQuery(name = "Policy.findByDeleted", query = "SELECT p FROM Policy p WHERE p.deleted = :deleted")})
public class Policy implements Serializable, Identified<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PolicyId")
    private Integer policyId;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "DateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "Deleted")
    private boolean deleted;
    @JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users userCreated;
    /*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "policy")
    private Collection<PolicyHasUserTypes> policyHasUserTypesCollection;
     */
    private Collection<UserTypes> userTypesCollection;
    
    public Policy() {
    }

    public Policy(Integer policyId) {
        this.policyId = policyId;
    }

    public Policy(Integer policyId, String name, Date dateCreated, boolean deleted) {
        this.policyId = policyId;
        this.name = name;
        this.dateCreated = dateCreated;
        this.deleted = deleted;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Users getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Users userCreated) {
        this.userCreated = userCreated;
    }
    /*
    @XmlTransient
    public Collection<PolicyHasUserTypes> getPolicyHasUserTypesCollection() {
        return policyHasUserTypesCollection;
    }

    public void setPolicyHasUserTypesCollection(Collection<PolicyHasUserTypes> policyHasUserTypesCollection) {
        this.policyHasUserTypesCollection = policyHasUserTypesCollection;
    }
    */
	@Override
	public Integer getId() {
		return getPolicyId();
	}

	@Override
	public void setId(Integer id) {
		setPolicyId(id);
	}

	public Collection<UserTypes> getUserTypesCollection() {
		return userTypesCollection;
	}

	public void setUserTypesCollection(Collection<UserTypes> userTypesCollection) {
		this.userTypesCollection = userTypesCollection;
	}
	

    
}
