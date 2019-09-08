package video.cn.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author husy
 * @date 2019/9/8
 */
public class SpUtil {
    /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "share_date";
    private static final String STRING = "String";
    private static final String INTEGER = "Integer";
    private static final String BOOLEAN = "Boolean";
    private static final String FLOAT = "Float";
    private static final String LONG = "Long";

    private static final String LAUNCH_PIC = "launch_pic";

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     */
    private static void setParam(Context context, String key, Object object) {

        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if(STRING.equals(type)){
            editor.putString(key, (String)object);
        }
        else if(INTEGER.equals(type)){
            editor.putInt(key, (Integer)object);
        }
        else if(BOOLEAN.equals(type)){
            editor.putBoolean(key, (Boolean)object);
        }
        else if(FLOAT.equals(type)){
            editor.putFloat(key, (Float)object);
        }
        else if(LONG.equals(type)){
            editor.putLong(key, (Long)object);
        }
        editor.apply();
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     */
    private static Object getParam(Context context , String key, Object defaultObject) {
        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if(STRING.equals(type)) {
            return sp.getString(key, (String)defaultObject);
        }

        else if(INTEGER.equals(type)) {
            return sp.getInt(key, (Integer)defaultObject);
        }

        else if(BOOLEAN.equals(type)) {
            return sp.getBoolean(key, (Boolean)defaultObject);
        }

        else if(FLOAT.equals(type)) {
            return sp.getFloat(key, (Float)defaultObject);
        }

        else if(LONG.equals(type)) {
            return sp.getLong(key, (Long)defaultObject);
        }

        return null;
    }

    /**
     * 清除所有数据
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().apply();
    }

    /**
     * 清除指定数据
     */
    public static void clearAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("定义的键名");
        editor.apply();
    }

    // --------------------------welcome pic----------------------------------

    public static void saveLaunchPic(Context context, String pic) {
        setParam(context, LAUNCH_PIC, pic);
    }

    public static String getLaunchPic(Context context) {
        return (String) getParam(context, LAUNCH_PIC, "");
    }
}
