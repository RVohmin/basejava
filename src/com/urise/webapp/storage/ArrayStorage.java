package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    /**
     * clearing storage of resume.
     */
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * Method replaced resume
     *
     * @param resume - new version resume.
     */
    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.printf("Error: absent resume with such uuid - \"%s\"\n", resume.getUuid());
        }
    }

    /**
     * Save new resume to storage.
     *
     * @param resume - new resume.
     */
    public void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if ((index == -1) && (size < storage.length)) {
            storage[size] = resume;
            size++;
        } else {
            System.out.printf("Error: resume \"%s\" is exist, use update() method, or storage is full\n", resume.getUuid());
        }
    }

    /**
     * get resume from storage.
     *
     * @param uuid - uuid resume.
     *
     * @return - com.urise.webapp.model.Resume if it exist in storage.
     */
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.printf("Error: absent resume with such uuid - \"%s\"\n", uuid);
        }
        return index != -1 ? storage[index] : null;
    }

    /**
     * delete resume from storage if it exist in storage.
     *
     * @param uuid - uuid resume.
     */
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.printf("Error: there isn't resume with such uuid - \"%s\"\n", uuid);
        }
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    /**
     * @return size of storage (quantity of resumes)
     */
    public int size() {
        return size;
    }
}
