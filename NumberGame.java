import java.util.Scanner;
public class NumberGamme
{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int chance=8;
        int finals=0;
        boolean playAgain=true;
        System.out.println("welcome to the tiny game");
        System.out.println("hey you have about  "+chance+" chance to win the game");
        while (playAgain){
            int rand=getrandN(1,100);
            boolean guess=false;
            for (int i=0;i<chance;i++){
                System.out.println("chance "+(i+1)+" enter your choice");
                int user=sc.nextInt();
                if(user==rand){
                    guess=true;
                    finals+=1;
                    System.out.println("congrats you won");
                    break;
                }
                else if(user>rand){
                System.out.println("entered number is greater");
                }
                else{
                    System.out.println("entered number is lesser");
                }
            }
            if(guess=false){
                System.out.println("sorry friend you lost the chance the number is "+rand);
            }
                System.out.println("do you want to play again (y/n)");
                String pA=sc.next();
                playAgain=pA.equalsIgnoreCase("y");
            }
                System.out.println("that's it friend,hope ypou enjoyed it");
                System.out.println("your final score is "+finals);
            }
            public static int getrandN(int min,int max){
            return (int)(Math.random()*(max-min+1)+min);
        }
    }