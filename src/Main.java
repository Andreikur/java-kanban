import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import domain.*;
import manager.*;

public class Main {

    public static void main(String[] args) throws IOException {

        TaskManager taskManager = Managers.getDefault();
        Task currentTask;
        Epic currentEpic;
        Subtask currentSubtask;
        List<Task> taskManager2;
        Menu menu = new Menu();
        menu.printMenu1();
        Scanner scanner = new Scanner(System.in);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        TasksTemplate.addTasksTemplate(taskManager);                   //создаем список задач!!!

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
                            List<Task> listTask = new ArrayList<>(taskManager.getAllTask());
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
                            taskManager.getAllEpics();
                            List<Epic> listEpic = new ArrayList<>(taskManager.getAllEpics());
                            if (!taskManager.getAllEpics().isEmpty()) {
                                for (Epic epic : listEpic) {
                                    System.out.println("ID: " + epic.getIdTask() + " - " + epic.getTaskName()
                                            + " статус: " + epic.getStatus());
                                }
                            }
                            else System.out.println("Список задач данного типа пуст");
                            break;
                        }
                        case 3: {
                            taskManager.getAllSubtasks();
                            List<Subtask> listSubtask = new ArrayList<>(taskManager.getAllSubtasks());
                            if (!taskManager.getAllEpics().isEmpty()) {
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
                            currentTask =taskManager.getTask(id);

                            if(currentTask != null) {
                                System.out.println(" ID: " + currentTask.getIdTask()
                                        + "\n Название: " +currentTask.getTaskName()
                                        + "\n Описание: " + currentTask.getTaskDescription()
                                        + "\n Статус: " + currentTask.getStatus());

                                //historyManager.add(taskManager.getTask(id));
                                if(taskManager.getHistory() == null) {
                                    System.out.println("Список истории пустой");
                                }
                                else {
                                    taskManager2 = taskManager.getHistory();      //убрать комментарий если не будет использоваться вывод на печать
                                    System.out.println(taskManager2);
                                }
                            }
                            else System.out.println("Задачи с таким ID нет");

                            break;
                        }
                        case 2: {
                            System.out.println("Введите ID задачи");
                            int id = scanner.nextInt();
                            currentEpic = taskManager.getEpic(id);
                            if(currentEpic != null) {
                                System.out.println(" ID: " + currentEpic.getIdTask()
                                        + "\n Название: " + currentEpic.getTaskName()
                                        + "\n Описание: " + currentEpic.getTaskDescription()
                                        + "\n Статус: " + currentEpic.getStatus()
                                        + "\n Номера подзадач: ");
                                for (Integer idSubtask : currentEpic.getIdSubtask()){
                                    System.out.print(idSubtask + ", ");
                                }
                                System.out.println("");
                                //historyManager.add(taskManager.getEpic(id));
                                if(taskManager.getHistory() == null) {
                                    System.out.println("Список истории пустой");
                                }
                                else {
                                    taskManager2 = taskManager.getHistory();
                                    System.out.println(taskManager2);
                                }

                            }
                            else System.out.println("Задачи с таким ID нет");
                            break;
                        }
                        case 3: {
                            System.out.println("Введите ID задачи");
                            int id = scanner.nextInt();
                            currentSubtask = taskManager.getSubtask(id);
                            if(currentSubtask != null) {
                                System.out.println(" ID: " + currentSubtask.getIdTask()
                                        + "\n Название: " + currentSubtask.getTaskName()
                                        + "\n Описание: " + currentSubtask.getTaskDescription()
                                        + "\n Статус: " + currentSubtask.getStatus()
                                        + "\n Входит в Epic c ID: " + currentSubtask.getIdEpic());

                                //historyManager.add(taskManager.getSubtask(id));
                                if(taskManager.getHistory() == null) {
                                    System.out.println("Список истории пустой");
                                }
                                else {
                                    taskManager2 = taskManager.getHistory();      //убрать комментарий если не будет использоваться вывод на печать
                                    System.out.println(taskManager2);
                                }
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
                            Epic epic = taskManager.getEpic(id);
                            taskManager.updateEpic(epic);
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
