import java.util.Random;
public class Value {
    private static Random random=new Random();
    int natural;
    int fraction;
    int denominator;
    public Value(int natural){
        this.natural=natural;
        this.fraction=0;
        this.denominator=1;
    }
    public Value(int natural,int fraction,int denominator){
        this.natural=natural;
        this.fraction=fraction;
        this.denominator=denominator;
    }
    public void reduce(){
        System.out.println("进行化简");
        while(fraction<0){
            fraction+=denominator;
            natural--;
        }
        if(fraction>=denominator&&fraction!=0&&denominator!=0)
        {
            natural+=fraction/denominator;
            fraction%=denominator;
        }
        if(fraction!=0&&fraction!=1&&denominator!=0&&denominator!=1&&denominator%fraction==0){
            int gcd=Get_Gcd(denominator,fraction);
            denominator/=gcd;
            fraction/=gcd;
        }
    }
    public void refraction(){
        fraction+=natural*denominator;
        natural=0;
        System.out.println("该Value转化为分数"+fraction+'\\'+denominator);

    }

    @Override
    public String toString() {
        String str="";
        reduce();
        if(natural!=0)
        {
            str+=String.valueOf(natural);
        }
        if(natural!=0&&fraction!=0)
        {
            str+='\'';
        }
        if(fraction!=0)
        {
            str=str+String.valueOf(fraction)+'/'+String.valueOf(denominator);
        }
        if(natural==0&&fraction==0)
        {
            str+='0';
        }
        return str;
    }
    public static Value Add(Value v1,Value v2) throws CloneNotSupportedException {

        System.out.println("进行加法运算");

        Value v1_clone=v1.clone();
        Value v2_clone=v2.clone();
        v1_clone.refraction();
        v2_clone.refraction();
        int f=v1_clone.fraction*v2_clone.denominator+v1_clone.denominator*v2_clone.fraction;
        int d=v1_clone.denominator* v2_clone.denominator;

        Value v3=new Value(0,f,d);
        System.out.println("计算出了新的结果"+f+'\\'+d);
        v3.reduce();
        return  v3;
    }
    public static Value Sub(Value v1,Value v2) throws CloneNotSupportedException {

        System.out.println("进行减法运算");

        Value v1_clone=v1.clone();
        Value v2_clone=v2.clone();
        v1_clone.refraction();
        v2_clone.refraction();
        int f= v1_clone.fraction* v2_clone.denominator-v1_clone.denominator*v2_clone.fraction;
        int d=v1_clone.denominator* v2_clone.denominator;

        Value v3=new Value(0,f,d);
        System.out.println("计算出了新的结果"+f+'\\'+d);
        v3.reduce();
        return  v3;
    }
    public static Value Mut(Value v1,Value v2) throws CloneNotSupportedException {

        System.out.println("进行乘法运算");

        Value v1_clone=v1.clone();
        Value v2_clone=v2.clone();
        v1_clone.refraction();
        v2_clone.refraction();
        int f=v1_clone.fraction*v2_clone.fraction;
        int d=v1_clone.denominator*v2_clone.denominator;

        Value v3=new Value(0,f,d);
        System.out.println("计算出了新的结果"+f+'\\'+d);
        v3.reduce();
        return  v3;
    }
    public static Value Div(Value v1,Value v2) throws CloneNotSupportedException {

        System.out.println("进行除法运算");

        Value v1_clone=v1.clone();
        Value v2_clone=v2.clone();
        v1_clone.refraction();
        v2_clone.refraction();
        int f=v1_clone.fraction*v2_clone.denominator;
        int d=v1_clone.denominator*v2_clone.fraction;
        Value v3=new Value(0,f,d);
        System.out.println("计算出了新的结果"+f+'\\'+d);
        v3.reduce();
        return  v3;
    }

    @Override
    public Value clone() throws CloneNotSupportedException {
        return new Value(natural,fraction,denominator);
    }
    public static Value Get_Random_Value(int Range){
        if(random.nextBoolean()){
            int na=1+random.nextInt(Range-1);

            System.out.println("随机生成自然数"+String.valueOf(na));

            return new Value(na);
        }
        else{
            int den=2+random.nextInt(Range-2);
            int fra=1+random.nextInt(den-1);

            System.out.println("随机生成真分数"+fra+'\\'+String.valueOf(den));

            return new Value(0,fra,den);
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        for(int i=0;i<3;i++)
        {
            Value v1 =Get_Random_Value(10);
            Value v2 =Get_Random_Value(10);
            System.out.println(v1.toString());
            System.out.println(v2.toString());
            System.out.println(Div(v1, v2).toString());
        }
    }
    private int Get_Gcd(int x,int y) {
        int min = x < y ? x : y;
        for (int i = min; i >= 1; i--) {
            if (x % i == 0 && y % i == 0) {
                return i;
            }
        }
        return 0;
    }
    public double toDouble(){
        double d;
        d=natural+(double)fraction/denominator;
        return d;
    }

}
