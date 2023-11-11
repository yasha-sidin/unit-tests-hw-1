package task1;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    private static boolean willAdditionOverflow(int left, int right) {
        if (right < 0 && right != Integer.MIN_VALUE) {
            return willSubtractionOverflow(left, -right);
        } else {
            return (~(left ^ right) & (left ^ (left + right))) < 0;
        }
    }

    private static boolean willSubtractionOverflow(int left, int right) {
        if (right < 0) {
            return willAdditionOverflow(left, -right);
        } else {
            return ((left ^ right) & (left ^ (left - right))) < 0;
        }
    }

    public static int calculation(int firstOperand, int secondOperand, char operator) {
        int result;

        switch (operator) {
            case '+':
                if (willAdditionOverflow(firstOperand, secondOperand)) throw new RuntimeException("Result of addition is too large.");
                result = firstOperand + secondOperand;
                break;
            case '-':
                if (willSubtractionOverflow(firstOperand, secondOperand)) throw new RuntimeException("Result of subtraction is too large.");
                result = firstOperand - secondOperand;
                break;
            case '*':
                result = firstOperand * secondOperand;
                break;
            case '/':
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                    break;
                } else {
                    throw new ArithmeticException("Division by zero is not possible");
                }
            default:
                throw new IllegalStateException("Unexpected value operator: " + operator);
        }
        return result;
    }

    // HW1.1: Придумайте и опишите (можно в псевдокоде) функцию извлечения корня и
    // необходимые проверки для него используя граничные случаи
    public static double squareRootExtraction(double num) {
        //  0
        //  Отрицательные числа
        //  Дробные значения корней
        //  Целые
        if (num == 0.0) return 0.0;
        if(num < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of a negative number");
        }
        double copyNum = num;
        double discharge = 1.0;
        while (copyNum > 10) {
            copyNum = copyNum / 10;
            discharge++;
        }
        double partOfFormula = 0;
        switch ((int) (discharge % 2)) {
            case 0:
                partOfFormula = 2.0;
                break;
            case 1:
                partOfFormula = 1.0;
                break;
        }
        double n = (discharge - partOfFormula) / 2;
        double result = discharge * Math.pow(10, n);
        for(int i = 0; i < 6; i++) {
            result = 0.5 * (result + (num / result));
        }
        return new BigDecimal(result).setScale(10, RoundingMode.DOWN).doubleValue();
    }

    // Нужно написать в калькуляторе метод вычисления суммы покупки со скидкой и проверить его, используя AssertJ
    // Примерная сигнатура и тело метода:
    public static double calculatingDiscount(double purchaseAmount, int discountAmount) {
        if (discountAmount < 0 || discountAmount > 100) throw new ArithmeticException("Discount must be less then 100 and over then 0 or equal them.");
        if (purchaseAmount < 0.0) throw new ArithmeticException("purchaseAmount must be over than 0.");
        // purchaseAmount - сумма покупки
        // discountAmount - размер скидки
        return purchaseAmount - ((double) discountAmount / 100) * purchaseAmount; // Метод должен возвращать сумму покупки со скидкой
    }
}
