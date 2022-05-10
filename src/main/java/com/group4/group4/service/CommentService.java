package com.group4.group4.service;

import com.group4.group4.dto.CommentDTO;
import com.group4.group4.entity.CommentEntity;
import com.group4.group4.repository.ICommentRepository;
import com.group4.group4.repository.INewsRepository;
import com.group4.group4.repository.IUserRepository;
import com.group4.group4.service.base.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService implements IBaseService<CommentDTO> {
    @Autowired
    ICommentRepository commentRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    INewsRepository newsRepository;

    @Override
    public List<CommentDTO> getAll() {
        List<CommentEntity> commentEntityList = commentRepository.findAll();
        List<CommentDTO> commentDTOList = commentEntityList.stream().map((dto) -> this.entityToDto(dto)).collect(Collectors.toList());
        return commentDTOList;
    }

    @Override
    public CommentDTO findById(long id) {
        CommentEntity entity = commentRepository.findById(id);
        CommentDTO dto = this.entityToDto(entity);
        return dto;
    }

    @Override
    public boolean insert(CommentDTO obj) {
        CommentEntity entity = this.dtoToEntity(obj);
        commentRepository.save(entity);
        return true;
    }

    @Override
    public boolean delete(long id) {
        commentRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean update(CommentDTO obj) {
        commentRepository.save(this.dtoToEntity(obj));
        return true;
    }

    public CommentDTO entityToDto(CommentEntity entity){
        CommentDTO dto = new CommentDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setUserId(entity.getUserEntity().getId());
        dto.setNewsId(entity.getNewsEntity().getId());
        return dto;
    }

    public CommentEntity dtoToEntity(CommentDTO model){
        CommentEntity entity = new CommentEntity();
        entity.setId(model.getId());
        entity.setContent(model.getContent());
        entity.setUserEntity(userRepository.findById(model.getId()));
        entity.setNewsEntity(newsRepository.findById(model.getNewsId()));
        return entity;
    }

    public List<String> getCommentsByNewsId(long id){
        return this.commentRepository.getCommentsByNewsId(id);
    }
}
