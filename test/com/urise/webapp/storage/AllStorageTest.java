package com.urise.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * basejava com.urise.webapp.storage.AllStorageTest
 *
 * @author romanvohmin
 * @since 13.04.2020 12:19
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapResumeStorageTest.class})
public class AllStorageTest {
}
