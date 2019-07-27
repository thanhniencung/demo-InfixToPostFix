import java.io.IOException;
import java.util.function.IntFunction;

public class Program {

    public static void main(String[] args) {
        InfixToPostFix infixToPostFix = new InfixToPostFix();
        String[] postFixString = infixToPostFix.inFixToPostFix("1+2+3+4+5+6*100");
        System.out.println(PostFix.calculator(postFixString));
    }
}
