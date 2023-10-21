package com.qin.genesis.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 领域
 * @author Aaron
 * @version 1.0
 * @date 2023/10/17 22:58
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_domain")
public class DomainEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String domainName;
}
