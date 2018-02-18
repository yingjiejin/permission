package com.jyj.controller;

import com.jyj.common.JsonData;
import com.jyj.param.DeptParam;
import com.jyj.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {

    @Resource
    private DeptService deptService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(DeptParam param) {
        deptService.save(param);
        return JsonData.success();
    }
}
