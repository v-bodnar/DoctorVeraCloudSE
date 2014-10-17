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
@Table(name = "Prices")
@NamedQueries({
    @NamedQuery(name = "Price.findAll", query = "SELECT p FROM Price p"),
    @NamedQuery(name = "Price.findByPriceId", query = "SELECT p FROM Price p WHERE p.priceId = :priceId"),
    @NamedQuery(name = "Price.findByTotal", query = "SELECT p FROM Price p WHERE p.total = :total"),
    @NamedQuery(name = "Price.findByDateTime", query = "SELECT p FROM Price p WHERE p.dateTime = :dateTime"),
    @NamedQuery(name = "Price.findByDeleted", query = "SELECT p FROM Price p WHERE p.deleted = :deleted")})
public class Price implements Serializable, Identified<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PriceId")
    private Integer priceId;
    @Basic(optional = false)
    @Column(name = "Total")
    private float total;
    @Column(name = "DateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @Basic(optional = false)
    @Column(name = "Deleted")
    private boolean deleted;
    @JoinColumn(name = "MethodId", referencedColumnName = "MethodId")
    @ManyToOne(optional = false)
    private Method methodId;
    @JoinColumn(name = "CreatedUserId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users createdUserId;

    public Price() {
    }

    public Price(Integer priceId) {
        this.priceId = priceId;
    }

    public Price(Integer priceId, float total, boolean deleted) {
        this.priceId = priceId;
        this.total = total;
        this.deleted = deleted;
    }

    public Integer getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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

    public Users getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Users createdUserId) {
        this.createdUserId = createdUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (priceId != null ? priceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Price)) {
            return false;
        }
        Price other = (Price) object;
        if ((this.priceId == null && other.priceId != null) || (this.priceId != null && !this.priceId.equals(other.priceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Price[ priceId=" + priceId + " ]";
    }

	@Override
	public Integer getId() {
		return getPriceId();
	}

	@Override
	public void setId(Integer id) {
		setPriceId(id);
	}
    
}
