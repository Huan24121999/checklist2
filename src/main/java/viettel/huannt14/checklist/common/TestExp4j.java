package viettel.huannt14.checklist.common;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class TestExp4j {
    public static void main(String[] args) {
        Expression e = new ExpressionBuilder("3 * sin(y) - 2 / (x - 2)")
                .variables("x", "y")
                .build()
                .setVariable("x", 2.3)
                .setVariable("y", 3.14);
        double result = e.evaluate();
        System.out.println(result);

        Expression e2=new ExpressionBuilder("x<=80")
                .variables("x")
                .build()
                .setVariable("x",20);
        double result2=e.evaluate();
        System.out.println(result2);
    }
}
