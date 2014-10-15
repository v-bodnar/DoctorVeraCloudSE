/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Bodun
 */
@Entity
@Table(name = "PolicyHasUserTypes")
@NamedQueries({
    @NamedQuery(name = "PolicyHasUserType.findAll", query = "SELECT p FROM PolicyHasUserType p"),
    @NamedQuery(name = "PolicyHasUserType.findByUserTypeId", query = "SELECT p FROM PolicyHasUserType p WHERE p.userTypeId = :userTypeId"),
    @NamedQuery(name = "PolicyHasUserType.findByDeleted", query = "SELECT p FROM PolicyHasUserType p WHERE p.deleted = :deleted")})
public class PolicyHasUserType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UserTypeId")
    private Integer userTypeId;
    @Basic(optional = false)
    @Column(name = "Deleted")
    private boolean deleted;
    @JoinColumn(name = "UserTypeId", referencedColumnName = "UserTypeId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private UserTypes userType;
    @JoinColumn(name = "PolicyId", referencedColumnName = "PolicyId")
    @ManyToOne(optional = false)
    private Policy policyId;
    @JoinColumn(name = "CreatedUserId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users createdUserId;

    public PolicyHasUserType() {
    }

    public PolicyHasUserType(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public PolicyHasUserType(Integer userTypeId, boolean deleted) {
        this.userTypeId = userTypeId;
        this.deleted = deleted;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
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

    public Policy getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Policy policyId) {
        this.policyId = policyId;
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
        if (!(object instanceof PolicyHasUserType)) {
            return false;
        }
        PolicyHasUserType other = (PolicyHasUserType) object;
        if ((this.userTypeId == null && other.userTypeId != null) || (this.userTypeId != null && !this.userTypeId.equals(other.userTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.PolicyHasUserType[ userTypeId=" + userTypeId + " ]";
    }
    
}
