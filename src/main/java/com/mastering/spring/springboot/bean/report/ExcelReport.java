package com.mastering.spring.springboot.bean.report;

import com.mastering.spring.springboot.bean.vo.ChildInfo;
import com.mastering.spring.springboot.bean.vo.exam.SubmitExamInfo;
import com.mastering.spring.springboot.bean.vo.score.Score;
import lombok.Data;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
public class ExcelReport {
    private static String path=System.getProperty("java.io.tmpdir");
    private static String TempLateFile=String.format("%s%s",
            System.getProperty("user.dir"),"/source/report.xlsx");
    private String fileName;
    private List<DomainSheet> sheetList;
    private DomainAnalyseSheet domainAnalyseSheet;
    private ContrastSheet contrastSheet;
    private String examTime=getReportTime();
    private ChildInfo childInfo;

    public ExcelReport(Score totalScore, Score currentScore,
                       SubmitExamInfo submitExamInfo){
        this.childInfo=submitExamInfo.getChildInfo();
    }

    public void writeSheet() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(ExcelReport.TempLateFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet("A、0-12沟通.图");
        XSSFRow row = sheet.getRow(6);
        String value=row.getCell(0).getStringCellValue();
        XSSFCell cell=row.getCell(1);
        cell.setCellValue(2);
        FileOutputStream out = new FileOutputStream(String.format(
                "%s\\%s\\%s",path,childInfo.getChildName(),this.examTime));
        workbook.write(out);
        out.close();
        fileInputStream.close();
    }

    public String getReportTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

    public static void main(String[] args){
        String curDir =System.getProperty("java.io.tmpdir") ;
        System.out.println("你当前的工作目录为 :" + curDir);
    }
}
