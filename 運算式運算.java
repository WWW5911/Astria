import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Stack;
import java.util.StringTokenizer;

class Equation{

    static Boolean isDig(String str) {
        Boolean isDigital = true;
        for (int i = 0; i < str.length(); i++)
            if (!Character.isDigit(str.charAt(i))) {
                isDigital = false;
                break;
            }
        return isDigital;
    }

    static int priority(String str) {
        if (str.charAt(0) == '*' || str.charAt(0) == '/' || str.charAt(0) == '%')
            return 2;
        if (str.charAt(0) == '+' || str.charAt(0) == '-')
            return 1;
        return 0;
    }

    static String [] strTok(String string){
        String [] ans = new String[string.length()];
        StringTokenizer st= new StringTokenizer(string, "+-*/()%", true); 
        int cont = 0; 
        while(st.hasMoreTokens()){
            ans[cont] = st.nextToken();
            ++cont;
       }
       return ans;
    }
    static String [] strPost(String string){
        String [] arr = strTok(string);
        String [] ans = new String[arr.length];
        Stack<String> stack = new Stack<String>();
        int cont = 0;

        stack.push("a");
        for(int i = 0; i<arr.length; i++){
            if(arr[i] == null) break;
            if( isDig(arr[i]) )
                ans[cont++] = arr[i];
            else{
                if(arr[i].charAt(0) != '(' && arr[i].charAt(0) != ')'){
                    while( priority( stack.peek() ) >= priority ( arr[i] ) ){
                        ans[cont++] = stack.pop();
                        if(stack.empty()) break;
                    }
                    stack.push(arr[i]);
                }
                if(arr[i].charAt(0) == '(' )
                    stack.push(arr[i]);
                if(arr[i].charAt(0) == ')'){
                    while(stack.peek().charAt(0) != '(')
                        ans[cont++] = stack.pop();
                    if( stack.peek().charAt(0) == '(' )
                        stack.pop();
                }
            }
       }
        while( stack.peek().charAt(0) !='a')
            ans[cont++] = stack.pop();
        return ans;
    }
    
    static BigDecimal Operation(String str){
        String [] arr = strPost(str);
        Stack<String> stack = new Stack<String>();
        BigDecimal temp1 = new BigDecimal(0), temp2 = new BigDecimal(0);
        for(int i = 0; i<arr.length; ++i){
            if(arr[i] != null)
                if(isDig(arr[i]))
                    stack.push(arr[i]);
            else{
                temp2 = new BigDecimal(stack.pop());
                temp1 = new BigDecimal(stack.pop());
                switch(arr[i].charAt(0)){
                    case '+' :
                        temp1 = temp1.add(temp2);
                        stack.push(temp1.toString());
                        break;
                    case '-' :
                        temp1 = temp1.subtract(temp2);
                        stack.push(temp1.toString());
                        break;
                    case '*' :
                        temp1 = temp1.multiply(temp2);
                        stack.push(temp1.toString());
                        break;
                    case '/' :
                        temp1 = temp1.divide(temp2);
                        stack.push(temp1.toString());
                        break;
                    case '%' :
                        temp1 = temp1.remainder(temp2);
                        stack.push(temp1.toString());
                        break;
                }
            }
        }
        BigDecimal ans = new BigDecimal(stack.pop());
        return ans;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BigDecimal  ans = Equation.Operation(new BufferedReader(new InputStreamReader(System.in)).readLine());

        System.out.println(ans);
    }
}
