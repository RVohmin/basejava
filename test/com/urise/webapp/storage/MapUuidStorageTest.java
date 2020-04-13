package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MapUuidStorageTest extends AbstractStorageTest {

    public MapUuidStorageTest() {
        super(new MapUuidStorage());
    }

    @Test
    public void getAll() {
        List<Resume> expexted = List.of(new Resume("uuid1", "Ivan Petrov"), new Resume("uuid2", "Alex Ivanov"), new Resume("uuid3", "Bob Marley"));
        List<Resume> result = storage.getAllSorted();
//        result.sort(result, new ResumeUUIDComparator());
        assertEquals(expexted, result);
        assertEquals(expexted.size(), storage.size());
    }
}
