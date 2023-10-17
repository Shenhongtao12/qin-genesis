package com.qin.genesis.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Aaron
 * @version 1.0
 * @date 2023/10/17 23:29
 */
@Data
@Entity
@Table(name = "t_attachment")
public class AttachmentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String link;

    @ManyToOne(targetEntity = EnterpriseEntity.class, optional = false)
    @JoinColumn(name = "enterprise_id")
    private EnterpriseEntity enterprise;
}
