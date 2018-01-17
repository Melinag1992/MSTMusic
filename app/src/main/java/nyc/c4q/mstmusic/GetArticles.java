package nyc.c4q.mstmusic;

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

    public void setArticles (Articles[] articles)
    {
        this.articles = articles;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [articles = "+articles+", status = "+status+"]";
    }

}
