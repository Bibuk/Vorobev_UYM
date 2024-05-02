import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.text.*;

public class Elevator {
    int Max_flor = 6;
    int Min_flor = 1;
    int current_flor = 1;
    boolean Moving = false;
    int target_flor = 0;
    int start_flor = 0;

    public static void main(String[] args) {
        Elevator elevator = new Elevator();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            elevator.Status();
            elevator.handInput(scanner);
            elevator.moveElevator();
        }
    }

    public void Status() {
        System.out.println("Текущий этаж: " + current_flor);
    }

    public void handInput(Scanner scanner) {     // Полученаем ввод от пользователя и сваеряем, правельный ли запрос.
        System.out.print("Введите номер этажа (1-6): ");
        try {
            int input = scanner.nextInt();
            if (input < Min_flor && input > Max_flor) {
                System.out.println("Некорректный номер этажа. Попробуй еще раз.");
            } else {
                if (Moving) {
                    System.out.println("Пока лифт движется, выбрать другой этаж нельзя.");
                } else {
                    startElevator(input);
                }
            }
        } catch (Exception e) {
            System.out.println("Некорректный ввод. Попробуйте еще раз.");
            scanner.nextLine();
        }
    }

    private void startElevator(int floor) { // Начало движения лифта
        target_flor = floor;
        start_flor = current_flor;
        Moving = true;
    }

    public void moveElevator() {    // Перемещение лифта
        if (Moving) {
            if (current_flor < target_flor) {
                while (current_flor < target_flor) {
                    System.out.println("Лифт находится на " + current_flor + " этаже и подымается дальше. Ждите.");
                    current_flor++;
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Лифт прибыл на " + current_flor + " этаж.");
                Moving = false;
                logElevatorMovement();
            } else if (current_flor > target_flor) {
                while (current_flor > target_flor) {
                    System.out.println("Лифт находится на " + current_flor + " этаже и опускается дальше. Ждите.");
                    current_flor--;
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Лифт прибыл на " + current_flor + " этаж.");
                Moving = false;
                logElevatorMovement();
            }
        }
    }

private void logElevatorMovement() { // Запись информации о перемещении лифта в файл elevator_log.txt
    try {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("'время' hh:mm:ss");

        BufferedWriter writer = new BufferedWriter(new FileWriter("elevator_log.txt", true));
        writer.write("Лифт переместился с " + start_flor + " на " + target_flor + " этаж"+ formatForDateNow.format(dateNow)+"\n");
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
}