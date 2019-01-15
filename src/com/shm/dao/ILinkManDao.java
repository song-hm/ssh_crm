package com.shm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.shm.domain.LinkMan;
/**
 * 联系人的持久层接口
 * @author SHM
 *
 */
public interface ILinkManDao {

	/**
	 * 新增联系人
	 * @param linkman
	 */
	void saveLinkMan(LinkMan linkman);

	/**
	 * 查询联系人列表
	 * @param dCriteria 查询的条件
	 * @param firstResult	查询开始记录的索引
	 * @param maxResults	每次查询记录的条数
	 * @return
	 */
	List<LinkMan> findAll(DetachedCriteria dCriteria,int firstResult,int maxResults);

	/**
	 * 删除联系人
	 * @param long1
	 */
	void removeLinkMan(Long lkmId);

	/**
	 * 根据ID查询联系人
	 * @param lkmId
	 * @return
	 */
	LinkMan findById(Long lkmId);

	/**
	 * 更新联系人
	 * @param linkman
	 */
	void updateLinkMan(LinkMan linkman);

	/**
	 * 查询联系人的总记录条数
	 * @param dCriteria	查询的条件
	 * @return
	 */
	int findTotalRecords(DetachedCriteria dCriteria);
}
