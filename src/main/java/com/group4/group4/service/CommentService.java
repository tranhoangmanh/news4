package com.group4.group4.service;

import com.group4.group4.entity.CommentEntity;
import com.group4.group4.repository.ICommentRepository;
import com.group4.group4.service.base.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements IBaseService<CommentEntity> {
    @Autowired
    ICommentRepository commentRepository;

    @Override
    public List<CommentEntity> getAll() {
        commentRepository.findAll();
        return null;
    }

    @Override
    public CommentEntity findById(long id) {
        return null;
    }

    @Override
    public boolean insert(CommentEntity obj) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean update(CommentEntity obj) {
        return false;
    }
}
