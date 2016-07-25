package es.alvaroweb.mpvexample;

/**
 * Copyright (C) 2016 Alvaro Bolanos Rodriguez
 */
public interface ChronoView {
    void setPresenter();
    void start();

    void reset();

    void updateTime(CharSequence text);

    void setPausedState();

    void setUnpausedState();

    void setRestartedState();
}
