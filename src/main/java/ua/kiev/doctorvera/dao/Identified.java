package ua.kiev.doctorvera.dao;

import java.io.Serializable;
import java.util.Date;

import ua.kiev.doctorvera.entity.Users;

/**
 * Интерфейс идентифицируемых объектов.
 */
public interface Identified<PK extends Serializable> {

    /** Возвращает идентификатор объекта */
    public PK getId();
    public void setId(PK id);
    
    public Users getUserCreated();
    public void setUserCreated(Users userCreated);
    
    public Date getDateCreated();
    public void setDateCreated(Date datereated);
    	
    public boolean getDeleted();
    public void setDeleted(boolean deleted);
}
