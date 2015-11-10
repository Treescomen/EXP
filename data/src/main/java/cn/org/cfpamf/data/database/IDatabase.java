package cn.org.cfpamf.data.database;

import java.util.List;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/8/28 17:05
 * 修改人：Administrator
 * 修改时间：2015/8/28 17:05
 * 修改备注：
 */
public interface IDatabase<M> {

    void insert(M m);

    void delete(M m);

    void insertOrReplace(M m);

    void update(M m);

    M selectByPrimaryKey(Class<M> entityClass, String Id);

    List<M> loadAll(Class<M> entityClass);

    void refresh(M m);

    /**
     * Closing available connections
     */
    void closeDbConnections();

    /**
     * Delete all tables and content from our database
     */
    void dropDatabase();

    /**
     * 事务
     */
    void runInTx(Runnable runnable);


}
