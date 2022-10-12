package domain;

import manager.Status;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

    public class CheckingLogic {
    private String status;
    private HashMap<Integer, Subtask> allSubtask;
    private List<Integer> idSubtask;

    public CheckingLogic(HashMap<Integer, Subtask> allSubtask, List<Integer> idSubtask, String status) {
        this.allSubtask = allSubtask;
        this.idSubtask = idSubtask;
        this.status = status;
    }

    public CheckingLogic(){                 //Вызывается в классе InMemoryTaskManager строка 152
    }

    public boolean checking (Map<Integer, Subtask> allSubtask , List<Integer> idSubtask, Status status) {
        boolean isStatus = false;
        for (Integer idSub : idSubtask) {
            Subtask thisSubtask = allSubtask.get(idSub);    //получаем субтаски входящие в эпик
            if (!(thisSubtask.getStatus()).equals(status)) {
                isStatus = false;
                break;
            }
            isStatus = true;                                   // если все субтаски  с одинаковым статусом
        }
        return isStatus;
    }
}
