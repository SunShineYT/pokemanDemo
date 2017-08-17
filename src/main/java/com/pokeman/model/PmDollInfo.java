package com.pokeman.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ycy
 * @since 2017/7/18
 * 玩偶表
 */
@Data
@Entity
@Alias("pmDollInfo")
@Table(name = "PM_DOLL_INFO")
public class PmDollInfo implements Serializable{

    /**
     * 主键 Id
     * 玩偶id
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @Column(name = "DI_DOLL_ID")
    private String dollId;
    /**
     * 玩偶名称
     */
    @Column(name = "DI_DOLL_NAME")
    private String dollName;
    /**
     * 玩偶等级
     * 1-橙  2-紫 3-蓝
     */
    @Column(name = "DI_DOLL_LEVEL")
    private int dollLevel;
    /**
     * 玩偶类型
     * 1-单玩偶  2-机器人
     */
    @Column(name = "DI_DOLL_TYPE")
    private int dollType;
    /**
     * 子玩偶id
     * 暂时无用
     */
    @Column(name = "DI_DOLL_CHILD")
    private String dollChild;
    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private String createTime;
}
