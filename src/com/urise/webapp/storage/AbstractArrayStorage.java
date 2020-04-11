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
public abstract class AbstractArrayStorage extends AbstractStorage {
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

    @Override
    protected void checkException(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Error: storage is full", resume.getUuid());
        }
    }

    @Override
    protected void doUpdate(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected Resume doGet(int index) {
        return storage[index];
    }

    public abstract void doDelete(int index);

    public abstract void doSave(int index, Resume resume);

    protected abstract int findIndex(String uuid);
}
