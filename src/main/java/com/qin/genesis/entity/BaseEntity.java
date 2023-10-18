package com.qin.genesis.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Aaron
 * @version 1.0
 * @date 2023/10/17 22:02
 */
@Data
public class BaseEntity {

    private String inUser;

    private LocalDateTime inDate;

    private String lastEditUser;

    private LocalDateTime lastEditDate;
}
