package create._05_prototype;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._05_prototype
 * @author: aishuang
 * @date: 2023-04-10 07:01
 */
@Setter
@Getter
@ToString
public class SKU02 implements Cloneable{
    private String id;
    private String name;

    private List<String> imageList;

    /**
     * 库存数量
     */
    private int quantity;

    public SKU02() {

    }

    public SKU02(String id, String name, int quantity, List<String> imageList) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.imageList = imageList;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
