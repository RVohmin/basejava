package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * basejava com.urise.webapp.storage.AbstractArrayStorage
 *
 * @author romanvohmin
 * @version 1
 * @since 08.04.2020 17:00
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    /**
     * @return size of storage (quantity of resumes)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * get resume from storage.
     *
     * @param uuid - uuid resume.
     *
     * @return - com.urise.webapp.model.Resume if it exist in storage.
     */
    @Override
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.printf("Error: absent resume with such uuid - \"%s\"\n", uuid);
        }
        return index != -1 ? storage[index] : null;
    }

    protected abstract int findIndex(String uuid);
}
