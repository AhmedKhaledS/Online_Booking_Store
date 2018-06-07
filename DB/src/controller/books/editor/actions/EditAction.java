package controller.books.editor.actions;

import controller.books.viewer.actions.ManagerAction;
import view.BooksViewerFrame;

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
		// TODO Auto-generated method stub
	}

	@Override
	public void back_button_action() {
		BooksViewerFrame.changeWindow("Edit", new ManagerAction());
	}

	@Override
	public String[] get_initial_data() {
		return data;
	}

}
