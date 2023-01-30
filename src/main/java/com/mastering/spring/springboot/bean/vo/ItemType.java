package com.mastering.spring.springboot.bean.vo;

import com.mastering.spring.springboot.bean.exception.EnumTypeError;

public enum ItemType {
    Requirement("需求相关要求"),
    RequirementReaction("需求相关反应"),
    Behavior("注意行为"),
    FollowDirections("听指令"),
    Acceptability("接受性"),
    AnswerNeed("回答要/不要问题"),
    AnswerYes("回答是/否"),
    AnswerWh("回答wh问题"),
    AnswerCan("回答能/不能或是会不会问题"),
    AnswerGuess("回答假设性的因应问题"),
    Response("接续他人句子"),
    Recall("回溯"),
    ActiveExpression("主动表达需求"),
    AskQuestion("wh的主动提问"),
    AskCan("主动提问能/不能或是会不会的问题");

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
