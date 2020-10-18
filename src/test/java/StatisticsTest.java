import org.junit.Assert;
import org.junit.Test;
import statistics.InvalidDataException;
import statistics.Statistics;

public class StatisticsTest {
    @Test
    public void testSortInAscendingOrder() throws InvalidDataException {
        final double[] sorted = {3, 5, 7, 8, 12, 17, 18, 21, 69, 169};
        final double[] notSorted = {69, 12, 3, 169, 7, 17, 8, 21, 5, 18};

        Assert.assertArrayEquals(sorted,
                                 Statistics.sortInAscendingOrder(notSorted),
                                 0);

        Assert.assertArrayEquals(new double[] {},
                                 Statistics.sortInAscendingOrder(new double[0]),
                                 0);

        Assert.assertArrayEquals(new double[] {1},
                                 Statistics.sortInAscendingOrder
                                                        (new double[] {1}),
                                 0);

        Assert.assertArrayEquals(new double[] {17, 18},
                                 Statistics.sortInAscendingOrder
                                                        (new double[] {18, 17}),
                                 0);
    }

    @Test
    public void testSortInDescendingOrder() throws InvalidDataException {
        final double[] sorted = {169, 69, 21, 18, 17, 12, 8, 7, 5, 3};
        final double[] notSorted = {69, 12, 3, 169, 7, 17, 8, 21, 5, 18};

        Assert.assertArrayEquals(sorted,
                                 Statistics.sortInDescendingOrder(notSorted),
                                 0);

        Assert.assertArrayEquals(new double[] {},
                                 Statistics.sortInDescendingOrder
                                                        (new double[] {}),
                                 0);

        Assert.assertArrayEquals(new double[] {19},
                                 Statistics.sortInDescendingOrder
                                                        (new double[] {19}),
                                 0);

        Assert.assertArrayEquals(new double[] {169, 69},
                                 Statistics.sortInDescendingOrder
                                                    (new double[] {69, 169}),
                                 0);
    }

    @Test
    public void testMin() throws InvalidDataException {
        Assert.assertEquals(-1, Statistics.min(new double[] {4.5, -1, 12}), 0);

        Assert.assertEquals(1, Statistics.min(new double[] {1}), 0);
    }

    @Test
    public void testMax() throws InvalidDataException {
        Assert.assertEquals(-8, Statistics.max(new double[] {-10, -9, -8}), 0);

        Assert.assertEquals(19, Statistics.max(new double[] {19}), 0);
    }

    @Test
    public void testAverageArithmetic() throws InvalidDataException {
        final double[] data = {5.5, 6.5, 8, 9, 10, 9.4, 8.6,
                               9.5, 7.5, 7.6, 10.4, 10.5, 8.5};

        Assert.assertEquals(8.5, Statistics.averageArithmetic(data), 0.1);

        Assert.assertEquals(5,
                            Statistics.averageArithmetic(new double[] {5}),
                            0);
    }

    @Test
    public void testMedian() throws InvalidDataException {
        final double[] data = {5.5, 6.5, 8, 9, 10, 9.4, 8.6,
                               9.5, 7.5, 7.6, 10.4, 10.5, 8.5};

        Assert.assertEquals(8.6, Statistics.median(data), 0);
    }

    @Test
    public void testFirstQuartile() throws InvalidDataException {
        final double[] data = {5.5, 6.5, 8, 9, 10, 9.4, 8.6,
                               9.5, 7.5, 7.6, 10.4, 10.5, 8.5};

        Assert.assertEquals(7.55, Statistics.firstQuartile(data), 0);

        Assert.assertEquals(1.5,
                            Statistics.firstQuartile(new double[] {1, 2, 2, 3}),
                            0);

        Assert.assertEquals(1,
                            Statistics.firstQuartile(new double[] {1, 2, 3}),
                            0);

        Assert.assertEquals(1,
                            Statistics.firstQuartile(new double[] {1, 1}),
                            0);
    }

    @Test
    public void testThirdQuartile() throws InvalidDataException {
        final double[] data = {5.5, 6.5, 8, 9, 10, 9.4, 8.6,
                               9.5, 7.5, 7.6, 10.4, 10.5, 8.5};

        Assert.assertEquals(9.75, Statistics.thirdQuartile(data), 0);

        Assert.assertEquals(2.5,
                            Statistics.thirdQuartile(new double[] {1, 2, 2, 3}),
                            0);

        Assert.assertEquals(69,
                            Statistics.thirdQuartile(new double[] {19, 69}),
                            0);

        Assert.assertEquals(18,
                            Statistics.thirdQuartile(new double[] {12, 17, 18}),
                            0);
    }

    @Test
    public void testIqr() throws InvalidDataException {
        final double[] data = {5.5, 6.5, 8, 9, 10, 9.4, 8.6,
                               9.5, 7.5, 7.6, 10.4, 10.5, 8.5};

        Assert.assertEquals(2.2, Statistics.iqr(data), 0);
    }

    @Test
    public void testThreeOverTwoIqr() throws InvalidDataException {
        final double[] data = {5.5, 6.5, 8, 9, 10, 9.4, 8.6,
                               9.5, 7.5, 7.6, 10.4, 10.5, 8.5};

        Assert.assertEquals((double) 3/2 * 2.2,
                            Statistics.threeOverTwoIqr(data),
                            0);
    }
}