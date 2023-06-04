package com.example.platform.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: com.example.platform.bean.dto
 * @author: aishuang
 * @date: 2023-05-16 06:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagModelDto {
    private TagDto tag;
    private ModelDto model;
}
