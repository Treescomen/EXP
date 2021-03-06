package cn.org.cfpamf.data.sql.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import cn.org.cfpamf.data.sql.db.Baidu;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BAIDU".
*/
public class BaiduDao extends AbstractDao<Baidu, String> {

    public static final String TABLENAME = "BAIDU";

    /**
     * Properties of entity Baidu.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property Response = new Property(1, String.class, "response", false, "RESPONSE");
    };


    public BaiduDao(DaoConfig config) {
        super(config);
    }
    
    public BaiduDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BAIDU\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"RESPONSE\" TEXT NOT NULL );"); // 1: response
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BAIDU\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Baidu entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
        stmt.bindString(2, entity.getResponse());
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Baidu readEntity(Cursor cursor, int offset) {
        Baidu entity = new Baidu( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.getString(offset + 1) // response
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Baidu entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setResponse(cursor.getString(offset + 1));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(Baidu entity, long rowId) {
        return entity.getId();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(Baidu entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
