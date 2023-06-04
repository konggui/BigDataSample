package com.example.platform.bean.dto;

import lombok.Data;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: com.example.platform.bean.dto
 * @author: aishuang
 * @date: 2023-05-16 06:03
 */
@Data
public class TagDto {
    private Long id;
    private String name;
    // 标签的规则
    private String rule;
    private Integer level;
    // 标签的父ID
    private Long pid;
}
