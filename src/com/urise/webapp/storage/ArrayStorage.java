package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void doSave(int index, Resume resume) {
        storage[size] = resume;
        size++;
    }

    @Override
    public void doDelete(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }
    @Override
    public void doUpdate(int index, Resume resume) {
        storage[index] = resume;
    }

    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
