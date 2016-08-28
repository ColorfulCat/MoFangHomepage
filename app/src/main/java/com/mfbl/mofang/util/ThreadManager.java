package com.mfbl.mofang.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 提供适合移动终端的Executor配置，集中管理应用的执行器，该执行器是全局的，任何一层都可以使用，不需要另外自己去起线程
 * 
 * @author toby.du
 * @date 2014年10月29日
 */
public class ThreadManager {
    static final String TAG = "ThreadManager";

    private ThreadManager() {
        // 阻止实例化
    }

    /*
     * 为了不影响应用启动时间，延迟初始化，ExecutorHolder为延迟初始化占位类
     */
    private static class ExecutorHolder {
        /*
         * 提供一般任务执行器，在Executors.newCachedThreadPool()参数的基础上，设置corePoolSize为处理器个数
         */
        private static ExecutorService mExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                64, 2L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    }

    private static class SingleExecutorHolder {
        /*
         * 提供顺序执行任务的执行器
         */
        private static ExecutorService mSingleExecutor = Executors.newSingleThreadExecutor();
    }

    private static class ScheduledExecutorHolder {
        /*
         * 提供周期性任务执行器
         */
        private static ScheduledExecutorService mScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    }

    private static ExecutorService getExecutor() {
        return ExecutorHolder.mExecutor;
    }

    private static ExecutorService getSingleExecutor() {
        return SingleExecutorHolder.mSingleExecutor;
    }

    private static ScheduledExecutorService getScheduledExecutor() {
        return ScheduledExecutorHolder.mScheduledExecutor;
    }

    /**
     * 执行不需要返回结果的任务
     * 
     * @param task
     *            要执行的任务
     * @throws NullPointerException
     *             如果task为Null
     */
    public static final void execute(Runnable task) {
        getExecutor().execute(task);
    }

    /**
     * 执行需要返回结果的任务
     * 
     * @param task
     *            要执行的任务
     * @param result
     * @return Future代表待完成的任务，可以通过Future获取结果或取消未开始执行的任务， 如果要取消正在执行的任务需要通过中断方式来实现
     * @throws NullPointerException
     *             如果task为Null
     */
    public static final <T> Future<T> submit(Runnable task, T result) {
        return getExecutor().submit(task, result);
    }

    /**
     * 执行需要返回结果的任务
     * 
     * @param task
     *            要执行的任务
     * @param result
     * @return Future代表待完成的任务，可以通过Future获取结果或取消未开始执行的任务， 如果要取消正在执行的任务需要通过中断方式来实现
     * @throws NullPointerException
     *             如果task为Null
     */
    public static final Future<?> submit(Runnable task) {
        return getExecutor().submit(task);
    }

    /**
     * 执行需要返回结果的任务
     * 
     * @param task
     *            要执行的任务
     * @param result
     * @return Future代表待完成的任务，可以通过Future获取结果或取消未开始执行的任务， 如果要取消正在执行的任务需要通过中断方式来实现
     * @throws NullPointerException
     *             如果task为Null
     */
    public static final <T> Future<T> submit(Callable<T> task) {
        return getExecutor().submit(task);
    }

    /**
     * 执行延迟任务
     * 
     * @param task
     *            要执行的任务
     * @param delay
     *            单位毫秒
     * @return ScheduledFuture
     * @throws NullPointerException
     *             如果task为Null
     */
    public static final ScheduledFuture<?> schedule(Runnable task, long delay) {
        return getScheduledExecutor().schedule(task, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 执行延迟任务
     * 
     * @param task
     *            要执行的任务
     * @param delay
     *            单位毫秒
     * @return ScheduledFuture
     * @throws NullPointerException
     *             如果task为Null
     */
    public static final <T> ScheduledFuture<T> schedule(Callable<T> task, long delay) {
        return getScheduledExecutor().schedule(task, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 执行周期性任务
     * 
     * @param task
     *            要执行的任务
     * @param initialDelay
     *            首次延迟时间，单位毫秒
     * @param period
     *            周期时间，单位毫秒
     * @return ScheduledFuture
     * @throws NullPointerException
     *             如果task为Null
     * @throws IllegalArgumentException
     *             如果period小于等于0
     */
    public static final ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long initialDelay, long period) {
        return getScheduledExecutor().scheduleAtFixedRate(task, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    /**
     * 执行周期性任务
     * 
     * @param task
     *            要执行的任务
     * @param initialDelay
     *            首次延迟时间，单位毫秒
     * @param delay
     *            每次延迟时间，单位毫秒
     * @return ScheduledFuture
     * @throws NullPointerException
     *             如果task为Null
     * @throws IllegalArgumentException
     *             如果delay小于等于0
     */
    public static ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long initialDelay, long delay) {
        return getScheduledExecutor().scheduleWithFixedDelay(task, initialDelay, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 在SingleExecutor中执行不需要返回结果的任务，保证任务按提交顺序执行
     * 
     * @param task
     *            要执行的任务
     * @throws NullPointerException
     *             如果task为Null
     */
    public static void executeInSingle(Runnable task) {
        getSingleExecutor().execute(task);
    }

    /**
     * 在SingleExecutor中执行需要返回结果的任务，保证任务按提交顺序执行
     * 
     * @param task
     *            要执行的任务
     * @param result
     * @return Future代表待完成的任务，可以通过Future获取结果或取消未开始执行的任务， 如果要取消正在执行的任务需要通过中断方式来实现
     * @throws NullPointerException
     *             如果task为Null
     */
    public static <T> Future<T> submitInSingle(Runnable task, T result) {
        return getSingleExecutor().submit(task, result);
    }

    /**
     * 在SingleExecutor中执行需要返回结果的任务，保证任务按提交顺序执行
     * 
     * @param task
     *            要执行的任务
     * @param result
     * @return Future代表待完成的任务，可以通过Future获取结果或取消未开始执行的任务， 如果要取消正在执行的任务需要通过中断方式来实现
     * @throws NullPointerException
     *             如果task为Null
     */
    public static Future<?> submitInSingle(Runnable task) {
        return getSingleExecutor().submit(task);
    }

    /**
     * 在SingleExecutor中执行需要返回结果的任务，保证任务按提交顺序执行
     * 
     * @param task
     *            要执行的任务
     * @param result
     * @return Future代表待完成的任务，可以通过Future获取结果或取消未开始执行的任务， 如果要取消正在执行的任务需要通过中断方式来实现
     * @throws NullPointerException
     *             如果task为Null
     */
    public static <T> Future<T> submitInSingle(Callable<T> task) {
        return getSingleExecutor().submit(task);
    }

    /**
     * 返回一个“线性”执行器，用于完成需顺序执行的一系列任务 如果需要全局顺序性执行，请用scheduleXXX系列方法
     * 
     * @return
     */
    public static Executor newSerialExecutor() {
        return new SerialExecutor();
    }

    private static class SerialExecutor implements Executor {
        final Queue<Runnable> mTasks = new LinkedList<Runnable>();
        Runnable mActive;

        public synchronized void execute(final Runnable r) {
            mTasks.offer(new Runnable() {
                public void run() {
                    try {
                        r.run();
                    } finally {
                        scheduleNext();
                    }
                }
            });
            if (mActive == null) {
                scheduleNext();
            }
        }

        protected synchronized void scheduleNext() {
            if ((mActive = mTasks.poll()) != null) {
                getExecutor().execute(mActive);
            }
        }
    }

    /**
     * 在后台线程执行，如果调用线程不是MainThread，则不新建线程
     * 
     * @param task
     */
    public static void executeInBackground(Runnable task) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            execute(task);
        } else {
            task.run();
        }
    }

    /**
     * DB写线程采用单线程执行器，避免并发异常
     * 
     * @param task
     */
    public static void executeInDbWriteThread(Runnable task) {
        executeInSingle(task);
    }

    private static class TimerHandlerThreadHolder {
        private static HandlerThread mHandlerThread = new HandlerThread("globle_timer");
        static {
            mHandlerThread.start();
            mHandler = new Handler(mHandlerThread.getLooper());
        }
        private static Handler mHandler;

    }

    // 全局Timer
    private static Handler getTimerThreadHandler() {
        return TimerHandlerThreadHolder.mHandler;
    }
    
    public static void postDelayed(Runnable r, long delayMillis) {
        getTimerThreadHandler().postDelayed(r, delayMillis);
    }

    public static void postDelayedAndRemoveBefore(Runnable r, long delayMillis) {
        getTimerThreadHandler().removeCallbacks(r);
        getTimerThreadHandler().postDelayed(r, delayMillis);
    }
    
    public static void postAtTime(Runnable r, long uptimeMillis) {
        getTimerThreadHandler().postAtTime(r, uptimeMillis);
    }

    // Event执行线程单独出来
    private static class EventHandlerThreadHolder {
        private static HandlerThread mHandlerThread = new HandlerThread("event-thread");
        static {
            mHandlerThread.start();
            mHandler = new Handler(mHandlerThread.getLooper());
        }
        private static Handler mHandler;

    }
    private static Handler getEventThreadHandler() {
        return EventHandlerThreadHolder.mHandler;
    }
    public static void runInEventThread(Runnable r) {
        getEventThreadHandler().post(r);
    }

}
