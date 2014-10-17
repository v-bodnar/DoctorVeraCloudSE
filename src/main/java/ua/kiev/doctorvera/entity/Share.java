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
@Table(name = "Share")
@NamedQueries({
    @NamedQuery(name = "Share.findAll", query = "SELECT s FROM Share s"),
    @NamedQuery(name = "Share.findByshareId", query = "SELECT s FROM Share s WHERE s.shareId = :shareId"),
    @NamedQuery(name = "Share.findBySalaryDoctor", query = "SELECT s FROM Share s WHERE s.salaryDoctor = :salaryDoctor"),
    @NamedQuery(name = "Share.findBySalaryAssistant", query = "SELECT s FROM Share s WHERE s.salaryAssistant = :salaryAssistant"),
    @NamedQuery(name = "Share.findByPersentageDoctor", query = "SELECT s FROM Share s WHERE s.persentageDoctor = :persentageDoctor"),
    @NamedQuery(name = "Share.findByPercentageAssistant", query = "SELECT s FROM Share s WHERE s.percentageAssistant = :percentageAssistant"),
    @NamedQuery(name = "Share.findByDataTime", query = "SELECT s FROM Share s WHERE s.dataTime = :dataTime"),
    @NamedQuery(name = "Share.findByDeleted", query = "SELECT s FROM Share s WHERE s.deleted = :deleted")})
public class Share implements Serializable, Identified<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ShareId")
    private Integer shareId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SalaryDoctor")
    private Float salaryDoctor;
    @Column(name = "SalaryAssistant")
    private Float salaryAssistant;
    @Column(name = "PersentageDoctor")
    private Float persentageDoctor;
    @Column(name = "PercentageAssistant")
    private Float percentageAssistant;
    @Basic(optional = false)
    @Column(name = "DataTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataTime;
    @Basic(optional = false)
    @Column(name = "Deleted")
    private boolean deleted;
    @JoinColumn(name = "MethodId", referencedColumnName = "MethodId")
    @ManyToOne(optional = false)
    private Method methodId;
    @JoinColumn(name = "DoctorId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users doctorId;
    @JoinColumn(name = "AssistantId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users assistantId;
    @JoinColumn(name = "CreatedUserId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users createdUserId;

    public Share() {
    }

    public Share(Integer shareId) {
        this.shareId = shareId;
    }

    public Share(Integer shareId, Date dataTime, boolean deleted) {
        this.shareId = shareId;
        this.dataTime = dataTime;
        this.deleted = deleted;
    }

    public Integer getShareId() {
        return shareId;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    public Float getSalaryDoctor() {
        return salaryDoctor;
    }

    public void setSalaryDoctor(Float salaryDoctor) {
        this.salaryDoctor = salaryDoctor;
    }

    public Float getSalaryAssistant() {
        return salaryAssistant;
    }

    public void setSalaryAssistant(Float salaryAssistant) {
        this.salaryAssistant = salaryAssistant;
    }

    public Float getPersentageDoctor() {
        return persentageDoctor;
    }

    public void setPersentageDoctor(Float persentageDoctor) {
        this.persentageDoctor = persentageDoctor;
    }

    public Float getPercentageAssistant() {
        return percentageAssistant;
    }

    public void setPercentageAssistant(Float percentageAssistant) {
        this.percentageAssistant = percentageAssistant;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Method getMethodId() {
        return methodId;
    }

    public void setMethodId(Method methodId) {
        this.methodId = methodId;
    }

    public Users getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Users doctorId) {
        this.doctorId = doctorId;
    }

    public Users getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(Users assistantId) {
        this.assistantId = assistantId;
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
        hash += (shareId != null ? shareId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Share)) {
            return false;
        }
        Share other = (Share) object;
        if ((this.shareId == null && other.shareId != null) || (this.shareId != null && !this.shareId.equals(other.shareId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Share[ shareId=" + shareId + " ]";
    }

	@Override
	public Integer getId() {
		return getShareId();
	}

	@Override
	public void setId(Integer id) {
		setShareId(id);
	}
    
}
