package com.example.meta5.common;

@FunctionalInterface
public interface Operation {
    int execute(int a, int b);
    /**     이렇게 사용한다.
     *         Operation add = (a, b) -> a + b;
     *         // 빼기 연산
     *         Operation subtract = (a, b) -> a - b;
     *         // 곱하기 연산
     *         Operation multiply = (a, b) -> a * b;
     *         // 나누기 연산
     *         Operation divide = (a, b) -> a / b;
     *
     *         // 연산 실행
     *         System.out.println("Addition: " + add.execute(10, 5));
     */
}
