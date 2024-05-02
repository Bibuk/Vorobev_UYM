package org.example;
import java.util.Scanner;

public class Elevator {
    int Max_flor = 6;
    int Min_flor = 1;
    int current_flor = 1;
    boolean Moving = false;
    int target_flor = 0;

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

    public void handInput(Scanner scanner) {
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

    private void startElevator(int floor) {
        target_flor = floor;
        Moving = true;
    }

    public void moveElevator() {
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
                    if (current_flor == target_flor) {
                        System.out.println("Лифт прибыл на " + current_flor + " этаж.");

                    }
                }
            } else if (current_flor > target_flor) {
                while (current_flor > target_flor ) {
                    System.out.println("Лифт находится на " + current_flor + " этаже и опускается дальше. Ждите.");
                    current_flor--;
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (current_flor == target_flor) {
                        System.out.println("Лифт прибыл на " + current_flor + " этаж.");
                    }

                }
            }
        }
    }
}