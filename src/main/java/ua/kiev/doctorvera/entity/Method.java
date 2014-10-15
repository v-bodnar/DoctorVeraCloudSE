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

/**
 *
 * @author Bodun
 */
@Entity
@Table(name = "Methods")
@NamedQueries({
    @NamedQuery(name = "Method.findAll", query = "SELECT m FROM Method m"),
    @NamedQuery(name = "Method.findByMethodId", query = "SELECT m FROM Method m WHERE m.methodId = :methodId"),
    @NamedQuery(name = "Method.findByShortName", query = "SELECT m FROM Method m WHERE m.shortName = :shortName"),
    @NamedQuery(name = "Method.findByFullName", query = "SELECT m FROM Method m WHERE m.fullName = :fullName"),
    @NamedQuery(name = "Method.findByShortDescription", query = "SELECT m FROM Method m WHERE m.shortDescription = :shortDescription"),
    @NamedQuery(name = "Method.findByFullDescription", query = "SELECT m FROM Method m WHERE m.fullDescription = :fullDescription"),
    @NamedQuery(name = "Method.findByTimeInMinutes", query = "SELECT m FROM Method m WHERE m.timeInMinutes = :timeInMinutes"),
    @NamedQuery(name = "Method.findByDeleted", query = "SELECT m FROM Method m WHERE m.deleted = :deleted")})
public class Method implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MethodId")
    private Integer methodId;
    @Basic(optional = false)
    @Column(name = "ShortName")
    private String shortName;
    @Basic(optional = false)
    @Column(name = "FullName")
    private String fullName;
    @Column(name = "ShortDescription")
    private String shortDescription;
    @Column(name = "FullDescription")
    private String fullDescription;
    @Basic(optional = false)
    @Column(name = "TimeInMinutes")
    private int timeInMinutes;
    @Basic(optional = false)
    @Column(name = "Deleted")
    private boolean deleted;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "method")
    private DoctorsHasMethod doctorsHasMethod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "methodId")
    private Collection<Price> priceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "methodId")
    private Collection<Schedule> scheduleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "methodId")
    private Collection<Share> shareCollection;
    @JoinColumn(name = "CreatedUserId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users createdUserId;

    public Method() {
    }

    public Method(Integer methodId) {
        this.methodId = methodId;
    }

    public Method(Integer methodId, String shortName, String fullName, int timeInMinutes, boolean deleted) {
        this.methodId = methodId;
        this.shortName = shortName;
        this.fullName = fullName;
        this.timeInMinutes = timeInMinutes;
        this.deleted = deleted;
    }

    public Integer getMethodId() {
        return methodId;
    }

    public void setMethodId(Integer methodId) {
        this.methodId = methodId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public DoctorsHasMethod getDoctorsHasMethod() {
        return doctorsHasMethod;
    }

    public void setDoctorsHasMethod(DoctorsHasMethod doctorsHasMethod) {
        this.doctorsHasMethod = doctorsHasMethod;
    }

    public Collection<Price> getPriceCollection() {
        return priceCollection;
    }

    public void setPriceCollection(Collection<Price> priceCollection) {
        this.priceCollection = priceCollection;
    }

    public Collection<Schedule> getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(Collection<Schedule> scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    public Collection<Share> getShareCollection() {
        return shareCollection;
    }

    public void setShareCollection(Collection<Share> shareCollection) {
        this.shareCollection = shareCollection;
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
        if (!(object instanceof Method)) {
            return false;
        }
        Method other = (Method) object;
        if ((this.methodId == null && other.methodId != null) || (this.methodId != null && !this.methodId.equals(other.methodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Method[ methodId=" + methodId + " ]";
    }
    
}
