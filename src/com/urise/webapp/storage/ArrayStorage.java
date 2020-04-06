package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    /**
     * Method check for resume present in storage.
     * @param uuid - String uuid.
     * @return boolean true if present.
     */
    private boolean checkPresent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * clearing storage of resume.
     */
    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    /**
     * Method replaced resume
     * @param resume - new version resume.
     */
    public void update(Resume resume) {
        if (checkPresent(resume.getUuid())) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(resume.getUuid())) {
                    storage[i] = resume;
                }
            }
        } else {
            System.out.println("Error: resume with such uuid absent");
        }
    }

    /**
     * Save new resume to storage.
     * @param resume - new resume.
     */
    public void save(Resume resume) {
        if (!checkPresent(resume.getUuid())) {
            if (size < storage.length) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("Error: com.urise.webapp.model.Resume cannot be saved - storage is full");
            }
        } else {
            System.out.println("Error: Such resume is exist, use update() method");
        }
    }

    /**
     * get resume from storage.
     * @param uuid - uuid resume.
     * @return - com.urise.webapp.model.Resume if it exist in storage.
     */
    public Resume get(String uuid) {
        if (checkPresent(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        } else {
            System.out.printf("Error: resume with uuid \"%s\" is absent in storage \n", uuid);
        }
        return null;
    }

    /**
     * delete resume from storage if it exist in storage.
     * @param uuid - uuid resume.
     */
    public void delete(String uuid) {
        if (checkPresent(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    System.arraycopy(storage, i + 1, storage, i, size - i - 1);
                    size--;
                    break;
                }
            }
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
