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
@Table(name = "DoctorsHasMethod")
@NamedQueries({
    @NamedQuery(name = "DoctorsHasMethod.findAll", query = "SELECT d FROM DoctorsHasMethod d"),
    @NamedQuery(name = "DoctorsHasMethod.findByMethodId", query = "SELECT d FROM DoctorsHasMethod d WHERE d.methodId = :methodId"),
    @NamedQuery(name = "DoctorsHasMethod.findByDeleted", query = "SELECT d FROM DoctorsHasMethod d WHERE d.deleted = :deleted")})
public class DoctorsHasMethod implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MethodId")
    private Integer methodId;
    @Basic(optional = false)
    @Column(name = "Deleted")
    private boolean deleted;
    @JoinColumn(name = "MethodId", referencedColumnName = "MethodId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Method method;
    @JoinColumn(name = "DoctorId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users doctorId;
    @JoinColumn(name = "CreatedUserId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users createdUserId;

    public DoctorsHasMethod() {
    }

    public DoctorsHasMethod(Integer methodId) {
        this.methodId = methodId;
    }

    public DoctorsHasMethod(Integer methodId, boolean deleted) {
        this.methodId = methodId;
        this.deleted = deleted;
    }

    public Integer getMethodId() {
        return methodId;
    }

    public void setMethodId(Integer methodId) {
        this.methodId = methodId;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Users getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Users doctorId) {
        this.doctorId = doctorId;
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
        hash += (methodId != null ? methodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoctorsHasMethod)) {
            return false;
        }
        DoctorsHasMethod other = (DoctorsHasMethod) object;
        if ((this.methodId == null && other.methodId != null) || (this.methodId != null && !this.methodId.equals(other.methodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.DoctorsHasMethod[ methodId=" + methodId + " ]";
    }
    
}
