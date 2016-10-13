package com.absurd.web;

import com.absurd.security.SecurityRole;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/10/11.
 */
@Controller
@RequestMapping(value = "/api")
public class IndexController {
    private final Log logger = LogFactory.getLog(this.getClass());
    @PreAuthorize(value = "hasAuthority('FOO_READ_PRIVILEGE')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Integer findOne(@PathVariable("id") final Long id) {
        return 1;
    }

    @RequestMapping(value = "/loginvaliad")
    public void login() {
    }

}
