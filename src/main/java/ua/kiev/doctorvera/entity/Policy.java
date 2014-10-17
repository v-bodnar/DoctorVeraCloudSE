/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ua.kiev.doctorvera.dao.Identified;

/**
 *
 * @author Bodun
 */
@Entity
@Table(name = "Policy")
@NamedQueries({
    @NamedQuery(name = "Policy.findAll", query = "SELECT p FROM Policy p"),
    @NamedQuery(name = "Policy.findByPolicyId", query = "SELECT p FROM Policy p WHERE p.policyId = :policyId"),
    @NamedQuery(name = "Policy.findByName", query = "SELECT p FROM Policy p WHERE p.name = :name"),
    @NamedQuery(name = "Policy.findByDescription", query = "SELECT p FROM Policy p WHERE p.description = :description"),
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
    @Column(name = "Deleted")
    private boolean deleted;
    @JoinColumn(name = "CreatedUserId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users createdUserId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "policyId")
    private Collection<PolicyHasUserType> policyHasUserTypeCollection;

    public Policy() {
    }

    public Policy(Integer policyId) {
        this.policyId = policyId;
    }

    public Policy(Integer policyId, String name, boolean deleted) {
        this.policyId = policyId;
        this.name = name;
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

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Users getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Users createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Collection<PolicyHasUserType> getPolicyHasUserTypeCollection() {
        return policyHasUserTypeCollection;
    }

    public void setPolicyHasUserTypeCollection(Collection<PolicyHasUserType> policyHasUserTypeCollection) {
        this.policyHasUserTypeCollection = policyHasUserTypeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (policyId != null ? policyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Policy)) {
            return false;
        }
        Policy other = (Policy) object;
        if ((this.policyId == null && other.policyId != null) || (this.policyId != null && !this.policyId.equals(other.policyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Policy[ policyId=" + policyId + " ]";
    }

	@Override
	public Integer getId() {
		return getPolicyId();
	}

	@Override
	public void setId(Integer id) {
		setPolicyId(id);
	}
    
}
