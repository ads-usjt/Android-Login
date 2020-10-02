package dev.lucasliet.androidlogin.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

public class UserRepository {
    private UserDAO userDAO;
    private LiveData<User> user;
    UserRepository(Application application) {
        AlunoRoomDatabase db = AlunoRoomDatabase.getDatabase(application);
        userDAO = db.userDAO();
        user = userDAO.getUser();
    }
    LiveData<User> getUser() {
        return user;
    }
    void insert(User user) {
        AlunoRoomDatabase.databaseWriteExecutor.execute(() -> userDAO.insert(user));
    }
}
