package com.qin.genesis.repository;

import com.qin.genesis.entity.FinancingMoneyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Aaron
 * @version 1.0
 * @date 2023/10/17 21:15
 */
public interface FinancingMoneyRepository extends JpaRepository<FinancingMoneyEntity, Integer>, JpaSpecificationExecutor<FinancingMoneyEntity> {


}
