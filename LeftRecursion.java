import java.util.ArrayList;
import java.util.Scanner;

public class LeftRecursion {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of productions");
        int n=sc.nextInt();

        System.out.println("Enter the productions in the following format E->E|A ");
        String[] production=new String[n];
        for(int i=0;i<n;i++){
            production[i]=sc.next();
        }


        System.out.println("\nThe productions are\n");
        for(String s : production){
            System.out.println(s);
        }


        for(String s : production){
            String sub=s.substring(3);
            String[] ar=sub.split("\\|");
            boolean ans=checkLeftRecursion(s.charAt(0),ar[0]);
            if(ans) {
                System.out.println("Left Recursion exists");
                ArrayList<String> newProd=removeLeftRecursion(s.charAt(0),ar);
                createNewDashProduction(s.charAt(0),ar,newProd);
                System.out.println(newProd);
            }else{
                System.out.println("Left Recursion does not exists");
            }
        }
    }

    private static void createNewDashProduction(char c, String[] ar, ArrayList<String> newProd) {
        String prod="";
        for(String s : ar){
            if(s.charAt(0)==c){
              prod=c+"'->"+s.substring(1)+c+"'";
              newProd.add(prod);
            }
        }
    }

    private static ArrayList<String> removeLeftRecursion(char charAt, String[] ar) {
        ArrayList<String> noRecursive=new ArrayList<>();
        ArrayList<String> noRecursiveWithDash=new ArrayList<>();
        ArrayList<String> newProduction=new ArrayList<>();
        for(String s : ar){
            if(s.charAt(0)!=charAt)
                noRecursive.add(s);
        }

        for(String s : noRecursive){
            s=s+charAt+"'";
            noRecursiveWithDash.add(s);
        }

        for(String s : noRecursiveWithDash){
            newProduction.add(charAt+"->"+s);
        }

        return newProduction;

    }

    private static boolean checkLeftRecursion(char ch, String ar) {
        return ch == ar.charAt(0);
    }
}
/*
Enter the number of productions
3
Enter the productions in the following format E->E|A 
E->E+T|T
T->T*F|F
F->(E)|id

The productions are

E->E+T|T
T->T*F|F
F->(E)|id
Left Recursion exists
[E->TE', E'->+TE']
Left Recursion exists
[T->FT', T'->*FT']
Left Recursion does not exists
*/
