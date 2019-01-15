package com.shm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.shm.domain.LinkMan;
import com.shm.web.commons.Page;

/**
 * 联系人的业务层接口
 * @author SHM
 *
 */
public interface ILinkManService {	

	/**
	 * 保存联系人
	 * @param linkman
	 */
	void saveLinkMan(LinkMan linkman);

	/**
	 * 查询所有联系人
	 * @param dCriteria 查询的条件
	 * @param num 当前页
	 * @return 封装好的分页信息
	 */
	Page findAllLinkMan(DetachedCriteria dCriteria,Integer num);

	/**
	 * 删除联系人
	 * @param long1
	 */
	void removeLinkMan(Long lkmId);

	/**
	 * 根据ID查询联系人信息
	 * @param lkmId
	 * @return
	 */
	LinkMan findLinkManById(Long lkmId);

	/**
	 * 更新联系人
	 * @param linkman
	 */
	void updateLinkMan(LinkMan linkman);
	
	
}
