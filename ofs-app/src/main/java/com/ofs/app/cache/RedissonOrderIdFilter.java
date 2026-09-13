package com.ofs.app.cache;

import com.ofs.domain.order.application.query.OrderIdFilter;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;

/**
 * {@link OrderIdFilter} 的布隆过滤器实现（Redisson RBloomFilter，位图存在 Redis 里，多机共享）。
 *
 * <p><b>tryInit 的坑</b>：它只在 key 不存在时真正初始化，重复调用是幂等的——所以应用重启不会
 * 清空过滤器（这是好事）。但反过来，<b>改了 expectedInsertions / falseProbability 之后必须先删掉
 * 旧 key 才会按新参数重建</b>，否则一直沿用旧位图规格，你以为调小了误判率其实没变。
 *
 * <p><b>容量说了算的是 expectedInsertions</b>：实际插入量超过它以后误判率会显著高于设定值，
 * 别按当前订单量配，按「重建周期内的增量上限」配。
 *
 * <p>不支持删除元素等已知取舍见 {@link OrderIdFilter} 的类注释。
 */
public class RedissonOrderIdFilter implements OrderIdFilter {

    private final RBloomFilter<String> bloomFilter;

    public RedissonOrderIdFilter(RedissonClient redissonClient,
                                 String key,
                                 long expectedInsertions,
                                 double falseProbability) {
        this.bloomFilter = redissonClient.getBloomFilter(key);
        this.bloomFilter.tryInit(expectedInsertions, falseProbability);
    }

    @Override
    public void add(String orderId) {
        bloomFilter.add(orderId);
    }

    @Override
    public boolean mightExist(String orderId) {
        return bloomFilter.contains(orderId);
    }
}
