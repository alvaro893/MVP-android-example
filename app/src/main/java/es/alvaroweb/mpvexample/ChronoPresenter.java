package es.alvaroweb.mpvexample;

/**
 * Copyright (C) 2016 Alvaro Bolanos Rodriguez
 */
public class ChronoPresenter implements Chrono.TimeChangedListener {
    private ChronoView chronoView;
    private Chrono chrono;

    public ChronoPresenter(ChronoView chronoView) {
        this.chronoView = chronoView;
        this.chrono = new Chrono(this);
    }

    public void toggle(){
        if(chrono.isRunning()){
            chrono.pause();
            chronoView.setPausedState();
        }else{
            chrono.unPause();
            chronoView.setUnpausedState();
        }
    }

    public void reset() {
        chrono.setToZero();
        chrono.pause();
        chronoView.setRestartedState();
    }

    @Override
    public void OnTimeChanged(long time) {
        long secs = time % 60L;
        long mins = (time / 60L) % 60L;
        long hours = time / 3600L;

        String formattedTime = String.format("%02d:%02d:%02d", hours, mins, secs);
        chronoView.updateTime(formattedTime);
    }

}
