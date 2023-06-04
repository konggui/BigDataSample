package create._02_factory_method;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._02_factory_method
 * @author: aishuang
 * @date: 2023-04-10 17:27
 */
public class AbstractExcelExportFactory implements IExcelExportFactory{
    @Override
    public IExcelExport createExcelFactory() {
        // 抽象工厂类，提供一个默认操作
        return null;
    }
}
