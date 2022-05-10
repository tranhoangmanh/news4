package com.group4.group4.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "COMMENT")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = NewsEntity.class)
    @JoinColumn(name = "news_id")
    private NewsEntity newsEntity;
}
