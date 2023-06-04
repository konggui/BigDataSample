package create._02_factory_method;

import java.util.List;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._02_factory_method
 * @author: aishuang
 * @date: 2023-04-10 17:29
 */
public class FileExportService {

    public void exportExcel(List<SKU> skuList, FileTypeEnum fileTypeEnum) {
        IExcelExportFactory excelExportFactory = null;
        IExcelExport excelExport = null;
        switch (fileTypeEnum) {
            case EXCEL_2003:
                excelExportFactory = new Excel2003ExportFactory();
                break;
            case EXCEL_2007:
                excelExportFactory = new Excel2007ExportFactory();
                break;
        }
        if (null != excelExportFactory) {
            excelExport = excelExportFactory.createExcelFactory();
            excelExport.exportExcel(skuList);
        }
    }
}
