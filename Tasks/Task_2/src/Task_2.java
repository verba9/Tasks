import java.util.Arrays;
import java.util.Collections;

public class Task_2 {
    public static void main(String[] args) {
        System.out.println(repeat("mice", 5));
        System.out.println(repeat("hello", 3));
        System.out.println(repeat("stop", 1));

        System.out.println("---");
        System.out.println(differenceMaxMin(new int[]{10, 4, 1, 4, -10, -50, 32, 21}));


        System.out.println("---");
        System.out.println(isAvgWhole(new int[]{1,3}));
        System.out.println(isAvgWhole(new int[]{1,2,3,4}));
        System.out.println(isAvgWhole(new int[]{1,5,6}));

        System.out.println("---");
        System.out.println(cumulativeSum(new int[]{1,2,3}));
        System.out.println(cumulativeSum(new int[]{1,-2,3}));
        System.out.println(cumulativeSum(new int[]{3,3,-2,408,3,3}));

        System.out.println("---");
        System.out.println(getDecimalPlaces("43.20"));
        System.out.println(getDecimalPlaces("400"));
        System.out.println(getDecimalPlaces("3.1"));

        System.out.println("---");
        System.out.println(fibonacci(3));
        System.out.println(fibonacci(7));
        System.out.println(fibonacci(12));

        System.out.println("---");
        System.out.println(isValid("59001"));
        System.out.println(isValid("9393939"));
        System.out.println(isValid("590 01"));
        System.out.println(isValid("590a01"));

        System.out.println("---");
        System.out.println(isStrangePair("ratio","orator"));
        System.out.println(isStrangePair("bush","hubris"));
        System.out.println(isStrangePair("",""));

        System.out.println("---");
        System.out.println(isPrefix("automation","auto-"));
        System.out.println(isSuffix("arachnophobia","-phobia"));
        System.out.println(isPrefix("retrospect","sub-"));
        System.out.println(isSuffix("vocation","-logy"));

        System.out.println("---");
        System.out.println(boxSeq(0));
        System.out.println(boxSeq(1));
        System.out.println(boxSeq(2));
        System.out.println(boxSeq(6));
        System.out.println(boxSeq(7));



    }
    // Метод, который повторяет каждый символ в строке n раз.
    public static String repeat(String string, int repeats){
        String newstring = "";
        for (int i = 0; i < string.length(); i++){
            int k = repeats;
            while (k != 0) {
                newstring += string.charAt(i);
                k -= 1;
            }
        }
        return newstring;
    }
    // Метод, который принимает массив и возвращает разницу между
    // самыми большими и самыми маленькими числами.
    public static int differenceMaxMin(int[] int_list){
        int min_n = int_list[0];
        int max_n = int_list[0];
        for(int num: int_list){
            if(num < min_n){
                min_n = num;
            }
            if(num > max_n){
                max_n = num;
            }
        }
        return max_n - min_n;
    }
    // Метод, который принимает массив в качестве аргумента и возвращает
    // true или false в зависимости от того, является ли среднее значение всех
    // элементов массива целым числом или нет.
    public static boolean isAvgWhole(int[] ints){
        int n = ints.length;
        double sum_num = 0;
        for(int num: ints){
            sum_num += num;
        }
        if(sum_num%n == 0){
            return true;
        }
        return false;
    }
    // Метод, который берет массив целых чисел и возвращает массив, в
    // котором каждое целое число является суммой самого себя + всех предыдущих
    // чисел в массиве.
    public static String cumulativeSum(int[] ints){
        int orig_num = 0;
        for (int i = 0; i < ints.length; i++){
            if (i > 0){
                orig_num = ints[i-1];
            }
            ints[i] = ints[i] + orig_num;

        }
        return Arrays.toString(ints);
    }
    // Метод, который возвращает число десятичных знаков, которое имеет
    // число (заданное в виде строки). Любые нули после десятичной точки
    // отсчитываются в сторону количества десятичных знаков.
    public static double getDecimalPlaces(String string){
        double value = Double.parseDouble(string);
        return Math.round(value%1*100d)/10d;

    }

    // Метод, который при заданном числе возвращает соответствующее
    // число Фибоначчи.
    public static int fibonacci(int num){
        int num_1 = 0;
        int num_2 = 1;
        int sum_num = 0;
        for(int i = 0; i < num; i++){
            sum_num = num_1 + num_2;
            num_1 = num_2;
            num_2 = sum_num;
        }
        return sum_num;
    }
    // Метод определяет почтовые индексы. Действительный почтовый индекс выглядит следующим образом:
    //Должно содержать только цифры (не допускается использование нецифровых цифр).
    //Не должно содержать никаких пробелов.
    // Длина не должна превышать 5 цифр.//
    public static boolean isValid(String string){
        if (string.length() > 5) return false;
        for(int i = 0; i < string.length(); i++){
            char letter = string.charAt(i);
            if(!Character.isDigit(letter)){
                return false;
            }
        }
        return true;
    }
    //Метод определяет странные пары. Пара строк образует странную пару, если оба из следующих условий истинны:
    //Первая буква 1-й строки = последняя буква 2-й строки.
    //Последняя буква 1-й строки = первая буква 2-й строки

    public static boolean isStrangePair(String a, String b){
        if(a == "" && b == "") return true;
        if((a.charAt(a.length()-1) == b.charAt(0)) && (a.charAt(0) == b.charAt(b.length()-1))){
            return true;
        }
        return false;
    }
    // Метод, который определяет префиксы в слове.
    public static boolean isPrefix (String word, String prefix){
        for(int i = 0; i < prefix.length()-1;i++){
            if(word.charAt(i) != prefix.charAt(i)) return false;
        }
        return true;
    }
    // Метод, который определяет суффиксы в слове.
    public static boolean isSuffix (String word, String suffix){
        for(int i = 1; i < suffix.length();i++){
            int dif = i + (word.length()-suffix.length());
            if(word.charAt(dif) != suffix.charAt(i)) return false;
        }
        return true;
    }
    // Метод, который принимает число (шаг) в качестве аргумента и
    // возвращает количество полей на этом шаге последовательности.
    public static int boxSeq(int step){
        if (step == 0){
            return 0;
        }else if(step%2 == 0){
            return step;
        }else return step+2;
    }
}
