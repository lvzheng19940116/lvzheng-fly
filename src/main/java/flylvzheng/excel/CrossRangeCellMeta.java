package flylvzheng.excel;

import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author LvZheng
 * 创建时间：2020/3/27 1:28 PM
 */
@Data
public class CrossRangeCellMeta {

    HSSFWorkbook hssfWorkbook = ConvertHtml2Excel.table2Excel(   new String("<table id=\"targetTable\">\n" +
            "    <thead>\n" +
            "        <tr align=\"center\">\n" +
            "            <th>名次</th>\n" +
            "            <th>姓名</th>\n" +
            "            <th>成绩</th>\n" +
            "        </tr>\n" +
            "    </thead>\n" +
            "    <tbody>\n" +
            "        <tr align=\"center\">\n" +
            "            <td>1</td>\n" +
            "            <td>小明</td>\n" +
            "            <td>100</td>\n" +
            "        </tr>\n" +
            "        <tr align=\"center\">\n" +
            "            <td>2</td>\n" +
            "            <td>小红</td>\n" +
            "            <td>95.5</td>\n" +
            "        </tr>\n" +
            "    </tbody>\n" +
            "</table>"));


    private int firstRowIndex;
    private int firstColIndex;
    private int rowSpan;// 跨越行数
    private int colSpan;// 跨越列数

    public CrossRangeCellMeta(int firstRowIndex, int firstColIndex, int rowSpan, int colSpan) {
        super();
        this.firstRowIndex = firstRowIndex;
        this.firstColIndex = firstColIndex;
        this.rowSpan = rowSpan;
        this.colSpan = colSpan;
    }


    int getFirstRow() {
        return firstRowIndex;
    }

    int getLastRow() {
        return firstRowIndex + rowSpan - 1;
    }

    int getFirstCol() {
        return firstColIndex;
    }

    int getLastCol() {
        return firstColIndex + colSpan - 1;
    }

    int getColSpan(){
        return colSpan;
    }

}
