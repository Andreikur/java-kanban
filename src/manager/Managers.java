package manager;

import domain.Epic;
import domain.Subtask;
import domain.Task;

import java.util.ArrayList;
import java.util.List;

public class Managers {

    public static TaskManager getDefault(){
        return new TaskManager() {
            @Override
            public void addTask(Task task) {

            }

            @Override
            public ArrayList<Task> getAllTask() {
                return null;
            }

            @Override
            public Task getTask(Integer id) {
                return null;
            }

            @Override
            public void deleteAllTasks() {

            }

            @Override
            public void deleteTask(Integer id) {

            }

            @Override
            public void updateTask(Task task) {

            }

            @Override
            public void addEpic(Epic epic) {

            }

            @Override
            public ArrayList<Epic> getAllEpic() {
                return null;
            }

            @Override
            public Epic getEpic(Integer id) {
                return null;
            }

            @Override
            public ArrayList<Subtask> getListSubtaskInEpic(Integer idEpic) {
                return null;
            }

            @Override
            public void deleteAllEpic() {

            }

            @Override
            public void dellEpic(Integer id) {

            }

            @Override
            public void updateEpic(Epic epic) {

            }

            @Override
            public void addSubtask(Subtask subtask) {

            }

            @Override
            public ArrayList<Subtask> getAllSubtask() {
                return null;
            }

            @Override
            public Subtask getSubtask(Integer id) {
                return null;
            }

            @Override
            public void deleteAllSubtask() {

            }

            @Override
            public void dellSubtask(Integer id) {

            }

            @Override
            public void updateSubtask(Subtask subtask) {

            }

            @Override
            public List<Task> getHistory(){

                return null;
            }

            @Override
            public void addHistory(Task task){}

        };
    }

    /*public static HistoryManager getDefaultHistory() {
        return new HistoryManager() {
            @Override
            public void add(Task task) {

            }

            @Override
            public List<Task> getHistory() {
                return null;
            }
        };
    }*/
}
