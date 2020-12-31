package flylvzheng.threadpool.pool;

import io.netty.util.concurrent.RejectedExecutionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 以动手实践为荣,以只看不练为耻.
 * 以打印日志为荣,以出错不报为耻.
 * 以局部变量为荣,以全局变量为耻.
 * 以单元测试为荣,以手工测试为耻.
 * 以代码重用为荣,以复制粘贴为耻.
 * 以多态应用为荣,以分支判断为耻.
 * 以定义常量为荣,以魔法数字为耻.
 * 以总结思考为荣,以不求甚解为耻.
 *
 * @author LvZheng
 * 创建时间：2019/6/12 下午5:21
 */
@Component
@EnableAsync
public class ExecutorConfig {
    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);


    /**
     * 2、Java 线程池
     * Java通过Executors提供四种线程池，分别为：
     * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     * newScheduledThreadPool 创建一个周期线程池，支持定时及周期性任务执行。
     * newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     * <p>
     * 无论创建那种线程池 必须要调用ThreadPoolExecutor
     * 线程池类为 java.util.concurrent.ThreadPoolExecutor，常用构造方法为：
     * ThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
     * long keepAliveTime, TimeUnit unit,
     * BlockingQueue workQueue,
     * RejectedExecutionHandler handler)
     * corePoolSize： 线程池维护线程的最少数量
     * maximumPoolSize：线程池维护线程的最大数量
     * keepAliveTime： 线程池维护线程所允许的空闲时间
     * unit： 线程池维护线程所允许的空闲时间的单位
     * workQueue： 线程池所使用的缓冲队列
     * handler： 线程池对拒绝任务的处理策略
     * <p>
     * 当一个任务通过execute(Runnable)方法欲添加到线程池时：
     * 如果此时线程池中的数量小于corePoolSize，即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务。
     * 如果此时线程池中的数量等于 corePoolSize，但是缓冲队列 workQueue未满，那么任务被放入缓冲队列。
     * 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maximumPoolSize，建新的线程来处理被添加的任务。
     * 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量等于maximumPoolSize，那么通过 handler所指定的策略来处理此任务。
     * <p>
     * 处理任务的优先级为：
     * 核心线程corePoolSize、任务队列workQueue、最大线程maximumPoolSize，如果三者都满了，使用handler处理被拒绝的任务。
     * <p>
     * 当线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数。
     * <p>
     * unit可选的参数为java.util.concurrent.TimeUnit中的几个静态属性：
     * NANOSECONDS、MICROSECONDS、MILLISECONDS、SECONDS。
     * <p>
     * workQueue我常用的是：java.util.concurrent.ArrayBlockingQueue
     * <p>
     * handler有四个选择：
     * ThreadPoolExecutor.AbortPolicy()
     * 抛出java.util.concurrent.RejectedExecutionException异常
     * ThreadPoolExecutor.CallerRunsPolicy()
     * 重试添加当前的任务，他会自动重复调用execute()方法
     * ThreadPoolExecutor.DiscardOldestPolicy()
     * 抛弃旧的任务
     * ThreadPoolExecutor.DiscardPolicy()
     * 抛弃当前的任务
     * <p>
     * Java并发包中的阻塞队列一共7个，当然他们都是线程安全的。
     * 　　ArrayBlockingQueue：一个由数组结构组成的有界阻塞队列。
     * 　　LinkedBlockingQueue：一个由链表结构组成的有界阻塞队列。
     * 　　PriorityBlockingQueue：一个支持优先级排序的无界阻塞队列。
     * 　　DealyQueue：一个使用优先级队列实现的无界阻塞队列。
     * 　　SynchronousQueue：一个不存储元素的阻塞队列。
     * 　　LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
     * 　　LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。
     */

    public Executor ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                       BlockingQueue workQueue,
                                       RejectedExecutionHandler handler) {

        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

//    public ScheduledThreadPoolExecutor(int corePoolSize) {
//        super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
//                new DelayedWorkQueue());
//    }


    /**
     * 注意这一行日志：2. do submit,taskCount [101], completedTaskCount [87], activeCount [5], queueSize [9]
     * 这说明提交任务到线程池的时候，调用的是submit(Callable task)这个方法，当前已经提交了101个任务，完成了87个，
     * 当前有5个线程在处理任务，还剩9个任务在队列中等待，线程池的基本情况一路了然；
     */
    @Bean
    public Executor asyncServiceExecutor() {
        logger.info("start asyncServiceExecutor");
        //  ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(5);
        //配置最大线程数
        executor.setMaxPoolSize(5);
        //配置队列大小
        executor.setQueueCapacity(99999);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
