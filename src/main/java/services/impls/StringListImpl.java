package services.impls;

import exceptions.ArgumentIsNullException;
import exceptions.ElementNotFoundException;
import exceptions.IndexIsOutOfRangeException;
import org.apache.commons.lang3.ArrayUtils;
import services.StringList;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private String[] dataStrSource;
    private int actualArraySize = 0;
    private final int SIZE_DELTA_INCREMENT = 3;

    public StringListImpl(int inpSize) {
        this.dataStrSource = new String[inpSize];
    }

    private void forceResizeArray(){
        String[] bufferArray = Arrays.copyOf(dataStrSource, dataStrSource.length + SIZE_DELTA_INCREMENT);
        dataStrSource = Arrays.copyOf(bufferArray, bufferArray.length);
    }

    private void forceResizeWithAddingItemArray(int index, String item, boolean needIncreaseSize){
        if (needIncreaseSize) forceResizeArray();
        for (int idx = actualArraySize - 1; idx >= index; idx--) {
            dataStrSource[idx+1] = dataStrSource[idx];
        }
        dataStrSource[index] = item;
    }

    private void forceDecreaseArray(int index){
        for (int idx = index + 1; idx <= actualArraySize; idx++) {
            dataStrSource[idx-1] = dataStrSource[idx];
        }
    }

    @Override
    public String add(String item) {
        if (item == null) throw new ArgumentIsNullException("item param string can't be NULL!");
        if (actualArraySize == dataStrSource.length) forceResizeArray();
        dataStrSource[actualArraySize] = item;
        actualArraySize++;
        return dataStrSource[actualArraySize-1];
    }

    @Override
    public String add(int index, String item) {
        if (item == null) throw new ArgumentIsNullException("item param string can't be NULL!");
        if (index >= actualArraySize || index < 0) throw new IndexIsOutOfRangeException("Wrong index value!");
        forceResizeWithAddingItemArray(index, item, actualArraySize == dataStrSource.length);
        actualArraySize++;
        return dataStrSource[index];
    }

    @Override
    public String set(int index, String item) {
        if (item == null) throw new ArgumentIsNullException("item param string can't be NULL!");
        if (index >= actualArraySize || index < 0) throw new IndexIsOutOfRangeException("Wrong index value!");
        dataStrSource[index] = item;
        return dataStrSource[index];
    }

    @Override
    public String remove(String item) {
        int foundIdx = indexOf(item);
        String deletedItem;
        if (foundIdx > -1) {
            deletedItem = get(foundIdx);
            remove(foundIdx);
        }
        else throw new ElementNotFoundException("Item not found for remove!");
        return deletedItem;
    }

    @Override
    public String remove(int index) {
        if (index >= actualArraySize || index < 0) throw new IndexIsOutOfRangeException("Wrong index value!");
        String deletingItem = dataStrSource[index];
        forceDecreaseArray(index);
        actualArraySize--;
        return deletingItem;
    }

    @Override
    public boolean contains(String item) {
        if (item == null) throw new ArgumentIsNullException("item param string can't be NULL!");
        return ArrayUtils.contains(dataStrSource, item);
    }

    @Override
    public int indexOf(String item) {
        int foundIdx = -1;
        if (item == null) throw new ArgumentIsNullException("item param string can't be NULL!");
        for (int idx = 0; idx < dataStrSource.length; idx++){
            if (item.equals(dataStrSource[idx])){
                foundIdx = idx;
                break;
            }
        }
        return foundIdx;
    }

    @Override
    public int lastIndexOf(String item) {
        int foundIdx = -1;
        if (item == null) throw new ArgumentIsNullException("item param string can't be NULL!");
        for (int idx = dataStrSource.length - 1; idx >= 0; idx--){
            if (item.equals(dataStrSource[idx])){
                foundIdx = idx;
                break;
            }
        }
        return foundIdx;
    }

    @Override
    public String get(int index) {
        if (index >= actualArraySize || index < 0) throw new IndexIsOutOfRangeException("Wrong index value!");
        return dataStrSource[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) throw new ArgumentIsNullException("otherList param StringList can't be NULL!");
        if (actualArraySize != otherList.size()) return false;
        else {
            return Arrays.equals(Arrays.copyOfRange(dataStrSource, 0, actualArraySize-1),
                    Arrays.copyOfRange(otherList.toArray(), 0, otherList.size() - 1));
        }
    }

    @Override
    public int size() {
        return actualArraySize;
    }

    @Override
    public boolean isEmpty() {
        return actualArraySize == 0 || dataStrSource.length == 0;
    }

    @Override
    public void clear() {
        dataStrSource = new String[SIZE_DELTA_INCREMENT];
        actualArraySize = 0;
    }

    @Override
    public String[] toArray() {
        return dataStrSource;
    }
}
