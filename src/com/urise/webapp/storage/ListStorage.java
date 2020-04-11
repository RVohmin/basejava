package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

/**
 * basejava com.urise.webapp.storage.ListStorage
 *
 * @author romanvohmin
 * @version 1
 * @since 10.04.2020 21:40
 */
public class ListStorage extends AbstractStorage {
    private final List<Resume> listStorage = new ArrayList<>();

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
    protected void doSave(int index, Resume resume) {
        listStorage.add(resume);
    }

    @Override
    protected int findIndex(String uuid) {
        int index = -1;
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                index = i;
            }
        }
        return index;
    }
}
