package com.qin.genesis.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 融资金额
 * @author Aaron
 * @version 1.0
 * @date 2023/10/17 23:02
 */
@Data
@Entity
@Table(name = "t_financing")
public class FinancingMoneyEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String financingMoney;
}
