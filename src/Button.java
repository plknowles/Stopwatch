import javax.swing.*;
import java.awt.*;

import static java.awt.Font.BOLD;

public class Button extends JButton {

    Button(String text) {
        this.setText(text);
        this.setFont(new Font("Comic Sans", BOLD,20));
        this.setFocusable(false);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setBackground(Color.lightGray);
        this.setForeground(Color.BLACK);
    }
}
