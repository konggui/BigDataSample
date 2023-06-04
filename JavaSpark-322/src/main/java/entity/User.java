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
 * @date: 2023-05-13 01:27
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private String id;
    private String username;
    private String nickname;
    private String password;
    private static long salary;

    public static long getSalary() {
        return salary;
    }
}
