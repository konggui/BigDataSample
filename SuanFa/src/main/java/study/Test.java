package study;

/**
 * @desc:
 * 细节1:
 *      mid = (L+R)/2 有可能数值溢出， 用 mid = L + (R-L)/2  => mid = L + (R-L)>>1
 * 细节2：
 *      1.HashMap、Hashtable不是有序的；
 *      2.TreeMap和LinkedHashMap是有序的（TreeMap默认 Key 升序，LinkedHashMap则记录了插入顺序）。
 *
 * @program: MapPOI
 * @packagename: study
 * @author: aishuang
 * @date: 2023-04-20 20:53
 */
public class Test {
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[maxSize];
        for (int i = 0; i< arr.length; i++){
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for(int i = 0; i< arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i< testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
        }
    }
}
