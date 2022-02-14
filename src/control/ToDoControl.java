package control;

import model.ToDoModel;
import model.ToDoTask;
import model.ToDoList;
import java.util.LinkedList;
import java.util.List;

/**
 * The controller for the whole data of the ToDoList. This controller would take
 * in the model as the construction and data. The controller can do function
 * such as addList, addTask, doing different kind of sorting to the ToDoList in
 * the model to support the function for the ToDoView.
 *
 * @author Ofer Greenberg, Quan Nguyen, Quang Vu, Allen David El.
 */

public class ToDoControl {

	/**
	 * The model which the controller hold to do useful method on �t.
	 */
	private ToDoModel model;

	/**
	 * Constructor takes ToDoModel object and stores it in the private attribute.
	 * The controller interacts between the gui and and the data in order to process
	 * the data based on how the controller is used
	 * 
	 * @param model the model which is a ToDoModel
	 */
	public ToDoControl(ToDoModel model) {
		this.model = model;
	}

	/**
	 * Author: Quang Vu Purpose: This method will add new list to the model
	 * 
	 * @param name name of the new list
	 */
	public void addList(String name) {
		model.addList(name);
	}

	/**
	 * Remove a list with the same name as the id. If there is not a list with that
	 * name, return null
	 * 
	 * @param id the name of the list to be removed
	 * @return the ToDoList has the same name as the id or null
	 */
	public ToDoList removeList(String id) {
		return this.model.removeList(id);
	}

	/**
	 * Rename a list in the model which have the same name as listName to the new
	 * name. Return true if success and false if not.
	 * 
	 * @param listName the name of the list to be changed
	 * @param newName  the new name to be changed to.
	 * @return indicate if the rename successful or not
	 */
	public boolean renameList(String listName, String newName) {
		return model.renameList(listName, newName);
	}

	/**
	 * Get the ToDoList from the model which have the name.
	 * 
	 * @param name the name of the ToDoList to be search
	 * @return the ToDoList which have the name
	 */
	public ToDoList getList(String name) {
		return model.getList(name);
	}

	/**
	 * Return a task in a list which have the name of nameList, that task will also
	 * have the same name of nameTask.
	 * 
	 * @param nameList the name of the list to be get the task from
	 * @param nameTask the task which will be get.
	 * @return the task to be searched.
	 */
	public ToDoTask getTask(String nameList, String nameTask) {
		return model.getTask(nameList, nameTask);
	}

	/**
	 * Check if the task in nameList which has the name of nameTask is important or
	 * not. True if it is and false if it is not.
	 * 
	 * @param nameList the name of the list to be get the task from
	 * @param nameTask the task which will be checked.
	 * @return boolean indicate if a task is important.
	 */
	public boolean isImportant(String nameList, String nameTask) {
		return model.isImportant(nameList, nameTask);
	}

	/**
	 * Check if the given task is done or not
	 * 
	 * @param nameList the name of the list to be get the task from
	 * @param nameTask the task which will be checked.
	 * @return true if the task is done
	 */
	public boolean isDone(String nameList, String nameTask) {
		return model.isDone(nameList, nameTask);
	}

	/**
	 * Changing important field in task has the name of nameTask in a nameList.
	 * 
	 * @param nameList  the name of the list to be get the task from
	 * @param nameTask  the task which will be change the importance.
	 * @param newStatus indicate if the task is importance or not
	 * @return true if successfully change the important field of the task
	 */
	public boolean setImportant(String nameList, String nameTask, boolean newStatus) {
		return model.setImportant(nameList, nameTask, newStatus);
	}

	/**
	 * Changing done field in task has the name of nameTask in a nameList.
	 * 
	 * @param nameList  the name of the list to be get the task from
	 * @param nameTask  the task which will be change the done.
	 * @param newStatus indicate if the task is done or not
	 * @return true if successfully change the done field of the task
	 */
	public boolean setDone(String nameList, String nameTask, boolean newStatus) {
		return model.setDone(nameList, nameTask, newStatus);
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
		return model.getDone(nameList, nameTask);
	}

	/**
	 * Author: Quang Vu Edit: Allen El: Create the new task in this method because
	 * the gui should not be handling data, let the controller create new instances
	 * of tasks Purpose: This method will add new Task to the given list
	 * 
	 * @param nameList String represent the list contains the task
	 * @param newTask  String represent the name of the task
	 * @return true if successfully add to the list
	 */
	public boolean addTask(String nameList, String newTask) {
		ToDoTask task = new ToDoTask();
		task.rename(newTask);
		return model.addTask(nameList, task);
	}

	/**
	 * Remove a task in a list which have the name of nameList, that task will also
	 * have the same name of nameTask.
	 * 
	 * @param listName the name of the list to be get the task from
	 * @param taskName the task which will be removed.
	 * @return the ToDoTask object
	 */
	public ToDoTask removeTask(String listName, String taskName) {
		return model.removeTask(listName, taskName);
	}

	/**
	 * Rename a task in a list which have the name of nameList, that task will also
	 * have the same name of nameTask into a newName
	 * 
	 * @param listName    identifier of list
	 * @param taskName    identifier of task
	 * @param newTaskName new name for the task
	 * @return true if task was found and name updated
	 */
	public boolean renameTask(String listName, String taskName, String newTaskName) {
		return model.renameTask(listName, taskName, newTaskName);
	}

	/**
	 * Author: Quang Vu Purpose: This method change the due date found at list list
	 * name task task name to due date
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
		return model.setTaskDueDate(listName, taskName, dueDate);
	}

	/**
	 * @author Allen El this method changes the note of the task in the data
	 *         structure model to the newNote String parameter
	 * @param listName is the list that the task is located in
	 * @param taskName is the task that is located in the listName
	 * @param newNote  is the newNote to set the task to
	 *
	 */
	public void setTaskNote(String listName, String taskName, String newNote) {
		ToDoTask task = model.getTask(listName, taskName);
		task.setNote(newNote);
	}

	/**
	 * @author Allen El This method swaps the taskName task with the task directly
	 *         below it(closer to list size index) to represent a lower priority of
	 *         the task
	 * @param taskName is the name of the task to move down in the list
	 * @param listName is the name of the list to move the task in down
	 */
	public void moveDown(String taskName, String listName) {
		ToDoList change = model.getList(listName);
		// only move the task down if it is not already the lowest task
		if (!(change.getList().get(change.getList().size() - 1).getName().equals(taskName))) {
			// iterate through the list and swap the task down one index towards the list
			// size position
			for (int i = 0; i < change.getList().size(); i++) {
				ToDoTask temp = change.getList().get(i + 1);
				ToDoTask behindTemp = change.getList().get(i);
				if (behindTemp.getName().equals(taskName)) {
					change.getList().remove(i);
					change.getList().add(i, temp);
					change.getList().remove(i + 1);
					change.getList().add(i + 1, behindTemp);
					break;
				}
			}
		}
	}

	/**
	 * @author Allen El This method swaps the taskName task with the task directly
	 *         above it(closer to 0 index) to represent a higher priority of the
	 *         task
	 * @param taskName is the name of the task to move up in the list
	 * @param listName is the name of the list to move the task in up
	 */
	public void moveUp(String taskName, String listName) {
		ToDoList change = model.getList(listName);
		// only move the task up if it is not already at the top
		if (!(change.getList().get(0).getName().equals(taskName))) {
			// iterate through the list and swap the task up one index towards the 0
			// position
			for (int i = 1; i < change.getList().size(); i++) {
				ToDoTask temp = change.getList().get(i - 1);
				ToDoTask behindTemp = change.getList().get(i);
				if (behindTemp.getName().equals(taskName)) {
					change.getList().remove(i);
					change.getList().add(i, temp);
					change.getList().remove(i - 1);
					change.getList().add(i - 1, behindTemp);
					break;
				}
			}
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
		return model.setTaskLocation(listName, taskName, newLocation);
	}

	/**
	 * @author Allen El This method sorts the name list param by the whatToSort by
	 *         field
	 * @param nameList   list to sort String
	 * @param whatToSort sort by field String
	 * @return true or false (not sure why)
	 */
	public boolean sortTask(String nameList, String whatToSort) {
		ToDoList temp = model.getList(nameList);
		if (temp == null) {
			return false;
		}

		if (whatToSort.equals("date")) {
			dateSort(nameList, temp);
		} else if (whatToSort.equals("creation")) {
			creationSort(nameList, temp);
		} else if (whatToSort.equals("name")) {
			nameSort(nameList, temp);
		} else if (whatToSort.equals("done")) {
			doneSort(nameList, temp);
		} else if (whatToSort.equals("important")) {
			importantSort(nameList, temp);
		}
		return true;
	}

	/**
	 * Sort all task in a list which have the name of nameList in the order of the
	 * dueDate.
	 * 
	 * @param nameList The name of the list to be sorted
	 * @param toDoList The list which will have all sorted object
	 */
	private void dateSort(String nameList, ToDoList toDoList) {
		ToDoList toDOlist = getList(nameList);
		List<ToDoTask> list = toDOlist.getList();
		for (int i = 0; i < list.size() - 1; i++) {
			ToDoTask min = list.get(i);
			int index = i;
			for (int j = i + 1; j < list.size(); j++) {
				ToDoTask task = list.get(j);
				if (min.getDueDate().compareTo(task.getDueDate()) >= 0) {
					index = j;
					min = task;
				}
			}
			ToDoTask temp = list.remove(i);
			list.add(i, min);
			list.remove(index);
			list.add(index, temp);

		}

	}


	/**
	 * Sort all task in a list which have the name of nameList in the order of the
	 * creation date.
	 * 
	 * @param nameList The name of the list to be sorted
	 * @param toDoList The list which will have all sorted object
	 */
	private void creationSort(String nameList, ToDoList toDoList) {
		ToDoList toDOlist = getList(nameList);
		List<ToDoTask> list = toDOlist.getList();
		for (int i = 0; i < list.size() - 1; i++) {
			ToDoTask min = list.get(i);
			int index = i;
			for (int j = i + 1; j < list.size(); j++) {
				ToDoTask task = list.get(j);
				if (min.getCreationDate().compareTo(task.getCreationDate()) >= 0) {
					index = j;
					min = task;
				}
			}
			ToDoTask temp = list.remove(i);
			list.add(i, min);
			list.remove(index);
			list.add(index, temp);

		}
	}

	/**
	 * Sort all task in a list which have the name of nameList in the order of the
	 * name of each task.
	 * 
	 * @param nameList The name of the list to be sorted
	 * @param toDoList The list which will have all sorted object
	 */
	private void nameSort(String nameList, ToDoList toDoList) {

		ToDoList toDOlist = getList(nameList);
		List<ToDoTask> list = toDOlist.getList();
		for (int i = 0; i < list.size() - 1; i++) {
			ToDoTask min = list.get(i);
			int index = i;
			for (int j = i + 1; j < list.size(); j++) {
				ToDoTask task = list.get(j);
				if (min.getName().compareTo(task.getName()) >= 0) {
					index = j;
					min = task;
				}
			}
			ToDoTask temp = list.remove(i);
			list.add(i, min);
			list.remove(index);
			list.add(index, temp);

		}
	}

	/**
	 * Sort all task in a list which have the name of nameList in the order of the
	 * done of each task.
	 * 
	 * @param nameList The name of the list to be sorted
	 * @param toDoList The list which will have all sorted object
	 */
	private void doneSort(String nameList, ToDoList toDoList) {
		List<ToDoTask> list = toDoList.getList();
		List<ToDoTask> doneTask = new LinkedList<ToDoTask>();
		List<ToDoTask> doomTask = new LinkedList<ToDoTask>();
		for (ToDoTask task : list) {
			if (task.isDone()) {
				doneTask.add(task);
			} else {
				doomTask.add(task);
			}

		}
		int curIndex = 0;
		for (ToDoTask task : doomTask) {
			list.remove(curIndex);
			list.add(curIndex, task);
			curIndex++;
		}
		for (ToDoTask task : doneTask) {
			list.remove(curIndex);
			list.add(curIndex, task);
			curIndex++;
		}
	}

	/**
	 * Sort all task in a list which have the name of nameList in the order of the
	 * important of each task.
	 * 
	 * @param nameList The name of the list to be sorted
	 * @param toDoList The list which will have all sorted object
	 */
	private void importantSort(String nameList, ToDoList toDoList) {
		List<ToDoTask> list = toDoList.getList();
		List<ToDoTask> importantTask = new LinkedList<ToDoTask>();
		List<ToDoTask> doomTask = new LinkedList<ToDoTask>();
		for (ToDoTask task : list) {
			if (!task.isImportant()) {
				doomTask.add(task);
			} else {
				importantTask.add(task);
			}

		}
		int curIndex = 0;
		for (ToDoTask task : importantTask) {
			list.remove(curIndex);
			list.add(curIndex, task);
			curIndex++;
		}
		for (ToDoTask task : doomTask) {
			list.remove(curIndex);
			list.add(curIndex, task);
			curIndex++;
		}
	}

	/**
	 * Return the List of ToDoList stored in the model.
	 * 
	 * @return the list of ToDoList
	 */
	public List<ToDoList> getAll() {
		return model.getAll();
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
		model.setTaskImportant(listName, taskName, important);
	}

}

