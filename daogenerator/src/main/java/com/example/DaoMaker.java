package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoMaker {

    public static void main(String[] args){
        Schema schema = new Schema(1, "com.student.entity");
        addStudent(schema);
        schema.setDefaultJavaPackageDao("com.student.dao");
        try {
            new DaoGenerator().generateAll(schema, "C:\\Users\\Administrator.XJB-01608311419\\Desktop\\GreenDao\\app\\src\\main\\java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //create database
    private static void addStudent(Schema schema){
        Entity entity = schema.addEntity("Student");
        entity.addIdProperty(); //primary key
        entity.addStringProperty("name");
        entity.addStringProperty("address");
        entity.addIntProperty("age");
    }
}
