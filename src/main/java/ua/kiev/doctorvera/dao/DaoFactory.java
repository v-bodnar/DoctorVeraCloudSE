package ua.kiev.doctorvera.dao;




/** Object fabric for working with DataBase */
public interface DaoFactory<Context> {
	  
	/** Возвращает объект для управления персистентным состоянием объекта */
    @SuppressWarnings("rawtypes")
	public GenericDao getDao(Class dtoClass) throws PersistException;
}
