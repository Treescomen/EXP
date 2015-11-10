package cn.org.cfpamf.data.sql.dbdeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import cn.org.cfpamf.data.sql.db.Baidu;

/**
 * 项目名称：groupBackstage
 * 类描述： 匹配服务器返回对象
 * 创建人：zzy
 * 创建时间：2015/8/22 13:59
 * 修改人：Administrator
 * 修改时间：2015/8/22 13:59
 * 修改备注：
 */
public class BaiduDeserializer implements JsonDeserializer<Baidu> {

    @Override
    public Baidu deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Baidu photoInfo = new Baidu();
        JsonObject jsonObject = json.getAsJsonObject();
        photoInfo.setId(jsonObject.get("id").getAsString());
        photoInfo.setResponse(jsonObject.get("reponse").getAsString());
        return photoInfo;
    }
}
