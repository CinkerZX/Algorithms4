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

    public static void main(String[] args) {
        String s = args[0];
        if (checkParenthese(s)){ System.out.println("Parentheses");}
        else{System.out.println("Not parentheses");}
        
    }
}
