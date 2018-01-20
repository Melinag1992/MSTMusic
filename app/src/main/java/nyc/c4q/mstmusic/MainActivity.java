package nyc.c4q.mstmusic;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Context context=this;
    GetArticles getArticles;

//    EditText searchET;
//    FloatingActionButton buttton_floatWeather, buttton_floatTodo;
//
//    NewsAdapter newsAdapter;
//    SoccerNewsAdapter soccerNewsAdapter;

    private static final String TAG = "JSON?";

    private static final String  url= "https://newsapi.org/v2/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiceAPI newsServiceAPI = retrofit.create(ServiceAPI.class);

        Call<GetArticles> response = newsServiceAPI.getResponseGet();

        response.enqueue(new Callback<GetArticles>() {//get model
            @Override
            public void onResponse(Call<GetArticles> call, Response<GetArticles> response) {

                getArticles = response.body();

                Log.d(TAG, "onResponse: " + getArticles.getStatus());
                Log.d(TAG, "onResponse: " + getArticles.getArticles()[0].getAuthor());

                String articleListJson = new Gson().toJson(getArticles);//Gson is a library that convert Json to string

                MainFragment mainFragment = new MainFragment();
                Bundle bundle = new Bundle();
                bundle.putString("NEWSARTICLES",articleListJson );
                mainFragment.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_framelayout, mainFragment);
                fragmentTransaction.commit();

            }

            @Override
            public void onFailure(Call<GetArticles> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch(item.getItemId()){
                case R.id.favorite_item:
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fragment_container_framelayout, new FavoriteFragment());
                    transaction.commit();
            }
            return true;
        }
}
