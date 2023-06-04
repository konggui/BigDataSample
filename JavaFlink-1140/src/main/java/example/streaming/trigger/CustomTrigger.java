package example.streaming.trigger;

import example.pojo.CustomEvent;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

/**
 * @desc: 样例需求： 按照计数和时间（processing-time)触发窗口计算，计算5次或者满足20s滚动窗口触发窗口计算
 *     注意： TriggerResult是个枚举
 *          CONTINUE:不触发窗口计算
 *          PURGE: 不触发窗口计算，但清楚窗口中的数据
 *          FIRE_AND_PURE: 触发窗口计算，并清除窗口中的数据；
 *          FIRE: 触发窗口计算，但不清除窗口中的数据；
 * @program: MapPOI
 * @packagename: example.streaming.trigger
 * @author: aishuang
 * @date: 2023-04-17 18:10
 */
public class CustomTrigger extends Trigger<CustomEvent, TimeWindow> {
    private static final long serialVersionUID = 6481944708733776075L;

    private CustomTrigger() {}

    public static CustomTrigger create() {
        return new CustomTrigger();
    }

    private static int flag = 0;

    /**
     * 向Window添加元素时，该方法就会被调用
     * @param customEvent 进入窗口的元素
     * @param timestamp 元素到达的时间戳
     * @param window 元素要添加的窗口
     * @param ctx Trigger的上下文，可以获取当前的水印，处理状态，注册timer计时器回调等
     * @return
     * @throws Exception
     */
    @Override
    public TriggerResult onElement(CustomEvent customEvent, long timestamp, TimeWindow window, TriggerContext ctx) throws Exception {

        return null;
    }

    /**
     * 当processing-time timer被触发时，会被调用
     * @param timestamp timer触发时的时间戳
     * @param window timer触发的窗口
     * @param ctx Trigger上下文，可以获取当前水印，处理状态，注册timer计时器回调等
     * @return
     * @throws Exception 异常
     */
    @Override
    public TriggerResult onProcessingTime(long timestamp, TimeWindow window, TriggerContext ctx) throws Exception {
        return null;
    }

    /**
     * 当even-time timer被触发时，会被调用
     * @param timestamp timer触发时的时间戳
     * @param window timer触发的窗口
     * @param ctx Trigger上下文，可以获取当前水印，处理状态，注册timer计时器回调等
     * @return
     * @throws Exception 异常
     */
    @Override
    public TriggerResult onEventTime(long timestamp, TimeWindow window, TriggerContext ctx) throws Exception {
        return null;
    }

    /**
     * 是否需要合并Trigger State，需要合并，则为true，不需要则为false。为true，需要实现onMerge方法
     * @return
     */
    @Override
    public boolean canMerge() {
        return super.canMerge();
    }

    /**
     * 对两个Trigger的State进行Merge
     * @param window merge产生的新窗口
     * @param ctx 可以用于注册time计时器回调和访问状态的上下文
     * @throws Exception
     */
    @Override
    public void onMerge(TimeWindow window, OnMergeContext ctx) throws Exception {
        super.onMerge(window, ctx);
    }

    /**
     * Window销毁时被窗口
     * @param window 要销毁的窗口
     * @param ctx  Trigger上下文
     * @throws Exception
     */
    @Override
    public void clear(TimeWindow window, TriggerContext ctx) throws Exception {

    }
}
