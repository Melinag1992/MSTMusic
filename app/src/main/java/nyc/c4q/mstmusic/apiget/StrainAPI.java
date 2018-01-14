package nyc.c4q.mstmusic.apiget;

import java.util.List;

import nyc.c4q.mstmusic.model.Strain;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by c4q on 1/13/18.
 */

public interface StrainAPI {

    @GET("6oJPfWj/strains/search/name/{NAME}")
    Call<List<Strain>> getstrains(@Path("NAME")String strainName);
}
