package example.udf;

import org.apache.spark.sql.api.java.UDF1;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: example.udf
 * @author: aishuang
 * @date: 2023-05-13 01:05
 */
public class GetLengthUDF implements UDF1<String,Integer> {
    private static final long serialVersionUID = -687338303404646969L;

    @Override
    public Integer call(String s) throws Exception {
        return s.length();
    }
}
