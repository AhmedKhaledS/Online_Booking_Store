package controller.books.editor.actions;

public interface EditorAction {

	String get_target_button_name();

	void target_button_action(String[] data);

	void back_button_action();

	String[] get_initial_data();
}
