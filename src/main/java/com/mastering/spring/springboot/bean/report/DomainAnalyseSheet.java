package com.mastering.spring.springboot.bean.report;

import lombok.Data;

@Data
public class DomainAnalyseSheet {
    private int[][] communicatePosition=new int[][]{{6,1},{6,2}};
    private int[][] SocialemotionPosition=new int[][]{{7,1},{7,2}};
    private int[][] cognitionPosition=new int[][]{{8,1},{8,2}};
    private int[][] behaviorPosition =new int[][]{{9,1},{9,2}};
    private int[][] movementPosition=new int[][]{{10,1},{10,2}};
}
