package com.group4.group4.controller;

import com.group4.group4.dto.CategoryDTO;
import com.group4.group4.dto.CommentDTO;
import com.group4.group4.service.CategoryService;
import com.group4.group4.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class RestfulController {
    @Autowired
    CommentService commentService;

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/getCommentsByNewsId")
    private ResponseEntity<?> getCommentsByNewsId(@RequestParam(value = "id")String id){
        List<String> commentDTOList = commentService.getCommentsByNewsId(Long.valueOf(id));
        return ResponseEntity.ok().body(commentDTOList);
    }

    @GetMapping(value = "/getNewsByCategoryId")
    private ResponseEntity<?> getNewsByCategoryId(@RequestParam(value = "id")String id){
        try {
            CategoryDTO categoryDTO = categoryService.findById(Long.valueOf(id));
            return ResponseEntity.ok().body(categoryDTO);
        }catch (NumberFormatException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã danh mục không hợp lệ!");
        }
    }
}
