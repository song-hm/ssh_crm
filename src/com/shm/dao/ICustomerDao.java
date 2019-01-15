package com.shm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.shm.domain.Customer;

/**
 * 客户的持久层接口
 * @author SHM
 *
 */
public interface ICustomerDao {


	/**
	 * 保存客户
	 * @param customer
	 */
	void saveCustomer(Customer customer);

	/**
	 * 查询的总记录条数
	 * @param dCriteria	查询的条件
	 * @return
	 */
	int findTotalRecords(DetachedCriteria dCriteria);
	/**
	 * 查询客户列表
	 * @param dCriteria	查询的条件
	 * @param firstResult	查询开始记录的索引
	 * @param maxResults	每次查询记录的条数
	 * @return
	 */
	List<Customer> findAllcustomer(DetachedCriteria dCriteria,int firstResult,int maxResults);

	/**
	 * 	删除客户
	 * @param custId
	 */
	void delete(Long custId);
	
	/**
	 * 	根据ID查询客户
	 * @param custId
	 * @return
	 */
	Customer findById(Long custId);

	/**
	 * 	更新客户
	 * @param customer
	 */
	void update(Customer customer);

	/**
	 * 使用投影查询，查询客户列表
	 * @return
	 */
	List<Customer> findAllcustomer();

}
