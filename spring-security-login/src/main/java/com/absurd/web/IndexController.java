package com.absurd.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/10/11.
 */
@Controller
@RequestMapping(value = "/api")
public class IndexController {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Integer findOne(@PathVariable("id") final Long id) {
        return 1;
    }
}
