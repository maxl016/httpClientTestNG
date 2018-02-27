package com.jason.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jason.framework.constants.constant;
import com.jason.framework.exception.NestedBusinessException;

public class ExcelReader {
    private String filePath;
    private String sheetName;
    private Workbook workBook;    
    private Sheet sheet;
    private List<String> columnHeaderList;
    private List<List<String>> listData;
    private List<Map<String,String>> mapData;
    private boolean flag;
    private static Map<String, String> map = new HashMap<String, String>();
    private static 	SimpleLogger log = SimpleLogger.getLogger(ExcelReader.class);
    
    public ExcelReader(String filePath, String sheetName) {
        this.filePath = filePath;
        this.sheetName = sheetName;
        this.flag = false;
        this.load();
    }    

    private void load() {
        FileInputStream inStream = null;
        try {
            inStream = new FileInputStream(new File(filePath));
            workBook = WorkbookFactory.create(inStream);
            sheet = workBook.getSheet(sheetName);            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(inStream!=null){
                    inStream.close();
                }                
            } catch (IOException e) {                
                e.printStackTrace();
            }
        }
    }
    
    public static List<Map<String, String>> getExcelToList(String xlsxPath, String sheetName) {

        XSSFWorkbook xssfWorkbook=null;
        try {
        	if (!xlsxPath.endsWith(".xlsx")) {
				throw new NestedBusinessException("请检查文件名是否正确！");
			}
        	InputStream is = new FileInputStream(xlsxPath);
        	xssfWorkbook = new XSSFWorkbook(is);
        } catch (IOException e) {
            throw new NestedBusinessException("系统找不到文件异常！", e);
        }
        // 循环工作表Sheet
            XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);
            
            if (null == xssfSheet) {
				throw new NestedBusinessException("请检查sheet页是否存在！");
			}
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            // 循环行Row
            XSSFRow rowTitleRow =xssfSheet.getRow(0);
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) {
                    continue;
                }
                Map<String, String> map = new HashMap<String, String>();
                // 循环列Cell
                for (int cellNum = 0; cellNum <rowTitleRow.getLastCellNum(); cellNum++) {
                    XSSFCell xssfCell = xssfRow.getCell(cellNum);
                    XSSFCell xssfCellTitleCell = rowTitleRow.getCell(cellNum);
                    map.put(getCellValue(xssfCellTitleCell), getCellValue(xssfCell));
                }
                list.add(map);
            }
            return list;
    }

    private static String getCellValue(Cell cell) {
        String cellValue = "";
        DataFormatter formatter = new DataFormatter();
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = formatter.formatCellValue(cell);
                    } else {
                        double value = cell.getNumericCellValue();
                        int intValue = (int) value;
                        cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    cellValue = String.valueOf(cell.getCellFormula());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    cellValue = "";
                    break;
                case Cell.CELL_TYPE_ERROR:
                    cellValue = "";
                    break;
                default:
                    cellValue = cell.toString().trim();
                    break;
            }
        }
        return cellValue.trim();
    }
    
    private List<List<String>> getSheetData() {
        listData = new ArrayList<List<String>>();
        mapData = new ArrayList<Map<String, String>>();    
        columnHeaderList = new ArrayList<String>();
        int numOfRows = sheet.getLastRowNum() + 1;
        for (int i = 0; i < numOfRows; i++) {
            Row row = sheet.getRow(i);
            Map<String, String> map = new HashMap<String, String>();
            List<String> list = new ArrayList<String>();
            if (row != null) {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (i == 0){
                        columnHeaderList.add(getCellValue(cell));
                    }
                    else{                        
                        map.put(columnHeaderList.get(j), getCellValue(cell));
                    }
                    list.add(getCellValue(cell));
                }
            }
            if (i > 0){
                mapData.add(map);
            }
            listData.add(list);
        }
        flag = true;
        
        return listData;
    }
    
    public String getCellData(int row, int col){
        if(row<=0 || col<=0){
            return null;
        }
        if(!flag){
            this.getSheetData();
        }        
        if(listData.size()>=row && listData.get(row-1).size()>=col){
            return listData.get(row-1).get(col-1);
        }else{
            return null;
        }
    }
    
    public String getCellData(int row, String headerName){
        if(row<=0){
            return null;
        }
        if(!flag){
            this.getSheetData();
        }        
        if(mapData.size()>=row && mapData.get(row-1).containsKey(headerName)){
            return mapData.get(row-1).get(headerName);
        }else{
            return null;
        }
    }
    
    public static Map<String, String> getDataByValue(String excelPath, String sheetName, String vaule){
 	   List<Map<String, String>> list =ExcelReader.getExcelToList(excelPath,sheetName);
 	   for (Map<String, String> data : list) {
 		   if (data.containsValue(vaule)) {
 			   map = data;
 		   }
 		}
 	   return map;
    }
}