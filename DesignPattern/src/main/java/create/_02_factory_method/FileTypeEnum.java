package create._02_factory_method;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._02_factory_method
 * @author: aishuang
 * @date: 2023-04-10 17:29
 */
public enum FileTypeEnum {
    EXCEL_2003("excel2003"),
    EXCEL_2007("excel2007");

    private String type;

    FileTypeEnum(String excelType) {
        this.type = excelType;

    }

    public String getType() {
        return this.type;
    }
}
