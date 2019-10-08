import java.util.*;
//先讀 讀到前括號 將其組合起來 放入第二個arr
//獨到乘除 在前後物件建立括號
//讀左誇號 紀錄位置&權重 越大越深
//當讀到右括弧

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

    public static void main(String[] args) throws Exception {
        ArrayList<String> arr = new ArrayList<String>();
        ArrayList<String> strarr = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        char c ;
        int ocn = 0, front = 0, cont = 0;
        StringTokenizer st= new StringTokenizer(str, "+-*/()%", true);
        

        while( st.hasMoreTokens() ){
            arr.add( st.nextToken() );
            ocn++;
        }
        String temp = null;
        int time = 0;
        for(int i = 0; i<arr.size(); i++){
            if(front+1 >= arr.size() ) break;
            if(arr.get(i).charAt(0) == '(' && arr.get(i).length() == 1 )
                front = i;
            if(arr.get(i).charAt(0) == ')' &&front != -1){//執行字串合併
       //         System.out.println("現在的i是 " + i);
                for(int ii = front; ii<i; ii++){
                    if(ii >= arr.size() ) break;
                    if( !isDig( arr.get(ii) ) ){//如果讀到符號
                        c = arr.get(ii).charAt(0);
                        if(c == '*' ||c == '/'||c == '%'  ){
                            String temp1 ="(", temp2 = ")"; 
                            arr.add(ii-1-time, temp1);
                            if(ii+ 3 < arr.size()-2 )
                                arr.add(ii+3, temp2);
                            else arr.add(temp2);
                            ii++;
                            i+=2;
                            time += 3;
         //                   System.out.println( "位置: "+ ii); 
         //                   for (String strr : arr) { 
         //                       System.out.print(strr + " "); 
        //                    } 
        //                    System.out.println("");
                        }
                    }
                }

                temp =arr.get(front);
                for(int j = front + 1 ; j <= i; j++){
                    temp += arr.get(j);
                    cont++;
                }
            }
            if(temp != null){
                arr.trimToSize();
       //         System.out.println(temp);
                arr.set(front, temp);
                temp = null;
                for(; cont > 0; cont--){
                    if(cont == 0) break;
                    arr.remove(front +1);
                }
                cont = 0;
                i = 0;
                front = -1;
                time = 0;
            }

        }
   //     for (String strr : arr) { 
   //         System.out.print(strr + " "); 
   //     } 
        time = 0;
    //    System.out.println("");
        for(int i = 0; i<arr.size(); i++){
            if(i >= arr.size() ) break;
   if( !isDig( arr.get(i) ) ){//如果讀到符號
                c = arr.get(i).charAt(0);
                if(c == '*' ||c == '/'||c == '%'  ){
                    String temp1 ="(", temp2 = ")"; 
                    arr.add(i-1-time, temp1);
                    if(i+ 3 < arr.size()-1 ){
                        arr.add(i+3, temp2);}

                    else arr.add(temp2);
                    i++;
                    time +=3;
        //            System.out.println( "位置: "+ i); 
        //            for (String strr : arr) { 
       //                 System.out.print(strr + " "); 
        //            } 
       //             System.out.println("");
                }
            }
        }
   //     for (String strr : arr) { 
   //        System.out.print(strr + " "); 
    //    } 
    //    System.out.println("");

        for(int i = 0; i<arr.size(); i++){
            if(i >= arr.size() ) break;
   if( !isDig( arr.get(i) ) ){//如果讀到符號
                c = arr.get(i).charAt(0);
                if(c == '+' ||c == '-'  ){
                    String temp1 ="(", temp2 = ")"; 
                    if(i-1-time >-0)
                        arr.add(i-1-time, temp1);
                    else arr.add(0, temp1);
                    if(i+3+time < arr.size()-1 ){
                        int k = 0;
                        while(arr.get(i+4+time+k).charAt(0) == ')')
                            k++;
                        if(arr.get(i+3+time+k).charAt(0) =='*'||arr.get(i+3+time+k).charAt(0) =='/'||arr.get(i+3+time+k).charAt(0) =='%' )
                           time+=4;
                        if(i+3+time < arr.size())
                            arr.add(i+3+time, temp2);
                        else arr.add(temp2);
                    }
                    else arr.add(temp2);
                    i++;
                    time +=4;
       //             System.out.println( "位置: "+ i); 
       //             for (String strr : arr) { 
      //                  System.out.print(strr + " "); 
      //              } 
     //               System.out.println("");
                }
            }
        }
    //    for (String strr : arr) { 
    //        System.out.print(strr + " "); 
    //    } 

    //    System.out.println("");
        for(int i = 0; i <arr.size(); i++){
            st= new StringTokenizer(arr.get(i), "+-*/()%", true);
            while(st.hasMoreTokens()){
                strarr.add(st.nextToken());
            }
        }
        arr.clear();
        char temp7;
        for (String strr : strarr) { 
            if(strr.charAt(0) == '(') continue;
            if( isDig(strr) )
                arr.add(strr);
            else{
                if(strr.charAt(0) == ')'){
                    if(!stack.empty()){
                    arr.add(stack.pop());}
                    }else stack.push(strr);
            }
            temp7 = strr.charAt(0);
        } 
        while(!stack.empty()){
            arr.add(stack.pop());
        }
        for (String strr : arr) { 
            System.out.print(strr + " "); 
        } 
        System.out.println("");
        scan.close();
    }
}
