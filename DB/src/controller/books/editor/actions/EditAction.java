package controller.books.editor.actions;

import controller.DatabaseConnector;
import controller.books.query.BooksQueryManager;

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
	public void target_button_action(String[] data) {
		DatabaseConnector.setCommitLevel(false);
		BooksQueryManager.deleteBook(this.data[0]);
		BooksQueryManager.addBook(data);
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