package com.mastering.spring.springboot.bean.vo.score;

import com.mastering.spring.springboot.bean.vo.DomainType;
import com.mastering.spring.springboot.bean.vo.ItemType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Score {
    private List<DomainScore> domainScores=new ArrayList<>();
    private List<ItemScore> itemScores=new ArrayList<>();

    public Score(){}

    public Score(List<DomainType> domainTypes,
                 List<ItemType> itemTypes){
        domainTypes.forEach(domainType -> {
            domainScores.add(new DomainScore(domainType));
        });
        itemTypes.forEach(i -> {
            itemScores.add(new ItemScore(i));
        });
    }

    public void updateDomainScore(DomainScore domainScore){

    }

    public void updateItemScore(ItemScore itemScore){

    }
}
