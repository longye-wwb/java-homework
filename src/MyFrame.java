import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import  af.swing.layout.AfAnyWhere;
import  af.swing.layout.AfMargin;


public class MyFrame extends JFrame implements ActionListener
{
	public BgPanel bgpanel;
	ImageIcon logo = new ImageIcon("/images/logo.png");
	JMenuItem gameOpenCmd,gameExitCmd,helpAbout,helpManual,copyRight;
	private JLabel logoLabel = new JLabel(logo);
		JLabel name=new JLabel();
		JButton start=new JButton();
		JButton help=new JButton();
		JButton developer=new JButton();
		JLabel matching=new JLabel();
		public MyFrame()
	{
			bgpanel=new BgPanel();
			this.setTitle("jojo��������Ϸ---by wwb,dcy");
			this.setSize(830,620);
			setResizable(false);
			setLocationRelativeTo(getOwner()); // ��Ļ����
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new  AfAnyWhere());
		name.setText("J O J O �� �� �� �� ð �");
		name.setFont(new Font("΢���ź�",0 , 64));
		start.setText("��ʼ");
		start.setFont(new Font("΢���ź�",0 , 22));
		
		//��ӿؼ�
		this.add(name,new  AfMargin(70, -1, -1, -1));
	this.add(bgpanel,new AfMargin(0, 0, 0,0));
	this.add(start,new AfMargin(300,-1,-1,-1));
		start.setPreferredSize(new Dimension(150,70));
		//��Ӳ˵���
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		JMenu fileMenu = new JMenu("��Ϸ");
		menubar.add(fileMenu);
		 gameOpenCmd = new JMenuItem("����Ϸ");
		fileMenu.add(gameOpenCmd);
		fileMenu.addSeparator();
		 gameExitCmd = new JMenuItem("�˳�");
		fileMenu.add(gameExitCmd);
		// �����˵�
		JMenu helpMenu = new JMenu("����");
		menubar.add(helpMenu);
		 helpAbout = new JMenuItem("������");
		 helpManual = new JMenuItem("����˵��");
		 copyRight=new JMenuItem("��Ȩ����");
		helpMenu.add(helpAbout);
		fileMenu.addSeparator();
		helpMenu.add(helpManual);

		//��ʼ��ť������
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Main();
				MyFrame.this.dispose();
				}
				});
		//�����˵��������
		helpAbout.addActionListener(this);
		helpManual.addActionListener(this);
		copyRight.addActionListener(this);
		gameOpenCmd.addActionListener(this);
		gameExitCmd.addActionListener(this);
		
	 	
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == gameExitCmd) {
				this.dispose();
			}
			
			if (e.getSource() == helpManual) {
				JOptionPane.showMessageDialog(this, "��Ϸ���ƣ�jojo�Ķ�����ð�ա�\r\n"
						+ "��Ϸ���ࣺ�����Ծ�����������Ϸ��\r\n"
						+ "��ϷĿ�꣺������������\r\n"
						+ "��Ϸ���ݣ�������ҽ�����Ϸ��ϵͳ�������ÿ��17���ƣ�Ԥ��3�ŵ��ƣ�����ȷ������ǰ��Ҳ��ܲ鿴��\r\n"
						+ "�ӵ�����ʼ���������˳����ƣ�ֱ��һ����ҳ��������ƺ���Ϸ������"
						);
			}
			if (e.getSource() == gameOpenCmd) {
				 //this.restart();
				JOptionPane.showMessageDialog(this, "���ڼ���ˮƽ���ޣ��������¿�����Ϸ�����˳���Ϸ�����´�");
			}
			if (e.getSource() == helpAbout) {
				JOptionPane.showMessageDialog(this, "           ����ҵ       "+"      ���Ĳ�");
			}
			if (e.getSource() == copyRight) {
				SingletonClass right=SingletonClass.getSingletonInstance()  ;
				JOptionPane.showMessageDialog(this,right.getCopyRight() );
			}
		}

	
	
}
