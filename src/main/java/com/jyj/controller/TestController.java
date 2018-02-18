package com.jyj.controller;

import com.jyj.common.ApplicationContextHelper;
import com.jyj.common.JsonData;
import com.jyj.dao.SysAclModuleMapper;
import com.jyj.exception.ParamException;
import com.jyj.exception.PermissionException;
import com.jyj.model.SysAclModule;
import com.jyj.param.TestVo;
import com.jyj.util.BeanValidator;
import com.jyj.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        log.info("hello");
        throw new PermissionException("test exception");
//        return JsonData.success("hello permission");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVo vo) throws ParamException {
        log.info("validate");
        SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
        SysAclModule module = moduleMapper.selectByPrimaryKey(1);
        log.info(JsonMapper.json2String(module));
        BeanValidator.check(vo);
        return JsonData.success("test validate");
    }

}
