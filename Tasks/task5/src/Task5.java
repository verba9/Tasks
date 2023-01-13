import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Task5 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("---encrypt/decrypt---");
        System.out.println(Arrays.toString(encrypt("Hello")));
        System.out.println(decrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println(Arrays.toString(encrypt("Sunshine")));
        System.out.println("---canMove");
        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));
        System.out.println("---canComplete---");
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(canComplete("butlz", "beautiful"));
        System.out.println(canComplete("tulb", "beautiful"));
        System.out.println(canComplete("bbutl", "beautiful"));
        System.out.println("---sumDigProd---");
        System.out.println(sumDigProd(new int[]{16, 28}));
        System.out.println(sumDigProd(new int[]{0}));
        System.out.println(sumDigProd(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println("---sameVowelGroup---");
        System.out.println(Arrays.toString(sameVowelGroup(new String[]{"toe", "ocelot", "maniac"})));
        System.out.println(Arrays.toString(sameVowelGroup(new String[]{"many", "carriage", "emit", "apricot", "animal"})));
        System.out.println(Arrays.toString(sameVowelGroup(new String[]{"hoops", "chuff", "bot", "bottom"})));
        System.out.println("---validateCard---");
        System.out.println(validateCard(1234567890123456L));
        System.out.println(validateCard(1234567890123452L));
        System.out.println("---numToEng---");
        System.out.println(numToEng(0));
        System.out.println(numToEng(18));
        System.out.println(numToEng(126));
        System.out.println(numToEng(909));
        System.out.println("---getSha256Hash");
        System.out.println(toHexString(getSHA("password123")));
        System.out.println(toHexString(getSHA("Fluffy@home")));
        System.out.println(toHexString(getSHA("Hey dude!")));
        System.out.println("---correctTitle---");
        System.out.println(corretTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(corretTitle("sansa stark, lady of winterfell."));
        System.out.println(corretTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        System.out.println("---hexLattice---");
        System.out.println(hexLattice(7));
        System.out.println(hexLattice(19));


    }

    /*
    encrypt
    В первом символе мы ищем его ascii, потом считаем разницу и добавляем её в список
    decrypt
    первый символ задает букву по ascii, потом прибавляется значение следюзего символа,
    это значение переводится в char и добавляется в строку
     */
    public static Object[] encrypt(String string) {
        List<Integer> list_int = new ArrayList<Integer>();
        int dif = 0;
        for (int i = 0; i < string.length(); i++) {
            if (i == 0) list_int.add((int) string.charAt(i));
            else {
                dif = (int) string.charAt(i) - (int) string.charAt(i - 1);
                list_int.add(dif);
            }
        }
        return list_int.toArray();
    }
    /*
     * Метод принимают строку и массив и возвращает закодированное
     * сообщение. Первая буква строки или первый элемент массива
     * представляет собой символьный код этой буквы.
     * Следующие элементы-это различия между символами.
     */
    public static String decrypt(int[] int_arr) {
        String ret_string = "";
        int dif = 0;
        for (int i = 0; i < int_arr.length; i++) {
            dif += int_arr[i];
            if (i == 0) ret_string += (char) int_arr[i];
            else {
                ret_string += (char) dif;
            }
        }
        return ret_string;
    }

    /*
    Пешка - pawn, Конь - knight, Слон - bishop, Ладья - rook , Ферзь - Queen, Король - King,
    Вычисляем на сколько фигура пошла по горизонтали и вертикали
    подставляем в ифы, вывыодим возможен ли такой ход
     */

    public static boolean canMove(String fig, String sc, String ec) {
        int dif_char = 0;
        int dif_int = 0;

        dif_char = (int) sc.charAt(0) - (int) ec.charAt(0);
        dif_int = sc.charAt(1) - ec.charAt(1);
        if (fig.equalsIgnoreCase("pawn") && dif_char == 0 && dif_int == 1) return true;
        if (fig.equalsIgnoreCase("knight") && ((Math.abs(dif_char) == 2 && Math.abs(dif_int) == 1) ||
                ((Math.abs(dif_char) == 1 && Math.abs(dif_int) == 2)))) return true;
        if (fig.equalsIgnoreCase("bishop") && (Math.abs(dif_char) == Math.abs(dif_int))) return true;
        if (fig.equalsIgnoreCase("rook") && (dif_char == 0 || dif_int == 0)) return true;
        if (fig.equalsIgnoreCase("queen") && ((Math.abs(dif_char) == Math.abs(dif_int)) ||
                (dif_char == 0 || dif_int == 0))) return true;
        if (fig.equalsIgnoreCase("king") && (Math.abs(dif_char) <= 1) && (Math.abs(dif_int) <= 1)) return true;
        return false;
    }

    /*
     Перебор 2ух строк
     если символы совпадают, то символ записывается в новую строку, второе слово обрезается
     После всего сравнивается новое слово с первым словом
     */
    public static boolean canComplete(String sword, String eword) {
        String sub_sword = "";
        for (int i = 0; i < sword.length(); i++) {
            for (int n = 0; n < eword.length(); n++) {
                if (sword.charAt(i) == eword.charAt(n)) {
                    sub_sword += eword.charAt(n);
                    eword = eword.substring(n + 1);
                    //System.out.println(sub_sword + " " + eword);
                    break;
                }
            }
        }

        return sub_sword.equals(sword);
    }

    /*
    Логика метода, сначало числа в списке перебираются и складываются
    потом переходит в метод перемножение
    если число меньше 10, то есть состоит из одной цифры, то оно возращается, иначе перемножаются все числа и число на выходе снова подается
    на вспомогательный метод
     */
    public static int sumDigProd(int[] int_list) {
        int sum_num = 0;
        for (int i : int_list) {
            sum_num += i;
        }
        return multNum(sum_num);

    }

    public static int multNum(int orig_num) {
        int multip_num = 1;
        if (orig_num < 10) return orig_num;
        else {
            while (orig_num > 0) {
                multip_num *= orig_num % 10;
                orig_num = orig_num / 10;
            }
            return multNum(multip_num);
        }
    }

    /*
    Сначала ищем все гласные в первом слове
    Потом выписываем их и сортируем
    Ищем все гласные в других словах, ортируем, сравниваем строки, если true, то добавляем в список возрата
     */
    public static String[] sameVowelGroup(String[] word_list) {
        ArrayList<String> ret_list = new ArrayList<String>();

        String orig_vowel = vowelsWord(word_list[0]);
        char[] orig_chars = orig_vowel.toCharArray();
        Arrays.sort(orig_chars);
        orig_vowel = new String(orig_chars);


        for (String word : word_list) {
            String sec_vowel = vowelsWord(word);
            char[] sec_char = sec_vowel.toCharArray();
            Arrays.sort(sec_char);
            sec_vowel = new String(sec_char);
            if (orig_vowel.equals(sec_vowel)) ret_list.add(word);
        }

        return ret_list.toArray(new String[0]);
    }

    public static String vowelsWord(String word) {
        String new_str = "";
        for (int i = 0; i < word.length(); i++) {
            if (("eyuioa".indexOf(word.charAt(i)) != -1) && (new_str.indexOf(word.charAt(i)) == -1)) {
                new_str += word.charAt(i);
            }
        }
        return new_str;
    }

    /*
    Вычисляем длину числа
    Если длина не входит в заданый промежуток, то возращаем false
    Задаем контрольную цифру
    с помощью вспомогательного метода переворачиваем число
    Умножаем каждый нечетный элемент на 2 и складываем 2 цифры числа, если оно больше 9
    Считаем Сумму вспех цифр, вычитаем из 10 последнюю цифру этой суммы и сравниваем с контрольной цифрой
     */
    public static boolean validateCard(Long num_card) {
        double num_of_dig = Math.ceil(Math.log10(num_card + 0.5));
        if (num_of_dig < 14 || num_of_dig > 19) return false;

        int check_number = (int) (num_card % 10);
        int sum_num = 0;
        num_card = num_card / 10;
        Integer[] num_list = rollNumbersInArrays(num_card);

        for (int i = 0; i < num_list.length; i++) {
            if (i % 2 != 0) {
                num_list[i] = num_list[i] * 2;
                if (num_list[i] > 9) num_list[i] = sumTwoDigits(num_list[i]);
            }
            sum_num += num_list[i];
        }
        return (10 - sum_num % 10) == check_number;

    }

    public static Integer[] rollNumbersInArrays(Long num) {
        ArrayList<Integer> ret_list = new ArrayList<Integer>();
        int reversed = 0;
        while (num != 0) {
            ret_list.add((int) (num % 10));
            num = num / 10;
        }
        return ret_list.toArray(new Integer[0]);
    }

    public static int sumTwoDigits(int num) {
        return num % 10 + num / 10;
    }

    /*
    Выписываем уникальные варианты, потом перебираем число, от большего к меньшему
    Сначала Сотни, потом десятки и единицы
    И добавляем в возращаемую строку
     */

    public static String numToEng(int num) {
        String ret_str = "";
        int iter = 0;
        if (num == 0) return "zero";
        if (num == 10) return "ten";
        if (num == 11) return "eleven";
        if (num == 12) return "twelve";
        String one = "one", two = "two", three = "three", four = "four", five = "five", six = "six",
                seven = "seven", eight = "eight", nine = "nine", fift = "fift", thirt = "thirt", twent = "twent";
        if (num > 99 && num < 1000) {
            switch (num / 100) {
                case (1) -> ret_str += one;
                case (2) -> ret_str += two;
                case (3) -> ret_str += three;
                case (4) -> ret_str += four;
                case (5) -> ret_str += five;
                case (6) -> ret_str += six;
                case (7) -> ret_str += seven;
                case (8) -> ret_str += eight;
                case (9) -> ret_str += nine;
            }
            ret_str += " hundred ";
            num = num - (num / 100 * 100); //?
        }
        if (num > 19 && num < 100) {
            switch (num / 10) {
                case (2) -> ret_str += twent;
                case (3) -> ret_str += thirt;
                case (4) -> ret_str += four;
                case (5) -> ret_str += fift;
                case (6) -> ret_str += six;
                case (7) -> ret_str += seven;
                case (8) -> ret_str += eight;
                case (9) -> ret_str += nine;
            }
            if (ret_str.charAt(ret_str.length() - 1) == 't') ret_str += "y ";
            else ret_str += "ty ";
            num = num - (num / 10 * 10);
        }
        if (num > 10 && num < 19) {
            switch (num % 10) {
                case (3) -> ret_str += thirt;
                case (4) -> ret_str += four;
                case (5) -> ret_str += fift;
                case (6) -> ret_str += six;
                case (7) -> ret_str += seven;
                case (8) -> ret_str += eight;
                case (9) -> ret_str += nine;
            }
            if (ret_str.charAt(ret_str.length() - 1) == 't') ret_str += "een";
            else ret_str += "teen";
            num = 0;
        }
        if (num > 0 && num < 10) {
            switch (num) {
                case (1) -> ret_str += one;
                case (2) -> ret_str += two;
                case (3) -> ret_str += three;
                case (4) -> ret_str += four;
                case (5) -> ret_str += five;
                case (6) -> ret_str += six;
                case (7) -> ret_str += seven;
                case (8) -> ret_str += eight;
                case (9) -> ret_str += nine;

            }
        }
        return ret_str;

    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Статический метод getInstance вызывается с хешированием SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
    /*
    public static String getSha256Hash(String string){
        String bin_str = stringToBinary(string); // Шаг 1.1  Преобразуем в двоичный вид:
        //System.out.println(bin_str);
        bin_str = bin_str.replaceAll("\\s+",""); // Убираем пробелы
        int or_lengt = bin_str.length();
        bin_str += "10000000"; // Шаг 1.2  Добавим одну единицу:
        while (bin_str.length() != 512 - 64) bin_str += '0'; // Шаг 1.3 Заполняем нулями до тех пор, пока данные не станут кратны 512 без последних 64 бит
        int bin_or_lenght = Integer.toBinaryString(or_lengt).length(); // Шаг 1.4 Добавим 64 бита в конец, где 64 бита — целое число с порядком байтов big-endian, обозначающее длину входных данных в двоичном виде.
        while (bin_str.length() != 512 - bin_or_lenght) bin_str += '0';
        bin_str += Integer.toBinaryString(or_lengt);
        // Шаг 2 нициализация значений хеша и окр. констант
        final int[] h = new int[]{0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a, 0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19,
                0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
                0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
                0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
                0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
                0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
                0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
                0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
                0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2};
        // Шаг 5. Создаём очередь сообщений (w)
        String new_str = bin_str;
        new_str = new_str.replaceAll("(.{32})", "$1 "); // Копируем входные данные из шага 1 в новый массив, где каждая запись является 32-битным словом:
        for(int i = 0; i < 48; i++) new_str += "00000000000000000000000000000000 "; // Добавляем ещё 48 слов, инициализированных нулями, чтобы получить массив w[0…63]:
        String[] mass_str = new_str.split(" ");
        // Изменяем нулевые индексы в конце массива
        System.out.println(mass_str[16]);
        for (int i = 16; i <= 63; i++){
          int s0f1 = Integer.parseInt(mass_str[i-15]) >> 7;
          int s0f2 = Integer.parseInt(mass_str[i-15]) >> 18;
          int s0f3 = Integer.parseInt(mass_str[i-15]) >>> 3;
          int s1f1 = Integer.parseInt(mass_str[i-15]) >> 7;
          int s1f2 = Integer.parseInt(mass_str[i-15]) >> 18;
          int s1f3 = Integer.parseInt(mass_str[i-15]) >>> 3;
          int s0 = (s0f1 ^ s0f2 ^ s0f3);
          int s1 = (s1f1 ^ s1f2 ^ s1f3);
          System.out.println(s0 + " " + s1);
          mass_str[i] = mass_str[i - 16] + s0 + mass_str[i - 7] + s1;
        }
        System.out.println(mass_str[16]);
        System.out.println(new_str);
        return bin_str;
    }
    public static String stringToBinary(String s) {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int numk = Integer.toBinaryString(c).length();
            while (numk != 8){
                answer.append('0');
                numk++;
            }
            answer.append(Integer.toBinaryString(c)).append(' ');
        }
        return answer.toString();
    }
    /*
    public static int first32Bit(int num_const){
        int const_s = simpleDigit(num_const);
        double fract = Math.sqrt(const_s) % 1;
        System.out.println(fract);
        return 0;
    }
    public static int simpleDigit (int num_digits){
        int k = 0;
        if (num_digits == 0) return 2;
        for(int i = 3; i < 312; i++){
            if(isSimple(i)) k+= 1;
            if(k == num_digits) return i;
        }
    return 0;
    }
    public static boolean isSimple(int n){
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
     */

    public static String corretTitle(String s) {
        s = s.toLowerCase();
        String ret_s = "";
        String start = s.substring(0, 3);

        for (int i = 0; i < s.length() - 1; i++) {
            if (i == s.length() - 2) {
                ret_s += s.charAt(i) + "" + s.charAt(i + 1);
                break;
            }
            if (!"the".contains(start) && i == 0) {
                ret_s += Character.toUpperCase(s.charAt(i));
                i++;
            }
            if (s.charAt(i) == ' ') {
                String new_str = "";
                new_str += s.charAt(i + 1) + "" + s.charAt(i + 2) + "" + s.charAt(i + 3);
                if (!"and".contains(new_str) && !"the".contains(new_str) && !"in ".contains(new_str) && !"of ".contains(new_str)) {
                    ret_s += " " + Character.toUpperCase(s.charAt(i + 1));
                    i++;
                } else ret_s += " ";
            } else ret_s += s.charAt(i);
        }
        return ret_s;

    }

    public static String hexLattice(int n) {
        String ret_str = "";
        String space = " ";
        String circl = "o ";
        int iter_lat = isLattice(n);
        if (iter_lat == 0) return "incorrect";
        for (int i = iter_lat; i <= iter_lat+(iter_lat-1); i++){
            ret_str += space.repeat(iter_lat+(iter_lat-1) - i);
            ret_str += circl.repeat(i);
            ret_str += "\n";
        }
        for (int i = iter_lat+(iter_lat-2); i >= iter_lat; i--){
            ret_str += space.repeat(iter_lat+(iter_lat-1) - i);
            ret_str += circl.repeat(i);
            ret_str += "\n";
        }
        return ret_str;
    }

    public static int isLattice(int n) {
        for (int i = 1; i < 100; i++) {
            int k = (int) (1 + 6 * (0.5 * i * (i - 1)));
            if(k == n){
                return i;
            }
        }
        return 0;
    }


}