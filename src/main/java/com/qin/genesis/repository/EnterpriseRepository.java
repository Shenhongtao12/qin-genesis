package com.qin.genesis.repository;

import com.qin.genesis.entity.EnterpriseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Aaron
 * @version 1.0
 * @date 2023/10/17 21:15
 */
public interface EnterpriseRepository extends JpaRepository<EnterpriseEntity, Integer>, JpaSpecificationExecutor<EnterpriseEntity> {
    @Query("select e from EnterpriseEntity e where e.universityName = ?1 and e.qualificationName = ?2")
    EnterpriseEntity test(String universityName, String qualificationName);

    List<EnterpriseEntity> findByUserId(String userId);
}
