package com.nationsky.seccom.uc.service;

import com.nationsky.seccom.uc.domain.LoginInfoRequestData;
import com.nationsky.seccom.uc.domain.Request;
import com.nationsky.seccom.uc.domain.UserRequestData;
import com.nationsky.seccom.uc.model.UserBasicInfo;
import com.nationsky.seccom.uc.model.UserBasicInfoExample;
import com.nationsky.seccom.uc.model.UserExtension;
import com.nationsky.seccom.uc.model.UserExtensionExample;

import java.util.Date;
import java.util.List;

public interface IUserService {
    /**
     * 添加用户
     * @param request
     * @return
     */
    String addUser(Request<UserRequestData> request);

    /**
     * 根据用户ID删除用户
     * @param userId
     * @return
     */
    boolean deleteUserByUserId(String userId);

    /**
     * @param userRequestData
     * @return
     */
    Date updateUserBasicInfo(UserRequestData userRequestData);

    /**
     * 根据用户ID查询用户详细信息
     * @param userId
     * @return
     */
    UserBasicInfo getUserBasicInfoByUserId(String userId);


    /**
     * 根据条件查询列表
     * @param example
     * @return
     */
    List<UserBasicInfo> findList(UserBasicInfoExample example);


    /**
     * 根据条件查询记录数
     * @param example
     * @return
     */
    int countList(UserBasicInfoExample example);


    /**
     * 根据扩展信息的Id删除扩展信息
     * @param userExtensionId
     * @return
     */
    boolean deleteUserExtension(String userExtensionId);

    /**
     * 根据条件获取用户扩展信息列表
     * @param example
     * @return
     */
    List<UserExtension> findUserExtension(UserExtensionExample example);


    /**
     * 添加用户扩展信息
     * @param userExtension
     * @return
     */
    boolean addUserExtension(UserExtension userExtension);


    /**
     * 修改用户扩展信息
     * @param userExtension
     * @return
     */
    boolean updateUserExtension(UserExtension userExtension);



}
