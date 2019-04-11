package id.ac.polinema.notesapp.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.util.List;

import id.ac.polinema.notesapp.dao.NoteDao;
import id.ac.polinema.notesapp.db.AppDatabase;
import id.ac.polinema.notesapp.models.Note;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> notes;

    public NoteRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        noteDao = db.noteDao();
        notes = noteDao.getAll();
    }

    public LiveData<List<Note>> getNotes(){
        return notes;
    }

    public static class InsertAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDao asyncTaksDao;

        InsertAsyncTask(NoteDao noteDao){
            asyncTaksDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            asyncTaksDao.insert(notes);
            return null;
        }
    }

    public static class UpdateAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDao asyncTaksDao;

        UpdateAsyncTask(NoteDao noteDao){
            asyncTaksDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            asyncTaksDao.update(notes);
            return null;
        }
    }

    public void Insert(Note note){
        new InsertAsyncTask(noteDao).execute(note);
    }

    public void Update(Note note){
        new UpdateAsyncTask(noteDao).execute(note);
    }
}
