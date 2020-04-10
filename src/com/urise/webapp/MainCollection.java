package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * basejava com.urise.webapp.MainCollection
 *
 * @author romanvohmin
 * @version 1
 * @since 10.04.2020 18:55
 */
public class MainCollection {
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);
        collection.add(RESUME_4);

        collection.removeIf(resume -> resume.getUuid().equals(UUID_1));
        collection.forEach(System.out::println);
    }

}
