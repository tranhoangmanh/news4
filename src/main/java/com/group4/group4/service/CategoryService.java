package com.group4.group4.service;

import com.group4.group4.dto.CategoryDTO;
import com.group4.group4.dto.NewsDTO;
import com.group4.group4.entity.CategoryEntity;
import com.group4.group4.entity.NewsEntity;
import com.group4.group4.repository.ICategoryRepository;
import com.group4.group4.repository.INewsRepository;
import com.group4.group4.service.base.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements IBaseService<CategoryDTO> {
    @Autowired
    ICategoryRepository categoryRepository;

    @Autowired
    INewsRepository newsRepository;

    @Autowired
    NewsService newsService;

    @Override
    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll().stream().map((cat) -> this.entityToDto(cat)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(long id) {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(id);
        if(categoryEntityOptional.isPresent()){
            return this.entityToDto(categoryEntityOptional.get());
        }
        return null;
    }

    @Override
    public boolean insert(CategoryDTO obj) {
        if(this.findById(obj.getId()) == null){
            categoryRepository.save(this.dtoToEntity(obj));
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        if(this.findById(id) != null){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(CategoryDTO obj) {
        if(this.findById(obj.getId()) != null){
            categoryRepository.save(this.dtoToEntity(obj));
            return true;
        }
        return false;
    }

    public CategoryDTO entityToDto(CategoryEntity entity){
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        List<NewsDTO> newsDTOList = new ArrayList<>();
        List<NewsEntity> newsEntityList = entity.getNewsEntityList();
        newsDTOList = newsEntityList.stream().map((newsEntity) -> newsService.entityToDto(newsEntity)).collect(Collectors.toList());
        dto.setNewsDTOList(newsDTOList);
        return dto;
    }

    public CategoryEntity dtoToEntity(CategoryDTO dto){
        CategoryEntity entity = new CategoryEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setNewsEntityList(
                dto.getNewsDTOList().stream().map((newsDto) -> newsRepository.findById(newsDto.getId()))
                        .collect(Collectors.toList())
        );
        return entity;
    }
}
