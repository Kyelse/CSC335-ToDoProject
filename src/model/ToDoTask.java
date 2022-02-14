package model;

import java.io.Serializable;
import java.util.Date;

/**
 * The representation of the each task in the to do list. Each task will have
 * its own content, note, the due date, and the location which each respective
 * method of getter and setter.
 * 
 * @author Ofer Greenberg, Quan Nguyen, Quang Vu, Allen David El.
 *
 */
public class ToDoTask implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4310909293974509308L;

	/**
	 * The name of the task
	 */
	private String name;

	/**
	 * The time for the due date.
	 */
	private String dueDate;

	/**
	 * The time which the task is created.
	 */
	private String creationDate;
	/**
	 * Any note which might benefit to the task o
	 */
	private String note;
	/**
	 * The location of which the task is created
	 */
	private String location;

	/**
	 * If this task is important or not
	 */
	private boolean important;

	/**
	 * check if this task is finish or not
	 */
	private boolean complete;

	/**
	 * Constructor. Create an initial ToDoTask object which have no information for
	 * it yet. The default value would be null.
	 */
	public ToDoTask() {
		this.name = null;
		this.dueDate = null;
		this.creationDate = java.time.LocalDate.now().toString() + " " + java.time.LocalTime.now().toString();
		this.note = null;
		this.location = null;
		this.complete = false;
		this.important = false;
	}

	/**
	 * This function will check if the task is important or not
	 * 
	 * @return true if it is important and false otherwise
	 */
	public boolean isImportant() {
		return important;
	}

	/**
	 * This function will set the important of the task
	 * 
	 * @param newStatus
	 * 
	 */
	public void setImportant(boolean newStatus) {
		important = newStatus;
	}

	/**
	 * This function will return true if the task is finish
	 * 
	 * @return true if the task is done and false otherwise
	 */
	public boolean isDone() {
		return complete;
	}

	/**
	 * This function will change the complete field of this object
	 * 
	 * @param state boolean true if the task is finish and false otherwise
	 * 
	 */
	public void setDone(boolean state) {
		complete = state;
	}

	/**
	 * Return the name of the task.
	 * 
	 * @return name of the task
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Return the due date of the task
	 * 
	 * @return the due date of the task
	 */
	public String getDueDate() {
		return this.dueDate;
	}

	/**
	 * Purpose: Return the note for the task.
	 * 
	 * @return location of the task
	 */
	public String getNote() {
		return this.note;
	}

	/**
	 * Return the location where the task is created.
	 * 
	 * @return location where the task is created
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Rename of the task of the task to another name
	 * 
	 * @param name which the user want to change the task to
	 */
	public void rename(String name) {
		this.name = name;
	}

	/**
	 * Set due date of the task to something else. REMINDER: THIS WILL NEED MORE
	 * WORK FROM THE CLASS ABOVE TO TRANSLATE TO A RIGHT TIME OBJECT
	 * 
	 * @param newDueDate the due date which the user want to change the task to
	 */
	public void setDueDate(String newDueDate) {
		this.dueDate = newDueDate;
	}

	/**
	 * Change the note into a new note which the user want to change.
	 * 
	 * @param note the note which the user want to change
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * Change the location to the location passed in
	 * 
	 * @param location the location which will be changed into
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Return creation time
	 * 
	 * @return creation time in type date XXX for now, string
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * To string for printing list on gui listView object.
	 * 
	 * @return the string which represent the task.
	 */
	public String toString() {
		String str = "";
		if (important) {
			str = "***Important!           ";
		}

		if (isDone()) {
			str = str + "(completed)\n" + name + "          	due: " + dueDate + "\n at - " + location + "\n " + note
					+ "\n \n \n";
		} else {
			str = str + "\n" + name + "          	due: " + dueDate + "\n at - " + location + "\n" + note + "\n \n \n";

		}
		return str;
	}
}
