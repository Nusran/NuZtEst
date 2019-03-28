import java.util.*;

class Checker implements Comparator<Player> {

	@Override
	public int compare(Player p1, Player p2) {
		if (p1.score == p2.score)
			return p1.name.compareTo(p2.name);
		else
			return p2.score - p1.score;
	}
}

class Player {
	String name;
	int score;

	Player(String name, int score) {
		this.name = name;
		this.score = score;
	}
}

class Solution4 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		Player[] player = new Player[n];
		Checker checker = new Checker();

		for (int i = 0; i < n; i++)
			player[i] = new Player(scan.next(), scan.nextInt());

		scan.close();
		Arrays.sort(player, checker);
		System.out.println(String.format("%s", "------------------------------------------------------------"));
		System.out.println(String.format("%s %15s %20s %10s %10s", "|", "Name", "|", "Score", "|"));
		System.out.println(String.format("%s", "============================================================"));
		for (int i = 0; i < player.length; i++)
			System.out.println(String.format("%s %15s %20s %10s %10s", "|", player[i].name, "|", player[i].score, "|"));
	
		System.out.println(String.format("%s", "------------------------------------------------------------"));
	}
}