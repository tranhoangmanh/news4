package com.group4.group4.controller;

import com.group4.group4.entity.NewsEntity;
import com.group4.group4.repository.NewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*@RestController
@RequestMapping( "/news")*/
@RestController
@RequestMapping(value = "/api/v2")
public class NewsController {
    @Autowired
    private NewRepository newRepository;
    //create news
    @PostMapping
    public ResponseEntity<NewsEntity> createNews(@RequestBody NewsEntity newsEntity) {
        return new ResponseEntity<>(newRepository.save(newsEntity), HttpStatus.OK);
    }
    //get news
    @GetMapping(value = "/getAllNew")
    public ResponseEntity<List<NewsEntity>> getAllNews() {
        return new ResponseEntity<>(newRepository.findAll(), HttpStatus.OK);
    }
    //get detail news
    @GetMapping("detail/{id}")
    public ResponseEntity<NewsEntity> getNewsEntity(@PathVariable Long id) {
        Optional<NewsEntity> NewsEntityOptional = newRepository.findById(id);
        return NewsEntityOptional.map(NewsEntity -> new ResponseEntity<>(NewsEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //update news
    @PutMapping("/{id}")
    public ResponseEntity<NewsEntity> updateNewsEntity(@PathVariable Long id, @RequestBody NewsEntity NewsEntity) {
        Optional<NewsEntity> NewsEntityOptional = newRepository.findById(id);
        return NewsEntityOptional.map(NewsEntity1 -> {
            NewsEntity.setId(NewsEntity1.getId());
            return new ResponseEntity<>(newRepository.save(NewsEntity), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //delete news
    @DeleteMapping("/{id}")
    public ResponseEntity<NewsEntity> deleteNewsEntity(@PathVariable Long id) {
        Optional<NewsEntity> newsEntity = newRepository.findById(id);
        return newsEntity.map(NewsEntity -> {
            newRepository.deleteById(id);
            return new ResponseEntity<>(NewsEntity, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //search news
    @GetMapping("/search")
    public ResponseEntity<List<NewsEntity>> searchNewsEntity(@RequestParam("query") String query){
        return ResponseEntity.ok(newRepository.searchNewsEntitys(query));
    }
}
