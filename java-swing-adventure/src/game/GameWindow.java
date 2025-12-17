package game;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private static GameWindow instance;

    private final JTextArea output;
    private final JTextField input;
    private final Game game;

    // Konstruktor für Seed-Start
    public GameWindow(String seed) {
        this(true);
        game.start(seed);
    }

    // Normaler Startkonstruktor
    public GameWindow() {
        this(true);
        game.start();
    }

    // Gemeinsamer Setup-Konstruktor
    private GameWindow(boolean initOnly) {
        super("Swing Adventure Project");
        instance = this;
        this.game = new Game();

        // Output-Bereich
        output = new JTextArea();
        output.setEditable(false);
        output.setFont(UIConfig.scaledFont("Monospaced", Font.PLAIN, 16));
        output.setBackground(new Color(20, 20, 20));
        output.setForeground(new Color(0, 255, 100));
        output.setMargin(UIConfig.scaledInsets(10, 10, 10, 10));
        add(new JScrollPane(output), BorderLayout.CENTER);

        // Input-Bereich
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(new Color(30, 30, 30));

        JLabel prompt = new JLabel(" > ");
        prompt.setForeground(new Color(0, 255, 100));
        prompt.setFont(UIConfig.scaledFont("Monospaced", Font.BOLD, 16));

        input = new JTextField();
        input.setFont(UIConfig.scaledFont("Monospaced", Font.PLAIN, 16));
        input.setBackground(new Color(30, 30, 30));
        input.setForeground(new Color(0, 255, 100));
        input.setCaretColor(new Color(0, 255, 100));
        input.setBorder(BorderFactory.createEmptyBorder(UIConfig.scale(8), UIConfig.scale(8), UIConfig.scale(8), UIConfig.scale(8)));

        input.addActionListener(e -> {
            String command = input.getText().trim();
            if (!command.isEmpty()) {
                print("> " + command);
                input.setText("");
                game.processInput(command);
            }
        });

        inputPanel.add(prompt, BorderLayout.WEST);
        inputPanel.add(input, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(UIConfig.scale(800), UIConfig.scale(600));
        setLocationRelativeTo(null);
        setVisible(true);
        input.requestFocus();

        if (!initOnly) {
            game.start();
        }
    }

    public static void print(String text) {
        GameWindow win = instance;
        if (win != null) {
            win.output.append(text + "\n");
            win.output.setCaretPosition(win.output.getDocument().getLength());
        }
    }
}
