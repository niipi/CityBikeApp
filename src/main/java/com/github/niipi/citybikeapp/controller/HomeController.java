package com.github.niipi.citybikeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
       return "index.html";
    }
}
