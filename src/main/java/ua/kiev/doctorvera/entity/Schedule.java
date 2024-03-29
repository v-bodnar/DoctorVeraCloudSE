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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import ua.kiev.doctorvera.dao.Identified;

/**
 *
 * @author Vova
 */
@Entity
@Table(name = "Schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s"),
    @NamedQuery(name = "Schedule.findByScheduleId", query = "SELECT s FROM Schedule s WHERE s.scheduleId = :scheduleId"),
    @NamedQuery(name = "Schedule.findByDateTimeStart", query = "SELECT s FROM Schedule s WHERE s.dateTimeStart = :dateTimeStart"),
    @NamedQuery(name = "Schedule.findByDateTimeEnd", query = "SELECT s FROM Schedule s WHERE s.dateTimeEnd = :dateTimeEnd"),
    @NamedQuery(name = "Schedule.findByDescription", query = "SELECT s FROM Schedule s WHERE s.description = :description"),
    @NamedQuery(name = "Schedule.findByDateCreated", query = "SELECT s FROM Schedule s WHERE s.dateCreated = :dateCreated"),
    @NamedQuery(name = "Schedule.findByDeleted", query = "SELECT s FROM Schedule s WHERE s.deleted = :deleted")})
public class Schedule implements Serializable, Identified<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ScheduleId")
    private Integer scheduleId;
    @Basic(optional = false)
    @Column(name = "DateTimeStart")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimeStart;
    @Basic(optional = false)
    @Column(name = "DateTimeEnd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimeEnd;
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "DateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "Deleted")
    private boolean deleted;
    @OneToMany(mappedBy = "schedule")
    private Collection<Payments> paymentsCollection;
    @JoinColumn(name = "Method", referencedColumnName = "MethodId")
    @ManyToOne(optional = false)
    private Methods method;
    @JoinColumn(name = "Room", referencedColumnName = "RoomId")
    @ManyToOne(optional = false)
    private Rooms room;
    @JoinColumn(name = "Patient", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users patient;
    @JoinColumn(name = "Assistant", referencedColumnName = "UserId")
    @ManyToOne
    private Users assistant;
    @JoinColumn(name = "Doctor", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users doctor;
    @JoinColumn(name = "DoctorDirected", referencedColumnName = "UserId")
    @ManyToOne
    private Users doctorDirected;
    @JoinColumn(name = "UserCreated", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users userCreated;

    public Schedule() {
    }

    public Schedule(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Schedule(Integer scheduleId, Date dateTimeStart, Date dateTimeEnd, Date dateCreated, boolean deleted) {
        this.scheduleId = scheduleId;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.dateCreated = dateCreated;
        this.deleted = deleted;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(Date dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public Date getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(Date dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
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

    @XmlTransient
    public Collection<Payments> getPaymentsCollection() {
        return paymentsCollection;
    }

    public void setPaymentsCollection(Collection<Payments> paymentsCollection) {
        this.paymentsCollection = paymentsCollection;
    }

    public Methods getMethod() {
        return method;
    }

    public void setMethod(Methods method) {
        this.method = method;
    }

    public Rooms getRoom() {
        return room;
    }

    public void setRoom(Rooms room) {
        this.room = room;
    }

    public Users getPatient() {
        return patient;
    }

    public void setPatient(Users patient) {
        this.patient = patient;
    }

    public Users getAssistant() {
        return assistant;
    }

    public void setAssistant(Users assistant) {
        this.assistant = assistant;
    }

    public Users getDoctor() {
        return doctor;
    }

    public void setDoctor(Users doctor) {
        this.doctor = doctor;
    }

    public Users getDoctorDirected() {
        return doctorDirected;
    }

    public void setDoctorDirected(Users doctorDirected) {
        this.doctorDirected = doctorDirected;
    }

    public Users getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Users userCreated) {
        this.userCreated = userCreated;
    }

	@Override
	public Integer getId() {
		return getScheduleId();
	}

	@Override
	public void setId(Integer id) {
		setScheduleId(id);
	}

    
}
