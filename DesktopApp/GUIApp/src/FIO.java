import javax.swing.*;

public class FIO {
    private JPanel fioPanel;
    private JTextField tFirstName;
    private JTextField tPatronymic;
    private JTextField tSecondName;
    private JTextField tFIO;
    private JButton button;
    private JLabel lFIO;
    private JLabel lFirstName;
    private JLabel lPatronymic;
    private JLabel lSecondName;

    public FIO() {
        lFIO.setVisible(false);
        tFIO.setVisible(false);

        button.addActionListener(event -> {
            String error = "Ведите корректные данные";
            String regex ="[А-Я][а-яё]+";
            if (tFirstName.isVisible()){
                boolean p1 = tSecondName.getText().matches(regex);
                boolean p2 = tFirstName.getText().matches(regex);
                boolean p3 = tPatronymic.getText().matches(regex) || tPatronymic.getText().isEmpty();

                if (!p1 || !p2 || !p3) {
                    JOptionPane.showMessageDialog(fioPanel,error,"Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                button.setText("Expand");
                lFIO.setVisible(true);
                tFIO.setVisible(true);
                tFIO.setText(tSecondName.getText() + " " +
                             tFirstName.getText() + " " +
                             tPatronymic.getText());
                lPatronymic.setVisible(false);
                lFirstName.setVisible(false);
                lSecondName.setVisible(false);
                tPatronymic.setVisible(false);
                tFirstName.setVisible(false);
                tSecondName.setVisible(false);
                fioPanel.updateUI();

            }else {
                String[] aFIO = tFIO.getText().trim().split("[\s]+");
                if (aFIO.length != 3 && aFIO.length != 2) {
                    JOptionPane.showMessageDialog(fioPanel,error,"Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String patronymic = "";
                if (aFIO.length == 3) patronymic = aFIO[2];
                boolean p1 = aFIO[0].matches(regex);
                boolean p2 = aFIO[1].matches(regex);
                boolean p3 = patronymic.matches(regex) || patronymic.isEmpty();
                if (!p1 || !p2 || !p3) {
                    JOptionPane.showMessageDialog(fioPanel,error,"Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                button.setText("Collapse");
                lFIO.setVisible(false);
                tFIO.setVisible(false);
                tFirstName.setText(aFIO[1]);
                tPatronymic.setText(patronymic);
                tSecondName.setText(aFIO[0]);
                lPatronymic.setVisible(true);
                lFirstName.setVisible(true);
                lSecondName.setVisible(true);
                tPatronymic.setVisible(true);
                tFirstName.setVisible(true);
                tSecondName.setVisible(true);
                fioPanel.updateUI();

            }
        });
    }

    public JPanel getFIOPanel(){
        return fioPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
