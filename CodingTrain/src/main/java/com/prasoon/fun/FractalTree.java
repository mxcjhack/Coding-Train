package com.prasoon.fun;

import javax.swing.*;
import java.awt.*;

public class FractalTree  extends JPanel{

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //Smooth Jagged Lines
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int X = getWidth() / 2;
        int Y = getHeight() + 50;


        drawTree(g2, X, Y, -90, 200, 15);
    }

    private void drawTree(Graphics2D g2, int x, int y, double angle, double length, double depth) {
        if(depth == 0 ) return;

        int x2 = x + (int) (length * Math.cos(Math.toRadians(angle)));
        int y2 = y + (int) (length * Math.sin(Math.toRadians(angle)));

        g2.drawLine(x, y, x2, y2);

        drawTree(g2, x2, y2, angle - 45, length * 0.7, depth - 1);
        drawTree(g2, x2, y2, angle + 45, length * 0.7, depth - 1);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fractal Tree");
        FractalTree tree = new FractalTree();

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(tree);
        frame.setVisible(true);
    }
}
