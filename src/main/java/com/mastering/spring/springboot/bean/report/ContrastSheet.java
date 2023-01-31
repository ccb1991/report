package com.mastering.spring.springboot.bean.report;

import lombok.Data;

@Data
public class ContrastSheet {
    private int[][] communicatePosition=new int[][]{{6,1},{6,2},{6,3},{6,4}};
    private int[][] SocialemotionPosition=new int[][]{{7,1},{7,2},{7,3},{7,4}};
    private int[][] cognitionPosition=new int[][]{{8,1},{8,2},{8,3},{8,4}};
    private int[][] behaviorPosition =new int[][]{{9,1},{9,2},{9,3},{9,4}};
    private int[][] movementPosition=new int[][]{{10,1},{10,2},{10,3},{10,4}};
}
