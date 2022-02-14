package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.junit.jupiter.api.Test;

import model.ToDoModel;
import model.ToDoTask;

/**
 * This file contains junit code to test model
 * 
 * @author Quan Nguyen, Quang Vu, Ofer Greenberg, Allen David El.
 *
 */
public class TestModel {
	/**
	 * Testing the adding method simple.
	 */
	@Test
	public void test_add_1() {
		ToDoModel model = new ToDoModel();
		model.addList("aList");
		assertEquals(null, model.removeList("a List")); // check if the list is in
		ToDoTask newTask = new ToDoTask();
		newTask.rename("myTask");
		assertFalse(model.addTask("myMy", newTask)); // should not add
		assertTrue(model.addTask("aList", newTask)); // should add
		// test model getALL
		model.getAll();
		model.getListInt();
		model.getTaskInt();
	}

	/**
	 * Testing add task with more complicated add and testing
	 */
	@Test
	public void test_add_2() { // a more extensive test based on 1
		ToDoModel model = new ToDoModel();
		model.addList("aList");
		assertEquals(null, model.removeList("a List")); // check if the list is in
		ToDoTask newTask = new ToDoTask();
		newTask.rename("myTask");
		newTask.setImportant(true);
		assertFalse(model.addTask("myMy", newTask)); // should not add
		assertTrue(model.addTask("aList", newTask)); // should add
		ToDoTask newTask1 = new ToDoTask();
		newTask1.rename("myTask1");
		newTask1.setImportant(false);
		assertTrue(model.addTask("aList", newTask1)); // should add
		model.addList("aList2");
		model.addList("aList3");
		ToDoTask newTask2 = new ToDoTask();
		newTask2.rename("myTask2");
		assertTrue(model.addTask("aList3", newTask2)); // should add

		ToDoTask taskToTest = model.getTask("aList", "myTask");
		assertEquals(taskToTest, newTask);

		ToDoTask taskToTest1 = model.getTask("aList3", "myTask2");
		assertEquals(taskToTest1, newTask2);
		assertNull(model.getTask("aList2", "myTask"));
	}

	/**
	 * Testing the remove method to see if they are working
	 */
	@Test
	public void test_remove() {
		ToDoModel model = new ToDoModel();
		model.addList("aList");
		ToDoTask newTask = new ToDoTask();
		newTask.rename("myTask");
		model.addTask("aList", newTask);
		ToDoTask newTask1 = new ToDoTask();
		newTask1.rename("myTask1");
		model.addTask("aList", newTask1);
		model.addList("aList2");
		model.addList("aList3");
		ToDoTask newTask2 = new ToDoTask();
		newTask2.rename("myTask2");
		model.addTask("aList3", newTask2);
		assertNull(model.removeList("aList "));
		assertNotNull(model.removeList("aList2"));
		assertNull(model.removeList("aList2"));

		assertNotEquals(newTask, model.removeTask("myTask", "myTask1"));
		assertEquals(newTask, model.removeTask("aList", "myTask"));
		assertEquals(newTask1, model.removeTask("aList", "myTask1"));
		assertEquals(newTask2, model.removeTask("aList3", "myTask2"));
	}

	/**
	 * Testing the rename method to see if they're right or not.
	 */
	@Test
	public void test_rename_1() {
		ToDoModel model = new ToDoModel();
		model.addList("aList");
		ToDoTask newTask = new ToDoTask();
		newTask.rename("myTask");
		model.addTask("aList", newTask);
		ToDoTask newTask1 = new ToDoTask();
		newTask1.rename("myTask1");
		model.addTask("aList", newTask1);
		model.addList("aList2");
		model.addList("aList3");
		ToDoTask newTask2 = new ToDoTask();
		newTask2.rename("myTask2");
		model.addTask("aList3", newTask2);
		assertTrue(model.renameList("aList", "myList"));
		assertFalse(model.renameList("aList", "myList"));
		assertNull(model.removeList("aList"));
		assertFalse(model.renameTask("aList", "myTask", "newName"));
		assertTrue(model.renameTask("myList", "myTask", "newName"));
		assertNull(model.getTask("aList", "myTask"));
		assertTrue(model.renameTask("aList3", "myTask2", "newName1"));
	}

	/**
	 * Testing all of the set due date, test location extensively
	 */
	@Test
	public void test_set() {
		ToDoModel model = new ToDoModel();
		model.addList("aList");
		ToDoTask newTask = new ToDoTask();
		newTask.rename("myTask");
		model.addTask("aList", newTask);
		ToDoTask newTask1 = new ToDoTask();
		newTask1.rename("myTask1");
		model.addTask("aList", newTask1);
		model.addList("aList2");
		model.addList("aList3");
		ToDoTask newTask2 = new ToDoTask();
		newTask2.rename("myTask2");
		model.addTask("aList3", newTask2);
		assertTrue(model.setTaskDueDate("aList", "myTask", "December"));
		assertFalse(model.setTaskDueDate("aList2", "myTask", "November"));

		assertTrue(model.setTaskDueDate("aList3", "myTask2", "November"));
		assertTrue(model.setTaskLocation("aList", "myTask", "Los Angeles"));
		assertTrue(model.setTaskLocation("aList3", "myTask2", "Vietnam"));

		assertFalse(model.setTaskLocation("aList3", "myTask", "Vietnam"));
		assertFalse(model.setTaskLocation("aList21", "myTask", "Vietnam"));
		assertTrue(model.setTaskNote("aList", "myTask", "Go to Los Angeles"));
		assertTrue(model.setTaskNote("aList3", "myTask2", "Back to Vietnam"));
		assertFalse(model.setTaskNote("aList3", "myTask", "Back to Vietnam"));
		assertFalse(model.setTaskNote("aList not exist", "myTask2", "Back to Vietnam"));
		ToDoTask otherTask = model.getTask("aList", "myTask");
		ToDoTask otherTask1 = model.getTask("aList3", "myTask2");

		assertEquals("December", otherTask.getDueDate());
		assertEquals("November", otherTask1.getDueDate());

		assertEquals("Los Angeles", otherTask.getLocation());
		assertEquals("Vietnam", otherTask1.getLocation());

		assertEquals("Go to Los Angeles", otherTask.getNote());
		assertEquals("Back to Vietnam", otherTask1.getNote());
	}

	/**
	 * Testing to see if the important field related function work or not.
	 */
	@Test
	public void test_important() {
		ToDoModel model = new ToDoModel();
		model.addList("aList");
		ToDoTask newTask = new ToDoTask();
		newTask.rename("myTask");
		newTask.setImportant(true);
		ToDoTask newTask1 = new ToDoTask();
		newTask1.rename("myTask1");
		newTask1.setImportant(false);
		model.addList("aList2");
		model.addList("aList3");
		ToDoTask newTask2 = new ToDoTask();
		newTask2.rename("myTask2");

		model.addTask("aList", newTask);
		model.addTask("aList", newTask1);
		model.addTask("aList3", newTask2);

		assertTrue(model.isImportant("aList", "myTask"));
		assertFalse(model.isImportant("aList", "myTask1"));

		model.setImportant("aList", "myTask", false);
		model.setImportant("aList", "myTask1", true);
		assertFalse(model.isImportant("aList", "myTask"));
		assertTrue(model.isImportant("aList", "myTask1"));
		model.setTaskImportant("aList", "myTask", true);
		model.setTaskImportant("aList", "myTask1", false);
		assertTrue(model.isImportant("aList", "myTask"));
		assertFalse(model.isImportant("aList", "myTask1"));
	}

	/**
	 * Test to see if the done field relation work or not.
	 */
	@Test
	public void test_done() {
		ToDoModel model = new ToDoModel();
		model.addList("aList");
		ToDoTask newTask = new ToDoTask();
		newTask.rename("myTask");
		ToDoTask newTask1 = new ToDoTask();
		newTask1.rename("myTask1");
		model.addList("aList2");
		model.addList("aList3");
		ToDoTask newTask2 = new ToDoTask();
		newTask2.rename("myTask2");
		model.addTask("aList", newTask);
		model.addTask("aList", newTask1);
		model.addTask("aList3", newTask2);
		model.setDone("aList", "myTask", true);
		model.setDone("aList", "myTask1", false);
		assertTrue(model.getDone("aList", "myTask"));
		assertFalse(model.getDone("aList", "myTask1"));
	}

	/**
	 * Test to see if saving is working or not.
	 */
	@Test
	public void test_save() { // confirming the file exist with the equals content
		File file = new File("save_app.dat");
		file.delete(); // reset
		ToDoModel model = new ToDoModel();
		model.addList("aList");
		ToDoTask newTask = new ToDoTask();
		newTask.rename("myTask");
		model.addTask("aList", newTask);
		ToDoTask newTask1 = new ToDoTask();
		newTask1.rename("myTask1");
		model.addTask("aList", newTask1);
		model.addList("aList2");
		model.addList("aList3");
		ToDoTask newTask2 = new ToDoTask();
		newTask2.rename("myTask2");
		model.addTask("aList3", newTask2);
		model.setTaskInt(4);
		model.setListInt(5);
		assertEquals(4, model.getTaskInt());
		assertEquals(5, model.getListInt());
		model.saveAll();
		assertTrue(file.delete()); // if the file truly exist
	}

}
