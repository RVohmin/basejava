package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.getAll().length);
    }

    @Test
    public void update() {
        Resume resume = new Resume("uuid2");
        assertNotSame(resume, storage.get("uuid2"));
        storage.update(resume);
        assertEquals(resume, storage.get("uuid2"));
    }

    @Test
    public void save() {
        clear();
        storage.save(new Resume(UUID_1));
        assertEquals(1, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void whenSaveExistResumeThenException() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void whenStorageFullThenException() {
        storage.clear();
        for (int i = 0; i <= 9999; i++) {
            storage.save(new Resume("uuid" + i));
        }
        storage.save(new Resume("name"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("uuid5");
    }

    @Test
    public void getAll() {
        Resume[] expexted = new Resume[] {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        assertArrayEquals(expexted, storage.getAll());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        assertEquals(storage.getAll()[0], storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }
}