package com.jlx;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;

/**
 * excle表格
 *
 * @author liuxiaobin
 * @creat 2018-09-04 13:54
 */
public class JlxDemo {
    public static void main(String[] args) throws IOException, RowsExceededException, WriteException {
        //1、创建excel
        File file = new File("Test.xls");
        file.createNewFile();
        //2、创建工作bo
        WritableWorkbook workBook = Workbook.createWorkbook(file);
        //3、创建sheet
        WritableSheet sheet = workBook.createSheet("用户管理", 0);
        //4、设置titles
        String[] titles = {"编号", "用户", "姓名"};
        //5、单元格 <列号、行号、内容> 都是从0开始
        Label label = null;
        //6、给第一行设置列明
        for (int i = 0; i < titles.length; i++) {
            //x,y,第一行的列名
            label = new Label(i, 0, titles[i]);
            //7、添加单元格
            sheet.addCell(label);
        }
        //8：模拟数据库导入数据
        for (int i = 1; i < 10; i++) {
            //添加编号，第二行第一列
            label = new Label(0, i, i + "");
            sheet.addCell(label);

            //添加账号
            label = new Label(1, i, "10010" + i);
            sheet.addCell(label);

            //添加密码
            label = new Label(2, i, "123456");
            sheet.addCell(label);
        }
        workBook.write();
        workBook.close();
    }
}
