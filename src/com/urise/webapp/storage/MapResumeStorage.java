package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
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
public class MapResumeStorage extends AbstractStorage {
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
        Resume r = (Resume) searchKey;
        mapStorage.remove(r.getUuid());
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        Resume r = (Resume) searchKey;
        return mapStorage.get(r.getUuid());
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = Arrays.asList(mapStorage.values().toArray(new Resume[0]));
        list.sort(compareUuid.thenComparing(compareFullName));
        return list;
    }

    @Override
    public int size() {
        return mapStorage.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return mapStorage.get(uuid);
    }

}
