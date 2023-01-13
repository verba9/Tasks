import java.util.*;

public class Task6 {

    public static void main(String[] args) {
        System.out.println("---bell---");
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));
        System.out.println(bell(8));
        System.out.println("---translateWord---");
        System.out.println(translateWord("flag"));
        System.out.println(translateWord("Apple"));
        System.out.println(translateWord("button"));
        System.out.println(translateWord(""));
        System.out.println(translateSentence("I like to eat honey waffles."));
        System.out.println(translateSentence("Do you think it is going to rain today?"));
        System.out.println("---validColor---");
        System.out.println(validColor("rgb(0,0,0)"));
        System.out.println(validColor("rgb(0,,0)"));
        System.out.println(validColor("rgb(255,256,255)"));
        System.out.println(validColor("rgba(0,0,0,0.123456789)"));
        System.out.println("---stripUrlParams---");
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new Character[]{'b'}));
        System.out.println(stripUrlParams("https://edabit.com", new Character[]{'b'}));
        System.out.println("---getHastTags---");
        System.out.println(Arrays.toString(getHashTags("How the Avocado Became the Fruit of the Global Trade")));
        System.out.println(Arrays.toString(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year")));
        System.out.println(Arrays.toString(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit")));
        System.out.println(Arrays.toString(getHashTags("Visualizing Science")));
        System.out.println("---ulam---");
        System.out.println(ulam(4));
        System.out.println(ulam(9));
        System.out.println(ulam(206));
        System.out.println("---longestNonrepeatingSubstring----");
        System.out.println(lonNotRepSubstr("abcabcbb"));
        System.out.println(lonNotRepSubstr("aaaaaaa"));
        System.out.println(lonNotRepSubstr("abcde"));
        System.out.println(lonNotRepSubstr("abcda"));
        System.out.println("---convertToRoman---");
        System.out.println(convertToRoman(2));
        System.out.println(convertToRoman(12));
        System.out.println(convertToRoman(16));
        System.out.println(convertToRoman(3999));
        System.out.println("----formula---");
        System.out.println(formula("6 * 4 = 24"));
        System.out.println(formula("18 / 17 = 2"));
        System.out.println(formula("16 * 10 = 160 = 14 + 120"));
        System.out.println("---palindrom---");
        System.out.println(palindrome(11211230));
        System.out.println(palindrome(13001120));
        System.out.println(palindrome(23336014));
        System.out.println(palindrome(11));
    }

    /*
    Белл Вычисление с помощью треугольника Пирса
      0  1   2   3   4
    0|1
    1|1	 2
    2|2	 3	5
    3|5  7	10	15
    4|15 20	27	37	52
    1+1 = 2, 2 переписывается, 1+2 = 3, 2+ 3 = 5
    когда оканчивается расчет, последнее число последней строки берется, как ответ
     */
    public static int bell(int n) {
        int[][] bell_int = new int[n][];
        bell_int[0] = new int[1];
        bell_int[0][0] = 1;
        for (int m = 1; m < n; m++) {
            bell_int[m] = new int[m + 1];
            bell_int[m][0] = bell_int[m - 1][m - 1];

            for (int i = 1; i < m + 1; i++) {
                //System.out.println(bell_int[m][i-1] + " " + bell_int[m-1][i]);
                bell_int[m][i] = bell_int[m][i - 1] + bell_int[m - 1][i - 1];
            }
        }
        return bell_int[n - 1][n - 1];
    }


    /*
    Метод действует согласно алгоритму
    Перевод предложений:
    Переносим послендний символ строки, в отдлеьную переменную
    переводим всю строку в нижний регистр
    Разделяем строку на массив, потом записываем все измененные слова в строку
    Первую букву делаем заглавной, и добавляем последний символ
     */
    public static String translateWord(String string) {
        if (string.equals("")) return "";
        String ret_str = "";
        if ("aeiouyAEIOUY".indexOf(string.charAt(0)) != -1) {
            ret_str += string;
            ret_str += "yay";
        } else {
            for (int i = 0; i < string.length(); i++)
                if ("aeiouyAEIOUY".indexOf(string.charAt(i)) != -1) {
                    ret_str = string.substring(i);
                    ret_str += string.substring(0, i);
                    ret_str += "ay";
                    break;
                }


        }
        return ret_str;
    }

    public static String translateSentence(String string) {
        char last_char = string.charAt(string.length() - 1);
        char first_char;
        String ret_s = "";
        string = string.toLowerCase();
        string = string.substring(0, string.length() - 1);
        String[] s_arr = string.split(" ");
        for (String s : s_arr) {
            ret_s = ret_s + translateWord(s) + " ";
        }
        first_char = Character.toUpperCase(ret_s.charAt(0));
        ret_s = first_char + ret_s.substring(1, ret_s.length() - 1);
        return ret_s + last_char;
    }

    /*
Смотрим rgb или rgba потом создаем массив по знаку ',' переводим значение массива в числа, делаем проверку
     */
    public static boolean validColor(String s) {
        String value_of_css = "";
        if (s.contains("rgba")) {
            value_of_css = s.substring(5, s.length() - 1);
            String[] s_arr = value_of_css.split(",");
            Double[] i_arr = new Double[]{-1.0, -1.0, -1.0, -1.0};
            for (int i = 0; i < s_arr.length; i++) {
                if (!s_arr[i].equals("")) i_arr[i] = Double.valueOf(s_arr[i]);
            }
            if ((i_arr[0] < 256 && i_arr[0] >= 0) && (i_arr[1] < 256 && i_arr[1] >= 0) &&
                    (i_arr[2] < 256 && i_arr[2] >= 0) && (i_arr[3] <= 1 && i_arr[3] >= 0)) return true;
            else return false;
        } else if (s.contains("rgb")) {
            value_of_css = s.substring(4, s.length() - 1);
            String[] s_arr = value_of_css.split(",");
            Double[] i_arr = new Double[]{-1.0, -1.0, -1.0};
            for (int i = 0; i < s_arr.length; i++) {
                if (!s_arr[i].equals("")) i_arr[i] = Double.valueOf(s_arr[i]);
            }
            if ((i_arr[0] < 256 && i_arr[0] >= 0) && (i_arr[1] < 256 && i_arr[1] >= 0) &&
                    (i_arr[2] < 256 && i_arr[2] >= 0)) return true;
            else return false;
        }
        return false;

    }


    /*
    Логика метода stripUrlParams
    если входное значение без доп аргументов (чисто ссылка на сайт) то сразу возращаем ссылку
    создаем словарь, где ключ это символ, а значение это значение
    потом перебираем строку по символу '=' если находим его, то значение слева это ключ, а справа value
    потом добавляем ключи в возращаемую строку
    Для перегруженного метода
    Мы удаляем значения, которые есть в списке, а потом используем стандартный метод
     */
    public static String stripUrlParams(String url) {
        int last_char = url.length();
        if (url.contains("?")) last_char = url.indexOf('?') + 1;
        else return url;

        HashMap<Character, Integer> map_par = new HashMap<>();

        String ret_str = url.substring(0, last_char);
        url = url.substring(last_char);

        for (int i = 0; i < url.length(); i++) {
            if (url.charAt(i) == '=') {
                int value = Character.getNumericValue(url.charAt(i + 1));
                map_par.put(url.charAt(i - 1), value);
            }
        }
        Set<Character> keys = map_par.keySet();
        for (Character k : keys) {
            String val = Integer.toString(map_par.get(k));
            ret_str += k;
            ret_str += "=";
            ret_str += val;
            ret_str += "&";
        }
        ret_str = ret_str.substring(0, ret_str.length() - 1);
        return ret_str;
    }

    public static String stripUrlParams(String url, Character[] argum) {

        int last_char = url.length();
        if (url.contains("?")) last_char = url.indexOf('?') + 1;
        else return url;

        String str_r = url.substring(last_char);
        String ret_str = url.substring(0, last_char);

        String[] str_r_arr = str_r.split("&");
        List<String> list = Arrays.asList(str_r_arr);
        List<Character> ar_list = Arrays.asList(argum);
        for (String s : list) {
            if (!ar_list.contains(s.charAt(0))) {
                ret_str += s;
                ret_str += "&";


            }
        }

        ret_str = stripUrlParams(ret_str);

        return ret_str;
    }

    /*
    Логика метода getHashTags
    делим строку по пробелам, если в конце слова есть "," то убираем её
    Добавляем значения в словарь где ключ - слово, значение - длина слова
    Делаем перебор 3 раза
    Где находим максимальную длину слова
    и добавляем её в список
    обнуляем длину
    Потом переводим список в Массив и возращаем его
     */

    public static String[] getHashTags(String str) {
        List<String> ret_list = new ArrayList<String>();
        HashMap<String, Integer> map_par = new HashMap<>();
        str = str.toLowerCase();
        String[] arr_str = str.split(" ");
        for (String s : arr_str) {
            if (s.charAt(s.length() - 1) == ',') {
                s = s.substring(0, s.length() - 1);
            }
            map_par.put(s, s.length());
        }
        Set<String> keys = map_par.keySet();
        for (int i = 0; i < 3; i++) {
            String max_k = "";
            String add_k = "#";
            Integer max = 0;
            for (String s : keys) {
                if (map_par.get(s) > max) {
                    max_k = s;
                    max = map_par.get(s);
                }
            }
            map_par.put(max_k, 0);
            add_k += max_k;
            if (!add_k.equals("#"))
                ret_list.add(add_k);
        }
        return ret_list.toArray(new String[0]);


    }

    /*
    Логика метода:
    Идет перебор значений с 3-ех, до n
    Каждое знач проверяется на число Улама, если да, т добавялем в список
    Когда размер списка = n то выводим число, на котором остановились
    */
    public static int ulam(int n) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        for (int i = 3; i < n + 1000000; i++) {
            if (isUlam(i, list)) {
                list.add(i);
            }
            if (list.size() == n) {
                //System.out.println(list);
                return i;
            }
        }

        return 0;
    }

    /*
    Перебираем значения списка и скалдываем их с последующими, считаем кол-во сумм, если их != 1 выводим
    false
     */
    public static boolean isUlam(int i, List<Integer> list) {
        int count = 0;
        for (int k = 0; k < list.size(); k++) {
            for (int k2 = k; k2 < list.size(); k2++) {
                if ((list.get(k) + list.get(k2) == i) && (list.get(k) != list.get(k2))) {

                    count += 1;
                }
                if (count > 1) return false;
            }
        }
        //System.out.println(list);

        return count == 1;
    }
    /*
    Логика
    Проверяем есть ли символ в строке, если нет, то добавляем
    если есть то обнуляем
    Если длина данной строки > чем длина возратной, то ret = test и длина ret = длина test
     */

    public static String lonNotRepSubstr(String str) {
        int long_sub_ret = 0;
        int long_sub_test = 1;
        String ret_str = "";
        String test_str = "";
        for (int i = 0; i < str.length(); i++) {
            String char_i = "";
            char_i += str.charAt(i);
            if (!test_str.contains(char_i)) {
                test_str += char_i;
                long_sub_test += 1;
            } else {
                test_str = "";
                long_sub_test = 1;
            }
            if (long_sub_test > long_sub_ret) {
                long_sub_ret = long_sub_test;
                ret_str = test_str;
            }
        }
        return ret_str;
    }

    /*
    Макс 3.999 I = 1 V = 5 X = 10 L = 50 C = 100 D = 500 M = 1000
       Логика
     */
    public static String convertToRoman(int n) {
        String ret_str = "";
        while (n > 0) {
            if (n >= 1000) {
                ret_str += "M";
                n -= 1000;
            } else if (n >= 500) {
                if (n / 100 == 9) {
                    ret_str += "CM";
                    n -= 900;
                } else {
                    ret_str += "D";
                    n -= 500;
                }
            } else if (n >= 100) {
                if (n / 100 == 4) {
                    ret_str += "CD";
                    n -= 400;
                } else {
                    ret_str += "C";
                    n -= 100;
                }
            } else if (n >= 50) {
                if (n / 10 == 9) {
                    ret_str += "XC";
                    n -= 90;
                } else {
                    ret_str += "L";
                    n -= 50;
                }
            } else if (n >= 10) {
                if (n / 10 == 4) {
                    ret_str += "XL";
                    n -= 40;
                } else {
                    ret_str += "X";
                    n -= 10;
                }
            } else if (n >= 5) {
                if (n == 9) {
                    ret_str += "IX";
                    n -= 9;
                } else {
                    ret_str += "V";
                    n -= 5;
                }
            } else {
                if (n == 4) {
                    ret_str += "IV";
                    n -= 4;
                } else {
                    ret_str += "I";
                    n -= 1;
                }
            }
        }
        return ret_str;
    }

    /*
    Логика метода
    Делим на массивы
    создаем массив цифр (значение каждого массива)
    делим массив строки по пробелам
    Задаем, что первый символ это готовый результат, в зависимости от действий производим некоторые операции над ним
    сверяем результаты массивов
     */
    public static boolean formula(String str) {
        String[] str_1 = str.split("=");
        Integer[] int_1 = new Integer[str_1.length];
        for (int k = 0; k < str_1.length; k++) {
            String[] str_2 = str_1[k].split(" ");
            if (!str_2[0].equals("")) int_1[k] = Integer.parseInt(str_2[0]);
            else int_1[k] = Integer.parseInt(str_2[1]);

            //System.out.println(int_1[k]);
            for (int i = 0; i < str_2.length; i++) {
                if ("+*/-".contains(str_2[i])) {
                    switch (str_2[i]) {
                        case ("+") -> int_1[k] += Integer.parseInt(str_2[i + 1]);
                        case ("*") -> int_1[k] *= Integer.parseInt(str_2[i + 1]);
                        case ("-") -> int_1[k] -= Integer.parseInt(str_2[i + 1]);
                        case ("/") -> int_1[k] /= Integer.parseInt(str_2[i + 1]);
                    }
                }
            }
        }
        for (int i = 0; i < int_1.length - 1; i++) {
            if (int_1[i] != int_1[i + 1]) return false;
        }
        return true;
    }
    /*
    Логика
     */
    public static boolean palindrome(int n){
        String str_n = String.valueOf(n);
        Integer[] int_n = new Integer[str_n.length()/2];
        int ret_i = 0;
        int k = 0;
        if(n < 10) return true;
        if(isPalindrome(n)) return true;
        else if (!isPalindrome(n) && (n%10 + n/10 < 10)) {
            return false;
        } else {
            for(int i = 0; i < str_n.length(); i++){
                String string = "";
                string += str_n.charAt(i);
                if((i % 2 == 0)){
                    int_n[k] = Integer.parseInt(string);
                }
                else {
                    int_n[k] += Integer.parseInt(string);
                    k+=1;
                }

            }
        }
        //System.out.println(Arrays.toString(int_n));
        for(int i = 0; i < int_n.length; i++){
            int pow = (int) Math.pow(10, int_n.length-1-i);
            ret_i = ret_i + int_n[i]*pow;
        }
        //System.out.println(ret_i);

        return palindrome(ret_i);
    }
    public static boolean isPalindrome(int n){
        String left = "";
        String right = "";
        String str_n = String.valueOf(n);
        for(int i = 0;i < str_n.length()/2; i++){
            left += str_n.charAt(i);
            right += str_n.charAt(str_n.length()-i-1);
        }

        return left.equals(right);


    }
}