package nyc.c4q.mstmusic;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.mstmusic.apiget.StrainAPI;
import nyc.c4q.mstmusic.model.Strain;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = " content is";
    private String race;
    private String description;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MainFragment mainFragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_framelayout, mainFragment);
        fragmentTransaction.commit();
       getAllStrains();
    }


    public void getAllStrains() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://strainapi.evanbusse.com/6oJPfWj/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StrainAPI strainBaseService = retrofit.create(StrainAPI.class);
        Call<List<Strain>> getStrainList = strainBaseService.getAllStrain();
        getStrainList.enqueue(new Callback<List<Strain>>() {
          @Override
          public void onResponse(Call<List<Strain>> call, Response<List<Strain>> response) {

            List strainList = new ArrayList<>();

              race = response.body().get(0).getRace();
              name = response.body().get(0).getName();
              description = response.body().get(0).getDesc();
              strainList.add(race);
              strainList.add(name);
              strainList.add(description);

              for (int i = 0; i <strainList.size() ; i++) {
                  Log.d(TAG, "onResponse: " + strainList.get(i));
              }

          }

          @Override
          public void onFailure(Call<List<Strain>> call, Throwable t) {
              Log.d(TAG, "onFailure: " + t);

          }
      });

    }

}