import java.io.IOException;
import java.util.Scanner;

public class Calcul {
    public static String calc(String inp) throws IOException {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
//        Scanner scn = new Scanner(System.in);
        String exp = inp;
        //Определяем арифметическое действие:
        int actionIndex=-1;
        int r = 0;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(",") || exp.contains(".")){
                throw new IOException("Пользователь ввел не целое число");
            }

            else if(exp.contains(actions[i])){
                actionIndex = i;
                r = r+1;
            }
        }
        //Если не нашли арифметического действия, завершаем программу
        if(actionIndex==-1){
            throw new IOException("Пользователь ввел не корректное выражение");
        }
        if(r >1){
            throw new IOException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        //Делим строчку по найденному арифметическому знаку
        String[]data = exp.split(regexActions[actionIndex]);
        data[0] = data[0].trim();
        data[1] = data[1].trim();
        if (data.length > 2) {
            throw new IOException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if(converter.isRoman(data[0]) == converter.isRoman(data[1])){
            int a,b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if(isRoman){
                //если римские, то конвертируем их в арабские
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);
                if (a > 10 || b > 10) {
                    throw new IOException("Пользователь ввел число больше 10");
                } if (a < b && actionIndex == 1){
                    throw new IOException("В римской системе нет отприцательных чисел");
                } if (a == b && actionIndex == 1){
                    throw new IOException("В римской системе нет 0");
                }

            }else{
                //если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
                if (a > 10 || b > 10){
                    throw new IOException("Пользователь ввел число больше 10");
                }
            }
            //выполняем с числами арифметическое действие
            int result;
            switch (actions[actionIndex]){
                case "+":
                    result = a+b;
                    break;
                case "-":
                    result = a-b;
                    break;
                case "*":
                    result = a*b;
                    break;
                default:
                    result = a/b;
                    break;
            }
            if(isRoman){
                //если числа были римские, возвращаем результат в римском числе
                String res = converter.intToRoman(result);
                return res;
            }
            else{
                //если числа были арабские, возвращаем результат в арабском числе
                String res = Integer.toString(result);
                return res;
            }
        }else{
            throw new IOException("Числа должны быть в одном формате");
        }

    }
}
