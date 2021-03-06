import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class ExampleJFrame extends JFrame {
 private JPanel contentPane;
 /**
  * Launch the application.
  */
 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
    	ExampleJFrame frame = new ExampleJFrame();
     frame.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }
 
 private ImageIcon[] image = new ImageIcon[2];
 JLabel ImageLabel;
 /**
  * Create the frame.
  * @throws IOException 
  */
 public ExampleJFrame() throws IOException {
  image[0] = new ImageIcon("C:\\Users\\nusrans\\Desktop\\1.png");
  image[1] = new ImageIcon("C:\\Users\\nusrans\\Desktop\\2.png");
  
  ImageLabel = new JLabel(image[0]);
  
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBounds(100, 100, 204, 160);
  contentPane = new JPanel();
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  contentPane.setLayout(new BorderLayout(0, 0));
  setContentPane(contentPane);
  
  Panel panel = new Panel();
  contentPane.add(panel, BorderLayout.CENTER);
  
  panel.add(ImageLabel);
  
  Panel panel_1 = new Panel();
  contentPane.add(panel_1, BorderLayout.EAST);
  
  Button button = new Button("Click");
  contentPane.add(button, BorderLayout.SOUTH);
  button.addMouseListener(new MouseAdapter() {
   
   public void mouseClicked(MouseEvent arg0) {
    ImageLabel.setIcon(image[1]);
   }
  });
 }
}