package com.group4.group4.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    @ManyToMany(mappedBy = "roleEntityList")
    private List<UserEntity> userEntityList;

}
