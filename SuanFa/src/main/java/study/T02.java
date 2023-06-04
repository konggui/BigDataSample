package study;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @desc: 求 arr[L..R] 范围上求最大值
 * @program: MapPOI
 * @packagename: study
 * @author: aishuang
 * @date: 2023-04-20 21:09
 */
public class T02 {

    // 压栈的思想
    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L)>>1);
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid +1, R);
        return Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
        int[] arr = Test.generateRandomArray(10, 100);
        List<Integer> ints = new ArrayList<Integer>();
        Arrays.stream(arr).forEach(ints::add);
        System.out.println(ints);
        System.out.println(process(arr, 2, 9));
    }
}
