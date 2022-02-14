package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import control.ToDoControl;
import model.ToDoList;
import model.ToDoModel;
import model.ToDoTask;
/**
 * This file contains Junit test case generate to test To Do Controller
 * It will test the controller and has coverage > 90%.
 * 
 * @author Quan Nguyen, Quang Vu, Ofer Greenberg, Allen David El.
 *
 */

class TestController {

	@Test
	void test() {
		// Create ToDoModel
		ToDoModel model = new ToDoModel();
		
		// Create ToDoTask
		ToDoTask task1 = new ToDoTask();
		task1.rename("CSC 252");
		task1.setNote("Zybook");
		task1.setDueDate("01/01/2022");
		task1.setLocation("Tucson");
		
		ToDoTask task2 = new ToDoTask();
		task2.rename("CSC 335");
		task2.setNote("project");
		task2.setDueDate("02/02/2022");
		task2.setLocation("Phoenix");
		
		
		ToDoControl control = new ToDoControl(model);
		
		// Test Remove
		control.addList("ListToTestRemove");
		control.removeList("ListToTestRemove");
		control.removeTask("Quang's To Do List", "CSC 335");
		control.addTask("Quang's To Do List", "CSC 335");
		control.removeTask("Quang's To Do List", "CSC 335");
		
		
		// Test add
		control.addList("Quang's To Do List");
		control.addTask("Quang's To Do List", "CSC 252");
		control.addTask("Quang's To Do List", "CSC 335");
		
		// Create ToDoTask
		control.setTaskDueDate("Quang's To Do List", "CSC 252", "01/01/2022");
		control.setTaskNote("Quang's To Do List", "CSC 252", "Zybook");
		control.setTaskLocation("Quang's To Do List", "CSC 252", "Tucson");
		
		control.setTaskDueDate("Quang's To Do List", "CSC 335", "02/02/2022");
		control.setTaskNote("Quang's To Do List", "CSC 335", "project");
		control.setTaskLocation("Quang's To Do List", "CSC 335", "Phoenix");
		
		// Test get
		ToDoList list = control.getList("Quang's To Do List");                  // Where the list is get
		ToDoTask test_task1 = control.getTask("Quang's To Do List", "CSC 252");
		
		// Task not exist
		ToDoTask null_task1 = control.getTask("Quang's To Do List", "CSC 345");
		
		// List not exist
		ToDoTask null_task2 = control.getTask("Ofer's To Do List", "CSC 252");
		
		assertEquals(test_task1.getName(), task1.getName());
		assertEquals(null_task1, null);
		assertEquals(null_task2, null);
		
		// rename
		control.renameTask("Quang's To Do List", "CSC 335", "CSC 473");
		ToDoTask test_task2 = control.getTask("Quang's To Do List", "CSC 473");
		assertEquals(test_task2.getName(), "CSC 473");
		
		// set task duedate
		control.setTaskDueDate("Quang's To Do List", "CSC 473", "01/01/2000");
		ToDoTask test_task3 = control.getTask("Quang's To Do List", "CSC 473");
		assertEquals(test_task3.getDueDate(), "01/01/2000");
		
		// set task 
		control.setTaskLocation("Quang's To Do List", "CSC 473", "Yuma");
		ToDoTask test_task4 = control.getTask("Quang's To Do List", "CSC 473");
		assertEquals(test_task4.getLocation(), "Yuma");
		
		// set note
		control.setTaskNote("Quang's To Do List", "CSC 473", "Read the book");
		ToDoTask test_task5 = control.getTask("Quang's To Do List", "CSC 473");
		assertEquals(test_task5.getNote(), "Read the book");
		
		assertEquals(list.getTask("CSC 252").getNote(), task1.getNote());
		
		ToDoTask test = control.getTask("Quang's To Do List", "CSC 473");
		System.out.println(test.getNote());
		
		// add more task
		control.addTask("Quang's To Do List", "CSC 110");
		control.setTaskDueDate("Quang's To Do List", "CSC 110", "00/00/2000");
		control.setTaskNote("Quang's To Do List", "CSC 110", "ldfsdfas");
		
		control.addTask("Quang's To Do List", "CSC 245");
		control.setTaskDueDate("Quang's To Do List", "CSC 245", "00/00/1900");
		control.setTaskNote("Quang's To Do List", "CSC 245", "ldfsdfas");
		
		// Test Sort
		
		// date sorting
		control.sortTask("leuleu", "date");
		control.sortTask("Quang's To Do List", "date");
		list = control.getList("Quang's To Do List");
		List<ToDoTask> taskList = list.getList();
		ToDoTask prev = null;
		for (ToDoTask task: taskList) {
			if (prev == null) {
				prev = task;
			}
			else {
				int check = prev.getDueDate().compareTo(task.getDueDate());
 				assertTrue(check <= 0);
			}
				
		}
		
		// set task duedate
		control.setTaskDueDate("Quang's To Do List", "CSC 473", "03/03/2023");
		assertEquals(test_task3.getDueDate(), "03/03/2023");
		
		control.sortTask("Quang's To Do List", "date");
		taskList = list.getList();
		prev = null;
		for (ToDoTask task: taskList) {
			if (prev == null) {
				prev = task;
			}
			else {
				int check = prev.getDueDate().compareTo(task.getDueDate());
 				assertTrue(check <= 0);
			}
				
		}
		
		// creation date sorting
		control.sortTask("Quang's To Do List", "creation");
		list = control.getList("Quang's To Do List");
		taskList = list.getList();
		prev = null;
		for (ToDoTask task: taskList) {
			System.out.println(task.getCreationDate());
			if (prev == null) {
				prev = task;
			}
			else {
				int check = prev.getCreationDate().compareTo(task.getCreationDate());
 				assertTrue(check <= 0);
			}
				
		}
		
		
		// name sorting
		control.sortTask("Quang's To Do List", "name");
		list = control.getList("Quang's To Do List");
		taskList = list.getList();
		prev = null;
		for (ToDoTask task: taskList) {
			if (prev == null) {
				prev = task;
			}
			else {
				int check = prev.getName().compareTo(task.getName());
 				assertTrue(check <= 0);
			}
				
		}
		
		// set task duedate
		control.renameTask("Quang's To Do List", "CSC 473", "CSC 120");
		list = control.getList("Quang's To Do List");
		control.sortTask("Quang's To Do List", "name");
		taskList = list.getList();
		prev = null;
		for (ToDoTask task: taskList) {
			if (prev == null) {
				prev = task;
			}
			else {
				int check = prev.getName().compareTo(task.getName());
 				assertTrue(check <= 0);
			}
				
		}
		control.setImportant("Quang's To Do List", "CSC 245", true);
		control.setDone("Quang's To Do List", "CSC 245", true);
		control.sortTask("Quang's To Do List", "important");
		control.sortTask("Quang's To Do List", "done");
		assertTrue(control.isDone("Quang's To Do List", "CSC 245"));
		assertTrue(control.isImportant("Quang's To Do List", "CSC 245"));
		
		
		// test movedown moveup
		control.moveDown("CSC 120", "Quang's To Do List");
		control.moveUp("CSC 120", "Quang's To Do List");
		
		// renamelist
		control.renameList("Quang's To Do List", "Quang");
		assertEquals(list.getName(), "Quang");
		control.getAll();
		
		
	}

}
