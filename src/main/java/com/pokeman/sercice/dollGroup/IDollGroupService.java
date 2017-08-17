package com.pokeman.sercice.dollGroup;

import com.pokeman.gateway.controller.dto.DollGroupDto;
import com.pokeman.model.PmDollGroup;

import java.util.List;

/**
 * @author ycy
 * @since 2017/7/31
 */
public interface IDollGroupService {

    /**
     * 保存玩偶组（含添加玩偶）
     *
     * @param dollGroupDto
     * @return
     */
    public String saveDollGroup(DollGroupDto dollGroupDto);


    /**
     * 查询玩偶组列表
     *
     * @param dollGroupName
     * @return
     */
    public List<PmDollGroup> findDollGroupList(String dollGroupName);

    /**
     * 删除玩偶组
     *
     * @param dollGroupId
     * @return
     */
    public Boolean deleteDollGroupById(String dollGroupId);
}
