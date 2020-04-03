package eg.edu.alexu.csd.datastructure.stack.cs23.Classes;
import java.util.Scanner;

public class ApplicationUI {
    public static void main(String[] args){
        Stack s = new Stack();
        while(true){
            Scanner in = new Scanner(System.in);

            System.out.print("\nStack Elements Are:\n");
            for(int i=s.size()-1; i>=0; i--){
                System.out.print( s.arr.get(i) + " ");
            }
            System.out.print("\n");

            System.out.print("Choose an option:\n" +
                    "1: Push\n" +
                    "2: Pop\n" +
                    "3: Peek\n" +
                    "4: Get size\n" +
                    "5: Check if empty\n"+
                    "6: End program\n");

            char input = in.next().charAt(0);

            switch (input){

                case '1':
                    System.out.println("Enter a number to push ..\n");
                    int num = in.nextInt();
                    s.push(num);

                    break;

                case '2':
                    if(s.isEmpty()){
                        System.out.print("You can't pop from an empty stack\n");
                        break;
                    }
                    System.out.print("Top ELement is " + s.pop() + "\n");
                    System.out.print("Top Element removed\n");
                    break;
                case '3':
                    if(s.isEmpty()){
                        System.out.print("You can't peak from an empty stack\n");
                        break;
                    }
                    System.out.print("Top ELement is " + s.peek() + "\n");
                    break;

                case '4':
                    System.out.println( s.size() );
                    break;

                case '5':
                    if(s.isEmpty())
                        System.out.println("The stack is empty. Maybe Try pushing a number");

                    else
                        System.out.println("The Stack has elements");
                    break;

                case '6':
                    System.out.print("Progr2am terminated\n");
                    return;


                default:
                    System.out.println("Enter a proper option");
                    break;
            }


        }
    }
}
