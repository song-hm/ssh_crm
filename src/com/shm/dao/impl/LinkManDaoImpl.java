package com.shm.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.shm.dao.ILinkManDao;
import com.shm.domain.LinkMan;
/**
 * 联系人的持久层实现类
 * @author SHM
 *
 */
@Repository("linkmanDao")
public class LinkManDaoImpl implements ILinkManDao {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	@Override
	public void saveLinkMan(LinkMan linkman) {
		hibernateTemplate.save(linkman);
	}
	
	@Override
	public void removeLinkMan(Long lkmId) {
		LinkMan linkman = findById(lkmId);
		hibernateTemplate.delete(linkman);
		
	}
	@Override
	public LinkMan findById(Long lkmId) {
		
		return hibernateTemplate.get(LinkMan.class, lkmId);
	}
	@Override
	public void updateLinkMan(LinkMan linkman) {
		hibernateTemplate.update(linkman);		
	}	
	@Override
	public int findTotalRecords(DetachedCriteria dCriteria) {
		dCriteria.setProjection(Projections.count("lkmId"));//把select * 变成  select count(*)
		List<Long> list = (List<Long>) hibernateTemplate.findByCriteria(dCriteria);		
		return list.isEmpty()?0:list.get(0).intValue();
	}
	
	@Override
	public List<LinkMan> findAll(DetachedCriteria dCriteria, int firstResult, int maxResults) {
		//把之前的设置清除
		dCriteria.setProjection(null);
		return (List<LinkMan>) hibernateTemplate.findByCriteria(dCriteria, firstResult, maxResults);
	}
	

}
