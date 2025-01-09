package ca.qc.abelem.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void shouldReturnTrueWhenIsFuturDate() {
        assertTrue(DateUtils.isEqualOrFutureDate(LocalDate.of(2120, 1, 1)));
    }
    @Test
    void shouldReturnFalseWhenIsPassDate() {
        assertFalse(DateUtils.isEqualOrFutureDate(LocalDate.of(1999, 1, 1)));
    }
    @Test
    void shouldReturnTrueForNowDate() {
        assertTrue(DateUtils.isEqualOrFutureDate(LocalDate.now()));
    }
}


