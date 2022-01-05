import bussiness.TaskBussiness;
import classes.Task;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainForm extends JFrame {

    private JPanel rootPanel;
    private JButton addTaskButton;
    private JTable tableTask;
    private JButton removeButton;
    private JLabel countTaskLabel;
    private TaskBussiness mTaskBussiness;
    private String titulo="";

    public MainForm(){

        // Função JavaSwing
        setContentPane(rootPanel);
        setSize(600, 400);
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - 300, dim.height / 2 - 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mTaskBussiness = new TaskBussiness();
        setListeners();
        loadTasks();

    }
    private void setListeners() {

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new TaskForm();
                dispose(); //Fechar o formulário atual (já que o de cima está aberto)
            }
        });

        tableTask.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent ev) {
                if(ev.getValueIsAdjusting()){

                    if(tableTask.getSelectedRow()!=-1)
                        titulo = tableTask.getValueAt(tableTask.getSelectedRow(), 0).toString();

                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mTaskBussiness.delete(titulo);
                new MainForm();
                dispose(); //Fechar o formulário atual (já que o de cima está aberto)
            }
        });

    }

    private void loadTasks(){

        ArrayList<Task> taskList = mTaskBussiness.getList();
        String[] columns = {"Nome", "Data", "Descrição"};
        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columns);

        for (Task t: taskList){
            Object o [] = new Object[3];
            o[0] = t.getTitulo();
            o[1] = t.getData().getDia()+t.getData().getMes()+t.getData().getAno();
            o[2] = t.getDescricao();
            model.addRow(o);
        }

        tableTask.clearSelection(); // Eliminar seleção atual (clique)
        tableTask.setModel(model);

        titulo="";

        countTaskLabel.setText("Quantidade de tarefas: " + mTaskBussiness.getList().size());

    }
}
