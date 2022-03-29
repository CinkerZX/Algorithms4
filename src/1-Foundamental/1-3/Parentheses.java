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
        //delete all the space
        Stack myStack = new Stack();
        String c = ")";
        String resultS = new String();
        for (int i = 0; i < s.length(); i++) {
            myStack.push(s.charAt(i));
            if (c.equals(s.charAt(i))){
                while(!myStack.isEmpty()){
                    resultS = resultS+myStack.pop().toString();
                }
                if (!checkParenthese(resultS) && i < s.length()){
                    return insertParentheseHelper(resultS,s,i);
                }
                if (!checkParenthese(resultS) && i == s.length()){
                    return insertParentheseHelper(resultS,s,i);
                }
            }
        }
        if (checkParenthese(resultS)){ return resultS;}
        else{return "The finial result is: "+resultS;}
    }

    public static String insertParentheseHelper(String s, String originS, int cut){
        String a = "()";
        String b = "+-"; // find ), go back unless meet one + OR - go back till the end + ( OR till * OR /
        String c = "*/";
        Stack myStack = new Stack();
        String result = new String();
        int num1 = 0; // num of + and -
        int num2 = 0; // num of (
        int i = 0;
        while(num2==num1 && i < s.length()){ // go back unless meet + OR -
            myStack.push(s.charAt(i));
            if(s.charAt(i) == b.charAt(0) || s.charAt(i) != b.charAt(1)){ num1++;}
            if (s.charAt(i) == a.charAt(0)){ num2++;}
            i++;
        }
        if (i==s.length() || (num2!=num1 && (s.charAt(i) == c.charAt(0) || s.charAt(i) == c.charAt(1)))){myStack.push("(");}
        while(!myStack.isEmpty()){
            result = result+myStack.pop().toString();
        }
        return insertParenthese(result+s.substring(cut+1,s.length()));
    }

    public static void main(String[] args) {
        String s = args[0];
        if (checkParenthese(s)){ System.out.println("Parentheses");}
        else{System.out.println("Not parentheses");}
    }
}
