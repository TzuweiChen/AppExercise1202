package razgriz.self.appexercise1202.api.retrofit;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import razgriz.self.appexercise1202.BuildConfig;
import razgriz.self.appexercise1202.api.basic.ApiException;
import razgriz.self.appexercise1202.helper.CommonHelper;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

abstract class BaseRtf<T> {
    protected T api;

    BaseRtf(Context context) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder().method(original.method(), original.body());
                    requestBuilder.addHeader("accept", "application/vnd.github.v3+json");
                    Request request = requestBuilder.build();

                    return chain.proceed(request);
                })
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonHelper.isConnectWithWIFI(context) ? BuildConfig.PRIVATE_API_URL : BuildConfig.PUBLIC_API_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client)
                .build();

        this.api = retrofit.create(this.getType());
    }

    String execute(Call<String> call) throws Exception {
        Response<String> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new ApiException(response.code(), response.errorBody() == null ? "" : response.errorBody().string());
        }
    }

    protected abstract Class<T> getType();
}
