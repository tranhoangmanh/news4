package com.group4.group4.repository;

import com.group4.group4.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<CommentEntity, Long> {
}
