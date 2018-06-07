package controller.books.editor.actions;

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
		System.out.println("Edited");
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