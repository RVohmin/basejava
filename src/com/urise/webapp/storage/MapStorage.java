package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

/**
 * basejava com.urise.webapp.storage.MapStorage
 *
 * @author romanvohmin
 * @version 1
 * @since 10.04.2020 21:39
 */
public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    protected Resume doGet(int index, String uuid) {
        return mapStorage.get(uuid);
    }

    @Override
    protected void doUpdate(int index, Resume resume) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(int index, String uuid) {
        mapStorage.remove(uuid);
    }

    @Override
    protected void doSave(int index, Resume resume) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected int findIndex(String uuid) {
        int index = -1;
        if (mapStorage.get(uuid) != null) {
            index = 1;
        }
        return index;
    }

    @Override
    public Resume[] getAll() {
        return (mapStorage.values().toArray(new Resume[0]));
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
