package cn.org.cfpamf.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import cn.org.cfpamf.data.sql.dao.DaoMaster;
import cn.org.cfpamf.data.sql.dao.DaoSession;
import de.greenrobot.dao.async.AsyncOperation;
import de.greenrobot.dao.async.AsyncOperationListener;
import de.greenrobot.dao.async.AsyncSession;


/**
 * @author Octa
 */
public class DatabaseManager<M> implements AsyncOperationListener, IDatabase<M> {

    private static final String DATABASE_NAME = "name.db";

    /**
     * The Android Activity reference for access to DatabaseManager.
     */
    protected static DaoMaster.DevOpenHelper mHelper;
    protected static SQLiteDatabase database;
    protected static DaoMaster daoMaster;
    protected static DaoSession daoSession;
    protected static AsyncSession asyncSession;
    protected static List<AsyncOperation> completedOperations;

    /**
     * Constructs a new DatabaseManager with the specified arguments.
     */
    public DatabaseManager(@NonNull Context context) {
        getOpenHelper(context, "");
    }

    /**
     * create new DataBase
     */
    public DatabaseManager(@NonNull Context context, @Nullable String db_name) {
        getOpenHelper(context, db_name);
    }

    @Override
    public void onAsyncOperationCompleted(AsyncOperation operation) {
        completedOperations.add(operation);
    }

    protected void assertWaitForCompletion1Sec() {
        asyncSession.waitForCompletion(1000);
        asyncSession.isCompleted();
    }

    /**
     * Query for readable DB
     */
    protected void openReadableDb() throws SQLiteException {
        database = mHelper.getReadableDatabase();
        getDaoMaster();
        getDaoSession();
    }

    /**
     * Query for writable DB
     */
    protected void openWritableDb() throws SQLiteException {
        database = mHelper.getWritableDatabase();
        getDaoMaster();
        getDaoSession();
    }

    /**
     * Query for readable DB
     */
    protected void openReadableDbAsync() throws SQLiteException {
        database = mHelper.getReadableDatabase();
        getDaoMaster();
        getDaoAsyncSession();
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
    }

    /**
     * Query for writable DB
     */
    protected void openWritableDbAsync() throws SQLiteException {
        database = mHelper.getWritableDatabase();
        getDaoMaster();
        getDaoAsyncSession();
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
    }

    /**
     * 初始化DatabaseHelper
     */
    protected void getOpenHelper(@NonNull Context context, @Nullable String dataBaseName) {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(context, dataBaseName + DATABASE_NAME, null);
        }
    }

    /**
     * 初始化DaoMaster
     */
    private void getDaoMaster() {
        if (daoMaster == null) {
            daoMaster = new DaoMaster(database);
        }
    }

    /**
     * 初始化DaoSession
     */
    private void getDaoSession() {
        if (daoSession == null) {
            daoSession = daoMaster.newSession();
        }
    }

    /**
     * 初始化AsyncDaoSession
     */
    private void getDaoAsyncSession() {
        if (daoSession == null) {
            daoSession = daoMaster.newSession();
            asyncSession = daoSession.startAsyncSession();
            asyncSession.setListener(this);
        }
    }

    @Override
    public void closeDbConnections() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
        if (database != null && database.isOpen()) {
            database.close();
        }
        if (daoMaster != null) {
            daoMaster = null;
        }
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }

    @Override
    public synchronized void dropDatabase() {
        try {
            openWritableDb();
//            daoSession.deleteAll(AdminDivisionInfo.class);    // clear all elements from a table
        } catch (Exception e) {
            Logger.e(e.toString());
        }
    }

    @Override
    public void insert(@NonNull M m) {
        try {
            openWritableDb();
            daoSession.insert(m);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(@NonNull M m) {
        try {
            openWritableDb();
            daoSession.delete(m);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOrReplace(@NonNull M m) {
        try {
            openWritableDb();
            daoSession.insertOrReplace(m);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(@NonNull M m) {
        try {
            openWritableDb();
            daoSession.update(m);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public M selectByPrimaryKey(@NonNull Class<M> entityClass, @NonNull String Id) {
        M m = null;
        try {
            openReadableDb();
            m = daoSession.load(entityClass, Id);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public List<M> loadAll(@NonNull Class<M> entityClass) {
        List<M> mList = null;
        try {
            openReadableDb();
            mList = daoSession.loadAll(entityClass);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return mList;
    }

    @Override
    public void refresh(@NonNull M m) {
        try {
            openWritableDb();
            daoSession.refresh(m);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void runInTx(Runnable runnable) {
        openReadableDb();
        daoSession.runInTx(runnable);
    }
//    @Override
//    public synchronized void bulkInsertPhoneNumbers(Set<DBPhoneNumber> phoneNumbers) {
//        try {
//            if (phoneNumbers != null && phoneNumbers.size() > 0) {
//                openWritableDb();
//                asyncSession.insertOrReplaceInTx(DBPhoneNumber.class, phoneNumbers);
//                assertWaitForCompletion1Sec();
//                daoSession.clear();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
