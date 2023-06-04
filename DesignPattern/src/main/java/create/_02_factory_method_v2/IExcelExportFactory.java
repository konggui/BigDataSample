package create._02_factory_method_v2;

import create._02_factory_method.IExcelExport;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._02_factory_method
 * @author: aishuang
 * @date: 2023-04-10 17:26
 */
public interface IExcelExportFactory {
    /**
     * 工厂方法
     */
    IExcelFile createExcel();

    /**
     * 工厂方法
     */
    IFileTitleRow createFileTitleRow();

    /**
     * 工厂方法
     */
    ITableTitleRow createTableTitleRow();

    /**
     * 工厂方法
     */
    ITotalRow createTotalRow();
}
