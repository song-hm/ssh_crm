package com.shm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shm.dao.IBaseDictDao;
import com.shm.dao.ICustomerDao;
import com.shm.domain.BaseDict;
import com.shm.domain.Customer;
import com.shm.service.ICustomerService;
import com.shm.web.commons.Page;

/**
 * 客户的业务层实现类
 * @author SHM
 *
 */
@Service("customerService")
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class CustomerServiceImpl implements ICustomerService {

	@Resource(name="customerDao")
	private ICustomerDao customerDao;
	
	@Resource(name="baseDictDao")
	private IBaseDictDao BaseDictDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void saveCustomer(Customer customer) {
		customerDao.saveCustomer(customer);

	}

	@Override
	public List<BaseDict> findAllCustomerSource() {
		
		return BaseDictDao.findBaseDictByTypeCode("002");
	}

	@Override
	public List<BaseDict> findAllCustomerLevel() {
		
		return BaseDictDao.findBaseDictByTypeCode("006");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void removeCustomer(Long custId) {
		customerDao.delete(custId);		
	}

	@Override
	public Customer findCustomerById(Long custId) {
		
		return customerDao.findById(custId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateCustomer(Customer customer) {
		customerDao.update(customer);
		
	}

	/**
	 * 使用投影查询，查询客户列表
	 */
	@Override
	public List<Customer> findAllCustomer() {		
		return customerDao.findAllcustomer();
	}

	@Override
	public Page findAllCustomer(DetachedCriteria dCriteria, Integer num) {
		
		//1、准备当前页信息
		int currentPageNum = 1;
		if(num != null) {
			currentPageNum = num;
		}
		//2、获取总记录条数
		int totalRecords = customerDao.findTotalRecords(dCriteria);
		
		//3、创建一个page对象
		Page page = new Page(currentPageNum, totalRecords);
		//4、使用page对象中的数据，查询带有分页的结果集
		List<Customer> records = customerDao.findAllcustomer(dCriteria, page.getStartIndex(), page.getPageSize());
		//5、把查询出来的结果集封装到page对象中
		page.setRecords(records);
		//6、返回page对象
		return page;
				
	}

}
