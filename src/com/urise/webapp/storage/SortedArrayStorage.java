package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

/**
 * basejava com.urise.webapp.storage.SortedArrayStorage
 *
 * @author romanvohmin
 * @version 1
 * @since 08.04.2020 17:00
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void doInsert(Resume resume, int index) {
        index = -(index + 1);
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }

    @Override
    public void doDeleteElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Comparator<Resume> uuidComparator = Comparator.comparing(Resume::getUuid);
        Resume searchKey = new Resume(uuid, "name");
        return Arrays.binarySearch(storage, 0, size, searchKey, uuidComparator);
    }
}
