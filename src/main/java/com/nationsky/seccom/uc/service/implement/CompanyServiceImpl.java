package com.nationsky.seccom.uc.service.implement;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.nationsky.seccom.uc.dao.DeptBasicInfoMapper;
import com.nationsky.seccom.uc.dao.DeptRelationMapper;
import com.nationsky.seccom.uc.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nationsky.seccom.uc.dao.CompanyInfoMapper;
import com.nationsky.seccom.uc.dao.CompanyRelationMapper;
import com.nationsky.seccom.uc.domain.CompanyRequestData;
import com.nationsky.seccom.uc.domain.CompanyResponseData;
import com.nationsky.seccom.uc.domain.DeptRequestData;
import com.nationsky.seccom.uc.service.ICompanyService;
import com.nationsky.seccom.uc.service.IDeptService;
import com.nationsky.seccom.uc.util.ServiceUtil;

@Service("companyService")
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private CompanyInfoMapper companyInfoMapper;
    @Autowired
    private CompanyRelationMapper companyRelationMapper;

    @Autowired
    private DeptRelationMapper deptRelationMapper;

    @Autowired
    private DeptBasicInfoMapper deptBasicInfoMapper;

    @Autowired
    private IDeptService deptService;

    @Transactional
    public String addCompany(CompanyRequestData requestData) {
        String descendantCompanyId = addCompanyInfo(requestData);
        if (descendantCompanyId == null) {
            return null;
        } else {
            String ancestorCompanyId = requestData.getAncestorCompanyId();

            boolean relationAdditionResult = addCompanyRelation(ancestorCompanyId, descendantCompanyId);

            if (!relationAdditionResult) {
                throw new RuntimeException("添加公司关系失败！");
            } else {
                // 返回公司的id号
                if (createRootDept(descendantCompanyId) == null) {
                    throw new RuntimeException("添加公司根部门失败！");
                }
                return descendantCompanyId;
            }
        }
    }

    private boolean deleteCompanyInfo(String companyId) {

        if (companyId == null) {
            return false;
        } else {
            int updateCount = companyInfoMapper.deleteByPrimaryKey(companyId);

            return updateCount == 1;
        }
    }

    public Date updateCompanyInfo(CompanyInfo companyInfo) {

        if (companyInfo == null) {
            return null;
        } else {
            //生成更新时间
            Date currentDate = ServiceUtil.getCurrentTime();
            companyInfo.setUpdateTime(currentDate);

            int updateCount = companyInfoMapper.updateByPrimaryKeySelective(companyInfo);

            if (updateCount == 0) {
                return null;
            } else {
                return currentDate;
            }
        }
    }

    public CompanyResponseData getCompanyInfoById(String companyId) {

        if (companyId == null) {
            return null;
        } else {
            CompanyInfo companyInfo =
                    companyInfoMapper.selectByPrimaryKey(companyId);
            if (companyInfo == null) {
                return null;
            } else {
                /*将获得的公司信息写入到返回体中*/
                CompanyResponseData companyResponseData = new CompanyResponseData();
                companyResponseData.setCompanyAddress(companyInfo.getCompanyAddress());
                companyResponseData.setCompanyAlias(companyInfo.getCompanyAlias());
                companyResponseData.setCompanyEmail(companyInfo.getCompanyEmail());
                companyResponseData.setCompanyId(companyInfo.getCompanyId());
                companyResponseData.setCompanyName(companyInfo.getCompanyName());
                companyResponseData.setCompanyPhone(companyInfo.getCompanyPhone());
                companyResponseData.setCompanyUrl(companyInfo.getCompanyUrl());
                companyResponseData.setCreateTime(companyInfo.getCreateTime());
                companyResponseData.setUpdateTime(companyInfo.getUpdateTime());
                companyResponseData.setLdapConfigIndex(companyInfo.getLdapConfigIndex());
                companyResponseData.setStatus(companyInfo.getStatus());

                return companyResponseData;
            }
        }
    }

    /**
     * 根据查询条件查询列表
     *
     * @param example
     * @return
     */
    @Override
    public List<CompanyInfo> findList(CompanyInfoExample example) {
        return companyInfoMapper.selectByExample(example);
    }

    /**
     * 根据查询条件查询记录数
     *
     * @param example
     * @return
     */
    @Override
    public int countList(CompanyInfoExample example) {
        return companyInfoMapper.countByExample(example);
    }

    @Override
    public DeptBasicInfo getRootDept(String companyId) {

		/*找出部门关系中上级部门id为ROOT的部门*/
        DeptRelationExample deptRelationExample = new DeptRelationExample();
        deptRelationExample.createCriteria().andAncestorDeptIdEqualTo("ROOT");
        List<DeptRelation> deptRelations =
                deptRelationMapper.selectByExample(deptRelationExample);
        Iterator<DeptRelation> iterator = deptRelations.iterator();

		/*在得到的部门中比对公司id是否和指定的公司id相同*/
        while (iterator.hasNext()) {
            String deptId = iterator.next().getDescendantDeptId();
            DeptBasicInfoExample deptBasicInfoExample = new DeptBasicInfoExample();
            deptBasicInfoExample.createCriteria().andDeptIdEqualTo(deptId)
                    .andCompanyIdEqualTo(companyId);
            int count = deptBasicInfoMapper.countByExample(deptBasicInfoExample);
            if (count != 0) {
                List<DeptBasicInfo> deptBasicInfos = deptBasicInfoMapper
                        .selectByExample(deptBasicInfoExample);
                return deptBasicInfos.get(0);
            }
        }
        return null;
    }

    @Transactional
    private boolean addCompanyRelation(String ancestorCompanyId,
                                       String descendantCompanyId) {
        CompanyRelation companyRelation = new CompanyRelation();
        int updateCount = 0;

        if (ancestorCompanyId != null && ancestorCompanyId.isEmpty() != true) {
			/*检查将要添加的子公司是否存在*/
            CompanyRelationExample checkExistingDescendantCompanyId = new CompanyRelationExample();
            checkExistingDescendantCompanyId.createCriteria().andDescendantCompanyIdEqualTo(descendantCompanyId);
            List<CompanyRelation> companyRelations = companyRelationMapper.selectByExample(checkExistingDescendantCompanyId);
            if (!companyRelations.isEmpty()) {
                throw new RuntimeException("子公司已经存在！");
            }
            companyRelations.clear();
			
			/*查找上级公司已经存在的关系*/
            CompanyRelationExample companyRelationExample = new CompanyRelationExample();
            companyRelationExample.createCriteria().andDescendantCompanyIdEqualTo(ancestorCompanyId);
            companyRelations = companyRelationMapper.selectByExample(companyRelationExample);

			/*如果为空则表示该输入的ancestorCompanyId 有问题。*/
            if (companyRelations == null || companyRelations.isEmpty()) {
                throw new RuntimeException("无法找不到指定的上级公司！");
            } else {
                for (CompanyRelation eachCompanyRelation : companyRelations) {
                    eachCompanyRelation.setDescendantCompanyId(descendantCompanyId);
                    int pathLength = eachCompanyRelation.getPathLength();
                    eachCompanyRelation.setPathLength(++pathLength);
                    updateCount = companyRelationMapper.insert(eachCompanyRelation);
                    if (updateCount == 0) {
                        throw new RuntimeException("插入公司关系失败！");
                    }
                }
            }
        }

		/*自己与自己的关系*/
        companyRelation.setAncestorCompanyId(descendantCompanyId);
        companyRelation.setDescendantCompanyId(descendantCompanyId);
        companyRelation.setPathLength(0);
        updateCount = companyRelationMapper.insert(companyRelation);
        if (updateCount == 0) {
            throw new RuntimeException("插入公司关系时失败！");
        }

        return true;
    }

    private String addCompanyInfo(CompanyRequestData requestData) {
        if (requestData == null) {
            return null;
        } else {
			/*从请求体中获得公司基本信息*/
            CompanyInfo companyInfo = new CompanyInfo();
            companyInfo.setCompanyName(requestData.getCompanyName());
            companyInfo.setCompanyAddress(requestData.getCompanyAddress());
            companyInfo.setCompanyAlias(requestData.getCompanyAlias());
            companyInfo.setCompanyEmail(requestData.getCompanyEmail());
            companyInfo.setCompanyPhone(requestData.getCompanyPhone());
            companyInfo.setCompanyUrl(requestData.getCompanyUrl());

            //生成公司id
            companyInfo.setCompanyId(ServiceUtil.getUUID());

            //生成创建时间和更新时间
            Date currentTime = ServiceUtil.getCurrentTime();
            companyInfo.setCreateTime(currentTime);
            companyInfo.setUpdateTime(currentTime);

			/*插入公司基本信息*/
            int updateCount = companyInfoMapper.insertSelective(companyInfo);

            if (updateCount == 0) {
                return null;
            } else {
                return companyInfo.getCompanyId();
            }
        }
    }

    private boolean removeCompanyRelation(String companyId) {
        //1.查看该公司是否存在，不存在返回false，否则继续下面的步骤。
        CompanyRelationExample companyRelationExample = new CompanyRelationExample();
        companyRelationExample.createCriteria().andAncestorCompanyIdEqualTo(companyId);
        List<CompanyRelation> companyRelations =
                companyRelationMapper.selectByExample(companyRelationExample);
        if (companyRelations == null || companyRelations.isEmpty()) {
            // 公司不存在
            return false;
        }
        //2.查看该公司是否有子公司，有则不能删除返回false，否则继续下面的步骤。
        else if (companyRelations.size() > 1) {
            // 存在子公司，不能删除
            return false;
        } else {
            //3.删除公司之间的关系
            CompanyRelationExample companyRelationDeleteExample = new CompanyRelationExample();
            companyRelationDeleteExample.createCriteria().andAncestorCompanyIdEqualTo(companyId);
            companyRelationMapper.deleteByExample(companyRelationDeleteExample);
            return true;
        }
    }

    @Transactional
    public boolean deleteCompany(String companyId) {
        //1.删除公司信息
        if (!deleteCompanyInfo(companyId)) {
            throw new RuntimeException("删除公司信息失败！");
        } else {
            // 2.删除公司之间的关系
            if (!removeCompanyRelation(companyId)) {
                throw new RuntimeException("删除与上级公司关系失败！");
            } else {
                if (!deleteAllDepts(companyId)) {
                    throw new RuntimeException("删除所属部门失败！");
                }
                return true;
            }
        }
    }


    private boolean deleteAllDepts(String companyId) {
        //TODO添加删除公司下所有部门的逻辑。
        return false;
    }


    public String createRootDept(String companyId) {
        // 创建一个root部门
        DeptRequestData createRootDeptRequestData = new DeptRequestData();
        createRootDeptRequestData.setAncestorDeptId(null);
        createRootDeptRequestData.setCompanyId(companyId);
        createRootDeptRequestData.setDeptName("root");
        String deptId = null;
        try {
            deptId = deptService.addDept(createRootDeptRequestData);
        } catch (RuntimeException e) {
            Logger logger = Logger.getLogger(this.getClass());
            logger.error(e);
            deptId = null;
        }
        return deptId;
    }
}
