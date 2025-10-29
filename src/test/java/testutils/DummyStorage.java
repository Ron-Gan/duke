package testutils;

import storage.Storage;
import task.TaskList;

public class DummyStorage extends Storage {

    public DummyStorage() {
        super(""); // no actual storage path needed
    }

    @Override
    public void save(TaskList tasks) {
    }
}
