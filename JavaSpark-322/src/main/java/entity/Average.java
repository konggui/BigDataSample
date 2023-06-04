package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: entity
 * @author: aishuang
 * @date: 2023-05-13 01:30
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Average implements Serializable {
    private long sum;
    private long count;
}
