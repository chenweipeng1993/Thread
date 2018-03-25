package peng.concurrent.racecondition;

public class EnergySystemTest {

	//将要构建的能量世界中能量盒子数量
	public static final int BOX_AMOUNT = 100;
	//每个盒子初始能量
    public static final double INITIAL_ENERGY = 1000;

    public static void main(String[] args){
    	//使用的同一个对象-EnergySystem
    	EnergySystem eng = new EnergySystem(BOX_AMOUNT, INITIAL_ENERGY);
    	for (int i = 0; i < BOX_AMOUNT; i++){
    		//使用同一个对象，这样在线程中-可以根据对象进行加锁操作，阻塞线程，唤醒此对象上所有等待线程
    		EnergyTransferTask task = new EnergyTransferTask(eng, i, INITIAL_ENERGY);
    		Thread t = new Thread(task,"TransferThread_"+i);
    		t.start();
    	}
    }

}
