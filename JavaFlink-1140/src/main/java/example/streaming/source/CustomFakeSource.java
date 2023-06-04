package example.streaming.source;

import example.pojo.CustomEvent;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: example.streaming.source
 * @author: aishuang
 * @date: 2023-04-17 16:27
 */
public class CustomFakeSource extends RichSourceFunction<CustomEvent> {
    private static final long serialVersionUID = -4722193766244758591L;

    private boolean flag;

    private long intervalSecond;

    private String[] idList = {"001", "002", "003"};

    public CustomFakeSource(long intervalSecond) {
        this.intervalSecond = intervalSecond;
    }

    @Override
    public void run(SourceContext ctx) throws Exception {
        while (flag) {
            CustomEvent customEvent = new CustomEvent();
            customEvent.setId(idList[(int)Math.random()*3]);
            customEvent.setMoney((int)Math.random()*100);
            customEvent.setTimestamp(System.currentTimeMillis());
            ctx.collect(customEvent);
            Thread.sleep(Time.seconds(intervalSecond).toMilliseconds());
        }
    }

    @Override
    public void cancel() {
        if (flag) {
            flag = false;
        }

    }
}
