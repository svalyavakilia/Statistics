package statistics;

import java.util.Arrays;

/**
 * This is a utility class which provides methods for data analysis.
 * Uses array of doubles as a data source.
 *
 * @author svalyavakilia
 */
public class Statistics {
    /**
     * This method checks if given array is null or has more than two items.
     *
     * @param data array to check.
     * @param minimumPermissibleLength minimum permissible length of a given
     *                                 array.
     * @throws InvalidDataException if data is null or has less than minimum
     *                              permissible length.
     */
    private static void checkIfDataIsValid(final double[] data,
                                           final int minimumPermissibleLength)
                                            throws InvalidDataException {
        if ((data == null) || (data.length < minimumPermissibleLength)) {
            final String message = "Data is null or has not enough data!";

            throw new InvalidDataException(message);
        }
    }

    /**
     * This method sorts given array in ascending order.
     *
     * @param data array to sort.
     * @return reference to the same array object but sorted.
     * @throws InvalidDataException if data is null.
     */
    public static double[] sortInAscendingOrder(final double[] data)
                                            throws InvalidDataException {
        checkIfDataIsValid(data, 0);

        Arrays.sort(data);

        return data;
    }

    /**
     * This method sorts given array in descending order.
     *
     * @param data array to sort.
     * @return reference to the same array object but sorted.
     * @throws InvalidDataException if data is null.
     */
    public static double[] sortInDescendingOrder(final double[] data)
                                            throws InvalidDataException {
        checkIfDataIsValid(data, 0);

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
     * @throws InvalidDataException if data is null or has less than two
     *                              elements.
     */
    private static void swap(final double[] data,
                             final int firstIndex,
                             final int secondIndex)
                                            throws InvalidDataException {
        checkIfDataIsValid(data, 2);

        final double valueUnderTheFirstIndex = data[firstIndex];
        data[firstIndex] = data[secondIndex];
        data[secondIndex] = valueUnderTheFirstIndex;
    }

    /**
     * This method finds the minimum value in a given array.
     *
     * @param data array to search minimum value in.
     * @return minimum value in a given array.
     * @throws InvalidDataException if data is null or empty.
     */
    public static double min(final double[] data) throws InvalidDataException {
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
     * @throws InvalidDataException if data is null or empty.
     */
    public static double max(final double[] data) throws InvalidDataException {
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
     * @throws InvalidDataException if data is null or empty.
     */
    public static double averageArithmetic(final double[] data)
                                            throws InvalidDataException {
        checkIfDataIsValid(data, 1);

        double sum = 0;

        for (final double value: data) {
            sum += value;
        }

        return sum / data.length;
    }

    /**
     * This method returns median of a given array.
     *
     * @param data array to find median of.
     * @return median of a given array.
     * @throws InvalidDataException if data is null or empty.
     */
    public static double median(final double[] data)
                                            throws InvalidDataException {
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
     * (i.e. the part before the actual median of a given array) which is also
     * called the first quartile.
     *
     * @param data array to find the first quartile of.
     * @return the first quartile.
     * @throws InvalidDataException if data is null or has less than two
     *                              elements.
     */
    public static double firstQuartile(final double[] data)
                                            throws InvalidDataException {
        checkIfDataIsValid(data, 2);

        sortInAscendingOrder(data);

        return median(Arrays.copyOfRange(data, 0, data.length / 2));
    }

    /**
     * This method returns the median of the second half of a given array
     * (i.e. the part after the actual median of a given array) which is also
     * called the third quartile.
     *
     * @param data array to find the third quartile of.
     * @return the third quartile.
     * @throws InvalidDataException if data is null or has less than two
     *                              elements.
     */
    public static double thirdQuartile(final double[] data)
                                            throws InvalidDataException {
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
     * @throws InvalidDataException if data is null or has less than two
     *                              elements.
     */
    public static double iqr(final double[] data) throws InvalidDataException {
        checkIfDataIsValid(data, 2);

        return thirdQuartile(data) - firstQuartile(data);
    }

    /**
     * This method calculates 3/2 of an interquartile range (IQR)
     * which is used in statistics.
     *
     * @param data array to find 3/2 IQR of.
     * @return 3/2 of an interquartile range.
     * @throws InvalidDataException if data is null or has less than two
     *                              elements.
     */
    public static double threeOverTwoIqr(final double[] data)
                                            throws InvalidDataException {
        checkIfDataIsValid(data, 2);

        return (double) 3 / 2 * iqr(data);
    }
}