package com.prasoon.fun.dino;

import javax.swing.*;
import java.awt.*;

public class DinoGameFrame extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    public DinoGameFrame() {
        setTitle("Dino Game - Difficulty Select");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Home screen
        HomeScreen homeScreen = new HomeScreen(this);
        cardPanel.add(homeScreen, "home");

        // Show home screen first
        add(cardPanel);
        setVisible(true);
    }

    public void startGame(int initialSpeed) {
        DinoGame dinoGame = new DinoGame(initialSpeed);
        cardPanel.add(dinoGame, "game");
        cardLayout.show(cardPanel, "game");
        dinoGame.requestFocusInWindow(); // Ensure key input works
    }

    public static void main(String[] args) {
        new DinoGameFrame();
    }


}
