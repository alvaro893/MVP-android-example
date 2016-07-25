/**
 * Copyright (C) 2016 Alvaro Bolanos Rodriguez
 */
package es.alvaroweb.mpvexample;

/*
 * TODO: Create JavaDoc
 */
public class Chrono implements Runnable{
    private static final long SLEEP_TIME = 1000L;
    private static final long LIMIT_TIME = 1000000L;

    public long time;
    private Thread mThread;
    private TimeChangedListener mListener;
    private boolean isRunning = false;

    public Chrono(long startingTime, TimeChangedListener listener) {
        this.time = startingTime;
        mListener = listener;
        createNewThread();
    }

    public Chrono(TimeChangedListener listener) {
        this(0, listener);
    }

    @Override
    public void run() {
        try {
            while(isRunning && time < LIMIT_TIME){
                Thread.sleep(SLEEP_TIME);
                if(isRunning()){
                    time++;
                    mListener.OnTimeChanged(time);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unPause() {
        if(!mThread.isAlive() && !isRunning()){
            isRunning = true;
            createNewThread();
            mThread.start();
        }
    }

    public void pause(){
        if(isRunning){
            isRunning = false;
        }
    }

    public void setToZero(){
        isRunning = false;
        time = 0L;
        mListener.OnTimeChanged(time);
    }

    private void createNewThread(){
        mThread = new Thread(this);
    }

    public boolean isRunning() {
        return isRunning;
    }


    public interface TimeChangedListener {
        void OnTimeChanged(long time);
    }
}
