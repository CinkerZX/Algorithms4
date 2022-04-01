import com.sun.deploy.security.SelectableSecurityManager;

import java.util.Stack;

import static java.lang.Character.isDigit;

public class InfixToPostfix {

    public static String infixToPost(String s){
        String result = new String();
        Stack myStack = new Stack();
        String incoming = new String();
        // TODO: scan the expression from left to right, put into stack
        s = s.replaceAll("\\s",""); //delete all the space
        for (int i = 0; i < s.length(); i++) {
            incoming = String.valueOf(s.charAt(i));
            return infixToPostHelper(incoming,myStack,result,s,i);
        }
        return result;
    }

    public static String infixToPostHelper(String incoming, Stack myStack, String curResult, String Origin, int cut){
        //TODO: compare operator
        while (curResult.length()!=Origin.length() && cut < Origin.length()){
            if (!incoming.matches("[+\\-*/]")){ // not an operator
                curResult = curResult+incoming;
                cut++;
                if (cut == Origin.length()){
                    while(!myStack.isEmpty()){
                        curResult = curResult+myStack.pop();
                    }
                    return curResult;
                }
                else{
                    return infixToPostHelper(String.valueOf(Origin.charAt(cut)), myStack, curResult, Origin, cut);
                }
            }
            else{ // an operator
                if (myStack.isEmpty()){
                    myStack.push(incoming);
                    cut++;
                    return infixToPostHelper(String.valueOf(Origin.charAt(cut)), myStack, curResult, Origin, cut);
                }
                else{
                    String a = "+-";
                    String temp;
                    temp = (String) myStack.pop();
                    int temp_level; // in tha stack
                    int curOperator_level; // from the expression
                    if (temp.equals(String.valueOf(a.charAt(0))) || temp.equals(String.valueOf(a.charAt(1)))){ temp_level = 0;}
                    else{temp_level=1;}
                    if (incoming.equals(String.valueOf(a.charAt(0))) || incoming.equals(String.valueOf(a.charAt(1)))){ curOperator_level = 0;}
                    else{curOperator_level=1;}
                    if (temp_level==curOperator_level) { // if equal => add temp to the result + push the incoming into stack
                        curResult = curResult + temp;
                        myStack.push(incoming);
                        cut++;
                        return infixToPostHelper(String.valueOf(Origin.charAt(cut)), myStack, curResult, Origin, cut);
                    }
                    if(temp_level<curOperator_level){ // incoming is higher => push incoming to stack
                        myStack.push(temp);
                        myStack.push(incoming);
                        cut++;
                        return infixToPostHelper(String.valueOf(Origin.charAt(cut)), myStack, curResult, Origin, cut);
                    }
                    if (temp_level>curOperator_level){ // incoming is lower => add temp to the result + test the incoming operator to the top of the stack
                        curResult = curResult+temp;
                        return infixToPostHelper(incoming, myStack, curResult, Origin, cut);
                    }
                }
            }

        }
        return curResult;
    }

    public static void main(String[] args){
        String s1 = "A+B*C-D/E";
        System.out.println(infixToPost(s1)); //ABC*+DE/-
    }
}
