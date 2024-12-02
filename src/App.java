import javax.swing.SwingUtilities;

import views.View;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(View::new);
    }

}
