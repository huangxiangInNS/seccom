package com.nationsky.seccom.uc.service.implement;

import com.nationsky.seccom.uc.dao.UserDeptRelationMapper;
import com.nationsky.seccom.uc.model.UserDeptRelation;
import com.nationsky.seccom.uc.model.UserDeptRelationExample;
import com.nationsky.seccom.uc.service.IUserDeptRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangxiang on 15-6-19.
 */
@Service("userDeptRelationService")
public class IUserDeptRelationServiceImpl implements IUserDeptRelationService {
    @Autowired
    private UserDeptRelationMapper userDeptRelationMapper;
    @Override
    public boolean insertUserDeptRelation(String userId, String companyId,
                                          String deptId, int positionOrder,
                                          String jobId, String status) {
        UserDeptRelation userDeptRelation = new UserDeptRelation();
        userDeptRelation.setCompanyId(companyId);
        userDeptRelation.setJobId(jobId);
        userDeptRelation.setUserId(userId);
        userDeptRelation.setDeptId(deptId);
        userDeptRelation.setPositionOrder(positionOrder);
        userDeptRelation.setStatus(status);

        return userDeptRelationMapper.insert(userDeptRelation) > 0;
    }

    @Override
    public boolean deleteUserDeptRelation(String userId, String companyId, String deptId) {
        UserDeptRelationExample userDeptRelationExample = new UserDeptRelationExample();
        userDeptRelationExample.createCriteria().andUserIdEqualTo(userId)
                .andCompanyIdEqualTo(companyId).andDeptIdEqualTo(deptId);
        return userDeptRelationMapper.deleteByExample(userDeptRelationExample) > 0;
    }

    @Override
    public boolean updateUserDeptRelation(String userId, String companyId, String deptId,
                                          int positionOrder, String jobId, String status) {
        UserDeptRelation userDeptRelation = new UserDeptRelation();
        userDeptRelation.setCompanyId(companyId);
        userDeptRelation.setJobId(jobId);
        userDeptRelation.setUserId(userId);
        userDeptRelation.setDeptId(deptId);
        userDeptRelation.setPositionOrder(positionOrder);
        userDeptRelation.setStatus(status);

        UserDeptRelationExample userDeptRelationExample = new UserDeptRelationExample();
        userDeptRelationExample.createCriteria().andUserIdEqualTo(userId)
                .andCompanyIdEqualTo(companyId).andDeptIdEqualTo(deptId);

        return userDeptRelationMapper.updateByExampleSelective(userDeptRelation,
                userDeptRelationExample) > 0;
    }

    @Override
    public List<UserDeptRelation> getUserDeptRelations(UserDeptRelationExample example) {
        return userDeptRelationMapper.selectByExample(example);
    }

    @Override
    public int countList(UserDeptRelationExample example) {
        return userDeptRelationMapper.countByExample(example);
    }
}
