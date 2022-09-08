package domain;

import manager.Status;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class CheckingLogic {
    String status;
    HashMap<Integer, Subtask> allSubtask;
    List<Integer> idSubtask;

    public CheckingLogic(HashMap<Integer, Subtask> allSubtask, List<Integer> idSubtask, String status) {
        this.allSubtask = allSubtask;
        this.idSubtask = idSubtask;
        this.status = status;
    }

    public CheckingLogic(){
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
