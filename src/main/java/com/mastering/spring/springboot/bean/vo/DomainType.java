package com.mastering.spring.springboot.bean.vo;

import com.mastering.spring.springboot.bean.exception.EnumTypeError;

public enum DomainType {
    ListenerLanguage("听者语言"),
    NotLanguage("非语言沟通"),
    AnswerQuestion("说者-回答问题"),
    ActiveExpression("说者-主动表达"),
    Sentencepattern("句型与文法"),
    InteractiveLanguage("交互式语言"),
    Socialemotion("非语言社会情绪"),
    Emotionalbehavior("情绪行为"),
    Playbehavior("游戏行为"),
    Theorymind("心智理论"),
    Socialskills("社交技能-人际互动"),
    Responsibility("社交技能-责任感"),
    Problemsolving("社交技能-问题解决"),
    Perceptualdevelopment("知觉发展"),
    Name("命名"),
    Mathematics("数学"),
    Chinese("基本语文"),
    Readingcomprehension("阅读理解"),
    Logicalreasoning("逻辑推理"),
    Eat("食"),
    Dress("穿衣"),
    Tidyup("打扮/整理"),
    Toilet("如厕行为"),
    Housework("家事"),
    Traffic("交通"),
    Community("社区休闲"),
    Grossmovement("粗大动作"),
    Finemovement("精细动作"),
    Selfmanagement("自我管理");

    private String name;

    DomainType(String name){
        this.name=name;
    }

    public DomainType getDomain(String name) throws EnumTypeError {
        switch (name){
            case "listening":
                return DomainType.valueOf(name);
            default:
                throw new EnumTypeError("领域类型错误");
        }
    }

    @Override
    public String toString() {
        return this.name();
    }
}
