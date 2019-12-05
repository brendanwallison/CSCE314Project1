package main;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigInteger;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = -3880026026104218593L;
	private Primes m_Primes;
	private int primeCount;
	private int crossCount;
	private int largestPrime;
	private int largestCrossLeft;
	private int largestCrossRight;
	private JTextField tfPrimeFileName = new JTextField();
	private JTextField tfCrossFileName = new JTextField();
	private JLabel lblPrimeCount = new JLabel();
	private JLabel lblCrossCount = new JLabel();
	private JLabel lblLargestPrime = new JLabel();
	private JLabel lblLargestCross = new JLabel();
	private JLabel lblStatus = new JLabel();

	MainWindow(String name, Primes p)
	{
		setSize(1000,400);
		lblStatus.setText("Bored");
		setTitle(name);
		m_Primes = p;
		//primeCount = m_Primes.primeCount();
		//crossCount = m_Primes.crossesCount();
		updateStats();
		
		GridBagConstraints gbcRow = new GridBagConstraints();
		gbcRow.fill = GridBagConstraints.HORIZONTAL;
		gbcRow.gridy=0;
		//gbcFilename.fill = GridBagConstraints.HORIZONTAL;

		lblPrimeCount.setText(String.valueOf(primeCount));
		lblCrossCount.setText(String.valueOf(crossCount));
		//lblLargestPrime.setText(String.valueOf(largestPrime));
		//lblLargestCross.setText(String.valueOf(largestCrossLeft + ", " + String.valueOf(largestCrossRight)));
		//JDialog mWindow = new JDialog(this, name);
		GridBagLayout gridLayout = new GridBagLayout();
		//getContentPane().setBackground(new Color(80, 0, 0));
		getContentPane().setLayout(gridLayout);

		
		
		GridBagConstraints gbcFilename = new GridBagConstraints();
		gbcFilename.fill = GridBagConstraints.HORIZONTAL;
		//gbcFilename.anchor = GridBagConstraints.WEST;
		gbcFilename.ipady = 10;
		gbcFilename.gridwidth = 2;
		gbcFilename.weightx = .5;
		gbcFilename.insets = new Insets(1, 2, 0, 0);
		gbcFilename.gridx = 0;
		gbcFilename.gridy = 0;

		GridBagConstraints gbcMetadata = new GridBagConstraints();
		//gbcMetadata.fill = GridBagConstraints.HORIZONTAL;
		gbcMetadata.anchor = GridBagConstraints.EAST;
		gbcMetadata.ipady = 10;
		gbcMetadata.weightx = .5;
		gbcMetadata.insets = new Insets(1, 2, 0, 0);
		gbcMetadata.gridx = 1;
		gbcMetadata.gridy = 0;
		gbcMetadata.gridwidth = 9;
		

		
		lblPrimeCount.setFont(new Font("Tahoma", Font.PLAIN, 12));

		//PRIME AREA OF MAIN WINDOW		
		tfPrimeFileName.setColumns(6);
		lblPrimeCount.setLabelFor(tfPrimeFileName);

		add(tfPrimeFileName,gbcFilename);
		add(lblPrimeCount,gbcMetadata);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridBagLayout());

		JButton btnLoadPrimes = new JButton("Load");
		btnLoadPrimes.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					String filename = tfPrimeFileName.getText();
					FileAccess.loadPrimes(m_Primes, filename);
				} catch (Exception ex)
				{
					lblStatus.setText("Could Not Read File");
				}
				updateStats();
			}
		});
		
		JButton btnSavePrimes = new JButton("Save");
		btnSavePrimes.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					String filename = tfPrimeFileName.getText();
					FileAccess.savePrimes(m_Primes, filename);
				} catch (Exception ex)
				{
					lblStatus
							.setText("Type a valid file path");
				}

			}
		});
		
		JLabel lblPrimeArea = new JLabel();
		lblPrimeArea.setText("Primes File");
		lblPrimeArea.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		GridBagConstraints gbcBigText = new GridBagConstraints();
		gbcBigText.gridx = 0;
		gbcBigText.gridy = 1;

		add(lblPrimeArea,gbcBigText);
		
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 1;
		add(btnLoadPrimes,gbcPanel);
		
		gbcPanel.gridx = 2;
		add(btnSavePrimes,gbcPanel);
		
		//CROSS AREA OF MAIN WINDOW		
		
		tfCrossFileName.setColumns(6);
		lblCrossCount.setLabelFor(tfCrossFileName);

		gbcFilename.gridy = 2;
		gbcMetadata.gridy = 2;

		add(tfCrossFileName,gbcFilename);
		add(lblCrossCount,gbcMetadata);


		JButton btnLoadCrosses = new JButton("Load");
		btnLoadCrosses.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					String filename = tfCrossFileName.getText();
					FileAccess.loadCrosses(m_Primes, filename);
				} catch (Exception ex)
				{
					lblStatus
							.setText("Type a valid file path");
				}
				updateStats();
			}
		});
		
		JButton btnSaveCrosses = new JButton("Save");
		btnSaveCrosses.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					String filename = tfCrossFileName.getText();
					FileAccess.saveCrosses(m_Primes, filename);
				} catch (Exception ex)
				{
					lblStatus
							.setText("Type a valid file path");
				}

			}
		});	
		
		
		JLabel lblCrossArea = new JLabel();
		lblCrossArea.setText("Cross File");
		lblCrossArea.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		gbcBigText.gridx = 0;
		gbcBigText.gridy = 3;

		add(lblCrossArea,gbcBigText);
		
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 3;
		add(btnLoadCrosses,gbcPanel);
		
		gbcPanel.gridx = 2;
		add(btnSaveCrosses,gbcPanel);	
		
		
		JButton btnGeneratePrimes = new JButton("Generate Primes");
		btnGeneratePrimes.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				popupGeneratePrimes();
				updateStats();
			}
		});
		
		JButton btnGenerateCrosses = new JButton("Generate Crosses");
		btnGenerateCrosses.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				m_Primes.generateHexPrimes();
				updateStats();
			}
		});	
		
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 4;
		add(btnGeneratePrimes,gbcPanel);
		
		
//		GridBagConstraints gbcPanel2 = new GridBagConstraints();
//		JPanel pnlStatus = new JPanel();
//		pnlStatus.setLayout(new GridBagLayout());
//		gbcPanel.anchor = GridBagConstraints.SOUTHWEST;
//		gbcPanel.weightx = .5;
//		gbcPanel.insets = new Insets(1, 2, 0, 0);
//		gbcPanel.gridx = 0;
//		gbcPanel.gridy = 0;
//		pnlStatus.add(lblLargestPrime, gbcPanel2);
//		gbcPanel2.gridy = 1;
//		pnlStatus.add(lblLargestCross, gbcPanel2);
		
		gbcPanel.gridx = 1;
		add(lblLargestPrime,gbcPanel);
		
		gbcPanel.gridx = 2;
		add(btnGenerateCrosses,gbcPanel);

		gbcPanel.gridx = 0;
		gbcPanel.gridy = 5;
		add(lblStatus, gbcPanel);

		
		pack();
		
		setVisible(true);
		


//		JLabel lblCount = new JLabel("Number of Primes to Generate");
//		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		pnlGenerate.add(lblCount, gbcPanel);

		//JTextField tfCount = new JTextField();
//		lblCount.setLabelFor(tfPrimeFileName);
//		tfPrimeFileName.setColumns(30);
//		gbcPanel.gridx = 1;
//		pnlGenerate.add(tfPrimeFileName, gbcPanel);
		
//		JLabel lblStart = new JLabel("Starting Number (does not have to be prime)");
//		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		gbcPanel.gridx = 0;
//		gbcPanel.gridy = 1;
//		pnlGenerate.add(lblStart, gbcPanel);
//
//		JTextField tfStart = new JTextField();
//		lblStart.setLabelFor(tfStart);
//		tfStart.setColumns(30);
//		gbcPanel.gridx = 1;
//		pnlGenerate.add(tfStart, gbcPanel);
//
//		dPrimes.add(pnlGenerate, gbcDialog);
//		
//		popupGeneratePrimes();
		//JPanel panel = new JPanel();
		//getContentPane().add(panel);
	}

	protected void popupGeneratePrimes()
	{
		JDialog dPrimes = new JDialog(this, "Prime Number Generation");
		GridBagLayout gridLayout = new GridBagLayout();
		dPrimes.getContentPane().setBackground(new Color(52, 0, 0));
		dPrimes.getContentPane().setLayout(gridLayout);

		GridBagConstraints gbcDialog = new GridBagConstraints();
		gbcDialog.fill = GridBagConstraints.HORIZONTAL;
		gbcDialog.anchor = GridBagConstraints.WEST;
		gbcDialog.ipady = 10;
		gbcDialog.weightx = .5;
		gbcDialog.insets = new Insets(1, 2, 0, 0);
		gbcDialog.gridx = 0;
		gbcDialog.gridy = 0;

		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1, 2, 0, 0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;

		JPanel pnlGenerate = new JPanel();
		pnlGenerate.setLayout(new GridBagLayout());

		JLabel lblCount = new JLabel("Number of Primes to Generate");
		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlGenerate.add(lblCount, gbcPanel);

		JTextField tfCount = new JTextField();
		lblCount.setLabelFor(tfCount);
		tfCount.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfCount, gbcPanel);

		JLabel lblStart = new JLabel("Starting Number (does not have to be prime)");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlGenerate.add(lblStart, gbcPanel);

		JTextField tfStart = new JTextField();
		lblStart.setLabelFor(tfStart);
		tfStart.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfStart, gbcPanel);

		dPrimes.add(pnlGenerate, gbcDialog);

		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridBagLayout());

		JButton btnGeneratePrimes = new JButton("Generate Primes");
		btnGeneratePrimes.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					BigInteger start = new BigInteger(tfStart.getText());
					int count = Integer.parseInt(tfCount.getText());
					m_Primes.generatePrimes(start, count);
					lblStatus.setText("Status: Excited. Primes have been generated.");
					updateStats();
					dPrimes.dispose();
				} catch (NumberFormatException ex)
				{
					lblStatus.setText("You failed to type in an integer successfully. You are terrible at math. Shame.");
					dPrimes.dispose();
				}

			}
		});
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlButtons.add(btnGeneratePrimes, gbcPanel);

		JButton btnCancel = new JButton("Cancel Generation");
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dPrimes.dispose();
			}
		});
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 1;
		pnlButtons.add(btnCancel, gbcPanel);

		gbcDialog.gridy = 1;
		dPrimes.add(pnlButtons, gbcDialog);

		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new GridBagLayout());

		gbcPanel.anchor = GridBagConstraints.SOUTHWEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1, 2, 0, 0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;

		JLabel lblNotice = new JLabel(
				"Warning: This application is single threaded, and will freeze while generating primes.");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlStatus.add(lblNotice, gbcPanel);

		gbcDialog.gridy = 2;
		dPrimes.add(pnlStatus, gbcDialog);

		dPrimes.setSize(200, 200);
		dPrimes.pack(); // Knowing what this is and why it is needed is important. You should read the
						// documentation on this function!
		dPrimes.setVisible(true);
	}

	
	
	// This function updates all the GUI statistics. (# of primes, # of crosses,
	// etc)
	private void updateStats()
	{
		primeCount = m_Primes.primeCount();
		crossCount = m_Primes.crossesCount();
		largestPrime = m_Primes.sizeofLastPrime();
		largestCrossLeft = m_Primes.sizeofLastCross().left();
		largestCrossRight = m_Primes.sizeofLastCross().right();
		lblPrimeCount.setText(String.valueOf(primeCount));
		lblCrossCount.setText(String.valueOf(crossCount));
		lblLargestPrime.setText("<html><center>The largest prime has " + largestPrime + " digits<br> The largest cross has " + largestCrossLeft + " and " + largestCrossRight + " digits");
		lblLargestCross.setText("The largest cross has " + largestCrossLeft + " and " + largestCrossRight + " digits");
		super.update(this.getGraphics());
	}

}
