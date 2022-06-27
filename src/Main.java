import java.io.*;
import java.util.Scanner;

 public class Main {

    public static void main(String[] args) throws IOException {
        Manager manager =new Manager();

        Menu menu = new Menu();
        menu.printMenu1();
        Scanner scanner = new Scanner(System.in);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int command = scanner.nextInt();

        while (command !=0) {
            switch (command) {
                case 1: {
                    System.out.println("Список каких задач вывести");
                    menu.printMenu2();
                    command = scanner.nextInt();
                    switch (command){
                        case 1:{
                            manager.getAllTask();
                            break;
                        }
                        case 2: {
                            manager.getAllEpic();
                            break;
                        }
                        case 3: {
                            manager.getAllSubtask();
                        }
                        case 0:
                            break;
                        default: System.out.println("Такого раздела не существует");
                    }

                    break;
                }
                case 2: {
                    manager.dellAllTask();
                    manager.dellAllSubtask();   //Удаление всех SubTask
                    manager.dellAllEpic();     //удаление всех Epic
                    System.out.println("Все задачи удалены");
                    break;
                }
                case 3: {
                    System.out.println("Задачу какого типа хотите получить?");
                    menu.printMenu2();
                    command = scanner.nextInt();
                    switch (command) {
                        case 1: {
                            System.out.println("Введите ID задачи");
                            int id = scanner.nextInt();
                            manager.getTask(id);
                            break;
                        }
                        case 2: {
                            System.out.println("Введите ID задачи");
                            int id = scanner.nextInt();
                            manager.getEpic(id);
                            break;
                        }
                        case 3: {
                            System.out.println("Введите ID задачи");
                            int id = scanner.nextInt();
                            manager.getSubtask(id);
                            break;
                        }
                        case 0: {
                            break;
                        }
                        default:
                            System.out.println("Такого раздела не существует");
                    }

                    break;

                }
                case 4: {
                    System.out.println("Задачу какого типа хотите создать?");
                    menu.printMenu2();
                    command = scanner.nextInt();
                    switch (command) {
                        case 1: {
                            System.out.println("Введите название задачи (Task)");
                            //String taskName = scanner.nextLine();
                            String taskName = bufferedReader.readLine();
                            System.out.println("Введите описание задачи");
                            //String taskDescription = scanner.nextLine();
                            String taskDescription = bufferedReader.readLine();
                            manager.addTask(taskName, taskDescription);
                            break;
                        }
                        case 2: {
                            System.out.println("Введите название задачи (Epic)");
                            //String taskName = scanner.nextLine();
                            String taskName = bufferedReader.readLine();
                            System.out.println("Введите описание задачи");
                            //String taskDescription = scanner.nextLine();
                            String taskDescription = bufferedReader.readLine();
                            manager.addEpic(taskName, taskDescription);
                            break;
                        }
                        case 3: {
                            System.out.println("К какой Epic будет относиться Subtask");
                            int idEpic = scanner.nextInt();
                            System.out.println("Введите название задачи (Subtask)");
                            //String taskName = scanner.nextLine();
                            String taskName = bufferedReader.readLine();
                            System.out.println("Введите описание задачи (Subtask)");
                            //String taskDescription = scanner.nextLine();
                            String taskDescription = bufferedReader.readLine();
                            manager.addSubtask(taskName, taskDescription, idEpic);
                            break;
                        }
                        case 0:
                            break;
                        default: {
                            System.out.println("Такого раздела не существует");
                        }
                    }

                    break;
                }
                case 5: {
                    System.out.println("Задачу какого типа хотите обновить?");
                    menu.printMenu2();
                    command = scanner.nextInt();
                    switch (command) {
                        case 1: {
                            System.out.println("Укажите ID Task");
                            int id = scanner.nextInt();
                            System.out.println("Укажите новый статус (NEW, IN_PROGRESS, DONE)");
                            String status = scanner.next();
                            manager.updateTask(id, status);
                            System.out.println("Статус обновлен");
                            break;
                        }
                        case 2: {
                            System.out.println("Укажите ID Epic");
                            int id = scanner.nextInt();
                            manager.updateEpic(id);
                            System.out.println("Статус обновлен");
                            break;
                        }
                        case 3: {
                            System.out.println("Укажите ID Subtask");
                            int id = scanner.nextInt();
                            System.out.println("Укажите новый статус (NEW, IN_PROGRESS, DONE)");
                            String status = scanner.next();
                            manager.updateSubtask(id, status);
                            System.out.println("Статус обновлен");
                            break;
                        }
                        case 0: {
                            break;
                        }
                        default:
                            System.out.println("Такого раздела не существует");
                    }
                    break;
                }
                case 6: {
                    System.out.println("Задачу какого типа хотите удалить?");
                    menu.printMenu2();
                    command = scanner.nextInt();
                    switch (command) {
                        case 1: {
                            System.out.println("Укажите ID Task");
                            int id = scanner.nextInt();
                            manager.dellTask(id);
                            break;
                        }
                        case 2: {
                            System.out.println("Укажите ID Epic");
                            int id = scanner.nextInt();
                            manager.dellEpic(id);
                            break;
                        }
                        case 3: {
                            System.out.println("Укажите ID Subtask");
                            int id = scanner.nextInt();
                            manager.dellSubtask(id);
                            break;
                        }
                        case 0: {
                            break;
                        }
                        default:
                            System.out.println("Такого раздела не существует");
                    }
                    break;
                }
                default:
                    System.out.println("Такого раздела не существует");
            }
            menu.printMenu1();
            command = scanner.nextInt();
        }
        System.out.println("Программа завершена");
    }
}
