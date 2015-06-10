package com.nationsky.seccom.uc.domain;

import java.util.Date;

public class CompanyResponseData
{
	private String companyId;
	private String companyName;
	private String companyAlias;
	private String companyEmail;
	private String companyPhone;
	private String companyAddress;
	private String companyUrl;
	private Date createTime;
	private Date updateTime;
	private String ldapConfigIndex;
	private String status;
	public String getCompanyId()
	{
		return companyId;
	}
	public void setCompanyId(String companyId)
	{
		this.companyId = companyId;
	}
	public String getCompanyName()
	{
		return companyName;
	}
	public String getCompanyAlias()
	{
		return companyAlias;
	}
	public String getCompanyEmail()
	{
		return companyEmail;
	}
	public String getCompanyPhone()
	{
		return companyPhone;
	}
	public String getCompanyAddress()
	{
		return companyAddress;
	}
	public String getCompanyUrl()
	{
		return companyUrl;
	}
	public Date getCreateTime()
	{
		return createTime;
	}
	public Date getUpdateTime()
	{
		return updateTime;
	}
	public String getLdapConfigIndex()
	{
		return ldapConfigIndex;
	}
	public String getStatus()
	{
		return status;
	}
	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}
	public void setCompanyAlias(String companyAlias)
	{
		this.companyAlias = companyAlias;
	}
	public void setCompanyEmail(String companyEmail)
	{
		this.companyEmail = companyEmail;
	}
	public void setCompanyPhone(String companyPhone)
	{
		this.companyPhone = companyPhone;
	}
	public void setCompanyAddress(String companyAddress)
	{
		this.companyAddress = companyAddress;
	}
	public void setCompanyUrl(String companyUrl)
	{
		this.companyUrl = companyUrl;
	}
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
	public void setUpdateTime(Date updateTime)
	{
		this.updateTime = updateTime;
	}
	public void setLdapConfigIndex(String ldapConfigIndex)
	{
		this.ldapConfigIndex = ldapConfigIndex;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}

}
