package service;

import model.CustomerCredit;

public interface CustomerCreditService {
	boolean deleteByPrimaryKey(Long id) throws Exception;

	boolean insert(CustomerCredit record) throws Exception;

	boolean insertSelective(CustomerCredit record) throws Exception;

	CustomerCreditService selectByPrimaryKey(Long id) throws Exception;

    boolean updateByPrimaryKeySelective(CustomerCredit record) throws Exception;

    boolean updateByPrimaryKey(CustomerCredit record) throws Exception;
}
