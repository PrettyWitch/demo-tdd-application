package com.tw.capability.gtb.demotddapplication;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// 单元测试 要mock
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void should_return_empty_task() {
        // given
        when(taskRepository.findAll()).thenReturn(emptyList());

        // when
        List<Task> tasks = taskService.findTasks(null);

        // then
        assertThat(tasks).isEmpty();
    }

    @Test
    void should_return_multiple_tasks() {
        // given
        List<Task> tasks = List.of(
                new Task(1L,"task01", true),
                new Task(2L,"Task02", false));

        when(taskRepository.findAll()).thenReturn(tasks);

        // when
        List<Task> foundTasks = taskService.findTasks(null);

        // then
        assertThat(foundTasks).isEqualTo(tasks);
        verify(taskRepository).findAll();
    }

    @Test
    void should_return_to_be_done_tasks_given_completed_is_false() {
        // given
        List<Task> tasks = List.of(
                new Task(1L, "task01", true),
                new Task(2L, "Task02", false));

        when(taskRepository.findAll()).thenReturn(tasks);

        // when
        List<Task> foundTasks = taskService.findTasks(false);

        // then
        assertThat(foundTasks).isEqualTo(List.of(tasks.get(1)));
        verify(taskRepository).findAll();
    }

    @Test
    void should_return_completed_tasks_given_completed_is_true() {
        // given
        List<Task> tasks = List.of(
                new Task(1L, "task01", true),
                new Task(2L, "Task02", false));

        when(taskRepository.findAll()).thenReturn(tasks);

        // when
        List<Task> foundTasks = taskService.findTasks(true);

        // then
        assertThat(foundTasks).isEqualTo(List.of(tasks.get(0)));
        verify(taskRepository).findAll();
    }


}
