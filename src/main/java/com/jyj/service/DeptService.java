package com.jyj.service;

import com.jyj.dao.SysDeptMapper;
import com.jyj.exception.ParamException;
import com.jyj.model.SysDept;
import com.jyj.param.DeptParam;
import com.jyj.util.BeanValidator;
import com.jyj.util.LevelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class DeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    public void save(DeptParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept sysDept = SysDept.builder().name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();

        sysDept.setLevel(LevelUtil.calulateLevel(getLevel(param.getParentId()), param.getParentId()));
        sysDept.setOperator("system"); //todo
        sysDept.setOperaterIp("127.0.0.1"); //todo
        sysDept.setOperaterTime(new Date());
        sysDeptMapper.insertSelective(sysDept);
    }

    private boolean checkExist(Integer parentId, String deptName, Integer deptId) {
        //todo
        return true;
    }

    private String getLevel(Integer deptId) {
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (sysDept == null) {
            return null;
        }
        return sysDept.getLevel();
    }
}
