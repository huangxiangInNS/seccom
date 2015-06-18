package com.nationsky.seccom.uc.service.implement;

import com.nationsky.seccom.uc.dao.UserIdLoginAccountRelationMapper;
import com.nationsky.seccom.uc.domain.LoginInfoRequestData;
import com.nationsky.seccom.uc.model.UserIdLoginAccountRelation;
import com.nationsky.seccom.uc.model.UserIdLoginAccountRelationExample;
import com.nationsky.seccom.uc.service.IAccountService;
import com.nationsky.seccom.uc.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by tiantao on 15-6-18.
 */
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private UserIdLoginAccountRelationMapper userIdLoginAccountRelationMapper;


    @Override
    public String createLoginInfo(LoginInfoRequestData loginInfoRequestData) {
        if (loginInfoRequestData == null) {
            return null;
        } else {
            /*从用户请求中获取信息*/
            String userId = loginInfoRequestData.getUserId();
            String companyId = loginInfoRequestData.getCompanyId();
            String loginName = loginInfoRequestData.getLoginName();
            String password = loginInfoRequestData.getPassword();

			/*生成用户多登录账户关系*/
            UserIdLoginAccountRelation userIdLoginAccountRelation =
                    new UserIdLoginAccountRelation();
            userIdLoginAccountRelation.setCompanyId(companyId);
            userIdLoginAccountRelation.setLoginName(loginName);
            userIdLoginAccountRelation.setPassword(password);
            userIdLoginAccountRelation.setCreateTime(ServiceUtil.getCurrentTime());
            String loginId = ServiceUtil.getRandomString(20);

            userIdLoginAccountRelation.setUserId(userId);
            userIdLoginAccountRelation.setLoginId(loginId);

			/*插入数据库*/
            int updateCount = 0;
            updateCount =
                    userIdLoginAccountRelationMapper.insert(userIdLoginAccountRelation);

            if (updateCount == 1) {
                return userId;// 返回用户id
            } else {
                throw new RuntimeException("登录信息创建失败！");
            }
        }
    }


    @Override
    public String checkLoginInfo(LoginInfoRequestData loginInfoRequestData) {
        String companyId = loginInfoRequestData.getCompanyId();
        String loginName = loginInfoRequestData.getLoginName();
        String password = loginInfoRequestData.getPassword();

        UserIdLoginAccountRelationExample example = new UserIdLoginAccountRelationExample();
        example.createCriteria().andCompanyIdEqualTo(companyId).andLoginNameEqualTo(loginName)
                .andPasswordEqualTo(password);

        List<UserIdLoginAccountRelation> relations = userIdLoginAccountRelationMapper.selectByExample(example);

        if (relations == null) {
            return null;
        } else {
            if (relations.size() != 1) {
                return null;
            } else {
                return relations.get(0).getUserId();
            }
        }
    }

    /**
     * 根据公司id和用户id删除账号信息
     *
     * @param companyId
     * @param userId
     * @return
     */
    @Override
    public boolean deleteLoginInfo(String companyId, String userId) {

        UserIdLoginAccountRelationExample example = new UserIdLoginAccountRelationExample();

        example.createCriteria().andCompanyIdEqualTo(companyId).andUserIdEqualTo(userId);

        return  userIdLoginAccountRelationMapper.deleteByExample(example) > 0;

    }


    @Override
    public String getSign(String userId) {
        UserIdLoginAccountRelationExample example = new UserIdLoginAccountRelationExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<UserIdLoginAccountRelation> relations =
                userIdLoginAccountRelationMapper.selectByExample(example);
        if (relations.size() == 0) {
            throw new RuntimeException("没有找到指定的用户");
        } else {
            return relations.get(0).getPersonSign();
        }
    }


    public boolean setSign(String userId, String sign) {
        UserIdLoginAccountRelationExample example = new UserIdLoginAccountRelationExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<UserIdLoginAccountRelation> relations =
                userIdLoginAccountRelationMapper.selectByExample(example);
        if (relations.size() == 0) {
            throw new RuntimeException("没有找到指定的用户！");
        } else {
            UserIdLoginAccountRelation relation = relations.get(0);
            relation.setPersonSign(sign);
            userIdLoginAccountRelationMapper.updateByPrimaryKey(relation);
            return true;
        }
    }
}
