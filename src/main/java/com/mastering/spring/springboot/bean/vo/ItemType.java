package com.mastering.spring.springboot.bean.vo;

import com.mastering.spring.springboot.bean.exception.EnumTypeError;

public enum ItemType {
    Requirement("需求相关要求"),
    RequirementReaction("需求相关反应");

    private String name;

    ItemType(String name){
        this.name=name;
    }

    public ItemType getItemType(String name) throws EnumTypeError {
        switch (name){
            case "Requirement":
                return ItemType.valueOf(name);
            default:
                throw new EnumTypeError("大项类型错误");
        }
    }
}
