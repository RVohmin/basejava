package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

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
        assertEquals(3, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void whenSaveExistResumeThenException() {
        Resume resume = new Resume("uuid2");
        storage.save(resume);
    }

    @Test(expected = StorageException.class)
    public void whenStorageFullThenException() {
        storage.clear();
        for (int i = 0; i <= 9999; i++) {
            storage.save(new Resume("uuid" + i));
        }
        storage.save(new Resume("name"));
    }

    @Test
    public void delete() {
        storage.delete("uuid2");
        String[] expected = {"uuid1", "uuid3"};
        assertEquals(2, storage.getAll().length);
        assertEquals("uuid1", storage.getAll()[0].getUuid());
        assertEquals("uuid3", storage.getAll()[1].getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("uuid5");
    }

    @Test
    public void getAll() {
        assertEquals(3, storage.getAll().length);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Resume resume = new Resume("Super");
        storage.save(resume);
        assertEquals(resume, storage.get("Super"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }
}