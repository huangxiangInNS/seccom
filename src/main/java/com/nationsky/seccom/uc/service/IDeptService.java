package com.nationsky.seccom.uc.service;

import java.util.Date;
import java.util.List;

import com.nationsky.seccom.uc.domain.DeptRequestData;
import com.nationsky.seccom.uc.domain.DeptResponseData;
import com.nationsky.seccom.uc.model.*;

public interface IDeptService {

	/**
	 * 添加部门
	 * @param request 创建部门所需的信息
	 * @return
	 */
	String addDept(DeptRequestData request);

	/**
	 * 删除部门
	 * @param deptId 部门id
	 * @return
	 */
	boolean deleteDept(String deptId);


	/**
	 * 更新部门基本信息
	 * @param departmentBasicInfo 部门基本信息
	 * @return
	 */
	Date updateDeptBasicInfo(DeptBasicInfo departmentBasicInfo);


	/**
	 * 获取部门基本信息
	 * @param deptId 部门id
	 * @return
	 */
	DeptResponseData getDepartmentBasicInfo(String deptId);


	/**
	 * 获取部门下的用户
	 * @param deptId 部门id
	 * @return
	 */
	List<UserBasicInfo> getAllUsersOfDeptExcludingSubDeptByDeptId(String deptId);


	/**
	 * 获取部门下的子部门
	 * @param deptId 部门id
	 * @param length 部门关系层数
	 * @return
	 * @throws IllegalArgumentException
	 */
	List<DeptResponseData> getDesendentDepts(String deptId, int length);


	/**
	 * 获得符合条件的部门个数
	 * @param deptBasicInfoExample 查询条件
	 * @return
	 */
	int countList(DeptBasicInfoExample deptBasicInfoExample);


	/**
	 * 获取符合条件的部门列表
	 * @param deptBasicInfoExample 查询条件
	 * @return
	 */
	List<DeptResponseData> findList(DeptBasicInfoExample deptBasicInfoExample);


	/**
	 * 获取符合条件的首个部门详情
	 * @param deptBasicInfoExample 查询条件
	 * @return
	 */
	DeptResponseData getDepartmentBasicInfo(DeptBasicInfoExample deptBasicInfoExample);


	/**
	 * 获取部门下的用户和子部门
	 * @param deptId 部门id
	 * @return
	 */
	String getPrimaryDesendentDeptAndUsers(String deptId);


	boolean addDeptExtension(String deptId, String extensionId, String extensionValue);


	int countDeptExtensionList(DeptExtensionExample deptExtensionExample);

	boolean deleteDeptExtension(DeptExtensionExample deptExtensionExample);
}
