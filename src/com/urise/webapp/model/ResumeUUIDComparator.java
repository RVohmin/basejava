package com.urise.webapp.model;

import java.util.Comparator;

/**
 * basejava com.urise.webapp.model.ResumeComparator
 *
 * @author romanvohmin
 * @since 13.04.2020 12:32
 */
public class ResumeUUIDComparator implements Comparator<Resume> {

    @Override
    public int compare(Resume o1, Resume o2) {
        return o1.getUuid().compareTo(o2.getUuid());
    }
}
