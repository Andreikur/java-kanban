package Server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import domain.Epic;
import domain.Subtask;
import domain.Task;
import manager.Managers;
import manager.taskManager.FileBackedTasksManager;
import com.google.gson.Gson;
import manager.taskManager.TaskManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class HttpTaskServer extends FileBackedTasksManager {

    private static final int PORT = 8080;
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private static Gson gson = new Gson();
    public HttpServer httpServer;

    public HttpTaskServer() throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress("localhost", PORT), 0);
        httpServer.createContext("/tasks", new TaskHandler());
    }

    public void start() {

        httpServer.start();
        System.out.println("HTTP-сервер запущен на " + PORT + " порту!!!");
    }

    public void stop() {
        httpServer.stop(0);
        System.out.println("Сервер остановлен");
    }


//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public class TaskHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            String filePath = "history/history.csv";            // путь местонахождения файла
            FileBackedTasksManager.setFilePath(filePath);
            TaskManager taskManager = Managers.getDefault();
            File file = new File(filePath);
            taskManager = FileBackedTasksManager.loadFromFile(file);

            String method = httpExchange.getRequestMethod();
            String requestPath = httpExchange.getRequestURI().getPath();
            String response = "";
            int serverResponse = 404;
            int id = 0;

            switch (method) {
                case "GET": {
                    if(requestPath.endsWith("/tasks")) {
                        response = gson.toJson(taskManager.getCombinedTaskList().values());
                        serverResponse = 200;
                    } else if (requestPath.endsWith("/tasks/task")){
                        response = gson.toJson(taskManager.getAllTasks().values());
                        serverResponse = 200;
                    } else if (requestPath.endsWith("/tasks/epic")) {
                        response = gson.toJson(taskManager.getAllEpicsMap().values());
                        serverResponse = 200;
                    } else if (requestPath.endsWith("/tasks/subtask")) {
                        response = gson.toJson(taskManager.getAllSubtasksMap().values());
                        serverResponse = 200;
                    } else if (requestPath.endsWith("/tasks/history")) {
                        response = gson.toJson(taskManager.getHistoryList());
                        serverResponse = 200;
                    } else if (requestPath.contains("/tasks/task") & httpExchange.getRequestURI().getQuery() != null){
                        id = receivingId(httpExchange.getRequestURI().getQuery());
                        response = gson.toJson(taskManager.getTask(id));
                        serverResponse = 200;
                    } else if (requestPath.contains("/tasks/epic") & httpExchange.getRequestURI().getQuery() != null){
                        id = receivingId(httpExchange.getRequestURI().getQuery());
                        response = gson.toJson(taskManager.getEpic(id));
                        serverResponse = 200;
                    } else if (requestPath.contains("/tasks/subtask") & httpExchange.getRequestURI().getQuery() != null){
                        id = receivingId(httpExchange.getRequestURI().getQuery());
                        response = gson.toJson(taskManager.getSubtask(id));
                        serverResponse = 200;
                    }
                    System.out.println("GET запрос");
                    break;
                }
                case "POST": {
                    if(requestPath.contains("/tasks/task")) {
                        InputStream inputStream = httpExchange.getRequestBody();
                        String taskBody = new String(inputStream.readAllBytes(), DEFAULT_CHARSET);
                        Task task = gson.fromJson(taskBody, Task.class);
                        taskManager.addTask(task);
                        serverResponse = 201;
                    } else if(requestPath.contains("/tasks/epic")) {
                        InputStream inputStream = httpExchange.getRequestBody();
                        String taskBody = new String(inputStream.readAllBytes(), DEFAULT_CHARSET);
                        Epic epic = gson.fromJson(taskBody, Epic.class);
                        taskManager.addEpic(epic);
                        serverResponse = 201;
                    } else if(requestPath.contains("/tasks/subtask")) {
                        InputStream inputStream = httpExchange.getRequestBody();
                        String taskBody = new String(inputStream.readAllBytes(), DEFAULT_CHARSET);
                        Subtask subtask = gson.fromJson(taskBody, Subtask.class);
                        taskManager.addSubtask(subtask);
                        serverResponse = 201;
                    }
                    System.out.println("POST запрос");
                    break;
                }
                case "DELETE": {
                    if (requestPath.contains("/tasks/task") & httpExchange.getRequestURI().getQuery() != null){
                        id = receivingId(httpExchange.getRequestURI().getQuery());
                        taskManager.deleteTask(id);
                        response = gson.toJson("Task c id:" + id + " удалена!");
                        serverResponse = 200;
                    } else if (requestPath.contains("/tasks/task")){
                        id = receivingId(httpExchange.getRequestURI().getQuery());
                        taskManager.deleteAllTasks();
                        response = gson.toJson("Все Task удалены!");
                        serverResponse = 200;
                    } else if (requestPath.contains("/tasks/epic") & httpExchange.getRequestURI().getQuery() != null){
                        id = receivingId(httpExchange.getRequestURI().getQuery());
                        taskManager.dellEpic(id);
                        response = gson.toJson("Epic c id:" + id + " удалена!");
                        serverResponse = 200;
                    } else if (requestPath.contains("/tasks/epic")){
                        id = receivingId(httpExchange.getRequestURI().getQuery());
                        taskManager.deleteAllEpic();
                        response = gson.toJson("Все Epic удалены!");
                        serverResponse = 200;
                    } else if (requestPath.contains("/tasks/subtask") & httpExchange.getRequestURI().getQuery() != null){
                        id = receivingId(httpExchange.getRequestURI().getQuery());
                        taskManager.dellSubtask(id);
                        response = gson.toJson("Subtask c id:" + id + " удалена!");
                        serverResponse = 200;
                    } else if (requestPath.contains("/tasks/subtask")){
                        id = receivingId(httpExchange.getRequestURI().getQuery());
                        taskManager.deleteAllSubtask();
                        response = gson.toJson("Все Subtask удалены!");
                        serverResponse = 200;
                    }

                    System.out.println("DELETE запрос");
                    break;
                }
            }
            httpExchange.sendResponseHeaders(serverResponse, response.getBytes().length);
            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    // получение ID из запроса
    private static int receivingId(String query) {
        String[] queryArray = query.split("=");
        int id = Integer.parseInt(queryArray[1]);
        return id;
    }

}
