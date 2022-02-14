
package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * The model for the whole data of the ToDoList. The model main function will be
 * to hold the entire data of the ToDo application. The model have some basic
 * things which it can do such as get some task, change task's property, or
 * lists. etc.
 * 
 * @author Ofer Greenberg, Quan Nguyen, Quang Vu, Allen David El.
 */
@SuppressWarnings("deprecation")
public class ToDoModel extends Observable implements Serializable {

	/**
	 * The list which hold many toDoList.
	 */
	public List<ToDoList> lists;
	/**
	 * The color number which the task field will have in GUI
	 */
	private int currentColorTaskInt;
	/**
	 * the color number which the list field will have in GUI
	 */
	private int currentColorListInt;

	// --Constructors--

	/**
	 * The constructor for the model. Create an empty List of ToDoList.
	 */
	public ToDoModel() {
		lists = new ArrayList<ToDoList>();
	}

	/**
	 * @author Allen El this method adds a new list to the list of lists in this
	 *         data structure. the param name is the name to identify the list
	 * @param name is the name to id the list by
	 */
	public void addList(String name) {
		ToDoList listToAdd = new ToDoList(name);
		lists.add(listToAdd);
	}

	/**
	 * Remove a list with the same name as the id. If there is not a list with that
	 * name, return null
	 * 
	 * @param id the name of the list to be removed
	 * @return the ToDoList has the same name as the id or null
	 */
	public ToDoList removeList(String id) {
		for (ToDoList aList : lists) {
			if (aList.getName().equals(id)) {
				lists.remove(aList);
				return aList;
			}
		}
		return null;
	}

	/**
	 * takes original list id (name) and replaces it with the newName
	 * 
	 * @param oldName string representation of list id/name
	 * @param newName string of new id/name of list
	 * @return true if list was found
	 */
	public boolean renameList(String oldName, String newName) {
		for (ToDoList list : lists) {
			if (list.getName().equals(oldName)) {
				list.renameList(newName);
				return true;
			}
		}
		return false;
	}

	/**
	 * Author: Quang Vu Purpose: this method will search through lists and returns
	 * the list with param name
	 * 
	 * @param name string represent the name of list that user method want to find
	 * 
	 * @return ToDoList object, null if not found.
	 */
	public ToDoList getList(String name) {
		for (ToDoList list : lists) {
			if (list.getName().equals(name))
				return list;
		}

		// did not find the list with the given name
		return null;
	}

	// Tasks methods --------

	/**
	 * Check if the task in taskList which has the name of taskName is important or
	 * not. True if it is and false if it is not.
	 * 
	 * @param taskList the name of the list to be get the task from
	 * @param taskName the task which will be checked.
	 * @return boolean indicate if a task is important.
	 */
	public boolean isImportant(String taskList, String taskName) {
		ToDoTask task = getTask(taskList, taskName);
		return task.isImportant();
	}

	/**
	 * Check if the given task is done or not
	 * 
	 * @param taskList the name of the list to be get the task from
	 * @param taskName the task which will be checked.
	 * @return true if the task is done
	 */
	public boolean isDone(String taskList, String taskName) {
		ToDoTask task = getTask(taskList, taskName);
		return task.isDone();
	}

	/**
	 * @author Allen El This method returns the task object for the given list name
	 *         and task name params
	 * @param nameList is the name of list to search the task in.
	 * @param nameTask is the name of the task to search for in nameList
	 * @return the ToDoTask to be get
	 */
	public ToDoTask getTask(String nameList, String nameTask) {
		for (ToDoList list : lists) {
			if (list.getName().equals(nameList)) {
				return list.getTask(nameTask);
			}
		}
		// return null if list not found
		return null;
	}

	/**
	 * Add the task into the list with the nameList.
	 * 
	 * @param nameList String represent the list contains the task
	 * @param newTask  String represent the name of the task
	 * @return true if add operation is success and false if not.
	 */
	public boolean addTask(String nameList, ToDoTask newTask) {
		ToDoList myList = this.getList(nameList);
		if (myList == null) {
			return false;
		} else {
			myList.addTask(newTask);
			return true;
		}
	}

	/**
	 * Author: Quang Vu Edit: Allen El: Create the new task in this method because
	 * the gui should not be handling data, let the controller create new instances
	 * of tasks Purpose: This method will add new Task to the given list
	 * 
	 * @param listName String represent the list contains the task
	 * @param taskName String represent the name of the task
	 * @return true if successfully add to the list
	 */
	public ToDoTask removeTask(String listName, String taskName) {
		for (ToDoList list : lists) {
			if (list.getName().equals(listName)) {
				return list.removeTask(taskName);
			}
		}
		return null;
	}

	/**
	 * Changing important field in task has the name of nameTask in a nameList.
	 * 
	 * @param nameList  the name of the list to be get the task from
	 * @param newTask   the task which will be change the importance.
	 * @param newStatus indicate if the task is importance or not
	 * @return true if successfully change the important field of the task
	 */
	public boolean setImportant(String nameList, String newTask, boolean newStatus) {
		ToDoTask task = getTask(nameList, newTask);
		if (task == null)
			return false;
		task.setImportant(newStatus);
		return true;
	}

	/**
	 * Change the complete status of a task in a nameList
	 * 
	 * @param nameList  the name of the list to be get the task from
	 * @param newTask   the task which will be change the completion status.
	 * @param newStatus the status will be changed to
	 * @return true if successful change the complete status
	 */
	public boolean setDone(String nameList, String newTask, boolean newStatus) {
		ToDoTask task = getTask(nameList, newTask);
		if (task == null)
			return false;
		task.setDone(newStatus);
		return true;

	}

	/**
	 * Check if the task in nameList which has the name of nameTask is done or not.
	 * True if it is and false if it is not.
	 * 
	 * @param nameList the name of the list to be get the task from
	 * @param nameTask the task which will get the done from.
	 * @return boolean indicate if a task is done.
	 */
	public boolean getDone(String nameList, String nameTask) {
		ToDoTask task = getTask(nameList, nameTask);
		if (task == null)
			return false;
		return task.isDone();
	}

	/**
	 * Author: Quang Vu Purpose: This method will rename the given task in
	 * appropriate list
	 * 
	 * @param nameList String represent the name of list contains the given task
	 * @param nameTask the name of the task
	 * @param newName  the new name that user want to change
	 * 
	 * @return true if it successful rename the task and false otherwise
	 */
	public boolean renameTask(String nameList, String nameTask, String newName) {
		ToDoList list = getList(nameList);

		// return false if the list is not found
		if (list == null)
			return false;

		// otherwise found the task
		return list.renameTask(nameTask, newName);
	}

	/**
	 * Purpose: This method change the due date found at list list name task task
	 * name to due date
	 * 
	 * 
	 * @param listName String represent the name of the list contains the task need
	 *                 to change the due date
	 * @param taskName String represent the name of the task need to change the due
	 *                 date
	 * @param dueDate  the new due date
	 * 
	 * @return true if successfully change the due date
	 */
	public boolean setTaskDueDate(String listName, String taskName, String dueDate) {
		ToDoTask taskToChangeDate = getTask(listName, taskName);
		if (taskToChangeDate == null) {
			return false;
		}
		// this causes an error because it wants a time object but right now it is a
		// string
		taskToChangeDate.setDueDate(dueDate);
		return true;
	}

	/**
	 * This method changes the note of the task in the data structure model to the
	 * newNote String parameter
	 * 
	 * @param nameList is the list that the task is located in
	 * @param taskName is the task that is located in the listName
	 * @param note     is the note to set the task to
	 * @return true if the set operation is success and false if it is not
	 */
	public boolean setTaskNote(String nameList, String taskName, String note) {
		ToDoList myList = this.getList(nameList);
		if (myList == null) {
			return false;
		} else {
			if (myList.getTask(taskName) == null) {
				return false;
			}
			myList.getTask(taskName).setNote(note);
			return true;
		}
	}

	/**
	 * Set location field on a task (taskName) in a list (listName)
	 * 
	 * @param listName    the list identifier
	 * @param taskName    the task identifier
	 * @param newLocation the new location string
	 * @return true if the list/task were found and updated
	 */
	public boolean setTaskLocation(String listName, String taskName, String newLocation) {
		for (ToDoList list : lists) {
			if (list.getName().equals(listName)) {
				return list.setTaskLocation(taskName, newLocation);
			}
		}
		return false;
	}

	/**
	 * Call the save method to save the whole application for us. Save the whole
	 * model.
	 */
	public void saveAll() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("save_app.dat");
			File file = new File("save_app.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) { // This error should never happen since save_app.dat is automatically created.
			e.printStackTrace();
		}
	}

	/**
	 * Return the List of ToDoList stored in the model.
	 * 
	 * @return the list of ToDoList
	 */
	public List<ToDoList> getAll() {
		return lists;
	}

	/**
	 * Used only for the view. Set the color number which the task field will have
	 * 
	 * @param val the color number
	 */
	public void setTaskInt(int val) {
		this.currentColorTaskInt = val;
	}

	/**
	 * Used only for the view. Set the color number which the task field will have
	 * 
	 * @return the color number which the task field will have
	 */
	public int getTaskInt() {
		return this.currentColorTaskInt;
	}

	/**
	 * Used only for the view. Set the color number which the list field will have
	 * 
	 * @param val the color number
	 */
	public void setListInt(int val) {
		this.currentColorListInt = val;
	}

	/**
	 * Used only for the view. Set the color number which the list field will have
	 * 
	 * @return the color number which the list field will have
	 */
	public int getListInt() {
		return this.currentColorListInt;
	}

	/**
	 * Change the importance of task in the list of listName with the name of
	 * taskName
	 * 
	 * @param listName  is the list that the task is located in
	 * @param taskName  is the task that is located in the listName
	 * @param important boolean indicate if a task is important or not.
	 */
	public void setTaskImportant(String listName, String taskName, boolean important) {
		for (ToDoList list : lists) {
			if (list.getName().equals(listName)) {
				ToDoTask temp = list.getTask(taskName);
				temp.setImportant(important);
			}
		}
	}

}
