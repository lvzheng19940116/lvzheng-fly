package flylvzheng.redis.stock;

/**
 * @author LvZheng
 * 创建时间：2021/3/10 下午4:27
 */
public interface IStockCallback {
    /**
     * 获取库存
     *
     * @return
     */
    int getStock();
}
