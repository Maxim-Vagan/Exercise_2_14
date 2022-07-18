package services.impls;

import exceptions.ArgumentIsNullException;
import exceptions.ElementNotFoundException;
import exceptions.IndexIsOutOfRangeException;
import services.NumArray;
import java.util.Random;

import java.util.Arrays;

public class NumArrayImpl implements NumArray {
    private Integer[] integersSource;
    private int actualArraySize = 0;
    private final int SIZE_DELTA_INCREMENT = 3;

    public NumArrayImpl(int actualArraySize) {
        this.actualArraySize = actualArraySize;
        integersSource = generateRandomArray(actualArraySize, 0, 10_000);
    }

    public static Integer[] generateRandomArray(int arrayLength, int beginValue, int endValue) {
        Random random = new java.util.Random();
        Integer[] arr = new Integer[arrayLength];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(endValue-beginValue) + beginValue;
        }
        return arr;
    }

    private void forceResizeArray(){
        Integer[] bufferArray = Arrays.copyOf(integersSource, integersSource.length + SIZE_DELTA_INCREMENT);
        integersSource = Arrays.copyOf(bufferArray, bufferArray.length);
    }

    private void forceResizeWithAddingItemArray(int index, Integer item, boolean needIncreaseSize){
        if (needIncreaseSize) forceResizeArray();
        for (int idx = actualArraySize - 1; idx >= index; idx--) {
            integersSource[idx+1] = integersSource[idx];
        }
        integersSource[index] = item;
    }

    private void forceDecreaseArray(int index){
        for (int idx = index + 1; idx <= actualArraySize; idx++) {
            integersSource[idx-1] = integersSource[idx];
        }
    }

    private void flipTwoElements(Integer[] arrayInp, int idx1, int idx2) {
        Integer buffItem = 0;
        buffItem = arrayInp[idx1];
        arrayInp[idx1] = arrayInp[idx2];
        arrayInp[idx2] = buffItem;
    }
    // BubbleSort
    private void bubbleSort(Integer[] inpArray) {
        for (int i = 0; i < inpArray.length-1; i++) {
            for (int j = i+1; j < inpArray.length; j++) {
                if (inpArray[i] > inpArray[j]) flipTwoElements(inpArray, i, j);
            }
        }
    }

    // SelectSort
    private void selectSort(Integer[] inpArray) {
        for (int i = 0; i < inpArray.length-1; i++) {
            int minValueIdx = i;
            for (int j = i+1; j < inpArray.length; j++) {
                if (inpArray[minValueIdx] > inpArray[j]) minValueIdx = j;
            }
            flipTwoElements(inpArray, i, minValueIdx);
        }
    }

    // InsertSort
    private void insertSort(Integer[] inpArray) {
        Integer memoredItem = 0; int j = 0;
        for (int i = 1; i < inpArray.length; i++) {
            memoredItem = inpArray[i];
            j = i;
            while (j > 0 && memoredItem <= inpArray[j - 1]) {
                inpArray[j] = inpArray[j - 1];
                j--;
            }
            inpArray[j] = memoredItem;
        }
    }

    public void theFastestSort(String methodName, Integer[] inpUnsortedArray) {
        switch (methodName) {
            case "bubbleSort": bubbleSort(inpUnsortedArray);
            case "selectSort": selectSort(inpUnsortedArray);
            case "insertSort": insertSort(inpUnsortedArray);
        }
    }

    // linerSearchItem
    private int searchItemIndexByNameLiner(Integer[] inpSortedArray, Integer lookedItem) {
        int memoredIdx = -1;
        for (int i = 0; i < inpSortedArray.length; i++) {
            if (lookedItem.equals(inpSortedArray[i])) {
                memoredIdx = i;
                break;
            }
        }
        return memoredIdx;
    }

    // binarySearchItem
    private int searchItemIndexByNameBinary(Integer[] inpSortedArray, Integer lookedItem) {
        int minIdx = 0;
        int maxIdx = size();
        int middleIdx;
        /*while (minIdx <= maxIdx){
            middleIdx = maxIdx / 2;
            if (lookedItem.equals(inpSortedArray[middleIdx]))
                return middleIdx;
            if (lookedItem > inpSortedArray[middleIdx])
                minIdx = middleIdx + 1;
            else maxIdx = middleIdx - 1;
        }*/
        do {
            middleIdx = (maxIdx + minIdx) / 2;
            if (lookedItem.equals(inpSortedArray[middleIdx]))
                return middleIdx;
            if (lookedItem > inpSortedArray[middleIdx])
                minIdx = middleIdx + 1;
            else maxIdx = middleIdx - 1;
        } while (minIdx <= maxIdx);
        return -1;
    }

    @Override
    public Integer add(Integer item) {
        if (item == null) throw new ArgumentIsNullException("item param string can't be NULL!");
        if (actualArraySize == integersSource.length) forceResizeArray();
        integersSource[actualArraySize] = item;
        actualArraySize++;
        return integersSource[actualArraySize-1];
    }

    @Override
    public Integer add(int index, Integer item) {
        if (item == null) throw new ArgumentIsNullException("item param string can't be NULL!");
        if (index >= actualArraySize || index < 0) throw new IndexIsOutOfRangeException("Wrong index value!");
        forceResizeWithAddingItemArray(index, item, actualArraySize == integersSource.length);
        actualArraySize++;
        return integersSource[index];
    }

    @Override
    public Integer set(int index, Integer item) {
        if (item == null) throw new ArgumentIsNullException("item param string can't be NULL!");
        if (index >= actualArraySize || index < 0) throw new IndexIsOutOfRangeException("Wrong index value!");
        integersSource[index] = item;
        return integersSource[index];
    }

    @Override
    public Integer remove(Integer item) {
        int foundIdx = indexOf(item);
        Integer deletedItem;
        if (foundIdx > -1) {
            deletedItem = get(foundIdx);
            remove(foundIdx);
        }
        else throw new ElementNotFoundException("Item not found for remove!");
        return deletedItem;
    }

    @Override
    public Integer remove(int index) {
        if (index >= actualArraySize || index < 0) throw new IndexIsOutOfRangeException("Wrong index value!");
        Integer deletingItem = integersSource[index];
        forceDecreaseArray(index);
        actualArraySize--;
        return deletingItem;
    }

    @Override
    public boolean contains(String method, Integer item) {
        if (item == null) throw new ArgumentIsNullException("item param string can't be NULL!");
        // return ArrayUtils.contains(integersSource, item);
        if (method.equals("binary")) {
            insertSort(integersSource);
            return searchItemIndexByNameBinary(integersSource, item) != -1;
        } else return searchItemIndexByNameLiner(integersSource, item) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        if (item == null) throw new ArgumentIsNullException("item param string can't be NULL!");
        return searchItemIndexByNameLiner(integersSource, item);
    }

    @Override
    public int lastIndexOf(Integer item) {
        int foundIdx = -1;
        if (item == null) throw new ArgumentIsNullException("item param string can't be NULL!");
        for (int idx = integersSource.length - 1; idx >= 0; idx--){
            if (item.equals(integersSource[idx])){
                foundIdx = idx;
                break;
            }
        }
        return foundIdx;
    }

    @Override
    public Integer get(int index) {
        if (index >= actualArraySize || index < 0) throw new IndexIsOutOfRangeException("Wrong index value!");
        return integersSource[index];
    }

    @Override
    public boolean equals(NumArray otherList) {
        if (otherList == null) throw new ArgumentIsNullException("otherList param StringList can't be NULL!");
        if (actualArraySize != otherList.size()) return false;
        else {
            return Arrays.equals(Arrays.copyOfRange(integersSource, 0, actualArraySize-1),
                    Arrays.copyOfRange(otherList.toArray(), 0, otherList.size() - 1));
        }
    }

    @Override
    public int size() {
        return actualArraySize;
    }

    @Override
    public boolean isEmpty() {
        return actualArraySize == 0 || integersSource.length == 0;
    }

    @Override
    public void clear() {
        integersSource = new Integer[SIZE_DELTA_INCREMENT];
        actualArraySize = 0;
    }

    @Override
    public Integer[] toArray() {
        return integersSource;
    }
}
