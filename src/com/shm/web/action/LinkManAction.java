package com.shm.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shm.domain.Customer;
import com.shm.domain.LinkMan;
import com.shm.service.ICustomerService;
import com.shm.service.ILinkManService;
import com.shm.web.commons.Page;
/**
 * 联系人的动作类
 * @author SHM
 *
 */
@Controller("linkmanAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/linkman")
@Results({
	@Result(name="addUI",type="dispatcher",location="/jsp/linkman/add.jsp"),
	@Result(name="success",type="redirect",location="/jsp/success.jsp"),
	@Result(name="findAll",type="dispatcher",location="/jsp/linkman/list.jsp"),
	@Result(name="editUI",type="dispatcher",location="/jsp/linkman/edit.jsp")
})
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	private LinkMan linkman = new LinkMan();
	private Integer num;
	private Page page;
	@Resource(name="customerService")
	private ICustomerService customerService;
	private List<Customer> customers;
	@Resource(name="linkmanService")
	private ILinkManService linkmanService;
	@Override
	public LinkMan getModel() {		
		return linkman;
	}

	/**
	 * 编辑联系人
	 * @return
	 */
	@Action("editLinkMan")
	public String editLinkMan() {
		linkmanService.updateLinkMan(linkman);
		return SUCCESS;
	}
	/**
	 * 获取编辑联系人页面
	 * @return
	 */
	@Action("editUILinkMan")
	public String editUILinkMan() {
		//1、根据ID查询联系人信息（查询那个要编辑的联系人）
		LinkMan man = linkmanService.findLinkManById(linkman.getLkmId());
		//2、把查询出来的联系人压入栈顶
		ActionContext.getContext().getValueStack().push(man);
		//3、查询所有客户（使用投影查询）
		customers = customerService.findAllCustomer();
		return "editUI";
	}
	/**
	 * 删除联系人
	 * @return
	 */
	@Action("removeLinkMan")
	public String removeLinkMan() {
		linkmanService.removeLinkMan(linkman.getLkmId());
		return SUCCESS;
	}
	/**
	 *	查询所有联系人
	 * @return
	 */
	@Action("findAllLinkMan")
	public String findAllLinkMan() {
		//1、定义离线对象
		DetachedCriteria dCriteria = DetachedCriteria.forClass(LinkMan.class); 
		//2、拼装查询条件
		//判断是否输入了联系人名称
		if(StringUtils.isNotBlank(linkman.getLkmName())) {
			//模糊查询
			dCriteria.add(Restrictions.like("lkmName", "%"+linkman.getLkmName()+"%"));
		}
		//判断是否输入了联系人职务
		if(StringUtils.isNotBlank(linkman.getLkmPosition())) {
			//模糊查询
			dCriteria.add(Restrictions.like("lkmPosition", "%"+linkman.getLkmPosition()+"%"));
		}
		//判断是否选择了联系人所属客户
		if(linkman.getCustomer() != null && linkman.getCustomer().getCustId() != null) {
			//精确查询
			dCriteria.add(Restrictions.eq("customer.custId",linkman.getCustomer().getCustId()));
		}
		//判断是否选择了联系人性别
		if(StringUtils.isNotBlank(linkman.getLkmGender())) {
			//精确查询
			dCriteria.add(Restrictions.eq("lkmGender",linkman.getLkmGender()));
		}
		//3、查询所有联系人
		page = linkmanService.findAllLinkMan(dCriteria,num);
		//4、查询所有客户（使用投影查询）
		customers = customerService.findAllCustomer();
		return "findAll";
	}
	/**
	 * 新增联系人
	 * @return
	 */
	@Action("addLinkMan")
	public String addLinkMan() {
		linkmanService.saveLinkMan(linkman);
		return SUCCESS;
	}
	/**
	 * 获取添加联系人页面
	 * @return
	 */
	@Action("addUILinkMan")
	public String addUILinkMan() {
		//查询所有客户		
		customers = customerService.findAllCustomer();
		return "addUI";
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
}
