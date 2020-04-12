package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Test
    public void getAll() {
        Resume[] expexted = new Resume[] {new Resume("uuid1"), new Resume("uuid2"), new Resume("uuid3")};
        Resume[] result = storage.getAll();
        Arrays.sort(result);
        assertArrayEquals(expexted, result);
        assertEquals(expexted.length, storage.getAll().length);
    }
}
