/**
 * A standalone Java exercise component.
 */
package gameCore;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import saving.SavingSystem;

public class Window {

  public static String player;
  public static JPanel panel;
  public static JFrame frame;

  public Window(int W, int H, String title, Game game) {
    panel = new JPanel();
    frame = new JFrame();
    panel.setBackground(Color.BLACK);
    panel.setLayout(null);
    JButton login = new JButton("Login");
    JButton delete = new JButton("Delete");
    JButton add = new JButton("Add");
    JButton exit = new JButton("Exit");
    JTextField text = new JTextField();
    TextArea label = new TextArea();
    label.setEditable(false);
    label.setBackground(Color.WHITE);
    label.setForeground(Color.BLACK);
    Font font = new Font("Comic Sans MS", 1, 70);
    exit.setBounds(1500 - 300, 700, 300, 100);
    exit.setFont(font);
    exit.setBackground(Color.RED);
    login.setBounds(1500 - 300, 250, 300, 100);
    login.setFont(font);
    add.setBounds(1500 - 300, 400, 300, 100);
    add.setFont(font);
    delete.setBounds(1500 - 300, 550, 300, 100);
    delete.setFont(font);
    text.setBounds(1500 - 300, 30, 300, 50);
    text.setFont(new Font("Comic Sans Ms", 0, 26));
    label.setBounds(20, 150, 150, 650);
    label.setFont(new Font("Comic Sans Ms", 0, 26));
    exit.addActionListener(
        new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent e) {
            System.exit(1);
          }
        });
    login.addActionListener(
        new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent e) {
            player = text.getText().replaceAll(" ", "").toLowerCase();
            if (SavingSystem.isValid(player)) {
              frame.remove(panel);
              frame.add(game);
              frame.setVisible(true);
              game.start();
              Game.save = SavingSystem.load(Window.player);
            }
          }
        });
    add.addActionListener(
        new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if (SavingSystem.size() < 10) {
              SavingSystem.addPlayerName(text.getText().replaceAll(" ", "").toLowerCase());
              label.setText(SavingSystem.getListPlayers());
            }
          }
        });
    delete.addActionListener(
        new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if (SavingSystem.size() > 1) {
              SavingSystem.deletePlayerName(text.getText().replaceAll(" ", "").toLowerCase());
              label.setText(SavingSystem.getListPlayers());
            }
          }
        });
    panel.add(exit);
    panel.add(login);
    panel.add(delete);
    panel.add(add);
    panel.add(text);
    panel.add(label);
    label.setText(SavingSystem.getListPlayers());
    frame.setSize(new Dimension(W, H));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.add(panel);
    frame.setUndecorated(true);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setVisible(true);
  }
}
