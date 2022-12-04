package com.tw.capability.gtb.demotddapplication;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.atomicMarkableReference;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureJsonTesters
class SpringBootTestTaskControllerTest {

    @Autowired
    private JacksonTester<List<Task>> taskJackson;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private TaskRepository taskRepository;

    @AfterEach
    void tearDown() {
        taskRepository.deleteAll();
    }

    @Test
    void should_return_empty_tasks() {
        // given

        // when
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("/tasks", List.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(responseEntity.getBody()).isEmpty();
    }

    @Test
    void should_return_multiple_tasks() throws IOException {
        // given
        List<Task> tasks = List.of(
                new Task("task01", true),
                new Task("Task02", false));

        taskRepository.saveAll(tasks);
        // when
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/tasks", String.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        String fetchedTasks = responseEntity.getBody();
        assertThat(taskJackson.parseObject(fetchedTasks)).isEqualTo(tasks);
    }

    @Test
    void should_return_to_be_done_tasks_given_completed_is_false() throws IOException {
        // given
        Task toBeDone = new Task("task01", false);
        taskRepository.save(toBeDone);
        Task completed = new Task("task02", true);
        taskRepository.save(completed);

        // when
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/tasks?completed=false", String.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        List<Task > fetchedTasks = taskJackson.parseObject(responseEntity.getBody());
        assertThat(fetchedTasks).hasSize(1);
        assertThat(fetchedTasks.get(0).getName()).isEqualTo(toBeDone.getName());
        assertThat(fetchedTasks.get(0).getCompleted()).isFalse();

    }
}
