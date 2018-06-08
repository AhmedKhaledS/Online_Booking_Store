package controller.books.editor.actions;

import controller.DatabaseConnector;
import controller.books.query.BookOrdersManagerController;

public class EditAction implements EditorAction {

	String[] data;

	public EditAction(String[] data) {
		this.data = data;
	}

	@Override
	public String get_target_button_name() {
		return "APPLY";
	}

	// We must execute update statement not delete and add new book!!
	@Override
	public boolean target_button_action(String[] newData) {
		DatabaseConnector.setCommitLevel(false);
		return BookOrdersManagerController.modifyBook(this.data[0], newData);
	}

	@Override
	public void back_button_action() {
		// DO NOTHING
	}

	@Override
	public String[] get_initial_data() {
		return data;
	}

}