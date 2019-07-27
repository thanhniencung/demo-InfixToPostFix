import java.util.Arrays;
import java.util.Stack;

public class InfixToPostFix {

    public int priority(char c) {        // thiet lap thu tu uu tien
        if (c == '+' || c == '-') return 1;
        else if (c == '*' || c == '/') return 2;
        else return 0;
    }

    public boolean isOperator(char c) {  // kiem tra xem co phai toan tu
        char operator[] = {'+', '-', '*', '/', ')', '('};
        Arrays.sort(operator);
        if (Arrays.binarySearch(operator, c) > -1)
            return true;
        else return false;
    }

    public String[] processString(String sMath) { // xu ly bieu thuc nhap vao thanh cac phan tu
        String s1 = "", elementMath[] = null;
        sMath = sMath.trim();
        sMath = sMath.replaceAll("\\s+", " "); //    chuan hoa sMath
        for (int i = 0; i < sMath.length(); i++) {
            char c = sMath.charAt(i);//sMath.substring(i,1);
            if (!isOperator(c))
                s1 = s1 + c;
            else s1 = s1 + " " + c + " ";
        }
        s1 = s1.trim();
        s1 = s1.replaceAll("\\s+", " "); //  chuan hoa s1
        elementMath = s1.split(" "); //tach s1 thanh cac phan tu
        //System.out.println(s1);
        return elementMath;
    }

    public String[] postfix(String[] elementMath) {
        String s1 = "", E[];
        Stack<String> S = new Stack<String>();
        for (int i = 0; i < elementMath.length; i++) {    // duyet cac phan tu
            char c = elementMath[i].charAt(0);  // c la ky tu dau tien cua moi phan tu

            if (!isOperator(c))         // neu c khong la toan tu
                s1 = s1 + " " + elementMath[i];     // xuat elem vao s1
            else {                       // c la toan tu
                if (c == '(') S.push(elementMath[i]);   // c la "(" -> day phan tu vao Stack
                else {
                    if (c == ')') {          // c la ")"
                        char c1;        //duyet lai cac phan tu trong Stack
                        do {
                            c1 = S.peek().charAt(0);    // c1 la ky tu dau tien cua phan tu
                            if (c1 != '(') s1 = s1 + " " + S.peek();    // trong khi c1 != "("
                            S.pop();
                        } while (c1 != '(');
                    } else {
                        while (!S.isEmpty() && priority(S.peek().charAt(0)) >= priority(c)) {
                            // Stack khong rong va trong khi phan tu trong Stack co do uu tien >= phan tu hien tai
                            s1 = s1 + " " + S.peek();   // xuat phan tu trong Stack ra s1
                            S.pop();
                        }
                        S.push(elementMath[i]); //  dua phan tu hien tai vao Stack
                    }
                }
            }
        }
        while (!S.isEmpty()) {   // Neu Stack con phan tu thi day het vao s1
            s1 = s1 + " " + S.peek();
            S.pop();
        }

        return s1.split(" ");  //  tach s1 thanh cac phan tu
    }

    public String[] inFixToPostFix(String expression) {
        String[] elementMath = processString(expression);     //  tach bieu thuc thanh cac phan tu
        return postfix(elementMath);
    }
}