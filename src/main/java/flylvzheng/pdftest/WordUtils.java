package flylvzheng.pdftest;

import org.apache.poi.xwpf.converter.core.XWPFConverterException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 以动手实践为荣,以只看不练为耻.
 * 以打印日志为荣,以出错不报为耻.
 * 以局部变量为荣,以全局变量为耻.
 * 以单元测试为荣,以手工测试为耻.
 * 以代码重用为荣,以复制粘贴为耻.
 * 以多态应用为荣,以分支判断为耻.
 * 以定义常量为荣,以魔法数字为耻.
 * 以总结思考为荣,以不求甚解为耻.
 *
 * @author LvZheng
 * 创建时间：2019/5/13 下午5:13
 */
@SuppressWarnings("all")
public class WordUtils {

    /**
     * <br>
     * 描 述: doc内容改变 <br>
     * 作 者: shizhenwei <br>
     * 历 史: (版本) 作者 时间 注释
     *
     * @param is
     *            doc文档模板
     * @param params
     *            key value 将模板里的可以替换为响应VALUE
     * @return
     * @throws IOException
     */
    public static byte[] docContentChange(InputStream is, Map<String, String> params) throws IOException {
        return DocxUtils.docContentChange(is, params);
    }

    /**
     * <br>
     * 描 述: docx内容改变 <br>
     * 作 者: shizhenwei <br>
     * 历 史: (版本) 作者 时间 注释
     *
     * @param is
     *            docx文档模板
     * @param params
     *            key value 将模板里的可以替换为响应VALUE
     * @return
     * @throws IOException
     * @throws XWPFConverterException
     */
    public static byte[] docxContentChange(InputStream is, Map<String, String> params)
            throws XWPFConverterException, IOException {
        return DocxUtils.docxContentChange(is, params);
    }

    /***
     * <br>
     * 描 述: docx内容预览 <br>
     * 作 者: zhaowei <br>
     * 历 史: (版本) 作者 时间 注释
     *
     * @param is
     * @param params
     * @return
     * @throws XWPFConverterException
     * @throws IOException
     */
    public static byte[] docxContentChangeView(InputStream is, Map<String, String> params)
            throws XWPFConverterException, IOException {
        return DocxUtils.docxContentChange(is, params,DocxUtils.PREVIEW_REPLACE);
    }

    /***
     * <br>
     * 描 述: docx内容预览 <br>
     * 作 者: zhaowei <br>
     * 历 史: (版本) 作者 时间 注释
     *
     * @param is
     * @param params
     * @return
     * @throws XWPFConverterException
     * @throws IOException
     */
    public static byte[] docxContentChangeDownload(InputStream is, Map<String, String> params)
            throws XWPFConverterException, IOException {
        return DocxUtils.docxContentChange(is, params,DocxUtils.DOWNLOAD_REPLACE);
    }

    /**
     * <br>
     * 描 述: 将docx字节数组流转换为pdf字节数组流 <br>
     * 作 者: shizhenwei <br>
     * 历 史: (版本) 作者 时间 注释
     *
     * @param docxBytes
     *            docx文档字节数组
     * @return
     * @throws XWPFConverterException
     * @throws IOException
     *             注：需在部署系统安装word对应的中文字体
     */
    public static byte[] docx2pdf(byte[] docxBytes) throws XWPFConverterException, IOException {
        return DocxUtils.docx2pdf(docxBytes);
    }

    /**
     * <br>
     * 描 述: 将Word模板流改变内容后转换为pdf字节数组流 <br>
     * 作 者: shizhenwei <br>
     * 历 史: (版本) 作者 时间 注释
     *
     * @param is
     *            docx文档输入流
     * @param params
     *            key value 将模板里的可以替换为响应VALUE
     * @return
     * @throws IOException
     * @throws XWPFConverterException
     *             * 注：需在部署系统安装word对应的中文字体
     */
    public static byte[] docx2pdf(InputStream is, Map<String, String> params)
            throws XWPFConverterException, IOException {
        return DocxUtils.docx2pdf(is, params,DocxUtils.NULL_REPLACE);
    }
}

