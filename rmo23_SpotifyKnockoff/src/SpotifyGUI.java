import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * @author Riley O'Neill
 * 2/13/2018
 * @version 1.0
 */

public class SpotifyGUI {

	private JFrame frame;
	private JTextField txtSearch;
	private JButton jbutton;
	private ButtonGroup group;
	private JRadioButton radioButton;
	private JTable tblData;
	private DefaultTableModel musicData;
	static ErrorLogger errorLogger = new ErrorLogger();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpotifyGUI window = new SpotifyGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}




	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public SpotifyGUI() throws IOException {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		Spotify spotify = new Spotify();
		frame = new JFrame("Spotify");
		frame.setBounds(100, 100, 1000, 600);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel selectViewLbl = new JLabel("Select View:");
		selectViewLbl.setBounds(80, 80, 100, 25);
		frame.add(selectViewLbl);

		JRadioButton songButton  = new JRadioButton("Songs");
		songButton.setBounds(100, 100, 100, 25);
		songButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(songButton.isSelected()) {
					try {
						musicData = spotify.searchSongs("");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tblData.setModel(musicData);
				}
			}
		});

		JRadioButton albumButton = new JRadioButton("Albums");
		albumButton.setBounds(100, 125, 100, 25);
		albumButton.setSelected(true);
		albumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(albumButton.isSelected()) {

					try {
						musicData = spotify.searchAlbums("");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// TODO Auto-generated catch block

					// TODO Auto-generated catch block
				}
				tblData.setModel(musicData);
			}

		});

		JRadioButton artistButton = new JRadioButton("Artists");
		artistButton.setBounds(100, 150, 100, 25);
		artistButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(artistButton.isSelected()) {
					try {
						musicData = spotify.searchArtists("");
					} catch (IOException e1) {
						// TODO Auto-generated catch block

					}
					tblData.setModel(musicData);

				}
			}});

		ButtonGroup group = new ButtonGroup();
		group.add(songButton);
		group.add(albumButton);
		group.add(artistButton);

		frame.add(songButton);
		frame.add(albumButton);
		frame.add(artistButton);

		JLabel searchLbl = new JLabel("Search");
		searchLbl.setBounds(20,290,200,30);
		frame.add(searchLbl);

		txtSearch = new JTextField();
		txtSearch.setBounds(20, 320, 200, 30);
		frame.add(txtSearch);
		txtSearch.setColumns(10);

		musicData = spotify.searchAlbums("");
		tblData = new JTable(musicData);
		tblData.setBounds(300, 45, 600, 400);
		tblData.setFillsViewportHeight(true);
		tblData.setShowGrid(true);
		tblData.setGridColor(Color.BLACK);
		frame.getContentPane().add(tblData);


		tblData.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {

				//String id =  (String) musicData.getValueAt(tblData.getSelectedRow(), 0);
				//				getSongsFromAlbum
				System.out.println("Click.");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		}); 




		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(songButton.isSelected()) {
					try {
						musicData = spotify.searchSongs(txtSearch.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tblData.setModel(musicData);
				}
				if(albumButton.isSelected()) {
					try {
						musicData = spotify.searchAlbums(txtSearch.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tblData.setModel(musicData);
				}
				if(artistButton.isSelected()) {
					try {
						musicData = spotify.searchArtists(txtSearch.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tblData.setModel(musicData);
				}
			}
		});

		btnSearch.setBounds(100, 360, 120,30);
		frame.add(btnSearch);


	}



}


