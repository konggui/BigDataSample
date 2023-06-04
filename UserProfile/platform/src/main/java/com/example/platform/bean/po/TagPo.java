package com.example.platform.bean.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: com.example.platform.bean.po
 * @author: aishuang
 * @date: 2023-05-16 05:50
 */
@Data
@Entity(name = "tbl_basic_tag")
public class TagPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String rule;
    private Integer level;
    private Long pid;

    private Date ctime;
    private Date utime;
}
