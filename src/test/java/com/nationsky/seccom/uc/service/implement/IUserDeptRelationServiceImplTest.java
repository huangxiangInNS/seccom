package com.nationsky.seccom.uc.service.implement;

import com.nationsky.seccom.uc.model.UserDeptRelation;
import com.nationsky.seccom.uc.model.UserDeptRelationExample;
import com.nationsky.seccom.uc.service.IUserDeptRelationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by huangxiang on 15-6-19.
 */
public class IUserDeptRelationServiceImplTest {

    private ClassPathXmlApplicationContext context = null;
    private IUserDeptRelationService userDeptRelationService;
    @Test
    public void testInsertUserDeptRelation() throws Exception {
        String userId = "TtobhPsDA2SowcXkeOXc";
        String companyId = "DGY2fF2D";
        String deptId = "BaFR2mSX";
        int position = 1;
        String jobId = "LHZx";
        String status = "1";

        userDeptRelationService.insertUserDeptRelation(
                userId, companyId, deptId, position, jobId, status);
    }

    @Test
    public void testDeleteUserDeptRelation() throws Exception {
        String userId = "TtobhPsDA2SowcXkeOXc";
        String deptId = "BaFR2mSX";
        String companyId = "DGY2fF2D";
        UserDeptRelationExample example = new UserDeptRelationExample();
        example.createCriteria().andUserIdEqualTo(userId).andDeptIdEqualTo(deptId);
        userDeptRelationService.deleteUserDeptRelation(userId, companyId, deptId);
    }

    @Test
    public void testUpdateUserDeptRelation() throws Exception {
        String userId = "TtobhPsDA2SowcXkeOXc";
        String companyId = "DGY2fF2D";
        String deptId = "BaFR2mSX";
        int position = 24;
        String jobId = "LHZx";
        String status = "1";

        userDeptRelationService.updateUserDeptRelation(
                userId, companyId, deptId, position, jobId, status);
    }

    @Test
    public void testGetUserDeptRelations() throws Exception {
        String userId = "TtobhPsDA2SowcXkeOXc";
        UserDeptRelationExample example = new UserDeptRelationExample();
        example.createCriteria().andUserIdEqualTo(userId);

        List<UserDeptRelation> userDeptRelationList = userDeptRelationService.getUserDeptRelations(example);
        for (UserDeptRelation userDeptRelation : userDeptRelationList)
        {
            System.out.println(userDeptRelation.getCompanyId());
        }

    }

    @Test
    public void testCountList() throws Exception {
        String userId = "TtobhPsDA2SowcXkeOXc";
        UserDeptRelationExample example = new UserDeptRelationExample();
        example.createCriteria().andUserIdEqualTo(userId);

        System.out.println(userDeptRelationService.countList(example));
    }

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("spring_config.xml");
        userDeptRelationService =
                (IUserDeptRelationService)context.getBean("userDeptRelationService");
    }

    @After
    public void tearDown() throws Exception {
        context.close();
    }
}