package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage extends AbstractArrayStorage {


    /**
     * clearing storage of resume.
     */
    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * Method replaced resume
     *
     * @param resume - new version resume.
     */
    @Override
    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index == -1) {
            System.out.printf("Error: absent resume with such uuid - \"%s\"\n", resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    /**
     * Save new resume to storage.
     *
     * @param resume - new resume.
     */
    @Override
    public void save(Resume resume) {
        if (findIndex(resume.getUuid()) != -1) {
            System.out.printf("Error: resume \"%s\" is exist, use update() method\n", resume.getUuid());
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Error: storage is full\n");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    /**
     * delete resume from storage if it exist in storage.
     *
     * @param uuid - uuid resume.
     */
    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.printf("Error: there isn't resume with such uuid - \"%s\"\n", uuid);
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
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
