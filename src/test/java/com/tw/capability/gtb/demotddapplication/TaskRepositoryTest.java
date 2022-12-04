package com.tw.capability.gtb.demotddapplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void should_return_empty_list() {
        // given

        // when
        List<Task> tasks = taskRepository.findAll();

        // then
        assertThat(tasks).isEmpty();
    }

    @Test
    void should_return_multiple_tasks() {
        // given
        entityManager.persist(new Task("task1", true));
        entityManager.persist(new Task("task2", false));

        // when
        List<Task> foundTasks = taskRepository.findAll();

        // then
        assertThat(foundTasks)
                .hasSize(2)
                .containsOnly(
                        new Task(1L, "task1", true),
                        new Task(2L, "task2", false)
                );

    }
}
