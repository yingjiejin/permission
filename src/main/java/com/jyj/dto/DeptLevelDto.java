package com.jyj.dto;

import com.google.common.collect.Lists;
import com.jyj.model.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Setter
@Getter
@ToString
public class DeptLevelDto extends SysDept {

    private List<DeptLevelDto> deptLevelDtoList = Lists.newArrayList();

    public static DeptLevelDto adapt(SysDept sysDept) {
        DeptLevelDto deptLevelDto = new DeptLevelDto();
        BeanUtils.copyProperties(sysDept, deptLevelDto);
        return deptLevelDto;
    }

}
