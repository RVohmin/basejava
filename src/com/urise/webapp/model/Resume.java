package com.urise.webapp.model;

import org.jetbrains.annotations.NotNull;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    /**
     * Unique identifier
     */
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;

        Resume resume = (Resume) o;

        return getUuid().equals(resume.getUuid());
    }

    @Override
    public int hashCode() {
        return getUuid().hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(@NotNull Resume o) {
        return uuid.compareTo(o.uuid);
    }
}
