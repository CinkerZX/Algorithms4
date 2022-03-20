package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        // 1.1.1
        int a1;
        double b1;
        boolean c1;
        a1 = (0+15) / 2;
        b1 = 2.0e-6 * 100000000.1;
        c1 = true && false || true && true;
        System.out.println(a1);
        System.out.println(b1);
        System.out.println(c1);

        //1.1.2
        double a2;
        a2 = (1+2.236)/2;
        System.out.println(a2);

        double b2 = 1 + 2 + 3 + 4.0;
        System.out.println(b2);

        boolean c2;
        c2 = 4.1 >= 4;
        System.out.println(c2);

        String d2 = 1 + 2 + "3";
        System.out.println(d2);

        // test for 1.1.3
        // 定义函数时加上public   System.out.println(intfor3(2,3,4));
        Main my = new Main();
        System.out.println(my.intfor3(2,2,2));
    }

    //1.1.3
    public String intfor3 (int num1, int num2, int num3){
        String result;
        if (num1 == num2 && num2 == num3)
            result = "equal";
        else
            result = "not euqal";
        return result;
    }



}
