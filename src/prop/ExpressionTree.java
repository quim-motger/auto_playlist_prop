package prop;

import prop.domain.*;

import java.util.Objects;
import java.util.Stack;

/**
 * Binary expression tree
 * @author oscar.manas
 * @author gerard.casas.saez
 * @author joaquim.motger
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
     * Evaluates the expression
     * @return  the result of the evaluation
     */
    public Relation evaluate() {
        return evaluateRec(root);
    }

    /**
     * Parses an arithmetic expression into an expression tree
     * @param exp   the expression to parse
     *              it should be something like "(((35)-((3)*((3)+(2))))/(4))"
     */
    public void parse(String exp, String relSimp) {
        root = parseRec(exp,relSimp);
    }

    private Relation evaluateRec(ExpNode node) {
        if (node.left == null && node.right == null) {
            ExpSRelNode leaf = (ExpSRelNode)node;
            return leaf.simpleRelation;
        } else if (node.right == null && Objects.equals(((ExpOpNode) node).op, '!')){
            return new NOT(evaluateRec(node.left));
        }
        else {
            Relation leftExp = evaluateRec(node.left);
            Relation rightExp = evaluateRec(node.right);
            ExpOpNode internal = (ExpOpNode)node;

            switch (internal.op) {
                case '&':
                    return new AND(leftExp,rightExp);
                case '|':
                    return  new OR(leftExp,rightExp);
                default:
                    throw new RuntimeException("invalid operand '" + internal.op + "'");
            }
        }
    }

    private ExpNode parseRec(String exp, String relSim) {
        ExpNode node;
        
        String[] rels = relSim.split("\n");

        int m = findRightParen(exp,1);
        String leftExp = exp.substring(1,m+1);

        if (m == exp.length()-1) {
            double d = Double.parseDouble(exp.substring(1,exp.length()-1));
            String[] relParts = rels[(int)d].split(" ");
            SimpleRelation sRel = new SimpleRelation(relParts[0],relParts[1],relParts[2]);
            node = new ExpSRelNode(sRel);
            return node;
        }
        else {
            int n = findLeftParen(exp, exp.length() - 2);
            String rightExp = exp.substring(n, exp.length()-1);

            ExpNode nodeLeft = parseRec(leftExp,relSim);
            ExpNode nodeRight = parseRec(rightExp,relSim);

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
     * @author oscar.manas
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
     * @author oscar.manas
     */
    private class ExpSRelNode extends ExpNode {

        private SimpleRelation simpleRelation;

        public ExpSRelNode(SimpleRelation sr) {
            simpleRelation = sr;
            super.left = null;
            super.right = null;
        }

        public void write() {
            System.out.println(simpleRelation.toString() + " ");
        }
    }

    /**
     * Expression tree operator node
     * @author oscar.manas
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

