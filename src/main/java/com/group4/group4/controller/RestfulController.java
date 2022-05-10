package com.group4.group4.controller;

import com.group4.group4.dto.CommentDTO;
import com.group4.group4.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/getAll")
    private ResponseEntity<?> getAll(@RequestParam(value = "id")String id){
        List<String> commentDTOList = commentService.getCommentsByNewsId(Long.valueOf(id));
        return ResponseEntity.ok().body(commentDTOList);
    }
}
