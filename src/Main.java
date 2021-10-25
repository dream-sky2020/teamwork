import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        JFrame f = new JFrame("四则运算生成器");
        f.setSize(600, 200);
        f.setLocation(580, 200);
        f.setLayout(new FlowLayout());

        JLabel lName = new JLabel("输入生成问题的数量：");
        // 输入框
        JTextField tfName = new JTextField("");
        tfName.setText("");
        tfName.setPreferredSize(new Dimension(80, 30));

        JLabel lPassword = new JLabel("输入数的范围：");
        // 输入框
        JTextField tfPassword = new JTextField("");
        tfPassword.setText("");
        tfPassword.setPreferredSize(new Dimension(80, 30));

        JButton b = new JButton("开始生成");
        b.setPreferredSize(new Dimension(100, 30));
        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int Problem_of_number;
                int Range_of_number;
                Problem_of_number=Integer.valueOf(tfName.getText());
                Range_of_number=Integer.valueOf(tfPassword.getText());
                try {
                    Main(Problem_of_number,Range_of_number);
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        f.add(lName);
        f.add(tfName);
        f.add(lPassword);
        f.add(tfPassword);
        f.add(b);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setVisible(true);
        tfPassword.grabFocus();


//        Main(100,10);
    }

    public static void Main(int Problem_of_number,int Range_of_number) throws CloneNotSupportedException, IOException {
        int problem_of_number=Problem_of_number;
        int range_of_number=Range_of_number;
        File directory = new File("");

        String exercise_file=directory.getAbsolutePath()+"\\Exercises.txt";
        FileWriter exercise_file_writer=fileWriter_writer(exercise_file);
        String answer_file=directory.getAbsolutePath()+"\\Answers.txt";
        FileWriter answer_file_writer=fileWriter_writer(answer_file);
        for(int i=0;i<problem_of_number;i++)
        {
            System.out.println("开始生成第"+String.valueOf(i+1)+"个表达式");
            for(;;)
            {
                Expression e = Expression.Get_Random_Expression(range_of_number);
                boolean flag=true;
                ArrayList<Expression> List=(ArrayList<Expression>)Expression.expression_dictionary.get(Expression.GetSortedIntegerList(e.operate));
                if(List!=null){
                    for(Expression expression:List){
                        for(int j=0;j<expression.operate.size();j++)
                        {
                            if(Expression.CheckExpressionSame(e,expression)==true)flag=false;
                        }
                    }
                }
                if(flag==true)
                {
                    e.AddedDictionary();
                    System.out.println("开始写入文件");
                    exercise_file_writer.write(e.toFromalString()+"\n");
                    answer_file_writer.write(e.toValue().toString()+"\n");
                    break;
                }
            }
        }
        exercise_file_writer.close();
        answer_file_writer.close();
    }
    public static FileWriter fileWriter_writer(String s) throws IOException {
        File f = new File(s);
        if(!f.exists()){	//如果_test2文件夹不存在
            f.mkdir();		//创建文件夹
        }
        FileWriter fr = new FileWriter(f);
        return fr;
    }
    public static FileOutputStream fileOutputStream_writer(String s) throws FileNotFoundException {
        File f = new File(s);
        FileOutputStream fos = new FileOutputStream(f);
        return fos;
    }
}
