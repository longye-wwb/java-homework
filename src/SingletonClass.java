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
		return "本项目由董成业 王文博共同完成。禁止用于商业用途。";
		
	}


}
