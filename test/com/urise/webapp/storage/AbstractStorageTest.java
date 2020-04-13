package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public abstract class AbstractStorageTest {
    protected final Storage storage;
    private static final Resume RESUME_1 = new Resume("uuid1", "Ivan Petrov");
    private static final Resume RESUME_2 = new Resume("uuid2", "Alex Ivanov");
    private static final Resume RESUME_3 = new Resume("uuid3", "Bob Marley");

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume resume = new Resume("uuid2", "Alex Ivanov");
        assertNotSame(resume, storage.get("uuid2"));
        storage.update(resume);
        assertEquals(resume, storage.get("uuid2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistingThenException() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void save() {
        Resume resume4 = new Resume("UUID_4", "Alex");
        storage.save(resume4);
        assertEquals(4, storage.size());
        assertEquals("UUID_4", storage.get("UUID_4").getUuid());
    }

    @Test(expected = ExistStorageException.class)
    public void whenSaveExistResumeThenExistStorageException() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid2");
        assertEquals(2, storage.size());
        storage.get("uuid2");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy!");
    }

    @Test
    public void get() {
        Resume RESUME_4 = new Resume("uuid4", "Ivan Petrov");
        storage.save(RESUME_4);
        assertEquals("Ivan Petrov", storage.get("uuid4").getFullName());
    }

    @Test
    public void getAllSorted() {
        List<Resume> expexted = List.of(RESUME_1, RESUME_2, RESUME_3);
        assertEquals(expexted, storage.getAllSorted());
        assertEquals(expexted.size(), storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}
