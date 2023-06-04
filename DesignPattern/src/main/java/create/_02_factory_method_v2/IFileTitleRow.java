package create._02_factory_method_v2;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._02_factory_method_v2
 * @author: aishuang
 * @date: 2023-04-10 18:24
 */
public interface IFileTitleRow {
    /**
     * 获取标题长度
     * @return
     */
    int getTitleLength();

    /**
     * 获取未格式化的标题内容
     * @return
     */
    String getRawContent();
}
