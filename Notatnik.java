package pracazespołowaniestacjonarne;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.black;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

public class Notatnik extends JFrame implements ActionListener {

    private JTextArea textArea;
    
    public Notatnik() {

        super();
        setTitle("Notatnik");

        Toolkit zestaw = Toolkit.getDefaultToolkit();
        Dimension rozmiarEkranu = zestaw.getScreenSize();
        int szerEkranu = rozmiarEkranu.width;
        int wysEkranu = rozmiarEkranu.height;
        setBounds(szerEkranu / 4, wysEkranu / 4, szerEkranu / 2, wysEkranu / 2);

        JMenuBar pasekMenu = new JMenuBar();

        JMenu mPlik = new JMenu("Plik");
        mPlik.setMnemonic('P');

        JMenuItem otworz = new JMenuItem("Otworz");
        otworz.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        otworz.addActionListener(this);
        otworz.setActionCommand("11");

        JMenuItem zapisz = new JMenuItem("Zapisz");
        zapisz.setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
        zapisz.addActionListener(this);
        zapisz.setActionCommand("12");
        
        
        
        JMenuItem zakoncz = new JMenuItem("Zakoncz");
        zakoncz.setAccelerator(KeyStroke.getKeyStroke("ctr K"));
        zakoncz.addActionListener(this);
        zakoncz.setActionCommand("13");

        mPlik.add(otworz);
        mPlik.add(zapisz);
        mPlik.addSeparator();
        mPlik.add(zakoncz);

        JMenu mEdycja = new JMenu("Edycja");
        mEdycja.setMnemonic('E');
        JRadioButton duza_czcionka= new JRadioButton("Duza czcionka");
        JRadioButton mala_czcionka= new JRadioButton("Mala czcionka",true);
        duza_czcionka.addActionListener(this);
        duza_czcionka.setActionCommand("21");
        mala_czcionka.addActionListener(this);
        mala_czcionka.setActionCommand("22");
        
        JPopupMenu menuPopUP = new JPopupMenu();
        JMenuItem metal = new JMenuItem("metal look-and-feel");
	JMenuItem windows = new JMenuItem("windows look-and-feel");
	JMenuItem motif = new JMenuItem("motif look-and-feel");
        
        menuPopUP.add(metal);
        menuPopUP.add(windows);
        menuPopUP.add(motif);
        
        
    
        
        
        ButtonGroup bg2 = new ButtonGroup();

        bg2.add(duza_czcionka);
        bg2.add(mala_czcionka);
        setResizable(false);
        
        
        
        JMenuItem wyczysc = new JMenuItem("Wyczyść");
        mEdycja.add(duza_czcionka);
        mEdycja.add(mala_czcionka);
        mEdycja.addSeparator();
        mEdycja.add(wyczysc);
        wyczysc.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
        
        wyczysc.addActionListener(this);
        wyczysc.setActionCommand("23");

        JMenu mPomoc = new JMenu("Pomoc");
        mPomoc.setMnemonic('o');
        JMenuItem autor= new JMenuItem("o Autorze");
        autor.addActionListener(this);
        autor.setActionCommand("31");
        mPomoc.add(autor);
        
        
        pasekMenu.add(mPlik);
        pasekMenu.add(mEdycja);
        pasekMenu.add(mPomoc);

        setJMenuBar(pasekMenu);

        textArea = new JTextArea();
        JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        setLayout(new BorderLayout());
        
        
        add(sp, BorderLayout.CENTER);

        textArea.setComponentPopupMenu(menuPopUP);
        
        Border obramowanieE = BorderFactory.createEtchedBorder();
        Border obramowanieL = BorderFactory.createLineBorder(black);
        Border wstawkiObramowaniaE = BorderFactory.createTitledBorder(obramowanieE,"Wstawiki");
        Border kolorTlaObramowanieE=BorderFactory.createTitledBorder(obramowanieE,"kolor tła");
        Border kolorCzcionkiObramowanieL=BorderFactory.createTitledBorder(obramowanieL,"Kolory Czcionki");
        
        
        JPanel panelLewy = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelPrawy = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelSrodek = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel panelPrzyciski = new JPanel(new GridLayout(1, 2));

        panelPrzyciski.add(panelLewy);
        panelPrzyciski.add(panelSrodek);
        panelPrzyciski.add(panelPrawy);

        add(panelPrzyciski, BorderLayout.SOUTH);

        JButton tytul = new JButton("tytuł");
        JButton podpis = new JButton("podpis");
        tytul.addActionListener(this);
        tytul.setActionCommand("41");
        podpis.addActionListener(this);
        podpis.setActionCommand("42");
        panelLewy.add(tytul);
        panelLewy.add(podpis);
        
        panelLewy.setBorder(wstawkiObramowaniaE);
        panelPrawy.setBorder(kolorTlaObramowanieE);
        panelSrodek.setBorder(kolorCzcionkiObramowanieL);
        
        
        JRadioButton bi = new JRadioButton("biały", true);
        JRadioButton zo = new JRadioButton("żółty");
        JRadioButton zi = new JRadioButton("zielony");

        bi.addActionListener(this);
        zo.addActionListener(this);
        zi.addActionListener(this);

        bi.setActionCommand("51");
        zo.setActionCommand("52");
        zi.setActionCommand("53");

        panelPrawy.add(bi);
        panelPrawy.add(zo);
        panelPrawy.add(zi);

        ButtonGroup bg1 = new ButtonGroup();

        bg1.add(bi);
        bg1.add(zo);
        bg1.add(zi);

        setResizable(false);
        
        String[] czKolory = {"czerwona","zielona","niebieska","czarna","biala"};
        JLabel etyKolory = new JLabel("Kolory:  ");
	JComboBox kolorList = new JComboBox(czKolory);
	kolorList.setSelectedIndex(3);
        
        panelSrodek.add(etyKolory);
        panelSrodek.add(kolorList);
        kolorList.addActionListener(this);
        kolorList.setActionCommand("60");
        
        metal.addActionListener(this);
        windows.addActionListener(this);
        //motif.addActionListener(this);
        
        metal.setActionCommand("70");
        windows.setActionCommand("71");
        //motif.setActionCommand("70");
        
        
        

    }

    public static void main(String[] args) throws Exception {

        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        Notatnik nt = new Notatnik();
        nt.setVisible(true);
        nt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //sprawdzenie ktory przycisk zostal wcisniety i wykonanie odpowiedniej akcji
        switch (Integer.parseInt(ae.getActionCommand())) {
            case 11: {
                JFileChooser pliki = new JFileChooser(".");
                if (JFileChooser.APPROVE_OPTION==pliki.showOpenDialog(this))
		try
		{
			File f=pliki.getSelectedFile();
			setTitle(f.getAbsolutePath()+" Notatnik");
			BufferedReader br = new BufferedReader(new FileReader(f));
			String temp="";
			while (br.ready())
			{
				temp+=br.readLine()+"\n";
			}
			textArea.setText(temp);
		}
		catch (IOException ex)
		{
			System.out.println("Brak pliku");
		}
                break;
            }
            case 12: {
                JFileChooser pliki = new JFileChooser(".");
	if (JFileChooser.APPROVE_OPTION==pliki.showSaveDialog(this))
		try
		{
			File f=pliki.getSelectedFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(textArea.getText());
			bw.flush();
			bw.close();
		}
		catch (IOException ee)
		{
			System.out.println("Problemy z zapisem");
		}
                break;
            }
            case 13: {
                System.exit(0);
                break;
            }
            case 21: {
                Font f = new Font("Arial",Font.PLAIN,18);
                textArea.setFont(f);
                break;
            }
            case 22: {
                Font f = new Font("Arial",Font.PLAIN,10);
                textArea.setFont(f);
                break;
            }
            case 23: {
                textArea.setText("");
                break;
            }
            case 31: {
                JOptionPane.showMessageDialog(this,"Autor: Jan Kowalski");
                break;
            }
            case 41: {
                textArea.setText("Szanowny Panie \n\n"+textArea.getText());
                break;
            }
            case 42: {
                textArea.setText(textArea.getText()+"\n\n Z poważaniem");
                break;
            }
            case 51: {
                textArea.setBackground(Color.WHITE);
                break;
            }
            case 52: {
                textArea.setBackground(Color.YELLOW);
                break;
            }
            case 53: {
                textArea.setBackground(Color.GREEN);
                break;
            }
            case 60: {
                JComboBox komboBox = (JComboBox)(ae.getSource());
                switch (komboBox.getSelectedIndex())
			{
				case 0:
				{
					textArea.setForeground(Color.RED);
					break;
				}
				case 1:
				{
					textArea.setForeground(Color.GREEN);
					break;
				}
                                case 2:
				{
					textArea.setForeground(Color.BLUE);
					break;
				}
                                case 3:
				{
					textArea.setForeground(Color.BLACK);
					break;
				}
                                case 4:
				{
					textArea.setForeground(Color.WHITE);
					break;
				}

			
			}  
                break;
            }
           
          
        }
    }

}
