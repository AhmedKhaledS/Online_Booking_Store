package controller.books.editor.actions;

import controller.DatabaseConnector;
import controller.books.query.BooksQueryManagerController;

public class EditAction implements EditorAction {

	String[] data;

	public EditAction(String[] data) {
		this.data = data;
	}

	@Override
	public String get_target_button_name() {
		return "APPLY";
	}

	@Override
	public boolean target_button_action(String[] data) {
		DatabaseConnector.setCommitLevel(false);
		BooksQueryManagerController.deleteBook(this.data[0]);
		return BooksQueryManagerController.addBook(data);
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