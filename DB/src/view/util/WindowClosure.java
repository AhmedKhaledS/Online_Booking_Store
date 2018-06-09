package view.util;

public class WindowClosure {

	static public void closeActiveWindows() {
		System.gc();
		java.awt.Window win[] = java.awt.Window.getWindows();
		for (int i = 0; i < win.length; i++) {
			win[i].dispose();
			win[i] = null;
		}
	}

}