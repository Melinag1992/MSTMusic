package nyc.c4q.mstmusic.network;

import android.util.Log;

import java.util.List;

import nyc.c4q.mstmusic.apiget.StrainAPI;
import nyc.c4q.mstmusic.model.Strain;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by c4q on 1/13/18.
 */

public class RetrofitFactory {

    private static RetrofitFactory retrofitFactory;

    private Retrofit retrofit;
    private StrainBaseNetworkListener strainBaseNetworkListener = null;

    public RetrofitFactory getInstance() {
        if (retrofitFactory == null) {
            retrofitFactory = new RetrofitFactory();
        }
        return retrofitFactory;

    }

    public void setStrainBaseNetworkListener(StrainBaseNetworkListener strainBaseNetworkListener) {
        this.strainBaseNetworkListener = strainBaseNetworkListener;

    }

    public Retrofit buildRetrofit() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Host.StrainBaseAPI.getUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;

    }
    public void getStrains(String name){
        StrainAPI strainBaseService = buildRetrofit().create(StrainAPI.class);
        Call<List<Strain>> getServiceResponse = strainBaseService.getstrains(name);
        getServiceResponse.enqueue(new Callback<List<Strain>>() {
            @Override
            public void onResponse(Call<List<Strain>> call, Response<List<Strain>> response) {

                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: " + "successful");
                    if(strainBaseNetworkListener != null){
                        strainBaseNetworkListener.strainCallback(response.body());


                    }
                }

            }

            @Override
            public void onFailure(Call<List<Strain>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+ t);

            }
        });

    }


}
