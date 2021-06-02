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
	public Container container = null;// 定义容器 
	JMenuItem gameOpenCmd,gameExitCmd,helpAbout,helpManual,copyRight;// 定义菜单按钮
	JButton landlord[]=new JButton[2];//抢地主按钮
	JButton publishCard[]=new JButton[2];//出牌按钮
	int dizhuFlag;//地主标志
	int turn;
	JLabel dizhu; //地主图标
	List<Card> currentList[] =new ArrayList[3]; //  当前的出牌
	List<Card> playerList[] = new ArrayList[3]; // 定义3个玩家表
	List<Card> lordList;//地主牌
	Card card[] = new Card[56]; // 定义54张牌
	//Card3 card[]=new Card3[24];
	JTextField time[]=new JTextField[3]; //计时器
	Time t; //定时器（线程）
	boolean nextPlayer=false; //转换角色
	public Main(){
//		this.Music();
		Init();// 初始化
		SetMenu();// 创建菜单 按钮(抢地主，发牌,计时器)
		this.setVisible(true);
		CardInit();//发牌
		getLord(); //发完牌开始抢地主
		time[1].setVisible(true);
		//线程安全性,把非主线程的UI控制放到里面
		t=new Time(this,10);//从10开始倒计时
		t.start();
		
		
	}
	// 抢地主
	public void getLord(){
		//System.out.println(CardType.c0.toString());
		for(int i=0;i<2;i++)
			landlord[i].setVisible(true);
	}
	//初始化牌
	// 发牌洗牌
	public void CardInit2() {
		int count = 1;
		//初始化牌
		for (int i = 1; i <= 12; i++) {
					card[count] = new Card(this, 1 + "-" + i, false);
					card[count].setLocation(350, 50);
					container.add(card[count]);
					count++;
				
			}
		
		//打乱顺序
		for(int i=0;i<100;i++){
			Random random=new Random();
			int a=random.nextInt(24)+1;
			int b=random.nextInt(24)+1;
			Card k=card[a];
			card[a]=card[b];
			card[b]=k;
		}
		//开始发牌
		for(int i=0;i<3;i++)
			playerList[i]=new ArrayList<Card>(); //玩家牌
		lordList=new ArrayList<Card>();//地主牌三张
		int t=0;
		for(int i=1;i<=24;i++)
		{
			/*if(i>=52)//地主牌
			{
				Common.move(card[i], card[i].getLocation(),new Point(300+(i-52)*80,10));
				lordList.add(card[i]);
				continue;
			}*/
			switch ((t++)%3) {
			case 0:
				//左边玩家
				Common.move(card[i], card[i].getLocation(),new Point(50,60+i*5));
				playerList[0].add(card[i]);
				break;
			case 1:
				//我
				Common.move(card[i], card[i].getLocation(),new Point(180+i*7,450));
				playerList[1].add(card[i]);
				card[i].turnFront(); //显示正面
				break;
			case 2:
				//右边玩家
				Common.move(card[i], card[i].getLocation(),new Point(700,60+i*5));
				playerList[2].add(card[i]);
				break;
			}
			//card[i].turnFront(); //显示正面
			container.setComponentZOrder(card[i], 0);
		}
		//发完牌排序，从大到小
		for(int i=0;i<3;i++)
		{
			Common.order(playerList[i]);
			Common.rePosition(this,playerList[i],i);//重新定位
		}
		dizhu=new JLabel(new ImageIcon("images/logo.gif"));
		dizhu.setVisible(false);
		dizhu.setSize(88, 55);
		container.add(dizhu);
		
	}
	public void CardInit() {
		
		int count = 1;
		//初始化牌
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
		//打乱顺序
		for(int i=0;i<100;i++){
			Random random=new Random();
			int a=random.nextInt(54)+1;
			int b=random.nextInt(54)+1;
			Card k=card[a];
			card[a]=card[b];
			card[b]=k;
		}
		//开始发牌
		for(int i=0;i<3;i++)
			playerList[i]=new ArrayList<Card>(); //玩家牌
		lordList=new ArrayList<Card>();//地主牌三张
		int t=0;
		for(int i=1;i<=54;i++)
		{
			if(i>=52)//地主牌
			{
				Common.move(card[i], card[i].getLocation(),new Point(300+(i-52)*80,10));
				lordList.add(card[i]);
				continue;
			}
			switch ((t++)%3) {
			case 0:
				//左边玩家
				Common.move(card[i], card[i].getLocation(),new Point(50,60+i*5));
				playerList[0].add(card[i]);
				break;
			case 1:
				//我
				Common.move(card[i], card[i].getLocation(),new Point(180+i*7,450));
				playerList[1].add(card[i]);
				card[i].turnFront(); //显示正面
				break;
			case 2:
				//右边玩家
				Common.move(card[i], card[i].getLocation(),new Point(700,60+i*5));
				playerList[2].add(card[i]);
				break;
			}
			//card[i].turnFront(); //显示正面
			container.setComponentZOrder(card[i], 0);
		}
		//发完牌排序，从大到小
		for(int i=0;i<3;i++)
		{
			Common.order(playerList[i]);
			Common.rePosition(this,playerList[i],i);//重新定位
		}
		dizhu=new JLabel(new ImageIcon("images/logo.gif"));
		dizhu.setVisible(false);
		dizhu.setSize(88, 55);
		container.add(dizhu);
	}

	// 初始化窗体
	public void Init() {

		this.setTitle("jojo斗地主游戏---by wwb,dcy");
		this.setSize(830,620);
		setResizable(false);
		setLocationRelativeTo(getOwner()); // 屏幕居中
		container = this.getContentPane();
		container.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setBackground(new Color(206, 216, 242)); // 背景为绿色
	
	}
//	void Music(){               //注意，java只能播放无损音质，如.wav这种格式
//        try {      
//       f = new File("D:\\Java\\Workplace\\项目\\src\\菅野祐悟 (かんの ゆうご) - il vento d'oro.wav"); //绝对路径
//        uri = f.toURI();
//       url = uri.toURL(); //解析路径
//           AudioClip aau; 
//           aau = Applet.newAudioClip(url);
//             aau.loop();  //单曲循环
//             System.out.print("开始播放");
//       } catch (Exception e) 
//        { 
//            e.printStackTrace();
//        } 
//     }

	// 创建菜单 功能按钮
	public void SetMenu() {
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		JMenu fileMenu = new JMenu("游戏");
		menubar.add(fileMenu);
		 gameOpenCmd = new JMenuItem("新游戏");
		fileMenu.add(gameOpenCmd);
		fileMenu.addSeparator();
		 gameExitCmd = new JMenuItem("退出");
		fileMenu.add(gameExitCmd);
		// 帮助菜单
		JMenu helpMenu = new JMenu("帮助");
		menubar.add(helpMenu);
		 helpAbout = new JMenuItem("开发者");
		 helpManual = new JMenuItem("规则说明");
		 copyRight=new JMenuItem("版权声明");
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
		landlord[0]=new JButton("抢jojo");
		landlord[1]=new JButton("不     抢");
		publishCard[0]= new JButton("出牌");
		publishCard[1]= new JButton("不要");
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
			time[i]=new JTextField("倒计时:");
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
			JOptionPane.showMessageDialog(this, "游戏名称：jojo的斗地主冒险。\r\n"
					+ "游戏种类：（策略竞技卡牌类游戏）\r\n"
					+ "游戏目标：出完所有手牌\r\n"
					+ "游戏内容：三名玩家进入游戏后，系统随机分配每人17张牌，预留3张底牌，（在确定地主前玩家不能查看）\r\n"
					+ "从地主开始，玩家依照顺序出牌，直到一名玩家出完所有牌后，游戏结束。"
					);
		}
		if (e.getSource() == gameOpenCmd) {
			 //this.restart();
			this.dispose();
			Init();// 初始化
			SetMenu();// 创建菜单 按钮(抢地主，发牌,计时器)
			this.setVisible(true);
			CardInit();//发牌
			getLord(); //发完牌开始抢地主
			time[1].setVisible(true);
			//线程安全性,把非主线程的UI控制放到里面
			t=new Time(this,10);//从10开始倒计时
			t.start();
			
			//JOptionPane.showMessageDialog(this, "由于技术水平有限，若想重新开启游戏，请退出游戏后重新打开");
		}
		if (e.getSource() == helpAbout) {
			JOptionPane.showMessageDialog(this, "           董成业       "+"      王文博");
		}
		if (e.getSource() == copyRight) {
			SingletonClass right=SingletonClass.getSingletonInstance()  ;
			JOptionPane.showMessageDialog(this,right.getCopyRight() );
		}
		
		if(e.getSource()==landlord[0])
		{
			time[1].setText("抢jojo");
			t.isRun=false; //时钟终结
		}
		if(e.getSource()==landlord[1])
		{
			time[1].setText("不抢");
			t.isRun=false; //时钟终结
		}
		//如果是不要
		if(e.getSource()==publishCard[1])
		{
			this.nextPlayer=true;
			currentList[1].clear();
			time[1].setText("不要") ;
		}
		//如果是出牌按钮
		if(e.getSource()==publishCard[0])
		{
			List<Card> c=new ArrayList<Card>();
			//点选出牌
			for(int i=0;i<playerList[1].size();i++)
			{
				Card card=playerList[1].get(i);
				if(card.clicked)
				{
					c.add(card);
				}
			}
			int flag=0;
			
			//如果我主动出牌
			if(time[0].getText().equals("不要")&&time[2].getText().equals("不要"))
			{
				if(Common.jugdeType(c)!=CardType.c0)
					flag=1;//表示可以出牌
			}//如果我跟牌
			else{
				flag=Common.checkCards(c,currentList,this);
			}
			//判断是否符合出牌
			if(flag==1)
			{
				currentList[1]=c;
				playerList[1].removeAll(currentList[1]);//移除走的牌
				//定位出牌
				Point point=new Point();
				point.x=(770/2)-(currentList[1].size()+1)*15/2;;
				point.y=300;
				for(int i=0,len=currentList[1].size();i<len;i++)
				{
					Card card=currentList[1].get(i);
					Common.move(card, card.getLocation(), point);
					point.x+=15;
				}
				//抽完牌后重新整理牌
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

