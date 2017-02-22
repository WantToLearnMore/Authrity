package com.Auth.service.Impl;

import com.Auth.service.ExcelService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Jiangtenglong on 2016/8/17.
 */

@Service
public class ExcelServiceImpl implements ExcelService {


    public void exoprtExcel( List<Map<String,Object>> info,HttpServletResponse response,String[] header) {


        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet0");
        try {
            outputHeaders(header,sheet);
            outputColumns(header,info,sheet,1);
            ServletOutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importExcel() {

    }


    //生成Excel模板
    public void exportExcelByTemplate(String filePath,String  templatePath) {

        File file =new File(filePath);
        SAXBuilder  saxBuilder =new SAXBuilder();
        try {
            //解析xml文件
            Document document= saxBuilder.build(file);
            //创建工作目录    Excel文件
            HSSFWorkbook hssfWorkbook =new HSSFWorkbook();

            HSSFSheet sheet =hssfWorkbook.createSheet("Sheet0");

            //获取xml的根节点
            Element root =document.getRootElement();

            //获取创建的模板的名字
            String excelName=root.getAttribute("name").getValue();

            int rowNum=0;
            int column=0;

            //设置列宽
            Element colgroup =root.getChild("colgroup");
            setColumWidth(sheet,colgroup);

            //设置标题
            Element title=root.getChild("title");
            List<Element> trs=title.getChildren();
            for(int  i=0;i<trs.size();i++){
                Element tr=trs.get(i);

                List<Element>tds=tr.getChildren("td");
                HSSFRow hssfRow =sheet.createRow(rowNum);
                HSSFCellStyle cellStyle =hssfWorkbook.createCellStyle();
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

                for(column=0;column<tds.size();column++){
                Element td =tds.get(column);
                    HSSFCell cell =hssfRow.createCell(column);
                    Attribute rowSpan=td.getAttribute("rowspan");
                    Attribute colSpan=td.getAttribute("colspan");
                    Attribute value =td.getAttribute("value");
                    // 合并单元格 居中
                 if(value!=null){
                      String val =value.getValue();
                      cell.setCellValue(val);
                      int rspn=rowSpan.getIntValue()-1;
                      int cspan=colSpan.getIntValue()-1;

                     //设置字体
                      HSSFFont hssfFont=hssfWorkbook.createFont();
                      hssfFont.setFontName("仿宋_GB2312");
                      hssfFont.setBold(true);
                      hssfFont.setFontHeightInPoints((short)12);
                      cellStyle.setFont(hssfFont);
                      cell.setCellStyle(cellStyle);

                      sheet.addMergedRegion(new CellRangeAddress(rspn,rspn,0,cspan));
                  }
                }
                rowNum++;

            }
       //设置表头
            Element thead=root.getChild("thead");
            trs=thead.getChildren("tr");
            for (int i=0;i<trs.size();i++)
            {
                Element tr=trs.get(i);
                List<Element> ths=tr.getChildren("th");
                System.out.println("th:"+ths.size());
                HSSFRow row =sheet.createRow(rowNum);
               for(column=0;column<ths.size();column++){
                   Element th=ths.get(column);
                   Attribute valueAttr= th.getAttribute("value");
                   System.out.println("value"+th.getAttribute("value"));
                   HSSFCell cell=row.createCell(column);
                   if(valueAttr!=null){
                    String value=valueAttr.getValue();
                    cell.setCellValue(value);
                   }
               }
              rowNum++;

            }
       //设置数据格式
            Element tbody =root.getChild("tbody");
            Element tr=tbody.getChild("tr");
            System.out.println("repeat:"+tr.getAttribute("repeat"));
            int  repeat =tr.getAttribute("repeat").getIntValue();//默认初始化的行数

            List<Element>tds=tr.getChildren("td");
            for(int i=0;i<repeat;i++){
                HSSFRow row  =sheet.createRow(rowNum);//创建一行
                for(column=0;column<tds.size();column++){
                   Element td=tds.get(column);
                    HSSFCell  cell=row.createCell(column);//创建一个单元格
                    setType(hssfWorkbook,cell,td);
                }
                rowNum++;
            }


          //生成Excel模板

            File  file1 =new File(filePath+"//"+excelName+".xls");
            file1.delete();
            file1.createNewFile();
            FileOutputStream stream  = FileUtils.openOutputStream(file1);
            hssfWorkbook.write(stream);
            stream.close();

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private   void outputHeaders(String[] headersInfo,HSSFSheet sheet ){
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headersInfo.length; i++) {
            sheet.setColumnWidth(i, 4000);
            row.createCell(i).setCellValue(headersInfo[i]);
        }
    }

    private   void outputColumns(String[] headersInfo,
                                 List<Map<String,Object>> columnsInfo,HSSFSheet sheet,int rowIndex ){
        HSSFRow row ;
        int headerSize = headersInfo.length;
        int columnSize = columnsInfo.size();
        //循环插入多少行
        for (int i = 0; i < columnsInfo.size(); i++) {
            row = sheet.createRow(rowIndex+i);
            Map map = columnsInfo.get(i);
            //循环每行多少列
            for (int j = 0; j < headersInfo.length; j++) {
                row.createCell(j).setCellValue(map.get(headersInfo[j]).toString());
            }
        }

    }

    //设置ecxel列宽
    private   void  setColumWidth(HSSFSheet sheet,Element colgroup){
       List  <Element> elements=colgroup.getChildren();

        for(int i=0;i<elements.size();i++){
              Element col=elements.get(i);

            Attribute width=col.getAttribute("width");

            String unit=width.getValue().replaceAll("[0-9,//.]","");
            String value=width.getValue().replaceAll(unit,"");
            int v=0;
            if(StringUtils.isBlank(unit)||"px".endsWith(unit)){
               v=Math.round(Float.parseFloat(value)*37F);
            }else  if("em".endsWith(unit)){
                v=Math.round(Float.parseFloat(value)*267.5F);
            }
            sheet.setColumnWidth(i,v);
        }
    }

    private  void  setType(HSSFWorkbook  workbook,HSSFCell cell,Element  element){
        //获取这个属性
        Attribute   attribute  =element.getAttribute("type");
        String  type  =attribute.getValue();
        HSSFDataFormat format  =workbook.createDataFormat();
        HSSFCellStyle cellStyle=workbook.createCellStyle();
        if("STRING".equalsIgnoreCase(type)){
            cell.setCellValue("");
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellStyle.setDataFormat(format.getFormat("@"));
        }else if("NUMERIC".equalsIgnoreCase(type)){
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            Attribute  formatAttr=element.getAttribute("format");
            String  formatValue=formatAttr.getValue();
            formatValue=StringUtils.isBlank(formatValue)?formatValue:"2012000001";
            cellStyle.setDataFormat(format.getFormat(formatValue));
        }else if("DATE".equalsIgnoreCase(type)){
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cellStyle.setDataFormat(format.getFormat("yyyy-m-d"));
        }else if("ENUM".equalsIgnoreCase(type)){
            CellRangeAddressList regions =
                    new CellRangeAddressList(cell.getRowIndex(), cell.getRowIndex(),
                            cell.getColumnIndex(), cell.getColumnIndex());
            Attribute enumAttr = element.getAttribute("format");
            String enumValue = enumAttr.getValue();
            //加载下拉列表内容
            DVConstraint constraint =
                    DVConstraint.createExplicitListConstraint(enumValue.split(","));
            //数据有效性对象
            HSSFDataValidation dataValidation = new HSSFDataValidation(regions, constraint);
            workbook.getSheetAt(0).addValidationData(dataValidation);
        }
         cell.setCellStyle(cellStyle);

    }

    private    List<Map<String,Object>> getInfo(String fileName){

        List<Map<String ,Object>> info=null;


        return  info;
    }

}
