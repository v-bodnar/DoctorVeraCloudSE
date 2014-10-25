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
@Table(name = "MethodTypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MethodTypes.findAll", query = "SELECT m FROM MethodTypes m"),
    @NamedQuery(name = "MethodTypes.findByMethodTypeId", query = "SELECT m FROM MethodTypes m WHERE m.methodTypeId = :methodTypeId"),
    @NamedQuery(name = "MethodTypes.findByShortName", query = "SELECT m FROM MethodTypes m WHERE m.shortName = :shortName"),
    @NamedQuery(name = "MethodTypes.findByFullName", query = "SELECT m FROM MethodTypes m WHERE m.fullName = :fullName"),
    @NamedQuery(name = "MethodTypes.findByDateCreated", query = "SELECT m FROM MethodTypes m WHERE m.dateCreated = :dateCreated"),
    @NamedQuery(name = "MethodTypes.findByDeleted", query = "SELECT m FROM MethodTypes m WHERE m.deleted = :deleted")})
public class MethodTypes implements Serializable, Identified<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MethodTypeId")
    private Integer methodTypeId;
    @Basic(optional = false)
    @Column(name = "ShortName")
    private String shortName;
    @Basic(optional = false)
    @Column(name = "FullName")
    private String fullName;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "methodType")
    private Collection<Methods> methodsCollection;
*/
    public MethodTypes() {
    }

    public MethodTypes(Integer methodTypeId) {
        this.methodTypeId = methodTypeId;
    }

    public MethodTypes(Integer methodTypeId, String shortName, String fullName, Date dateCreated, boolean deleted) {
        this.methodTypeId = methodTypeId;
        this.shortName = shortName;
        this.fullName = fullName;
        this.dateCreated = dateCreated;
        this.deleted = deleted;
    }

    public Integer getMethodTypeId() {
        return methodTypeId;
    }

    public void setMethodTypeId(Integer methodTypeId) {
        this.methodTypeId = methodTypeId;
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
    public Collection<Methods> getMethodsCollection() {
        return methodsCollection;
    }

    public void setMethodsCollection(Collection<Methods> methodsCollection) {
        this.methodsCollection = methodsCollection;
    }

   */
	@Override
	public Integer getId() {
		return getMethodTypeId();
	}

	@Override
	public void setId(Integer id) {
		setMethodTypeId(id);
	}
    
}
