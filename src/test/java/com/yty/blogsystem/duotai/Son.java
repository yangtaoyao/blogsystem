package com.yty.blogsystem.duotai;

public class Son extends Father{
    public String s="Son";
    @Override
    public void method(){
        System.out.println("Son.method");
    }
    public void sonMethod(){
        System.out.println("Son.sonMethod");
    }
    public static void main(String[] arg){
        Father father=new Son();//向上转型
        father.method();//Son.method
        ((Son) father).sonMethod();//Son.sonMetho

        System.out.println( father.s);//Father
        System.out.println(((Son) father).s);//Son
    }
}
class Father{
    public String s="Father";
    public void method(){
        System.out.println("Father.method");
    }
}
