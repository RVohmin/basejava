package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * basejava com.urise.webapp.storage.SortedArrayStorage
 *
 * @author romanvohmin
 * @version 1
 * @since 08.04.2020 17:00
 */
public class SortedArrayStorage extends AbstractArrayStorage {

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
            index = -(index + 1);
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = resume;
            size++;
        }
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
