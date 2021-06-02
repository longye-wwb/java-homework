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
			this.setTitle("jojo斗地主游戏---by wwb,dcy");
			this.setSize(830,620);
			setResizable(false);
			setLocationRelativeTo(getOwner()); // 屏幕居中
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new  AfAnyWhere());
		name.setText("J O J O の 斗 地 主 冒 険");
		name.setFont(new Font("微软雅黑",0 , 64));
		start.setText("开始");
		start.setFont(new Font("微软雅黑",0 , 22));
		
		//添加控件
		this.add(name,new  AfMargin(70, -1, -1, -1));
	this.add(bgpanel,new AfMargin(0, 0, 0,0));
	this.add(start,new AfMargin(300,-1,-1,-1));
		start.setPreferredSize(new Dimension(150,70));
		//添加菜单项
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

		//开始按钮监听器
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Main();
				MyFrame.this.dispose();
				}
				});
		//创建菜单项监听器
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
				JOptionPane.showMessageDialog(this, "游戏名称：jojo的斗地主冒险。\r\n"
						+ "游戏种类：（策略竞技卡牌类游戏）\r\n"
						+ "游戏目标：出完所有手牌\r\n"
						+ "游戏内容：三名玩家进入游戏后，系统随机分配每人17张牌，预留3张底牌，（在确定地主前玩家不能查看）\r\n"
						+ "从地主开始，玩家依照顺序出牌，直到一名玩家出完所有牌后，游戏结束。"
						);
			}
			if (e.getSource() == gameOpenCmd) {
				 //this.restart();
				JOptionPane.showMessageDialog(this, "由于技术水平有限，若想重新开启游戏，请退出游戏后重新打开");
			}
			if (e.getSource() == helpAbout) {
				JOptionPane.showMessageDialog(this, "           董成业       "+"      王文博");
			}
			if (e.getSource() == copyRight) {
				SingletonClass right=SingletonClass.getSingletonInstance()  ;
				JOptionPane.showMessageDialog(this,right.getCopyRight() );
			}
		}

	
	
}
