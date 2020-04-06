/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int count = 0;

    void clear() {
        for (int i = 0; i < count; i++) {
            storage[i] = null;
        }
        count = 0;
    }

    void save(Resume resume) {
        if (count < storage.length - 1) {
            storage[count] = resume;
            count++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < count; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int i = 0;
        while (i < count) {
            if (storage[i].toString().equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, count - i - 1);
                count--;
                break;
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
        while (i < count) {
            resumes[i] = storage[i];
            i++;
        }
        return resumes;
    }

    int size() {
        return count;
    }
}
