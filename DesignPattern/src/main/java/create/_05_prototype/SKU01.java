package create._05_prototype;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._05_prototype
 * @author: aishuang
 * @date: 2023-04-10 06:53
 */
@Setter
@Getter
@ToString
public class SKU01 implements Cloneable{
    private String id;
    private String name;

    /**
     * 库存数量
     */
    private int quantity;

    public SKU01() {

    }

    public SKU01(String id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
