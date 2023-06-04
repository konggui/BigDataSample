package create._02_factory_method_v2;

import create._02_factory_method.IExcelExport;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._02_factory_method
 * @author: aishuang
 * @date: 2023-04-10 17:27
 */
public class AbstractExcelExportFactory implements IExcelExportFactory {

    @Override
    public IExcelFile createExcel() {
        //模板方法实现工厂自产自销
        // 实现默认功能
        IFileTitleRow fileTitleRow = createFileTitleRow();
        ITableTitleRow tableTitleRow = createTableTitleRow();
        ITotalRow totalRow = createTotalRow();
        return null;
    }

    @Override
    public IFileTitleRow createFileTitleRow() {
        // 实现默认功能
        return null;
    }

    @Override
    public ITableTitleRow createTableTitleRow() {
        // 实现默认功能
        return null;
    }

    @Override
    public ITotalRow createTotalRow() {
        // 实现默认功能
        return null;
    }
}
