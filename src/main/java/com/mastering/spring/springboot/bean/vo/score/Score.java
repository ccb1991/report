package com.mastering.spring.springboot.bean.vo.score;

import com.mastering.spring.springboot.bean.dto.QuestionDomain;
import com.mastering.spring.springboot.bean.dto.QuestionItem;
import com.mastering.spring.springboot.bean.exception.DomainTypeNotFound;
import com.mastering.spring.springboot.bean.exception.ItemTypeNotFound;
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

    public Score(List<QuestionDomain> questionDomains,
                 List<QuestionItem> questionItems){
        questionDomains.forEach(q -> {
            domainScores.add(new DomainScore(
                    DomainType.valueOf(q.getDomain())));
        });
        questionItems.forEach(i -> {
            System.out.println(i.getItem());
            itemScores.add(new ItemScore(
                    ItemType.valueOf(i.getItem())));
        });
    }

    public void updateDomainScore(DomainScore domainScore){
        int index = this.domainScores.indexOf(domainScore);
        DomainScore d=this.domainScores.get(index);
        d.setScore(domainScore.getScore()+d.getScore());
    }

    public void updateItemScore(ItemScore itemScore){
        int index=this.itemScores.indexOf(itemScore);
        ItemScore i=this.itemScores.get(index);
        i.setScore(itemScore.getScore()+i.getScore());
    }

    public DomainScore getDomainScoreByDomain(DomainType domainType)
            throws DomainTypeNotFound {
        for(DomainScore domainScore : this.domainScores){
            if (domainScore.getDomainType().equals(domainType)){
                return domainScore;
            }
        }
        throw new DomainTypeNotFound(
                String.format("未找到领域%s关联分数",domainType.name()));
    }

    public ItemScore getItemSocreByItemType(ItemType itemType)
            throws DomainTypeNotFound, ItemTypeNotFound {
        for(ItemScore itemScore : this.itemScores){
            if (itemScore.getItemType().equals(itemType)){
                return itemScore;
            }
        }
        throw new ItemTypeNotFound(
                String.format("未找到大项%s关联分数",itemType.name()));
    }
}
