import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        System.out.println(solution(1,0,-1));
        System.out.println(solution(1,0,0));
        System.out.println(solution(1,0,1));

        System.out.println("---Find Zip---");
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(findZip("all zip files are compresseped"));

        System.out.println("---checkPerfect---");
        System.out.println(checkPerfect(6));
        System.out.println(checkPerfect(496));
        System.out.println(checkPerfect(12));
        System.out.println(checkPerfect(97));

        System.out.println("---flipEndChars---");
        System.out.println(flipEndChars("Cat, dog, and mouse."));
        System.out.println(flipEndChars("ada"));
        System.out.println(flipEndChars("z"));

        System.out.println("---is ValidHexCode---");
        System.out.println(isValidHexCode("#CD5C5C"));
        System.out.println(isValidHexCode("#cd5c5e"));
        System.out.println(isValidHexCode("#eaeceaeee"));
        System.out.println(isValidHexCode("#hngngf"));
        System.out.println(isValidHexCode("CD5C5C"));

        System.out.println("---same---");
        System.out.println(same(new Integer[]{1,3,4,4,4},new Integer[]{2,5,7}));
        System.out.println(same(new Integer[]{9,8,7,6},new Integer[]{4,4,3,1}));
        System.out.println(same(new Integer[]{2},new Integer[]{3,3,3,3,3}));

        System.out.println("---isKaprekar---");
        System.out.println(isKaprekar(3));
        System.out.println(isKaprekar(5));
        System.out.println(isKaprekar(297));

        System.out.println("---longestZero---");
        System.out.println(longestZero("01100001011000"));
        System.out.println(longestZero("100100100"));
        System.out.println(longestZero("11111"));

        System.out.println("---nextPrime---");
        System.out.println(nextPrime(12));
        System.out.println(nextPrime(29));
        System.out.println(nextPrime(11));

        System.out.println("---rightTriangle---");
        System.out.println(rightTriangle(3,4,5));
        System.out.println(rightTriangle(145, 105, 100));
        System.out.println(rightTriangle(70, 130,110));

    }

    /*
    Логика метода solution
    Вводятся значения а, b, c
    Вычисляется дискриминант
    Если он больше 0, то значит 2 решения уравнения
    Если равен 0, то 1
    Если меньше 0, то 0
     */
    public static int solution(int a, int b, int c){
        int D = b*b -4*a*c;
        if (D == 0){
            return 1;
        }else if(D > 0){
            return 2;
        }else return 0;
    }
    /*
    Логика метода findZip
    Вводится переменная, для подсчета кол-во zip
    Перебирается строка, если в последовательности встречается zip, n += 1
    Если это происходит второй раз, то возращается индекс местоположения начала второй zip
    Если он не нашел zip, то возращается -1
     */

    public static int findZip(String string){
        int n = 0;
        for (int i = 0; i<string.length()-2; i++){
            if ((n == 0) && (string.charAt(i) == 'z') && (string.charAt(i+1) == 'i') && (string.charAt(i+2) == 'p')){
                n+=1;
            } else if ((n == 1) && (string.charAt(i) == 'z') && (string.charAt(i+1) == 'i') && (string.charAt(i+2) == 'p')) {
                return i;
            }
        }
        return -1;
    }
    /*
    логика метода checkPerfect
    Идете перебор значений от 1, до половины заданного числа + 1 (т.к. быстрее)
    если число делится на итератор, то мы складываем его с переменной суммы (изначально равной 0)
    После перебора мы сравниваем сумму с изначальным числом и выводим результат
     */
    public static boolean checkPerfect(int num) {
        int sum_num = 0;
        for (int i = 1; i < (num / 2) + 1; i++) {
            if (num % i == 0) {
                sum_num += i;
            }
        }
        return sum_num == num;
    }
     /*
     логика метода flipEndChars
     Проверяется длина строки, если она меньше 2-ух, то возращается Incompatible
     Если последний и первый символы строки равны, то выводим Two's a pair
     Если же нет, то мы выводим Последний Элемент, субстроку и последний элемент
      */

    public static String flipEndChars(String string){
        if (string.length() < 2) return "Incompatible";
        if (string.charAt(0) == string.charAt(string.length()-1)) return "Two's a pair.";
        else {
            char fp = string.charAt(0);
            char lp = string.charAt(string.length()-1);
            return lp + string.substring(1,string.length()-1) + fp;
        }
    }
    /*
    Логика метода isValidHexCode
    Если первый знак не # то возращает false
    Если длина строки != 7 то возращает false
    Идет перебор со второго знака
    Знак переводится в ASCII и сравнивается с допустимыми значениями
    Если не подходит, возращается false
     */

    public static boolean isValidHexCode(String string){
        if (string.charAt(0) != '#') return false;
        if (string.length() != 7) return false;
        for(int i = 1; i<string.length()-1; i++){
            char character = string.charAt(i);
            int ascii_c = (int) character;
            if (!((ascii_c >= 48 && ascii_c <= 57) || (ascii_c >= 65 && ascii_c <= 70) || (ascii_c >= 96 && ascii_c <= 102))){
                return false;

            }
        }
        return true;
    }
    /*
    Логика метода same
    Переводим входящие массивы в список
    после с помощью вспомогательного метода supportSort убираем дубликаты
    После сравниваем размер списков
    если они одинаковые, то возращаем true, иначе false
     */
    public static boolean same(Integer[] arr1, Integer[] arr2){
        List<Integer> arr1_edit = new ArrayList<Integer>(Arrays.asList(arr1));
        List<Integer> arr2_edit = new ArrayList<Integer>(Arrays.asList(arr2));

        arr1_edit = supportSort(arr1_edit);
        arr2_edit = supportSort(arr2_edit);
        return arr1_edit.size() == arr2_edit.size();
    }
    public static List<Integer> supportSort(List<Integer> list){
        List<Integer> list_edit = list;
        for (int i = 0; i < list_edit.size(); i++){
            for(int k = i+1; k < list.size(); k++){
                if(list_edit.get(i) == list_edit.get(k)){
                    list_edit.remove(k);
                    k -= 1;
                }
            }

        }
        return list_edit;
    }

    /*
    Логика метода isKaprekar
    Если входное число 0 или 1, то возращаем true
    Если входное число цифра, то возращаем false
    Иначе переводим числом в строку
    Перебираем её, пока i в первой половине, то записываем число в left
    После в right
    Переводим строки в int и сравниваем их сумму с исходным числом
    Если сходится, то ворзащаем true иначе false
     */
    public static boolean isKaprekar(int num){
        if (num == 0 || num == 1) return true;

        int n_sqad = num*num;
        String string_n = Integer.toString(n_sqad);
        String left = "";
        String right = "";

        if (string_n.length() == 1) return false;
        for(int i = 0; i < string_n.length(); i++){
            if (i < string_n.length()/2){
                left = left += string_n.charAt(i);
            }else right = right += string_n.charAt(i);
        }
        return Integer.valueOf(left) + Integer.valueOf(right) == num;

    }
    /*
    Логика метода longestZero
    На вход подается строка
    n - кол-во нуленй
    max_n - максимальное кол-во
    ret - пустая строка
    Идет перебор строки и подсчет нулей
    (если идет "00" - то n + 1, при "01" n + 1 (возмещение первога 0 в последовательности)
    если n > max_n, то max_n = n
    После выводим строку с полученным кол-во нулей
     */

    public static String longestZero (String string){
        int n = 0;
        int max_n = 0;
        String ret = "";
        for (int i = 0; i < string.length()-1; i++){
            if (string.charAt(i) == '0' && string.charAt(i+1) == '0'){
                n+=1;
            }else if(string.charAt(i) == '0' && string.charAt(i+1) == '1'){
                n += 1;
                if(n > max_n) max_n = n;
                n = 0;
            }
        }
        while (max_n != 0){
            ret += '0';
            max_n -= 1;
        }
        return '"' + ret + '"' ;

    }
    /* Логика Метода nextPrime
    Идет перебор с этого числа до num + 1000
    Создаеся доп метод isPrime - который проверяет является ли число простым
    Если isPrime то возращается это число
     */

    public static int nextPrime(int num){
        for (int i = num; i < num + 1000; i++){
            if(isPrime(i)) return i;
        }
        return num;
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    /*
    Логика метода rightTriangle
    Ищется гипатенуза (максимальное значение среди x y z)
    Остальные катеты
    Проверка по Теореме Пифагора
    */
    public static boolean rightTriangle(int x, int y, int z){
        int gipp = 0;
        int f_kat = 0;
        int s_kat = 0;
        if(x > y && x > z) {
            gipp = x;
            f_kat = y;
            s_kat = z;
        }
        if(y > x && y > z) {
            gipp = y;
            f_kat = x;
            s_kat = z;
        }
        if(z > x && z > y) {
            gipp = z;
            f_kat = x;
            s_kat = y;
        }
        return (gipp*gipp == f_kat*f_kat + s_kat*s_kat);
    }



}