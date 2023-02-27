package testkg;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DslSqlV {
    public static Map varlst = Maps.newConcurrentMap();
    private static int retLinenum;
    public Map blklst = Maps.newConcurrentMap();
    List Blkli = Lists.newArrayList();

    public static int nextCmdIdxPoint = 0;

    //chose strutsn if age>1 then show bgt1  ele less 1

    public static void main(String[] args) {
        List<String> stmts = Lists.newArrayList();
      //  stmts.add("setvar name 007");

 //       stmts.add("echo name");

        stmts.add("echoStr name");
        stmts.add("setvar age true");

        stmts.add("while age");


        stmts.add("end while");


        stmts.add("def subprcs ");
              stmts.add("echoStr   in sub prcs");
        stmts.add("defend");


        stmts.add("if  age  then");


            stmts.add(":thenblk");
            stmts.add("bef call sub");
               stmts.add("call  subprcs   ");
               stmts.add(":aftercall");
               stmts.add("echoStr then blk finish  ");
        stmts.add("else");

            stmts.add(":elseblk");
            stmts.add("echoStr here else blk..");
            stmts.add("echoStr .. else blk finish  ");

        stmts.add("endif");




        stmts.add("echoStr finis.... ");

        // 按行监视额执行




        eval(stmts);


//       Dsl dsl=new Dsl();
//       setVar("vx",1); //setvaqr vx,1
//
//        //goto block1
//        setVar("name",007); //setvaqr name 007
//     //  dsl.Blkli.addStmt( dsl.setVar("k2",2));

    }

    private static void eval(List<String> stmts) {
        while (true) {
            if (nextCmdIdxPoint == stmts.size())
                break;
            String curStm = stmts.get(nextCmdIdxPoint);
            System.out.println("===>linenum:" + nextCmdIdxPoint + " : " + curStm);
            String[] tks_line =splitx(curStm) ;
            String op = tks_line[0].toString().trim();
            if (op.equals("setvar"))
                setVar(tks_line[1], tks_line[2]);
            else if (op.equals("echo"))  //show var
                echoVar(tks_line[1]);
            else if (op.equals("echoStr"))
                echoStr(curStm.substring(7));
            else if (op.equals("if"))
                ifProcess(tks_line, curStm, stmts);
            else if (op.equals("else"))
            {
                elseProcess(tks_line, curStm, stmts);
                continue;
            }



            // sub prcss fun
            else if (op.equals("def")) {
                defProcess(tks_line, curStm, stmts);
                continue;
            }
            else if (op.equals("call"))
            {
                callSubProcess(tks_line, curStm, stmts); continue;
            }

            else if (op.equals("defend"))   //in call mode
            {
                defEnd_Process(tks_line, curStm, stmts);continue;
            }


            else if (op.equals("ifgrt"))
                ifgrt(tks_line, curStm, stmts);

            else if (op.equals("iflessthan"))
                iflessthan(tks_line, curStm, stmts);

            else if (op.equals("jmp"))
                jmp(tks_line[1], stmts);
            nextCmdIdxPoint++;

        }
    }

    private static void defEnd_Process(String[] tksLine, String curStm, List<String> stmts) {

            nextCmdIdxPoint=retLinenum;
    }

    private static void callSubProcess(String[] tksLine, String curStm, List<String> stmts) {
        retLinenum=nextCmdIdxPoint+1;  //零食保存吓一条地址
        String subprName=tksLine[1];
        int nextPoint = getLinenum_stmtlabV2("def "+subprName, stmts);
        nextCmdIdxPoint = nextPoint+1 ;

    }

    private static void defProcess(String[] tksLine, String curStm, List<String> stmts) {
        int nextPoint = getLinenum_stmtlab("defend", stmts);
        nextCmdIdxPoint = nextPoint+1 ;
    }


    private static String[] splitx(String curStm) {

       String[] a= curStm.split(" ");
       List li=Lists.newArrayList();
        for(String e:a){  //内层遍历得到数组元素
          if(e.trim().length()>0)
              li.add(e.trim());
        }
     //   return li.toArray(new String[li.size()]);
        String[] strArray = (String[]) li.toArray(new String[li.size()]);
        return  strArray;
    }

    private static void ifProcess(String[] tks_line, String curStm, List<String> stmts) {
        String var = tks_line[1];
        if(var.trim().toLowerCase().equals("true") || varlst.get(var).toString().equals("true"))
        {
            //then prcs
        }else
        {
            int nextPoint = getLinenum_stmtlab("else", stmts);
            nextCmdIdxPoint = nextPoint ;
            //else prcs
        }
    }

    private static void elseProcess(String[] tksLine, String curStm, List<String> stmts) {
        //end if then block
        int nextPoint = getLinenum_stmtlab("endif", stmts);
        nextCmdIdxPoint = nextPoint + 1;


    }


    private static void jmp(String stmtLineTag, List<String> stmts) {
        nextCmdIdxPoint = getNextPcpoint(stmtLineTag, stmts);
    }



    private static int getNextPcpoint(String stmtLineTag, List<String> stmts) {
        int n = 0;
        for (String stmt : stmts) {
            if (stmt.trim().equals(":" + stmtLineTag.trim()))
                return n;
            n++;
        }
        return n;
    }



    private static int getLinenum_stmtlabV2(String stmtLable, List<String> stmts) {
        //  String stmtLable = "else";
        int n = 0;
        for (String stmt : stmts) {


            if (stmt.trim().equals(stmtLable))
                return n;
            n++;
        }
        return n;
    }

    private static int getLinenum_stmtlab(String stmtLable, List<String> stmts) {
      //  String stmtLable = "else";
        int n = 0;
        for (String stmt : stmts) {
            if(n<nextCmdIdxPoint)
            {
                //skip pre lines
                n++;continue;
            }

            if (stmt.trim().equals(stmtLable))
                return n;
            n++;
        }
        return n;
    }
    private static void echoStr(String tk) {
        System.out.println((tk));
    }

    private static void echoVar(String tk) {
        System.out.println(varlst.get(tk));
    }

    private List setBlock(String blknm1) {
        ArrayList<Object> value = Lists.newArrayList();
        blklst.put(blknm1, value);
        return value;
    }

    public static void setVar(String k, Object v) {

        varlst.put(k, v);
    }


    private static void iflessthan(String[] tks, String curStm, List<String> stmts) {
        String var = tks[1];
        String exprs = curStm.substring(0, curStm.indexOf("?"));
        int v = Integer.parseInt(exprs.split(" ")[2].toString());
        String[] tks2 = curStm.split(":");
        String elsblk = tks2[tks2.length - 1];
        String ifblk = curStm.substring(curStm.indexOf("?"), curStm.indexOf(":"));
        int i = Integer.parseInt(varlst.get(var).toString());
        if (i < v) {
            int nextPoint = getNextPcpoint(ifblk, stmts);
            nextCmdIdxPoint = nextPoint;
        } else {
            int nextPoint = getNextPcpoint(elsblk, stmts);
            nextCmdIdxPoint = nextPoint - 1;
            //find elseblk to got
        }
    }


    private static void ifgrt(String[] tks, String curStm, List<String> stmts) {
        String var = tks[1];
        String exprs = curStm.substring(0, curStm.indexOf("?"));
        int v = Integer.parseInt(exprs.split(" ")[2].toString());
        String[] tks2 = curStm.split(":");
        String elsblk = tks2[tks2.length - 1];
        String ifblk = curStm.substring(curStm.indexOf("?") + 1, curStm.indexOf(":"));
        if (Integer.parseInt(varlst.get(var).toString()) > v) {
            int nextPoint = getNextPcpoint(ifblk, stmts);
            nextCmdIdxPoint = nextPoint - 1;
        } else {
            int nextPoint = getNextPcpoint(elsblk, stmts);
            nextCmdIdxPoint = nextPoint;
            //find elseblk to got
        }

    }

}
