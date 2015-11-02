package com.example.chrisgao.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.chrisgao.data.model.DaoMaster;
import com.example.chrisgao.data.model.DaoSession;
import com.example.chrisgao.data.model.Note;
import com.example.chrisgao.data.model.NoteDao;

import java.util.List;

/**
 * Created by ChrisGao on 2015/10/28.
 */
public class NoteDBService {

    private Context ctx;
    public NoteDBService(Context ctx){
        this.ctx = ctx;
    }

    public void Insert(Context context,Note note){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "test.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        NoteDao noteDao = daoSession.getNoteDao();
        noteDao.insert(note);
        daoSession.getDatabase().close();
        
    }
    public void Insert(Context context,List<Note> notes){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "test.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        NoteDao noteDao = daoSession.getNoteDao();
        for (Note note:notes
             ) {
            noteDao.insert(note);
        }
        daoSession.getDatabase().close();
    }
}
