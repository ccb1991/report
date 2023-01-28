package com.mastering.spring.springboot.bean.vo;

import com.mastering.spring.springboot.bean.dto.QuestionItem;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDomainVo {
    private String id;
    private String domain;
    private String domainName;
    private List<QuestionItem> questionItem;
}
