package rahul.myapplication.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import rahul.myapplication.data.database.news.NewsDao;
import rahul.myapplication.data.model.News;

/**
 * Created by rahul on 4/8/18.
 */

@Database(entities = {News.class},version=1)

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    public abstract NewsDao newsDao();

    public static AppDatabase getInstance(Context context){
        if(INSTANCE==null){
            synchronized (AppDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context,AppDatabase.class,"Rahul db").build();
                }
            }
        }
        return INSTANCE;
    }


}
