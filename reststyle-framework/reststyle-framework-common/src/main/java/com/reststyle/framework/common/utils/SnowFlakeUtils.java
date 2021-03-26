package com.reststyle.framework.common.utils;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-03-26
 * @Time: 13:58
 */
public class SnowFlakeUtils
{
    private final long workerId;
    private final long datacenterId;
    private long sequence = 0L;
    /**
     * 物理节点ID长度
     */
    private final long workerIdBits = 5L;
    /**
     * 数据中心ID长度
     */
    private final long datacenterIdBits = 5L;

    private long lastTimestamp = -1L;

    /**
     * 成员类，SnowFlakeUtil的实例对象的保存域
     */
    private static class IdGenHolder
    {
        private static final SnowFlakeUtils INSTANCE = new SnowFlakeUtils();
    }

    /**
     * 外部调用获取SnowFlakeUtil的实例对象，确保不可变
     * @return
     */
    public static SnowFlakeUtils get()
    {
        return IdGenHolder.INSTANCE;
    }

    /**
     * 初始化构造，无参构造有参函数，默认节点都是0
     */
    public SnowFlakeUtils()
    {
        this(0L, 0L);
    }

    /**
     * 设置机器节点和数据中心节点数，都是 0-31
     * @param workerId
     * @param datacenterId
     */
    public SnowFlakeUtils(long workerId, long datacenterId)
    {
        /**
         * 最大支持机器节点数0~31，一共32个
         */
        long maxWorkerId = ~(-1L << workerIdBits);
        if (workerId > maxWorkerId || workerId < 0)
        {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        /**
         * 最大支持数据中心节点数0~31，一共32个
         */
        long maxDatacenterId = ~(-1L << datacenterIdBits);
        if (datacenterId > maxDatacenterId || datacenterId < 0)
        {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 线程安全的id生成方法
     * @return
     */
    @SuppressWarnings("all")
    public synchronized long nextId()
    {
        //获取当前毫秒数
        long timestamp = timeGen();
        //如果服务器时间有问题(时钟后退) 报错。
        if (timestamp < lastTimestamp)
        {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果上次生成时间和当前时间相同,在同一毫秒内
        /**
         * 序列号12位， 4095，同毫秒内生成不同id的最大个数
         */
        long sequenceBits = 12L;
        if (lastTimestamp == timestamp)
        {
            //sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
            /**
             * 用于和当前时间戳做比较，以获取最新时间
             */
            long sequenceMask = -1L ^ (-1L << sequenceBits);
            sequence = (sequence + 1) & sequenceMask;
            //判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0
            if (sequence == 0)
            {
                //自旋等待到下一毫秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        else
        {
            //如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加，每个毫秒时间内，都是从0开始计数，最大4095
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        // 最后按照规则拼出ID 64位
        // 000000000000000000000000000000000000000000  00000            00000       000000000000
        //1位固定整数   time                                       datacenterId   workerId    sequence
        /**
         *  Thu, 04 Nov 2010 01:42:54 GMT 标记时间 用来计算偏移量，距离当前时间不同，得到的数据的位数也不同
         */
        long twepoch = 1288834974657L;
        /**
         * 机器节点左移12位
         */
        /**
         * 数据中心节点左移17位
         */
        long datacenterIdShift = sequenceBits + workerIdBits;
        /**
         * 时间毫秒数左移22位
         */
        long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                | (workerId << sequenceBits) | sequence;
    }

    /**
     * 比较当前时间和过去时间，防止时钟回退（机器问题），保证给的都是最新时间/最大时间
     * @param lastTimestamp
     * @return
     */
    protected long tilNextMillis(long lastTimestamp)
    {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp)
        {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取当前的时间戳（毫秒）
     * @return
     */
    protected long timeGen()
    {
        return System.currentTimeMillis();
    }

    /**
     * 获取全局唯一编码String类型的
     */
    public static String getStringId()
    {
        Long id = SnowFlakeUtils.get().nextId();
        return id.toString();
    }

    /**
     * 获取全局唯一编码Long类型的
     */
    public static Long getId()
    {
        return SnowFlakeUtils.get().nextId();
    }

    public static void main(String[] args)
    {
        for (int i = 0; i < 1000; i++)
        {
            System.out.println(getId());
        }

    }
}