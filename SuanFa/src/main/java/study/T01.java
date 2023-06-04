package study;

import java.util.Scanner;

/**
 * @desc:
 *  有2个奇数个数字， n个偶数个数字，找出这2个奇数个数字
 *  Math.random() -> [0,1)
 *  Math.random() * N -> [0, N）
 *  (int)(Math.random() * N) -> [0, N-1]
 *
 * @program: MapPOI
 * @packagename: study
 * @author: aishuang
 * @date: 2023-04-20 20:02
 */
public class T01 {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 7, 8, 9,8};
        printOddNumbers(arr);
    }

    public static void printOddNumbers(int[] arr) {
        int eor = 0;
        for (int x : arr) {
            eor ^= x;
        }
        int rightOne = eor & (~eor + 1);
        int onlyOne = 0;
        for (int x : arr) {
            if ((x & rightOne) == 0) {
                // 只能先找出这一位是零的
                onlyOne ^= x;
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }
}
