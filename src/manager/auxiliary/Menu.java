package manager.auxiliary;

public class Menu {

    public void printMenu1 () {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Получить список всех задач");
        System.out.println("2 - Удалить все задачи");
        System.out.println("3 - Получить задачу по идентификатору");
        System.out.println("4 - Создать задачу");
        System.out.println("5 - Обновить статус задачи");
        System.out.println("6 - Удалить задачу по идентификатору");
        System.out.println("7 - Вывести список задач в порядке приоритета");
        System.out.println("0 - Завершить работу программы");
    }

    public void printMenu2 () {
        System.out.println("1 - Task");
        System.out.println("2 - Epic");
        System.out.println("3 - Subtask");
        System.out.println("0 - Выйти на уровень выше ");
    }
}
