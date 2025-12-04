package game;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Menu extends JFrame {
    
    private JButton newRunButton;
    private JPanel newRunWithSeedPanel;
    private JLabel newRunWithSeedLabel;
    private JTextField newRunWithSeedTextField;

    public Menu() {
        super("Menü");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        setUpButtons();

        setVisible(true);
    }

    private void setUpButtons() {
        newRunButton = new JButton();
        newRunButton.addActionListener(l -> {
                SwingUtilities.invokeLater(() -> {
                    this.dispose();
                    @SuppressWarnings("unused")
                    GameWindow gameWindow = new GameWindow();
                });
        });
        newRunButton.setText("Starte ein neues Spiel!");
        add(newRunButton);


        newRunWithSeedPanel = new JPanel(new BorderLayout());
        
        newRunWithSeedLabel = new JLabel("Hier eigenen Seed eingeben: ");

        newRunWithSeedTextField = new JTextField();
        newRunWithSeedTextField.addActionListener(l -> {
                String input = newRunWithSeedTextField.getText().trim();
                if(!input.isEmpty()) {
                    this.dispose();
                    @SuppressWarnings("unused")
                    GameWindow gameWindow = new GameWindow(input);
                }
        });
        
        newRunWithSeedPanel.add(newRunWithSeedLabel, BorderLayout.WEST);
        newRunWithSeedPanel.add(newRunWithSeedTextField, BorderLayout.CENTER);
        add(newRunWithSeedPanel, BorderLayout.SOUTH);

    }
    
}