package nyc.c4q.mstmusic;

import java.util.ArrayList;

/**
 * Created by C4Q on 1/15/18.
 */

class GetArticles {

    private Articles[] articles;

    private String status;

    public Articles[] getArticles ()
    {
        return articles;
    }

    public void setArticles (ArrayList<Articles> articles1) {
        articles = (Articles[]) articles1.toArray();
    }


    public String getStatus ()
    {
        return status;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [articles = "+articles+", status = "+status+"]";
    }

}
