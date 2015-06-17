package com.nationsky.seccom.uc.service;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * Created by tiantao on 15-6-10.
 */
public interface IimportService {

    /**
     * 用户导入
     * @param sheet
     */
    public void importUser(Sheet sheet, String companyId);


    /**
     * 部门导入
     * @param sheet
     */
    public void importDept(Sheet sheet, String companyId);
}
