package br.com.framework.service;

import br.com.framework.dao.DAOException;

public class ServiceException extends Exception {
	
	public ServiceException(String msn){
		super(msn);
	}

	public ServiceException(DAOException e) {
		super(e);
	}

}
