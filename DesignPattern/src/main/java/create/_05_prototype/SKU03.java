package create._05_prototype;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc: 不适用JDK的复制机制，自己去搞
 * @program: MapPOI
 * @packagename: create._05_prototype
 * @author: aishuang
 * @date: 2023-04-10 07:13
 */
@Setter
@Getter
@ToString
public class SKU03 {
    private String id;
    private String name;
    private int quantity;
    private List<String> imageList;

    public SKU03() {

    }

    public SKU03(String id, String name, int quantity, List<String> imageList) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.imageList = imageList;
    }

    public SKU03(SKU03 origin) {
        this.id = origin.id;
        this.name = origin.name;
        this.quantity = origin.quantity;
        if (null == origin.imageList) {
            this.imageList = null;
        } else {
            imageList = new ArrayList();
            for (String image : origin.imageList) {
                imageList.add(image);
            }
        }
    }
}
