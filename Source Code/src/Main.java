import com.sun.javafx.image.IntPixelGetter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
   static HashMap<String,Integer>coursetopic=new HashMap<String,Integer>(); //coursetopic key value apir
   static List<String> course=new ArrayList<String>(); //course list
   static List<String> prof=new ArrayList<String>(); //professor list
    public static void main(String[] args) {
        System.out.println("CourseId"+" "+"professorId"+" "+"Matching Score");
        readdata();
        while(!course.isEmpty())
        {
            int max=0;
            String key="";
            for(Map.Entry< String,Integer> entry : coursetopic.entrySet()) //extracting course and topic whose topic has more percentage
            {
                int v=entry.getValue();
                if(v>max)
                {
                 max=v;
                 key=entry.getKey();
                }
            }
            expt(key,max);

        }
    }
    public static void readdata() //reading files at runtime
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/course"));
            String s="";
            while ((s=br.readLine())!=null)
            {
                String []sp=s.split(",");
                course.add(sp[0]);
            }
            br.close();
            BufferedReader br1 = new BufferedReader(new FileReader("data/CourseTopic"));
            String ct="";
            while ((ct=br1.readLine())!=null) {

                String csp[] = ct.split(",");
                coursetopic.put(csp[0]+csp[1],Integer.parseInt(csp[2]));
            }
            br1.close();
            BufferedReader br2 = new BufferedReader(new FileReader("data/professor"));
            String p="";
            while ((p=br2.readLine())!=null) {

                String pd[] = p.split(",");
                prof.add(pd[0]);
            }
            br2.close();

        }
        catch (Exception e)
        {

        }
    }

    public static void expt(String key,int score)
    {
        String c=key.substring(0,3);
        String t=key.substring(3);
      //  System.out.println(c);
        int le=0;
        String expprof="";
        int score2=0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/Expertise"));
            String e = "";
            while ((e = br.readLine()) != null) {   //finding professor whose expertise more on that topic

                String exp[] = e.split(",");
                if(t.equals(exp[0]) & course.contains(c) & prof.contains(exp[1]))
                {
                    if(le<Integer.parseInt(exp[2]))
                    {
                        le = Integer.parseInt(exp[2]);
                        expprof=exp[1];
                        score2=Integer.parseInt(exp[2]);
                    }
                }
                if(!prof.contains(exp[1]))
                    coursetopic.remove(key);

            }
            if(expprof!="") {
                course.remove(c);
                prof.remove(expprof);
                coursetopic.remove(key);
                System.out.println(c +"         "+expprof+"         "+(score+(score2*20))/2+"%");
            }
            br.close();
        }catch (Exception e)
        {

        }
    }


}
