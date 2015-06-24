package com.nationsky.seccom.uc.service.implement;

import com.nationsky.seccom.uc.dao.ExtensionMapper;
import com.nationsky.seccom.uc.model.Extension;
import com.nationsky.seccom.uc.model.ExtensionExample;
import com.nationsky.seccom.uc.service.IExtensionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by tiantao on 15-6-18.
 */
public class ExtensionServiceImpl implements IExtensionService {

    @Autowired
    private ExtensionMapper extensionMapper;


    /**
     * 添加扩展字段信息
     *
     * @param extension
     * @return
     */
    @Override
    public boolean addExtension(Extension extension) {
        return extensionMapper.insert(extension) > 0;
    }

    /**
     * 根据ID删除扩展字段信息
     *
     * @param extensionId
     * @return
     */
    @Override
    public boolean deleteExtensionById(String extensionId) {
        return extensionMapper.deleteByPrimaryKey(extensionId) > 0;
    }

    /**
     * 更新扩展字段信息
     *
     * @param extension
     * @return
     */
    @Override
    public boolean updateExtension(Extension extension) {
        return extensionMapper.updateByPrimaryKey(extension) > 0;
    }

    /**
     * 根据Id查询扩展字段信息详情
     *
     * @param extensionId
     * @return
     */
    @Override
    public Extension getExtensionById(String extensionId) {
        return extensionMapper.selectByPrimaryKey(extensionId);
    }

    /**
     * 根据条件查询扩展字段信息列表
     *
     * @param example
     * @return
     */
    @Override
    public List<Extension> findList(ExtensionExample example) {
        return extensionMapper.selectByExample(example);
    }

    /**
     * 根据条件查询列表记录数
     *
     * @param example
     * @return
     */
    @Override
    public int countList(ExtensionExample example) {
        return extensionMapper.countByExample(example);
    }
}
