import com.sun.xml.internal.bind.v2.TODO;

import java.util.Stack;

public class Parentheses {
    public static boolean checkParenthese(String s){
        String a = "()";
        String b = "[]";
        String c = "{}";
        Stack stackLocation = new Stack();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == a.charAt(0)){ stackLocation.push(1); num++;}
            if (s.charAt(i) == a.charAt(1)){ stackLocation.push(-1);num++;}
            if (s.charAt(i) == b.charAt(0)){ stackLocation.push(2);num++;}
            if (s.charAt(i) == b.charAt(1)){ stackLocation.push(-2);num++;}
            if (s.charAt(i) == c.charAt(0)){ stackLocation.push(3);num++;}
            if (s.charAt(i) == c.charAt(1)){ stackLocation.push(-3);num++;}
        }
        if ( num%2 != 0){return false;}
        int temp1 = Integer.parseInt(stackLocation.pop().toString());
        int temp2 = Integer.parseInt(stackLocation.pop().toString());
        Stack newStack = new Stack();
        //pop out 2 items, if sum up is not zero, continue, and put the first one into stack2
        //continue unless the sum up is zero, continue pop, and also pop stack2, each pair sum should be zero, or return false
        return (checkParentheseHelper(temp1,temp2,stackLocation,newStack));
    }

    public static boolean checkParentheseHelper(int a, int b, Stack s, Stack newS){
        if (a+b == 0){
            if (s.isEmpty() && newS.isEmpty()){
                return true;
            }else{
                while(!newS.isEmpty()){
                    s.push(newS.pop());
                }
                return checkParentheseHelper(Integer.parseInt(s.pop().toString()),Integer.parseInt(s.pop().toString()),s,newS);
            }
        }else{
            if (s.isEmpty()){
                return false;
            }else{
                newS.push(a);
                return checkParentheseHelper(b,Integer.parseInt(s.pop().toString()),s,newS);  // return recursive DON'T forget return!!!!
            }
        }
    }

    public static String insertParenthese(String s){
        s = s.replaceAll("\\s",""); //delete all the space
        if (checkParenthese(s)){ return s;}
        else{
            //TODO: put the char into a stack unless meet a ")" and string in the stack is unparenthese, then call helper
            Stack myStack = new Stack();
            String c = ")";
            String resultS = new String();
            for (int i = 0; i < s.length(); i++) {
                myStack.push(String.valueOf(s.charAt(i)));
                if (c.equals(String.valueOf(s.charAt(i)))){
                    resultS = stackToString(myStack);
                    if (!checkParenthese(resultS) && i < s.length()){
                        s = insertParentheseHelper(resultS,s,i)+s.substring(i+1,s.length());
                        return insertParenthese(s);
                    }
                    if (!checkParenthese(resultS) && i == s.length()){ // the last time, get all
                        return "("+resultS;
                    }
                    if (checkParenthese(resultS) && resultS.length() < s.length()){
                        myStack= stringToStack(resultS);
                    }
                }
            }
            return "The finial result is: "+resultS;
        }
//        if (checkParenthese(resultS)){ return resultS;}
    }

    public static String stackToString(Stack s){ // first in first out
        //TODO: pop out all the elements in a stack and return a string
        String result = new String();
        while(!s.isEmpty()){
            result = s.pop().toString()+result;
        }
        return result;
    }

    public static String stackToString2(Stack s){ // first in last out
        //TODO: pop out all the elements in a stack and return a string
        String result = new String();
        while(!s.isEmpty()){
            result = result+s.pop().toString();
        }
        return result;
    }

    public static Stack stringToStack(String s){ // first in first out
        //TODO: pop out all the elements in a stack and return a string
        Stack result = new Stack();
        for (int i = 0; i < s.length(); i++) {
            result.add(String.valueOf(s.charAt(i)));
        }
        return result;
    }

    public static String insertParentheseHelper(String s, String originS, int cut){
        //TODO: find ), go back unless meet one + OR - go back till the end + ( OR till * OR /
        String a = "()";
        String b = "+-";
        String c = "*/";
        Stack myStack = new Stack();
        String parentResult = new String();
        int num1 = 0; // num of + and -
        int num2 = 0; // num of ()
        int num3 = 0; // num of * and /
        int i = 0;
        String temp = new String();
        while(!(Math.abs(num1+num3-num2)==1 && num3>0 && num1 > 0) && i < s.length()){ // go back unless meet + OR -
            char c1 = s.charAt(s.length() - i - 1);
            myStack.push(String.valueOf(c1));
            if(c1 == b.charAt(0) || c1 == b.charAt(1)){ num1++;}  //b: +-
            if(c1 == c.charAt(0) || c1 == c.charAt(1)){ num3++;}  //c: */
            if (c1 == a.charAt(1)){ num2++;} //a: )
            if (c1== a.charAt(0)){
                num2--;
                if (num1>0){ num1--;}
                if (num3>0){ num3--;}
            }
            i++;
        }
        if (i==s.length()){
            myStack.push("(");
            parentResult = stackToString2(myStack);
            return parentResult;
        }
        if (Math.abs(num1+num3-num2)==1 && s.length()<=originS.length()){
            temp = (String) myStack.pop();
            myStack.push("(");
            myStack.push(temp);
            parentResult = stackToString2(myStack);
            return s.substring(0,s.length()-i)+parentResult;
        }
        return "************";
    }

    public static void main(String[] args) {
        String s1 = "1+2 )";
        System.out.println(insertParenthese(s1));
        String s2 = "1+2 )*3";
        System.out.println(insertParenthese(s2));
        String s3 = "1+2 )*3-4)";
        System.out.println(insertParenthese(s3));
        String s4 = "1+2 )*3-4))";
        System.out.println(insertParenthese(s4));
        String s5 = "1+2 )*3-4)*2+1)";
        System.out.println(insertParenthese(s5));
    }
}
