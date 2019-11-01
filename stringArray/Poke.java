package exp02;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Poke {
	/**
	 * @param num
	 * @return 产生 num  副扑克牌 ( 每一副扑克牌 52 张 不含有大小王 )
	 */
	public static String[] createCard(int num) {
		//准备花色
		ArrayList<String> color = new ArrayList<String>();
		color.add("♠");
		color.add("♥");
		color.add("♦");
		color.add("♣");
		
		//准备数字
		ArrayList<String> number = new ArrayList<String>();
		Collections.addAll(number,"3","4","5","6","7","8","9","10","J","Q","K","A","2");
		
		//定义一个cardsOrigin数组：按照顺序存入每一张牌
		String[] cards = new String[52];
		ArrayList<String> cardsOrigin = new ArrayList<String>();
		
		int index = 0;
		for (String thisNumber : number) {
			for (String thisColor : color) {
				cards[index++]=thisColor+thisNumber;
			}
		}
		while(num--!=0) {
			Collections.addAll(cardsOrigin, cards);
		}
		return cardsOrigin.toArray(new String[cardsOrigin.size()]);
	}
	
	public static void display(String[] cards) {
		int index = 0;
		if(cards.length>52)
			System.out.println("共有 " + cards.length/52 + "副牌:" );
		for(String card : cards) {
			if(card==null) break;
			System.out.print(card+" ");
			if(index % 13 == 0 && index !=0) {
				System.out.println();
			}
			index++;
			if(cards.length>52&&index==52)
				break;
		}
	}
	
	 // 不要取消注释
//	public static void test(String[] cards) {
//		String[] teststr = new String[5];
//		for(int i=0;i<5;i++) {
//			teststr[i] = "teststr";
//		}
//		cards = teststr;
//	}
	
	public static  void shuffle(String[] cards) {
		Random rand=new Random(52);
		List<String> cardsList = new ArrayList<>(Arrays.asList(cards));
		Collections.shuffle(cardsList,rand);
		String[] shufflecards  = cardsList.toArray(new String[cardsList.size()]);

		cards = cardsList.toArray(new String[cardsList.size()]);   //  不生效
		
//		display(cards);    //  取消这个注释 可以看出 cards确实内容改变了 ,  调试也行 就不用取消了
		
		//  把上面那个注释不生效的注释 然后取消下面的注释 生效
//		cards = shufflecards.clone();
//		int i=0;
//		for(i=0; i<shufflecards.length;i++) {
//			cards[i]=shufflecards[i];
//		}
	}
	
	public static void distribute(String[] cards, int player) {
		int index = 0;
		while(index < player) {
			System.out.println("\n玩家"+ (index+1) );
			String[] distributeCards = new String[52];
			System.arraycopy(cards, 0+index*(cards.length/player),distributeCards, 0,cards.length/player);
			display(distributeCards);
			index++;
		}
	}
}
