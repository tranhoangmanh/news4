package com.group4.group4.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NewsDTO {
    private long id;
    private String title;
    private String preview;
    private String content;
    private String imageName;
    private List<Long> commentIds;
}
