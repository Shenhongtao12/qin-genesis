package com.qin.genesis.controller;

import com.qin.genesis.common.RestResponse;
import com.qin.genesis.dto.BasicConfigDTO;
import com.qin.genesis.entity.EnterpriseEntity;
import com.qin.genesis.service.IEnterpriseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aaron
 * @version 1.0
 * @date 2023/10/17 21:08
 */
@Tag(name = "科技企业服务登记")
@RestController
@RequestMapping("/api/enterprise")
public class EnterpriseController extends BaseController {

    @Autowired
    private IEnterpriseService enterpriseService;

    @Operation(summary = "获取下拉框配置")
    @GetMapping("config")
    public RestResponse<BasicConfigDTO> getConfig() {
        return SUCCESS(enterpriseService.getConfig());
    }

    @PostMapping
    public RestResponse<String> saveEnterprise(@RequestBody EnterpriseEntity enterprise) {
        enterpriseService.save(enterprise);
        return SUCCESS("success");
    }
}
