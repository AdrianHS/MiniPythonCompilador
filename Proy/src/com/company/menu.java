package com.company;

import generated.PParser;
import generated.PScanner;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.List;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;



class menu extends JFrame {
	PScanner scanner = null;
	PParser parser = null;
	private int tamaño=17;
	private JTextArea areaTexto;
	private JTextArea areaErrores;
	private JButton cor,cop,peg,nue,gua,ab,correr,arbol;
	private JScrollPane scroll;
	private JScrollPane scrollErrores;
	private JComboBox tFuente;
	private Font areaFuente;
	private JTextField status;


	public menu () {
		super("Editor de textos");
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error al intentar cargar L&F");
		}
		areaFuente= new Font("Arial", Font.PLAIN, tamaño);
		areaTexto= new JTextArea(21,89);
		areaErrores= new JTextArea(10,145);

		areaTexto.setFont(areaFuente);
		menus ();
		barra();
		scroll= new JScrollPane(areaTexto);
		scrollErrores = new JScrollPane(areaErrores);
		status =  new JTextField();

		JPanel panel= new JPanel();
		JPanel panel1= new JPanel();
		JPanel panel2= new JPanel();
		JPanel panel3 = new JPanel();

		panel.setBackground(Color.lightGray);
		panel1.setBackground(Color.lightGray);
		panel2.setBackground(Color.lightGray);
		panel3.setBackground(Color.lightGray);

		panel3.add(scroll,BorderLayout.NORTH);
		panel3.add(scrollErrores,BorderLayout.SOUTH);

		getContentPane().add(panel,BorderLayout.SOUTH);
		getContentPane().add(panel1,BorderLayout.WEST);
		getContentPane().add(panel2,BorderLayout.EAST);
		getContentPane().add(panel3,BorderLayout.CENTER);
		getContentPane().add(status,BorderLayout.SOUTH);

		areaTexto.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {

				int linea = 1;
				int columna = 1;

				try {
					int caretpos = areaTexto.getCaretPosition();
					linea= areaTexto.getLineOfOffset(caretpos);
					columna = caretpos - areaTexto.getLineStartOffset(linea);

					// Ya que las líneas las cuenta desde la 0
					linea += 1;
				} catch(Exception ex) { }

				// Actualizamos el estado
				actualizarEstado(linea, columna);
			}
		});

		setSize(1200,800);
		setVisible(true);
		show ();
	}
	private void actualizarEstado(int linea, int columna) {
		status.setText("Linea: " + linea + " Columna: " + columna);
	}

	public void menus () {
		JMenuBar menus = new JMenuBar();
		JMenu archivo= new JMenu("Archivo");
		JMenuItem nue= new JMenuItem("Nuevo", new ImageIcon("images/hoja_6.gif"));
		nue.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					nuevo();
				}
			}
		);
		JMenuItem sal= new JMenuItem("Salir",new ImageIcon("images/salir.gif"));
		sal.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					String d=JOptionPane.showInputDialog("Desea salir y guardar el archivo S/N");
					if (d.equals("s")) {
						guardar();
						System.exit(0);
					}
					else if(d.equals("n")) {
						System.exit(0);
					}
					else {
						JOptionPane.showMessageDialog(null,"Caracter invalido");
					}
				}
			}
		);

		JMenuItem abr= new JMenuItem("Abrir",new ImageIcon("images/libro_1.gif"));
		abr.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					abrir ();
				}
			}
		);
		JMenuItem guar= new JMenuItem("Guardar",new ImageIcon("images/guardar.gif"));
		guar.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					guardar ();
				}
			}
		);
		JMenu editar= new JMenu("Editar");
		JMenuItem cor= new JMenuItem("Cortar", new ImageIcon("images/cut.gif"));
		cor.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					cortar ();
				}
			}
		);
		JMenuItem cop= new JMenuItem("Copiar",new ImageIcon("images/copiar_0.gif"));
		cop.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					copiar ();
				}
			}
		);
		JMenuItem peg= new JMenuItem ("Pegar",new ImageIcon("images/paste.gif"));
		peg.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					pegar ();
				}
			}
		);

		JMenuItem fuen= new JMenuItem("Tamaño de fuente",new ImageIcon("images/hoja_2.gif"));
		fuen.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					tamaño_f();
				}
			}
		);

		JMenu herramientas= new JMenu("Herramientas");
		JMenuItem run= new JMenuItem("Run", new ImageIcon("images/cut.gif"));
		run.addActionListener (
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						correr ();
					}
				}
		);
		JMenuItem aarbol= new JMenuItem("Arbol", new ImageIcon("images/cut.gif"));
		aarbol.addActionListener (
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						arbol ();
					}
				}
		);
		JMenuItem errors= new JMenuItem("Errores", new ImageIcon("images/cut.gif"));

		archivo.add(nue);
		archivo.add(sal);
		archivo.addSeparator();
		archivo.add(abr);
		archivo.add(guar);
		editar.add(cor);
		editar.add(cop);
		editar.add(peg);
		editar.addSeparator();
		editar.add(fuen);
		herramientas.add(run);
		herramientas.add(aarbol);
		menus.add(archivo);
		menus.add(editar);
		menus.add(herramientas);
		setJMenuBar(menus);
	}

	public void barra () {
		JToolBar barras= new JToolBar();

		nue= new JButton ();
		nue.setPreferredSize(new Dimension(70,70));
		nue.setIcon(new ImageIcon("images/hoja_6.gif"));
		nue.setMargin(new Insets(3,0,0,0));
		nue.setToolTipText("Nuevo");
		nue.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					nuevo ();
				}
			}
		);
		barras.add(nue);

		ab= new JButton();
		ab.setPreferredSize(new Dimension(70,70));
		ab.setIcon(new ImageIcon("images/libro_1.gif"));
		ab.setMargin(new Insets(2,0,0,0));
		ab.setToolTipText("Abrir");
		ab.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					abrir ();
				}
			}
		);
		barras.add(ab);

		gua= new JButton();
		gua.setPreferredSize(new Dimension(70,70));
		gua.setIcon(new ImageIcon("images/guardar.gif"));
		gua.setMargin(new Insets(2,0,0,0));
		gua.setToolTipText("Guardar");
		gua.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					guardar ();
				}
			}
		);
		barras.add(gua);

		barras.addSeparator();

		cor= new JButton();
		cor.setPreferredSize(new Dimension(70,70));
		cor.setIcon(new ImageIcon("images/cut.gif"));
		cor.setMargin(new Insets(2,0,0,0));
		cor.setToolTipText("Cortar");
		cor.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					cortar ();
				}
			}
		);
		barras.add(cor);

		cop= new JButton();
		cop.setPreferredSize(new Dimension(70,70));
		cop.setIcon(new ImageIcon("images/copiar_0.gif"));
		cop.setMargin(new Insets(-3,0,0,0));
		cop.setToolTipText("Copiar");
		cop.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					copiar ();
				}
			}
		);
		barras.add(cop);

		peg= new JButton();
		peg.setPreferredSize(new Dimension(70,70));
		peg.setIcon(new ImageIcon("images/paste.gif"));
		peg.setMargin(new Insets(2,0,0,0));
		peg.setToolTipText("Pegar");
		peg.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					pegar ();
				}
			}
		);
		barras.add(peg);

		JButton del= new JButton();
		del.setIcon(new ImageIcon("images/borrador.gif"));
		del.setMargin(new Insets(2,0,0,0));
		del.setToolTipText("BORRAR todo el texto");
		del.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					areaTexto.setText("");
				}
			}
		);
		barras.add(del);
		barras.addSeparator();

		tFuente= new JComboBox();
		tFuente.addItem("Tamaño Fuente");
		tFuente.addItem("10");
		tFuente.addItem("20");
		tFuente.addItem("30");
		tFuente.addItem("40");
		tFuente.addItem("50");
		tFuente.addItem("Personalizar");
		tFuente.setToolTipText("Tamaño de fuente");
		tFuente.addItemListener(
			new ItemListener () {
				public void itemStateChanged(ItemEvent e) {

					int elegido;
					elegido=tFuente.getSelectedIndex();
					switch (elegido) {

						case 1:
							areaFuente= new Font("Arial", Font.PLAIN, 10);
							areaTexto.setFont(areaFuente);
							break;

						case 2:
							areaFuente= new Font("Arial", Font.PLAIN, 20);
							areaTexto.setFont(areaFuente);
							break;

						case 3:
							areaFuente= new Font("Arial", Font.PLAIN, 30);
							areaTexto.setFont(areaFuente);
							break;

						case 4:
							areaFuente= new Font("Arial", Font.PLAIN, 40);
							areaTexto.setFont(areaFuente);
							break;

						case 5:
							areaFuente= new Font("Arial", Font.PLAIN, 50);
							areaTexto.setFont(areaFuente);
							break;
						case 6:
							tamaño=Integer.parseInt(JOptionPane.showInputDialog("Digite el tamaño de la fuente"));
							areaFuente= new Font("Arial", Font.PLAIN, tamaño);
							areaTexto.setFont(areaFuente);
							break;
					}
				}
			}
		);
		barras.add(tFuente);

		barras.addSeparator();

		correr= new JButton();
		correr.setPreferredSize(new Dimension(70,70));
		correr.setIcon(new ImageIcon("images/chulo.gif"));
		correr.setMargin(new Insets(5,2,0,0));
		correr.setToolTipText("RUN");
		correr.addActionListener (
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						correr ();
					}
				}
		);
		barras.add(correr);

		arbol= new JButton();
		arbol.setPreferredSize(new Dimension(70,70));
		arbol.setIcon(new ImageIcon("images/chulo.gif"));
		arbol.setMargin(new Insets(5,2,0,0));
		arbol.setToolTipText("Arbol");
		arbol.addActionListener (
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						arbol ();
					}
				}
		);
		barras.add(arbol);

		barras.addSeparator();

		getContentPane().add(barras,BorderLayout.NORTH);
	}

	public void nuevo () {
		new menu ();
	}

	public void abrir () {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result= fileChooser.showOpenDialog(this);
		if (result== JFileChooser.CANCEL_OPTION) return;
		File name= fileChooser.getSelectedFile();
		if(name.exists()) {
			if (name.isFile()) {
				try {
					BufferedReader input= new BufferedReader(new FileReader (name));
					StringBuffer buffer= new StringBuffer();
					String text;
					areaTexto.setText("");
					while ((text=input.readLine()) !=null)
						buffer.append(text+ "\n");
					areaTexto.append(buffer.toString());
				}
				catch (IOException ioException) {
					JOptionPane.showMessageDialog(null,"Error en el archivo", "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			else if (name.isDirectory ()) {
				String directory[] = name.list();
				areaTexto.append("\n\nContenido del directorio:\n");
				for (int i=0;i<directory.length; i++)
					areaTexto.append(directory [i]+"\n");
			}
			else {
				JOptionPane.showMessageDialog(null," No existe "," Error ",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void guardar () {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result= fileChooser.showSaveDialog(this);
		if (result== JFileChooser.CANCEL_OPTION) return;
			File name= fileChooser.getSelectedFile();
			try {
				PrintWriter output= new PrintWriter(new FileWriter( name+".txt"));
				output.write(areaTexto.getText());
				output.close();
			}
			catch (IOException ioException) {
				JOptionPane.showMessageDialog(null,"Error en el archivo","Error",JOptionPane.ERROR_MESSAGE);
			}
	}

	public void cortar () {
		areaTexto.cut();
	}

	public void copiar () {
		areaTexto.copy();
	}

	public void pegar () {
		areaTexto.paste();
	}

	public void tamaño_f () {
		tamaño=Integer.parseInt(JOptionPane.showInputDialog("Digite el tamaño de la fuente"));
		areaFuente= new Font("Arial", Font.PLAIN, tamaño);
		areaTexto.setFont(areaFuente);
	}
	public void correr(){
		JOptionPane.showMessageDialog(null," Run");
		PPrint pPrint= null;
		try {
			ANTLRInputStream input = new ANTLRInputStream(new FileReader("prueba.txt"));//areaTexto.getText());//"prueba.txt"));// se hace el input con la direccion de archivo
			scanner = new PScanner(input);// nuevo scanner con el input
			java.util.List<Token> lista = (java.util.List<Token>) scanner.getAllTokens();//se crea la lista,devuelve la una lista

			for (Token t : lista)
				System.out.println(t.getType() + " : '" + t.getText()+"'");

			scanner.reset();
			scanner = new PScanner(input);
			CommonTokenStream tokens = new CommonTokenStream(scanner);
			parser = new PParser(tokens);
			//parser.program();



			pPrint = new PPrint();


			ParseTree raiz = parser.program();
			pPrint.visit(raiz);
			//System.out.println(raiz.toStringTree(parser));
		}
		catch(Exception e){
			System.out.println("No hay archivo");
			System.out.println(e.getMessage());
		}
	}
	public void arbol(){
		JOptionPane.showMessageDialog(null," Arbol");
	}


	public static void main (String []args) {
		new menu();
	}
}