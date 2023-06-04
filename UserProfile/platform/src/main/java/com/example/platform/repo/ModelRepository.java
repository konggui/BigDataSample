package com.example.platform.repo;

import com.example.platform.bean.po.ModelPo;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: com.example.platform.repo
 * @author: aishuang
 * @date: 2023-05-16 05:44
 */
public interface ModelRepository {
    ModelPo findByTagId(Long tagId);
}
