package org.example.test;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

/*
给定了一组输入，格式如下：
第一行输入一个整数
第二行输入n个数字，空格间隔
满足多个测试用例。注意写循环用例
 */
public class ScannerTest {
    @Test
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            for (int i =0;i<n;i++){
                int m = sc.nextInt();
                System.out.print(m+" ");
            }
        }


    }
}
