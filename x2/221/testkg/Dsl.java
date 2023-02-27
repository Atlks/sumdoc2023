package testkg;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Dsl {
    public static Map varlst = Maps.newConcurrentMap();
    public Map blklst = Maps.newConcurrentMap();
    List Blkli = Lists.newArrayList();

    public static int nextCmdIdxPoint = 0;

    //chose strutsn if age>1 then show bgt1  ele less 1

    public static void main(String[] args) {
        List<String> stmts = Lists.newArrayList();
        stmts.add("setvar name 007");
        stmts.add("setvar age 33");

        stmts.add("echo name");
        stmts.add("echo name");
        stmts.add("echo name");

        stmts.add("echo name");

        stmts.add("echoStr name");
        stmts.add("ifgrt age 2 ? thenblk : elseblk ");

        stmts.add("echo name");
        stmts.add("echo name");
        stmts.add(":thenblk");
        stmts.add("echoStr here_thenblk_start");
        stmts.add("echoStr name");
        stmts.add("echoStr 1111 ");
        stmts.add("jmp end11 ");

        stmts.add(":elseblk");
        stmts.add("echoStr here else blk..");
        stmts.add("echoStr 333 ");


        stmts.add(":end11");
        stmts.add("echoStr finis.... ");

        // 按行监视额执行

        while (true) {
            if( nextCmdIdxPoint ==stmts.size())
                break;
            String curStm = stmts.get(nextCmdIdxPoint);
            System.out.println("===>linenum:"+ nextCmdIdxPoint +" : " + curStm );
            String[] tks = curStm.split(" ");
            String op = tks[0];
            if (op.equals("setvar"))
                setVar(tks[1], tks[2]);
            else if (op.equals("echo"))  //show var
                echoVar(tks[1]);
            else if (op.equals("echoStr"))
                echoStr(curStm.substring(7));
            else if (op.equals("ifgrt"))
                ifgrt(tks, curStm, stmts);

            else if (op.equals("iflessthan"))
                iflessthan(tks, curStm, stmts);

            else if (op.equals("jmp"))
                jmp(tks[1],stmts);
            nextCmdIdxPoint++;

        }


//       Dsl dsl=new Dsl();
//       setVar("vx",1); //setvaqr vx,1
//
//        //goto block1
//        setVar("name",007); //setvaqr name 007
//     //  dsl.Blkli.addStmt( dsl.setVar("k2",2));

    }

    private static void iflessthan(String[] tks, String curStm, List<String> stmts) {
        String var = tks[1];
        String exprs=curStm.substring(0,curStm.indexOf("?"));
        int v = Integer.parseInt( exprs.split(" ")[2].toString());
        String[] tks2 = curStm.split(":");
        String elsblk = tks2[tks2.length - 1];
        String ifblk= curStm.substring(  curStm.indexOf("?") ,curStm.indexOf(":"));
        int i = Integer.parseInt(varlst.get(var).toString());
        if (i < v) {
            int nextPoint = getNextPcpoint(ifblk, stmts);
            nextCmdIdxPoint = nextPoint;
        } else {
            int nextPoint = getNextPcpoint(elsblk, stmts);
            nextCmdIdxPoint = nextPoint-1;
            //find elseblk to got
        }
    }

    private static void jmp(String stmtLineTag, List<String> stmts) {
        nextCmdIdxPoint =  getNextPcpoint(stmtLineTag, stmts);
    }

    private static void ifgrt(String[] tks, String curStm, List<String> stmts) {
        String var = tks[1];
        String exprs=curStm.substring(0,curStm.indexOf("?"));
        int v = Integer.parseInt( exprs.split(" ")[2].toString());
        String[] tks2 = curStm.split(":");
        String elsblk = tks2[tks2.length - 1];
        String ifblk= curStm.substring(  curStm.indexOf("?")+1 ,curStm.indexOf(":"));
        if (Integer.parseInt(varlst.get(var).toString()) > v) {
            int nextPoint = getNextPcpoint(ifblk, stmts);
            nextCmdIdxPoint = nextPoint-1;
        } else {
            int nextPoint = getNextPcpoint(elsblk, stmts);
            nextCmdIdxPoint = nextPoint;
            //find elseblk to got
        }

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
}
