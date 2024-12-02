package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import services.TaskService;
import models.Task;

public class View extends JFrame {

    private DefaultListModel<Task> taskListModel; // Modèle pour la liste des tâches
    private JList<Task> taskList; // Composant JList pour afficher les tâches
    private JTextField taskTitleField; // Champ de texte pour le titre
    private JTextArea taskDescriptionField; // Champ de texte pour la description
    private JButton addButton, modifyButton, deleteButton; // Boutons d'action
    private TaskService service = new TaskService();// Service de Backend

    public View() {
        setTitle("To-Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new BorderLayout());

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);

        add(new JScrollPane(taskList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Nouvelle Tâche"));

        taskTitleField = new JTextField();
        inputPanel.add(new JLabel("Title : "));
        inputPanel.add(taskTitleField);

        // Champ pour la description de la tâche
        taskDescriptionField = new JTextArea(3, 20);
        inputPanel.add(new JLabel("Description de la tâche:"));
        inputPanel.add(new JScrollPane(taskDescriptionField));

        add(inputPanel, BorderLayout.NORTH);

        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Ajouter");
        modifyButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");

        buttonPanel.add(addButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action pour le bouton "Ajouter"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = taskTitleField.getText().trim();
                String description = taskDescriptionField.getText().trim();
                if (!title.isEmpty() && !description.isEmpty()) {
                    Task newTask = new Task(title, description);
                    service.createTask(newTask);

                    taskListModel.addElement(newTask);
                    taskTitleField.setText("");
                    taskDescriptionField.setText("");
                } else {
                    JOptionPane.showMessageDialog(View.this, "Veuillez saisir le titre et la description.", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action pour le bouton "Modifier"
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String title = taskTitleField.getText().trim();
                    String description = taskDescriptionField.getText().trim();
                    if (!title.isEmpty() && !description.isEmpty()) {
                        Task updateTask = new Task(title, description);
                        service.updateTask(updateTask);
                        taskListModel.set(selectedIndex, updateTask);
                        taskTitleField.setText("");
                        taskDescriptionField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(View.this, "Veuillez saisir le titre et la description.",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(View.this, "Veuillez sélectionner une tâche à modifier.", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action pour le bouton "Supprimer"
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Task deleteTask = taskListModel.get(selectedIndex);
                    service.deleteTask(deleteTask);
                    taskListModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(View.this, "Veuillez sélectionner une tâche à supprimer.",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true); // Rendre la fenêtre visible
    }

}
