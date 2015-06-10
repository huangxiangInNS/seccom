package com.nationsky.seccom.uc.domain;

public class CompanyRequestData
{
	private String companyName;
	private String companyAlias;
	private String companyEmail;
	private String companyPhone;
	private String companyAddress;
	private String companyUrl;
	private String ancestorCompanyId;
	private String companyId;

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
	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}
	public String getCompanyAlias()
	{
		return companyAlias;
	}
	public void setCompanyAlias(String companyAlias)
	{
		this.companyAlias = companyAlias;
	}
	public String getCompanyEmail()
	{
		return companyEmail;
	}
	public void setCompanyEmail(String companyEmail)
	{
		this.companyEmail = companyEmail;
	}
	public String getCompanyPhone()
	{
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone)
	{
		this.companyPhone = companyPhone;
	}
	public String getCompanyAddress()
	{
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress)
	{
		this.companyAddress = companyAddress;
	}
	public String getCompanyUrl()
	{
		return companyUrl;
	}
	public void setCompanyUrl(String companyUrl)
	{
		this.companyUrl = companyUrl;
	}
	public String getAncestorCompanyId()
	{
		return ancestorCompanyId;
	}
	public void setAncestorCompanyId(String ancestorCompanyId)
	{
		this.ancestorCompanyId = ancestorCompanyId;
	}
}
