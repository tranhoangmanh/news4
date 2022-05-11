package com.group4.group4.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryEntity", fetch = FetchType.EAGER)
    private List<NewsEntity> newsEntityList;
}
