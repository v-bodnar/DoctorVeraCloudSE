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
@Table(name = "DoctorsHasMethod")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoctorsHasMethod.findAll", query = "SELECT d FROM DoctorsHasMethod d"),
    @NamedQuery(name = "DoctorsHasMethod.findByDoctorsHasMethodId", query = "SELECT d FROM DoctorsHasMethod d WHERE d.doctorsHasMethodId = :doctorsHasMethodId"),
    @NamedQuery(name = "DoctorsHasMethod.findByDateCreated", query = "SELECT d FROM DoctorsHasMethod d WHERE d.dateCreated = :dateCreated"),
    @NamedQuery(name = "DoctorsHasMethod.findByDeleted", query = "SELECT d FROM DoctorsHasMethod d WHERE d.deleted = :deleted")})
public class DoctorsHasMethod implements Serializable, Identified<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DoctorsHasMethodId")
    private Integer doctorsHasMethodId;
    @Basic(optional = false)
    @Column(name = "DateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "Deleted")
    private boolean deleted;
    @JoinColumn(name = "Doctor", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users doctor;
    @JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users userCreated;
    @JoinColumn(name = "Method", referencedColumnName = "MethodId")
    @ManyToOne(optional = false)
    private Methods method;

    public DoctorsHasMethod() {
    }

    public DoctorsHasMethod(Integer doctorsHasMethodId) {
        this.doctorsHasMethodId = doctorsHasMethodId;
    }

    public DoctorsHasMethod(Integer doctorsHasMethodId, Date dateCreated, boolean deleted) {
        this.doctorsHasMethodId = doctorsHasMethodId;
        this.dateCreated = dateCreated;
        this.deleted = deleted;
    }

    public Integer getDoctorsHasMethodId() {
        return doctorsHasMethodId;
    }

    public void setDoctorsHasMethodId(Integer doctorsHasMethodId) {
        this.doctorsHasMethodId = doctorsHasMethodId;
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

    public Users getDoctor() {
        return doctor;
    }

    public void setDoctor(Users doctor) {
        this.doctor = doctor;
    }

    public Users getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Users userCreated) {
        this.userCreated = userCreated;
    }

    public Methods getMethod() {
        return method;
    }

    public void setMethod(Methods method) {
        this.method = method;
    }

	@Override
	public Integer getId() {
		return getDoctorsHasMethodId();
	}

	@Override
	public void setId(Integer id) {
		setDoctorsHasMethodId(id);
	}
    
}
