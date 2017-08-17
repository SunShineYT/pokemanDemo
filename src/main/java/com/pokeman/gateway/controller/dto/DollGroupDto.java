package com.pokeman.gateway.controller.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ycy
 * @since 2017/7/31
 */
@Data
public class DollGroupDto implements Serializable {

    /**
     * 玩偶组id
     */
    private String dollGroupId;
    /**
     * 玩偶组名
     */
    private String dollGroupName;

    /**
     * 玩偶id集合
     */
    private List<String> dollIdList;

}
