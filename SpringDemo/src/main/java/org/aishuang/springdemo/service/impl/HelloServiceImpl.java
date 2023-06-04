package org.aishuang.springdemo.service.impl;

import org.aishuang.springdemo.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: org.aishuang.springdemo.service.impl
 * @author: aishuang
 * @date: 2023-04-15 06:17
 */
@Service
public class HelloServiceImpl implements HelloService {


    @Override
    public String getPerson(String number) {
        String name = "";
        switch (number) {
            case "1":
                name = "number =" + number + "; name = " + "tom";
                break;
            case "2":
                name = "number =" + number + "; name = " + "tony";
                break;
            case "3":
                name = "number =" + number + "; name = " + "jack";
                break;
            default:
                name = "number =" + number + "; name = " + "chen";
        }
        return name;
    }
}
