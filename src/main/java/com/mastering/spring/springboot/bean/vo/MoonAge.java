package com.mastering.spring.springboot.bean.vo;

import com.mastering.spring.springboot.bean.exception.NoPreviousMoonAgeError;
import lombok.Data;

@Data
public class MoonAge {
    public static final Integer stage=6;

    public static Integer getPreviousMoonAge(Integer currentMoonAge) throws NoPreviousMoonAgeError {
        if (currentMoonAge==0){
            throw new NoPreviousMoonAgeError("没有前一级年龄段");
        }
        Integer previousAge=currentMoonAge-stage;
        previousAge=previousAge>0?previousAge:0;
        return previousAge;
    }
}
