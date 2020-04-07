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

    private Integer find(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    /**
     * Method replaced resume
     *
     * @param resume - new version resume.
     */
    public void update(Resume resume) {
        Integer pos = find(resume.getUuid());
        if (pos != null) {
            storage[pos] = resume;
        } else {
            System.out.println("Error: resume with such uuid absent in storage");
        }
    }

    /**
     * Save new resume to storage.
     *
     * @param resume - new resume.
     */
    public void save(Resume resume) {
        Integer pos = find(resume.getUuid());
        if ((pos == null) && (size < storage.length)) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Error: Such resume is exist, use update() method, or storage is full");
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
        Integer pos = find(uuid);
        return pos != null ? storage[pos] : null;
    }

    /**
     * delete resume from storage if it exist in storage.
     *
     * @param uuid - uuid resume.
     */
    public void delete(String uuid) {
        Integer temp = find(uuid);
        if (temp != null) {
            storage[temp] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Error: there isn't resume with such uuid");
        }
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
