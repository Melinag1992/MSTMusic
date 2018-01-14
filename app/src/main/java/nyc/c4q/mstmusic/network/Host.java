package nyc.c4q.mstmusic.network;

/**
 * Created by c4q on 1/13/18.
 */

public enum Host {
    StrainBaseAPI("strainapi.evanbusse.com/API_KEY");

    private final String url ;

    private Host(final String url) {
        this.url = url;

    }
    public String getUrl(){
        return this.url;
    }
}
