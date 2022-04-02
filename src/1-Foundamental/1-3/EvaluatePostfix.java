import java.util.*;

public class EvaluatePostfix {
    public static String PostfixtoIn(String s){ // s is postfix expression
        //TODO: scan the expression and save them into a queue (first in first out
        Queue<String> myQueue = new LinkedList<String>(); //LinkedList implements List as well as Queue. It can acts as a queue as well.
        for (int i = 0; i < s.length(); i++) {
            myQueue.offer(String.valueOf(s.charAt(i)));
        }
        //TODO: save the calculated result in a stack: when meet an operator, calculate and push the result into the stack
        Stack myStack = new Stack();
        String temp = new String();
        String temp1 = new String();
        String temp2 = new String();
        while(!myQueue.isEmpty()){
            temp = myQueue.poll();
            if (!isOperator(temp)){// temp is an num
                myStack.push(temp); // put the number in
            }
            else{ // temp is an operator
                temp1 = (String) myStack.pop();
                temp2 = (String) myStack.pop();
                myStack.push(String.valueOf(Calculate(temp2,temp,temp1)));
            }
        }
        if (myQueue.isEmpty()){
            return (String) myStack.pop();
        }
        return "Something wrong happened.";
    }

    public static boolean isOperator(String s){
        if (s.matches("[+\\-*/]")){
            return true;
        }
        else{return false;}
    }

    public static double Calculate(String n1, String operator, String n2){
        Double num1 = Double.parseDouble(n1);
        Double num2 = Double.parseDouble(n2);
        if (operator.equals("+")){
            return num1+num2;
        }
        if (operator.equals("-")){
            return num1-num2;
        }
        if (operator.equals("*")){
            return num1*num2;
        }
        if (operator.equals("/")){
            return num1/num2;
        }
        return 0;
    }

    public static void main(String[] args) {
        String a = "1";
        String b = "-";
        String c = "3";
        System.out.println(Calculate(a,b,c));
        String s = "342*15-/+";
        System.out.println(PostfixtoIn(s)); //-1
    }
}
