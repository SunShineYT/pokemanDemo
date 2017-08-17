package com.pokeman.sercice.doll;

import com.pokeman.gateway.controller.dto.DollAddDto;
import com.pokeman.gateway.controller.dto.DollQuery;
import com.pokeman.model.PmDollInfo;

import java.util.List;
import java.util.Map;

/**
 * @author ycy
 * @since 2017/7/31
 */
public interface IDollService {

    /**
     * 保存玩偶
     *
     * @param dollAddDto
     * @return
     */
    public Map saveDollInfo(DollAddDto dollAddDto);

    /**
     * 查询玩偶列表
     *
     * @param dollQuery
     * @return
     */
    public List<DollAddDto> findPmDollInfoList(DollQuery dollQuery);

    /**
     * 删除玩偶
     *
     * @param dollId
     * @return
     */
    public Boolean deleteDollInfoById(String dollId);
}
