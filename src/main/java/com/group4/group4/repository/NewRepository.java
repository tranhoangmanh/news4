package com.group4.group4.repository;

import com.group4.group4.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewRepository extends JpaRepository<NewsEntity,Long> {
    @Query("SELECT n FROM NewsEntity n WHERE " +
            "n.title LIKE CONCAT('%',:query, '%')" +
            "Or n.preview LIKE CONCAT('%', :query, '%')")
    List<NewsEntity> searchNewsEntitys(String query);
}
