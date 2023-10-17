package com.qin.genesis.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 需求
 * @author Aaron
 * @version 1.0
 * @date 2023/10/17 23:04
 */
@Data
@Entity
@Table(name = "t_demand")
public class DemandEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String category;

    private String subCategory;

    private String demandName;
}
