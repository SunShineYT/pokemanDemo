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
 * 宠物信息表
 */

@Data
@Entity
@Alias("pmPokemanInfo")
@Table(name = "Pm_Pokeman_Info")
public class PmPokemanInfo implements Serializable{
    /**
     * 主键 Id
     * 宠物id
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @Column(name = "POKEMAN_ID")
    private String pokemanId;

    /**
     * 宠物名
     */
    @Column(name = "POKEMAN_NAME")
    private String pokemanName;
    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private String createTime;
}
