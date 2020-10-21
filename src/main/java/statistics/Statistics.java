package statistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is a utility class which provides methods for data analysis.
 * It uses array of doubles as a data source.
 *
 * @author svalyavakilia
 */
public class Statistics {
    /**
     * No need to instantiate this class as all its members are static.
     */
    private Statistics() {}

    /**
     * This method checks if a given array is null or has enough data
     * to analyse.
     *
     * @param data array to check.
     * @param minimumPermissibleLength minimum permissible length of a given
     *                                 array.
     * @throws NullPointerException if data is null.
     * @throws NotEnoughDataException if data has length less than
     *                                permissible minimum.
     */
    private static void checkIfDataIsValid(final double[] data,
                                           final int minimumPermissibleLength)
                                            throws NotEnoughDataException {
        if (data == null) {
            throw new NullPointerException();
        } else if (data.length < minimumPermissibleLength) {
            final String message = "There is not enough data!" +
                                   "Minimum quantity of values needed: " +
                                   minimumPermissibleLength + ".";

            throw new NotEnoughDataException(message);
        }
    }

    /**
     * This method sorts a given array in ascending order.
     *
     * @param data array to sort.
     * @return reference to the same array object but sorted.
     * @throws NullPointerException if data is null.
     */
    public static double[] sortInAscendingOrder(final double[] data) {
        Arrays.sort(data);

        return data;
    }

    /**
     * This method sorts a given array in descending order.
     *
     * @param data array to sort.
     * @return reference to the same array object but sorted.
     * @throws NullPointerException if data is null.
     */
    public static double[] sortInDescendingOrder(final double[] data) {
        Arrays.sort(data);

        for (int index = 0; index < data.length / 2; ++index) {
            swap(data, index, data.length - index - 1);
        }

        return data;
    }

    /**
     * This method swaps two values in a given array.
     *
     * @param data array in which two items will be swapped.
     * @param firstIndex index of the first value to swap.
     * @param secondIndex index of the second value to swap.
     * @throws NullPointerException if data is null.
     */
    private static void swap(final double[] data,
                             final int firstIndex,
                             final int secondIndex) {
        final double valueUnderTheFirstIndex = data[firstIndex];
        data[firstIndex] = data[secondIndex];
        data[secondIndex] = valueUnderTheFirstIndex;
    }

    /**
     * This method finds the minimum value in a given array.
     *
     * @param data array to search minimum value in.
     * @return minimum value in a given array.
     * @throws NullPointerException if data is null.
     * @throws NotEnoughDataException if data is empty.
     */
    public static double min(final double[] data)
                                            throws NotEnoughDataException {
        checkIfDataIsValid(data, 1);

        double currentMinimum = data[0];

        for (int index = 1; index < data.length; ++index) {
            if (data[index] < currentMinimum) {
                currentMinimum = data[index];
            }
        }

        return currentMinimum;
    }

    /**
     * This method finds the maximum value in a given array.
     *
     * @param data array to search maximum value in.
     * @return maximum value in a given array.
     * @throws NullPointerException if data is null.
     * @throws NotEnoughDataException if data is empty.
     */
    public static double max(final double[] data)
                                            throws NotEnoughDataException {
        checkIfDataIsValid(data, 1);

        double currentMax = data[0];

        for (int index = 1; index < data.length; ++index) {
            if (data[index] > currentMax) {
                currentMax = data[index];
            }
        }

        return currentMax;
    }

    /**
     * This method calculates average arithmetic of a given array.
     *
     * @param data array to find average arithmetic of.
     * @return average arithmetic of a given array.
     * @throws NullPointerException if data is null.
     * @throws NotEnoughDataException if data is empty.
     */
    public static double averageArithmetic(final double[] data)
                                            throws NotEnoughDataException {
        checkIfDataIsValid(data, 1);

        double sum = 0;

        for (final double value: data) {
            sum += value;
        }

        return sum / data.length;
    }

    /**
     * This method calculates median of a given array.
     *
     * @param data array to find median of.
     * @return median of a given array.
     * @throws NullPointerException if data is null.
     * @throws NotEnoughDataException if data is empty.
     */
    public static double median(final double[] data)
                                            throws NotEnoughDataException {
        checkIfDataIsValid(data, 1);

        Arrays.sort(data);

        final int dataLength = data.length;

        if ((dataLength & 1) == 1) {
            return data[dataLength / 2];
        } else {
            final double firstPartOfMedian = data[(dataLength / 2) - 1];
            final double secondPartOfMedian = data[dataLength / 2];

            return (firstPartOfMedian + secondPartOfMedian) / 2;
        }
    }

    /**
     * This method returns the median of the first half of a given array
     * (the part before the actual median of a given array) which is also
     * called the first quartile.
     *
     * @param data array to find the first quartile of.
     * @return the first quartile.
     * @throws NullPointerException if data is null.
     * @throws NotEnoughDataException if data has less than two values.
     */
    public static double firstQuartile(final double[] data)
                                            throws NotEnoughDataException {
        checkIfDataIsValid(data, 2);

        sortInAscendingOrder(data);

        return median(Arrays.copyOfRange(data, 0, data.length / 2));
    }

    /**
     * This method returns the median of the second half of a given array
     * (the part after the actual median of a given array) which is also
     * called the third quartile.
     *
     * @param data array to find the third quartile of.
     * @return the third quartile.
     * @throws NullPointerException if data is null.
     * @throws NotEnoughDataException if data has less than two values.
     */
    public static double thirdQuartile(final double[] data)
                                            throws NotEnoughDataException {
        checkIfDataIsValid(data, 2);

        sortInAscendingOrder(data);

        final int dataLength = data.length;

        if ((dataLength & 1) == 1) {
            return median(Arrays.copyOfRange(data,
                                             dataLength / 2 + 1,
                                             dataLength));
        } else {
            return median(Arrays.copyOfRange(data, dataLength / 2, dataLength));
        }
    }

    /**
     * This method calculates an interquartile range (IQR)
     * i.e. the difference between the third quartile and the first quartile.
     *
     * @param data array to find IQR of.
     * @return interquartile range.
     * @throws NullPointerException if data is null.
     * @throws NotEnoughDataException if data has less than two values.
     */
    public static double iqr(final double[] data)
                                            throws NotEnoughDataException {
        checkIfDataIsValid(data, 2);

        return thirdQuartile(data) - firstQuartile(data);
    }

    /**
     * This method calculates 3/2 of an interquartile range (IQR)
     * which is used in statistics.
     *
     * @param data array to find 3/2 IQR of.
     * @return 3/2 of an interquartile range.
     * @throws NullPointerException if data is null.
     * @throws NotEnoughDataException if data has less than two values.
     */
    public static double threeOverTwoIqr(final double[] data)
                                            throws NotEnoughDataException {
        checkIfDataIsValid(data, 2);

        return (double) 3 / 2 * iqr(data);
    }

    /**
     * This method calculates the variance of the given values.
     *
     * @param data array to find variance of.
     * @return variance of the given values.
     * @throws NullPointerException if data is null.
     * @throws NotEnoughDataException if data has less than two values.
     */
    public static double variance(final double[] data)
                                            throws NotEnoughDataException {
        checkIfDataIsValid(data, 2);

        double variance = (double) 1 / (data.length - 1);

        final double averageArithmetic = averageArithmetic(data);

        double sumOfSquaredDifferences = 0;

        for (final double value: data) {
            sumOfSquaredDifferences += Math.pow(value - averageArithmetic, 2);
        }

        variance *= sumOfSquaredDifferences;

        return variance;
    }

    /**
     * This method returns mode(s) and their quantity.
     *
     * @param data array to find mode(s) of.
     * @return an array with modes and (!!!) their quantity as the last value of
     *         the array.
     * @throws NullPointerException is data is null.
     * @throws NotEnoughDataException if data is empty.
     */
    public static double[] mode(final double[] data)
            throws NotEnoughDataException {
        checkIfDataIsValid(data, 1);

        sortInAscendingOrder(data);

        final List<Double> modes = new ArrayList<>();

        int currentMaximumQuantity = 1;
        int currentQuantity;
        int currentIndex = 0;

        double currentValue;

        while (currentIndex < data.length) {
            currentValue = data[currentIndex];
            currentQuantity = 1;

            ++currentIndex;

            while (currentIndex < data.length
                    &&
                    data[currentIndex] == currentValue) {
                ++currentQuantity;

                ++currentIndex;
            }

            if (currentQuantity == currentMaximumQuantity) {
                modes.add(currentValue);
            } else if (currentQuantity > currentMaximumQuantity) {
                currentMaximumQuantity = currentQuantity;

                modes.clear();

                modes.add(currentValue);
            }
        }

        final double[] modesAndTheirQuantity = new double[modes.size() + 1];

        for (int index = 0; index < modes.size(); ++index) {
            modesAndTheirQuantity[index] = modes.get(index);
        }

        modesAndTheirQuantity[modesAndTheirQuantity.length - 1] =
                currentMaximumQuantity;

        return modesAndTheirQuantity;
    }

    /**
     * This method returns an overall statistics on this data.
     *
     * @param data array with values.
     * @return overall statistics.
     * @throws NullPointerException if data is null.
     * @throws NotEnoughDataException if data has less than two values.
     */
    public static String overallStatistics(final double[] data)
                                            throws NotEnoughDataException {
        checkIfDataIsValid(data, 2);

        final StringBuilder modes = new StringBuilder("Mode(s): ");

        final double[] modesAndTheirQuantity = mode(data);

        // [3, 5, 7, 10], length == 4
        //  0, 1, 2, 3
        for (int index = 0; index < modesAndTheirQuantity.length - 1; ++index) {
            if (index != modesAndTheirQuantity.length - 2) {
                modes.append(modesAndTheirQuantity[index]).append(", ");
            } else {
                modes.append(modesAndTheirQuantity[index]);
            }
        }

        final int modeQuantity =
                (int) modesAndTheirQuantity[modesAndTheirQuantity.length - 1];

        modes.append("; quantity: ")
             .append(modeQuantity);

        return "Minimum: " + min(data) + "\n" +
               "Maximum: " + max(data) + "\n" +
               "Average arithmetic: " + averageArithmetic(data) + "\n" +
               modes.toString() + "\n" +
               "First quartile: " + firstQuartile(data) + "\n" +
               "Median: " + median(data) + "\n" +
               "Third quartile: " + thirdQuartile(data) + "\n" +
               "IQR: " + iqr(data) + "\n" +
               "3/2 IQR: " + threeOverTwoIqr(data);
    }
}