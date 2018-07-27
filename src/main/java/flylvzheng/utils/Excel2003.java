//package flylvzheng.utils;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.imageio.ImageIO;
//
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
//import org.apache.poi.hssf.usermodel.HSSFPatriarch;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.CellRangeAddress;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
//
//
//public class Excel2003 {
//	private static final String EXTENSION_XLS = "xls";
//    private static final String EXTENSION_XLSX = "xlsx";
//    private Workbook workbook = null;
//    private HSSFSheet sheet =null;
//    public int lineNum = 0;
//    private String filePath;
//    
//    /**
//     * 取单元格的值
//     * @param cell 单元格对象
//     * @param treatAsStr 为true时，当做文本来取值 (取到的是文本，不会把“1”取成“1.0”)
//     * @return
//     */
//    private String getCellValue(Cell cell, boolean treatAsStr) {
//        if (cell == null) {
//            return "";
//        }
//
//        if (treatAsStr) {
//            // 虽然excel中设置的都是文本，但是数字文本还被读错，如“1”取成“1.0”
//            // 加上下面这句，临时把它当做文本来读取
//            cell.setCellType(Cell.CELL_TYPE_STRING);
//        }
//
//        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
//            return String.valueOf(cell.getBooleanCellValue());
//        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
//            return String.valueOf(cell.getNumericCellValue());
//        } else {
//            return String.valueOf(cell.getStringCellValue());
//        }
//    }
//
//    /**
//     * 读取excel文件内容
//     * @param filePath
//     * return <sheet<List<Map>>>
//     * @throws FileNotFoundException
//     * @throws FileFormatException
//     */
//    public Map readExcel(String filePath) throws Exception {
//        // 检查
//    	 File file = new File(filePath);
//         if (!file.exists()) {
//             throw new Exception("传入的文件不存在：" + filePath);
//         }
//
//         if (!(filePath.endsWith(EXTENSION_XLS) || filePath.endsWith(EXTENSION_XLSX))) {
//             throw new Exception("传入的文件不是excel");
//         }
//        // 获取workbook对象
//        Map<String, List<String>> map = new HashMap<String, List<String>>();
//
//        try {
//        	InputStream is = new FileInputStream(filePath);
//            if (filePath.endsWith(EXTENSION_XLS)) {
//                workbook = new HSSFWorkbook(is);
//            } else if (filePath.endsWith(EXTENSION_XLSX)) {
//                workbook = new XSSFWorkbook(is);
//            }
//            // 读文件 一个sheet一个sheet地读取
//           // for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
//            	try {
//            		Sheet sheet = workbook.getSheetAt(0);
//	                if (sheet == null) {
//	                    return null;
//	                }
//	
//	                int firstRowIndex = sheet.getFirstRowNum();
//	                int lastRowIndex = sheet.getLastRowNum();
//	
//	                // 读取首行 即,表头
//	                List<String> titles = new ArrayList<String>();//标题
//	                Row firstRow = sheet.getRow(firstRowIndex);
//                
//					for (int i = firstRow.getFirstCellNum(); i <= firstRow.getLastCellNum(); i++) {
//					    Cell cell = firstRow.getCell(i);
//					    String cellValue = this.getCellValue(cell, true);
//					   // if(cellValue!=null && cellValue.length()>0)
//					    titles.add(cellValue);
//					}
//					map.put("titles", titles);
//					
//
//	                // 读取数据行
//					  List<String> values = new ArrayList<String>();//内容
//	                for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
//	                    Row currentRow = sheet.getRow(rowIndex);// 当前行
//	                    Map<String, String> co = new HashMap<String, String>();
//	                    int firstColumnIndex = currentRow.getFirstCellNum(); // 首列
//	                    int lastColumnIndex = currentRow.getLastCellNum();// 最后一列
//	                    
//	                    String str = "";
//	                    for (int columnIndex = firstColumnIndex; columnIndex <= lastColumnIndex; columnIndex++) {
//	                        Cell currentCell = currentRow.getCell(columnIndex);// 当前单元格
//	                        String currentCellValue = this.getCellValue(currentCell, true);// 当前单元格的值
//	                       // if(currentCellValue!=null && currentCellValue.length()>0)
//	                        	//co.put(coName.get(columnIndex), currentCellValue);
//	                        str += currentCellValue+"###";
//	                    }
//	                    if(str.length()>0){
//	                    	str = str.substring(0, str.length()-3);
//	                    } 
//	                    values.add(str);
//	                }
//	                
//	                map.put("values", values);
//            	} catch (Exception e) {
//					e.printStackTrace();
//				}
//           // }
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return map;
//    }
//    
//    public void closedObject(){
//    	if (workbook != null) {
//            try {
//                workbook.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    
//    ////////////////////////////////////////////////////////////////
//    
//
//    public void openWorkbook(String filePath,String fileType,String title,int num) throws Exception{
//    	this.filePath = filePath;
//    	File file = new File(filePath);
//	    //创建工作文档对象   
//	    if (!file.exists()) {
//	        if (filePath.endsWith(EXTENSION_XLS)) {
//	            workbook = new HSSFWorkbook();
//	        } else if (filePath.endsWith(EXTENSION_XLSX)) {
//	            workbook = new XSSFWorkbook();
//	        }
//	        //创建sheet对象   
//	        sheet = (HSSFSheet) workbook.createSheet("sheet1");  
//	        
//	    } else {
//	        if (fileType.equals("xls")) {  
//	        	workbook = new HSSFWorkbook();  
//	            
//	        } else if(fileType.equals("xlsx")) { 
//	        	workbook = new XSSFWorkbook();  
//	        } 
//	    }
//	     //创建sheet对象   
//	    if (sheet==null) {
//	        sheet = (HSSFSheet) workbook.createSheet("sheet1");  
//	    }
//	  //添加表头  
//        Row row = sheet.createRow(lineNum);
//        Cell cell = row.createCell(0);
//        row.setHeight((short) 540); 
//        cell.setCellValue(title);    //创建第一行    
//        
//        CellStyle style = workbook.createCellStyle(); // 样式对象      
//        // 设置单元格的背景颜色为淡蓝色  
//        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index); 
//        
//        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直      
//        style.setAlignment(CellStyle.ALIGN_CENTER);// 水平   
//        style.setWrapText(true);// 指定当单元格内容显示不下时自动换行
//        org.apache.poi.ss.usermodel.Font font = workbook.createFont();   
//        font.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);  
//        font.setFontName("宋体");  
//        font.setFontHeight((short) 280);  
//        style.setFont(font);
//        
//        cell.setCellStyle(style); // 样式，居中
//         
//        // 单元格合并      
//        // 四个参数分别是：起始行，起始列，结束行，结束列      
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, num));  
//        sheet.autoSizeColumn(5200);
//        lineNum ++;
//        
//        OutputStream outputStream = new FileOutputStream(file);
//        workbook.write(outputStream);
//        outputStream.flush();
//        outputStream.close();
//        
//    }
//    
//   
//    
//    
//    public void addTable(List<String> titleList,
//			List<String> columnList,List<Map> dataList){
//    	Row row = sheet.createRow(lineNum);
//    	CellStyle style = workbook.createCellStyle(); // 样式对象     
//    	style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中 
//    	style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
//        style.setWrapText(true);// 指定当单元格内容显示不下时自动换行
//        
//     // 背景色
//        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
//        style.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
//
//        // 设置边框
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
//        
//        
//        org.apache.poi.ss.usermodel.Font font = workbook.createFont();   
//        font.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);  
//        font.setFontName("宋体");  
//        font.setFontHeight((short) 200);  
//        style.setFont(font);
//        
//        
//    	for(int i = 0;i < titleList.size();i++){  
//        	Cell cell = row.createCell(i);  
//            cell.setCellValue(titleList.get(i));  
//            cell.setCellStyle(style); // 样式，居中
//            
//            sheet.setColumnWidth(i, 20 * 256); 
//        }  
//        row.setHeight((short) 540); 
//        lineNum ++;
//        
//      //循环写入行数据   
//        
//        CellStyle style2 = workbook.createCellStyle(); // 样式对象      
//        // 设置单元格的背景颜色为淡蓝色  
//        style2.setFillForegroundColor(HSSFColor.PALE_BLUE.index); 
//        
//        style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直      
//        style2.setAlignment(CellStyle.ALIGN_CENTER);// 水平   
//        style2.setWrapText(true);// 指定当单元格内容显示不下时自动换行
//        
//        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
//        
//        org.apache.poi.ss.usermodel.Font font1 = workbook.createFont();   
//        font1.setFontName("宋体");  
//        font1.setFontHeight((short) 190);  
//        style2.setFont(font1);
//        
//        for (int i = 0; i < dataList.size(); i++) {  
//        	Map map = dataList.get(i);
//            row = (Row) sheet.createRow(lineNum);  
//            row.setHeight((short) 500); 
//            for(int j=0;j<columnList.size();j++){
//            	String key = columnList.get(j);
//            	try {
//            		Cell ce = row.createCell(j); 
//            		ce.setCellValue(map.get(key).toString());
//                    ce.setCellStyle(style2);
//				} catch (Exception e) {
//					row.createCell(j).setCellValue("");
//				}
//            }
//            lineNum++;
//        }
//         
//        try {
//			OutputStream outputStream = new FileOutputStream(this.filePath);
//			workbook.write(outputStream);
//			outputStream.flush();
//			outputStream.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    }
//    
//    public void addImg(String path,int startWidth,int endWidth,int startHeight,int endHeight){
//		try {
//			BufferedImage bufferImg = null; 
//			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();     
//			bufferImg = ImageIO.read(new File(path));     
//			ImageIO.write(bufferImg, "jpg", byteArrayOut); 
//			//画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）  
//            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();     
//            //anchor主要用于设置图片的属性  
//            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255,(short) startWidth, startHeight, (short) endWidth, endHeight);     
//            anchor.setAnchorType(3);     
//            //插入图片    
//            patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));   
//            
//			OutputStream outputStream = new FileOutputStream(this.filePath);
//			workbook.write(outputStream);
//			outputStream.flush();
//			outputStream.close();
//			
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
// 
//    
//   /* public static void main(String[] args) throws Exception{
//    	Excel2003 re = new Excel2003();
//    	Map<String,List<String>> result = re.readExcel("E:\\petcare\\assetManage\\c#\\11.xlsx");
//    	List<String> titles = result.get("titles");
//    	List<String> values = result.get("values");
//	       for(int i=0;i<titles.size();i++) {
//	    	   System.out.println(titles.get(i)+"***");
//	       }
//	       
//	       
//	       for(int i=0;i<values.size();i++) {
//	    	   System.out.println(values.get(i));
//	       }
//    	
//        String title = "北京艺美装饰检查结果统计报表";  
//        
//        List<String> titleList = new ArrayList();
//		List<String> columnList = new ArrayList();
//		List<Map> dataList = new ArrayList();
//		titleList.add("编号");titleList.add("资产名称");titleList.add("单价");titleList.add("结果");titleList.add("日期");titleList.add("时间");
//		columnList.add("id");columnList.add("assetname");columnList.add("danjia");columnList.add("result");columnList.add("date");columnList.add("time");
//		for(int i=0;i<5;i++){
//			Map m = new HashMap();
//			m.put("id", "00"+i);
//			m.put("assetname", "电脑");
//			m.put("danjia", "1200");
//			m.put("result", "已查");
//			m.put("date", "2017-11-23");
//			m.put("time", "12:30:00");
//			dataList.add(m);
//		}
//       
//        Excel2003 excel = new Excel2003();
//        excel.openWorkbook("E:/112233.xls","xls",title,titleList.size()-1);
//        excel.addTable(titleList,columnList,dataList);
//        excel.lineNum += 2;
//        excel.addImg("e:/bing.jpg",0,4,excel.lineNum,excel.lineNum+20);
//        excel.lineNum += 24;
//        excel.addImg("e:/bing.jpg",0,4,excel.lineNum,excel.lineNum+20);
//        //excel.addImg("e:/bing.jpg",4);
//        excel.closedObject();
//	}*/
//    
//    
//    
//}
