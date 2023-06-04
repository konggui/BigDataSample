package create._02_factory_method;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._05_prototype
 * @author: aishuang
 * @date: 2023-04-10 07:19
 */
@Setter
@Getter
@ToString
public class SKU implements Cloneable, Serializable {
    private String id;
    private String name;
    private int quantity;
    private List<String> imageList;

    public SKU() {

    }

    public SKU(String id, String name, int quantity, List<String> imageList) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.imageList = imageList;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return SerializationUtils.clone(this);
    }
}
