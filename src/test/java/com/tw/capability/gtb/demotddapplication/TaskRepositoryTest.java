package com.tw.capability.gtb.demotddapplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//集成测试
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
        assertThat(foundTasks).hasSize(2);
        assertThat(foundTasks.get(0).getName()).isEqualTo("task1");
        assertThat(foundTasks.get(0).getCompleted()).isTrue();
        assertThat(foundTasks.get(1).getName()).isEqualTo("task2");
        assertThat(foundTasks.get(1).getCompleted()).isFalse();

    }

    @Test
    void should_return_created_task_when_add_task() {
        // given
        Task task = new Task("task01", false);
        entityManager.persist(task);

        // when
        Task savedTask = taskRepository.save(task);

        // then
        assertThat(savedTask).isEqualTo(task);
    }
}
