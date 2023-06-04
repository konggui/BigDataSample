package example.udf;

import entity.Average;
import entity.User;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.expressions.Aggregator;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: example.udf
 * @author: aishuang
 * @date: 2023-05-13 01:21
 */
// Aggregator[-IN, BUF, OUT]
public class MyUDAF extends Aggregator<User, Average, Double> {
    // 初始化： b + zero = b
    @Override
    public Average zero() {
        return new Average(0, 0);
    }

    // 结合两个值生成新的值
    @Override
    public Average reduce(Average buffer, User user) {
        long newSum = buffer.getSum() + User.getSalary();
        long newCount = buffer.getCount() + 1;
        buffer.setSum(newSum);
        buffer.setCount(newCount);
        return buffer;
    }

    // 合并两个中间值
    @Override
    public Average merge(Average b1, Average b2) {
        long mergedSum = b1.getSum() + b2.getSum();
        long mergedCount = b1.getCount() + b2.getCount();
        b1.setSum(mergedSum);
        b1.setCount(mergedCount);
        return b1;
    }

    // 传递这个聚合值
    @Override
    public Double finish(Average reduction) {
        return ((double) reduction.getSum()) / reduction.getCount();
    }

    @Override
    public Encoder<Average> bufferEncoder() {
        return Encoders.bean(Average.class);
    }

    @Override
    public Encoder<Double> outputEncoder() {
        return Encoders.DOUBLE();
    }
}
