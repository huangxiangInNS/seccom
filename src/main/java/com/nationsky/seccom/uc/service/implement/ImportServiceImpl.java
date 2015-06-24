package com.nationsky.seccom.uc.service.implement;

import com.nationsky.seccom.uc.dao.*;
import com.nationsky.seccom.uc.model.*;
import com.nationsky.seccom.uc.service.IimportService;
import com.nationsky.seccom.uc.util.ServiceUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by tiantao on 15-6-10.
 */
@Service
public class ImportServiceImpl implements IimportService {


    @Autowired
    private DeptBasicInfoMapper deptBasicInfoMapper;


    @Autowired
    private DeptRelationMapper deptRelationMapper;

    @Autowired
    private UserDeptRelationMapper userDeptRelationMapper;

    @Autowired
    private UserBasicInfoMapper userBasicInfoMapper;

    @Autowired
    private UserIdLoginAccountRelationMapper userIdLoginAccountRelationMapper;

    @Autowired
    private JobDictMapper jobDictMapper;

    @Autowired
    private BatchIntertMapper batchIntertMapper;


    /**
     * 公司id
     */
    private String companyId;
    private Hashtable<String, JobDict> userJobHashtable = new Hashtable<String, JobDict>();
    private Hashtable<String, DeptBasicInfo> deptList;
    private List<UserBasicInfo> userList = new ArrayList<UserBasicInfo>();
    private List<UserDeptRelation> userDeptList = new ArrayList<UserDeptRelation>();
    private List<UserIdLoginAccountRelation> userAccountList = new ArrayList<UserIdLoginAccountRelation>();


    /**
     * 用户导入
     *
     * @param sheet
     */
    @Override
    public void importUser(Sheet sheet, String companyId) {

        this.companyId = companyId;

        deptList = loadDept();

        readUserData(sheet);


    }


    /**
     * 部门导入
     *
     * @param sheet
     */
    @Override
    public void importDept(Sheet sheet, String companyId) {

        Hashtable<String, DeptBasicInfo> depts = readDeptData(sheet);

        if (depts != null) {
            for (String key : depts.keySet()) {

                /**
                 * 添加部门关系表
                 */
                setDeptRelation(depts, key);

                /**
                 * 转换部门全路径
                 */
                String fullPathId = convertFullPathToId(depts, key);
                depts.get(key).setFullPath(fullPathId);

            }
            /**
             * 插入数据库
             */
            insertDept(depts);
        }

    }

    private Hashtable<String, DeptBasicInfo> loadDept() {
        List<DeptBasicInfo> list = deptBasicInfoMapper.selectByExample(null);

        Hashtable<String, DeptBasicInfo> idHashtable = new Hashtable<String, DeptBasicInfo>();

        for (DeptBasicInfo dept : list) {
            idHashtable.put(dept.getDeptId(), dept);
        }

        Hashtable<String, DeptBasicInfo> fullPathNameHashtable = new Hashtable<String, DeptBasicInfo>();

        for (String key : idHashtable.keySet()) {
            DeptBasicInfo deptBasicInfo = idHashtable.get(key);
            String fullPathName = deptBasicInfo.getFullPath();
            for (String subDeptName : fullPathName.split(",")) {
                fullPathName = fullPathName.replace(subDeptName, idHashtable.get(subDeptName).getDeptName());
            }
            fullPathNameHashtable.put(fullPathName, deptBasicInfo);
        }

        return fullPathNameHashtable;
    }


    /**
     * 插入数据库
     *
     * @param depts
     */
    private void insertDept(Hashtable<String, DeptBasicInfo> depts) {
        for (String key : depts.keySet()) {
            deptBasicInfoMapper.insert(depts.get(key));
        }
    }


    private void batchInsert(UserBasicInfo userBasicInfo) {
        userList.add(userBasicInfo);

        if (userList.size() >= 30) {
            batchIntertMapper.insertUserBatch(userList);
            userList.clear();
        }

    }

    private void batchInsert(UserDeptRelation userDeptRelation) {
        userDeptList.add(userDeptRelation);

        if (userDeptList.size() >= 30) {
            batchIntertMapper.insertUserDeptBatch(userDeptList);
            userDeptList.clear();
        }

    }

    private void batchInsert(UserIdLoginAccountRelation userAccount) {
        userAccountList.add(userAccount);

        if (userAccountList.size() >= 30) {
            batchIntertMapper.insertAccountBatch(userAccountList);
            userAccountList.clear();
        }

    }


    private List<UserBasicInfo> readUserData(Sheet sheet) {


        if (sheet != null) {

            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                /**
                 * 跳过首行
                 */
                if (i == 0) {
                    continue;
                }

                Row row = sheet.getRow(i);
                UserBasicInfo userBasicInfo = new UserBasicInfo();
                userBasicInfo.setUserId(ServiceUtil.getUUID());
                userBasicInfo.setCreateTime(new Date());
                userBasicInfo.setUpdateTime(new Date());
                userBasicInfo.setRealName(row.getCell(0).getStringCellValue());
                userBasicInfo.setNickName(row.getCell(1).getStringCellValue());
                userBasicInfo.setUserSex(row.getCell(3).getStringCellValue());
                userBasicInfo.setUserIdCard(row.getCell(4).getStringCellValue());
                if (row.getCell(5) == null) {
                    userBasicInfo.setUserEmail(null);
                } else {
                    userBasicInfo.setUserEmail(row.getCell(5).getStringCellValue());
                }
                userBasicInfo.setUserTelephone(String.valueOf(row.getCell(6).getNumericCellValue()));
                userBasicInfo.setUserMobile(row.getCell(7).getStringCellValue());
                userBasicInfo.setUserAddress(String.valueOf(row.getCell(8).getStringCellValue()));
                userBasicInfo.setStatus("1");
                userBasicInfo.setUserSource("1");

                String userName = row.getCell(2).getStringCellValue();
                String userJob = row.getCell(11).getStringCellValue();
                String deptName = row.getCell(10).getStringCellValue();

                addUserJobToList(userJob);
                addUserIdLoginAccountRelation(userBasicInfo, userName);
                addUserDeptRelation(userBasicInfo.getUserId(), deptName, userJob);

                batchInsert(userBasicInfo);

            }

            return userList;
        } else {
            return null;
        }
    }

    private void addUserIdLoginAccountRelation(UserBasicInfo userBasicInfo, String userName) {
        UserIdLoginAccountRelation userIdLoginAccountRelation = new UserIdLoginAccountRelation();
        userIdLoginAccountRelation.setStatus("1");
        userIdLoginAccountRelation.setCompanyId(companyId);
        userIdLoginAccountRelation.setUserId(userBasicInfo.getUserId());
        userIdLoginAccountRelation.setCreateTime(new Date());
        userIdLoginAccountRelation.setLoginId(ServiceUtil.getUUID());
        userIdLoginAccountRelation.setLoginName(userName);
        userIdLoginAccountRelation.setPassword("123456");
        //userIdLoginAccountRelationMapper.insert(userIdLoginAccountRelation);
        batchInsert(userIdLoginAccountRelation);
    }

    private void addUserJobToList(String userJob) {
        if (!userJobHashtable.containsKey(userJob)) {
            JobDict jobDict = new JobDict();
            jobDict.setJobId(ServiceUtil.getUUID());
            jobDict.setJobName(userJob);
            jobDict.setCompanyId(companyId);
            userJobHashtable.put(userJob, jobDict);
            jobDictMapper.insert(jobDict);
        }
    }

    private void addUserDeptRelation(String userId, String deptName, String userJob) {
        UserDeptRelation userDeptRelation = new UserDeptRelation();
        userDeptRelation.setCompanyId(companyId);
        userDeptRelation.setUserId(userId);
        userDeptRelation.setDeptId(deptList.get(deptName).getDeptId());
        userDeptRelation.setJobId(userJobHashtable.get(userJob).getJobId());
        userDeptRelation.setPositionOrder(1);
        userDeptRelation.setStatus("1");

        batchInsert(userDeptRelation);
        //userDeptRelationMapper.insert(userDeptRelation);
    }


    /**
     * 将数据从sheet中取出放入Hashtable
     *
     * @param sheet
     */

    private Hashtable<String, DeptBasicInfo> readDeptData(Sheet sheet) {

        if (sheet != null) {

            Hashtable<String, DeptBasicInfo> depts = new Hashtable<String, DeptBasicInfo>();
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                /**
                 * 跳过首行
                 */
                if (i == 0) {
                    continue;
                }

                Row row = sheet.getRow(i);

                DeptBasicInfo deptBasicInfo = new DeptBasicInfo();
                deptBasicInfo.setCreateTime(new Date());
                deptBasicInfo.setUpdateTime(new Date());
                deptBasicInfo.setCompanyId("");
                deptBasicInfo.setDeptId(ServiceUtil.getUUID());
                deptBasicInfo.setDeptName(row.getCell(0).getStringCellValue());
                deptBasicInfo.setDeptPhone(String.valueOf(row.getCell(2).getNumericCellValue()));
                if (row.getCell(3) != null) {
                    deptBasicInfo.setDeptInfo(String.valueOf(row.getCell(3).getNumericCellValue()));
                }

                deptBasicInfo.setStatus("1");
                deptBasicInfo.setIsRootDept("0");
                deptBasicInfo.setDeptSource("1");
                deptBasicInfo.setDeptOrder(1);
                String parentDept;
                if (row.getCell(4) == null) {
                    parentDept = "";
                } else {
                    parentDept = row.getCell(4).getStringCellValue();
                }
                String fullPath;
                if (deptBasicInfo.getDeptName().equals(parentDept) || parentDept == null || "".equals(parentDept.trim())) {
                    deptBasicInfo.setIsRootDept("1");
                    fullPath = deptBasicInfo.getDeptName();
                } else {
                    fullPath = parentDept + "," + deptBasicInfo.getDeptName();
                }
                depts.put(fullPath, deptBasicInfo);

            }
            return depts;
        } else {
            return null;
        }
    }


    /**
     * 找到部门关系
     *
     * @param depts
     * @param fullPath
     */
    private void setDeptRelation(Hashtable<String, DeptBasicInfo> depts, String fullPath) {
        String[] parentDepts = fullPath.split(",");
        String headPept = "";
        for (int i = 0; i < parentDepts.length; i++) {
            String parentDeptName = parentDepts[i];
            if ("".equals(headPept)) {
                headPept = parentDeptName;
            } else {
                headPept += "," + parentDeptName;
            }
            DeptRelation deptRelation = new DeptRelation();
            if (i == 0) {
                DeptBasicInfo basicInfo = depts.get(headPept);
                deptRelation.setAncestorDeptId(basicInfo.getDeptId());
            } else {
                deptRelation.setAncestorDeptId(depts.get(headPept).getDeptId());
            }
            deptRelation.setDescendantDeptId(depts.get(fullPath).getDeptId());
            deptRelation.setPathLength(parentDepts.length - 1 - i);

            /**
             * deptRelation 入库
             */
            deptRelationMapper.insert(deptRelation);
        }
    }

    /**
     * 转换全路径从名字转成id
     *
     * @param depts
     */
    private String convertFullPathToId(Hashtable<String, DeptBasicInfo> depts, String fullPath) {
        String fullPathId = "";

        String[] deptsName = fullPath.split(",");
        String subdepts = "";

        for (int i = 0; i < deptsName.length; i++) {
            if (i == 0) {
                fullPathId = depts.get(deptsName[i]).getDeptId();
                subdepts = deptsName[i];
            } else {
                subdepts += "," + deptsName[i];
                fullPathId += "," + depts.get(subdepts).getDeptId();
            }
        }

        return fullPathId;
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        String deptFile = "/Users/tiantao/work/SECCom/doc/dept2000.xlsx";
        String userFile = "/Users/tiantao/work/SECCom/doc/user10.xlsx";

        File file = new File(deptFile);

        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheet("nationsky");

            ApplicationContext ctx = new ClassPathXmlApplicationContext("./spring_config.xml");

            ImportServiceImpl importServiceImpl = (ImportServiceImpl) ctx.getBean("importServiceImpl");
            //importServiceImpl.importDept(sheet, "xxxccc");
            //importServiceImpl.importUser(sheet, "xxxccc");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start);


        file = new File(userFile);

        inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheet("nationsky");

            ApplicationContext ctx = new ClassPathXmlApplicationContext("./spring_config.xml");

            ImportServiceImpl importServiceImpl = (ImportServiceImpl) ctx.getBean("importServiceImpl");
            //importServiceImpl.importDept(sheet,"xxxccc");
            importServiceImpl.importUser(sheet, "xxxccc");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        end = System.currentTimeMillis();

        System.out.println(end - start);

    }
}
