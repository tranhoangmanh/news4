package com.group4.group4.service;

import com.group4.group4.dto.NewsDTO;
import com.group4.group4.entity.NewsEntity;
import com.group4.group4.repository.INewsRepository;
import com.group4.group4.service.base.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService implements IBaseService<NewsDTO> {
    @Autowired
    INewsRepository newsRepository;

    @Override
    public List<NewsDTO> getAll() {
        return null;
    }

    @Override
    public NewsDTO findById(long id) {
        return null;
    }

    @Override
    public boolean insert(NewsDTO obj) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean update(NewsDTO obj) {
        return false;
    }

    public NewsDTO entityToModel(NewsEntity entity){
        NewsDTO dto = new NewsDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setPreview(entity.getPreview());
        dto.setContent(entity.getContent());
        dto.setImageName(entity.getImageName());
        List<Long> IdList = entity.getCommentList().stream().map((id) -> entity.getId()).collect(Collectors.toList());
        dto.setCommentIds(IdList);
        return dto;
    }
}
