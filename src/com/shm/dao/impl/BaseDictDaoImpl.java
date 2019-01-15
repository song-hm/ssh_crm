package com.shm.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.shm.dao.IBaseDictDao;
import com.shm.domain.BaseDict;
/**
 * 	字典表的持久层实现类
 * @author SHM
 *
 */
@Repository("baseDictDao")
public class BaseDictDaoImpl implements IBaseDictDao {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	@Override
	public List<BaseDict> findBaseDictByTypeCode(String typeCode) {
		
		return (List<BaseDict>) hibernateTemplate.find("from BaseDict where dictTypeCode = ?", typeCode);
	}

}
