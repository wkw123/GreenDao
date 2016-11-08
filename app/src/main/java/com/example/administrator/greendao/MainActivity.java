package com.example.administrator.greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.greendao.damanager.CommonUtils;
import com.student.entity.Student;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button addButton;
    private CommonUtils commonUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = (Button) findViewById(R.id.button);
        commonUtils = new CommonUtils(this);
        Student student = new Student();
        student.setAddress("厦门");
        student.setName("张三");
        student.setAge(22);
        student.setId(1001l);
        commonUtils.insertStudent(student);
    }

    public void insertData(View view){
        Log.i(TAG, "insert Data");

    }
}
