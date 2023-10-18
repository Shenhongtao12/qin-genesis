package com.qin.genesis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qin.genesis.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(name = "BasicConfigDTO", description = "通用下拉框配置")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicConfigDTO {

    /**
     * 高校
     */
    @Schema(description = "高校配置")
    private List<UniversityEntity> universityList;

    /**
     * 企业资质
     */
    @Schema(description = "企业资质")
    private List<QualificationEntity> qualificationList;

    /**
     * 融资金额
     */
    @Schema(description = "融资金额")
    private List<FinancingMoneyEntity> financingMoneyList;

    /**
     * 领域
     */
    @Schema(description = "领域")
    private List<DomainEntity> domainList;


    /**
     * 产业与市场需求
     */
    @Schema(description = "产业与市场需求")
    private List<DemandEntity> industryAndMarketDemandList;

    /**
     * 空间政策需求
     */
    @Schema(description = "空间政策需求")
    private List<DemandEntity> spacePolicyDemandList;

    /**
     * 投融资需求
     */
    @Schema(description = "投融资需求")
    private List<DemandEntity> investDemandList;

    /**
     * 人力资源需求
     */
    @Schema(description = "人力资源需求")
    private List<DemandEntity> manpowerDemandList;

    /**
     * 企业运营需求
     */
    @Schema(description = "企业运营需求")
    private List<DemandEntity> enterpriseOperationDemandList;

    /**
     * 深度孵化
     */
    @Schema(description = "深度孵化")
    private List<DemandEntity> deepIncubationDemandList;
}
