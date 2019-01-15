package com.shm.test;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shm.domain.Customer;
import com.shm.service.ICustomerService;

/**
 * 测试客户的业务层两方法
 * @author SHM
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:config/spring/applicationContext.xml"})
public class CustomerServiceTest {

	@Autowired
	private ICustomerService cs;
	@Test
	public void testFindAllCustomer() {
//		DetachedCriteria dCriteria = DetachedCriteria.forClass(Customer.class);
//		List list = cs.findAllCustomer(dCriteria);
//		for (Object object : list) {
//			System.out.println(object);
//		}
	}
	
	@Test
	public void testSaveCustomer() {
		Customer c = new Customer();
		c.setCustName("crm customer");
		cs.saveCustomer(c);
		
	}
}
