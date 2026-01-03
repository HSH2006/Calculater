import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calculater extends JFrame implements ActionListener {
    private JTextField display;
    private JButton[] numberB;
    private JButton[] functionButtons;
    private JButton addB, subB, mulB, divB, decB, eqB, clrB, delB, powB, modB, sqrtB, parantezB1, parantezB2;

    private JPanel panel;

    private double num1 = 0, num2 = 0, result = 0;
    private char operator;
    private boolean isClick = false;

    public calculater() {
        setTitle("Calculater");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        display = new JTextField();
        display.setBounds(50, 25, 300, 50);
        display.setFont(new Font("Arial", Font.BOLD, 20));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);

        numberB = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberB[i] = new JButton(String.valueOf(i));
            numberB[i].setFont(new Font("Arial", Font.BOLD, 18));
            numberB[i].addActionListener(this);
        }

        addB = createFunctionButton("+", Color.ORANGE);
        subB = createFunctionButton("-", Color.ORANGE);
        mulB = createFunctionButton("×", Color.ORANGE);
        divB = createFunctionButton("÷", Color.ORANGE);
        powB = createFunctionButton("^", Color.CYAN);
        modB = createFunctionButton("%", Color.CYAN);
        sqrtB = createFunctionButton("√", Color.CYAN);

        decB = createFunctionButton(".", Color.LIGHT_GRAY);
        eqB = createFunctionButton("=", Color.LIGHT_GRAY);
        clrB = createFunctionButton("C", Color.LIGHT_GRAY);
        delB = createFunctionButton("DEL", Color.LIGHT_GRAY);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 350);
        panel.setLayout(new GridLayout(6, 4, 10, 10));
        String[] button1 = {"C", "DEL", "√", "÷", "7", "8", "9", "×", "4", "5", "6", "-", "1", "2", "3", "+",
                "0", ".", "=", "%", "^"};

        for (String button2 : button1) {
            JButton button;
            switch (button2) {
                case "C":
                    button = clrB;
                    break;
                case "DEL":
                    button = delB;
                    break;
                case "√":
                    button = sqrtB;
                    break;
                case "÷":
                    button = divB;
                    break;
                case "×":
                    button = mulB;
                    break;
                case "-":
                    button = subB;
                    break;
                case "+":
                    button = addB;
                    break;
                case ".":
                    button = decB;
                    break;
                case "=":
                    button = eqB;
                    break;
                case "%":
                    button = modB;
                    break;
                case "^":
                    button = powB;
                    break;
                case "0": case "1": case "2": case "3":
                case "4": case "5": case "6": case "7":
                case "8": case "9":
                    button = numberB[Integer.parseInt(button2)];
                    button.setBackground(Color.WHITE);
                    break;
                default:
                    button = new JButton(button2);
                    button.setEnabled(false);
                    button.setBackground(Color.LIGHT_GRAY);
            }
            button.setFont(new Font("Arial", Font.BOLD, 16));
            panel.add(button);
        }

        add(display);
        add(panel);

        setVisible(true);
    }

    private JButton createFunctionButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(color);
        button.addActionListener(this);
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberB[i]) {
                if (isClick) {
                    display.setText("");
                    isClick = false;
                }
                display.setText(display.getText() + i);
            }
        }

        if (e.getSource() == decB) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        }

        if (e.getSource() == addB)
            performOperation('+');
        if (e.getSource() == subB)
            performOperation('-');
        if (e.getSource() == mulB)
            performOperation('×');
        if (e.getSource() == divB)
            performOperation('÷');
        if (e.getSource() == powB)
            performOperation('^');
        if (e.getSource() == modB)
            performOperation('%');
        if (e.getSource() == sqrtB)
            try {
                double num = Double.parseDouble(display.getText());
                if (num >= 0) {
                    result = Math.sqrt(num);
                    display.setText(String.valueOf(result));
                } else {
                    display.setText("EROR add manfi");
                }
            } catch (NumberFormatException ex) {
                display.setText("EROR");
            }

        if (e.getSource() == eqB)
            calculateResult();
        if (e.getSource() == clrB) {
            display.setText("");
            num1 = num2 = result = 0;
            isClick = false;
        }

        if (e.getSource() == delB) {
            String currentText = display.getText();
            if (!currentText.isEmpty()) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            }
        }
    }

    private void performOperation(char op) {
        try {
            num1 = Double.parseDouble(display.getText());
            operator = op;
            isClick = true;
        } catch (NumberFormatException ex) {
            display.setText("Eror");
        }
    }

    private void calculateResult() {
        try {
            num2 = Double.parseDouble(display.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '×':
                    result = num1 * num2;
                    break;
                case '÷':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Eror tarif nashde");
                        return;
                    }
                    break;
                case '^':
                    result = Math.pow(num1, num2);
                    break;
                case '%':
                    result = num1 % num2;
                    break;
                default:
                    return;
            }

            if (result == (int) result)
                display.setText(String.valueOf((int) result));
            else
                display.setText(String.valueOf(result));

            isClick = true;

        } catch (NumberFormatException ex) {
            display.setText("Eror");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new calculater();
            }
        });
    }
}
