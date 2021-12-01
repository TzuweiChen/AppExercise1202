package razgriz.self.appexercise1202.api.task;

public class DataLoader {
    public static void run(final DataTask task) {
        new Thread(task::action).start();
    }
}
