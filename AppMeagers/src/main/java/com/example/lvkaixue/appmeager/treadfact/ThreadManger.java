package com.example.lvkaixue.appmeager.treadfact;

public class ThreadManger {
    private static final  int time =720*100*100*100;
    public static void newInstanceThreadPool(Runnable task){
        ThreadPool mThreadPool = null;
        if(mThreadPool  == null){
            mThreadPool =  new ThreadPool();
        }
        mThreadPool.newThreadPoo(3, 5, time).newThreadPool().execute(task);
    }
}
