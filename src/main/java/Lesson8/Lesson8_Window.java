package Lesson8;

import javax.swing.*;
import java.awt.*;

public class Lesson8_Window extends JFrame {
    private boolean memorize = false;
    private int value;


    public Lesson8_Window(int initialValue){
        final int[] savedValue = {initialValue};
        setTitle("Счетчик машин за окном");
        setBounds(300, 300, 500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Font font = new Font("Arial", Font.BOLD, 32);
        JLabel counterValueView = new JLabel();
        counterValueView.setFont(font);
        counterValueView.setHorizontalAlignment(SwingConstants.CENTER);
        add(counterValueView, BorderLayout.CENTER);

        value = initialValue;
        counterValueView.setText(String.valueOf(value));

        JButton decrementButton = new JButton("-1");
        decrementButton.setFont(font);


        JButton decrementButtonFive = new JButton("-5");
        decrementButtonFive.setFont(font);


        JButton decrementButtonTen = new JButton("-10");
        decrementButtonTen.setFont(font);


        JPanel panelDecrement = new JPanel(new GridLayout(3, 1));
        panelDecrement.add(decrementButton);
        panelDecrement.add(decrementButtonFive);
        panelDecrement.add(decrementButtonTen);
        add(panelDecrement, BorderLayout.WEST);

        JButton incrementButton = new JButton("+1");
        incrementButton.setFont(font);

        JButton incrementButtonFive = new JButton("+5");
        incrementButtonFive.setFont(font);

        JButton incrementButtonTen = new JButton("+10");
        incrementButtonTen.setFont(font);

        JPanel panelIncrement = new JPanel(new GridLayout(3, 1));
        panelIncrement.add(incrementButton);
        panelIncrement.add(incrementButtonFive);
        panelIncrement.add(incrementButtonTen);
        add(panelIncrement, BorderLayout.EAST);

        JButton resetButton = new JButton("RESET");
        resetButton.setFont(font);

        JButton saveButton = new JButton("MEMO");
        saveButton.setFont(font);

        JPanel southPanel = new JPanel(new GridLayout(1, 2));
        southPanel.add(saveButton);
        southPanel.add(resetButton);
        add(southPanel, BorderLayout.SOUTH);



        decrementButton.addActionListener(e -> {
            value--;
            counterValueView.setText(String.valueOf(value));
        });

        decrementButtonFive.addActionListener(e -> {
            value -= 5;
            counterValueView.setText(String.valueOf(value));
        });

        decrementButtonTen.addActionListener(e -> {
            value -= 10;
            counterValueView.setText(String.valueOf(value));
        });

        incrementButton.addActionListener(e -> {
            value++;
            counterValueView.setText(String.valueOf(value));
        });

        incrementButtonFive.addActionListener(e -> {
            value += 5;
            counterValueView.setText(String.valueOf(value));
        });

        incrementButtonTen.addActionListener(e -> {
            value += 10;
            counterValueView.setText(String.valueOf(value));
        });

        resetButton.addActionListener(e -> {
            value = initialValue;
            counterValueView.setText(String.valueOf(value));
            memorize = false;
        });

        saveButton.addActionListener(e -> {
            if (!memorize) {
                savedValue[0] = value;
                memorize = true;
                return;
            }
            value = savedValue[0];
            counterValueView.setText(String.valueOf(value));
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Lesson8_Window( 127);
    }
}
