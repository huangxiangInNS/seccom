package com.nationsky.seccom.uc.service.implement;

import com.nationsky.seccom.uc.dao.CompanyInfoMapper;
import com.nationsky.seccom.uc.dao.UserIdLoginAccountRelationMapper;
import com.nationsky.seccom.uc.domain.LoginInfoRequestData;
import com.nationsky.seccom.uc.model.CompanyInfo;
import com.nationsky.seccom.uc.model.CompanyInfoExample;
import com.nationsky.seccom.uc.model.UserIdLoginAccountRelation;
import com.nationsky.seccom.uc.model.UserIdLoginAccountRelationExample;
import com.nationsky.seccom.uc.service.IAccountService;
import com.nationsky.seccom.uc.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tiantao on 15-6-18.
 */
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private UserIdLoginAccountRelationMapper userIdLoginAccountRelationMapper;

    @Autowired
    private CompanyInfoMapper companyInfoMapper;


    @Override
    public String createLoginInfo(String userId, LoginInfoRequestData loginInfoRequestData) {
        if (loginInfoRequestData == null) {
            return null;
        } else {
            /*通过公司别名找到公司id*/
            String companyAlias = loginInfoRequestData.getCompanyAlias();
            CompanyInfoExample companyInfoExample = new CompanyInfoExample();
            companyInfoExample.createCriteria().andCompanyAliasEqualTo(companyAlias);
            List<CompanyInfo> companyInfos =
                    companyInfoMapper.selectByExample(companyInfoExample);
            if (companyInfos == null | companyInfos.size() == 0)
            {
                throw new RuntimeException("无法找到该公司别名指定的公司");
            }
            CompanyInfo companyInfo = companyInfos.get(0);
            String companyId = companyInfo.getCompanyId();

            /*从登录请求中获取信息*/
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

            //TODOloginName不能重复
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
        String companyAlias = loginInfoRequestData.getCompanyAlias();
        String loginName = loginInfoRequestData.getLoginName();
        String password = loginInfoRequestData.getPassword();

        /*通过公司别名找到公司id*/
        CompanyInfoExample companyInfoExample = new CompanyInfoExample();
        companyInfoExample.createCriteria().andCompanyAliasEqualTo(companyAlias);
        List<CompanyInfo> companyInfos =
                companyInfoMapper.selectByExample(companyInfoExample);
        if (companyInfos == null | companyInfos.size() == 0)
        {
            throw new RuntimeException("无法找到该公司别名指定的公司！");
        }
        CompanyInfo companyInfo = companyInfos.get(0);
        String companyId = companyInfo.getCompanyId();

        /*找到员工id*/
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


    public boolean setSign(String userId, String loginName, String sign) {
        UserIdLoginAccountRelationExample example =
                new UserIdLoginAccountRelationExample();
        example.createCriteria().andUserIdEqualTo(userId)
                .andLoginNameEqualTo(loginName);
        List<UserIdLoginAccountRelation> relations =
                userIdLoginAccountRelationMapper.selectByExample(example);
        if (relations.size() == 0) {
            throw new RuntimeException("没有找到指定的用户或登录名！");
        } else {
            UserIdLoginAccountRelation relation = relations.get(0);
            relation.setPersonSign(sign);
            return userIdLoginAccountRelationMapper
                    .updateByPrimaryKey(relation) > 0;
        }
    }

    @Override
    public boolean setNewPassword(String userId, String loginName, String newPassword) {
        UserIdLoginAccountRelationExample example =
                new UserIdLoginAccountRelationExample();
        example.createCriteria().andUserIdEqualTo(userId)
                .andLoginNameEqualTo(loginName);
        UserIdLoginAccountRelation userIdLoginAccountRelation =
                new UserIdLoginAccountRelation();
        userIdLoginAccountRelation.setPassword(newPassword);

        return userIdLoginAccountRelationMapper
                .updateByExampleSelective(userIdLoginAccountRelation, example) > 0;
    }
}
