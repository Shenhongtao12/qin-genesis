package com.qin.genesis.service.impl;

import com.qin.genesis.common.Constant;
import com.qin.genesis.dto.BasicConfigDTO;
import com.qin.genesis.entity.AttachmentEntity;
import com.qin.genesis.entity.DemandEntity;
import com.qin.genesis.entity.DomainEntity;
import com.qin.genesis.entity.EnterpriseEntity;
import com.qin.genesis.repository.*;
import com.qin.genesis.service.IEnterpriseService;
import com.qin.genesis.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aaron
 * @version 1.0
 * @date 2023/10/17 21:13
 */
@Service
public class EnterpriseServiceImpl implements IEnterpriseService {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private DemandRepository demandRepository;

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private FinancingMoneyRepository financingMoneyRepository;

    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private EntityManager entityManager;


    @Override
    public BasicConfigDTO getConfig() {
        BasicConfigDTO basicConfigDTO = BasicConfigDTO.builder()
                .domainList(domainRepository.findAll())
                .financingMoneyList(financingMoneyRepository.findAll())
                .qualificationList(qualificationRepository.findAll())
                .universityList(universityRepository.findAll()).build();

        demandRepository.findAll().stream().collect(Collectors.groupingBy(DemandEntity::getCategory)).forEach((key, value) -> {
            List<DemandEntity> demandEntities = new ArrayList<>();
            value.stream().collect(Collectors.groupingBy(DemandEntity::getSubCategory)).forEach((subKey, demandList) -> {
                DemandEntity demandEntity = new DemandEntity();
                demandEntity.setCategory(key);
                demandEntity.setSubCategory(subKey);
                demandEntity.setDemandList(demandList);
                demandEntities.add(demandEntity);
            });
            if (StringUtil.trimEqualsIgnoreCase(Constant.INDUSTRY_AND_MARKET, key)) {
                basicConfigDTO.setIndustryAndMarketDemandList(demandEntities);
            } else if (StringUtil.trimEqualsIgnoreCase(Constant.SPACE_POLICY, key)) {
                basicConfigDTO.setSpacePolicyDemandList(demandEntities);
            } else if (StringUtil.trimEqualsIgnoreCase(Constant.INVEST, key)) {
                basicConfigDTO.setInvestDemandList(demandEntities);
            } else if (StringUtil.trimEqualsIgnoreCase(Constant.MANPOWER, key)) {
                basicConfigDTO.setManpowerDemandList(demandEntities);
            } else if (StringUtil.trimEqualsIgnoreCase(Constant.ENTERPRISE_OPERATION, key)) {
                basicConfigDTO.setEnterpriseOperationDemandList(demandEntities);
            } else if (StringUtil.trimEqualsIgnoreCase(Constant.DEEP_INCUBATION, key)) {
                basicConfigDTO.setDeepIncubationDemandList(demandEntities);
            }
        });
        return basicConfigDTO;
    }

    @Override
    public void save(EnterpriseEntity enterprise) {
        for (AttachmentEntity attachmentEntity : enterprise.getAttachmentList()) {
            attachmentEntity.setEnterprise(enterprise);
        }
        enterpriseRepository.save(enterprise);
    }

    @Override
    public void criteriaBuilder() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<EnterpriseEntity> enterpriseRoot = criteriaQuery.from(EnterpriseEntity.class);
        Join<EnterpriseEntity, AttachmentEntity> attachmentRoot = enterpriseRoot.join("enterprise_id");

        criteriaQuery.multiselect(enterpriseRoot, attachmentRoot).where(criteriaBuilder.equal(enterpriseRoot.get("id"), 3));
        List<Tuple> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        for (Tuple tuple : resultList) {

        }
    }
}
