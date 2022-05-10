package com.group4.group4.repository;

import com.group4.group4.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICommentRepository extends JpaRepository<CommentEntity, Long> {
    CommentEntity findById(long id);

    @Query("SELECT c.content FROM CommentEntity c WHERE c.newsEntity.id = :id")
    List<String> getCommentsByNewsId(@Param("id") Long id);
}
