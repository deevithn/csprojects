package com.deevith.learning.todomanagerapplication.service;

import com.deevith.learning.todomanagerapplication.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private static int count = 3;

    static {
        todos.add(new Todo(1,"deevith","Spring Boot in 100 steps",new Date(),false));
        todos.add(new Todo(2,"deevith","Spring Hibernate and JPA",new Date(),false));
        todos.add(new Todo(3,"deevith","Azure developer associate",new Date(),false));
    }
    public List<Todo> retrieveTodos(String user) {
        List<Todo> result = new ArrayList<Todo>();
        for(Todo eachTodo : todos) {
            if(eachTodo.getUser().equalsIgnoreCase(user)) {
                result.add(eachTodo);
            }
        }
        return result;
    }

    public Todo retrieveTodo(int id) {
        for(Todo eachTodo : todos) {
            if(eachTodo.getId() == id) {
                return eachTodo;
            }
        }
        return null;
    }

    public void updateTodo(Todo todo) {
        todos.remove(todo);
        todos.add(todo);
    }
    public void addToDo(String username, String desc, Date targetDate, boolean isDone) {
        todos.add(new Todo(++count, username, desc, targetDate, isDone));
    }
    public void deleteToDo(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while(iterator.hasNext()) {
            Todo todo = iterator.next();
            if(todo.getId() == id) {
                iterator.remove();
            }
        }
    }
}
