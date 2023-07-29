package homework.hm1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class CustomArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public CustomArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity " + capacity);
        }
        elements = new Object[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(elements[i]).append(", ");
        }
        sb.append(elements[size - 1]).append("]");
        return sb.toString();
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elements.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    public void add(T newElement) {
        ensureCapacity(size + 1);
        elements[size++] = newElement;
    }

    public void add(int index, T newElemnent) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = newElemnent;
        size++;
    }

    public boolean addAll(Collection<? extends T> c) {
        Object[] newElements = c.toArray();
        int newElementsArrayLength = newElements.length;

        if (newElementsArrayLength == 0) {
            return false;
        }

        ensureCapacity(size + newElementsArrayLength);

        System.arraycopy(newElements, 0, elements, size, newElementsArrayLength);
        size += newElementsArrayLength;

        return true;
    }

    public void clear() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) elements[index];
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int movedPos = size - index - 1;
        if (movedPos > 0) {
            System.arraycopy(elements, index + 1, elements, index, movedPos);
        }
        elements[--size] = null;
    }

    public void remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, elements[i])) {
                int movedPos = size - i - 1;
                if (movedPos > 0) {
                    System.arraycopy(elements, i + 1, elements, i, movedPos);
                }
                elements[--size] = null;
                return;
            }
        }
    }

    public void sort(Comparator<? super T> c) {
        mergeSort(0, size - 1, c);
    }

    private void mergeSort(int leftIndex, int rightIndex, Comparator<? super T> c) {
        if (leftIndex < rightIndex) {
            int midIndex = leftIndex + (rightIndex - leftIndex) / 2;
            mergeSort(leftIndex, midIndex, c);
            mergeSort(midIndex + 1, rightIndex, c);
            merge(leftIndex, midIndex, rightIndex, c);
        }
    }

    private void merge(int leftBorder, int midIndex, int rightBorder, Comparator<? super T> c) {
        int leftArraySize = midIndex - leftBorder + 1;
        int rightArraySize = rightBorder - midIndex;

        Object[] leftArray = new Object[leftArraySize];
        Object[] rightArray = new Object[rightArraySize];

        System.arraycopy(elements, leftBorder, leftArray, 0, leftArraySize);
        System.arraycopy(elements, midIndex + 1, rightArray, 0, rightArraySize);

        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = leftBorder;

        while (leftIndex < leftArraySize && rightIndex < rightArraySize) {
            T leftElement = (T) leftArray[leftIndex];
            T rightElement = (T) rightArray[rightIndex];

            if (c.compare(leftElement, rightElement) <= 0) {
                elements[currentIndex++] = leftElement;
                leftIndex++;
            } else {
                elements[currentIndex++] = rightElement;
                rightIndex++;
            }
        }

        while (leftIndex < leftArraySize) {
            elements[currentIndex++] = (T) leftArray[leftIndex];
            leftIndex++;
        }

        while (rightIndex < rightArraySize) {
            elements[currentIndex++] = (T) rightArray[rightIndex];
            rightIndex++;
        }
    }
}
