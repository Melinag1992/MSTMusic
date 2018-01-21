package nyc.c4q.mstmusic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static nyc.c4q.mstmusic.MainActivity.favorites;


/**
 * A simple {@link Fragment} subclass.
 */
public class  SecondFragment extends Fragment {

    GetArticles getArticles;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        getArticles = new GetArticles();
        getArticles.setArticles(favorites);
//        Bundle bundle = getArguments();
//        String articleListJson = bundle.getString("NEWSARTICLES");
        //getArticles = new Gson().fromJson(articleListJson,GetArticles.class);//convert String to Json
       // Log.d(TAG, "onCreateView: " + getArticles.getStatus());

        RecyclerView recyclerView = view.findViewById(R.id.favorites_recycler);
        Adapter adapter = new Adapter(getArticles, view.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);


        return view;
    }

}
