package com.qin.genesis.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 企业服务登记表
 * @author Aaron
 * @version 1.0
 * @date 2023/10/17 21:09
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_enterprise", indexes = {
        @Index(name = "idx_userId", columnList = "userId"),
})
public class EnterpriseEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;

    private String enterpriseName;

    private String contacts;

    private String phone;

    @Column(length = 500)
    private String universityName;

    @Column(length = 500)
    private String domainName;

//    @JsonManagedReference
    @OneToMany(targetEntity = AttachmentEntity.class, mappedBy = "enterprise", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<AttachmentEntity> attachmentList;

    /**
     * 企业经营信息
     */
    private Integer employeesNumber;

    private Double halfYearRevenue;

    private Double halfYearProfit;

    @Column(length = 1000)
    private String qualificationName;

    private Double lastFinancingMoney;

    @Column(length = 2000)
    private String businessDescription;

    @Column(length = 2000)
    private String teamIntroduction;


    /**
     * 需求登记
     */
    @Column(length = 500, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String industryAndMarketDemand;

    @Column(length = 500)
    private String spacePolicyDemand;

    @Column(length = 500)
    private String investDemand;

    @Column(length = 500)
    private String manpowerDemand;

    @Column(length = 500)
    private String enterpriseOperation;

    @Column(length = 500)
    private String deepIncubationDemand;

    @Column(length = 1000)
    private String memo;
}
