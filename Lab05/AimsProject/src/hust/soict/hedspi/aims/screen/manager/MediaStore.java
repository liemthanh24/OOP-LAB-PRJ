package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.exception.PlayerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediaStore extends JPanel {
    private final Media media;

    public MediaStore(Media media) {
        this.media = media;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = createLabel((media.getTitle() != null ? media.getTitle() : "N/A"), 15, true);
        JLabel costLabel = createLabel(String.format("%.2f $", media.getCost()), 13, false);

        add(Box.createVerticalStrut(10));
        add(titleLabel);
        add(Box.createVerticalStrut(5));
        add(costLabel);
        add(Box.createVerticalStrut(5));
        add(createButtonPanel());
        add(Box.createVerticalStrut(10));
    }

    private JLabel createLabel(String text, int fontSize, boolean bold) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", bold ? Font.BOLD : Font.PLAIN, fontSize));
        return label;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        ((Playable) media).play();
                    } catch (PlayerException ex) {
                        JOptionPane.showMessageDialog(MediaStore.this,
                                ex.getMessage(),
                                "Playback Error", 
                                JOptionPane.ERROR_MESSAGE);
                        System.err.println("PlayerException in MediaStore for " + media.getTitle() + ": " + ex.getMessage());
                    } catch (Exception generalEx) {
                         JOptionPane.showMessageDialog(MediaStore.this,
                                "An unexpected error occurred while trying to play: " + generalEx.getMessage(),
                                "Playback Error",
                                JOptionPane.ERROR_MESSAGE);
                        System.err.println("Unexpected error in MediaStore for " + media.getTitle() + ": " + generalEx.getMessage());
                    }
                }
            });
            panel.add(playButton);
        }
        return panel;
    }
}
