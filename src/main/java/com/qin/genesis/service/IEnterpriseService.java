package com.qin.genesis.service;

import com.qin.genesis.dto.BasicConfigDTO;
import com.qin.genesis.entity.EnterpriseEntity;

/**
 * @author Aaron
 * @version 1.0
 * @date 2023/10/17 21:12
 */
public interface IEnterpriseService {

    BasicConfigDTO getConfig();

    void save(EnterpriseEntity enterprise);
}
