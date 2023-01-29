package com.mastering.spring.springboot.bean.vo;

import lombok.Data;

@Data
public class ItemScore {
    private Integer score;
    private ItemType itemType;

    public ItemScore(){}

    public ItemScore(Integer score, ItemType itemType){
        this.score = score;
        this.itemType=itemType;
    }

    public void setItemType(String itemType){
        this.itemType=ItemType.valueOf(itemType);
    }
}
