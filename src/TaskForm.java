import bussiness.TaskBussiness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskForm extends JFrame {

    private JPanel rootPanel;
    private JTextField textTitulo;
    private JTextField textData;
    private JTextField textDesc;
    private JButton addTaskButton;
    private JButton cancelButton;

    private TaskBussiness mTaskBussiness;

    TaskForm(){

        setContentPane(rootPanel);
        setSize(600,400);
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - 300, dim.height / 2 - 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mTaskBussiness = new TaskBussiness();
        setListeners();

    }

    private void setListeners() {

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    String titulo = textTitulo.getText();
                    String data = textData.getText();
                    String descricao = textDesc.getText();

                    mTaskBussiness.save(titulo, data, descricao);

                    new MainForm();
                    dispose();
                } catch (Exception e){

                    JOptionPane.showMessageDialog(new JFrame(), e.getMessage());

                }

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new MainForm();
                dispose();
            }
        });

    }


}
