package razgriz.self.appexercise1202.api.retrofit;

import android.content.Context;

import retrofit2.Call;
import retrofit2.http.GET;

public class StatusRtf extends BaseRtf<StatusRtf.Service> {

    public StatusRtf(Context context) {
        super(context);
    }

    @Override
    protected Class<Service> getType() {
        return Service.class;
    }

    public String getStatus() throws Exception {
        return this.execute(this.api.getStatus());
    }


    public interface Service {
        @GET("status")
        Call<String> getStatus();
    }
}