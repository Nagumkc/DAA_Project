import com.sun.javafx.image.IntPixelGetter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
   static HashMap<String,Integer>coursetopic=new HashMap<String,Integer>();
   static List<String> course=new ArrayList<String>();
   static List<String> prof=new ArrayList<String>();
    public static void main(String[] args) {
        System.out.println("CourseId"+" "+"professorId"+" "+"Matching Score");
        readdata();
        while(!course.isEmpty())
        {
            int max=0;
            String key="";
            for(Map.Entry< String,Integer> entry : coursetopic.entrySet())
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
      /*  try {
            BufferedReader br = new BufferedReader(new FileReader("data/course"));
            String s="";
            while ((s=br.readLine())!=null)
            {
               coursetopic(s);
               course.add(s);
            }
        }
        catch (Exception e)
        {

        }
    }
    static void  coursetopic(String c)
    {
        String sp[]=c.split(",");
        int topicid=0;
        int percentage=0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/CourseTopic"));
            String ct="";
            while ((ct=br.readLine())!=null)
            {

          String csp[]=ct.split(",");

          if(Integer.parseInt(sp[0])==Integer.parseInt(csp[0]))
          {
             if(percentage<Integer.parseInt(csp[2])) {
                 percentage=Integer.parseInt(csp[2]);
                 topicid = Integer.parseInt(csp[1]);
             }

          }

            }
        }
        catch (Exception e)
        {

        }
        if(topicid!=0) {
        //    System.out.println(topicid);
            expert(topicid);
        }
    }
    static void expert(int topicid)
    {
        int le=0;
        int pid=0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/Expertise"));
            String e = "";
            while ((e = br.readLine()) != null) {

                String exp[] = e.split(",");
                if(topicid==Integer.parseInt(exp[0]))
                {
                    if(le<Integer.parseInt(exp[2]))
                    {
                        le=Integer.parseInt(exp[2]);
                        pid=Integer.parseInt(exp[1]);
                    }

                }
                else
                {
                    topic.add(topicid);
                }

            }
            if(pid!=0)
             System.out.println("professor"+pid);
        }catch (Exception e)
        {

        }*/
    }
    public static void readdata()
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
            while ((e = br.readLine()) != null) {

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
