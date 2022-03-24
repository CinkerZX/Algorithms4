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

        // test for 1.1.5
        System.out.println(test5(0.8,1.2));

        //test for 1.1.6
        System.out.println(test6(33));
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

    //1.1.5
    public static boolean test5 (double x, double y){
        boolean result5;
        if (x > 0 && x < 1 && y > 0 && y <1)
            result5 = true;
        else
            result5 = false;
        return result5;
    }

    //1.1.6
    public static String test6 (int x6){
        String result6;
        result6 = "";
        while(x6 != 0){
            int remainder = x6 % 2;
            result6 = remainder + result6;
            x6 /= 2;  // x6 = x6/2;
        }
        return result6;
    }
}
