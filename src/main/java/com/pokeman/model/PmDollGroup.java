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
 * 玩偶组
 */
@Data
@Entity
@Alias("pmDollGroup")
@Table(name = "Pm_Doll_Group")
public class PmDollGroup implements Serializable{

    /**
     * 主键 Id
     * 玩偶组id
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @Column(name = "DOLL_GROUP_ID")
    private String dollGroupId;
    /**
     * 玩偶组名（突破等级名）
     */
    @Column(name = "DOLL_GROUP_NAME")
    private String dollGroupName;
    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private String createTime;
}
