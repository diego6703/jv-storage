package core.basesyntax.impl;

import core.basesyntax.Storage;

public class StorageImpl<K, V> implements Storage<K, V> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MIN_CAPACITY = 0;
    private static final String CCE_MESSAGE = "cant cast class";
    private K[] keyList;
    private V[] valueList;
    private int numberOfItemsInList = MIN_CAPACITY;

    public StorageImpl() {
        try {
            this.keyList = (K[]) new Object[DEFAULT_CAPACITY];
            this.valueList = (V[]) new Object[DEFAULT_CAPACITY];
        } catch (ClassCastException c) {
            throw new RuntimeException(CCE_MESSAGE);
        }
    }

    @Override
    public void put(K key, V value) {
        boolean isInList = false;
        for (int i = 0; i < numberOfItemsInList; i++) {
            if (key == null && keyList[i] == null) {
                valueList[i] = value;
                isInList = true;
            }
            if (key != null && key.equals(keyList[i])) {
                valueList[i] = value;
                isInList = true;
            }
        }
        if (!isInList) {
            keyList[numberOfItemsInList] = key;
            valueList[numberOfItemsInList] = value;
            numberOfItemsInList++;
        }
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < numberOfItemsInList; i++) {
            if (key == null && keyList[i] == null) {
                return valueList[i];
            }
            if (key != null && key.equals(keyList[i])) {
                return valueList[i];
            }
        }
        return null;
    }

    @Override
    public int size() {
        return numberOfItemsInList;
    }
}
