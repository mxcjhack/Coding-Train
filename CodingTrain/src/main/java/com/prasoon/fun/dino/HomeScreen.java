package com.prasoon.fun.dino;

import javax.swing.*;
import java.awt.*;

public class HomeScreen extends JPanel {

    public HomeScreen(DinoGameFrame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel title = new JLabel("🦖 Choose Difficulty");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        gbc.gridy = 0;
        add(title, gbc);

        JButton easy = new JButton("Easy");
        JButton medium = new JButton("Medium");
        JButton hard = new JButton("Hard");

        easy.setFont(new Font("Arial", Font.PLAIN, 20));
        medium.setFont(new Font("Arial", Font.PLAIN, 20));
        hard.setFont(new Font("Arial", Font.PLAIN, 20));

        gbc.gridy = 1;
        add(easy, gbc);
        gbc.gridy = 2;
        add(medium, gbc);
        gbc.gridy = 3;
        add(hard, gbc);

        easy.addActionListener(e -> frame.startGame(30));   // Slow
        medium.addActionListener(e -> frame.startGame(20)); // Default
        hard.addActionListener(e -> frame.startGame(10));   // Fast
    }
}
