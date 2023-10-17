package com.qin.genesis.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 企业服务登记表
 * @author Aaron
 * @version 1.0
 * @date 2023/10/17 21:09
 */
@Data
@Entity
@Table(name = "t_enterprise", indexes = {
        @Index(name = "idx_userId", columnList = "userId"),
})
public class EnterpriseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;

    private String enterpriseName;

    private String contacts;

    private String phone;

    private String universityName;

    private String domainName;

    @OneToMany(targetEntity = AttachmentEntity.class, mappedBy = "enterprise", cascade = CascadeType.ALL)
    private List<AttachmentEntity> attachmentList;

    /**
     * 企业经营信息
     */
    private Integer employeesNumber;

    private Double halfYearRevenue;

    private Double halfYearProfit;

    private String qualificationName;

    private Double lastFinancingMoney;

    @Column(length = 2000)
    private String businessDescription;

    @Column(length = 2000)
    private String teamIntroduction;


    /**
     * 需求登记
     */
    
}
