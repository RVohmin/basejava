import java.util.Arrays;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    /**
     * Method check for resume present in storage.
     * @param uuid - String uuid.
     * @return boolean true if present.
     */
    private boolean checkPresent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * clearing storage of resume.
     */
    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    /**
     * Method replaced resume
     * @param resume - new version resume.
     */
    public void update(Resume resume) {
        if (checkPresent(resume.toString())) {
            for (int i = 0; i < size; i++) {
                if (storage[i].equals(resume)) {
                    storage[i] = resume;
                }
            }
        } else {
            System.out.println("Error: resume with such uuid absent");
        }
    }

    /**
     * Save new resume to storage.
     * @param resume - new resume.
     */
    void save(Resume resume) {
        if (!checkPresent(resume.toString())) {
            if (size < storage.length) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("Error: Resume cannot be saved - storage is full");
            }
        } else {
            System.out.println("Error: Such resume is exist, use update() method");
        }
    }

    /**
     * get resume from storage.
     * @param uuid - uuid resume.
     * @return - Resume if it exist in storage.
     */
    Resume get(String uuid) {
        if (checkPresent(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    return storage[i];
                }
            }
        } else {
            System.out.printf("Error: resume with uuid \"%s\" is absent in storage \n", uuid);
        }
        return null;
    }

    /**
     * delete resume from storage if it exist in storage.
     * @param uuid - uuid resume.
     */
    void delete(String uuid) {
        if (checkPresent(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].toString().equals(uuid)) {
                    System.arraycopy(storage, i + 1, storage, i, size - i - 1);
                    size--;
                    break;
                }
            }
        } else {
            System.out.println("Error: there isn't resume with such uuid");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    /**
     * @return size of storage (quantity of resumes)
     */
    int size() {
        return size;
    }
}
