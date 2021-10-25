import java.util.*;

public class Expression {
    public static Dictionary expression_dictionary=new Hashtable();
    public static Random random=new Random();
    public static int max_operators=3;
    public static char[] operators_char={'+', '-', '÷', '×'};
    Value[] values;
    ArrayList<Integer> operate;
    public Expression(Value[] v,ArrayList<Integer> o){
        this.values=v;this.operate=o;
    }
    public static Expression Get_Random_Expression(int range) throws CloneNotSupportedException {
        int i=1+random.nextInt(max_operators);
        ArrayList<Integer> o=new ArrayList<Integer>();
        Value v[]=new Value[i+1];
        v[0]=Value.Get_Random_Value(range);
        for(int j=0;j<i;j++){
            for(;;)
            {
                o.add(random.nextInt(3));
                System.out.println("为表达式添加符号"+operators_char[o.get(j)]);
                v[j + 1] = Value.Get_Random_Value(range);
                Expression e=new Expression(v,o);
                System.out.println("进行表达式生成检测");
                if(e.toValue().toDouble()>=0){System.out.println("合格，表达式计算大于0");break;}
                else
                {
                    System.out.println("不合格，表达式计算小于0");
                    o.remove(o.size()-1);
                    System.out.println("删除最后一位操作符");
                }
            }
        }
        System.out.println("生成表达式成功");
        return new Expression(v,o);
    }
    @Override
    public String toString() {
        String str=values[0].toString();
        for(int i=0;i<operate.size();i++)
        {
            str+=operators_char[operate.get(i)];
            str+=values[i+1].toString();
        }
        return str;
    }
    public String toFromalString(){
        String str=values[0].toString();
        str+=operators_char[operate.get(0)];
        str+=values[1].toString();
        for(int i=1;i<operate.size();i++)
        {
            if((operators_char[operate.get(i-1)]=='+'||operators_char[operate.get(i-1)]=='-')&&(operators_char[operate.get(i)]=='÷'||operators_char[operate.get(i)]=='×'))
                str="("+str+")";
            str+=operators_char[operate.get(i)];
            str+=values[i+1].toString();
        }
        return str;
    }
    public Value toValue() throws CloneNotSupportedException {
        System.out.println("为"+toString()+"进行表达式值计算");
        Value value=values[0];
        for(int i=0;i<operate.size();i++)
        {
            if(operators_char[operate.get(i)]=='+'){value=Value.Add(value,values[i+1]);}
            else if(operators_char[operate.get(i)]=='-'){value=Value.Sub(value,values[i+1]);}
            else if(operators_char[operate.get(i)]=='×'){value=Value.Mut(value,values[i+1]);}
            else if(operators_char[operate.get(i)]=='÷'){value=Value.Div(value,values[i+1]);}
        }
        return value;
    }
    public void AddedDictionary()
    {
        ArrayList<Integer> list=GetSortedIntegerList(operate);
        if(expression_dictionary.get(list)==null)
        {
            ArrayList<Expression> List=new ArrayList<Expression>();
            List.add(this);
            expression_dictionary.put(list,List);
        }
        else
        {
            ArrayList<Expression> List=(ArrayList<Expression>)expression_dictionary.get(list);
            List.add(this);
        }
    }
    public static boolean CheckExpressionSame(Expression e1,Expression e2){
        if(e1.operate.size()!=e2.operate.size())return false;
        ArrayList<String[]> temp1=new ArrayList<String[]>();
        ArrayList<String[]> temp2=new ArrayList<String[]>();
        for(int i=0;i<e1.operate.size();i++){
            if(operators_char[e1.operate.get(i)]=='+'||operators_char[e1.operate.get(i)]=='×')
            {
                temp1.add(GetRLStringExpression(e1,i));
                temp2.add(GetRLStringExpression(e2,i));
            }
        }
        return CheckListHaveSmall(temp1,temp2);
    }
    public static String[] GetRLStringExpression(Expression e,int x){
        String strR=e.values[0].toString();
        for(int i=0;i<x;i++)
        {
            strR+=operators_char[e.operate.get(i)];
            strR+=e.values[i+1].toString();
        }
        String strL=e.values[x+1].toString();
        for(int i=x+1;i<e.operate.size();i++)
        {
            strL+=operators_char[e.operate.get(i)];
            strL+=e.values[i+1].toString();
        }
        String[] s={strR,strL};
        return s;
    }
    public static boolean CheckListHaveSmall(ArrayList<String[]> a1,ArrayList<String[]> a2){
        boolean flag=false;
        for(String[] s1:a1)
            for(String[] s2:a2)
            {
                if(s1[0]==s2[0]&&s1[1]==s2[1])return true;
                if(s1[0]==s2[1]&&s1[1]==s2[0])return true;
            }
        return false;
    }
    public static ArrayList<Integer> GetSortedIntegerList(ArrayList<Integer> source){
        ArrayList<Integer> list=DeepCloneIntegerList(source);
        Collections.sort(list);
        return  list;
    }
    private static ArrayList<Integer> DeepCloneIntegerList(ArrayList<Integer> source) {
        if (null == source) {
            return null;
        }
        ArrayList<Integer> newList = new ArrayList<Integer>();
        for(Integer intObj : source) {
            newList.add(intObj.intValue());
        }
        return newList;
    }



    public static void main(String[] args) throws CloneNotSupportedException {
        for(int i=0;i<10;i++)
        {
            Expression e=Get_Random_Expression(10);
            String s=e.toString();
            System.out.println(s);
            String[] temp=GetRLStringExpression(e,0);
            System.out.println(temp[0]);
            System.out.println(temp[1]);
        }
    }
}

