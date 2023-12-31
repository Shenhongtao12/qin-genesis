package com.qin.genesis.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 高校
 * @author Aaron
 * @version 1.0
 * @date 2023/10/17 22:01
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_university")
public class UniversityEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String universityName;
}
