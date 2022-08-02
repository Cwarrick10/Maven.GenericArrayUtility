package com.zipcodewilmington.arrayutility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;
import java.lang.Integer;
import java.lang.reflect.Array;
/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<T> {
    private T[] array;

    public ArrayUtility(T[] inputArray) {
        this.array = inputArray;

    }

    public Integer getNumberOfOccurrences(T valueToEvaluate) {
        //        int count = 0;
        //        for(int i = 0; i<= objectArray.length-1; i++) {
        //            if(valueToEvaluate.equals(objectArray[i])) {
        //                count++;
        //            }
        //        }

        return (int) Arrays.stream(this.array)
                .filter(item -> item.equals(valueToEvaluate))
                .count();
    }

    public Integer countDuplicatesInMerge(T[] arrayToMerge, T valueToEvaluate) {
        //    int counter = 0;
        //    Integer [] mergedArray = new Integer[input.length + arrayToMerge.length];
        this.array = mergeArray(arrayToMerge);

        return countDuplicates(valueToEvaluate);
    }

    private Integer countDuplicates(T valueToEvaluate) {
        return (int) Arrays.stream(this.array)
                .filter(item -> item.equals(valueToEvaluate))
                .count();
    }

    private T[] mergeArray(T[] arrayToMerge) {
        return Stream.concat(Arrays.stream(this.array), Arrays.stream(arrayToMerge))
                .toArray(this::getNewArray);
    }

    private T[] getNewArray(Integer size) {
        return (T[]) Array.newInstance(this.array.getClass().getComponentType(), size);
    }

    public T[] removeValue(T valueToRemove) {
        return Arrays.stream(this.array)
                .filter(item -> !item.equals(valueToRemove))
                .toArray(this::getNewArray);
    }

    public T getMostCommonFromMerge(T[] arrayToMerge) {
        this.array = mergeArray(arrayToMerge);
        Integer max = getMaxDuplicateCount();
        return Arrays.stream(array)
                .filter(item -> getNumberOfOccurrences(item) >= max)
                .findFirst().orElse(null);
    }

    private Integer getMaxDuplicateCount() {
        return Arrays.stream(this.array)
                .map(item -> countDuplicates(item))
                .max(Comparator.naturalOrder()).orElse(0);
    }
}



