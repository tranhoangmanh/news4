package com.group4.group4.service;

import com.group4.group4.repository.INewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    @Autowired
    INewsRepository newsRepository;
}
