import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //2+3
        //X+V=XV
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        //Определяем арифметическое действие:
        int actionIndex=-1;
        int r = 0;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(",") || exp.contains(".")){
                System.out.println("Пользователь ввел не целое число");
                return;
            }
            else if(exp.contains(actions[i])){
                actionIndex = i;
                r = r+1;
            }
        }
        //Если не нашли арифметического действия, завершаем программу
        if(actionIndex==-1){
            System.out.println("Некорректное выражение");
            return;
        }
        if(r >1){
            System.out.println("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            return;
        }
        //Делим строчку по найденному арифметическому знаку
        String[]data = exp.split(regexActions[actionIndex]);
        data[0] = data[0].trim();
        data[1] = data[1].trim();
        if (data.length > 2) {
            System.out.println("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            return;
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
                    System.out.println("Пользователь ввел число больше 10");
                    return;
                } if (a < b && actionIndex == 1){
                    System.out.println("В римской системе нет отприцательных чисел");
                    return;
                } if (a == b && actionIndex == 1){
                    System.out.println("В римской системе нет 0");
                    return;
                }

            }else{
                //если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
                if (a > 10 || b > 10){
                    System.out.println("Пользователь ввел число больше 10");
                    return;
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
                System.out.println(converter.intToRoman(result));
            }
            else{
                //если числа были арабские, возвращаем результат в арабском числе
                System.out.println(result);
            }
        }else{
            System.out.println("Числа должны быть в одном формате");
        }


    }
}