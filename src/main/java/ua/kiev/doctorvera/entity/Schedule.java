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

import ua.kiev.doctorvera.dao.Identified;

/**
 *
 * @author Bodun
 */
@Entity
@Table(name = "Schedule")
@NamedQueries({
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s"),
    @NamedQuery(name = "Schedule.findByScheduleId", query = "SELECT s FROM Schedule s WHERE s.scheduleId = :scheduleId"),
    @NamedQuery(name = "Schedule.findByDateTimeStart", query = "SELECT s FROM Schedule s WHERE s.dateTimeStart = :dateTimeStart"),
    @NamedQuery(name = "Schedule.findByDateTimeEnd", query = "SELECT s FROM Schedule s WHERE s.dateTimeEnd = :dateTimeEnd"),
    @NamedQuery(name = "Schedule.findByDescription", query = "SELECT s FROM Schedule s WHERE s.description = :description"),
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
    @Column(name = "Deleted")
    private boolean deleted;
    @OneToMany(mappedBy = "scheduleId")
    private Collection<Payments> paymentCollection;
    @JoinColumn(name = "PatientId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users patientId;
    @JoinColumn(name = "RoomId", referencedColumnName = "RoomId")
    @ManyToOne(optional = false)
    private Rooms roomId;
    @JoinColumn(name = "MethodId", referencedColumnName = "MethodId")
    @ManyToOne(optional = false)
    private Method methodId;
    @JoinColumn(name = "AssistantId", referencedColumnName = "UserId")
    @ManyToOne
    private Users assistantId;
    @JoinColumn(name = "DoctorId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users doctorId;
    @JoinColumn(name = "DoctorDirectedId", referencedColumnName = "UserId")
    @ManyToOne
    private Users doctorDirectedId;
    @JoinColumn(name = "CreatedUserId", referencedColumnName = "UserId")
    @ManyToOne
    private Users createdUserId;

    public Schedule() {
    }

    public Schedule(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Schedule(Integer scheduleId, Date dateTimeStart, Date dateTimeEnd, boolean deleted) {
        this.scheduleId = scheduleId;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
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

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Collection<Payments> getPaymentCollection() {
        return paymentCollection;
    }

    public void setPaymentCollection(Collection<Payments> paymentCollection) {
        this.paymentCollection = paymentCollection;
    }

    public Users getPatientId() {
        return patientId;
    }

    public void setPatientId(Users patientId) {
        this.patientId = patientId;
    }

    public Rooms getRoomId() {
        return roomId;
    }

    public void setRoomId(Rooms roomId) {
        this.roomId = roomId;
    }

    public Method getMethodId() {
        return methodId;
    }

    public void setMethodId(Method methodId) {
        this.methodId = methodId;
    }

    public Users getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(Users assistantId) {
        this.assistantId = assistantId;
    }

    public Users getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Users doctorId) {
        this.doctorId = doctorId;
    }

    public Users getDoctorDirectedId() {
        return doctorDirectedId;
    }

    public void setDoctorDirectedId(Users doctorDirectedId) {
        this.doctorDirectedId = doctorDirectedId;
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
        hash += (scheduleId != null ? scheduleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        if ((this.scheduleId == null && other.scheduleId != null) || (this.scheduleId != null && !this.scheduleId.equals(other.scheduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Schedule[ scheduleId=" + scheduleId + " ]";
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
