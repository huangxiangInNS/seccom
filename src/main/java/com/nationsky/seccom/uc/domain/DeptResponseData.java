package com.nationsky.seccom.uc.domain;

import java.util.Date;

public class DeptResponseData
{
	private String deptId;
	private String companyId;
	private String deptName;
	private String deptPhone;
	private String deptInfo;
	private int deptOrder;
	private String deptLeaderId;
	private String status;
	private String deptSource;
	private String isRootDept;
	private String fullPath;
	private Date createTime;
	private Date updateTime;
	public String getDeptId()
	{
		return deptId;
	}
	public String getCompanyId()
	{
		return companyId;
	}
	public String getDeptName()
	{
		return deptName;
	}
	public String getDeptPhone()
	{
		return deptPhone;
	}
	public String getDeptInfo()
	{
		return deptInfo;
	}
	public int getDeptOrder()
	{
		return deptOrder;
	}
	public String getDeptLeaderId()
	{
		return deptLeaderId;
	}
	public String getStatus()
	{
		return status;
	}
	public String getDeptSource()
	{
		return deptSource;
	}
	public String getIsRootDept()
	{
		return isRootDept;
	}
	public String getFullPath()
	{
		return fullPath;
	}
	public Date getCreateTime()
	{
		return createTime;
	}
	public Date getUpdateTime()
	{
		return updateTime;
	}
	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
	}
	public void setCompanyId(String companyId)
	{
		this.companyId = companyId;
	}
	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}
	public void setDeptPhone(String deptPhone)
	{
		this.deptPhone = deptPhone;
	}
	public void setDeptInfo(String deptInfo)
	{
		this.deptInfo = deptInfo;
	}
	public void setDeptOrder(int deptOrder)
	{
		this.deptOrder = deptOrder;
	}
	public void setDeptLeaderId(String deptLeaderId)
	{
		this.deptLeaderId = deptLeaderId;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public void setDeptSource(String deptSource)
	{
		this.deptSource = deptSource;
	}
	public void setIsRootDept(String isRootDept)
	{
		this.isRootDept = isRootDept;
	}
	public void setFullPath(String fullPath)
	{
		this.fullPath = fullPath;
	}
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
	public void setUpdateTime(Date updateTime)
	{
		this.updateTime = updateTime;
	}
}
