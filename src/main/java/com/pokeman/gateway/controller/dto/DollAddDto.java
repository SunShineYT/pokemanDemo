package com.pokeman.gateway.controller.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ycy
 * @since 2017/8/1
 */
@Data
public class DollAddDto implements Serializable {
    /**
     * 主键 Id
     * 玩偶id
     */
    private String dollId;
    /**
     * 玩偶名称
     */
    private String dollName;
    /**
     * 玩偶等级
     * 1-橙  2-紫 3-蓝
     */
    private int dollLevel;
    /**
     * 玩偶类型
     * 1-单玩偶  2-机器人
     */
    private int dollType;
    /**
     * 子玩偶集合
     */
    private List<DollAddDto> dollChildList;
}
