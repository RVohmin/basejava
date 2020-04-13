package com.urise.webapp.model;

import java.util.Comparator;

/**
 * basejava com.urise.webapp.model.ResumeFullNameComparator
 *
 * @author romanvohmin
 * @since 13.04.2020 12:38
 */
public class ResumeFullNameComparator implements Comparator<Resume> {

    @Override
    public int compare(Resume o1, Resume o2) {
        return o1.getFullName().compareTo(o2.getFullName());
    }
}
