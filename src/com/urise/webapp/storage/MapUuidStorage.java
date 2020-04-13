package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * basejava com.urise.webapp.storage.MapStorage
 *
 * @author romanvohmin
 * @version 1
 * @since 10.04.2020 21:39
 */
public class MapUuidStorage extends AbstractStorage {
    private final Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        mapStorage.remove((String) searchKey);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return mapStorage.get((String) searchKey);
    }

    @Override
    public List<Resume> getList() {

        return new ArrayList<>(mapStorage.values());
    }

    @Override
    public int size() {
        return mapStorage.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return mapStorage.containsKey((String) searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

}
