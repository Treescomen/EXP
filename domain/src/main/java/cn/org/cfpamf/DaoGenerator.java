package cn.org.cfpamf;

import java.io.File;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "cn.org.cfpamf.data.sql.db");
        schema.setDefaultJavaPackageDao("cn.org.cfpamf.data.sql.dao");
        createTableBaidu(schema);
        new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema, getPath() + "\\data\\src\\main\\java");
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

        File f = new File(new DaoGenerator().getClass().getResource("/").getPath());

        String path = f.getAbsolutePath();
        System.out.println(path);
        //程序包名
        final String str = "Zhnx";

        int i = path.indexOf(str);

        return path.substring(0, i + str.length());
    }

}
