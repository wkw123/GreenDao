package com.example.administrator.greendao.damanager;

import android.content.Context;

import com.student.dao.DaoMaster;
import com.student.dao.DaoSession;

import de.greenrobot.dao.query.QueryBuilder;

/**创建数据库
 * 创建数据库的表
 * 包含对数据库的CRUD
 * 对数据库的升级
 * Created by Administrator on 2016/11/8.
 */

public class DaoManager {

    private static final String TAG = DaoManager.class.getSimpleName();
    private static final String DB_NAME = "mydb.sqlite";
    private volatile static DaoManager daoManager;
    private static DaoMaster.DevOpenHelper helper;
    private static  DaoMaster daoMaster;
    private static DaoSession daoSession;
    private Context context;
    public static DaoManager getInstance(){
        DaoManager instance = null;
        if(daoManager == null){
            synchronized (DaoManager.class){
                if(instance == null){
                    instance = new DaoManager();
                    daoManager = instance;
                }
            }
        }
        return instance;
    }

    public void init(Context context){
        this.context = context;
    }

    /**
     * 判断是否存在数据库，没有的话就创建
     * @return
     */

    public DaoMaster getDaoMaster(){
        if(daoMaster == null){
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,DB_NAME);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 完成对数据库的增删改查操作，仅仅是一个接口
     * @return
     */

    public DaoSession getDaoSession(){
        if (daoSession == null){
            if(daoMaster == null){
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    /**
     * 打开输出日志的操作，默认是关闭的
     */

    public void setDebug(){
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    public void closeConnection(){
        closeHelper();
        closeDaoSession();
    }

    /**
     * 关闭所有的操作，数据库开启的时候，使用完毕要关闭
     */

    public void closeHelper(){
        if (helper != null){
            helper.close();
            helper = null;
        }
    }

    public void closeDaoSession(){
        if (daoSession != null){
            daoSession.clear();
            daoSession = null;
        }
    }

}
