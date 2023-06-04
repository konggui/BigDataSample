package create._02_factory_method_v2;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._02_factory_method_v2
 * @author: aishuang
 * @date: 2023-04-10 18:24
 */
public interface IExcelFile {
    void addFileTitleRow(IFileTitleRow fileTitleRow);

    void addTableTitleRow(ITableTitleRow tableTitleRow);

    void addDataRow(IDataRow dataRow);

    void addTotalRow(ITotalRow totalRow);
}
