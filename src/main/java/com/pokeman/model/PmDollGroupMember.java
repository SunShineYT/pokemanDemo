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
 * 玩偶组成员
 */
@Data
@Entity
@Alias("pmDollGroupMember")
@Table(name = "Pm_Doll_Group_Member")
public class PmDollGroupMember implements Serializable {

    /**
     * 主键 Id
     *
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @Column(name = "DGM_ID")
    private String dgmId;
    /**
     * 玩偶组id/机器人id
     * 对应 type
     */
    @Column(name = "DGM_DOLL_GROUP_ID")
    private String dollGroupId;
    /**
     * 玩偶id
     */
    @Column(name = "DGM_DOLL_ID")
    private String dollId;

    /**
     *  类型： 1： 组  2：机器人
     */
    @Column(name = "DGM_TYPE")
    private int type;
    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private String createTime;
}
