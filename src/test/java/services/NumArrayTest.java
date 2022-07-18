package services;

import exceptions.ArgumentIsNullException;
import exceptions.ArraySizeOverflowException;
import exceptions.IndexIsOutOfRangeException;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.impls.NumArrayImpl;

import javax.lang.model.type.ArrayType;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class NumArrayTest {
    private NumArray numbers;
    int searchedIndex;

    @BeforeEach
    void setUp() {
        numbers = new NumArrayImpl(5000);
        Random random = new java.util.Random();
        searchedIndex = random.nextInt(numbers.size() - 1);
    }

    @Test
    void checkRemoveMeth() {
        numbers.add(125);
        Integer factRespond = numbers.remove((Integer) 125);
        assertEquals(125, factRespond);
    }

    @Test
    void checkRemoveByIndexMeth() {
        numbers.add(4999, 125);
        Integer factRespond = numbers.remove(4999);
        assertEquals(125, factRespond);
    }

    @Test
    void checkAddMeth() {
        Integer factRespond = numbers.add(125);
        assertEquals(152, factRespond);
    }

    @Test
    void checkAddByIndexMeth() {
        assertThrows(IndexIsOutOfRangeException.class, () -> numbers.add(5000, 125));
    }

    @Test
    void checkIndexOfMeth() {
        numbers.set(searchedIndex, 1986);
        int factRespond = numbers.indexOf(1986);
        assertEquals(searchedIndex, factRespond);
    }

    @Test
    void checkLastIndexOfMeth() {
        numbers.set(searchedIndex, 1986);
        int factRespond = numbers.lastIndexOf(1986);
        assertEquals(2, factRespond);
    }

    @Test
    void checkContainsNullMeth() {
        numbers.set(searchedIndex, 1986);
        assertThrows(ArgumentIsNullException.class, () -> numbers.contains("binary", null));
    }

    @Test
    void checkContainsMeth() {
        numbers.set(searchedIndex, 1986);
        assertFalse(numbers.contains("binary", 1987));
    }

    @Test
    void checkClearMeth() {
        numbers.clear();
        assertEquals(5000, numbers.size());
    }

    @Test
    void checkTheFastestSortMethod() {
        Integer[] numbers2 = Arrays.copyOf(numbers.toArray(), numbers.size());
        Integer[] numbers3 = Arrays.copyOf(numbers.toArray(), numbers.size());
        //System.out.println("Array for Sort Challenge!");
        //System.out.println(Arrays.toString(numbers.toArray()));
        System.out.print("1 - Bubble Sort takes: ");
        long start = System.currentTimeMillis();
        numbers.theFastestSort("bubbleSort", numbers.toArray());
        System.out.println(System.currentTimeMillis() - start + " millisecs");
        System.out.print("2 - Selection Sort takes: ");
        start = System.currentTimeMillis();
        numbers.theFastestSort("selectSort", numbers2);
        System.out.println(System.currentTimeMillis() - start + " millisecs");
        System.out.print("3 - Insertion Sort takes: ");
        start = System.currentTimeMillis();
        numbers.theFastestSort("insertSort", numbers3);
        System.out.println(System.currentTimeMillis() - start + " millisecs");
    }

    @Test
    void checkTheFastestContainsMethod() {
        numbers.set(searchedIndex, 1986);
        System.out.println("Lets search 1986 with " + searchedIndex + " index");
        System.out.print("1 - Liner search takes: ");
        long start = System.currentTimeMillis();
        numbers.contains("liner", 1986);
        System.out.println(System.currentTimeMillis() - start + " millisecs");
        System.out.print("2 - Binary search takes: ");
        start = System.currentTimeMillis();
        numbers.contains("binary", 1986);
        System.out.println(System.currentTimeMillis() - start + " millisecs");
    }

    @Test
    void checkRealSize() {
        assertEquals(5, numbers.size());
    }

    @Test
    void checkIsArrayEmpty() {
        assertFalse(numbers.isEmpty());
    }
}