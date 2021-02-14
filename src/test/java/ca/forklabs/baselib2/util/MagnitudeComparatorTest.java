package ca.forklabs.baselib2.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ca.forklabs.baselib2.util.comparator.MagnitudeComparator;

public class MagnitudeComparatorTest {

    private final MagnitudeComparator _magnitudeComparator = new MagnitudeComparator();

    @Test
    public void testCompareBothPositive() {
        // | pi | > | e |
        var pi = Math.PI;
        var e = Math.E;
        int comparison = _magnitudeComparator.compare(pi, e);
        assertTrue(comparison > 0);
    }

    @Test
    public void testCompareBothNegative() {
        // | -pi | > | -e |
        var pi = -Math.PI;
        var e = -Math.E;
        int comparison = _magnitudeComparator.compare(pi, e);
        assertTrue(comparison > 0);
    }

    @Test
    public void testCompareD1PositiveD2Negative() {
        // | pi | > | -e |
        var pi = Math.PI;
        var e = -Math.E;
        int comparison = _magnitudeComparator.compare(pi, e);
        assertTrue(comparison > 0);
    }

    @Test
    public void testCompareD1NegativeD2Positive() {
        // | -pi | > | e |
        var pi = Math.PI;
        var e = -Math.E;
        int comparison = _magnitudeComparator.compare(pi, e);
        assertTrue(comparison > 0);
    }

    @Test
    public void testCompareSame() {
        // | e | == | e |
        var e1 = Math.E;
        var e2 = Math.E;
        int comparison = _magnitudeComparator.compare(e1, e2);
        assertTrue(comparison == 0);
    }

    @Test
    public void testCompareSameButDifferentSign() {
        // | -pi | == | pi |
        var minus_pi = -Math.PI;
        var plus_pi = Math.PI;
        int comparison = _magnitudeComparator.compare(minus_pi, plus_pi);
        assertTrue(comparison == 0);
    }

    @Test
    public void testNullPointerOnFirstParameter() {
        Integer i1 = null;
        Integer i2 = Integer.valueOf(42);
        /* var exception = */ assertThrows(NullPointerException.class, () -> _magnitudeComparator.compare(i1, i2));
    }

    @Test
    public void testNullPointerOnSecondParameter() {
        Integer i1 = Integer.valueOf(27);
        Integer i2 = null;
        /* var exception = */ assertThrows(NullPointerException.class, () -> _magnitudeComparator.compare(i1, i2));
    }

}
