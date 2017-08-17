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
 * 宠物__玩偶组关联表
 */

@Data
@Entity
@Alias("pmPokemanDollGroupRel")
@Table(name = "Pm_Pokeman_Doll_Group_Rel")
public class PmPokemanDollGroupRel implements Serializable{
    /**
     * 主键 Id
     *
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @Column(name = "PDGR_ID")
    private String pdgrId;
    /**
     * 宠物id
     */
    @Column(name = "PDGR_POKEMAN_ID")
    private String pokemanId;
    /**
     * 玩偶组id
     */
    @Column(name = "PDGR_DOLL_GROUP_ID")
    private String dollGroupId;
    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private String createTime;
}
