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

    public static void run(boolean hasChoice){
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < 1001; i++) {
            result.put(i, play(hasChoice));
        }
        System.out.println("Выиграл в " + resultProcent(result) + "% случаев");
    }

    private static List<String> createDoors(){
        List<String> threeDoors = new ArrayList<>(3);
        fillDoors(threeDoors);
        return threeDoors;
    }

    private static int randomIndex(){
        return random.nextInt(0,3);
    }

    private static void fillDoors(List<String> emptyThreeDoors){
        for (int i = 0; i < 3; i++) {
            emptyThreeDoors.add("Empty");
        }
        emptyThreeDoors.set(randomIndex(), "Price");
    }

    private static void chooseDoor(List<String> threeDoors){
        int index = randomIndex();
        if (threeDoors.get(index).equals("Empty")){
            threeDoors.set(index, "Lose");
        } else {
            threeDoors.set(index, "Win");
        }
    }

    private static void openInvalidDoor(List<String> threeDoors){
        for (int i = 0; i < threeDoors.size(); i++) {
            if (threeDoors.get(i).equals("Empty")){
                threeDoors.set(i, "Opened");
                return;
            }
        }
    }

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

    private static int play(boolean hasChoice){
        List<String> threeDoors = createDoors();
        chooseDoor(threeDoors);
        openInvalidDoor(threeDoors);
        return getStatus(threeDoors, hasChoice);
    }

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
