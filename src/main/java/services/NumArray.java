package services;

public interface NumArray {
    Integer add(Integer item);

    Integer add(int index, Integer item);

    Integer set(int index, Integer item);

    Integer remove(Integer item);

    Integer remove(int index);

    boolean contains(String method, Integer item);

    int indexOf(Integer item);

    int lastIndexOf(Integer item);

    Integer get(int index);

    boolean equals(NumArray otherList);

    int size();

    boolean isEmpty();

    void clear();

    Integer[] toArray();

    void theFastestSort(String methodName, Integer[] unsortedArray);

}
