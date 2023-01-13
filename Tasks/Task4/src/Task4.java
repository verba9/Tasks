import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task4 {
    public static void main(String[] args) {

        System.out.println(text_recover(10,7, "hello my name is Bessie and this is my essay"));
        System.out.println("---split---");
        System.out.println(split("()()()"));
        System.out.println(split("((()))"));
        System.out.println(split("((()))(())()()(()())"));
        System.out.println(split("((())())(()(()()))"));
        System.out.println("---toCamel/Snake---");
        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toSnakeCase("helloEdabit"));
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("getColor"));
        System.out.println("---overTime---");
        System.out.println(overTime(new double[]{9,17,30,1.5}));
        System.out.println(overTime(new double[]{16, 18, 30, 1.8}));
        System.out.println(overTime(new double[]{13.25, 15, 30, 1.5}));
        System.out.println("---BMI---");
        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(BMI("55 kilos", "1.65 meters"));
        System.out.println(BMI("154 pounds", "2 meters"));
        System.out.println("---bugger---");
        System.out.println(bugger(39));
        System.out.println(bugger(999));
        System.out.println(bugger(4));
        System.out.println("---toStartShorthand---");
        System.out.println(toStarShorthand("abbccc"));
        System.out.println(toStarShorthand("77777geff"));
        System.out.println(toStarShorthand("abc"));
        System.out.println(toStarShorthand(""));
        System.out.println("---doesRhyme---");
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM."));
        System.out.println(doesRhyme("You are off to the races", "a splendid day."));
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));
        System.out.println("--trouble---");
        System.out.println(trouble(451999277, 41177722899L));
        System.out.println(trouble(1222345, 12345));
        System.out.println(trouble(666789, 12345667));
        System.out.println(trouble(33789, 12345337));
        System.out.println("---CountUniqueBooks---");
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", '$'));
        System.out.println(countUniqueBooks("ZZABCDEF", 'Z'));

    }
    /*
    Логика text_recover
    Переводим строку в массив
    перебираем этот массив
    если слово влезает в нужный размер, то мы записываем его и складываем с нынешним размером
    если нет, то переносим и задаем нынешний размер строки
    выводим строку с переносами
     */

    public static String text_recover(int n, int k, String string){
        String[] mas_str = string.split(" ");
        int string_lengt = 0;
        String exit_string = "";
        for (int i = 0; i < mas_str.length; i++){
            String now_string = mas_str[i];
            if(now_string.length() + string_lengt <= k ){
                exit_string += now_string + " ";
                string_lengt += now_string.length();
            } else if (now_string.length() + string_lengt > k) {
                exit_string += "\n" + now_string + " ";
                string_lengt = now_string.length();
            }
        }
        return exit_string;
    }
    /*
    Перебирается строка
    Если находит символ "(", то счетчик прибавляется
    Иначе убывает
    потом символ записывается в строку
    когда счетчик равен 0
    строка записывается в возращаемому строку
    то,
     */
    public static List split(String string){
        int num_bkt = 0;
        ArrayList<String> return_bkt = new ArrayList<String>();
        String return_str = "";
        String edit_str = "";
        for (int i = 0; i < string.length(); i++){
            if(string.charAt(i) == '('){
                num_bkt+=1;
            }else if (string.charAt(i) == ')'){
                num_bkt-=1;
            }
            edit_str += string.charAt(i);
            if (num_bkt == 0) {
                return_bkt.add(edit_str);
                return_str += edit_str + " ";
                edit_str = "";
            }
        }
        List strList = Arrays.asList(return_str);
        return strList;
    }
    /*
    В Верблюдном кейсе мы перебираем и строку, ищем "_"
    Переводим в ASCII и отнимаем 32 (переводим строчную букву в Большую)
    А так мы просто добавляем символ из строки
    В Змеином если попадается заглавная буква, то мы к строе прибавляем "_" и потом переводим большую букву в строчную
     */
    public static String toCamelCase(String string){
        String ret_string = "";
        for(int i = 0; i < string.length(); i++){
            if (string.charAt(i) == '_'){
                int ascii = (int) string.charAt(i+1) - 32;
                ret_string += (char) ascii;
                i+=1;
            }else {
                ret_string += string.charAt(i);
            }
        }
        return ret_string;
    }
    public static String toSnakeCase(String string){
        String ret_string = "";
        for(int i = 0; i < string.length(); i++){
            if ((int) string.charAt(i) <= 90 && ((int) string.charAt(i) >= 65)){
                ret_string += '_';

                int ascii = (int) string.charAt(i) + 32;
                ret_string += (char) ascii;
            } else {
                ret_string += string.charAt(i);
            }
        }
        return ret_string;
    }
    /*
    Логика метода overTime
    Задаем переменные
    Если конец работы позднее 17, то мы высчитываем переработку потом
    Высчитываем конец работчего дня без переработки
    считаем кол-во отработаных часов
    возращаем зарплату
     */
    public static String overTime(double[] enter_ms){
        double start_wk = enter_ms[0]; // - начало раб дня
        double stop_wk = enter_ms[1]; // - конец раб дня
        double salary = enter_ms[2]; // - ставка
        double factor = enter_ms[3]; // - коэф переработки

        double normal_hr = 0;
        double overprice_hr = 0;
        if(stop_wk > 17){
            overprice_hr = stop_wk - 17;
            stop_wk -= overprice_hr;
        }
        normal_hr = stop_wk - start_wk;
        return ("$" + Math.round((normal_hr * salary + overprice_hr * salary * factor)*100d)/100d);
    }
    /*
    Логика метода BMI
    Делим вводные строки по пробелу
    В задаем в переменные обе части
    если вторая часть начинается на p (в случае с весом) и на i (в случае с ростом)
    то делаем перевод в кг и дюймы
    потом вычисляем ИМТ
    и выводим строку в заивисимости от результата
     */
    // m / h*h
    public static String BMI(String weight_str, String height_str){
        String[] arr_weight = weight_str.split(" ");
        double weight_db = Double.parseDouble(arr_weight[0]);
        String type_of_weight = arr_weight[1];

        String[] arr_height = height_str.split(" ");
        double height_db = Double.parseDouble(arr_height[0]);
        String type_of_height = arr_height[1];

        if(type_of_weight.charAt(0) == 'p') weight_db /= 2.205;
        if (type_of_height.charAt(0) == 'i') height_db/= 39.37;

        double bmi = weight_db/ (height_db*height_db);

        if(bmi >= 25) return (Math.round(bmi*10d)/10d + " Overweight");
        else if (bmi < 18.5) return (Math.round(bmi*10d)/10d + " Underweight");
        else return (Math.round(bmi*10d)/10d + " Normal weight");
    }

    /*
    Логика метода bugger
    Сразу идет проверка на то, что число меньше 9 (возращаем 0, если да)
    Вычисляется кол-во разрядов в числе (используя логарифм 10)
    пока кол-во разрядов не станет 0
    мы умножаем каждый разряд начиная с правого
    после получаем следующее число
    используем рекрусию и грубо говоря считаем кол-во использованных рекурсий
     */

    public static int bugger(int start_num){

        if (start_num <= 9) return  0;

        int next_num = 1;
        int digit = (int) Math.ceil(Math.log10(start_num + 0.5));
        while (digit != 0){
            next_num *= start_num % 10;
            start_num = start_num / 10 ;
            digit -= 1;
        }
        return 1 + bugger(next_num);
    }

    /*
    логика toStarShorthand
    К вводной строке добавляем доп знак
    если делаем перебор строки
    если символ разве слндющему символу, то счетчик + 1
    иначе мы добавляем в возращаемую строку этот символ
    Примечание: и добавялем "*n" если n != 1
     */
    public static String toStarShorthand(String string){
        String return_string = "";
        string += "&";
        int n = 1;
        for (int i = 0; i < string.length()-1; i++){
            if(string.charAt(i) == string.charAt(i + 1)){
                n++;
            }
            else {
                return_string += string.charAt(i);
                if (n != 1) return_string +=  "*" + String.valueOf(n);
                n = 1;
            }
        }
        return return_string;
    }

    /*
    логика метода doesRhyme
    Переводм строку в массив, берем последнее слово, потом мы создаем новую строку чисто из гласных
    проверяем через equalseIgnoreCase эти 2 строки возращаем это значение
     */
    public static boolean doesRhyme(String str1, String str2){
        String[] arr_str1 = str1.split(" ");
        String[] arr_str2 = str2.split(" ");
        String last_word1 = arr_str1[arr_str1.length-1];
        String last_word2 = arr_str2[arr_str2.length-1];
        String vowels_1 = "";
        String vowels_2 = "";

        for(int i = 0; i < last_word1.length(); i++){
            if("AEIOUaeiou".indexOf(last_word1.charAt(i)) != -1) vowels_1+=last_word1.charAt(i);
        }
        for(int i = 0; i < last_word2.length(); i++){
            if("AEIOUaeiou".indexOf(last_word2.charAt(i)) != -1) vowels_2+=last_word2.charAt(i);
        }
        return vowels_1.equalsIgnoreCase(vowels_2);
    }
    /*
    Логика trouble
    Переводим числа в строки
    перебираем первую строку, если число повторяется 3 раза, то добавялем это число в список
    потом перебираем значения этого списка по второй строке смотрим, повторяются ли они 2 раза
     */
    public static boolean trouble(long num1, long num2){
        String num1_str = String.valueOf(num1);
        String num2_str = String.valueOf(num2);
        List<Character> list_rep_int = new ArrayList<>();
        int q = 1;
        for (int i = 0; i < num1_str.length()-1; i++){
            if (num1_str.charAt(i) == num1_str.charAt(i+1)){
                q+=1;
            }else if(q==3){
                list_rep_int.add(num1_str.charAt(i));
                q = 1;
            }else q = 1;
        }

        for(Character num : list_rep_int){
            String str = "" + num + num;
            return num2_str.contains(str);
        }
        return false;

    }

    /*
    Логика
    Вводится рычаг
    Перебор строки
    если заходим на bookEnd, то рычаг переключаем
    если рычаг true
    то сраниваем элемент с элементами списка
    если он уникальный, то добавляем его
    выводим длину списка
     */
    public static int countUniqueBooks(String stringSequnce, char bookEnd){
        boolean lever = false;
        List<Character> list_char = new ArrayList<Character>();
        for (int i = 0; i < stringSequnce.length()-1; i++){
            if (stringSequnce.charAt(i) == bookEnd) lever = !lever;
            if (lever){
                if(!list_char.contains(stringSequnce.charAt(i)) && stringSequnce.charAt(i) != bookEnd){
                    list_char.add(stringSequnce.charAt(i));
                }
            }
        }
        return list_char.size();
    }
}