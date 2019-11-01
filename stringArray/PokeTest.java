package exp02;
import java.io.Closeable;
import java.util.Scanner;

public class PokeTest {
	
	private static String[] cards;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("该扑克游戏需要几付扑克牌？");
		int num = sc.nextInt(); 
		PokeTest.cards = Poke.createCard(num);
		System.out.println("该扑克游戏有几个玩家？");
		int per = sc.nextInt();
		sc.close();
		System.out.println("显示所有的牌：");
		Poke.display(PokeTest.cards);
//		Poke.test(PokeTest.cards);  // 不要取消注释
		Poke.shuffle(PokeTest.cards);
		System.out.println("\n显示分配给每个人的牌：");
		Poke.distribute(PokeTest.cards, per);
}


}
