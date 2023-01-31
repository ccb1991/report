package com.mastering.spring.springboot.bean.report;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class FirstSheet {
    private List<Integer> NamePosition= Arrays.asList(28,5);
    private List<Integer> OrganizationPosition= Arrays.asList(7,0);
    private List<Integer> examTimePosition= Arrays.asList(33,5);
}
