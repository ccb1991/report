package com.mastering.spring.springboot.bean.report;

import com.mastering.spring.springboot.bean.dto.DomainPosition;
import com.mastering.spring.springboot.bean.dto.ItemPosition;
import lombok.Data;

import java.util.List;

@Data
public class DomainSheet {
    private final String name;
    private List<DomainPosition> domainPositionList;
    private List<ItemPosition> domainScoreList;
}
