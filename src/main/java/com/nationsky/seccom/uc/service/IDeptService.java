package com.nationsky.seccom.uc.service;

import java.util.Date;
import java.util.List;

import com.nationsky.seccom.uc.domain.DeptRequestData;
import com.nationsky.seccom.uc.model.DeptBasicInfo;

public interface IDeptService {
	public String addDept(DeptRequestData request);
	public boolean deleteDept(String deptId);
	public Date updateDeptBasicInfo(DeptBasicInfo departmentBasicInfo);
	public DeptBasicInfo getDepartmentBasicInfo(String deptId);
	public List<String> getAllUserIdOfDeptExcludiingSubDeptByDeptId(String deptId) throws IllegalArgumentException;
	public List<String> getSubDepts(String deptId, int length) throws IllegalArgumentException;
	public boolean addDescendantDept(String deptId, String ancestorDeptId);
}
