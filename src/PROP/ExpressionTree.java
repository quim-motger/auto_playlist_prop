package PROP;

import java.util.Stack;

/**
 * Binary expression tree
 * @author Oscar Mañas Sanchez
 */
public class ExpressionTree {

    private ExpNode root;

    /**
     * Constructor
     */
    public ExpressionTree() {
        root = null;
    }

    /**
     * Writes the expression tree in preOrder
     */
    public void write() {
        writeRec(root);
        System.out.print('\n');
    }

    /**
     * Evaluates the expression
     * @return  the result of the evaluation
     */
    public double evaluate() {
        return evaluateRec(root);
    }

    /**
     * Parses an arithmetic expression into an expression tree
     * @param exp   the expression to parse
     *              it should be something like "(((35)-((3)*((3)+(2))))/(4))"
     */
    public void parse(String exp) {
        root = parseRec(exp);
    }

    private void writeRec(ExpNode node) {
        if (node == null) {
            System.out.print("0 ");
        }
        else {
            // preOrder
            node.write();
            writeRec(node.left);
            writeRec(node.right);
        }
    }

    private double evaluateRec(ExpNode node) {
        if (node.left == null && node.right == null) {
            ExpNumNode leaf = (ExpNumNode)node;
            return leaf.number;
        }
        else {
            double leftExp = evaluateRec(node.left);
            double rightExp = evaluateRec(node.right);
            ExpOpNode internal = (ExpOpNode)node;

            switch (internal.op) {
                case '+':
                    return leftExp + rightExp;
                case '-':
                    return  leftExp - rightExp;
                case '*':
                    return leftExp * rightExp;
                case '/':
                    return leftExp / rightExp;
                default:
                    throw new RuntimeException("invalid operand '" + internal.op + "'");
            }
        }
    }

    private ExpNode parseRec(String exp) {
        ExpNode node;

        System.out.println(exp);

        int m = findRightParen(exp,1);
        String leftExp = exp.substring(1,m+1);

        if (m == exp.length()-1) {
            double number = Double.parseDouble(exp.substring(1,exp.length()-1));
            node = new ExpNumNode(number);
            return node;
        }
        else {
            int n = findLeftParen(exp, exp.length() - 2);
            String rightExp = exp.substring(n, exp.length()-1);

            ExpNode nodeLeft = parseRec(leftExp);
            ExpNode nodeRight = parseRec(rightExp);

            node = new ExpOpNode(exp.charAt(m+1),nodeLeft,nodeRight);
            return node;
        }
    }

    private int findRightParen(String exp, int left) {
        Stack<Character> stack = new Stack<Character>();
        stack.push(exp.charAt(left));
        for (int i = left+1; i < exp.length(); ++i) {
            char ch = exp.charAt(i);
            if (ch == '(') {
                stack.push (ch);
            }
            else if (ch == ')') {
                stack.pop ();
                if (stack.isEmpty()) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int findLeftParen(String exp, int right) {
        Stack<Character> stack = new Stack<Character>();
        stack.push(exp.charAt(right));
        for (int i = right-1; i >= 0; --i) {
            char ch = exp.charAt(i);
            if (ch == ')') {
                stack.push(ch);
            }
            else if (ch == '(') {
                stack.pop();
                if (stack.isEmpty()) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Expression tree node
     * @author Oscar Mañas Sanchez
     */
    private abstract class ExpNode {

        private ExpNode left;
        private ExpNode right;

        public ExpNode() {
        }

        public abstract void write();

    }

    /**
     * Expression tree number node
     * @author Oscar Mañas Sanchez
     */
    private class ExpNumNode extends ExpNode {

        private double number;

        public ExpNumNode(double number) {
            this.number = number;
            super.left = null;
            super.right = null;
        }

        public void write() {
            System.out.print(String.valueOf(number) + " ");
        }
    }

    /**
     * Expression tree operator node
     * @author Oscar Mañas Sanchez
     */
    private class ExpOpNode extends ExpNode {

        private char op;

        public ExpOpNode(char op, ExpNode left, ExpNode right) {
            this.op = op;
            super.left = left;
            super.right = right;
        }

        public void write() {
            System.out.print(op + " ");
        }
    }
}

