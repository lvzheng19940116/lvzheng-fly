package flylvzheng.excel;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ExcelUtil {
	/**
	 * 导出
	 * @param list
	 * @param pojoClass
	 * @param fileName
	 * @param response
	 */
    public static void exportExcel(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response) {
        ExportParams exportParams = new ExportParams("DOA导出数据", "sheet1");
        exportParams.setHeaderColor((short) 41);
        exportParams.setStyle(LenovoExcelStyle.class);
        defaultExport(list, pojoClass, fileName, response, exportParams);
    }
    /**
     * 导出
     * @param list
     * @param title
     * @param sheetName
     * @param pojoClass
     * @param fileName
     * @param response
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName, HttpServletResponse response){
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response,
                                      ExportParams exportParams) {
        Workbook workbook;
		try {
			workbook = Optional.ofNullable(ExcelExportUtil.exportExcel(exportParams, pojoClass, list))
			        .orElseThrow(() -> new Exception("错误"));
			downLoadExcel(fileName, response, workbook);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
    /**
     * 导入
     * @param filePath
     * @param titleRows
     * @param headerRows
     * @param pojoClass
     * @return
     */
    public static <T> List<T> importExcel(String filePath,Integer titleRows,Integer headerRows, Class<T> pojoClass){
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        }catch (NoSuchElementException e){
          //  throw new NormalException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
          //  throw new NormalException(e.getMessage());
        }
        return list;
    }
    /**
     * 导入
     * @param file
     * @param titleRows
     * @param headerRows
     * @param pojoClass
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (file == null){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        }catch (NoSuchElementException e){
          //  throw new NormalException("excel文件不能为空");
        } catch (Exception e) {
          //  throw new NormalException(e.getMessage());
        }
        return list;
    }


    
}
