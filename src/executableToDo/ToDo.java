package executableToDo;
import javafx.application.Application;
import view.ToDoView;

/**
 * The initial launch for the whole application. Basically this would run the
 * ToDoView class for GUI interactions.
 * 
 * @author Ofer Greenberg, Quan Nguyen, Quang Vu, Allen David El.
 */
public class ToDo {
	/**
	 * Main classes. Launch the application.
	 * 
	 * @param args the default parameters
	 */
	public static void main(String[] args) {
		Application.launch(ToDoView.class, args);
	}
}
