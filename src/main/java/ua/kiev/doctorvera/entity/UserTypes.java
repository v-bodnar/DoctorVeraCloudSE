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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ua.kiev.doctorvera.dao.Identified;

/**
 *
 * @author Bodun
 */
@Entity
@Table(name = "UserTypes")
@NamedQueries({
    @NamedQuery(name = "UserType.findAll", query = "SELECT u FROM UserType u"),
    @NamedQuery(name = "UserType.findByUserTypeId", query = "SELECT u FROM UserType u WHERE u.userTypeId = :userTypeId"),
    @NamedQuery(name = "UserType.findByName", query = "SELECT u FROM UserType u WHERE u.name = :name"),
    @NamedQuery(name = "UserType.findByDescription", query = "SELECT u FROM UserType u WHERE u.description = :description"),
    @NamedQuery(name = "UserType.findByDeleted", query = "SELECT u FROM UserType u WHERE u.deleted = :deleted")})
public class UserTypes implements Serializable, Identified<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UserTypeId")
    private Integer userTypeId;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "Deleted")
    private boolean deleted;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTypeId")
    private Collection<Users> userCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userType")
    private PolicyHasUserType policyHasUserType;
    @JoinColumn(name = "CreatedUserId", referencedColumnName = "UserId")
    @ManyToOne
    private Users createdUserId;

    public UserTypes() {
    }

    public UserTypes(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public UserTypes(Integer userTypeId, String name, boolean deleted) {
        this.userTypeId = userTypeId;
        this.name = name;
        this.deleted = deleted;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
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

    public Collection<Users> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<Users> userCollection) {
        this.userCollection = userCollection;
    }

    public PolicyHasUserType getPolicyHasUserType() {
        return policyHasUserType;
    }

    public void setPolicyHasUserType(PolicyHasUserType policyHasUserType) {
        this.policyHasUserType = policyHasUserType;
    }

    public Users getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Users createdUserId) {
        this.createdUserId = createdUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userTypeId != null ? userTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTypes)) {
            return false;
        }
        UserTypes other = (UserTypes) object;
        if ((this.userTypeId == null && other.userTypeId != null) || (this.userTypeId != null && !this.userTypeId.equals(other.userTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.UserType[ userTypeId=" + userTypeId + "userTypeName = " + name + " ]";
    }

	@Override
	public Integer getId() {
		return getUserTypeId();
	}

	@Override
	public void setId(Integer id) {
		setUserTypeId(id);
	}
    
}
