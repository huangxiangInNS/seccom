package com.nationsky.seccom.uc.domain;

public class DeptRequestData
{
	private String deptId;
	private String companyId;
	private String deptName;
	private String deptPhone;
	private String deptInfo;
	private int deptOrder;
	private String ancestorDeptId;
	public String getDeptId()
	{
		return deptId;
	}
	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
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
	public String getAncestorDeptId()
	{
		return ancestorDeptId;
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
	public void setAncestorDeptId(String ancestorDeptId)
	{
		this.ancestorDeptId = ancestorDeptId;
	}

}
