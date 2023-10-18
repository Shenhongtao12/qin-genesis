package com.qin.genesis.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Aaron
 * @version 1.0
 * @date 2023/10/17 23:29
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_attachment")
public class AttachmentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String link;

//    @JsonBackReference
    @JsonIgnore
    @ManyToOne(targetEntity = EnterpriseEntity.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "enterprise_id")
    private EnterpriseEntity enterprise;
}
