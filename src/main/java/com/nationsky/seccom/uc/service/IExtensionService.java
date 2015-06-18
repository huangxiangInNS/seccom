package com.nationsky.seccom.uc.service;

import com.nationsky.seccom.uc.model.Extension;
import com.nationsky.seccom.uc.model.ExtensionExample;

import java.util.List;

/**
 * Created by tiantao on 15-6-18.
 */
public interface IExtensionService {

    /**
     * 添加扩展字段信息
     * @param extension
     * @return
     */
    boolean addExtension(Extension extension);

    /**
     * 根据ID删除扩展字段信息
     * @param extensionId
     * @return
     */
    boolean deleteExtensionById(String extensionId);


    /**
     * 更新扩展字段信息
     * @param extension
     * @return
     */
    boolean updateExtension(Extension extension);


    /**
     * 根据Id查询扩展字段信息详情
     * @param extensionId
     * @return
     */
    Extension getExtensionById(String extensionId);


    /**
     * 根据条件查询扩展字段信息列表
     * @param example
     * @return
     */
    List<Extension> findList(ExtensionExample example);


    /**
     * 根据条件查询列表记录数
     * @param example
     * @return
     */
    int countList(ExtensionExample example);

}
