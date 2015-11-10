package cn.org.cfpamf;

import java.io.File;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoGenerator {

    public static final String SQL_DB="cn.org.cfpamf.data.sql.db";
    public static final String SQL_DAO="cn.org.cfpamf.data.sql.dao";

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, SQL_DB);
        schema.setDefaultJavaPackageDao(SQL_DAO);
        createTableBaidu(schema);
        new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema, getPath());
    }

    private static void createTableBaidu(Schema schema) {
        Entity baidu = schema.addEntity("Baidu");
        baidu.addStringProperty("id").primaryKey();
        baidu.addStringProperty("response").notNull();
        baidu.implementsInterface("Parcelable");
        baidu.setHasKeepSections(true);
    }

    /**
     * 获取程序的根目录
     *
     * @return
     */
    private static String getPath() {

        String path=new StringBuilder()
                .append("data")
                .append(File.separator)
                .append("src")
                .append(File.separator)
                .append("main")
                .append(File.separator)
                .append("java")
                .append(File.separator).toString();
        return new File(path).getAbsolutePath();
    }

}
