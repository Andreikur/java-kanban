package domain;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckingLogic {
    String status;
    HashMap<Integer, Subtask> allSubtask;
    ArrayList<Integer> idSubtask;

    public CheckingLogic(HashMap<Integer, Subtask> allSubtask, ArrayList<Integer> idSubtask, String status) {
        this.allSubtask = allSubtask;
        this.idSubtask = idSubtask;
        this.status = status;
    }

    public CheckingLogic(){

    }

    public boolean checking (HashMap<Integer, Subtask> allSubtask , ArrayList<Integer> idSubtask, String status) {
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
