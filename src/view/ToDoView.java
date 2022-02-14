package view;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import control.ToDoControl;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.ToDoList;
import model.ToDoModel;
import model.ToDoTask;

/**
 * This file will provide implementation for the GUI part of ToDo application
 * using JavaFX. The application would have multiple functionality such as add
 * task, add list, change due/date/location and save on close which all
 * interactive through the gui.
 * 
 * @author Quang Vu, Ofer Greenberg, Quan Nguyen, Allen David El
 */
public class ToDoView extends Application {


	/**
	 * The size of the application
	 */
	private static final int SCREEN_SIZE = 800;
	/**
	 * The controller for the application
	 */
	private ToDoControl control;
	/**
	 * The model which contains for the application
	 */
	private ToDoModel model;
	/**
	 * GridPane
	 */
	private GridPane window = new GridPane();
	/**
	 * The whole scene for the application
	 */
	private Scene scene = new Scene(window, 600, 600);
	/**
	 * A stage for the GUI
	 */
	private Stage stage = new Stage();

	/**
	 * The GridPane which display stuff
	 */
	private GridPane listPane;
	/**
	 * List view would display every ToDoList on the GUI
	 */
	private ListView<ToDoList> listView;
	/**
	 * Add button
	 */
	private Button addList = new Button("+");
	/**
	 * Remove button
	 */
	private Button removeList = new Button("Remove");
	/**
	 * Rename button
	 */
	private Button renameList = new Button("Rename");
	/**
	 * Select button
	 */
	private Button selectList = new Button("  Load  ");
	/**
	 * The grid pane which will contains task and show it
	 */
	private GridPane taskPane;
	/**
	 * The ListView object which will display the whole task view side
	 */
	private ListView<ToDoTask> taskView;
	/**
	 * Add button
	 */
	private Button addTask = new Button("+");
	/**
	 * Remove button
	 */
	private Button removeTask = new Button("Remove");
	/**
	 * Rename button
	 */
	private Button renameTask = new Button("Edit");
	/**
	 * Mark Done
	 */
	private Button toggleDoneButton = new Button("Mark Done/Undone");

	/**
	 * Mark Done
	 */
	private Button hideDone = new Button("Hide/Show Done Tasks");
	/**
	 * Indicate if we should hide the completed task or show it
	 */
	private boolean hide = false;

	/**
	 * Button for sort task up //move the task up
	 */
	private Button sortTaskUp = new Button("\u2191");
	/**
	 * Button for sort task down
	 */
	private Button sortTaskDown = new Button("\u2193");
	/**
	 * Button for sort by alpha
	 */
	private Button sortByAlph = new Button("Sort By ABC");
	/**
	 * Button for sort by date
	 */
	private Button sortByDate = new Button("Sort By Date");

	/**
	 * Button for sort by creation date
	 */
	private Button sortByCreation = new Button("Sort By Creation Date");

	/**
	 * Button for sort by the importance
	 */
	private Button sortByImportance = new Button("Sort By Importance ");   
	/**
	 * Button for the color changing for task view
	 */
	Button colorMode = new Button();
	/**
	 * Button for the color changing of listView
	 */
	Button colorModeCustom = new Button();
	/**
	 * The color for the dark mode.
	 */
	private final String DarkMode1 = "#32373b"; // list background even
	/**
	 * The color pool for the change color of pool the list and the task
	 */
	private final String[] colorChangePool = { "#32373b", "#b1dafa", "#9C2998", "#EC3B03", "#BE256B", "#C1B75C",
			"#E1A0F4", "#DF6274", "#6579E8", "#16414D", "#FF7F57", "#78D23F", "#973969" };
	/**
	 * Number of the current color of task side in the color pool
	 */
	private int currentColorTaskInt;
	/**
	 * Number of the current color of list side in the color pool
	 */
	private int currentColorListInt;

	/**
	 * A menu bar
	 */
	private MenuBar menuBar = new MenuBar();
	/**
	 * A field in the pop up of the menu bar indicate a File
	 */
	private Menu mainmMenu = new Menu("File");
	/**
	 * A field in the pop up of the menu bar indicate a List
	 */
	private Menu listMenu = new Menu("Lists");
	/**
	 * A field in the pop up of the menu bar indicate a Tasks
	 */
	private Menu taskMenu = new Menu("Tasks");
	/**
	 * A field in the pop up of the menu bar indicate a Views
	 */
	private Menu colorMenu = new Menu("View");

	/**
	 * Start the application with the Stage arguments.
	 * 
	 * @param args state to show our application
	 */
	@Override
	public void start(Stage args) throws Exception {
		loadSave();

		// set up
		setLeftRightPane();
		setMenu();
		setWindow();
		setStage();
		stage.show();
		if (listView.getSelectionModel().getSelectedItem() != null) {
			control.sortTask(listView.getSelectionModel().getSelectedItem().getName(), "important");
			displayTasks();
		}
		this.displayList();

		// events
		addList.setOnAction(click -> addList()); // lists
		removeList.setOnAction(click -> removeList());
		renameList.setOnAction(click -> renameList());
		selectList.setOnAction(click -> selectList());
		addTask.setOnAction(click -> addTask()); // tasks
		removeTask.setOnAction(click -> removeTask());
		renameTask.setOnAction(click -> renameTask());
		toggleDoneButton.setOnAction(click -> toggleDone());
		hideDone.setOnAction(click -> hideDone());
		sortTaskUp.setOnAction(click -> moveTaskUp()); // ordering
		sortTaskDown.setOnAction(click -> moveTaskDown());
		sortByAlph.setOnAction(click -> sortAlph());
		sortByDate.setOnAction(click -> sortDate());
		sortByCreation.setOnAction(click -> creationSort());
		sortByImportance.setOnAction(click -> importantSort()); // ----------------------->TODO	
		colorMode.setOnAction(click -> colorMode()); 		// color modes
		colorModeCustom.setOnAction(click -> colorModeCustom());


	} 

	/**
	 * Load the save file information (i.e the model) if there is any save file
	 * exists. If the save file not exist, create a new model for the view.
	 */
	private void loadSave() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("save_app.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ToDoModel myModel = null;
			try {
				myModel = (ToDoModel) ois.readObject();
			} catch (ClassNotFoundException e) { // should have never happen since save_game.dat would have at least the
													// first state of the board
				e.printStackTrace();
			}
			this.model = myModel;
			ois.close();
		} catch (IOException e) {
			this.model = new ToDoModel();
			this.currentColorListInt = 0;
			this.currentColorTaskInt = 0;
		}
		control = new ToDoControl(model);
		this.currentColorListInt = model.getListInt();
		this.currentColorTaskInt = model.getTaskInt();
	}

	/**
	 * Setup all of the listPane for populating the GUI.
	 */
	private void setLeftRightPane() {

		// create left and right panes
		// for list and tasks respectively
		listPane = new GridPane(); // left
		listPane.setPadding(new Insets(7));
		taskPane = new GridPane();
		taskPane.setPadding(new Insets(7)); // right
		HBox panelRight = new HBox(1);
		HBox panelLeft = new HBox(1);

		// styling
		listPane.setStyle("-fx-background-color: #484C5A");
		taskPane.setStyle("-fx-background-color: #575D71");

		// adding rows and columns to grid
		RowConstraints row = new RowConstraints();
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		ColumnConstraints column4 = new ColumnConstraints();
		column1.setPercentWidth(50);
		column1.setHalignment(HPos.LEFT);
		row.setPrefHeight(40);
		column2.setPercentWidth(50);
		column2.setHalignment(HPos.LEFT);

		// create list label
		Label listLabel = new Label("To Do Lists");
		listLabel.setTextFill(Color.WHITE);
		listLabel.setFont(new Font("Arial", 24));

		// create task label
		Label taskLabel = new Label("To Do Tasks");
		taskLabel.setTextFill(Color.WHITE);
		taskLabel.setFont(new Font("Arial", 24));

		// adding labels to panes at location 0,0
		listPane.getRowConstraints().add(row); // left
		listPane.getColumnConstraints().add(column1);
		listPane.add(listLabel, 0, 0, 1, 1);
		taskPane.getRowConstraints().add(row); // right
		taskPane.getColumnConstraints().add(column2);
		taskPane.getColumnConstraints().add(column2);
		taskPane.add(taskLabel, 0, 0, 1, 1);

		// edit listview style and button style
		editStyle();

		// adding columns for buttons
		column3.setPercentWidth(20);
		column3.setHalignment(HPos.RIGHT);
		column4.setPercentWidth(12);
		column4.setHalignment(HPos.RIGHT);

		// list buttons
		listPane.getColumnConstraints().add(column3);
		listPane.getColumnConstraints().add(column3);
		listPane.getColumnConstraints().add(column3);
		listPane.getColumnConstraints().add(column3);
		panelLeft.setAlignment(Pos.CENTER_RIGHT);
		panelLeft.setPadding(new Insets(1));
		panelLeft.getChildren().addAll(addList, removeList, renameList, selectList);
		listPane.getColumnConstraints().add(column3);
		listPane.add(panelLeft, 1, 0, 5, 1);

		// task buttons
		taskPane.getColumnConstraints().add(column4);
		taskPane.getColumnConstraints().add(column4);
		taskPane.getColumnConstraints().add(column4);
		panelRight.setAlignment(Pos.CENTER_RIGHT);
		panelRight.setPadding(new Insets(1));
		panelRight.getChildren().addAll(addTask, removeTask, renameTask, toggleDoneButton, hideDone);
		taskPane.add(panelRight, 1, 0, 4, 1);

		// button events on start
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(80);
		listPane.getRowConstraints().add(row2); // left -- listView
		listPane.add(listView, 0, 1, 6, 1);
		taskPane.getRowConstraints().add(row2); // right -- taskView ListView
		taskPane.add(taskView, 0, 1, 5, 1);

		// sort/ordering buttons
		GridPane sortingPanel = new GridPane();
		ColumnConstraints gap = new ColumnConstraints();
		ColumnConstraints col = new ColumnConstraints();
		gap.setMinWidth(10);
		gap.setMaxWidth(10);
		col.setMinWidth(150);
		col.setMinWidth(150);
		
		sortingPanel.getRowConstraints().addAll(row, row, row);
		sortingPanel.getColumnConstraints().add(col);
		sortingPanel.getColumnConstraints().add(gap);
		sortingPanel.getColumnConstraints().add(col);
		sortingPanel.add(sortByCreation, 0,0,1,1);
		sortingPanel.add(sortByAlph, 0, 1,1,1);		
		sortingPanel.add(sortByDate, 2, 0,1,1);
		sortingPanel.add(sortByImportance, 2,1,1,1) ;
		taskPane.add(sortingPanel, 0,2,4,3);			
		
		taskPane.getColumnConstraints().add(column4);
		taskPane.add(sortTaskUp, 3, 1, 4, 1);
		taskPane.add(sortTaskDown, 3, 1, 4, 7);

		// color modes
		colorMode.setPrefWidth(200);
		colorMode.setText("Task");
		colorModeCustom.setPrefWidth(200);
		colorModeCustom.setText("List");
		taskPane.getRowConstraints().add(row);
		taskPane.add(colorMode, 4, 2, 1, 1);
		taskPane.add(colorModeCustom, 3, 2, 1, 1);
	}

	/**
	 * This function will basically edit the style and format every button in the
	 * view of the GUI to be neat and aesthetically pleasing.
	 */
	private void editStyle() {
		listView = new ListView<ToDoList>();
		taskView = new ListView<ToDoTask>();
		// get the color change button to b the right color.
		colorMode.setStyle("-fx-background-color: " + this.colorChangePool[this.currentColorTaskInt]);
		colorModeCustom.setStyle("-fx-background-color: " + this.colorChangePool[this.currentColorListInt]);
		colorMode.setTextFill(Color.WHITE);
		colorModeCustom.setTextFill(Color.WHITE);

		// set the listView and rightView to the right color also.
		listView.setStyle("-fx-control-inner-background: " + this.colorChangePool[this.currentColorListInt]
				+ "; -fx-box-border: black;");
		taskView.setStyle("-fx-control-inner-background: " + this.colorChangePool[this.currentColorTaskInt]
				+ "; -fx-box-border: black;");
		// formatting all of the button
		sortByAlph.setPrefWidth(130);
		sortByAlph.setTextAlignment(TextAlignment.LEFT);

		sortByDate.setPrefWidth(150);

		sortByDate.setTextAlignment(TextAlignment.LEFT);

		sortByCreation.setPrefWidth(150);

		sortByCreation.setTextAlignment(TextAlignment.LEFT);
		sortByImportance.setPrefWidth(150);
		sortByImportance.setTextAlignment(TextAlignment.LEFT);
		
		setButtonStyle(addList);
		setButtonStyle(removeList);
		setButtonStyle(renameList);
		setButtonStyle(selectList);
		setButtonStyle(addTask);
		setButtonStyle(removeTask);
		setButtonStyle(toggleDoneButton);
		setButtonStyle(hideDone);
		setButtonStyle(renameTask);
		setButtonStyle(sortTaskUp);
		setButtonStyle(sortTaskDown);
		setButtonStyle(sortByAlph);
		setButtonStyle(sortByDate);
		setButtonStyle(sortByCreation);	
		setButtonStyle(sortByImportance);
	}

	/**
	 * Takes a button as a parameter and apply the style standard for this GUI
	 * 
	 * @param b the button
	 */
	private void setButtonStyle(Button b) {
		b.setTextFill(Color.WHITE);
		b.setStyle("-fx-background-color: #34393b");
	}

	/**
	 * set up the window object
	 */
	private void setWindow() {
		RowConstraints row_bar = new RowConstraints();
		row_bar.setPercentHeight(3);
		window.getRowConstraints().add(row_bar);
		window.add(menuBar, 0, 0, 2, 1);

		RowConstraints row = new RowConstraints();
		row.setPercentHeight(100);
		window.getRowConstraints().add(row);

		ColumnConstraints column = new ColumnConstraints();

		column.setPercentWidth(35);
		window.getColumnConstraints().add(column);
		window.add(listPane, 0, 1, 1, 1);

		column = new ColumnConstraints();
		column.setPercentWidth(65);
		window.getColumnConstraints().add(column);
		window.add(taskPane, 1, 1, 1, 1);
	}

	/**
	 * Set up menu items and its function for each button on the popUp.
	 */
	private void setMenu() {

		// main menu
		MenuItem menu_reset = new MenuItem("Reset (delete all)");
		MenuItem menu_save_and_close = new MenuItem("Save and Close");
		mainmMenu.getItems().add(menu_reset);
		mainmMenu.getItems().add(menu_save_and_close);

		// list menu
		MenuItem addList_M = new MenuItem("Add List");
		MenuItem removeList_M = new MenuItem("Remove List");
		MenuItem renameList_M = new MenuItem("Rename List");
		listMenu.getItems().add(addList_M);
		listMenu.getItems().add(removeList_M);
		listMenu.getItems().add(renameList_M);

		// task menu
		MenuItem addTask_M = new MenuItem("Add Task");
		MenuItem removeTask_M = new MenuItem("Remove Task");
		MenuItem renameTask_M = new MenuItem("Rename Task");
		taskMenu.getItems().add(addTask_M);
		taskMenu.getItems().add(removeTask_M);
		taskMenu.getItems().add(renameTask_M);

		// view menu (colorMenu)
		MenuItem resetColors_M = new MenuItem("Restore Default");
		colorMenu.getItems().add(resetColors_M);

		// events
		menu_reset.setOnAction(click -> resetModel());
		menu_save_and_close.setOnAction(click -> stop());
		addList_M.setOnAction(click -> addList()); // lists
		removeList_M.setOnAction(click -> removeList());
		renameList_M.setOnAction(click -> renameList());
		addTask_M.setOnAction(click -> addTask()); // tasks
		removeTask_M.setOnAction(click -> removeTask());
		renameTask_M.setOnAction(click -> renameTask());
		resetColors_M.setOnAction(click -> resetColors()); // color modes

		// adding style

		// adding to menu bar
		menuBar.getMenus().add(mainmMenu);
		menuBar.getMenus().add(listMenu);
		menuBar.getMenus().add(taskMenu);
		menuBar.getMenus().add(colorMenu);

	}

	/**
	 * set up the Stage object
	 */
	private void setStage() {
		stage.setScene(scene);
		stage.setHeight(SCREEN_SIZE);
		stage.setWidth(SCREEN_SIZE * 1.5);
		stage.setTitle("To Do");
		stage.setResizable(true);
	}

	/**
	 * This will call the sort by date in the controller if the SortDate button is
	 * pressed
	 */
	private void sortDate() {
		if (listView.getSelectionModel().getSelectedItem() != null) {
			control.sortTask(listView.getSelectionModel().getSelectedItem().getName(), "date");
			displayTasks();

		}
	}

	/**
	 * This will call the sort by alphabet in the controller if the SortABC button
	 * is pressed
	 */
	private void sortAlph() {
		if (listView.getSelectionModel().getSelectedItem() != null) {
			control.sortTask(listView.getSelectionModel().getSelectedItem().getName(), "name");
			displayTasks();

		}
	}

	/**
	 * This will call the sort by creation date in the controller if the
	 * SortCreation button is pressed
	 */
	private void creationSort() {
		if (listView.getSelectionModel().getSelectedItem() != null) {
			control.sortTask(listView.getSelectionModel().getSelectedItem().getName(), "creation");
			displayTasks();
		}
	}

	
	/**
	 * This will call the sort by creation date in the controller if the
	 * SortImportant button is pressed
	 */
	private void importantSort() {
		if(listView.getSelectionModel().getSelectedItem() != null) {
			control.sortTask(listView.getSelectionModel().getSelectedItem().getName(), "important");
			displayTasks();	
		}
	}
  
  /**
	 * Perform the moveTaskDown option if the corresponding button is pressed
	 */
	private void moveTaskDown() {

		// only move task if a task is actually selected on the gui
		if (taskView.getSelectionModel().getSelectedItem() != null
				&& listView.getSelectionModel().getSelectedItem() != null) {
			control.moveDown(taskView.getSelectionModel().getSelectedItem().getName(),
					listView.getSelectionModel().getSelectedItem().getName());
			displayTasks();
		}
	}

	/**
	 * Perform the moveTaskUp option if the corresponding button is pressed
	 */
	private void moveTaskUp() {

		// only move task if a task is actually selected on the gui
		if (taskView.getSelectionModel().getSelectedItem() != null
				&& listView.getSelectionModel().getSelectedItem() != null) {
			control.moveUp(taskView.getSelectionModel().getSelectedItem().getName(),
					listView.getSelectionModel().getSelectedItem().getName());
			displayTasks();
		}

	}

	/**
	 * Perfrom the add task if the corresponding button is pressed. This will also
	 * ask for the information for the task.
	 */
	@SuppressWarnings("unlikely-arg-type")
	private void addTask() {
		// get the list
		ToDoList current = listView.getSelectionModel().getSelectedItem();
		if (current == null) {
			return;
		}

		String listName = current.getName();

		// setting dialog box
		TextInputDialog inputNameDialog = new TextInputDialog();
		GridPane input = new GridPane();

		// fields to input new task for currently displayed list
		TextField newTaskName = new TextField("Task Name");
		input.add(newTaskName, 1, 1);
		DatePicker datePicker = new DatePicker();
		input.add(datePicker, 1, 2);
		TextField newTaskLocation = new TextField("Task Location");
		input.add(newTaskLocation, 1, 3);
		TextField newTaskNote = new TextField("Task Note");
		input.add(newTaskNote, 1, 4);
		CheckBox important = new CheckBox("Important");
		input.add(important, 1, 5);

		inputNameDialog.setHeaderText("(" + listName + " list) New Task: ");
		inputNameDialog.getDialogPane().setContent(input);
		inputNameDialog.showAndWait();
		inputNameDialog.setResizable(true);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String taskName = newTaskName.getText();
		LocalDate pDate = datePicker.getValue();
		String date = datePicker.getEditor().getText();
		if (pDate != null)
			date = formatter.format(pDate);
		String location = newTaskLocation.getText();
		String note = newTaskNote.getText();
		boolean importantFlag = important.isSelected();

		// make sure that important fields such as the name is being entered.
		if (inputNameDialog.getResult() != null && (taskName.equals("Task Name") || date.equals("")
				|| newTaskLocation.equals("Task Location") || newTaskNote.equals("Task Note"))) {
			Alert alert = new Alert(AlertType.ERROR, "Please fill in all the fields");
			alert.showAndWait();
			// alert user to fill all fields or exit/cancel the redo
			addTask();
			return;
		}

		// update model
		if (inputNameDialog.getResult() == null) {
			return;
		}
		control.addTask(listName, taskName);
		control.setTaskDueDate(listName, taskName, date);
		control.setTaskLocation(listName, taskName, location);
		control.setTaskNote(listName, taskName, note);
		control.setTaskImportant(listName, taskName, importantFlag);
		if (listView.getSelectionModel().getSelectedItem() != null) {
		control.setImportant(listName, taskName, importantFlag);
			if(listView.getSelectionModel().getSelectedItem() != null) {
				control.sortTask(listView.getSelectionModel().getSelectedItem().getName(), "important");
				displayTasks();
			}
		displayTasks(listName);
		}
	}
		

	/**
	 * Perfrom the remove task if the corresponding button is pressed. T
	 */
	private void removeTask() {

		ToDoTask currentTask = taskView.getSelectionModel().getSelectedItem();
		ToDoList currentList = listView.getSelectionModel().getSelectedItem();

		if (currentTask != null && currentList != null) {
			control.removeTask(currentList.getName(), currentTask.getName());
		}
		displayTasks();
	}

	/**
	 * @author Allen
	 * @author Ofer This method, based on ofer's renameList method, renames the
	 *         selected task in the selected list. It is based on ofers rename list
	 *         method with slight adjustments for the task rename process.
	 */
	private void renameTask() {
		// load task
		ToDoTask currentTask = taskView.getSelectionModel().getSelectedItem();
		ToDoList currentList = listView.getSelectionModel().getSelectedItem();

		if (currentTask == null || currentList == null || !currentList.getList().contains(currentTask)) {
			return;
		}

		String nameTask = currentTask.getName();
		String nameList = currentList.getName();

		// setting dialog box
		TextInputDialog inputNameDialog = new TextInputDialog();
		GridPane input = new GridPane();

		// fields to input new task for currently displayed list
		TextField newTaskName = new TextField(currentTask.getName());
		input.add(newTaskName, 1, 1);
		DatePicker datePicker = new DatePicker();
		datePicker.getEditor().setText(currentTask.getDueDate());
		input.add(datePicker, 1, 2);
		TextField newTaskLocation = new TextField(currentTask.getLocation());
		input.add(newTaskLocation, 1, 3);
		TextField newTaskNote = new TextField(currentTask.getNote());
		input.add(newTaskNote, 1, 4);
		CheckBox important = new CheckBox("Important");
		important.setSelected(control.isImportant(nameList, nameTask));
		input.add(important, 1, 5);

		inputNameDialog.setHeaderText("(" + nameList + " list) Edit Task: ");
		inputNameDialog.getDialogPane().setContent(input);
		inputNameDialog.showAndWait();
		inputNameDialog.setResizable(true);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String taskName = newTaskName.getText();
		LocalDate pDate = datePicker.getValue();
		String date = datePicker.getEditor().getText();
		if (pDate != null)
			date = formatter.format(pDate);
		String location = newTaskLocation.getText();
		String note = newTaskNote.getText();
		boolean importantFlag = important.isSelected();

		// update model
		if (inputNameDialog.getResult() == null) {
			return;
		}
		if ((!taskName.equals(""))) {
			control.renameTask(nameList, nameTask, taskName);
			control.setTaskDueDate(nameList, taskName, date);
			control.setTaskLocation(nameList, taskName, location);
			control.setTaskNote(nameList, taskName, note);
			control.setImportant(nameList, taskName, importantFlag);
		}

		displayTasks(nameList);
	}

	/**
	 * Add a new list into the listView if the corresponding button is being
	 * pressed. Asking for the user he name of the list.
	 * 
	 * @author Allen El
	 */
	private void addList() {
		TextInputDialog inputNameDialog = new TextInputDialog();
		inputNameDialog.setHeaderText("List name: ");
		inputNameDialog.showAndWait();
		String name = (inputNameDialog.getEditor().getText());

		// check empty list name
		if (name.equals("")) {
			Alert emptyTitle = new Alert(AlertType.WARNING);
			emptyTitle.setContentText("Title Can Not Be Empty");
			emptyTitle.setHeaderText("Empty Title !");
			emptyTitle.show();

			// check for duplicate list names
		} else if (control.getList(name) != null) {
			displayTasks(name);

			// add new list
		} else {
			control.addList(name);
			listView.getItems().add(control.getList(name));
			taskView.getItems().clear();
		}
	}

	/**
	 * Removes current tasks presented and loads requested list
	 */
	private void displayTasks() {
		ToDoList current = listView.getSelectionModel().getSelectedItem();
		if (current == null || current.getName() == null || control.getList(current.getName()).getList() == null) {
			return;
		}

		taskView.getItems().clear();
		if (!hide) {
			taskView.getItems().addAll(control.getList(current.getName()).getList());
		} else {
			List<ToDoTask> tempList = control.getList(current.getName()).getList();
			for (ToDoTask task : tempList) {
				if (!task.isDone()) {
					taskView.getItems().add(task);
				}
			}
		}
	}

	/**
	 * Overloading displayTasks (). This function will basically load the list of
	 * listName and display all of the task it hold.
	 * 
	 * @param listName the name of the list which its task will be displayed.
	 */
	private void displayTasks(String listName) {
		taskView.getItems().clear();

		if (!hide) {
			taskView.getItems().addAll(control.getList(listName).getList());
		} else {
			List<ToDoTask> tempList = control.getList(listName).getList();
			for (ToDoTask task : tempList) {
				if (!task.isDone()) {
					taskView.getItems().add(task);
				}
			}
		}
	}

	/**
	 * Draw the list field again with new information in the model/control
	 */
	private void displayList() {
		listView.getItems().clear();
		listView.getItems().addAll(control.getAll());
	}

	/**
	 * @author allen el This method removes the selected list, if a list is
	 *         selected, and then updates the list view
	 */
	private void removeList() {
		if (listView.getSelectionModel().getSelectedItem() != null) {
			control.removeList(listView.getSelectionModel().getSelectedItem().getName());
			displayList();
			taskView.getItems().clear();
		}

	}

	/**
	 * Performing renaming list if the corresponding button is being pressed
	 */
	private void renameList() {

		// load list
		ToDoList current = listView.getSelectionModel().getSelectedItem();
		if (current == null) {
			return;
		}

		String nameList = current.getName();

		// rename list
		if (control.getList(nameList) != null) {
			TextInputDialog inputNameDialog = new TextInputDialog();
			inputNameDialog.setHeaderText("List name: ");
			inputNameDialog.showAndWait();
			String newName = (inputNameDialog.getEditor().getText());

			control.renameList(nameList, newName);
			displayList();

		}
	}

	/**
	 * displays the selected list, already built in selectList with no param
	 */
	private void selectList() {
		displayTasks();
	}

	/**
	 * Change colors of the task field into the new color cycled through out the
	 * color pool, and change the color of the corresponding button also.
	 */
	private void colorMode() {
		this.currentColorTaskInt = this.currentColorTaskInt + 1;
		if (this.currentColorTaskInt == colorChangePool.length) {
			this.currentColorTaskInt = 0;
		}

		String currentColor = this.colorChangePool[this.currentColorTaskInt];
		colorMode.setStyle("-fx-background-color: " + currentColor);
		taskView.setStyle("-fx-control-inner-background: " + currentColor + "; -fx-box-border: black;");

	}

	/**
	 * Change colors of the list field into the new color cycled through out the
	 * color pool, and change the color of the corresponding button also.
	 */
	private void colorModeCustom() {
		this.currentColorListInt = this.currentColorListInt + 1;
		if (this.currentColorListInt == colorChangePool.length) {
			this.currentColorListInt = 0;
		}

		String currentColor = this.colorChangePool[this.currentColorListInt];
		colorModeCustom.setStyle("-fx-background-color: " + currentColor);
		listView.setStyle("-fx-control-inner-background: " + currentColor + "; -fx-box-border: black;");
	}

	/**
	 * Perform saving when the application is being closed by the user.
	 */
	@Override
	public void stop() {
		model.setListInt(this.currentColorListInt);
		model.setTaskInt(this.currentColorTaskInt);
		model.saveAll();
		System.exit(0);
	}

	/**
	 * Return to default color scheme when the button is pressed
	 */
	private void resetColors() {
		currentColorTaskInt = 0;
		currentColorListInt = 0;
		colorModeCustom.setStyle("-fx-background-color: " + DarkMode1);
		colorMode.setStyle("-fx-background-color: " + DarkMode1);
		taskView.setStyle("-fx-control-inner-background: " + DarkMode1 + "; -fx-box-border: black;");
		listView.setStyle("-fx-control-inner-background: " + DarkMode1 + "; -fx-box-border: black;");
	}

	/**
	 * Deletes saved model and restart. Basically a whole new ToDo application now.
	 */
	private void resetModel() {
		model = new ToDoModel();
		control = new ToDoControl(model);
		displayTasks();
		displayList();

	}

	/**
	 * Toggle between done and undone for the button being pressed
	 */
	private void toggleDone() {
		ToDoTask currentTask = taskView.getSelectionModel().getSelectedItem();
		ToDoList currentList = listView.getSelectionModel().getSelectedItem();
		if (currentTask == null || currentList == null || !currentList.getList().contains(currentTask)) {
			return;
		}
		String nameTask = currentTask.getName();
		String nameList = currentList.getName();	    
		
		if  (control.isDone(nameList,  nameTask)) {
			control.setDone(nameList,  nameTask, false);
		} else {
			control.setDone(nameList, nameTask, true);
		}
		control.sortTask(nameList, "done");
		displayTasks();
	}

	/**
	 * toggle hide done tasks if the button is pressed.
	 */
	private void hideDone() {
		if (hide) {
			hide = false;
		} else {
			hide = true;
		}
		displayTasks();
	}
}
