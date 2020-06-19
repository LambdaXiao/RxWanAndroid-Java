package app.android.rxwanandroidjava.repository.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import app.android.rxwanandroidjava.repository.local.database.MyDatabase;
import app.android.rxwanandroidjava.repository.local.database.Student;
import app.android.rxwanandroidjava.repository.local.database.StudentDao;

/**
 * 描述：仓库角色 本地/DB  单例
 */
public class LocalRepository implements IDatabaseRequest {

    private static LocalRepository sLocalRepository;
    private StudentDao studentDao;//表数据访问对象


    private LocalRepository(Context context) {
        MyDatabase myDatabase = MyDatabase.getDatabase(context);
        studentDao = myDatabase.getStudentDao();
    }

    public static LocalRepository getInstance(Context context) {
        if (sLocalRepository == null) {
            synchronized (LocalRepository.class) {
                if (sLocalRepository == null) {
                    sLocalRepository = new LocalRepository(context);
                }
            }
        }
        return sLocalRepository;
    }

    public LiveData<List<Student>> getStudentsLiveData() {
        return studentDao.getAllStudentLiveData();
    }

    @Override
    public void insertStudnets(Student... students) {
        MyDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.insertStudent(students);
        });
    }

    @Override
    public void updateStudnets(Student... students) {
        MyDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.updateStudent(students);
        });
    }

    @Override
    public void deleteStudents(Student... students) {
        MyDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.deleteStudent(students);
        });
    }

    @Override
    public void deleteAllStudnets() {
        MyDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.deleteAllStudent();
        });
    }
}
