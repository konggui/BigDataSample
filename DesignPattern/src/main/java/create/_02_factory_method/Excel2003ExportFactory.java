package create._02_factory_method;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._02_factory_method
 * @author: aishuang
 * @date: 2023-04-10 17:28
 */
public class Excel2003ExportFactory extends AbstractExcelExportFactory{


    @Override
    public IExcelExport createExcelFactory() {
        // 创建Excel2003Export对象
        return new Excel2003Export();
    }
}
