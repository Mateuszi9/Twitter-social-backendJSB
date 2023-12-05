package com.mati.twittersocial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping()
    public String homeControllerHandler() {
        return "thie is home controller";
    }

    @GetMapping("/home")
    public String homeControllerHandler2() {
        return "thie is home controller2";
    }


}
