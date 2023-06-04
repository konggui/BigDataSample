package create._02_factory_method_v2;

import create._02_factory_method.SKU;

import java.util.List;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._02_factory_method_v2
 * @author: aishuang
 * @date: 2023-04-10 18:25
 */
public interface IDataRow {
    int getRowCount();

    List<SKU> getData();
}
