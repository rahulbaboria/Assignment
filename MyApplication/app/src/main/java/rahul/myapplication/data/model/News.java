package rahul.myapplication.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by rahul on 4/8/18.
 */
@Entity(tableName = "News")
public class News {


        public News(@NonNull String title, String description, String author, String urlToImage, String publishedAt) {
                this.title = title;
                this.description = description;
                this.author = author;
                this.urlToImage = urlToImage;
                this.publishedAt = publishedAt;
        }

        private String source;

        public String getSource() { return this.source; }

        public void setSource(String source) { this.source = source; }

        private String author;

        public String getAuthor() { return this.author; }

        public void setAuthor(String author) { this.author = author; }

        @PrimaryKey()
        @NonNull
        private String title;

        @NonNull
        public String getTitle() { return this.title; }

        public void setTitle(@NonNull String title) { this.title = title; }

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
