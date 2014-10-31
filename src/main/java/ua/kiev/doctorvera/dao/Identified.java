package ua.kiev.doctorvera.dao;

import java.io.Serializable;
import java.util.Date;

import ua.kiev.doctorvera.entity.Users;

/**
 * Identified object interface
 */
public interface Identified<PK extends Serializable> {

    /** Returns unique object identifier */
    public PK getId();
    /** Sets unique object identifier */
    public void setId(PK id);
    
    /** Returns User created object */
    public Users getUserCreated();
    /** Sets User created object */
    public void setUserCreated(Users userCreated);
    
    /** Returns Date when created object */
    public Date getDateCreated();
    /** Sets Date when created object */
    public void setDateCreated(Date datereated);
    
    /** Returns if object is deleted */	
    public boolean getDeleted();
    /** Sets if object is deleted */
    public void setDeleted(boolean deleted);
}
