package com.testclass.innerclasses;


/*
匿名类继承默认类
 */
public class Parcel8 {

    public Wrapping wrapping(int x) {
        return new Wrapping(x) {
            @Override
            public int value() {
                return super.value() * 47;
            }
        };
    }

    public static void main(String[] args) {
        Parcel8 p = new Parcel8();
        Wrapping w = p.wrapping(10);
        System.out.println("value:" + w.value());
    }
}
