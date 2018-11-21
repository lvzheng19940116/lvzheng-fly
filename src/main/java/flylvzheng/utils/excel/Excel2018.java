//package flylvzheng.utils;
//
//import java.awt.image.BufferedImage;
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.imageio.ImageIO;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.CellRangeAddress;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
//import org.apache.poi.xssf.usermodel.XSSFDrawing;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class Excel2018 {
//	private Workbook workbook = null;
//	private XSSFSheet sheet = null;
//	public int lineNum = 0;
//
//	public void openWorkbook(String title, int num) throws Exception {
//		// 创建工作文档对象
//		workbook = new XSSFWorkbook();
//		sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook.createSheet("sheet1");
//		// 添加表头
//		Row row = sheet.createRow(lineNum);
//		Cell cell = row.createCell(0);
//		row.setHeight((short) 540);
//		cell.setCellValue(title); // 创建第一行
//
//		CellStyle style = workbook.createCellStyle(); // 样式对象
//		// 设置单元格的背景颜色为淡蓝色
//		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
//
//		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直
//		style.setAlignment(CellStyle.ALIGN_CENTER);// 水平
//		style.setWrapText(true);// 指定当单元格内容显示不下时自动换行
//		org.apache.poi.ss.usermodel.Font font = workbook.createFont();
//		font.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
//		font.setFontName("宋体");
//		font.setFontHeight((short) 280);
//		style.setFont(font);
//
//		cell.setCellStyle(style); // 样式，居中
//
//		// 单元格合并
//		// 四个参数分别是：起始行，起始列，结束行，结束列
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, num));
//		sheet.autoSizeColumn(5200);
//		lineNum++;
//	}
//
//	public void addTable(List<String> titleList, List<String> columnList, List<Map> dataList) {
//		Row row = sheet.createRow(lineNum);
//		CellStyle style = workbook.createCellStyle(); // 样式对象
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
//		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
//		style.setWrapText(true);// 指定当单元格内容显示不下时自动换行
//
//		// 背景色
//		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
//		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		style.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
//
//		// 设置边框
//		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//
//		org.apache.poi.ss.usermodel.Font font = workbook.createFont();
//		font.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
//		font.setFontName("宋体");
//		font.setFontHeight((short) 200);
//		style.setFont(font);
//
//		for (int i = 0; i < titleList.size(); i++) {
//			Cell cell = row.createCell(i);
//			cell.setCellValue(titleList.get(i));
//			cell.setCellStyle(style); // 样式，居中
//
//			sheet.setColumnWidth(i, 20 * 256);
//		}
//		row.setHeight((short) 540);
//		lineNum++;
//
//		// 循环写入行数据
//
//		CellStyle style2 = workbook.createCellStyle(); // 样式对象
//		// 设置单元格的背景颜色为淡蓝色
//		style2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
//
//		style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直
//		style2.setAlignment(CellStyle.ALIGN_CENTER);// 水平
//		style2.setWrapText(true);// 指定当单元格内容显示不下时自动换行
//
//		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//
//		org.apache.poi.ss.usermodel.Font font1 = workbook.createFont();
//		font1.setFontName("宋体");
//		font1.setFontHeight((short) 190);
//		style2.setFont(font1);
//
//		for (int i = 0; i < dataList.size(); i++) {
//			Map map = dataList.get(i);
//			row = (Row) sheet.createRow(lineNum);
//			row.setHeight((short) 500);
//			for (int j = 0; j < columnList.size(); j++) {
//				String key = columnList.get(j);
//				try {
//					Cell ce = row.createCell(j);
//					ce.setCellValue(map.get(key).toString());
//					ce.setCellStyle(style2);
//				} catch (Exception e) {
//					row.createCell(j).setCellValue("");
//				}
//			}
//			lineNum++;
//		}
//	}
//
//	public void addImg(String path, int startWidth, int endWidth, int startHeight, int endHeight) {
//		try {
//			BufferedImage bufferImg = null;
//			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
//			bufferImg = ImageIO.read(new File(path));
//			ImageIO.write(bufferImg, "jpg", byteArrayOut);
//			// 画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
//			XSSFDrawing patriarch = sheet.createDrawingPatriarch();
//			// anchor主要用于设置图片的属性
//			XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 255, 255, (short) startWidth, startHeight,
//					(short) endWidth, endHeight);
//			anchor.setAnchorType(3);
//			// 插入图片
//			patriarch.createPicture(anchor,
//					workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void closedObject() {
//		if (workbook != null) {
//			try {
//				workbook.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public void downLoad(HttpServletResponse response, String t) {
//		response.reset();
//		ByteArrayOutputStream os = new ByteArrayOutputStream();
//		try {
//			workbook.write(os);
//			workbook.close();
//			byte[] content = os.toByteArray();
//			InputStream is = new ByteArrayInputStream(content);
//			// 设置response参数，可以打开下载页面
//			response.reset();
//
//			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
//			response.setHeader("Content-Disposition",
//					"attachment;filename=" + new String((t + ".xlsx").getBytes(), "iso-8859-1"));
//			response.setContentLength(content.length);
//			ServletOutputStream outputStream = response.getOutputStream();
//			BufferedInputStream bis = new BufferedInputStream(is);
//			BufferedOutputStream bos = new BufferedOutputStream(outputStream);
//			byte[] buff = new byte[8192];
//			int bytesRead;
//			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
//				bos.write(buff, 0, bytesRead);
//			}
//			bis.close();
//			bos.close();
//			outputStream.flush();
//			outputStream.close();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/*public static void main(String[] args) throws Exception {
//
//		String title = "北京艺美装饰检查结果统计报表";
//
//		List<String> titleList = new ArrayList();
//		List<String> columnList = new ArrayList();
//		List<Map> dataList = new ArrayList();
//		titleList.add("编号");
//		titleList.add("资产名称");
//		titleList.add("单价");
//		titleList.add("结果");
//		titleList.add("日期");
//		titleList.add("时间");
//		columnList.add("id");
//		columnList.add("assetname");
//		columnList.add("danjia");
//		columnList.add("result");
//		columnList.add("date");
//		columnList.add("time");
//		for (int i = 0; i < 5; i++) {
//			Map m = new HashMap();
//			m.put("id", "00" + i);
//			m.put("assetname", "电脑");
//			m.put("danjia", "1200");
//			m.put("result", "已查");
//			m.put("date", "2017-11-23");
//			m.put("time", "12:30:00");
//			dataList.add(m);
//		}
//
//		Excel2007 excel = new Excel2007();
//		excel.openWorkbook("E:/test4444.xlsx", title, titleList.size() - 1);
//		excel.addTable(titleList, columnList, dataList);
//		excel.lineNum += 2;
//
//		
//		 * BarChart bar = new BarChart(); JFreeChart chart =
//		 * bar.getBarChart(bar.getDataSet(),"酒仙桥地区资产清查结果柱状图");
//		 * bar.writeChart(chart, "e:/bar.jpg");
//		 
//
//		excel.addImg("E:/bar.jpg", 0, 4, excel.lineNum, excel.lineNum + 20);
//		excel.lineNum += 22;
//
//		
//		 * PieChart pie = new PieChart(); JFreeChart chart =
//		 * pie.getPieChart(pie.getDataSet(),"水果产量"); pie.writeChart(chart,
//		 * "e:/pie.jpg");
//		 
//
//		excel.addImg("E:/pie.jpg", 0, 4, excel.lineNum, excel.lineNum + 20);
//		excel.closedObject();
//	}*/
//
//}
