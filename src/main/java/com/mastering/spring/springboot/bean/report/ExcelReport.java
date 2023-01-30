package com.mastering.spring.springboot.bean.report;

import com.mastering.spring.springboot.bean.dto.*;
import com.mastering.spring.springboot.bean.exception.DomainTypeNotFound;
import com.mastering.spring.springboot.bean.exception.ItemTypeNotFound;
import com.mastering.spring.springboot.bean.exception.PositionTypeError;
import com.mastering.spring.springboot.bean.vo.ChildInfo;
import com.mastering.spring.springboot.bean.vo.DomainType;
import com.mastering.spring.springboot.bean.vo.ItemType;
import com.mastering.spring.springboot.bean.vo.exam.SubmitExamInfo;
import com.mastering.spring.springboot.bean.vo.score.DomainScore;
import com.mastering.spring.springboot.bean.vo.score.ItemScore;
import com.mastering.spring.springboot.bean.vo.score.Score;
import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class ExcelReport {
    private static String path = System.getProperty("java.io.tmpdir");
    private static String TempLateFile = String.format("%s%s",
            System.getProperty("user.dir"), "/source/report.xlsx");
    private String fileName;
    private List<ReportSheet> reportSheets;
    private DomainAnalyseSheet domainAnalyseSheet;
    private ContrastSheet contrastSheet;
    private String examTime = getReportTime();
    private ChildInfo childInfo;
    private Score totalScore;
    private Score currentScore;

    public ExcelReport(Score totalScore, Score currentScore,
                       SubmitExamInfo submitExamInfo, List<ReportSheet> reportSheets) {
        this.childInfo = submitExamInfo.getChildInfo();
        this.fileName = String.format("%s_%s", childInfo.getChildName(), this.examTime);
        this.reportSheets = Arrays.asList(reportSheets.get(0));
//        this.reportSheets = reportSheets;
        this.totalScore = totalScore;
        this.currentScore = currentScore;
    }

    public void writeSheet() throws IOException, DomainTypeNotFound, PositionTypeError, ItemTypeNotFound {
        FileInputStream fileInputStream = new FileInputStream(ExcelReport.TempLateFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        for (ReportSheet reportSheet:this.reportSheets){
            XSSFSheet sheet = workbook.getSheet(reportSheet.getSheetName());
            List<QuestionDomain> questionDomains = reportSheet.getQuestionDomains();
            for (QuestionDomain questionDomain:questionDomains){
                List<DomainPosition> domainPositionList=questionDomain.getDomainPositionList();
                DomainType domainType= DomainType.valueOf(questionDomain.getDomain());
                // 记录领域分数
                DomainScore currentDomainScore= currentScore.getDomainScoreByDomain(domainType);
                DomainScore totalDomainScore=totalScore.getDomainScoreByDomain(domainType);
                for (DomainPosition domainPosition:domainPositionList){
                    Cell cell=sheet.getRow(domainPosition.getRow()).
                            getCell(domainPosition.getCol());
                    if (domainPosition.getPositionType().equals("0")){
                        cell.setCellValue(totalDomainScore.getScore());
                    } else if (domainPosition.getPositionType().equals("1")){
                        cell.setCellValue(currentDomainScore.getScore());
                    } else{
                        throw new PositionTypeError("位置类型错误");
                    }

                }
                //记录大项分数
                for (QuestionItem questionItem:questionDomain.getQuestionItems()){
                    List<ItemPosition> itemPositionList=questionItem.getItemPositionList();
                    ItemType itemType=ItemType.valueOf(questionItem.getItem());
                    ItemScore currentItemScore=this.currentScore.getItemSocreByItemType(itemType);
                    ItemScore totalItemScore=this.totalScore.getItemSocreByItemType(itemType);
                    for (ItemPosition itemPosition:itemPositionList){
                        Cell cell=sheet.getRow(itemPosition.getRow()).
                                getCell(itemPosition.getCol());
                        if (itemPosition.getPositionType().equals("0")){
                            cell.setCellValue(totalItemScore.getScore());
                        }else if (itemPosition.getPositionType().equals("1")){
                            cell.setCellValue(currentItemScore.getScore());
                        } else{
                            throw new PositionTypeError("位置类型错误");
                        }
                    }
                }
            }
        }
//        XSSFSheet sheet = workbook.getSheet("A、0-12沟通.图");
//        XSSFRow row = sheet.getRow(6);
//        String value = row.getCell(0).getStringCellValue();
//        XSSFCell cell = row.getCell(1);
//        cell.setCellValue(2);
        FileOutputStream out = new FileOutputStream(String.format(
                "%s\\%s", path, fileName));
        workbook.write(out);
        out.close();
        fileInputStream.close();
    }

    public String getReportTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

    public static void main(String[] args) {
        String curDir = System.getProperty("java.io.tmpdir");
        System.out.println("你当前的工作目录为 :" + curDir);
    }
}
