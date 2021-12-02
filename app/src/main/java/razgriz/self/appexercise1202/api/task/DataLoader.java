package razgriz.self.appexercise1202.api.task;

public class DataLoader {
    public static void run(@SuppressWarnings("rawtypes") final DataTask task) {
        new Thread(task::action).start();
    }
}
