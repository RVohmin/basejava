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
    protected void doUpdate(int index, Resume resume) {
        listStorage.set(index, resume);
    }

    @Override
    protected Resume doGet(int index) {
        return listStorage.get(index);
    }

    @Override
    protected void doDelete(int index) {
        listStorage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) listStorage.toArray();
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    protected void checkException(Resume resume) {
    }

    @Override
    protected void doSave(int index, Resume resume) {
        listStorage.add(resume);
    }

    @Override
    protected int findIndex(String uuid) {
        int index = listStorage.indexOf(new Resume(uuid));
        for (Resume item : listStorage) {
            if (item.getUuid().equals(uuid)) {
                index = listStorage.indexOf(item);
            }
        }
        return index;
    }
}
