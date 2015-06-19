package com.nationsky.seccom.uc.service;

import com.nationsky.seccom.uc.model.UserDeptRelation;
import com.nationsky.seccom.uc.model.UserDeptRelationExample;

import java.util.List;

/**
 * Created by huangxiang on 15-6-19.
 */
public interface IUserDeptRelationService {
    boolean insertUserDeptRelation(String userId, String companyId,
                                   String deptId, int positionOrder,
                                   String jobId, String status);

    boolean deleteUserDeptRelation(String userId, String companyId, String deptId);


    boolean updateUserDeptRelation(String userId, String companyId,
                                   String deptId, int positionOrder,
                                   String jobId, String status);


    List<UserDeptRelation> getUserDeptRelations(UserDeptRelationExample example);

    int countList(UserDeptRelationExample example);
}
