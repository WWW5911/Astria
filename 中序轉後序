import java.util.*;

public class Main {

    public static Boolean isDig(String str){  
        Boolean isDigital = true;   
        for(int i = 0; i<str.length(); i++)  
            if( !Character.isDigit(str.charAt(i)) ){  
                isDigital = false;  
                break;     
            }  
        return isDigital;  
    } 
    public static int priority(String str){ 
        if(str.charAt(0) == '*'||str.charAt(0) == '/'||str.charAt(0) == '%')
            return 2;
        if(str.charAt(0) == '+'||str.charAt(0) == '-')
            return 1;
        if(str.charAt(0) == 'a')
            return -1;
        return 0;
    } 


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        String [] arr = new String[10000];
        String [] ans = new String[10000];
        StringTokenizer st= new StringTokenizer(str, "+-*/()%", true); 
        Stack<String> stack = new Stack<String>();
        int cont = 0, nowLevel = 0, pre = 100; 
        while(st.hasMoreTokens()){
            arr[cont] = st.nextToken();
            cont++;
       }
       cont = 0;
       stack.push("a");
       for(int i = 0; i<arr.length; i++){
            if(arr[i] == null) break;
            if( isDig(arr[i]) ){
                ans[cont++] = arr[i];
            }
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
                    while(stack.peek().charAt(0) != '('){
                        ans[cont++] = stack.pop();
                    }
                    if( stack.peek().charAt(0) == '(' )
                        stack.pop();
                }
            }
       }
        while( stack.peek().charAt(0) !='a'){
            ans[cont++] = stack.pop();
        }

        for(String strr : ans){
            if(strr == null) break;
            if(strr.charAt(0) == '(') continue;
            System.out.print(strr+" ");
        }
        System.out.println("");

       scan.close();
    }
}
