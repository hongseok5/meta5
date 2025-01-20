package com.example.meta5.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MetaObject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int objectId;
  private int classId;
  private String objRmk1;
  private String objRmk2;
  private String objRmk3;
  private String objRmk4;
  private String objRmk5;
  private String objRmk6;
  private String objRmk7;

  private MetaObject(Builder builder){
    this.objectId = builder.objectId;
    this.classId = builder.classId;
    this.objRmk1 = builder.objRmk1;
    this.objRmk2 = builder.objRmk2;
    this.objRmk3 = builder.objRmk3;
    this.objRmk4 = builder.objRmk4;
    this.objRmk5 = builder.objRmk5;
    this.objRmk6 = builder.objRmk6;
    this.objRmk7 = builder.objRmk7;
  }

    protected MetaObject() {}

    // Getter 메서드
    public int getObjectId() {
        return objectId;
    }

    public String getObjRmk1() {
        return objRmk1;
    }

    public int getClassId() {
        return classId;
    }

    public String getObjRmk2() {
        return objRmk2;
    }

    public String getObjRmk3() {
        return objRmk3;
    }

  public static class Builder {
      private int objectId;
      private int classId;
      private String objRmk1;
      private String objRmk2;
      private String objRmk3;
      private String objRmk4;
      private String objRmk5;
      private String objRmk6;
      private String objRmk7;

      public Builder setObjRmk1(String objRmk1) {
          this.objRmk1 = objRmk1;
          return this;
      }

      public Builder setObjRmk2(String objRmk2) {
          this.objRmk2 = objRmk2;
          return this;
      }

      public Builder setObjRmk3(String objRmk3) {
          this.objRmk3 = objRmk3;
          return this;
      }

      public Builder setObjectId(int objectId) {
          this.objectId = objectId;
          return this;
      }

      public Builder setClassId(int classId) {
          this.classId = classId;
          return this;
      }

      public MetaObject build(){
          return new MetaObject(this);
    }
  }
}
