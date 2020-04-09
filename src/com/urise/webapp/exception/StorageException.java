package com.urise.webapp.exception;

/**
 * basejava com.urise.webapp.exception.StorageException
 *
 * @author romanvohmin
 * @version 1
 * @since 09.04.2020 20:26
 */
public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
