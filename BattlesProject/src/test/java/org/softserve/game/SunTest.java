package org.softserve.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SunTest {
    @Test
    @DisplayName("Sun smoke test")
    void smokeTest(){
        Sun sun1 = Sun.getInstance();
        Sun sun2 = Sun.getInstance();
        Sun sun3 = Sun.getInstance();

        Sun.DayTime sun1InitTime = sun1.getTime();
        Sun.DayTime sun2InitTime = sun2.getTime();
        Sun.DayTime sun3InitTime = sun3.getTime();

        sun1.setTime(Sun.DayTime.NIGHT);

        Sun.DayTime sun1TimeAfterChangeToSun1 = sun1.getTime();
        Sun.DayTime sun2TimeAfterChangeToSun1 = sun2.getTime();
        Sun.DayTime sun3TimeAfterChangeToSun1 = sun3.getTime();

        sun2.setTime(Sun.DayTime.DAY);

        Sun.DayTime sun1TimeAfterChangeToSun2 = sun1.getTime();
        Sun.DayTime sun2TimeAfterChangeToSun2 = sun2.getTime();
        Sun.DayTime sun3TimeAfterChangeToSun2 = sun3.getTime();

        assertSame(sun1, sun2);
        assertSame(sun2, sun3);
        assertEquals(Sun.DayTime.DAY, sun1InitTime);
        assertEquals(Sun.DayTime.DAY, sun2InitTime);
        assertEquals(Sun.DayTime.DAY, sun3InitTime);
        assertEquals(Sun.DayTime.NIGHT, sun1TimeAfterChangeToSun1);
        assertEquals(Sun.DayTime.NIGHT, sun2TimeAfterChangeToSun1);
        assertEquals(Sun.DayTime.NIGHT, sun3TimeAfterChangeToSun1);
        assertEquals(Sun.DayTime.DAY, sun1TimeAfterChangeToSun2);
        assertEquals(Sun.DayTime.DAY, sun2TimeAfterChangeToSun2);
        assertEquals(Sun.DayTime.DAY, sun3TimeAfterChangeToSun2);
    }

}