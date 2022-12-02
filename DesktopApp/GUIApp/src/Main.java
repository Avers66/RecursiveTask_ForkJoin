import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.add(new FIO().getFIOPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("FIOForm");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
