package com.deevith.learning.todomanagerapplication.controllers;

import com.deevith.learning.todomanagerapplication.model.Todo;
import com.deevith.learning.todomanagerapplication.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Date;

@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    TodoService todoService;

    @RequestMapping("/list-todos")
    public String getListOfTodos(ModelMap modelMap) {
        modelMap.put("todos",todoService.retrieveTodos("deevith"));
        return "todos-list";
    }

    @RequestMapping(value="/add-todo",method = RequestMethod.GET)
    public String getAddToDoPage(ModelMap modelMap) {
        modelMap.addAttribute("todo", new Todo(0, (String) modelMap.get("name"), "", new Date(), false));
        return "add-todo";
    }

    @RequestMapping(value="/add-todo",method = RequestMethod.POST)
    public String addTodo(ModelMap modelMap, @Valid Todo todo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "add-todo";
        }
        todoService.addToDo("deevith",todo.getDesc(),new Date(),false);
        return "redirect:/list-todos";
    }

    @RequestMapping(value="/delete-todo",method = RequestMethod.GET)
    public String deleteToDo(ModelMap modelMap, @RequestParam int id) {
        todoService.deleteToDo(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value="/update-todo",method = RequestMethod.GET)
    public String showUpdateToDoPage(ModelMap modelMap, @RequestParam int id) {
        Todo givenTodo = todoService.retrieveTodo(id);
        modelMap.put("todo",givenTodo);
        return "add-todo";
    }

    @RequestMapping(value="/update-todo",method = RequestMethod.POST)
    public String updateToDo(ModelMap modelMap, @Valid Todo todo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "add-todo";
        }
        todo.setUser((String) modelMap.get("name"));

        todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }
}
