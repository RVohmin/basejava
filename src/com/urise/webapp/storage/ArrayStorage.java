package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage extends AbstractArrayStorage {

    /**
     * Save new resume to storage.
     *
     * @param resume - new resume.
     */
    @Override
    public void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            System.out.printf("Error: resume \"%s\" is exist, use update() method\n", resume.getUuid());
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Error: storage is full\n");
        } else {
            storage[size] = resume;
            size++;
        }
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
