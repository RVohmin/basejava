package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

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
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
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
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else if (size >= STORAGE_LIMIT) {
            throw new StorageException("Error: storage is full", resume.getUuid());
        }
        doSave(index, resume);
        size++;
    }

    /**
     * delete resume from storage if it exist in storage.
     *
     * @param uuid - uuid resume.
     */
    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        doDelete(index);
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

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
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public abstract void doDelete(int index);

    public abstract void doSave(int index, Resume resume);

    protected abstract int findIndex(String uuid);
}
