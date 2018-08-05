package rahul.myapplication.data.model;


import java.util.ArrayList;

/**
 * Created by ajit on 4/8/18.
 */
public class NewsResponse {
    private String status;

    public String getStatus() { return this.status; }

    public void setStatus(String status) { this.status = status; }

    private int totalResults;

    public int getTotalResults() { return this.totalResults; }

    public void setTotalResults(int totalResults) { this.totalResults = totalResults; }

    private ArrayList<Article> articles;

    public ArrayList<Article> getArticles() { return this.articles; }

    public void setArticles(ArrayList<Article> articles) { this.articles = articles; }

    @Override
    public String toString() {
        return "News{" +
                "status='" + status + '\'' +
                ", totalResults=" + totalResults +
                ", articles=" + articles +
                '}';
    }

    public class Source
    {
        private String id;

        public String getId() { return this.id; }

        public void setId(String id) { this.id = id; }

        private String name;

        public String getName() { return this.name; }

        public void setName(String name) { this.name = name; }

        @Override
        public String toString() {
            return name;
        }
    }


    public class Article
    {

        public int getSno() {
            return sno;
        }

        public void setSno(int sno) {
            this.sno = sno;
        }

        private int sno;

        private Source source;

        public Source getSource() { return this.source; }

        public void setSource(Source source) { this.source = source; }

        private String author;

        public String getAuthor() { return this.author; }

        public void setAuthor(String author) { this.author = author; }

        private String title;

        public String getTitle() { return this.title; }

        public void setTitle(String title) { this.title = title; }

        private String description;

        public String getDescription() { return this.description; }

        public void setDescription(String description) { this.description = description; }

        private String url;

        public String getUrl() { return this.url; }

        public void setUrl(String url) { this.url = url; }

        private String urlToImage;

        public String getUrlToImage() { return this.urlToImage; }

        public void setUrlToImage(String urlToImage) { this.urlToImage = urlToImage; }

        private String publishedAt;

        public String getPublishedAt() { return this.publishedAt; }

        public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt; }

        @Override
        public String toString() {
            return "Article{" +
                    "source=" + source +
                    ", author='" + author + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", url='" + url + '\'' +
                    ", urlToImage='" + urlToImage + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    '}';
        }
    }
}
