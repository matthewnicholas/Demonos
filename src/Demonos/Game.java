package Demonos;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Game {
	
	public static void main(String[] args) {
		boolean skipPlayer = false;
		boolean skipCpu = false;
		boolean skipPretiles = false;
		boolean debugStart =  false;
		boolean tutorial = true;
		
		System.out.println("Instructions?(y/n)");
		Scanner tut = new Scanner(System.in);
		if(tut.hasNext("[Nn]"))
				tutorial = false;
		
		System.out.println(tutorial);
		
		//setup playscape
		Board board = new Board(41,20);
		
		//setup player input
		Scanner playerInput = new Scanner(System.in);
		
		if(tutorial) {
			Player tutorialPlayer = new Player("tutorial");
			System.out.println(board);
			System.out.println("this is the play surface");
			System.out.println("It is divided into rows and columns.\nPlay positions 1A 4C 8H etc.(y)");
			while(playerInput.nextLine().isEmpty()) {};
			Tile tutT = new Tile(7001);
			System.out.println(tutT);
			System.out.println("This is a Demono.\nAn Angelo to be exact.  There are 4 types of tiles in Demonos.");
			System.out.println("Demonos - §\nAngelos - ©\nHumanos - © and §\nAnd acts of god - *\n(y)");
			//setup player input
			while(playerInput.nextLine().isEmpty()) {};
			System.out.println(tutT);
			System.out.println("Each tile has 2 sides and each side and a power");
			System.out.println("Some Demonos have special powers.\nThis on is called Army of Cute.");
			System.out.println("Special powers are cast as soon as the Demono is played.(y)");
			while(playerInput.nextLine().isEmpty()) {};
			Tile tut2 = new Tile(7001);
			board.playCard(tut2, "3B");
			System.out.println(board);
			System.out.println("Here is how this special works- as you can see\n another cherub is on the board(y)");
			while(playerInput.nextLine().isEmpty()) {};
			tutT.rotate(90);
			board.playCard(tutT, "5C");
			System.out.println(board);
			System.out.println("Once the card is played the effect is applied to the other cards in play");
			System.out.println("Also notice we can rotate tiles in any multiple of 90(y)");
			while(playerInput.nextLine().isEmpty()) {};
			Tile tut3 = new Tile(6001);
			System.out.println(tut3);
			System.out.println("All tiles start with no rotation(y)");
			while(playerInput.nextLine().isEmpty()) {};
			tut3.rotate(90);
			System.out.println(tut3);
			System.out.println("but we can rotate them 90...(y)");
			while(playerInput.nextLine().isEmpty()) {};
			tut3.rotate(90);
			System.out.println(tut3);
			System.out.println("or 90 again..(y)");
			while(playerInput.nextLine().isEmpty()) {};
			tut3.rotate(270);
			System.out.println(tut3);
			System.out.println("or 270 from there");
			System.out.println("Tiles are always played from side 1(y)");
			while(playerInput.nextLine().isEmpty()) {};
			tut3.rotate(-90);
			System.out.println(tut3);
			System.out.println("Let's rotate -90, now side 1 is to the left when the numbers are on the bottom.(y)");
			while(playerInput.nextLine().isEmpty()) {};
			tut3.rotate(180);
			System.out.println(tut3);
			System.out.println("Or to the right when the numbers are on the top(y)");
			while(playerInput.nextLine().isEmpty()) {};
			tut3.rotate(-90);
			System.out.println(tut3);
			System.out.println("To the top when 90 (notice the power is on the left)...(y)");
			while(playerInput.nextLine().isEmpty()) {};
			tut3.rotate(180);
			System.out.println(tut3);
			System.out.println("Or bottom when 270 (again side 1 power is on the left)");
			System.out.println("Remember, when playing the tile the play position is based on side 1,(y)");
			while(playerInput.nextLine().isEmpty()) {};
			tut3.rotate(90);
			System.out.println(tut3);
			System.out.println("so to play the left side at position 1A when not rotated it's 1A,(y)");
			while(playerInput.nextLine().isEmpty()) {};
			tut3.rotate(180);
			System.out.println(tut3);
			System.out.println("but if we put side 2 to the left the postion would be 2A.\nAlso combat is initiated from side 1(y)");
			while(playerInput.nextLine().isEmpty()) {};
			System.out.println("Combat!!!!!!");
			System.out.println("Place opposite tiles next to each other, the highest power wins\nand the loser is removed");
			System.out.println("But remember - tiles reienforce each other- tiles of the same type\nbehind you increase your power while tiles of the same type\nbehind your opponet increase thiers(not counting the other side of your own tile)(y)");
			while(playerInput.nextLine().isEmpty()) {};
			tut3.rotate(180);
			board.playCard(tut3, "5B");
			System.out.println(board);
			System.out.println("So if we play this Imp Demono at 5B with no rotation we can defeat\nthe Churb below but create a stalemate with the one to the left");
			while(playerInput.nextLine().isEmpty()) {};
			board.combat(tutorialPlayer);
			System.out.println(board);
			System.out.println("That's Deomonos in a nutshell, the goal is to position cards so you can defeat more than one tile at a time\nas you gain points for defeated tiles\nbut lose them when your play is defeated.");
			System.out.println("GLHF!(y)");
			while(playerInput.nextLine().isEmpty()) {};
			board.removeCard(tut2);
			board.removeCard(tut3);
		}
		
		
		if(debugStart) {
			Tile t1 = new Tile(8001);
			//t1.rotate(270);
			//Tile t2 = new Tile(5001);
			//t2.rotate(180);
			board.playCard(t1, "2C");
			//board.playCard(t2, "5C");
			//board.playCard(t3, "7E");
		}
		
		if(!skipPretiles) {
			//it's more fun where there are some tiles on the board to start with
			Deck startTiles = new Deck(3);
			for(int i=2; i>=0; i--) {
				Tile t = startTiles.draw(i);
				t.rotate((int)(Math.random()*4) * 90);
				int col = (int)(Math.random()*8);
				int row = (int)(Math.random()*9);
				while(!board.playCard(t, ""+(col+1)+(char)(row+'A'))) {
					col = (int)(Math.random()*8);
					row = (int)(Math.random()*9);
				}
			}
		}
		
		System.out.println(board);
		
		//get players name
		System.out.println("What is your name?");
		String name = "Player1";
		name = playerInput.nextLine();
		Player player = new Player(name);
		System.out.println("Welcome " + name);
		
		//get Difficulty
		System.out.println("What difficulty?(1-2)");
		System.out.println("1 is totaly random,\n2 trys to win but can't look ahead in moves and doesn't understand specials");
		int difficulty = 1;
		difficulty = playerInput.nextInt();
		Player cpu = new Player(difficulty>0?difficulty<3?difficulty:2:1);
		
		//get the size of the deck
		System.out.println("How many cards would you like to play with?(10)");
		int numOfCards = 10;
		numOfCards = playerInput.nextInt();
		Deck deck = new Deck(numOfCards>=10?numOfCards:10);
		
		//draw players initial hand
		System.out.println("Shuffling deck...");
		deck.shuffle();
		while(player.getHand().size() < 5)
			player.draw(deck.draw(0));
		while(cpu.getHand().size() < 5)
			cpu.draw(deck.draw(0));
		
		if(debugStart) {
			Tile t = new Tile(8001);
			//player.draw(t);
			board.playCard(t, "2C");
		}
		
		//game loop
		boolean showBoard = true;
		boolean endTurn = false;
		boolean gameOver = false;
		while(!gameOver) {
			if(showBoard) {System.out.println(board);}
			showBoard = true;
			
			//player phase//////////////////////////////////////////////////////////////////
			if(!skipPlayer) {
				System.out.println("What would you like to do?");
				System.out.println("(You have " + player.numOfCards() + " cards and your apponient has " + cpu.numOfCards() + ")");
				System.out.println("(Score) " + player.getName() + ": " + player.getScore() + " | " + cpu.getName() + ": " + cpu.getScore());
				if(deck.hasCards())
					System.out.println("1-Draw card\t2-Play card");
				else
					System.out.println("*DECK EMPTY*\t2-Play card");
					
				System.out.println("3-Look at hand\t4-Quit");
				int choice = 0;
				choice = playerInput.nextInt();
				
				if(choice == 4) {
					deck.trash();
					cpu.dropHand();
					player.dropHand();
					player.resetScore();
					cpu.addPoint();
					endTurn = false;
				}
				
				if(choice == 3) {
					System.out.println(player);
					showBoard = false;
				}
				
				if(choice == 2 && player.hasCards()) {
					String[] play = player.playCard().split(" ");
					if(play[0] == "back") {continue;} //exit menu
					Tile card = player.takeCard(Integer.parseInt(play[0])-1);
					if(!board.playCard(card, play[1]))
						player.insertIntoHand(card, Integer.parseInt(play[0])-1);
					else {
						System.out.println(board);
						System.out.println("Sure?(y/n)");
						Scanner confirm = new Scanner(System.in);
						String input = confirm.nextLine();
						if(input.matches("[Yy]")) {
							System.err.println("COMBAT");
							board.combat(player);
							System.out.println(board);
							System.out.println("continue?(y)");
							while(playerInput.nextLine().isEmpty()) {};
							endTurn = true;
						}
						else {
							System.out.println("Not ready? Card returned to hand");
							board.removeCard(card);
							player.insertIntoHand(card, Integer.parseInt(play[0])-1);
							endTurn = false;
						}
					}
				}
				
				if(choice == 1 && deck.hasCards()) {
					player.draw(deck.draw(0));
					endTurn = true;
				}
				
				playerInput.reset();
				
			}
			else
				endTurn = true;
			
			if(endTurn && !skipCpu) {
				///computer phase////////////////////////////////////////////////////////////////
				System.out.println(cpu.getName() +" says- my turn");
				switch(difficulty) {
				//easy cpu
				case 1:
					//look at hand
					System.out.println(cpu.getName() + " says- Hummm....");
					if(cpu.hasCards()) {
						//pick a random card
						Tile cpuPlay = cpu.takeCard((int)Math.random()*cpu.getHand().size());
						//randomly rotate it
						cpuPlay.rotate(((int)(Math.random()*4))*90);
						//find the first spot that accommodate this tile
						int row = 0;
						int col = 0;
						while(!board.playCard(cpuPlay, ""+(col+1)+((char)(row+'A')))){
							col++;
							if(col>7) {
								col = 0;
								row++;
							}
							if(row > 9) {
								System.out.println(cpu.getName() +" says- couldn't find a spot, this is hard!");
								cpuPlay.rotate((((int)(Math.random()*360)/90)*90));
								row = 0;
								col = 0;
							}
							switch((int)(Math.random()*5)) {
							case 0:
								System.out.println(cpu.getName() + " says- oops");
								break;
							case 1:
								System.out.println(cpu.getName() + " says- balls");
								break;
							case 2:
								System.out.println(cpu.getName() + " says- hold on");
								break;
							case 3:
								System.out.println(cpu.getName() + " says- well not there");
								break;
							case 4:
								System.out.println(cpu.getName() + " says- heh, are you waiting on me?");
							}
							
						}
						System.out.println(board);
						System.out.println(cpu.getName() + " says- this is going to be good! Ready?(y)");
						while(playerInput.nextLine().isEmpty()) {};
						board.combat(cpu);
						
					}else {
						if(deck.hasCards()) {
							System.err.println(cpu.getName() + " draws a card.");
							cpu.draw(deck.draw(0));
						}
					}
				//medium cpu
				case 2:
					//loop while computer strategizes
					while(true) {
						if(cpu.hasCards()) {
							System.out.println("Good play... but you're going down");
							//look at board and find lowest dmg side of the cards in play
							int lowest = 20;
							int targetSide = 1;
							int chosenSide = 1;
							Tile target = null;
							Tile chosenCard = null;
							for(Tile t : board.getCards()) {
								for(int side=1; side<3; side++) {
									if(t.getDmg(side) < lowest) {
										lowest = t.getDmg(side);
										targetSide = side;
										target = t;
									}
								}
							}
							
							//compare the lowest card dmg to the computer player's hand
							for(int i=0; i<cpu.getHand().size(); i++) {
								Tile t = cpu.takeCard(i);
								for(int side=1; side<3; side++) {
									if(t.getDmg(side) > target.getDmg(targetSide) && t.getType(side) != target.getType(targetSide)) {
										chosenCard = t;
										chosenSide = side;
										break;
									}
								}
								if(chosenCard == null)
									cpu.insertIntoHand(t, i);
								else
									break;
							}
							
							//if we don't have a card that can win
							if(chosenCard == null) {
								//draw a card
								if(deck.hasCards()) {
									System.out.println(cpu.getName() + " says- Hummm, your better than I thought");
									System.err.println(cpu.getName() + " draws a card.");
									cpu.draw(deck.draw(0));
									break;
								}
								//play a random card (easy computer's play)
								else {
									//pick a random card
									Tile cpuPlay = cpu.takeCard((int)Math.random()*cpu.getHand().size());
									//randomly rotate it
									cpuPlay.rotate(((int)(Math.random()*4))*90);
									//find the first spot that accommodate this tile
									int row = 0;
									int col = 0;
									while(!board.playCard(cpuPlay, ""+(col+1)+((char)(row+'A')))){
										col++;
										if(col>7) {
											col = 0;
											row++;
										}
										if(row > 9) {
											System.out.println(cpu.getName() +" says- couldn't find a spot, this is hard!");
											cpuPlay.rotate((((int)(Math.random()*360)/90)*90));
											row = 0;
											col = 0;
										}
										switch((int)(Math.random()*5)) {
										case 0:
											System.out.println(cpu.getName() + " says- oops");
											break;
										case 1:
											System.out.println(cpu.getName() + " says- balls");
											break;
										case 2:
											System.out.println(cpu.getName() + " says- hold on");
											break;
										case 3:
											System.out.println(cpu.getName() + " says- well not there");
											break;
										case 4:
											System.out.println(cpu.getName() + " says- heh, are you waiting on me?");
										}
									}
									
									break;
								}
								
							}
							//we do have a card to play
							else {
								int targetCol = target.getPos(targetSide).charAt(0) - '0' - 1; //0 indexed
								int targetRow = target.getPos(targetSide).charAt(1) - 'A';
								System.out.println(chosenCard);
								//look left
								if(targetCol-1 >=0 && board.getTilesInPlay()[targetRow][targetCol-1] == null) {
									//left open
									//now look up
									if(targetRow-1 >=0 && board.getTilesInPlay()[targetRow-1][targetCol-1] == null) {
										//valid play
										if(chosenSide == 1) {
											chosenCard.rotate(270);
											if(board.playCard(chosenCard, ""+(targetCol-1+1)+(char)(targetRow+'A')));
												break;
										}
										else {
											chosenCard.rotate(90);
											if(board.playCard(chosenCard, ""+(targetCol-1+1)+(char)((targetRow-1)+'A')));
												break;
										}
									}
									//look left
									else if(targetCol-2 >=0 && board.getTilesInPlay()[targetRow][targetCol-2] == null) {
										//valid play
										if(chosenSide == 1) {
											chosenCard.rotate(180);
											if(board.playCard(chosenCard, ""+(targetCol-1+1)+(char)(targetRow+'A')));
												break;
										}
										else {
											if(board.playCard(chosenCard, ""+(targetCol-2+1)+(char)(targetRow+'A')));
												break;
										}
									}
									//look down
									else if(targetRow+1 <=9 && board.getTilesInPlay()[targetRow+1][targetCol-1] == null) {
										//valid play
										if(chosenSide == 1) {
											chosenCard.rotate(90);
											if(board.playCard(chosenCard, ""+(targetCol-1+1)+(char)(targetRow+'A')));
												break;
										}
										else {
											chosenCard.rotate(270);
											if(board.playCard(chosenCard, ""+(targetCol-1+1)+(char)((targetRow+1)+'A')));
												break;
										}
									}	
								}
								
								//look down
								if(targetRow+1 <=9 && board.getTilesInPlay()[targetRow+1][targetCol] == null) {
									//down is valid
									//look left
									if(targetCol-1 >=0 && board.getTilesInPlay()[targetRow+1][targetCol-1] == null) {
										//valid play
										if(chosenSide == 1) {
											chosenCard.rotate(180);
											if(board.playCard(chosenCard, ""+(targetCol+1)+(char)((targetRow+1)+'A')));
												break;
										}
										else {
											if(board.playCard(chosenCard, ""+(targetCol-1+1)+(char)((targetRow+1)+'A')));
												break;
										}
									}
									//look down again
									else if(targetRow+2 <= 9 && board.getTilesInPlay()[targetRow+2][targetCol] == null) {
										//valid play
										if(chosenSide == 1) {
											chosenCard.rotate(90);
											if(board.playCard(chosenCard, ""+(targetCol+1)+(char)((targetRow+1)+'A')));
												break;
										}
										else {
											chosenCard.rotate(270);
											if(board.playCard(chosenCard, ""+(targetCol+1)+(char)((targetRow+2)+'A')));
												break;
										}
									}
									//look right
									else if(targetCol+1 <=8 && board.getTilesInPlay()[targetRow+1][targetCol+1] == null) {
										//valid play
										if(chosenSide == 1) {
											if(board.playCard(chosenCard, ""+(targetCol+1)+(char)((targetRow+1)+'A')));
												break;
										}
										else {
											chosenCard.rotate(180);
											if(board.playCard(chosenCard, ""+(targetCol+2)+(char)((targetRow+1)+'A')));
												break;
										}
									}	
								}
								
								//look right
								if(targetCol+1 <=8 && board.getTilesInPlay()[targetRow][targetCol+1] == null) {
									//down is valid
									//look up
									if(targetRow-1 >=0 && board.getTilesInPlay()[targetRow-1][targetCol+1] == null) {
										//valid play
										if(chosenSide == 1) {
											chosenCard.rotate(270);
											if(board.playCard(chosenCard, ""+(targetCol+2)+(char)(targetRow+'A')));
												break;
										}
										else {
											chosenCard.rotate(90);
											if(board.playCard(chosenCard, ""+(targetCol+2)+(char)((targetRow-1)+'A')));
												break;
										}
									}
									//look right again
									else if(targetCol+2 <= 8 && board.getTilesInPlay()[targetRow][targetCol+2] == null) {
										//valid play
										if(chosenSide == 1) {
											if(board.playCard(chosenCard, ""+(targetCol+2)+(char)(targetRow+'A')));
												break;
										}
										else {
											chosenCard.rotate(180);
											if(board.playCard(chosenCard, ""+(targetCol+2)+(char)((targetRow)+'A')));
												break;
										}
									}
									//look down
									else if(targetRow+1 <=9 && board.getTilesInPlay()[targetRow+1][targetCol+1] == null) {
										//valid play
										if(chosenSide == 1) {
											chosenCard.rotate(90);
											if(board.playCard(chosenCard, ""+(targetCol+2)+(char)(targetRow+'A')));
											break;
										}
										else {
											chosenCard.rotate(270);
											if(board.playCard(chosenCard, ""+(targetCol+2)+(char)((targetRow+1)+'A')));
												break;
										}
									}	
								}
								
								//look up
								if(targetRow-1 >=0 && board.getTilesInPlay()[targetRow-1][targetCol] == null) {
									//down is valid
									//look left
									if(targetCol-1 >=0 && board.getTilesInPlay()[targetRow-1][targetCol-1] == null) {
										//valid play
										if(chosenSide == 1) {
											chosenCard.rotate(180);
											if(board.playCard(chosenCard, ""+(targetCol+1)+(char)((targetRow-1)+'A')));
												break;
										}
										else {
											if(board.playCard(chosenCard, ""+(targetCol-1+1)+(char)((targetRow-1)+'A')));
												break;
										}
									}
									//look up again
									else if(targetRow-2 >= 0 && board.getTilesInPlay()[targetRow-2][targetCol] == null) {
										//valid play
										if(chosenSide == 1) {
											chosenCard.rotate(270);
											if(board.playCard(chosenCard, ""+(targetCol+1)+(char)((targetRow-1)+'A')));
												break;
										}
										else {
											chosenCard.rotate(90);
											if(board.playCard(chosenCard, ""+(targetCol+1)+(char)((targetRow-2)+'A')));
												break;
										}
									}
									//look right
									else if(targetCol+1 <=8 && board.getTilesInPlay()[targetRow-1][targetCol+1] == null) {
										//valid play
										if(chosenSide == 1) {
											if(board.playCard(chosenCard, ""+(targetCol+1)+(char)((targetRow-1)+'A')));
												break;
										}
										else {
											chosenCard.rotate(180);
											if(board.playCard(chosenCard, ""+(targetCol+2)+(char)((targetRow-1)+'A')));
												break;
										}
									}	
								}
							}
							
														
						}else {
							if(deck.hasCards()) {
								System.err.println(cpu.getName() + " draws a card.");
								cpu.draw(deck.draw(0));
								break;
							}
						}
					}
					System.err.println("COMBAT");
					System.out.println(board);
					System.out.println(cpu.getName() + " says- prepare to be defeated! Ready?(y)");
					while(playerInput.nextLine().isEmpty()) {};
					board.combat(cpu);
					System.out.println(board);
				}
			}
			
			if(!deck.hasCards() || (!player.hasCards() && !cpu.hasCards())){
					if(!player.hasCards() && player.getScore() < cpu.getScore())
						gameOver = true;
					if(!cpu.hasCards() && cpu.getScore() < player.getScore())
						gameOver = true;
					if(!deck.hasCards() && !player.hasCards() && !cpu.hasCards())
						gameOver = true;
			}
		}
		
		System.out.println("Thanks for playing!");
		System.out.println("(You have " + player.numOfCards() + " cards and your apponient has " + cpu.numOfCards() + ")");
		System.out.println("(Score) " + player.getName() + ": " + player.getScore() + " | " + cpu.getName() + ": " + cpu.getScore());
		if(cpu.getScore()-player.getScore() < 0)
			System.out.println(player.getName() + " WINS!");
		else
			System.out.println(cpu.getName() + " WINS!");
		
	}

}

class Board{
	//size
	private int width;
	private int height;
	private int tileSize = 10;
	//array to hold cards in play
	private Tile[][] tilesInPlay = new Tile[10][8];
	//list of blocked play squares
	private ArrayList<Tile> playHistory = new ArrayList<>();
	private ArrayList<Tile> discardPile = new ArrayList<>();
	//constructor
	Board(int w, int h){
		this.width = w;
		this.height = h;
	}
	//toString to print the board and the cards in play
	public String toString() {
		StringBuilder retString = new StringBuilder();
		for(int h=0; h<=this.height;h++) {
			for(int w=0; w<this.width; w++) {
				if(w==0) {
					if(h==0) {
						retString.append("/");  //top left corner
					}else {
						retString.append("|"); //left side
					}
				}else if(w==width-1) {
					if(h==0) {
						retString.append("\\"); //top right corner
					}else {
						retString.append("|"); //right side
					}
				}else {
					if(h==0) {
						retString.append((char)175);  //top
					}else {
						if(w % (this.tileSize/2) == 0) {
							retString.append(":");
						}
						else if(h % (this.tileSize/4) == 0) {
							retString.append(".");
						}
						else {
							retString.append(" ");
						}
					}
				}
			}
		}
		//display the cards in play
		for(int r=0;r<tilesInPlay.length; r++)
			for(Tile t : tilesInPlay[r]) {
				if(t != null) {
					String pos = t.getPos(1);
					int col = pos.charAt(0)-'0';
					int row = pos.charAt(1)-'A';
					int topLeft = (row*2+1)*width+(col-1)*5+1;
					switch(t.getRotation()) {
					case 180:
						topLeft-=this.tileSize/2;
					case 0:
						for(int h=0; h<2; h++) {
							retString.replace(topLeft+width*h-1, topLeft+width*h-1 + t.getImgLine(h).length(), t.getImgLine(h));
						}
						break;
					case 270:
						topLeft -= 2*width;
					case 90:
						for(int h=0; h<4; h++) {
							retString.replace(topLeft+width*h-1, topLeft+width*h-1 + t.getImgLine(h).length(), t.getImgLine(h));
						}
						break;
					}
				}
			}
		
		String columns = "";
		for(int col=1; col<9; col++) {
			columns += " " + col + "   ";
		}
		retString.insert(0,columns);
		
		char row = 'A';
		for(int i = 1; i<=height+1; i++) {
			retString.insert(i*(width+1)-2+(row-'A'), (i%2==1&&i>2)?row++ + "\n": "\n");
		}
		
		retString.append("\n\\");
		for(int w = 0; w <width-2; w++) {retString.append("_");}
		retString.append("/");
		
		return retString.toString();
	}
	
	//return a card at a location
	
	//play a card at a location
	public Boolean playCard(Tile target, String pos) {
		if(this.getCards().contains(target))
			this.removeCard(target); //to prevent phantom moves
		
		int col = pos.charAt(0) - '0';//NOT 0 indexed ********************************
		int row = pos.charAt(1) - 'A';
		//check that the position is valid
		if(row < 0 || col < 0 || row > 9 || col > 8)
			return false;
	
		//System.out.println(this.printInPlay());  DEBUG!
		if(this.tilesInPlay[row][col-1] != null) {
			System.err.println("Position blocked");
			return false;
		}
			
		switch(target.getRotation()) {
		case 0:
			//tile position check
			if(col > 7) {
				System.err.println("Maximum row is 7");
				return false;
			}
			else if(this.tilesInPlay[row][col] != null){
				System.err.println("Position blocked(0)");
				return false;
			}
			break;
			
		case 90:
			//check position
			if(row > 9) {
				System.err.println("Maximum row is I");
				return false;
			}
			else if(this.tilesInPlay[row+1][col-1] != null){
				System.err.println("Position blocked(90)");
				return false;
			}
			break;
		
		case 180:
			//check pos
			if(col<2) {
				System.err.println("Minimum column is 2");
				return false;
			}
			else if(this.tilesInPlay[row][col-2] != null){
				System.err.println("Position blocked(180)");
				return false;
			}
			break;
			
		case 270:
			if(row<1) {
				System.err.println("Minimum row is B");
				return false;
			}
			else if(this.tilesInPlay[row-1][col-1] != null){
				System.err.println("Position blocked(270)");
				return false;
			}
			break;
		}
		
		target.setPos(pos);
		
		for(int side=1; side<3; side++) {
			col = target.getPos(side).charAt(0) - '0' - 1; //0 index
			row = target.getPos(side).charAt(1) - 'A';
			this.tilesInPlay[row][col] = target;
		}
		this.playHistory.add(target);
		if(target.getSpecial() != null)
			target.getSpecial().cast(target, this);
		
		if(target.getType(1) == "G" || target.getType(2) == "G")
			this.removeCard(target);
		
		return true;
		
	}
	
	public void removeCard(Tile target) {
		for(int r=0; r<this.tilesInPlay.length; r++)
			for(int c=0; c<this.tilesInPlay[r].length; c++)
				if(target == this.tilesInPlay[r][c])
					this.tilesInPlay[r][c] = null;
		
		this.discardPile.add(target);		
	}
	
	public ArrayList<Tile> getCards() {
		ArrayList retList = new ArrayList();
		for(int r=0; r<this.tilesInPlay.length; r++)
			for(int c=0; c<this.tilesInPlay[r].length; c++)
				if(!retList.contains(this.tilesInPlay[r][c]) && this.tilesInPlay[r][c] != null)
					retList.add(this.tilesInPlay[r][c]);
		
		return retList;
	}
	
	//semi-protected- not deep copying the tiles so you could still manipulate their positions directly
	public Tile[][] getTilesInPlay(){
		Tile[][] retTile = new Tile[10][8];
		for(int r=0; r<this.tilesInPlay.length; r++) {
			for(int c=0; c<this.tilesInPlay[r].length; c++) {
				retTile[r][c] = this.tilesInPlay[r][c];
			}
		}
		
		return retTile;
	}
	
	public String printInPlay() {  //for debug
		String retString = new String("");
		for(int r=0; r<this.tilesInPlay.length; r++) {
			for(int c=0; c<this.tilesInPlay[r].length; c++) {
				if(this.tilesInPlay[r][c] != null) {
					retString += "O";
				}
				else {
					retString += "X";
				}
			}
			retString += "\n";
		}
		return retString;
	}
	
	public void combat(Player p) {
		//get last card played
Tile currentCard = this.playHistory.get(this.playHistory.size()-1);
		//do for each side of the tile
		for(int side=1; side<3 && this.getCards().contains(currentCard); side++) {
			int col = currentCard.getPos(side).charAt(0) - '0' - 1;//0 Indexed !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			int row = currentCard.getPos(side).charAt(1) - 'A';
			int dmgSum = 0;  //sum of damage against the played card
			int defSum = 0;  //bonuses for allies next to the played card
			boolean lose = false;  //save to the end
			//iterate up down left right
			for(int direction=0; direction<4;direction++) {
				int r,c,rowMod,colMod;
				//prevent false positives if a card is already placed or shouldn't count as defense
				boolean attackable = false;
				switch(direction){
				//up
				case 0:
					r = row-1;
					c = col;
					rowMod = -1;
					colMod = 0;
					break;
				//down
				case 1:
					r = row+1;
					c = col;
					rowMod = 1;
					colMod = 0;
					break;
				//left
				case 2:
					r = row;
					c = col-1;
					rowMod = 0;
					colMod = -1;
					break;
				//right
				default:
					r = row;
					c = col+1;
					rowMod = 0;
					colMod = 1;
				}
				
				//go check both directions check behind first
				for(int i=0; i<2; i++) {
					if(i==0) {
						rowMod *= -1;
						r = row + rowMod;
						colMod *= -1;
						c = col + colMod;
					}
					
					//this is a bit ridiculous -- change the conditional based on the direction
					for(;(rowMod==-1)?r>=0:(rowMod==1)?row<this.tilesInPlay.length:(colMod==-1)?c>=0:c<this.tilesInPlay[row].length; r+=rowMod, c+=colMod) {
						Tile target = this.tilesInPlay[r][c];
						//check if the next row up has a card
						if(target != null && target != currentCard) {
							//get the correct side to compare
							int compSide = target.getPos(side).equals(""+(c+1)+(char)(r+'A'))?side:(side==1)?2:1;
							//compare sides
							if(target.getType(compSide) != currentCard.getType(side))
								//calc dmg
								if(i == 1) {
									dmgSum += target.getDmg(compSide);
									attackable = true;
								}
								else
									break;
								
							else
								defSum += target.getDmg(compSide);
							
							//make sure that the next card above isn't rotated vertically
							if(direction == 0 || direction == 1) {
								if(target.getRotation() == 90 || target.getRotation() == 270)
									//tiles can't buff themselves
									break;
							}
							else {
								if(target.getRotation() == 0 || target.getRotation() == 180)
									//tiles can't buff themselves
									break;
							}
							
						}else {
							//not a valid card above
							//attackable = false;
							break;
						}
					}
				}
				
				//kill the cards above and below if this one wins
				if(dmgSum > currentCard.getDmg(side) + defSum) {
					//we need to evaluate left and right before deleting this card so
					//we'll save that until later
					lose = true;
					
				}
				else if(dmgSum < currentCard.getDmg(side) + defSum && attackable) {
					if(row+rowMod >= 0 && row+rowMod <= 10 && col+colMod >= 0 && col+colMod <= 7) {
						if(this.tilesInPlay[row+rowMod][col+colMod] != null && this.tilesInPlay[row+rowMod][col+colMod] != currentCard) {
							int compSide = this.tilesInPlay[row+rowMod][col+colMod].getPos(side).equals(""+(col+colMod+1)+(char)(row+rowMod+'A'))?side:(side==1)?2:1;
							if(this.tilesInPlay[row+rowMod][col+colMod].getType(compSide) != currentCard.getType(side)) {
								this.removeCard(this.tilesInPlay[row+rowMod][col+colMod]);
								p.addPoint();
							}
						}
					}
				}
				//if we went all 4 directions and lost kill this card
				if(direction == 3 && lose) {
					this.removeCard(currentCard);
					p.losePoint();
				}
				//reset damage and defense when changing direction
				dmgSum = 0;
				defSum = 0;
			}
		}
	}
}

class Deck{
	private ArrayList<Tile> contents = new ArrayList<>();

	Deck(int size){
		try {
			Scanner file = new Scanner(new File("tiles"));
			ArrayList<String> lines = new ArrayList<>();
			//get all the tiles
			while(file.hasNextLine()) {
				String thisLine = file.nextLine();
				if(thisLine.charAt(0) != '#') {
					lines.add(thisLine);
				}
			}
			
			//build the deck
			while(contents.size() < size) {
				//generate a tile
				Tile t = new Tile(Integer.parseInt(lines.get((int)(Math.random()*100)%lines.size()).substring(0,4)));
				this.contents.add(t);
			}
			
			file.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
		
	public boolean hasCards() {
		return (this.contents.size() > 0)?true:false;
	}
	
	public Tile draw(int index) {
		return contents.remove(index);
	}
	
	public void shuffle() {
		Collections.shuffle(contents);
	}
	
	public void trash() {
		this.contents.clear();
	}
	
	public String toString() {
		String retString = "";
		for(Tile t : contents) {
			retString += t.getName() + ", ";
		}
		
		return retString;
	}
}

class Tile{
	private int id;
	private int atkBase1;
	private int atkBase2;
	//track modifiers to dmg
	private int atkMod1;
	private int atkMod2;
	private String name;
	private String description;
	private Specials special;
	//track who played the card
	private Player owner;
	//left side when not rotated
	private String pos1;
	private String pos2;
	private int rotation = 0;
	//cosole image of card
	private ArrayList<String> img = new ArrayList<>();
	private String symbol1;
	private String symbol2;
	
	//constructor
	Tile(int id){
		this.id = id;
		try {
			Scanner file = new Scanner(new File("tiles"));
			while(file.hasNextLine()) { 
				//parse the tile file into an object
				String[] tile = file.nextLine().split("\\t+");
				if(tile[0].charAt(0) == '#') {continue;}
				
				if (Integer.parseInt(tile[0]) == this.id) {
					this.name = tile[1];
					this.atkBase1 = Integer.parseInt(tile[2]);
					this.atkBase2 = Integer.parseInt(tile[3]);
					this.atkMod1 = 0;
					this.atkMod2 = 0;
					this.symbol1 = tile[4];
					this.symbol2 = tile[5];
					this.special = Specials.getSpecial(Integer.parseInt(tile[6]));
					this.description = tile[7];
					break;
				}
			}
			
			file.close();
			setImg();
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void setImg() {
		try {
			if(this.img.size() > 0) {this.img.clear();}
			
			Scanner imgFile = new Scanner(new File("card"));
			while(imgFile.hasNextLine()) {
				String line = imgFile.nextLine();
				if(line.equals(""+this.rotation)) {
					switch(this.rotation) {
					case 180:
					case 0:
						for(int height=0; height<2; height++) {
							this.img.add(imgFile.nextLine());
						}
						break;
					case 270:
					case 90:
						for(int height=0; height<4; height++) {
							this.img.add(imgFile.nextLine());
						}
						break;
					}
				}
			}
			
			imgFile.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//get description based on id
	public String getDesc() {
		return this.description;
	}
	
	//get name
	public String getName() {
		return this.name;
	}
	
	public String getType(int side) {
		if(side == 1) {
			if(this.symbol1.equals("§"))
				return "D";
			else if(this.symbol1.equals("©"))
				return "A";
			else 
				return "G";
		}
		else {
			if(this.symbol2.equals("§"))
				return "D";
			else if(this.symbol2.equals("©"))
				return "A";
			else 
				return "G";
		}
	}
	
	//attack
	
	//add special
	public void addSpecial(Specials s) {
		if(this.special == null) {
			this.special = s;
		}else {
			System.out.println("Replace " + name + "'s current special? (Y/N)");
			Scanner sc = new Scanner(System.in);
			if(sc.next() == "Y"){
				this.special = s;
			}
			sc.close();
		}
	}
	
	public Specials getSpecial() {
		return this.special;
	}
	
	//rotate
	public void rotate(int degree) {
		this.rotation += degree%360;
		if(this.rotation >= 360) {
			this.rotation %= 360;
		}
		if(this.rotation < 0) {
			this.rotation += 360;
		}
		
		setImg();
	}
	
	public int getRotation() {
		return this.rotation;
	}
	
	//place
	public void setPos(String pos) {
		this.pos1 = pos;
		int col = pos.charAt(0) - '0';
		int row = pos.charAt(1) - 'A';
		switch(this.rotation) {
		case 0:
			this.pos2 = "" + (col+1) + (char)(row+'A');
			break;
		case 90:
			this.pos2 = "" + col + (char)(row+1+'A');
			break;
		case 180:
			this.pos2 = "" + (col-1) + (char)(row+'A');
			break;
		case 270:
			this.pos2 = "" + col + (char)(row-1+'A');
			break;			
		}
	}
	
	public String getPos(int side) {
		if(side == 2)
			return this.pos2;
		else
			return this.pos1;
	}
	
	public int getAtk(int side) {
		if(side == 1)
			return this.atkBase1;
		
		else if(side == 2)
			return this.atkBase2;
		
		else
			return 0;
	}
	
	public void setMod(int side, int val) {
		if(side == 1)
			this.atkMod1 = val;
		else if(side == 2)
			this.atkMod2 = val;
	}
	
	public int getMod(int side) {
		if(side == 2)
			return this.atkMod2;
		else
			return this.atkMod2;
	}
	
	public int getDmg(int side) {
		if(side == 2)
			return this.atkBase2+this.atkMod2;
		else
			return this.atkBase1+this.atkMod1;
	}
	
	public String otherPos() {
		int col = this.pos1.charAt(0)-'0';
		int row = this.pos1.charAt(1)-'A';
		switch(this.rotation) {
		case 0:
			return "" + (col+1) + (char)row;
			
		case 90:
			return "" + col + (char)(row+1);
			
		case 180:
			return "" + (col-1) + (char)row;
			
		case 270:
			return "" + col + (char)(row-1);
			
		}
		
		return this.pos1;
		
	}
	
	public void changeType(int side, String type) {
		if(side == 1)
			if(!this.symbol1.equals(type))
				this.symbol1 = type;
		
		if(side == 2)
			if(!this.symbol2.equals(type))
				this.symbol2 = type;
	}
	
	public String getImgLine(int line) {
		String retString = this.img.get(line);
		if(this.atkBase1+this.atkMod1 < 10 && this.atkBase1+this.atkMod1 >= 0)
			retString = retString.replace("1", ""+(this.atkBase1+this.atkMod1));
		else
			switch(this.rotation) {
			case 270:
			case 0:
				retString = retString.replace("1_", ""+(this.atkBase1+this.atkMod1));
				break;
			case 90:
				retString = retString.replace("1¯", ""+(this.atkBase1+this.atkMod1));
				break;
			case 180:
				retString = retString.replace("1¯", ""+(this.atkBase1+this.atkMod1));
				break;
			}
		
		if(this.atkBase2+this.atkMod2 < 10)
			retString = retString.replace("2", ""+(this.atkBase2+this.atkMod2));
		else
			switch(this.rotation) {
			case 90:
			case 0:
				retString = retString.replace("_2", ""+(this.atkBase2+this.atkMod2));
				break;
			case 180:
				retString = retString.replace("2¯", ""+(this.atkBase2+this.atkMod2));
				break;
			case 270:
				retString = retString.replace("¯2", ""+(this.atkBase2+this.atkMod2));
				break;
			}
			
		retString = retString.replace("@", this.symbol1);
		retString = retString.replace("&", this.symbol2);
		return retString;
	}
	
	public String toString() {
		String retString = "";
		for(int i=0; i<this.img.size(); i++) {
			retString += this.getImgLine(i) + "\n";
		}
		
		retString += "Name: "+this.name;
		retString += "\n'"+this.description + "'";
		retString += (this.special != null)? "\nSpecial: "+this.special:"";
		return retString;
		/*for(String s : this.img) {
			retString += s + "\n";
		}
		
		retString = retString.replace("1", ""+(this.atkBase1+this.atkMod1));
		retString = retString.replace("2", ""+(this.atkBase2+this.atkMod2));
		retString = retString.replace("@", this.symbol1);
		retString = retString.replace("&", this.symbol2);
		retString += "Name: "+this.name;
		retString += "\n'"+this.description + "'";
		retString += (this.special != null)? "\nSpecial: "+this.special:"";
		return retString;*/
	}
	
}

enum Specials{
	ARMYOFCUTE(1), PESTILENCE(2), SPEAR(3), AUDIT(4), CHAOSTHEORY(5);
	
	private String name;
	private String description;
	private int id;
	
	private Specials(int id) {
		this.id = id;
		try {
			Scanner file = new Scanner(new File("specials"));
			while(file.hasNextLine()) { 
				//pasrse the special file into an object
				String[] special = file.nextLine().split("\\t+");
				if(special[0].charAt(0) == '#') {continue;}
				
				if (Integer.parseInt(special[0]) == id) {
					this.name = special[1];
					this.description = special[2];
					break;
				}
			}
			
			file.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static Specials getSpecial(int id) {
		switch(id) {
		case 1:
			return ARMYOFCUTE;
		case 2:
			return PESTILENCE;
		case 3:
			return SPEAR;
		case 4:
			return AUDIT;
		case 5:
			return CHAOSTHEORY;
		}
		return null;
	}
	
	public void cast(Tile self, Board game) {
		switch(this.id) {
		case 1:
			armyOfCute(game);
			break;
		case 2:
			pestilence(game);
			break;
		case 3:
			spear(self, game);
			break;
		case 4:
			audit(game);
			break;
		case 5:
			chaosTheory(game);
			break;
		}
	}
	
	public void armyOfCute(Board target) {
		//add +1 to all angelos on board
		for(Tile t : target.getCards()) {
			if(t.getType(1) == "A" && t.getMod(1) < 3) 
				t.setMod(1, t.getMod(1)+1);
			
			if(t.getType(2) == "A" && t.getMod(1) < 3)
				t.setMod(2, t.getMod(2)+1);
		}
		System.out.println("_\\|It just got a little cuter in here *GIGGLES*|/_");
	}
		
	public void pestilence(Board target) {
		//Remove 1 from all angelos
		for(Tile t : target.getCards()) {
			if(t.getType(1) == "A" && t.getMod(1) < 3) 
				t.setMod(1, t.getMod(1)-1);
			
			if(t.getType(2) == "A" && t.getMod(1) < 3)
				t.setMod(2, t.getMod(2)-1);
		}
		System.out.println("_\\|The Angelos begin to cough and gag|/_");
	}
	
	public void spear(Tile self, Board board) {
		String side2 = self.getPos(2);
		int col = side2.charAt(0) - '0'; ///NOT 0 indexed
		int row = side2.charAt(1) - 'A';
		ArrayList<String> adjacent = new ArrayList<>(); 
		if(row-1 >= 0 && !self.getPos(1).equals("" + col + (char)((row-1)+'A')))
			adjacent.add("" + col + (char)((row-1)+'A'));
		if(row+1 <= 10 && !self.getPos(1).equals("" + col + (char)((row+1)+'A')))
			adjacent.add("" + col + (char)((row+1)+'A'));
		if(col-1 >= 0 && !self.getPos(1).equals("" + (col-1) + (char)(row+'A')))
			adjacent.add("" + (col-1) + (char)(row+'A'));
		if(col+1 >= 9 && !self.getPos(1).equals("" + (col+1) + (char)(row+'A')))
			adjacent.add("" + (col+1) + (char)(row+'A'));
			
		for(Tile t : board.getCards()) {
			for(int neighbor=0; neighbor<adjacent.size(); neighbor++) {
				for(int side=1; side<3; side++)
				if(t.getPos(side).equals(adjacent.get(neighbor))){
					if(t.getType(side)=="A")
						self.setMod(1, t.getDmg(side));
				}
			}
		}
		
		System.out.println("_\\|The Angelos prepare a foramtion for atttack|/_");
	}
	
	public void audit(Board board) {
		for(Tile t : board.getCards()) {
			for(int side=1; side<3; side++) {
				if(t.getType(side) == "D")
					t.setMod(side, -1);
			}
		}
		
		System.out.println("_\\|Someone's been cheating on their taxes!|/_");
	}
	
	public void chaosTheory(Board target) {
		for(int side=1; side<3; side++) {
			for(Tile t : target.getCards()) {
				if(t.getType(side) == "A")
					t.changeType(side, "§");
				else
					t.changeType(side, "©");
			}
		}
		
		System.out.println("_\\|The tiles in play swirl and change|/_");
	}
	
	public String toString() {
		return "(" + this.name + ")" + this.description;
	}
}

class Player{
	private ArrayList<Tile> hand = new ArrayList<>();
	private String name;
	private int score = 0;
	
	//constructor
	Player(String name){
		this.name = name;
	}
	
	Player(int diff){
		switch(diff) {
		case 1:
			this.name = "Easy Computer";
			break;
		case 2:
			this.name = "Medium Computer";
			break;
		case 3:
			this.name = "Hard Computer";
			break;
		}
	}
	
	//add tile to hand
	public void draw(Tile t) {
		hand.add(t);
	}
	
	public Tile takeCard(int index) {
		return this.hand.remove(index);
	}
	
	public void insertIntoHand(Tile t, int index) {
		this.hand.add(index, t);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int numOfCards() {
		return hand.size();
	}
	
	public boolean hasCards() {
		return (hand.size() > 0)?true:false;
	}
	
	public ArrayList<Tile> getHand(){
		ArrayList<Tile> retHand = new ArrayList<>(this.hand);
		return retHand;
	}
	
	public void dropHand() {
		this.hand.clear();
	}
	
	public String playCard() {
		System.out.println(this);
		Scanner input = new Scanner(System.in);
		System.out.println("1-Rotate (ie 2 -270)\t2-Play (ie 1 1A)\t3-back");
		String line = input.nextLine();
		while(true) {
			if(line.matches("(\\d?)(\\s)(\\d+)") || line.matches("(\\d?)(\\s)-(\\d+)")) {
				String[] params = line.split(" ");
				this.hand.get(Integer.parseInt(params[0])-1).rotate(Integer.parseInt(params[1]));
				System.out.println(this);
				System.out.println("1-Rotate (ie 2 -270)\t2-Play (ie 1 1A)");
				line = input.nextLine();
				
			}else if(line.matches("(\\d+)(\\s)(\\d)(\\D)")) {
				//input.close();
				return line.toUpperCase();
				
			}else if(line.matches("3")){
				return "back";
			}
			else {
				System.out.println("Invalid choice, try again");
				System.out.println(this);
				System.out.println("1-Rotate (ie 2 -270)\t2-Play (ie 1 1A)");
				line = input.nextLine();
			}
		}
	}
	
	public void addPoint() {
		this.score++;
	}
	
	public void losePoint() {
		this.score--;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void resetScore() {
		this.score=0;
	}
	
	public String toString() {
		ArrayList<String> hldString = new ArrayList<>();
		int cardNum = 1;
		for(Tile t : hand) {
			hldString.add(t.toString().replaceFirst("\\n", " -> " + cardNum++ + "\n"));
		}
		
		String retString = "";
		for(String s : hldString) {
			retString+=s+"\n\n";
		}
		
		return retString;
	}
}