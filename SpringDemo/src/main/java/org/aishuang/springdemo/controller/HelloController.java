package org.aishuang.springdemo.controller;

import org.aishuang.springdemo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: org.aishuang.springdemo.controller
 * @author: aishuang
 * @date: 2023-04-15 06:16
 */
@Controller
@RequestMapping(value ="/hello")
public class HelloController {
    @Autowired
    private HelloService helloService;

    // http://localhost:8081/hello/getPerson?number=2
    @RequestMapping(value = "/getPerson")
    @ResponseBody
    public String getPerson(String number) {
        new Thread() {
            int i =1;
            @Override
            public void run() {
                super.run();
                System.out.println(i++);
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        String person = helloService.getPerson(number);
        return person;
    }
}
