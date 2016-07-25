package es.alvaroweb.mpvexample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Copyright (C) 2016 Alvaro Bolanos Rodriguez
 */
public class ChronoTest {

    private Chrono chrono;

    @Before
    public void setUp() throws Exception {
        chrono = new Chrono(new Chrono.TimeChangedListener() {
            @Override
            public void OnTimeChanged(long time) {
                System.out.print("time:" + time);
            }
        });
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testUnPause() throws Exception {
        chrono.unPause();
        assertTrue(chrono.isRunning());
        assertEquals(chrono.time, 0L);
    }

    @Test
    public void testPause() throws Exception {
        chrono.pause();
        assertTrue(!chrono.isRunning());
    }

    @Test
    public void testSetToZero() throws Exception {
        chrono.unPause();
        assertTrue(chrono.isRunning());
        Thread.sleep(1000L);

        chrono.setToZero();
        //Thread.sleep(100L);
        assertFalse(chrono.isRunning());
        assertEquals(chrono.time, 0L);
    }
}