package com.urise.webapp.exception;

/**
 * basejava com.urise.webapp.exception.ExistStorageException
 *
 * @author romanvohmin
 * @version 1
 * @since 09.04.2020 20:27
 */
public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " is already exist", uuid);
    }
}
