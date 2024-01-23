package org.example;

/*В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла (Парадокс Монти Холла — Википедия )
 и наглядно убедиться в верности парадокса (запустить игру в цикле на 1000 и вывести итоговый счет).
Необходимо:
Создать свой Java Maven или Gradle проект;
Самостоятельно реализовать прикладную задачу;
Сохранить результат в HashMap<шаг теста, результат>
Вывести на экран статистику по победам и поражениям*/

import java.util.*;

public class Main {
    private final static Random random = new Random();

    public static void main(String[] args) {

        run(true); //если изменить выбор
        run(false); // если не менять выбор

    }

    /** Итоговый запуск
     * @param hasChoice - изменять ли выбор двери, или нет
     */
    public static void run(boolean hasChoice){
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            result.put(i, play(hasChoice));
        }
        System.out.println("Выиграл в " + resultProcent(result) + "% случаев");
    }

    /** Создаем три двери, в одной из которых приз
     */
    private static List<String> createDoors(){
        List<String> threeDoors = new ArrayList<>(3);
        fillDoors(threeDoors);
        return threeDoors;
    }

    /** случайный выбор двери
     */
    private static int randomIndex(){
        return random.nextInt(0,3);
    }

    /** Заполняем одну случайную дверь призом
     * @param emptyThreeDoors - три созданные пустые двери
     */
    private static void fillDoors(List<String> emptyThreeDoors){
        for (int i = 0; i < 3; i++) {
            emptyThreeDoors.add("Empty");
        }
        emptyThreeDoors.set(randomIndex(), "Price");
    }

    /** Выбираем дверь с призом
     * @param threeDoors - три созданные двери, в одной из которых находится приз
     */
    private static void chooseDoor(List<String> threeDoors){
        int index = randomIndex();
        if (threeDoors.get(index).equals("Empty")){
            threeDoors.set(index, "Lose");
        } else {
            threeDoors.set(index, "Win");
        }
    }

    /** Открываем пустую дверь
     * @param threeDoors - три созданные двери, в одной из которых находится приз
     */
    private static void openInvalidDoor(List<String> threeDoors){
        for (int i = 0; i < threeDoors.size(); i++) {
            if (threeDoors.get(i).equals("Empty")){
                threeDoors.set(i, "Opened");
                return;
            }
        }
    }

    /** Возвращаем статус выиграша
     * @param threeDoors - три двери, в одной из них приз
     * @param hasChoice - выбираем, менять или не менять дверь
     * @return 1- если выиграли, 0 - если проиграли
     */
    private static int getStatus(List<String> threeDoors, boolean hasChoice){
        if (hasChoice){
            if (threeDoors.contains("Win")){
                return 0;
            } else {
                return 1;
            }
        } else {
            if (threeDoors.contains("Win")){
                return 1;
            } else {
                return 0;
            }
        }
    }

    /** Проигрывается один раунд игры
     * @param hasChoice - изменять ли выбор двери, или нет
     * @return 1 - выиграл, 0 - проиграл
     */
    private static int play(boolean hasChoice){
        List<String> threeDoors = createDoors();
        chooseDoor(threeDoors);
        openInvalidDoor(threeDoors);
        return getStatus(threeDoors, hasChoice);
    }

    /** Определяем процент выигрыша
     * @param result Результат всех игр
     * @return процент выигрыша
     */
    private static double resultProcent(HashMap<Integer, Integer> result){
        int count = 0;
        for (HashMap.Entry<Integer, Integer> element : result.entrySet()) {
            if (element.getValue() == 1){
                count++;
            }
        }
        double resultPprocent = ((double) count/1000)*100;

        return resultPprocent;
    }
}
