package com.group4.group4.repository;

import com.group4.group4.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INewsRepository extends JpaRepository<NewsEntity, Long> {
}
