package com.urise.webapp.exception;

/**
 * basejava com.urise.webapp.exception.NotExistStorageException
 *
 * @author romanvohmin
 * @version 1
 * @since 09.04.2020 20:27
 */
public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String uuid) {
        super("Resume " + uuid + " not exist", uuid);
    }

}
