package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

import javax.swing.*;
import java.awt.*;

public class MediaStore extends JPanel {
    private final Media media;

    public MediaStore(Media media) {
        this.media = media;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(Box.createVerticalGlue());
        add(createLabel(media.getTitle(), 15, true));
        add(createLabel(String.format("%.2f $", media.getCost()), 13, false));
        add(createButtonPanel());
        add(Box.createVerticalGlue());
    }

    private JLabel createLabel(String text, int fontSize, boolean bold) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", bold ? Font.BOLD : Font.PLAIN, fontSize));
        return label;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(e -> {
                ((Playable) media).play();
                JOptionPane.showMessageDialog(this, "Playing " + media.getTitle(), "Playing Media", JOptionPane.INFORMATION_MESSAGE);
            });
            panel.add(playButton);
        }
        return panel;
    }
}
