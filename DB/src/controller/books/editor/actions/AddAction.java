package controller.books.editor.actions;

import controller.DatabaseConnector;
import controller.books.query.BooksQueryManagerController;
import view.AddItemFrame;

public class AddAction implements EditorAction {

	@Override
	public String get_target_button_name() {
		return "ADD";
	}

	@Override
	public boolean target_button_action(String[] data) {
		DatabaseConnector.setCommitLevel(false);
		return BooksQueryManagerController.addBook(data);
	}

	@Override
	public void back_button_action() {
		AddItemFrame.changeWindow();
	}

	@Override
	public String[] get_initial_data() {
		// DO NOTHING
		return null;
	}

}