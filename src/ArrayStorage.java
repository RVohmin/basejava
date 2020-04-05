import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int count = 0;

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            }
            storage[i] = null;
        }
        count = 0;
    }

    void save(Resume resume) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = resume;
                count++;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume item : storage) {
            if (item == null) {
                break;
            }
            if (item.uuid.equals(uuid)) {
                return item;
            }
        }
        return new Resume();
    }

    void delete(String uuid) {
        int i = 0;
        while (storage[i] != null) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = null;
                System.arraycopy(storage, i + 1, storage, i, count);
                count--;
            }
            i++;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[count];
        int i = 0;
        while (storage[i] != null) {
            resumes[i] = storage[i];
            i++;
        }
        return resumes;
    }

    int size() {
        return getAll().length;
    }
}
