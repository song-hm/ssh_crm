package com.shm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shm.dao.ILinkManDao;
import com.shm.domain.LinkMan;
import com.shm.service.ILinkManService;
import com.shm.web.commons.Page;
/**
 * 联系人的业务层实现类
 * @author SHM
 *
 */
@Service("linkmanService")
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class LinkManServiceImpl implements ILinkManService {

	@Resource(name="linkmanDao")
	private ILinkManDao linkmanDao;
	
	/**
	 * 保存联系人
	 */
	@Override
	public void saveLinkMan(LinkMan linkman) {
		linkmanDao.saveLinkMan(linkman);
	}

	
	
	@Override
	public void removeLinkMan(Long lkmId) {
		linkmanDao.removeLinkMan(lkmId);
		
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public LinkMan findLinkManById(Long lkmId) {
		
		return linkmanDao.findById(lkmId);
	}

	@Override
	public void updateLinkMan(LinkMan linkman) {
		linkmanDao.updateLinkMan(linkman);
	}



	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public Page findAllLinkMan(DetachedCriteria dCriteria, Integer num) {
		//1、准备当前页信息
		int currentPageNum = 1;
		if(num != null) {
			currentPageNum = num;
		}
		//2、获取总记录条数
		int totalRecords = linkmanDao.findTotalRecords(dCriteria);
		//创建page对象
		Page page = new Page(currentPageNum, totalRecords);
		//4、使用page对象中的数据，查询带有分页的结果集
		List<LinkMan> records = linkmanDao.findAll(dCriteria, page.getStartIndex(), page.getPageSize());
		//5、把查询出来的结果集封装到page对象中
		page.setRecords(records);
		//6、返回page对象
		return page;
	}

}
