package example.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: example.entity
 * @author: aishuang
 * @date: 2023-04-17 16:34
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class CustomEvent implements Serializable {
    private String id;
    private String data;
    private int money;
    private long timestamp;
}
