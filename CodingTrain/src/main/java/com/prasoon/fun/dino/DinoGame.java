package com.prasoon.fun.dino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class DinoGame extends JPanel implements ActionListener, KeyListener {

    private final Timer timer;
    private int dinoY = 250, velocityY = 0;
    private boolean isJumping = false;
    private final int groundY = 300;
    private final ArrayList<Rectangle> obstacles;
    private int score = 0;
    private boolean gameOver = false;

    private int currentSpeed;
    private int speedLevel = 0;

    public DinoGame(int initialSpeed) {
        setFocusable(true);
        addKeyListener(this);
        setBackground(Color.WHITE);

        this.currentSpeed = initialSpeed;
        this.timer = new Timer(currentSpeed, this);
        this.timer.start();

        obstacles = new ArrayList<>();
        spawnObstacle();
    }

    public void spawnObstacle() {
        int width = 20 + (int)(Math.random() * 20);
        Rectangle obs = new Rectangle(800, groundY - width, width, width);
        obstacles.add(obs);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Ground
        g.setColor(Color.GRAY);
        g.fillRect(0, groundY, getWidth(), 5);

        // Dino
        g.setColor(Color.BLACK);
        g.fillRect(50, dinoY, 40, 40);

        // Obstacles
        g.setColor(Color.GREEN);
        for (Rectangle obs : obstacles) {
            g.fillRect(obs.x, obs.y, obs.width, obs.height);
        }

        // Score
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 600, 30);

        // Game Over
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", 300, 200);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (gameOver) return;

        // Dino physics
        if (isJumping) {
            velocityY += 1;
            dinoY += velocityY;

            if (dinoY >= 250) {
                dinoY = 250;
                isJumping = false;
                velocityY = 0;
            }
        }

        boolean needToSpawn = false;
        Iterator<Rectangle> it = obstacles.iterator();

        while (it.hasNext()) {
            Rectangle obs = it.next();
            obs.x -= 10;

            if (obs.x + obs.width < 0) {
                it.remove();
                score++;
                needToSpawn = true;
            }

            if (score > 0 && score % 5 == 0 && score / 5 > speedLevel) {
                speedLevel = score / 10;
                currentSpeed = Math.max(5, currentSpeed - 2); // Decrease delay to speed up
                timer.setDelay(currentSpeed);
                System.out.println("Speed increased! Current delay: " + currentSpeed + "ms");
            }

            Rectangle dino = new Rectangle(50, dinoY, 40, 40);
            if (dino.intersects(obs)) {
                gameOver = true;
                timer.stop();
            }
        }

        if (needToSpawn) {
            spawnObstacle();
        }

        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !isJumping && !gameOver) {
            isJumping = true;
            velocityY = -15;
        }

        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            // Restart
            dinoY = 250;
            isJumping = false;
            velocityY = 0;
            obstacles.clear();
            score = 0;
            gameOver = false;
            spawnObstacle();
            timer.start();
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

}
