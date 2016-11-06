package com.example.lvkaixue.appmeager.treadfact;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lvkaixue on 2016/7/1.
 */
public class ThreadPool {
    private  ThreadPoo mThreadPoo = null;
    public   ThreadPoo newThreadPoo( int mCorePoolSize, int mMaxPoolSize, int mKeepAliveTim){
       if(mThreadPoo==null){
           synchronized (ThreadPoo.class){
               if(mThreadPoo==null){
                   mThreadPoo = new ThreadPoo(mCorePoolSize,mMaxPoolSize,mKeepAliveTim);
               }
           }
       }
       return mThreadPoo;
    }
        public class ThreadPoo{
        private int mCorePoolSize;//核心线程
        private  int mMaxPoolSize;//最大线程数
        private int mKeepAliveTime;//保持时间
        private TimeUnit mTimeUnit =TimeUnit.MILLISECONDS;//保持对应的单位
        private BlockingQueue<Runnable> mWorkQueue = new LinkedBlockingDeque<>();//缓存队列
        private ThreadFactory mThreadFactory = Executors.defaultThreadFactory();//线程工厂
        private RejectedExecutionHandler mHandler = new ThreadPoolExecutor.AbortPolicy();//异常捕获器
        private ThreadPoolExecutor mThreadPoolExecutor;

        public ThreadPoo( int mCorePoolSize, int mMaxPoolSize, int mKeepAliveTim){
            this.mCorePoolSize = mCorePoolSize;
            this.mMaxPoolSize = mMaxPoolSize;
            this.mKeepAliveTime = mKeepAliveTim;
        }
        public ThreadPoolExecutor  newThreadPool(){
                                        mThreadPoolExecutor = new ThreadPoolExecutor(
                                                mCorePoolSize,
                                                mMaxPoolSize,
                                                mKeepAliveTime,
                                                mTimeUnit,
                                                mWorkQueue,
                                                mThreadFactory,
                                                mHandler);
            return mThreadPoolExecutor;
        }
    }
}
