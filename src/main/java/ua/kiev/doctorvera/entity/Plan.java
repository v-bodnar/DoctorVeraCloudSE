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

import ua.kiev.doctorvera.dao.Identified;

/**
 *
 * @author Bodun
 */
@Entity
@Table(name = "Plan")
@NamedQueries({
    @NamedQuery(name = "Plan.findAll", query = "SELECT p FROM Plan p"),
    @NamedQuery(name = "Plan.findByPlanId", query = "SELECT p FROM Plan p WHERE p.planId = :planId"),
    @NamedQuery(name = "Plan.findByDateTimeStart", query = "SELECT p FROM Plan p WHERE p.dateTimeStart = :dateTimeStart"),
    @NamedQuery(name = "Plan.findByDateTimeEnd", query = "SELECT p FROM Plan p WHERE p.dateTimeEnd = :dateTimeEnd"),
    @NamedQuery(name = "Plan.findByDescription", query = "SELECT p FROM Plan p WHERE p.description = :description"),
    @NamedQuery(name = "Plan.findByDeleted", query = "SELECT p FROM Plan p WHERE p.deleted = :deleted")})
public class Plan implements Serializable, Identified<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PlanId")
    private Integer planId;
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
    @JoinColumn(name = "RoomId", referencedColumnName = "RoomId")
    @ManyToOne(optional = false)
    private Rooms roomId;
    @JoinColumn(name = "DoctorId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users doctorId;
    @JoinColumn(name = "UserCreatedId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users userCreatedId;

    public Plan() {
    }

    public Plan(Integer planId) {
        this.planId = planId;
    }

    public Plan(Integer planId, Date dateTimeStart, Date dateTimeEnd, boolean deleted) {
        this.planId = planId;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.deleted = deleted;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
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

    public Rooms getRoomId() {
        return roomId;
    }

    public void setRoomId(Rooms roomId) {
        this.roomId = roomId;
    }

    public Users getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Users doctorId) {
        this.doctorId = doctorId;
    }

    public Users getUserCreatedId() {
        return userCreatedId;
    }

    public void setUserCreatedId(Users userCreatedId) {
        this.userCreatedId = userCreatedId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planId != null ? planId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plan)) {
            return false;
        }
        Plan other = (Plan) object;
        if ((this.planId == null && other.planId != null) || (this.planId != null && !this.planId.equals(other.planId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Plan[ planId=" + planId + " ]";
    }

	@Override
	public Integer getId() {
		return getPlanId();
	}

	@Override
	public void setId(Integer id) {
		setPlanId(id);
	}
    
}
