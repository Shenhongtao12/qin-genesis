package com.qin.genesis.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 需求
 * @author Aaron
 * @version 1.0
 * @date 2023/10/17 23:04
 */
@Data
@Entity
@Table(name = "t_demand")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemandEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String category;

    private String subCategory;

    private String demandName;

    @Transient
    private List<DemandEntity> demandList;

}
