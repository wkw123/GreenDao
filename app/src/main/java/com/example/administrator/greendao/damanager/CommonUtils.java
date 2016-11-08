package com.example.administrator.greendao.damanager;

import android.content.Context;

import com.student.entity.Student;

import java.util.List;

/**完成对某一张表的具体操作，
 * Created by Administrator on 2016/11/8.
 */

public class CommonUtils {
    private DaoManager daoManager;


    public CommonUtils(Context context){
        daoManager = DaoManager.getInstance();
        daoManager.init(context);

    }

    /**
     * 完成对数据库中的Student表的插入操作
     * @param student
     * @return
     */

    public boolean insertStudent(Student student){
        boolean flag = false;
        flag = daoManager.getDaoSession()
                .insert(student) != -1 ? true : false;
        return  flag;
    }

    /**
     * 插入多条记录需要开辟新的线程
     * @param students
     * @return
     */

    public boolean insertMultStudent(final List<Student> students){
        boolean flag = false;
        try {
            daoManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for(Student s : students){
                        daoManager.getDaoSession().insertOrReplace(s);
                    }
                }
            });
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return  flag;
    }

    public boolean updateStudent(Student student){
        boolean flag = false;
        try{
            daoManager.getDaoSession().update(student);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

}
