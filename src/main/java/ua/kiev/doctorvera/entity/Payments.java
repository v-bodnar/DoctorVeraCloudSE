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
@Table(name = "Payments")
@NamedQueries({
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p"),
    @NamedQuery(name = "Payment.findByPaymentId", query = "SELECT p FROM Payment p WHERE p.paymentId = :paymentId"),
    @NamedQuery(name = "Payment.findByDataTime", query = "SELECT p FROM Payment p WHERE p.dataTime = :dataTime"),
    @NamedQuery(name = "Payment.findByTotal", query = "SELECT p FROM Payment p WHERE p.total = :total"),
    @NamedQuery(name = "Payment.findByDescription", query = "SELECT p FROM Payment p WHERE p.description = :description"),
    @NamedQuery(name = "Payment.findByDeleted", query = "SELECT p FROM Payment p WHERE p.deleted = :deleted")})
public class Payments implements Serializable, Identified<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PaymentId")
    private Integer paymentId;
    @Basic(optional = false)
    @Column(name = "DataTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataTime;
    @Basic(optional = false)
    @Column(name = "Total")
    private float total;
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "Deleted")
    private boolean deleted;
    @JoinColumn(name = "PaymasterId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users paymasterId;
    @JoinColumn(name = "ScheduleId", referencedColumnName = "ScheduleId")
    @ManyToOne
    private Schedule scheduleId;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    private Users userId;

    public Payments() {
    }

    public Payments(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Payments(Integer paymentId, Date dataTime, float total, boolean deleted) {
        this.paymentId = paymentId;
        this.dataTime = dataTime;
        this.total = total;
        this.deleted = deleted;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
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

    public Users getPaymasterId() {
        return paymasterId;
    }

    public void setPaymasterId(Users paymasterId) {
        this.paymasterId = paymasterId;
    }

    public Schedule getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Schedule scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentId != null ? paymentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payments)) {
            return false;
        }
        Payments other = (Payments) object;
        if ((this.paymentId == null && other.paymentId != null) || (this.paymentId != null && !this.paymentId.equals(other.paymentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Payment[ paymentId=" + paymentId + " ]";
    }

	@Override
	public Integer getId() {
		return getPaymentId();
	}

	@Override
	public void setId(Integer id) {
		setPaymentId(id);
	}
    
}
