package razgriz.self.appexercise1202.module.home.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.Locale;

import razgriz.self.appexercise1202.api.basic.ApiException;
import razgriz.self.appexercise1202.api.task.DataLoader;
import razgriz.self.appexercise1202.api.task.status.GetStatusTask;
import razgriz.self.appexercise1202.bean.Status;

public class GetStatusModel extends AndroidViewModel {

    private final MutableLiveData<Status> status;
    private final MutableLiveData<String> error;
    private final MutableLiveData<Boolean> progress;

    public GetStatusModel(@NonNull Application application) {
        super(application);

        this.status = new MutableLiveData<>();
        this.error = new MutableLiveData<>();
        this.progress = new MutableLiveData<>();
    }

    public MutableLiveData<Status> getStatus() {
        return status;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public MutableLiveData<Boolean> getProgress() {
        return progress;
    }

    public void doGetStatus() {
        DataLoader.run(
                new GetStatusTask(getApplication().getApplicationContext()) {

                    @Override
                    protected void onResult(Status val) {
                        status.postValue(val);
                    }

                    @Override
                    protected void onStart() {
                        progress.postValue(true);
                    }

                    @Override
                    protected void onFinish() {
                        progress.postValue(false);
                    }

                    @Override
                    protected void onApiException(ApiException e) {
                        super.onApiException(e);
                        error.postValue(String.format(Locale.getDefault(), "%s\n(%d)", e.getBody(), e.getCode()));
                    }

                    @Override
                    protected void onException(Exception e) {
                        error.postValue(e.getMessage());
                    }
                });
    }
}
