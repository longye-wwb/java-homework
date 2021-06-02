package af.swing.border;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;

/** AfBorder: ���ڿ������ñ߿�ͱ߾�
 * 
 *  Ҫ���Լ�Ҳ��д�������������
 */
public class AfBorder
{
	// �����ڼ��
	public static void addPadding(JComponent c, int size)
	{
		addPadding(c, size, size, size, size);
	}
	
	// �����ڼ��
	public static void addPadding(JComponent c, int top, int left, int bottom, int right)
	{
		Border border = BorderFactory.createEmptyBorder(top, left, bottom, right);
		addInnerBorder(c, border);
	}
	
	// ��������
	public static void addMargin(JComponent c, int size)
	{
		addMargin(c, size, size, size, size);
	}
	
	// ��������
	public static void addMargin(JComponent c, int top, int left, int bottom, int right)
	{
		Border border = BorderFactory.createEmptyBorder(top, left, bottom, right);
		addOuterBorder(c, border);
	}
	
	// ����һ����߿�
	public static void addOuterBorder(JComponent c, Border outerBorder)
	{
		Border border = c.getBorder();
		if(border != null)
		{
			// ���ԭ����һ���߿�����и���
			border = BorderFactory.createCompoundBorder(outerBorder,border);
			c.setBorder( border );
		}
		else
		{
			c.setBorder(outerBorder);
		}
	}

	// ����һ���ڱ߿�
	public static void addInnerBorder(JComponent c, Border innerBorder)
	{
		Border border = c.getBorder();
		if(border != null)
		{
			// ���ԭ����һ���߿�����и���
			border = BorderFactory.createCompoundBorder(border, innerBorder);
			c.setBorder( border );
		}
		else
		{
			c.setBorder(innerBorder);
		}
	}
}
