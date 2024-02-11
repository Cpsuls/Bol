package sample;


import java.util.Scanner;
import sample.dataconnect;
import sample.Handler;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class main_code {
    public static void main(String[] args) {
        System.out.println("Это калькулятор группы №2");
        System.out.println("Консольное меню:");
        System.out.println("1. Вывести все таблицы из MySQL.\n" +
                "2. Создать таблицу в MySQL.\n" +
                "3. Сложение чисел, результат сохранить в MySQL с последующим выводом в консоль.\n" +
                "4. Вычитание чисел, результат сохранить в MySQL с последующим выводом в консоль.\n" +
                "5. Умножение чисел, результат сохранить в MySQL с последующим выводом в консоль.\n" +
                "6. Деление чисел, результат сохранить в MySQL с последующим выводом в консоль.\n" +
                "7. Деление чисел по модулю (остаток), результат сохранить в MySQL с последующим выводом в консоль.\n" +
                "8. Возведение числа в модуль, результат сохранить в MySQL с последующим выводом в консоль.\n" +
                "9. Возведение числа в степень, результат сохранить в MySQL с последующим выводом в консоль.\n" +
                "10. Сохранить все данные (вышеполученные результаты) из MySQL в Excel и вывести на экран.\n");
        double[] results = new double[100];
        int i = 0;
        Scanner scan = new Scanner(System.in);
        int command = 0;
        String input = "";
        while (true) {
            command = scan.nextInt();
            switch (command){
                case 2:
                    Handler handler = new Handler();
                    System.out.println("Введите название новой таблицы: \n");
                    String name_table = scan.nextLine().trim();
                    handler.createNew(name_table);
                    break;
                default:
                    scan.nextLine();
                    input = scan.nextLine().trim();
            }
            try {
                double result = calculate(command, input);
                System.out.println("Результатик: " + result + " \n");
                results[i] = result;
                i += 1;
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формат чисел");
            } catch (IllegalArgumentException e) {
                System.out.println("Неизвестная или невозможная при данных значениях команда");
            }

        }
    }
    public static double calculate(int command, String input) {
        String[] numbers_string = input.split(" ");
        if (((command == 7) || ((command == 8)) && (numbers_string.length != 1))){
            throw new IllegalArgumentException();
        }
        double[] numbers = new double[numbers_string.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Double.parseDouble(numbers_string[i]);
        }

        double result = numbers[0];

        if (command == 8){
            result = Math.abs(result);
        }

        for (int i = 1; i < numbers.length; i++) {
            switch (command) {
                case 3:
                    result += numbers[i];
                    break;
                case 4:
                    result -= numbers[i];
                    break;
                case 5:
                    result *= numbers[i];
                    break;
                case 6:
                    result /= numbers[i];
                    break;
                case 7:
                    result = result % numbers[i];
                    break;
                case 9:
                    result = Math.pow(result, numbers[i]);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        String command_string = String.valueOf(command);
        String result_string = String.valueOf(result);
        Handler handler = new Handler();
        handler.enterRes(input, command_string, result_string);

        return (double) result;
    }
}