import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import domain.*;
import manager.Manager;

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
                            ArrayList<Task> listTask = new ArrayList<>(manager.getAllTask());
                            if (!manager.getAllTask().isEmpty()) {
                                for (Task task : listTask) {
                                    System.out.println("ID: " + task.getIdTask() + " - " + task.getTaskName()
                                            + " статус: " + task.getStatus());
                                    }
                                }
                            else {
                                System.out.println("Список задач данного типа пуст");
                                }
                            break;
                        }
                        case 2: {
                            manager.getAllEpic();
                            ArrayList<Epic> listEpic = new ArrayList<>(manager.getAllEpic());
                            if (!manager.getAllEpic().isEmpty()) {
                                for (Epic epic : listEpic) {
                                    System.out.println("ID: " + epic.getIdTask() + " - " + epic.getTaskName()
                                            + " статус: " + epic.getStatus());
                                }
                            }
                            else System.out.println("Список задач данного типа пуст");
                            break;
                        }
                        case 3: {
                            manager.getAllSubtask();
                            ArrayList<Subtask> listSubtask = new ArrayList<>(manager.getAllSubtask());
                            if (!manager.getAllEpic().isEmpty()) {
                                for (Subtask subtask : listSubtask) {
                                    System.out.println("ID: " + subtask.getIdTask() + " - " + subtask.getTaskName()
                                            + " статус: " + subtask.getStatus());
                                }
                            }
                            else System.out.println("Список задач данного типа пуст");
                        }
                        case 0:
                            break;
                        default: System.out.println("Такого раздела не существует");
                    }

                    break;
                }
                case 2: {
                    manager.deleteAllTasks();
                    manager.deleteAllSubtask();   //Удаление всех SubTask
                    manager.deleteAllEpic();     //удаление всех Epic
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

                            if(manager.getTask(id) != null) {
                                System.out.println(" ID: " + manager.getTask(id).getIdTask()
                                        + "\n Название: " + manager.getTask(id).getTaskName()
                                        + "\n Описание: " + manager.getTask(id).getTaskDescription()
                                        + "\n Статус: " + manager.getTask(id).getStatus());
                            }
                            else System.out.println("Задачи с таким ID нет");
                            break;
                        }
                        case 2: {
                            System.out.println("Введите ID задачи");
                            int id = scanner.nextInt();
                            manager.getEpic(id);

                            if(manager.getEpic(id) != null) {
                                System.out.println(" ID: " + manager.getEpic(id).getIdTask()
                                        + "\n Название: " + manager.getEpic(id).getTaskName()
                                        + "\n Описание: " + manager.getEpic(id).getTaskDescription()
                                        + "\n Статус: " + manager.getEpic(id).getStatus()
                                        + "\n Номера подзадач: ");
                                for (Integer idSubtask : manager.getEpic(id).getIdSubtask()){
                                    System.out.print(idSubtask + "  ");
                                    System.out.println("");
                                }
                            }
                            else System.out.println("Задачи с таким ID нет");

                            break;
                        }
                        case 3: {
                            System.out.println("Введите ID задачи");
                            int id = scanner.nextInt();
                            manager.getSubtask(id);

                            if(manager.getSubtask(id) != null) {
                                System.out.println(" ID: " + manager.getSubtask(id).getIdTask()
                                        + "\n Название: " + manager.getSubtask(id).getTaskName()
                                        + "\n Описание: " + manager.getSubtask(id).getTaskDescription()
                                        + "\n Статус: " + manager.getSubtask(id).getStatus()
                                        + "\n Входит в Epic c ID: " + manager.getSubtask(id).getIdEpic());
                            }
                            else System.out.println("Задачи с таким ID нет");


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
                            String taskName = bufferedReader.readLine();
                            System.out.println("Введите описание задачи");
                            String taskDescription = bufferedReader.readLine();
                            Task task =new Task(taskName, taskDescription);
                            manager.addTask(task);
                            break;
                        }
                        case 2: {
                            System.out.println("Введите название задачи (Epic)");
                            String taskName = bufferedReader.readLine();
                            System.out.println("Введите описание задачи");
                            String taskDescription = bufferedReader.readLine();
                            Epic epic = new Epic(taskName, taskDescription);
                            manager.addEpic(epic);
                            break;
                        }
                        case 3: {
                            System.out.println("К какой Epic будет относиться Subtask");
                            int idEpic = scanner.nextInt();
                            System.out.println("Введите название задачи (Subtask)");
                            String taskName = bufferedReader.readLine();
                            System.out.println("Введите описание задачи (Subtask)");
                            String taskDescription = bufferedReader.readLine();
                            Subtask subtask = new Subtask(taskName, taskDescription, idEpic);
                            manager.addSubtask(subtask);
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

                            Task task = manager.getTask(id);
                            if (task != null){
                            task.setStatus(status);
                            manager.updateTask(task);
                            System.out.println("Статус обновлен");
                            }
                            else {
                                System.out.println("Задачи с таким ID нет");
                            }
                            break;
                        }
                        case 2: {
                            System.out.println("Укажите ID Epic");
                            int id = scanner.nextInt();
                            manager.updateEpic(manager.getEpic(id));    // обновляем  Epic с указанным Id
                            System.out.println("Статус обновлен");
                            break;
                        }
                        case 3: {
                            System.out.println("Укажите ID Subtask");
                            int id = scanner.nextInt();
                            System.out.println("Укажите новый статус (NEW, IN_PROGRESS, DONE)");
                            String status = scanner.next();

                            Subtask subtask = manager.getSubtask(id);
                            if (subtask != null) {
                                subtask.setStatus(status);
                                manager.updateSubtask(subtask);
                                System.out.println("Статус обновлен");
                            }
                            else {
                                System.out.println("Задачи с таким ID нет");
                            }
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
                            manager.deleteTask(id);
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
