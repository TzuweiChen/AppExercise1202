package razgriz.self.appexercise1202.module.home.model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import razgriz.self.appexercise1202.R;
import razgriz.self.appexercise1202.bean.Pass;
import razgriz.self.appexercise1202.helper.CommonHelper;

public class GetPassModel extends AndroidViewModel {

    private final MutableLiveData<List<PassSectionAndTypeModel>> sections;
    private final MutableLiveData<String> error;
    private final MutableLiveData<Boolean> progress;

    public GetPassModel(@NonNull Application application) {
        super(application);

        this.sections = new MutableLiveData<>();
        this.error = new MutableLiveData<>();
        this.progress = new MutableLiveData<>();
    }

    public MutableLiveData<List<PassSectionAndTypeModel>> getSections() {
        return sections;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public MutableLiveData<Boolean> getProgress() {
        return progress;
    }

    public void doGetPass(Context context, String passType) {
        boolean isDayPass = CommonHelper.isPassTypeDayPass(passType);
        // TODO: 2021/12/2 show call data from API

        List<Pass> passes = new ArrayList<>();
        if (isDayPass) {
            passes.add(new Pass(passType, 1, 2000, "Rp"));
            passes.add(new Pass(passType, 3, 5000, "Rp"));
            passes.add(new Pass(passType, 7, 10000, "Rp"));
        } else {
            passes.add(new Pass(passType, 1, 500, "Rp"));
            passes.add(new Pass(passType, 8, 1000, "Rp"));
        }

        // After API response success
        List<PassSectionAndTypeModel> sectionAndTypeModels = new ArrayList<>();
        sectionAndTypeModels.add(new PassSectionAndTypeModel(PassSectionAndTypeModel.IPassAdapterType.PASS_TYPE_TITLE, context.getString(isDayPass ? R.string.txt_day_pass : R.string.txt_hour_pass)));
        for (Pass pass : passes) {
            sectionAndTypeModels.add(new PassSectionAndTypeModel(PassSectionAndTypeModel.IPassAdapterType.PASS_CONTENT, pass));
        }

        sections.postValue(sectionAndTypeModels);
    }
}
