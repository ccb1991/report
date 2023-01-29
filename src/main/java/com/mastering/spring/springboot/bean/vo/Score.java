package com.mastering.spring.springboot.bean.vo;

import lombok.Data;

import java.lang.ref.PhantomReference;
import java.util.List;

@Data
public class Score {
    private List<DomainScore> domainScores;
    private List<ItemScore> itemScores;
}
