package com.nationsky.seccom.uc.service;

import com.nationsky.seccom.uc.domain.LoginInfoRequestData;

/**
 * Created by tiantao on 15-6-18.
 */
public interface IAccountService {

    /**
     * @param loginInfoRequestData
     * @return
     */
    String createLoginInfo(LoginInfoRequestData loginInfoRequestData);

    /**
     * @param loginInfoRequestData
     * @return
     */
    String checkLoginInfo(LoginInfoRequestData loginInfoRequestData);


    /**
     * 根据公司id和用户id删除账号信息
     * @param companyId
     * @param userId
     * @return
     */
    boolean deleteLoginInfo(String companyId, String userId);


    /**
     * @param userId
     * @return
     */
    String getSign(String userId);

    /**
     * @param sign
     * @return
     */
    boolean setSign(String userId, String sign);
}
