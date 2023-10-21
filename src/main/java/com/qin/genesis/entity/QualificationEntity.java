package com.qin.genesis.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 企业资质
 * @author Aaron
 * @version 1.0
 * @date 2023/10/17 22:59
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_qualification")
public class QualificationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String qualificationName;
}
