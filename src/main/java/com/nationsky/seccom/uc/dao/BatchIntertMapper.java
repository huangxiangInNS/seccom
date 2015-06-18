package com.nationsky.seccom.uc.dao;

import com.nationsky.seccom.uc.model.UserBasicInfo;
import com.nationsky.seccom.uc.model.UserDeptRelation;
import com.nationsky.seccom.uc.model.UserIdLoginAccountRelation;

import java.util.List;

/**
 * Created by tiantao on 15-6-17.
 */
public interface BatchIntertMapper {

    public int insertUserBatch(List<UserBasicInfo> list);
    public int insertUserDeptBatch(List<UserDeptRelation> list);
    public int insertAccountBatch(List<UserIdLoginAccountRelation> list);
}
