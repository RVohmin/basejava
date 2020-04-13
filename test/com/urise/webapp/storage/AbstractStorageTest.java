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
    private static final String UUID_1 = "uuid1";
    private static final String FULLNAME_1 = "Ivan Petrov";
    private static final Resume RESUME_1 = new Resume(UUID_1, FULLNAME_1);
    private static final String UUID_2 = "uuid2";
    private static final String FULLNAME_2 = "Alex Ivanov";
    private static final Resume RESUME_2 = new Resume(UUID_2, FULLNAME_2);
    private static final String UUID_3 = "uuid3";
    private static final String FULLNAME_3 = "Bob Marley";
    private static final Resume RESUME_3 = new Resume(UUID_3, FULLNAME_3);

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
        Resume resume = new Resume(UUID_2);
        assertNotSame(resume, storage.get(UUID_2));
        storage.update(resume);
        assertEquals(resume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistingThenException() {
        storage.update(new Resume());
    }

    @Test
    public void save() {
        storage.save(new Resume("UUID_4"));
        assertEquals(4, storage.size());
        assertEquals("UUID_4", storage.get("UUID_4").getUuid());
    }

    @Test(expected = ExistStorageException.class)
    public void whenSaveExistResumeThenExistStorageException() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        assertEquals(2, storage.size());
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void get() {
        String UUID_4 = "uuid4";
        Resume RESUME_4 = new Resume(UUID_4, "Ivan Petrov");
        storage.save(RESUME_4);
        assertEquals(UUID_4, storage.get(UUID_4).getUuid());
    }

    @Test
    public void getAll() {
        List<Resume> expexted = List.of(new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3));
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
