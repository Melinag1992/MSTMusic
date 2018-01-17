package nyc.c4q.mstmusic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;


import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    GetArticles getArticles;
    Adapter adapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Bundle bundle = getArguments();
        String articleListJson = bundle.getString("NEWSARTICLES");
        getArticles = new Gson().fromJson(articleListJson,GetArticles.class);//convert String to Json
        Log.d(TAG, "onCreateView: " + getArticles.getStatus());

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new Adapter(getArticles, view.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        Log.d(TAG, "onCreateView: " + "recyclerview run");
        return view;
    }

}
