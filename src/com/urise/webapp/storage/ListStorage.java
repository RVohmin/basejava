package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * basejava com.urise.webapp.storage.ListStorage
 *
 * @author romanvohmin
 * @version 1
 * @since 10.04.2020 21:40
 */
public class ListStorage extends AbstractStorage {
    private List<Resume> listStorage = new ArrayList<>();

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public void update(Resume resume) {
        if (listStorage.contains(resume)) {
            int index = listStorage.indexOf(resume);
            listStorage.set(index, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void save(Resume resume) {
        if (!listStorage.contains(resume)) {
            listStorage.add(resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        for (Resume item : listStorage) {
            if (item.getUuid().equals(uuid)) {
                return item;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        Iterator<Resume> iterator = listStorage.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getUuid().equals(uuid)) {
                iterator.remove();
                return;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) listStorage.toArray();
    }

    @Override
    public int size() {
        return listStorage.size();
    }



}
