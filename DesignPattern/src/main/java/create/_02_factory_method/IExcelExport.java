package create._02_factory_method;

import java.util.List;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._02_factory_method
 * @author: aishuang
 * @date: 2023-04-10 17:25
 */
public interface IExcelExport {
    void exportExcel(List<SKU> skuList);
}
