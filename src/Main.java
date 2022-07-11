import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import domain.*;
import manager.*;

public class Main {

    public static void main(String[] args) throws IOException {

        TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = Managers.getDefaultHistory();
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
                            taskManager.getAllTask();
                            ArrayList<Task> listTask = new ArrayList<>(taskManager.getAllTask());
                            if (!taskManager.getAllTask().isEmpty()) {
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
                            taskManager.getAllEpic();
                            ArrayList<Epic> listEpic = new ArrayList<>(taskManager.getAllEpic());
                            if (!taskManager.getAllEpic().isEmpty()) {
                                for (Epic epic : listEpic) {
                                    System.out.println("ID: " + epic.getIdTask() + " - " + epic.getTaskName()
                                            + " статус: " + epic.getStatus());
                                }
                            }
                            else System.out.println("Список задач данного типа пуст");
                            break;
                        }
                        case 3: {
                            taskManager.getAllSubtask();
                            ArrayList<Subtask> listSubtask = new ArrayList<>(taskManager.getAllSubtask());
                            if (!taskManager.getAllEpic().isEmpty()) {
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
                    taskManager.deleteAllTasks();
                    taskManager.deleteAllSubtask();   //Удаление всех SubTask
                    taskManager.deleteAllEpic();     //удаление всех Epic
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
                            taskManager.getTask(id);
                            historyManager.add(taskManager.getTask(id));
                            historyManager.getHistory();
                            System.out.println(historyManager.getHistory());

                            /*if(taskManager.getTask(id) != null) {
                                System.out.println(" ID: " + taskManager.getTask(id).getIdTask()
                                        + "\n Название: " + taskManager.getTask(id).getTaskName()
                                        + "\n Описание: " + taskManager.getTask(id).getTaskDescription()
                                        + "\n Статус: " + taskManager.getTask(id).getStatus());
                            }
                            else System.out.println("Задачи с таким ID нет");*/
                            break;
                        }
                        case 2: {
                            System.out.println("Введите ID задачи");
                            int id = scanner.nextInt();
                            taskManager.getEpic(id);
                            historyManager.add(taskManager.getEpic(id));
                            historyManager.getHistory();
                            System.out.println(historyManager.getHistory());

                            /*if(taskManager.getEpic(id) != null) {
                                System.out.println(" ID: " + taskManager.getEpic(id).getIdTask()
                                        + "\n Название: " + taskManager.getEpic(id).getTaskName()
                                        + "\n Описание: " + taskManager.getEpic(id).getTaskDescription()
                                        + "\n Статус: " + taskManager.getEpic(id).getStatus()
                                        + "\n Номера подзадач: ");
                                for (Integer idSubtask : taskManager.getEpic(id).getIdSubtask()){
                                    System.out.print(idSubtask + "  ");
                                    System.out.println("");
                                }
                            }
                            else System.out.println("Задачи с таким ID нет");*/

                            break;
                        }
                        case 3: {
                            System.out.println("Введите ID задачи");
                            int id = scanner.nextInt();
                            taskManager.getSubtask(id);
                            historyManager.add(taskManager.getSubtask(id));
                            historyManager.getHistory();
                            System.out.println(historyManager.getHistory());

                            /*if(taskManager.getSubtask(id) != null) {
                                System.out.println(" ID: " + taskManager.getSubtask(id).getIdTask()
                                        + "\n Название: " + taskManager.getSubtask(id).getTaskName()
                                        + "\n Описание: " + taskManager.getSubtask(id).getTaskDescription()
                                        + "\n Статус: " + taskManager.getSubtask(id).getStatus()
                                        + "\n Входит в Epic c ID: " + taskManager.getSubtask(id).getIdEpic());
                            }
                            else System.out.println("Задачи с таким ID нет");*/
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
                            taskManager.addTask(task);
                            break;
                        }
                        case 2: {
                            System.out.println("Введите название задачи (Epic)");
                            String taskName = bufferedReader.readLine();
                            System.out.println("Введите описание задачи");
                            String taskDescription = bufferedReader.readLine();
                            Epic epic = new Epic(taskName, taskDescription);
                            taskManager.addEpic(epic);
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
                            taskManager.addSubtask(subtask);
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
                            Status status1 = Status.valueOf(status);  //приведение к типу Enum

                            Task task = taskManager.getTask(id);
                            if (task != null){
                            task.setStatus(status1);
                                taskManager.updateTask(task);
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
                            taskManager.updateEpic(taskManager.getEpic(id));    // обновляем  Epic с указанным Id
                            System.out.println("Статус обновлен");
                            break;
                        }
                        case 3: {
                            System.out.println("Укажите ID Subtask");
                            int id = scanner.nextInt();
                            System.out.println("Укажите новый статус (NEW, IN_PROGRESS, DONE)");
                            String status = scanner.next();
                            Status status1 = Status.valueOf(status);  //приведение к типу Enum

                            Subtask subtask = taskManager.getSubtask(id);
                            if (subtask != null) {
                                subtask.setStatus(status1);
                                taskManager.updateSubtask(subtask);
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
                            taskManager.deleteTask(id);
                            break;
                        }
                        case 2: {
                            System.out.println("Укажите ID Epic");
                            int id = scanner.nextInt();
                            taskManager.dellEpic(id);
                            break;
                        }
                        case 3: {
                            System.out.println("Укажите ID Subtask");
                            int id = scanner.nextInt();
                            taskManager.dellSubtask(id);
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
