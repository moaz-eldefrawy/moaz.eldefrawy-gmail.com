package eg.edu.alexu.csd.datastructure.stack.cs23.Classes;
import eg.edu.alexu.csd.datastructure.stack.cs23.Interfaces.IExpressionEvaluator;

import java.rmi.server.ExportException;
import java.util.Scanner;

public class InfixToPostfixUIApplication {

    public static void main(String args[]){
        ExpressionEvaluator expEval = new ExpressionEvaluator();

        Scanner in = new Scanner(System.in);
        InfixToPostfixUIApplication app = new InfixToPostfixUIApplication();
        boolean firstRun=true;
        while(true){
            System.out.println("Enter an expression or 0 for exist:");
            if(firstRun) {
                firstRun = false;
                System.out.println("Try costPerBox * numberOfBoxes * 2 ");
            }
            String expression = in.nextLine();
            if(expression.equals("0")) break;


            try{
                expression = app.getVariablesValues(expression);
                System.out.println("Expression After entering values:\n" +
                        expression + "\n" );

                String postfixExp = expEval.infixToPostfix(expression);
                System.out.println("Postfix Expression:\n" +
                        postfixExp + "\n" );
                System.out.println( "Answer = " + expEval.evaluate(postfixExp) );
            } catch (Exception e){
                System.out.println("Enter A valid input\n");
            }
            System.out.print("\n\n");
        }
    }
    private String separateOperators(String expression){
        ExpressionEvaluator expEval = new ExpressionEvaluator();
        String newExpression = new String("");

        for(int i=0; i<expression.length(); i++){
            char c = expression.charAt(i);
            if( expEval.isArithmeticOperator(c) || c == ')' || c =='(' ){
                newExpression += " " + c + " ";
            }
            else
                newExpression +=c;
        }

        return newExpression;
    }

    private String getVariablesValues(String expression){
        ExpressionEvaluator expEval = new ExpressionEvaluator();
        expression = this.separateOperators(expression);
        String[] arr = expression.split(" ");
        Scanner in = new Scanner(System.in);

        for(int i=0; i<arr.length; i++){
           // System.out.print(arr[i] + " - " + i);
            if( arr[i].length()>0 && Character.isAlphabetic(arr[i].charAt(0)) ) {
                System.out.print("Enter the value for '" + arr[i] + "'\n");
                String input = in.nextLine();
                while(!input.matches("-?\\d+(\\.\\d+)?")){
                    System.out.println("Enter A valid number, please");
                    input = in.nextLine();

                }
                String variableName = arr[i];
                for(int j=0; j<arr.length; j++){
                    if(arr[j].equals(variableName))
                        arr[j] = input;
                }

            }
        }

        return String.join(" ", arr);
    }
}
