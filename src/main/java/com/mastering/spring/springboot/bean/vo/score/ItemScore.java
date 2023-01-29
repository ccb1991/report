package com.mastering.spring.springboot.bean.vo.score;

import com.mastering.spring.springboot.bean.vo.ItemType;
import lombok.Data;

import java.util.Objects;

@Data
public class ItemScore {
    private Integer score=0;
    private ItemType itemType;

    public ItemScore(){}

    public ItemScore(ItemType itemType){
        this.itemType=itemType;
    }

    public ItemScore(Integer score, ItemType itemType){
        this.score = score;
        this.itemType=itemType;
    }

    public void setItemType(String itemType){
        this.itemType=ItemType.valueOf(itemType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemScore itemScore = (ItemScore) o;
        return itemType == itemScore.itemType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemType);
    }
}
