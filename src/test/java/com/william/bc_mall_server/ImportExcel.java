package com.william.bc_mall_server;

import com.william.bc_mall_server.entity.ComEquipment;
import com.william.utils.ExcelUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.util.Date;

/**
 * 三诺导入设备信息
 * @author xinchuang
 * @version v1.0
 * @date 2020/1/14 9:14
 * @since Copyright(c) 爱睿智健康科技
 */
public class ImportExcel {

    // 三诺亲智血糖仪
    private static String e_id = "5dc27dbc09103b15a4601886";
    // 三诺供应商
    private static String e_provide_id = "5dc2820309103b7968cf026a";

    public static void main(String[] args) throws IOException, InvalidFormatException {
        //  读取设备excel导入到数据库
        String fromXsl = "C:\\Users\\xinchuang\\Downloads\\导出设备信息_1575531946303.xls";
        Workbook workbook = ExcelUtils.getWorkbook(fromXsl);
        Sheet sheet = workbook.getSheetAt(0);
        int rowNos = sheet.getLastRowNum();// 得到excel的总记录条数
        ComEquipment comEquipment;
        for (int i = 1; i <= rowNos; i++) {// 遍历行
            Row row = sheet.getRow(i);
            if(row != null){
                Cell cell = row.getCell(1);
                cell.setCellType(CellType.STRING);
                System.out.println(cell.getStringCellValue());
                comEquipment = new ComEquipment();
                comEquipment.setEquipmentId(e_id);
                comEquipment.setEquipmentCode(cell.getStringCellValue());
                comEquipment.setCreateTime(new Date());
                comEquipment.setEquipmentProvideId(e_provide_id);
                comEquipment.setStatus(1);
            }
        }
    }

}
