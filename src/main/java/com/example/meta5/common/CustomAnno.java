package com.example.meta5.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 클래스, 필드, 메소드 에 적용 가능한 어노테이션
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAnno {

    // 1. 컴파일러에게 코드문법 에러체크
    // 2. 소프트웨어 개발 툴이 빌드나 배치시 코드를 자동으로 생성하는 정보 제공
    // 3. 실행시 특정 기능을 실행하도록 정보 제공

    // 4. 엘리먼트를 멤버로 가질수 있다.

    String elemName1();
    int elemName2() default 5;
    String value();     // default 엘리먼트

}
