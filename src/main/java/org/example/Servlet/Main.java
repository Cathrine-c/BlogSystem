package org.example.Servlet;

public class Main {

    public static void main(String[] args) {

    }

    public static void main1(String[] args) {
        Hello hello = new Hello();
        Hello hello1 = new Hello();

        hello1.hello();

        hello.hello();
        hello.hello("很好玩的");
        hello.hello("wed1","wd1");
    }


   static class Hello{

        void hello(){
            System.out.println("你好");
        }


        void hello(String name){
            System.out.println(name+"你好");
        }


        void hello(String name1,String name2){
            System.out.println(name1+"先生、"+name2+"女士,你们好");
        }


    }


    public static void main2(String[] args) {
        int i =0;
        do{
            System.out.print(i);
            i++;
        }while (i<=9);
    }



}
