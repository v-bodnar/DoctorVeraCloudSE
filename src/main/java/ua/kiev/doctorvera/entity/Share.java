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

/**
 *
 * @author Bodun
 */
@Entity
@Table(name = "Share")
@NamedQueries({
    @NamedQuery(name = "Share.findAll", query = "SELECT s FROM Share s"),
    @NamedQuery(name = "Share.findBySalaryId", query = "SELECT s FROM Share s WHERE s.salaryId = :salaryId"),
    @NamedQuery(name = "Share.findBySalaryDoctor", query = "SELECT s FROM Share s WHERE s.salaryDoctor = :salaryDoctor"),
    @NamedQuery(name = "Share.findBySalaryAssistant", query = "SELECT s FROM Share s WHERE s.salaryAssistant = :salaryAssistant"),
    @NamedQuery(name = "Share.findByPersentageDoctor", query = "SELECT s FROM Share s WHERE s.persentageDoctor = :persentageDoctor"),
    @NamedQuery(name = "Share.findByPercentageAssistant", query = "SELECT s FROM Share s WHERE s.percentageAssistant = :percentageAssistant"),
    @NamedQuery(name = "Share.findByDataTime", query = "SELECT s FROM Share s WHERE s.dataTime = :dataTime"),
    @NamedQuery(name = "Share.findByDeleted", query = "SELECT s FROM Share s WHERE s.deleted = :deleted")})
public class Share implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SalaryId")
    private Integer salaryId;
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

    public Share(Integer salaryId) {
        this.salaryId = salaryId;
    }

    public Share(Integer salaryId, Date dataTime, boolean deleted) {
        this.salaryId = salaryId;
        this.dataTime = dataTime;
        this.deleted = deleted;
    }

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
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
        hash += (salaryId != null ? salaryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Share)) {
            return false;
        }
        Share other = (Share) object;
        if ((this.salaryId == null && other.salaryId != null) || (this.salaryId != null && !this.salaryId.equals(other.salaryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Share[ salaryId=" + salaryId + " ]";
    }
    
}
