package nyc.c4q.mstmusic.network;

import java.util.List;

import nyc.c4q.mstmusic.model.Strain;

/**
 * Created by c4q on 1/13/18.
 */

interface StrainBaseNetworkListener {

    void strainCallback (List<Strain> strain);
    void onNetworkError(Throwable t);

}
