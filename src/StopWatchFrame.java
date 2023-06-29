import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.Timer;

public class StopWatchFrame extends JFrame implements ActionListener {
    Button startButton;
    Button resetButton;
    JLabel displayLabel;
    int seconds=0;
    int minutes=0;
    int hours=0;
    boolean started = false;
    DecimalFormat DF = new DecimalFormat();

    Timer updateTimer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds++;
            if (seconds>=60){
                seconds=0;
                minutes++;
                if(minutes>=60){
                    minutes=0;
                    hours++;
                }
            }
            displayLabel.setText(DF.format(hours)+":"+DF.format(minutes)+":"+DF.format(seconds));
        }
    });

    StopWatchFrame() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.gray);
        this.setLocationRelativeTo(null);
        this.setTitle("Stopwatch");
        this.setIconImage(new ImageIcon("icon.png").getImage());

        startButton = new Button("START/STOP");
        startButton.addActionListener(this);
        resetButton = new Button("RESET");
        resetButton.addActionListener(this);

        this.setLabel();
        constraints.insets = new Insets(10,10,10,10);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        constraints.weightx=0.5;
        constraints.weighty=0.5;
        constraints.gridy=0;
        constraints.gridwidth=2;
        this.add(displayLabel,constraints);

        constraints.gridy=1;
        constraints.gridx=0;
        constraints.gridwidth=1;
        this.add(resetButton,constraints);

        constraints.gridx=1;
        this.add(startButton,constraints);

        this.pack();
        this.setVisible(true);
    }

    public void setLabel(){
        DF.setMinimumIntegerDigits(2);
        displayLabel = new JLabel();
        displayLabel.setText(DF.format(hours)+":"+DF.format(minutes)+":"+DF.format(seconds));
        displayLabel.setFont(new Font("digital-7", Font.PLAIN,150));
        displayLabel.setHorizontalAlignment(JLabel.LEFT);
        displayLabel.setBackground(Color.gray);
        displayLabel.setForeground(Color.BLACK);
        displayLabel.setOpaque(true);
        displayLabel.setBorder(BorderFactory.createEmptyBorder(0,0,40,0));
    }

    public void stopTimer() {
        updateTimer.stop();
    }

    public void startTimer() {
        updateTimer.start();
    }

    public void resetTimer() {
        started=false;
        updateTimer.stop();
        seconds=0;
        minutes=0;
        hours=0;
        displayLabel.setText(DF.format(hours)+":"+DF.format(minutes)+":"+DF.format(seconds));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==resetButton){
            resetTimer();
        }
        if(e.getSource()==startButton) {
            if(!started) {
                started=true;
                startTimer();
            }
            else{
                started=false;
                stopTimer();
            }
        }
    }
}