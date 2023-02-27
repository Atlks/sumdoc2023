bk prgrm exprs Parser   



package testkg;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExprsCalc {

    public static void main(String[] args) {
        String e = "1+2*3-4/5";  //should 9 ....not 2
        //将字符串转化为集合
        List<String> l = toInfixExpressionList(e);
        System.out.println(JSONObject.toJSONString(l, true));


        System.out.println(calc(l));

    }

    static Stack dtStk = new Stack();
    static Stack opStk = new Stack();

    private static Object calc(List<String> list) {
        //    String e="4+1*6-8/9";
        //计算，算出 后缀表达式 的结果


        for (String item : list) {
            System.out.println("===>" + item);
            if (isData(item))
                dtStk.push(item);
            else if (isOptr(item)) {
                if (opStk.isEmpty()) //first op
                {
                    opStk.push(item);
                    continue;
                }
                if (lessThanPreOp(item)) {
                    calcLastOpNpushToDtStk();  calcLastOpNpushToDtStk();  calcLastOpNpushToDtStk();
                    opStk.push(item);
                } else {
                    opStk.push(item);
                }

                /**/

            }
        }

        calcLastOpNpushToDtStk();calcLastOpNpushToDtStk();calcLastOpNpushToDtStk();
        return  Float.parseFloat(dtStk.pop().toString());


    }

    private static boolean lessThanPreOp(String curOP) {
        String preOp= opStk.peek().toString();
        if(curOP.equals("*") || curOP.equals("/") )
            if(preOp.equals("+")|| preOp.equals("-"))
            {
                return false;
            }

        return true;
    }

    private static void calcLastOpNpushToDtStk() {
        if(dtStk.size()==1)
            return;
        //as asm ..two dt and one targetStore
        float num1 = Float.parseFloat(dtStk.pop().toString());
        float num2 = Float.parseFloat(dtStk.pop().toString());



        String op = (String) opStk.pop();
        float res = 0;
        if (op.equals("+")) {
            res = num1 + num2;
        } else if (op.equals("-")) {
            res = num2 - num1;
        } else if (op.equals("*") || op.equals("x")) {
            res = num1 * num2;
        } else if (op.equals("/")) {
            res = num2 / num1;
        } else {
            throw new RuntimeException("输入的符号不正确");
        }
        dtStk.push(res);

    }


    // token 词法分析  asm 词法很简单，空格风格即可
    //      “（3+4）*5-2”   --> “[（,3,+,4,）,*,5,-,2]”
    public static List<String> toInfixExpressionList(String s) {
        List<String> list = new ArrayList<String>();
        int index = 0;  //索引，相当于指向字符串每一个元素的指针
        char c;  //将字符串中取出的元素付给c
        String dt = "";
        String curToken = "";
        while (index < s.length()) {
            String curChar = s.charAt(index) + "";
            //  System.out.println("===>curChar:"+curChar);
            //首先判断c是不是操作符
            if (isOptr(curChar)) {
                list.add(curToken);// add last token
                curToken = "";
                list.add(curChar);  //add op token

                index++;
            } else {  //noop
                //如果是数据的化
                //如果是多位数的话，将多位数转化为String
                curToken = curToken + curChar;
                index++;
                if (index >= s.length())
                    list.add(curChar);
            }


        }
        return list;
    }

    private static boolean isOptr(String curChar) {
        if (curChar.equals("+") || curChar.equals("-") || curChar.equals("*") || curChar.equals("/"))
            return true;
        else
            return false;
    }

    private static boolean isData(String token) {
        return !isOptr(token);
    }
}
