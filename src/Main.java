import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Main extends JFrame implements ActionListener,Runnable{
	File f;
	 URI uri;
	    URL url; 
	public Container container = null;// �������� 
	JMenuItem gameOpenCmd,gameExitCmd,helpAbout,helpManual,copyRight;// ����˵���ť
	JButton landlord[]=new JButton[2];//��������ť
	JButton publishCard[]=new JButton[2];//���ư�ť
	int dizhuFlag;//������־
	int turn;
	JLabel dizhu; //����ͼ��
	List<Card> currentList[] =new ArrayList[3]; //  ��ǰ�ĳ���
	List<Card> playerList[] = new ArrayList[3]; // ����3����ұ�
	List<Card> lordList;//������
	Card card[] = new Card[56]; // ����54����
	//Card3 card[]=new Card3[24];
	JTextField time[]=new JTextField[3]; //��ʱ��
	Time t; //��ʱ�����̣߳�
	boolean nextPlayer=false; //ת����ɫ
	public Main(){
//		this.Music();
		Init();// ��ʼ��
		SetMenu();// �����˵� ��ť(������������,��ʱ��)
		this.setVisible(true);
		CardInit();//����
		getLord(); //�����ƿ�ʼ������
		time[1].setVisible(true);
		//�̰߳�ȫ��,�ѷ����̵߳�UI���Ʒŵ�����
		t=new Time(this,10);//��10��ʼ����ʱ
		t.start();
		
		
	}
	// ������
	public void getLord(){
		//System.out.println(CardType.c0.toString());
		for(int i=0;i<2;i++)
			landlord[i].setVisible(true);
	}
	//��ʼ����
	// ����ϴ��
	public void CardInit2() {
		int count = 1;
		//��ʼ����
		for (int i = 1; i <= 12; i++) {
					card[count] = new Card(this, 1 + "-" + i, false);
					card[count].setLocation(350, 50);
					container.add(card[count]);
					count++;
				
			}
		
		//����˳��
		for(int i=0;i<100;i++){
			Random random=new Random();
			int a=random.nextInt(24)+1;
			int b=random.nextInt(24)+1;
			Card k=card[a];
			card[a]=card[b];
			card[b]=k;
		}
		//��ʼ����
		for(int i=0;i<3;i++)
			playerList[i]=new ArrayList<Card>(); //�����
		lordList=new ArrayList<Card>();//����������
		int t=0;
		for(int i=1;i<=24;i++)
		{
			/*if(i>=52)//������
			{
				Common.move(card[i], card[i].getLocation(),new Point(300+(i-52)*80,10));
				lordList.add(card[i]);
				continue;
			}*/
			switch ((t++)%3) {
			case 0:
				//������
				Common.move(card[i], card[i].getLocation(),new Point(50,60+i*5));
				playerList[0].add(card[i]);
				break;
			case 1:
				//��
				Common.move(card[i], card[i].getLocation(),new Point(180+i*7,450));
				playerList[1].add(card[i]);
				card[i].turnFront(); //��ʾ����
				break;
			case 2:
				//�ұ����
				Common.move(card[i], card[i].getLocation(),new Point(700,60+i*5));
				playerList[2].add(card[i]);
				break;
			}
			//card[i].turnFront(); //��ʾ����
			container.setComponentZOrder(card[i], 0);
		}
		//���������򣬴Ӵ�С
		for(int i=0;i<3;i++)
		{
			Common.order(playerList[i]);
			Common.rePosition(this,playerList[i],i);//���¶�λ
		}
		dizhu=new JLabel(new ImageIcon("images/logo.gif"));
		dizhu.setVisible(false);
		dizhu.setSize(88, 55);
		container.add(dizhu);
		
	}
	public void CardInit() {
		
		int count = 1;
		//��ʼ����
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 13; j++) {
				if ((i == 5) && (j > 2))
					break;
				else {
					card[count] = new Card(this, i + "-" + j, false);
					card[count].setLocation(350, 50);
					container.add(card[count]);
					count++;
				}
			}
		}
		//����˳��
		for(int i=0;i<100;i++){
			Random random=new Random();
			int a=random.nextInt(54)+1;
			int b=random.nextInt(54)+1;
			Card k=card[a];
			card[a]=card[b];
			card[b]=k;
		}
		//��ʼ����
		for(int i=0;i<3;i++)
			playerList[i]=new ArrayList<Card>(); //�����
		lordList=new ArrayList<Card>();//����������
		int t=0;
		for(int i=1;i<=54;i++)
		{
			if(i>=52)//������
			{
				Common.move(card[i], card[i].getLocation(),new Point(300+(i-52)*80,10));
				lordList.add(card[i]);
				continue;
			}
			switch ((t++)%3) {
			case 0:
				//������
				Common.move(card[i], card[i].getLocation(),new Point(50,60+i*5));
				playerList[0].add(card[i]);
				break;
			case 1:
				//��
				Common.move(card[i], card[i].getLocation(),new Point(180+i*7,450));
				playerList[1].add(card[i]);
				card[i].turnFront(); //��ʾ����
				break;
			case 2:
				//�ұ����
				Common.move(card[i], card[i].getLocation(),new Point(700,60+i*5));
				playerList[2].add(card[i]);
				break;
			}
			//card[i].turnFront(); //��ʾ����
			container.setComponentZOrder(card[i], 0);
		}
		//���������򣬴Ӵ�С
		for(int i=0;i<3;i++)
		{
			Common.order(playerList[i]);
			Common.rePosition(this,playerList[i],i);//���¶�λ
		}
		dizhu=new JLabel(new ImageIcon("images/logo.gif"));
		dizhu.setVisible(false);
		dizhu.setSize(88, 55);
		container.add(dizhu);
	}

	// ��ʼ������
	public void Init() {

		this.setTitle("jojo��������Ϸ---by wwb,dcy");
		this.setSize(830,620);
		setResizable(false);
		setLocationRelativeTo(getOwner()); // ��Ļ����
		container = this.getContentPane();
		container.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setBackground(new Color(206, 216, 242)); // ����Ϊ��ɫ
	
	}
//	void Music(){               //ע�⣬javaֻ�ܲ����������ʣ���.wav���ָ�ʽ
//        try {      
//       f = new File("D:\\Java\\Workplace\\��Ŀ\\src\\��Ұ�v�� (����� �椦��) - il vento d'oro.wav"); //����·��
//        uri = f.toURI();
//       url = uri.toURL(); //����·��
//           AudioClip aau; 
//           aau = Applet.newAudioClip(url);
//             aau.loop();  //����ѭ��
//             System.out.print("��ʼ����");
//       } catch (Exception e) 
//        { 
//            e.printStackTrace();
//        } 
//     }

	// �����˵� ���ܰ�ť
	public void SetMenu() {
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
		fileMenu.addSeparator();
		helpMenu.add(copyRight);
		helpAbout.addActionListener(this);
		helpManual.addActionListener(this);
		copyRight.addActionListener(this);
		gameOpenCmd.addActionListener(this);
		gameExitCmd.addActionListener(this);
		landlord[0]=new JButton("��jojo");
		landlord[1]=new JButton("��     ��");
		publishCard[0]= new JButton("����");
		publishCard[1]= new JButton("��Ҫ");
		for(int i=0;i<2;i++)
		{
			publishCard[i].setBounds(320+i*100, 400, 60, 20);
			landlord[i].setBounds(320+i*100, 400,75,20);
			container.add(landlord[i]);
			landlord[i].addActionListener(this);
			landlord[i].setVisible(false);
			container.add(publishCard[i]);
			publishCard[i].setVisible(false);
			publishCard[i].addActionListener(this);
		}
		for(int i=0;i<3;i++){
			time[i]=new JTextField("����ʱ:");
			time[i].setVisible(false);
			container.add(time[i]);
		}
		time[0].setBounds(140, 230, 60, 20);
		time[1].setBounds(374, 360, 60, 20);
		time[2].setBounds(620, 230, 60, 20);
		
		for(int i=0;i<3;i++)
		{
			currentList[i]=new ArrayList<Card>();
		}
		
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
			this.dispose();
			Init();// ��ʼ��
			SetMenu();// �����˵� ��ť(������������,��ʱ��)
			this.setVisible(true);
			CardInit();//����
			getLord(); //�����ƿ�ʼ������
			time[1].setVisible(true);
			//�̰߳�ȫ��,�ѷ����̵߳�UI���Ʒŵ�����
			t=new Time(this,10);//��10��ʼ����ʱ
			t.start();
			
			//JOptionPane.showMessageDialog(this, "���ڼ���ˮƽ���ޣ��������¿�����Ϸ�����˳���Ϸ�����´�");
		}
		if (e.getSource() == helpAbout) {
			JOptionPane.showMessageDialog(this, "           ����ҵ       "+"      ���Ĳ�");
		}
		if (e.getSource() == copyRight) {
			SingletonClass right=SingletonClass.getSingletonInstance()  ;
			JOptionPane.showMessageDialog(this,right.getCopyRight() );
		}
		
		if(e.getSource()==landlord[0])
		{
			time[1].setText("��jojo");
			t.isRun=false; //ʱ���ս�
		}
		if(e.getSource()==landlord[1])
		{
			time[1].setText("����");
			t.isRun=false; //ʱ���ս�
		}
		//����ǲ�Ҫ
		if(e.getSource()==publishCard[1])
		{
			this.nextPlayer=true;
			currentList[1].clear();
			time[1].setText("��Ҫ") ;
		}
		//����ǳ��ư�ť
		if(e.getSource()==publishCard[0])
		{
			List<Card> c=new ArrayList<Card>();
			//��ѡ����
			for(int i=0;i<playerList[1].size();i++)
			{
				Card card=playerList[1].get(i);
				if(card.clicked)
				{
					c.add(card);
				}
			}
			int flag=0;
			
			//�������������
			if(time[0].getText().equals("��Ҫ")&&time[2].getText().equals("��Ҫ"))
			{
				if(Common.jugdeType(c)!=CardType.c0)
					flag=1;//��ʾ���Գ���
			}//����Ҹ���
			else{
				flag=Common.checkCards(c,currentList,this);
			}
			//�ж��Ƿ���ϳ���
			if(flag==1)
			{
				currentList[1]=c;
				playerList[1].removeAll(currentList[1]);//�Ƴ��ߵ���
				//��λ����
				Point point=new Point();
				point.x=(770/2)-(currentList[1].size()+1)*15/2;;
				point.y=300;
				for(int i=0,len=currentList[1].size();i<len;i++)
				{
					Card card=currentList[1].get(i);
					Common.move(card, card.getLocation(), point);
					point.x+=15;
				}
				//�����ƺ�����������
				Common.rePosition(this, playerList[1], 1);
				time[1].setVisible(false);
				this.nextPlayer=true;
			}

		}
	}
	
	public static void main(String args[]) {
		
			new Thread(new Main()).start();
			new Thread(()->{while(true) {Data.playMusic();}

			}).start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}

