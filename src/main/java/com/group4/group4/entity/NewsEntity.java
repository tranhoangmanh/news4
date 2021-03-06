package com.group4.group4.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "NEWS")
@Entity
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "preview")
    private String preview;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "newsEntity")
    private List<CommentEntity> commentList;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CategoryEntity categoryEntity;
}
