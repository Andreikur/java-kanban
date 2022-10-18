package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpServer;
import domain.Epic;
import domain.Subtask;
import domain.Task;
import manager.Managers;
import manager.enums.Status;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HttpTaskServerTest {

    HttpTaskServer httpTaskServer;
    HTTPTaskManager httpTaskManager;
    HTTPTaskManager loadedTaskManager;
    HttpClient httpClient = HttpClient.newHttpClient();
    KVServer kvServer;
    String path = "http://localhost:8078";
    private static final Gson gson = new GsonBuilder().
            registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter()).
            create();

    Task task1 = new Task("testTask1", "testTaskDescription1",  30,  LocalDateTime.of(2022, 9, 25, 13, 30, 15));
    Task task2 = new Task("testTask2", "testTaskDescription1", 30, LocalDateTime.of(2022, 9, 26, 12, 0, 15));
    Epic epic1 = new Epic("testEpic2", "testEpicDescription1");
    Subtask Subtask1 = new Subtask("testSubtask1", "testSubtaskDescription1", 30, LocalDateTime.of(2022,9,25,9,0,10), 3);
    Subtask Subtask2 = new Subtask("testSubtask3", "testSubtaskDescription2", 45, LocalDateTime.of(2022, 9, 25, 10, 0, 15), 3);
    Subtask Subtask3 = new Subtask("testSubtask3", "testSubtaskDescription3", 120, LocalDateTime.of(2022, 9, 25, 11, 0, 15),  3);

    public HttpTaskServerTest() {
        loadedTaskManager = Managers.getDefaultHTTPTaskManager();
    }
    @BeforeAll
    static void constructFileForeTests() {
        var fileManager = Managers.getDefault();

        fileManager.addTask (new Task("testTask1", "testTaskDescription1",  30,  LocalDateTime.of(2022, 9, 25, 13, 30, 15)));
        fileManager.addTask(new Task("testTask2", "testTaskDescription1", 30, LocalDateTime.of(2022, 9, 26, 12, 0, 15)));
        fileManager.addEpic(new Epic("testEpic2", "testEpicDescription1"));
        fileManager.addSubtask(new Subtask("testSubtask1", "testSubtaskDescription1", 30, LocalDateTime.of(2022,9,25,9,0,10), 3));
        fileManager.addSubtask(new Subtask("testSubtask3", "testSubtaskDescription2", 45, LocalDateTime.of(2022, 9, 25, 10, 0, 15), 3));
        fileManager.addSubtask(new Subtask("testSubtask3", "testSubtaskDescription3", 120, LocalDateTime.of(2022, 9, 25, 11, 0, 15),  3));
    }

    @BeforeEach
    void starServer() throws IOException {
        kvServer = new KVServer();
        kvServer.start();
        httpTaskServer = new HttpTaskServer();
        httpTaskServer.start();
        httpTaskManager = Managers.getDefaultHTTPTaskManager();
        httpTaskManager.getToken();
        httpTaskManager.saveTasks();
    }

    @AfterEach
    void stopStart() {
        httpTaskServer.stop();
        kvServer.stop();
    }

    @Test
    void getAllTasksAndEpicsAndSubtasks() throws IOException {
        httpTaskManager = loadedTaskManager;
        httpTaskManager.saveTasks();
        URI url = URI.create(path + "/tasks");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            assertEquals(404, response.statusCode());
            String json = response.body();
            Type type = new TypeToken<ArrayList<Task>>(){}.getType();
            List<Task> tasksList = gson.fromJson(json, type);
            List<Task> expectedList = new ArrayList<>(httpTaskManager.getAllTasks().values());
            assertEquals(expectedList.size(), tasksList.size());
        } catch (IOException | InterruptedException e) {
            System.out.println("Во время выполнения запроса возникла ошибка.\n" +
                    "Проверьте, пожалуйста, адрес и повторите попытку.");
        }
    }

    @Test
    void getTasksTest() {
        URI url = URI.create(path + "/tasks/task");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            assertEquals(200, response.statusCode());
            String json = response.body();
            Type type = new TypeToken<ArrayList<Task>>(){}.getType();
            List<Task> tasksList = gson.fromJson(json, type);
            List<Task> expectedList = new ArrayList<>(httpTaskManager.getAllTasks().values());
            for (int i = 0; i < tasksList.size(); i++) {
                assertEquals(expectedList.get(i).getTaskName(), tasksList.get(i).getTaskName());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Во время выполнения запроса возникла ошибка.\n" +
                    "Проверьте, пожалуйста, адрес и повторите попытку.");
        }
    }

    @Test
    void getEpicsTest() {
        URI url = URI.create(path + "/tasks/epic");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            assertEquals(200, response.statusCode());
            String json = response.body();
            Type type = new TypeToken<ArrayList<Epic>>(){}.getType();
            List<Epic> tasksList = gson.fromJson(json, type);
            List<Epic> expectedList = new ArrayList<>(httpTaskManager.getAllEpicsMap().values());
            for (int i = 0; i < tasksList.size(); i++) {
                assertEquals(expectedList.get(i).getTaskName(), tasksList.get(i).getTaskName());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Во время выполнения запроса возникла ошибка.\n" +
                    "Проверьте, пожалуйста, адрес и повторите попытку.");
        }
    }

    @Test
    void getSubtasksTest() {
        URI url = URI.create(path + "/tasks/subtask");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            assertEquals(200, response.statusCode());
            String json = response.body();
            Type type = new TypeToken<ArrayList<Subtask>>(){}.getType();
            List<Subtask> tasksList = gson.fromJson(json, type);
            List<Subtask> expectedList = new ArrayList<>(httpTaskManager.getAllSubtasksMap().values());
            for (int i = 0; i < tasksList.size(); i++) {
                assertEquals(expectedList.get(i).getTaskName(), tasksList.get(i).getTaskName());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Во время выполнения запроса возникла ошибка.\n" +
                    "Проверьте, пожалуйста, адрес и повторите попытку.");
        }
    }

    @Test
    void getTaskByIdTest() {
        URI url = URI.create(path + "/tasks/task?id=1");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            assertEquals(200, response.statusCode());
            String json = response.body();
            System.out.println(response.body());
            Task task = gson.fromJson(json, Task.class);
            Task expectedTask = httpTaskManager.getTask(1);

            assertEquals(expectedTask.getTaskName(), task.getTaskName());
        } catch (IOException | InterruptedException e) {
            System.out.println("Во время выполнения запроса возникла ошибка.\n" +
                    "Проверьте, пожалуйста, адрес и повторите попытку.");
        }
    }

    @Test
    void getSubtaskByIdTest() {
        URI url = URI.create(path + "/tasks/subtask?id=5");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            assertEquals(200, response.statusCode());
            String json = response.body();
            Subtask subtask = gson.fromJson(json, Subtask.class);
            Subtask expectedSubtask = httpTaskManager.getSubtask(5);

            assertEquals(expectedSubtask.getTaskName(), subtask.getTaskName());
        } catch (IOException | InterruptedException e) {
            System.out.println("Во время выполнения запроса возникла ошибка.\n" +
                    "Проверьте, пожалуйста, адрес и повторите попытку.");
        }
    }

    @Test
    void getHistoryTest() {
        URI url = URI.create(path + "/tasks/history");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            assertEquals(200, response.statusCode());

            String json = response.body();
            Type type = new TypeToken<ArrayList<Task>>(){}.getType();
            List<Task> historyList = gson.fromJson(json, type);
            List<Task> expectedHistoryList = new ArrayList<>(httpTaskManager.getHistory());
            for (int i = 0; i < historyList.size(); i++) {
                assertEquals(expectedHistoryList.get(i).getTaskName(), historyList.get(i).getTaskName());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Во время выполнения запроса возникла ошибка.\n" +
                    "Проверьте, пожалуйста, адрес и повторите попытку.");
        }
    }

    @Test
    void getPrioritizedListTest() {
        URI url = URI.create(path + "/tasks/priority");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            assertEquals(200, response.statusCode());

            String json = response.body();
            Type type = new TypeToken<List<Task>>(){}.getType();
            List<Task> prioritizedList = gson.fromJson(json, type);
            List<Task> expectedPrioritizedList = new ArrayList<>(httpTaskManager.getTreeSet().getListSortedByDate());
            for (int i = 0; i < prioritizedList.size(); i++) {
                assertEquals(expectedPrioritizedList.get(i).getTaskName(), prioritizedList.get(i).getTaskName());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Во время выполнения запроса возникла ошибка.\n" +
                    "Проверьте, пожалуйста, адрес и повторите попытку.");
        }
    }

    @Test
    void deleteTaskByIdFromServerTest() {
        HTTPTaskManager rollback = Managers.getDefaultHTTPTaskManager();
        URI url = URI.create(path + "/tasks/task?id=2");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .DELETE()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            assertEquals(200, response.statusCode());
            HTTPTaskManager taskManager = Managers.getDefaultHTTPTaskManager();
            assertNull(taskManager.getTask(2));
            httpTaskManager = rollback;
        } catch (IOException | InterruptedException e) {
            System.out.println("Во время выполнения запроса возникла ошибка.\n" +
                    "Проверьте, пожалуйста, адрес и повторите попытку.");
        }
    }

    @Test
    void deleteSubtaskByIdFromServerTest() {
        HTTPTaskManager rollback = Managers.getDefaultHTTPTaskManager();
        URI url = URI.create(path + "/tasks/subtask?id=6");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .DELETE()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            assertEquals(200, response.statusCode());
            httpTaskManager = Managers.getDefaultHTTPTaskManager();
            assertNull(httpTaskManager.getSubtask(6));
            httpTaskManager = rollback;
        } catch (IOException | InterruptedException e) {
            System.out.println("Во время выполнения запроса возникла ошибка.\n" +
                    "Проверьте, пожалуйста, адрес и повторите попытку.");
        }
    }

}