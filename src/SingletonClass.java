public class SingletonClass {

	private static SingletonClass singletonInstance;
	// other useful instance variable here
	private SingletonClass() { }
	public static SingletonClass getSingletonInstance() {
		if(singletonInstance == null) {
			 singletonInstance = new SingletonClass();
		}
		return singletonInstance;
	}
	public String getCopyRight() {
		return "����Ŀ�ɶ���ҵ ���Ĳ���ͬ��ɡ���ֹ������ҵ��;��";
		
	}


}
