
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Quiz implements ActionListener{

    String[] questions =   {
            "[Vehicle Handling] You should adjust your seat and mirrors correctly in order to: ?",
            "[Vehicle Handling] The typical stopping distance of a vehicle in dry weather is approximately __________ the normal distance of a vehicle in wet weather.",
            "[Hazard Awareness] You are travelling slowly behind a cyclist in front of you. Why might they suddenly swerve or move across the traffic lane?",
            "[Safety Margin] What is the total typical stopping distance for a car travelling at 60mph (96 km/h)?",
            " What do WHITE stud marks or reflective cat's eyes indicate?"
    };
    String[][] options =   {
            {"Keep your body as close as possible to the steering wheel, even if its uncomfortable.","Ensure you can see yourself whilst you drive.","Keep the distracting vehicle controls out of reach.","Ensure comfort, full control and maximum field of vision."},
            {"The same as","Triple","Double","Half"},
            {"As a warning to you that you're travelling too closely.","Cyclists like to show off.","To avoid an uneven road surface or an icy patch.","To get a better view of the road ahead."},
            {"53 metres (175 feet)","73 metres (240 feet)","36 metres (118 feet)","96 metres (315 feet)"},
            {"The edge of the main carriageway at lay-bys and slip roads.","The central reservation of a dual carriageway or motorway.","The left edge of the road.","The lane dividers or the middle of the road."}
    };
    char[] answers =      {
            'A',
            'B',
            'C',
            'C'
    };
    char guess;
    char answer;
    int index;
    int correct_guesses =0;
    int total_questions = questions.length;
    int result;
    int seconds=5;

    JFrame frame = new JFrame();
    JTextField textfield = new JTextField();
    JTextArea textarea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JTextArea answer_labelA = new JTextArea();
    JTextArea answer_labelB = new JTextArea();
    JTextArea answer_labelC = new JTextArea();
    JTextArea answer_labelD = new JTextArea();
    JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();

    Timer timer = new Timer(1000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            seconds_left.setText(String.valueOf(seconds));
            if(seconds<=0) {
                displayAnswer();
            }
        }
    });

    public Quiz() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,600);
        frame.getContentPane().setBackground(new Color(199,199,206));
        frame.setLayout(null);
        frame.setResizable(false);

        textfield.setBounds(0,0,650,50);
        textfield.setBackground(new Color(199,199,206));
        textfield.setForeground(new Color(15,15,15));
        textfield.setFont(new Font("Ink Free",Font.BOLD,40));
        textfield.setBorder(BorderFactory.createBevelBorder(1));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);

        textarea.setBounds(0,50,650,100);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setBackground(new Color(199,199,206));
        textarea.setForeground(new Color(15,15,15));
        textarea.setFont(new Font("MV Boli",Font.BOLD,20));
        textarea.setBorder(BorderFactory.createBevelBorder(1));
        textarea.setEditable(false);

        buttonA.setBounds(0,154,100,100);
        buttonA.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0,256,100,100);
        buttonB.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0,358,100,100);
        buttonC.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(0,460,100,100);
        buttonD.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        answer_labelA.setBounds(125,154,500,100);
        answer_labelA.setLineWrap(true);
        answer_labelA.setWrapStyleWord(true);
        answer_labelA.setBackground(new Color(199,199,206));
        answer_labelA.setForeground(new Color(15,15,15));
        answer_labelA.setFont(new Font("MV Boli",Font.PLAIN,20));

        answer_labelB.setBounds(125,285,500,100);
        answer_labelB.setLineWrap(true);
        answer_labelB.setWrapStyleWord(true);
        answer_labelB.setBackground(new Color(199,199,206));
        answer_labelB.setForeground(new Color(15,15,15));
        answer_labelB.setFont(new Font("MV Boli",Font.PLAIN,20));

        answer_labelC.setBounds(125,380,500,100);
        answer_labelC.setLineWrap(true);
        answer_labelC.setWrapStyleWord(true);
        answer_labelC.setBackground(new Color(199,199,206));
        answer_labelC.setForeground(new Color(15,15,15));
        answer_labelC.setFont(new Font("MV Boli",Font.PLAIN,20));

        answer_labelD.setBounds(125,470,500,100);
        answer_labelD.setLineWrap(true);
        answer_labelD.setWrapStyleWord(true);
        answer_labelD.setBackground(new Color(199,199,206));
        answer_labelD.setForeground(new Color(15,15,15));
        answer_labelD.setFont(new Font("MV Boli",Font.PLAIN,20));

        seconds_left.setBounds(545,1,100,50);
        seconds_left.setBackground(new Color(25,25,25));
        seconds_left.setForeground(new Color(255,0,0));
        seconds_left.setFont(new Font("Ink Free",Font.BOLD,60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        time_label.setBounds(535,475,100,25);
        time_label.setBackground(new Color(50,50,50));
        time_label.setForeground(new Color(255,0,0));
        time_label.setFont(new Font("MV Boli",Font.PLAIN,16));
        time_label.setHorizontalAlignment(JTextField.CENTER);

        number_right.setBounds(225,225,200,100);
        number_right.setBackground(new Color(25,25,25));
        number_right.setForeground(new Color(15,15,15));
        number_right.setFont(new Font("Ink Free",Font.BOLD,50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        percentage.setBounds(225,325,200,100);
        percentage.setBackground(new Color(25,25,25));
        percentage.setForeground(new Color(15,15,15));
        percentage.setFont(new Font("Ink Free",Font.BOLD,50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

        frame.add(time_label);
        frame.add(seconds_left);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textarea);
        frame.add(textfield);
        frame.setVisible(true);

        nextQuestion();
    }
    public void nextQuestion() {

        if(index>=total_questions) {
            results();
        }
        else {
            textfield.setText("Question "+(index+1));
            textarea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
            timer.start();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(e.getSource()==buttonA) {
            answer= 'A';
            if(answer == answers[index]) {
                correct_guesses++;
            }
        }
        if(e.getSource()==buttonB) {
            answer= 'B';
            if(answer == answers[index]) {
                correct_guesses++;
            }
        }
        if(e.getSource()==buttonC) {
            answer= 'C';
            if(answer == answers[index]) {
                correct_guesses++;
            }
        }
        if(e.getSource()==buttonD) {
            answer= 'D';
            if(answer == answers[index]) {
                correct_guesses++;
            }
        }
        displayAnswer();
    }
    public void displayAnswer() {

        timer.stop();

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(answers[index] != 'A')
            answer_labelA.setForeground(new Color(255,0,0));
        if(answers[index] != 'B')
            answer_labelB.setForeground(new Color(255,0,0));
        if(answers[index] != 'C')
            answer_labelC.setForeground(new Color(255,0,0));
        if(answers[index] != 'D')
            answer_labelD.setForeground(new Color(255,0,0));

        Timer pause = new Timer(2000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                answer_labelA.setForeground(new Color(15,15,15));
                answer_labelB.setForeground(new Color(15,15,15));
                answer_labelC.setForeground(new Color(15,15,15));
                answer_labelD.setForeground(new Color(15,15,15));

                answer = ' ';
                seconds=5;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();
    }
    public void results(){

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int)((correct_guesses/(double)total_questions)*100);

        textfield.setText("RESULTS!");
        textarea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        number_right.setText("("+correct_guesses+"/"+total_questions+")");
        percentage.setText(result+"%");

        frame.add(number_right);
        frame.add(percentage);

    }
}
