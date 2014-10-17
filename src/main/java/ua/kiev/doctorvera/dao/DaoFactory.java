package ua.kiev.doctorvera.dao;

import java.sql.Connection;



/** Фабрика объектов для работы с базой данных */
public interface DaoFactory<Context> {
	  
    /** Возвращает подключение к базе данных */
    public Context getConnection() throws PersistException;

    /** Возвращает объект для управления персистентным состоянием объекта */
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException;
}
