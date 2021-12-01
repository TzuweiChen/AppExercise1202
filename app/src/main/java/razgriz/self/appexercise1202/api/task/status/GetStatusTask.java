package razgriz.self.appexercise1202.api.task.status;

import android.content.Context;

import com.google.gson.Gson;

import razgriz.self.appexercise1202.api.retrofit.StatusRtf;
import razgriz.self.appexercise1202.api.task.DataTask;
import razgriz.self.appexercise1202.bean.Status;

public class GetStatusTask extends DataTask<Status> {

    private final StatusRtf api;

    protected GetStatusTask(Context context) {
        this.api = new StatusRtf(context);
    }

    @Override
    protected String load() throws Exception {
        return this.api.getStatus();
    }

    @Override
    protected Status parseData(String s) {
        try {
            return new Gson().fromJson(s, Status.class);
        } catch (Exception e) {
            onException(e);
            return null;
        }
    }
}