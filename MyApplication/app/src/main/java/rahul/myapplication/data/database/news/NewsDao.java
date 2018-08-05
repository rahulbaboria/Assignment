package rahul.myapplication.data.database.news;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import rahul.myapplication.data.model.News;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface NewsDao {

    @Query("Select * from News")
    List<News> getAllItems();

    @Insert(onConflict = REPLACE)
    void insertAll(List<News> news);

    @Delete
    void delete(News item);

}
