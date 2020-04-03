package eg.edu.alexu.csd.datastructure.stack.cs23.Classes;

import eg.edu.alexu.csd.datastructure.stack.cs23.Interfaces.IExpressionEvaluator;


/** 
 * Made Assumptions:
 *
 * 1- Any ALphanumeric expression is valid. According to this source,
 * https://www.reference.com/world-view/examples-alphanumeric-characters-86117e89a44d1322
 * "Alphanumeric characters can also be used in any order.
 * Examples are bz3@llt, RdYw4!, and 982hfgq&667m."
 * However, It doesn't make any sense to have $ss$ as a an entry
 * It makes more sense for the use an equation like this:
 * currentBudget = ( preCash + preCash*interest ) = preCash * ( 1 + interest )
 *
 * 2- ++--++a is valid. They are considered here unary operators.
 * 3- +(+a) is valid. You can use an expression with one number
 *
 * 4- IN the UI asd123 is allowed while 123asd Is not.
 *
 *
 */

public class ExpressionEvaluator implements IExpressionEvaluator {

    /**
     * compares operators orders
     * @param higherOrder Operator
     * @param lowerOrder Operator
     * @return True, if and only if the first operator had a strictly higher order
     */

    public boolean isHigherOrderOperator(char higherOrder, char lowerOrder){
        if (higherOrder == '*' || higherOrder == '/') {
            if(lowerOrder == '+' || lowerOrder == '-')
                return true;

            else
                return false;
        }

        else
            return false;
    }

    public boolean isArithmeticOperator(char c){
        if(c == '+' || c == '*' || c == '-' || c == '/')
            return true;

        return false;
    }

    public boolean isOperand(char c){
        if((c >= '0' && c <= '9') || Character.isAlphabetic(c)){
            return true;
        }
        return false;
    }

    /**
     * Handle negative numbers in expressions. -b -> (0-b)
     * @param expression
     * @return modified expression
     */
    public String addDummyZeroToNegativeNumbers(String expression){
        String newExpression = new String("(");
        for(int i=1; i<expression.length(); i++){
            char c = expression.charAt(i);
            if(c == '-'){


                char preC = expression.charAt(i-1);
                if(preC == '('){
                     newExpression += "0-";
                }

                else if(isArithmeticOperator(preC)){
                    newExpression += "(0-";

                    while( isOperand(expression.charAt(i+1)) ){
                        newExpression += expression.charAt(i+1);
                        i++;
                    }
                    newExpression += ")";

                }

                else { // normal negative sign
                    newExpression += c;
                }

            }

            else if(c == '+' && (expression.charAt(i-1) == '(' ||
                    isArithmeticOperator(expression.charAt(i-1))) ) {

                continue;
            }
            else { // normal Character
                newExpression += c;
            }
        }
        return newExpression;
    }

    /**
     * remove adjacent unary operators "+++-+ -> "-"
     * @param expression
     * @return modified Expression
     */
    public String removeExtraUnaryOperators(String expression) {
        for (int i = 1; i < expression.length(); i++) {
            char c = expression.charAt(i);
            char cPre = expression.charAt(i-1);
            if(c == '+' && cPre == '+' ) {
                expression = expression.substring(0, i) + expression.substring(i+1);
                i--;
            }

            else if(c == '+' && cPre == '-' ) {
                expression = expression.substring(0, i) + expression.substring(i+1);
                i--;
            }

            else if(c == '-' && cPre == '-' ) {
                expression = expression.substring(0, i-1)+ "+" + expression.substring(i + 1);
                i--;
            }

            else if(c == '-' && cPre == '+' ) {
                expression = expression.substring(0, i-1) + expression.substring(i);
                i--;
            }

            else if(isOperand(c) && cPre == ')' ){ // )a
                throw new RuntimeException("Enter a valid postfix expression");
            }

            else if(isOperand(cPre) && c == '(' ){ // a(
                throw new RuntimeException("Enter a valid postfix expression");
            }

        }
        return  expression;
    }

    /**
     *
     * @param expression
     * @return expression with no extra spaces
     */
    public String removeSpaces(String expression){
        String newExpression = new String();
        for(int i=0; i<expression.length(); i++){
            if(expression.charAt(i) != ' ')
                newExpression += expression.charAt(i);
        }
        return newExpression;
    }

    /**
     * Takes a symbolic/numeric infix expression as input and converts it to
     * postfix notation. There is no assumption on spaces between terms or the
     * length of the term (e.g., two digits symbolic or numeric term)
     *
     * @param expression
     * infix expression
     * @return postfix expression
     */

    public String infixToPostfix(String expression) {
        expression = '(' + expression + ')';

        expression = removeSpaces(expression);
        expression = removeExtraUnaryOperators(expression);
        expression = addDummyZeroToNegativeNumbers(expression);


        // Design Decision: readability over performance, we could integrate the previous functions
        // into one single function called modifyExpression

        String postFixExpression = new String("");
        Stack stack = new Stack();

        for(int i=0; i<expression.length(); i++){

            char c = expression.charAt(i);
            if( isOperand(c) ){
                postFixExpression += c;
                while( isOperand(expression.charAt(i+1)) ){
                    postFixExpression += expression.charAt(i+1);
                    i++;
                }
                postFixExpression += ' ';
            }

            else if( isArithmeticOperator(c) ){
                char c2 = (char)stack.peek();

                while( !isHigherOrderOperator(c,c2) && isArithmeticOperator(c2) ){
                    postFixExpression += Character.toString(c2)  + ' ';
                    stack.pop();
                    c2 = (char) stack.peek();
                }
                stack.push(c);
            }

            else if( c == '(' ){
                stack.push('(');
            }

            else if( c == ')'){
                char c2 = (char)stack.pop();
                while(c2 != '(') {
                    postFixExpression += Character.toString(c2) + ' ';
                    c2 = (char) stack.pop();
                }
            }

            else
                throw new RuntimeException("Use Proper Format");
        }

        return  postFixExpression.trim();
    }

    /**
     * Evaluate a postfix numeric expression, with a single space separator
     *
     * @param expression
     * postfix expression
     * @return the expression evaluated value
     */
    public int evaluate(String expression) {
        String evalExpression = new String(expression);
        double value;
        String[] arr = expression.split(" ", 0);
        Stack stack = new Stack();

        for(String i : arr) {
            if(i.equals(" ") || i.equals(""))
                continue;

            if( isArithmeticOperator(i.charAt(0)) ){
                Double val1 =  (Double)stack.pop();
                Double val2 =  (Double)stack.pop();

                switch (i.charAt(0)){
                    case '+': stack.push(val2 + val1); break;
                    case '-': stack.push(val2 - val1); break;
                    case '*': stack.push(val2 * val1); break;
                    case '/':
                        if(val1.intValue() == 0)
                            throw new RuntimeException("Can't divide by 0");
                        stack.push(val2 / val1); break;
                }
            }

            else{
                stack.push( Double.parseDouble(i) );
            }
        }
        int result = ((Double)stack.pop()).intValue();
        if(!stack.isEmpty())
            throw new RuntimeException("Not a valid PostFix");
        return result;
    }
}
