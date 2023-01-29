package com.mastering.spring.springboot.bean.vo;

import com.mastering.spring.springboot.bean.exception.DomainTypeError;

public enum DomainType {
    listening("听者语言"),
    NotLanguage("非语言沟通");

    private String name;

    DomainType(String name){
        this.name=name;
    }

    public DomainType getDomain(String name) throws DomainTypeError {
        switch (name){
            case "listening":
                return DomainType.valueOf(name);
            default:
                throw new DomainTypeError("领域类型错误");
        }
    }
}
