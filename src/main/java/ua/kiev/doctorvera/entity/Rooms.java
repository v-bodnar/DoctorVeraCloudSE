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
import javax.persistence.Table;

/**
 *
 * @author Bodun
 */
@Entity
@Table(name = "Rooms")
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
    @NamedQuery(name = "Room.findByRoomId", query = "SELECT r FROM Room r WHERE r.roomId = :roomId"),
    @NamedQuery(name = "Room.findByName", query = "SELECT r FROM Room r WHERE r.name = :name"),
    @NamedQuery(name = "Room.findByDescription", query = "SELECT r FROM Room r WHERE r.description = :description"),
    @NamedQuery(name = "Room.findByDeleted", query = "SELECT r FROM Room r WHERE r.deleted = :deleted")})
public class Rooms implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RoomId")
    private Integer roomId;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "Deleted")
    private boolean deleted;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
    private Collection<Plan> planCollection;
    @JoinColumn(name = "CreatedUserId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users createdUserId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
    private Collection<Schedule> scheduleCollection;

    public Rooms() {
    }

    public Rooms(Integer roomId) {
        this.roomId = roomId;
    }

    public Rooms(Integer roomId, String name, boolean deleted) {
        this.roomId = roomId;
        this.name = name;
        this.deleted = deleted;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Collection<Plan> getPlanCollection() {
        return planCollection;
    }

    public void setPlanCollection(Collection<Plan> planCollection) {
        this.planCollection = planCollection;
    }

    public Users getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Users createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Collection<Schedule> getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(Collection<Schedule> scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomId != null ? roomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rooms)) {
            return false;
        }
        Rooms other = (Rooms) object;
        if ((this.roomId == null && other.roomId != null) || (this.roomId != null && !this.roomId.equals(other.roomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Room[ roomId=" + roomId + " ]";
    }
    
}
