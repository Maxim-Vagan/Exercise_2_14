import services.StringList;
import services.impls.StringListImpl;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Задание 1 - Создание списка и проверка его Фактической длинны (кол-во элементов)
        StringList stringList = new StringListImpl(5);
        System.out.println("stringList.size() = " + stringList.size());
        // Задание 2 - Вызов метода add(item) для проверки возврата добавляемого элемента
        System.out.println("stringList.add(\"1st\") = " + stringList.add("1st"));
        System.out.println("stringList.size() = " + stringList.size());
        // печать содержимого массива для проверки
        System.out.println(Arrays.toString(stringList.toArray()));
        // Задание 3 - Вызов метода add(item) более 7-ми раз для проверки увеличения размера массива
        stringList.add("2nd");
        stringList.add("3d");
        stringList.add("4th");
        stringList.add("5th");
        stringList.add("6th");
        stringList.add("7th");
        System.out.println("stringList.size() = " + stringList.size());
        // печать содержимого массива для проверки
        System.out.println(Arrays.toString(stringList.toArray()));
        // Задание 4 - Вызов метода add(index, item) для проверки добавления элемента в начало
        System.out.println("stringList.add(0, \"8th\") = " + stringList.add(0, "8th"));
        System.out.println("stringList.size() = " + stringList.size());
        // печать содержимого массива для проверки
        System.out.println(Arrays.toString(stringList.toArray()));
        // Задание 5 - Вызов метода add(index, item) для проверки добавления элемента в конец
        System.out.println("stringList.add(7, \"9th\") = " + stringList.add(7, "9th"));
        System.out.println("stringList.size() = " + stringList.size());
        // печать содержимого массива для проверки
        System.out.println(Arrays.toString(stringList.toArray()));
        // Задание 6 - Вызов метода add(index, item) для проверки добавления элемента в середину
        System.out.println("stringList.add(5, \"10th\") = " + stringList.add(5, "10th"));
        System.out.println("stringList.size() = " + stringList.size());
        // печать содержимого массива для проверки
        System.out.println(Arrays.toString(stringList.toArray()));
        // Задание 7 - Вызов метода add(index, item) c неликвидным индексом или NULL
        //System.out.println("stringList.add(9, null) = " + stringList.add(9, null));
        // Задание 8 - Вызов метода remove(index, item) для проверки удаления первого элемента
        System.out.println("stringList.remove(0) = " + stringList.remove(0));
        System.out.println("stringList.size() = " + stringList.size());
        // печать содержимого массива для проверки
        System.out.println(Arrays.toString(stringList.toArray()));
        // Задание 9 - Вызов метода remove(index, item) для проверки удаления последнего элемента
        System.out.println("stringList.remove(8) = " + stringList.remove(8));
        System.out.println("stringList.size() = " + stringList.size());
        // печать содержимого массива для проверки
        System.out.println(Arrays.toString(stringList.toArray()));
        // Задание 10 - Вызов метода remove(index, item) для проверки удаления элемента из середины
        System.out.println("stringList.remove(2) = " + stringList.remove(2));
        System.out.println("stringList.size() = " + stringList.size());
        // печать содержимого массива для проверки
        System.out.println(Arrays.toString(stringList.toArray()));
        // Задание 11 - Вызов метода remove(index/item) c неликвидным индексом или NULL
        //System.out.println("stringList.remove(7) = " + stringList.remove(-1));
        // Задание 12 - Вызов метода remove(item) для проверки удаления элемента (при обнаружении)
        System.out.println("stringList.remove(\"4th\") = " + stringList.remove("4th"));
        System.out.println("stringList.size() = " + stringList.size());
        // печать содержимого массива для проверки
        System.out.println(Arrays.toString(stringList.toArray()));
        // Задание 13 - Вызов метода get(item) для получения элемента
        System.out.println("stringList.get(2) = " + stringList.get(2));
        // Задание 14 - Вызов метода set(index, item) для установки значения элемента
        System.out.println("stringList.set(3, \"55th\") = " + stringList.set(3, "55th"));
        System.out.println("stringList.size() = " + stringList.size());
        // печать содержимого массива для проверки
        System.out.println(Arrays.toString(stringList.toArray()));
        // Задание 15 - Вызов метода set(index, item) с пустой строкой NULL
        //System.out.println("stringList.set(3, \"55th\") = " + stringList.set(3, null));
        // Задание 16 - Вызов метода contains(item) для проверки его присутствия в списке
        System.out.println("stringList.contains(\"6th\") = " + stringList.contains("6th"));
        // Задание 17 - Вызов метода contains(item) для проверки его присутствия в списке
        System.out.println("stringList.contains(\"77th\") = " + stringList.contains("77th"));
        // Задание 18 - Вызов метода isEmpty() для проверки пуст ли список
        System.out.println("stringList.isEmpty() = " + stringList.isEmpty());
        // Задание 19 - Вызов метода equals() для проверки 2-ух списков
        StringList duplicatedStringList = new StringListImpl(6);
        duplicatedStringList.add("1st");
        duplicatedStringList.add("2nd");
        duplicatedStringList.add("10th");
        duplicatedStringList.add("55th");
        duplicatedStringList.add("6th");
        duplicatedStringList.add("9th");
        System.out.println(Arrays.toString(duplicatedStringList.toArray()));
        System.out.println("stringList.equals(duplicatedStringList) = " + stringList.equals(duplicatedStringList));
        // Задание 20 - Вызов метода clean() у 2-ух списков
        duplicatedStringList.clear();
        System.out.println(Arrays.toString(duplicatedStringList.toArray()));
        stringList.clear();
        System.out.println(Arrays.toString(stringList.toArray()));
    }
}