package com.pokeman.gateway.controller.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ycy
 * @since 2017/7/27
 */
@Data
public class DollQuery implements Serializable{

    /**
     * 玩偶名称
     */
    private String dollName;
    /**
     * 玩偶等级
     * -1:全部 1-橙  2-紫 3-蓝
     */
    private int dollLevel;
    /**
     * 玩偶类型
     * -1：全部 1-单玩偶  2-组合玩偶
     */
    private int dollType;
}
