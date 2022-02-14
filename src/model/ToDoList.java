package model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The representation of the whole ToDoList which will be composed of each
 * ToDoTask. Using the interface of a List and the underlying structure of
 * ArrayList from java.util to contain each ToDoTask.
 * 
 * @author Quan Nguyen, Quang Vu, Ofer Greenberg, Allen David El.
 *
 */

public class ToDoList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7138753154130458365L;
	/**
	 * The name of the list
	 */
	private String name;
	/**
	 * The list which contains ToDoTask
	 */
	private List<ToDoTask> list;

	/**
	 * Constructor. Create an initial empty ToDoList with nothing in it yet. The
	 * list will be have a name first.
	 * @param name the name of the list will be added in
	 */
	public ToDoList(String name) {
		this.name = name;
		this.list = new ArrayList<ToDoTask>();
	}

	/**
	 * Rename the list to another name
	 * 
	 * @param name the name which the list will be changed into
	 */
	public void renameList(String name) {
		this.name = name;
	}
	
	/**
	 * return the name of the list
	 * 
	 * @return name of the list
	 */
	public String getName() {
		return this.name;
	}

	
	/**
	 * Return the list of ToDoTask
	 * 
	 * @return the list of ToDoTask
	 */
	public List<ToDoTask> getList() {
		return this.list;
	}



	
	

	/**
	 * @author Allen El
	 * This method returns the task that has the name of theh input param String name
	 * @param name the name of the task to be search
	 * @return the Task with the name, or null if the task does not exist
	 */
	public ToDoTask getTask(String name) {
		for(ToDoTask task : list){
			if(task.getName().equals(name)){
				return task;
			}
		}
		return null; 
	}
	/**
	 * Add a ToDoTask into the list.
	 * @param task the task to be added in 
	 */
	public void addTask(ToDoTask task) {
		this.list.add(task); 
	}
	
	/**
	 * Author: Quang Vu
	 * Purpose: This method will rename the given task 
	 * @param nameTask String represent name of task that need to change
	 * @param newName String represent new name
	 * @return true if successfully change the name.
	 */
	public boolean renameTask (String nameTask, String newName) {
		ToDoTask task = getTask(nameTask);
		// if task cannot be found return null
		if (task == null)
			return false;
		
		// change name
		task.rename(newName);
		return true;
	}
	
	/**
	 * Ofer's_assinged_methods
	 * removes task from list and return it
	 * @param taskName identifier of task
	 * @return the task object that was removed
	 */
	public ToDoTask removeTask(String taskName) { 
		for (ToDoTask task:list) {
			if(task.getName().equals(taskName)) {
				list.remove(task);
				return task;
			}
		}
		return null;
	}
	/**
	 * Set location field on a task (taskName).
	 * 
	 * @param taskName    the task identifier
	 * @param newLocation the new location string
	 * @return true if the list/task were found and updated
	 */
	public boolean setTaskLocation(String taskName, String newLocation) {
		for (ToDoTask task: list) {
			if (task.getName().equals(taskName)) {
				task.setLocation(newLocation);
				return true; 
			}
		}
		return false; 
	}
	
	/**
	 * Print out the name of the list as a representation of the GUI object
	 */
	public String toString() {
		return name;
	}
}
