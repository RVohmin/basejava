package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * com.urise.webapp.storage.Storage
 *
 * @author romanvohmin
 * @since 08.04.2020
 */
public interface Storage {

    void clear();

    void update(Resume resume);

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    Resume[] getAll();

    int size();
}
