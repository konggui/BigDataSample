package create._02_factory_method_v2;

import create._02_factory_method.Excel2007Export;
import create._02_factory_method.IExcelExport;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._02_factory_method
 * @author: aishuang
 * @date: 2023-04-10 17:28
 */
public class Excel2007ExportFactory extends Excel2003ExportFactory {
    @Override
    public IExcelExport createExcelFactory() {
        return new Excel2007Export();
    }
}
