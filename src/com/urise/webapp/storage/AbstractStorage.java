package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

/**
 * basejava com.urise.webapp.storage.AbstractStorage
 *
 * @author romanvohmin
 * @version 1
 * @since 10.04.2020 21:35
 */
public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        checkException(resume);
        doSave(index, resume);
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        doDelete(index);
    }

    @Override
    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        doUpdate(index, resume);
    }

    @Override
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(index);
    }

    protected abstract Resume doGet(int index);

    protected abstract void doUpdate(int index, Resume resume);

    protected abstract void doDelete(int index);

    protected abstract void doSave(int index, Resume resume);

    protected abstract void checkException(Resume resume);

    protected abstract int findIndex(String uuid);


}
