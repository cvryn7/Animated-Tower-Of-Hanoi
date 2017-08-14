/*
 * Hanoi.java
 *
 * Version: $Id$: Hanoi.java, v1.0  5:30 PM 9/23/2014 $
 *     
 *
 * Revisions: 0
 *     $Log$
 */

import java.util.Scanner;


/**
  * This is a stack class to save the disks on the tower 
  * at particular instance in runtime.
  *
  * @author      Karan Bhagat
  */
class TowerStack{

    private int[] stk_ary;// Array to contain stack elements
    private int top = -1;// To maintain index of the stack
    private int max_size;

    
    /**
     * Constructor of TowerStack
     * @param   max_size  maximum size of the stack
     */
    TowerStack( int max_size ){
	
	// java intialize all class variables, instance variable
	// and array components to 0 automatically.
	stk_ary = new int[ max_size ];
	this.max_size = max_size; 
    }
    

    /**
     * Push elements on the stack
     * @param    item	element to push on the stack 
     */
    public void push( int item ){
	if( top == max_size ){
	    System.out.println("Overflow");
	}
	else{
	    stk_ary[ ++top ] = item;
	}
    }
    

    /**
     * Pop element out of the stack
     * @return	    elements which is popped out.
     */
    public int pop(){
	if( top == -1 ){
	    System.out.println("Stack Empty");
	}
	else{
	    int popped_item;
	    popped_item = stk_ary[ top ];
	    stk_ary[ top ] = 0;
	    top--;
	    return popped_item; 
	}
	return 0;
    }
    

    /**
     * Make copy of the stack into other array.
     * @param    copy	Array in which elements will be copied. 
     */
    public void copyStack( int[] copy ){
	
	for( int i = 0; i < max_size; i++ ){
	    copy[i] = stk_ary[i];
	}
    
    }

    /**
     * Getting element at given index.
     * @param    idx	index at which we want element.
     * @return		return element at given element.
     */
    public int elementAt( int idx ){
	return stk_ary[ idx ];
    }

    
    /**
     * To the element at top of the disk
     * @return		return top element of the disk.
     */
    public int getTopDisk(){
	if( top == -1 ){
	    return 0;
	}
	return stk_ary[ top ];
    }
}


/**
  * This class represents the Tower of hanoi
  * in animated format
  * @author      Karan Bhagat
  */
class Visual{
    int no_of_disks;// total no. of disk to represent
    final int HEIGHT = 30;// height of the print grid
    final int WIDTH = 74;// Width of the print grid
    final int A_MID = 13;// Base center cordinate of Tower A
    final int B_MID = 37;// "" """ """""""""""""""" Tower B
    final int C_MID = 61;// "" """ """""""""""""""" Tower C
    final int TOWER_DIST = 24;// Distance between tower towers.
    final int TOWER_HEIGHT = 10;// Height of the tower except the base.
    
    //Array to save current cordinates of the tower 
    // and to save current top disk at each tower
    // column 0 : x cordinate
    // column 1 : y cordinate
    // column 2 : top disk no.
    int[][] current_cord = new int[ 3 ][ 3 ];
    
    // This the grid which will acutally get print
    char[][] play_scrn = new char[ HEIGHT ][ WIDTH ];
    int[] disk_size;// contain size or width of each disk accordin to its number.

    
    /**
     * Constructor of visual class
     * @param	  no_of_disks total no.of disks to animated 
     */
    Visual( int no_of_disks ){
	this.no_of_disks = no_of_disks;
	disk_size = new int[ no_of_disks ];
	int size_value = 17; // maximum size a disk can have.
	
	// Assign size to each disk according to its 
	// its no. 
	// e.i. disk if there 3 disks then 3 size is 17 , 2 is 15
	// 1 is 13.
	for( int i = no_of_disks-1; i >= 0; i-- ){
	    disk_size[ i ] = size_value;
	    size_value -= 2; 
	}
	// saving coordinates of bases for the intialization.
	current_cord[ 0 ][ 0 ] = 29;
	current_cord[ 0 ][ 1 ] = 3;
	
	current_cord[ 1 ][ 0 ] = 29;
	current_cord[ 1 ][ 1 ] = 27;
	
	current_cord[ 2 ][ 0 ] = 29;
	current_cord[ 2 ][ 1 ] = 51;
	
	// this method will fill the first tower with all disks.
	makeTowers(); 

    }
    
    /**
     * This method fill the first tower with given no of disks
     */
    public void makeTowers(){
	
	// Fill grid with blanks or to say clearing the screen.
	for( int i = 0; i < HEIGHT; i++ ){
	    for( int j = 0; j < WIDTH; j++ ){
		play_scrn[ i ][ j ] = ' ';
	    }
	}

	// Filling grid with towers and bases of towers
	for( int i = ( HEIGHT - 1 - TOWER_HEIGHT ); i < HEIGHT - 1 ; i++ ){
	    for( int j = 0; j < WIDTH; j++ ){
		if( j == A_MID || j == B_MID || j == C_MID ){
		    play_scrn[ i ][ j ] = 219;
		}
		else{
		    play_scrn[ i ][ j ] = ' ';
		}
	    }
	}

	// Creating tower base
	for( int i = 0; i < WIDTH; i++ ){
	    if( ( i >= 3 && i<= 23 ) || ( i >= 27 && i <= 47 ) || ( i >= 51 && i <= 71 ) ){
		play_scrn[ HEIGHT-1 ][ i ] = 223;
	    }
	    else{
		play_scrn[ HEIGHT-1 ][ i ] = ' ';
	    }
	}
	
	// Intializing Tower A with no_of_disks
	int temp_dec = no_of_disks-1;// Decremental variable to control the loop.
	int disk_fit = 2; // Factor by which y cordinate to decrease to place it over larger disks
	int new_x = 0; // New x cordinate
	int new_y = 0;  // New y cordinate

	// Placing the disks one over the other of Tower A
	for( int j = current_cord[ 0 ][ 0 ] - 1; j >= HEIGHT - no_of_disks - 1 ; j-- ){
	    new_x = j;
	    new_y = current_cord[ 0 ][ 1 ] + disk_fit;
	    for( int k = current_cord[ 0 ][ 1 ] + disk_fit; k < ( current_cord[ 0 ][ 1 ] + disk_fit ) + disk_size[ temp_dec ]; k++ ){
		play_scrn[ j ][ k ] = '*';
	    }
	    current_cord[ 0 ][ 2 ] = temp_dec + 1;
	    disk_fit++;
	    temp_dec--;
	}
	

	// New current cordinate of Tower A
	current_cord[ 0 ][ 0 ] = new_x;
	current_cord[ 0 ][ 1 ] = new_y;

	// Printing the current grid with Tower A filled.
	printHanoi();

    }

/**
  * Printing the grid.
  */
    public void printHanoi(){
	String ANSI_CLS = "\u001b[2J";// Character for clearing the screen.
	final String ANSI_HOME = "\u001b[H";// Character for taking cursor at home of the screen.
	System.out.print(ANSI_CLS + ANSI_HOME);// Printing these to implement these.
	System.out.flush();// Clearing the buffer.
	
	
	for( int i = 0; i < HEIGHT; i++ ){
	    for( int j = 0; j < WIDTH; j++ ){
		System.out.print( play_scrn[ i ][ j ] );
	    }
	    System.out.print("\n");
	}

	// pausing the program for 0.2 seconds.
	try {
	    Thread.sleep(100);                 //1000 milliseconds is one second.
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
    
    }
    

/**
  * This class control the animation of the disks from
  * one tower to otherr tower.
  * 
  * @param	start_x  current x cordinate of disk to move
  * @param	start_y  current y cordiante of disk to move
  * @param      end_x   final x cordinate of disk to move.
  * @param      end_y	final y cordinate of disk to move.
  * @param      disk_no  no. of the disk which to move.
  *
  */
    public void shiftAndPrint( int start_x, int start_y, int end_x, int end_y, int disk_no ){
	
	// taking disk from the current cordinate to the top of the tower.
	for( int i = start_x - 1;  i >= 18; i-- ){
	    // deleting the disk before printing it to the next upper x cordinate.
	    for( int j = start_y; j < start_y + disk_size[ disk_no -1 ]; j++ ){
		if( j == A_MID || j == B_MID || j == C_MID ){
		    play_scrn[ i+1 ][ j ] = 219;
		}
		else{
		    play_scrn[ i+1 ][ j ] = ' ';
		}

	    }
	    
	    for( int j = start_y; j < start_y + disk_size[ disk_no -1 ]; j++ ){
		play_scrn[ i ][ j ] = '*';
	    }
	    start_x = i;
	    printHanoi();
	}
	

	// taking the disk again to little more up above the current tower.
	for( int i = start_x - 1; i > 14; i-- ){
	  
	  // deleting the previous x position of the disk before moving it.
	    for( int j = start_y; j < start_y + disk_size[ disk_no - 1 ]; j++ ){
		play_scrn[ i+1 ][ j ] = ' ';
	  
	    }
	    for( int j = start_y; j < start_y + disk_size[ disk_no - 1]; j++ ){
		play_scrn[ i ][ j ] = '*';
	    }
	    start_x = i;
	    printHanoi();
	}
	

	// moving the disk horizonatally to the next destination tower.

	if( start_y < end_y ){
	    // moving right.
	    for( int i = start_y + 3; i <= end_y ; i+=3 ){
		
		start_y = i;
		for( int j = i - 3; j < start_y - 3 + disk_size[ disk_no - 1 ]; j++ ){
		    play_scrn[ start_x ][ j ] = ' ';
		}
		
		for( int j = i; j < start_y + disk_size[ disk_no - 1]; j++ ){
		    play_scrn[ start_x ][ j ] = '*';
		}
		printHanoi();
	    }

	}
	else{
	    // moving left
	    for( int  i = start_y - 3; i >= end_y; i-=3 ){
		
		start_y = i;
		for( int j = i + 3; j < start_y + 3 + disk_size[ disk_no - 1 ]; j++ ){
		    play_scrn[ start_x ][ j ] = ' ';
		}
		for( int j = i; j < start_y + disk_size[ disk_no - 1 ]; j++ ){
		    play_scrn[ start_x ][ j ] = '*';
		}
		printHanoi();
	    }

	}
	
	// fitting the tower to the destination tower by taking the disk down to tower.
	for( int i = start_x+1 ; i <= 19; i++ ){
	    for( int j = start_y; j < start_y + disk_size[ disk_no - 1 ]; j++ ){
		play_scrn[ i - 1][ j ] = ' ';
	    }

	    for( int j = start_y; j < start_y + disk_size[ disk_no - 1]; j++ ){
		play_scrn[ i ][ j ] = '*';
	    }
	    printHanoi();
	    start_x = i ;
	}
	

	for( int i = start_x + 1; i <= end_x; i ++ ){
	    for( int j = start_y; j < start_y + disk_size[ disk_no - 1 ]; j++ ){
		if( j == A_MID || j == B_MID || j == C_MID ){
		    play_scrn[ i-1 ][ j ] = 219;
		}
		else{
		    play_scrn[ i-1 ][ j ] = ' ';
		}
	    }
	    
	    for( int j = start_y; j < start_y + disk_size[ disk_no - 1 ]; j++ ){
		play_scrn[ i ][ j ] = '*';
	    }
	    printHanoi(); 
	}
	printHanoi();
    }
    
/**
  * This method actually control the animation of disks.
  * 
  * @param	tower_start tower currently containing the disk
  * @param	tower_end   destination tower.
  * @param      new_top_disk  after removing current disk the top disk on current tower.
  *
  */
    public void moveDisk( int tower_start, int tower_end, int new_top_disk ){
	int start_x = 0;// start cordinate of the disk.
	int start_y = 0;
	int end_x = 0;// end cordinate of the disk
	int end_y = 0;
	int disk_no = 0;// current disk number.
	switch( tower_start ){
	    // if start tower is A.
	    case 0:
		start_x = current_cord[ 0 ][ 0 ];
		start_y = current_cord[ 0 ][ 1 ];
		disk_no = current_cord[ 0 ][ 2 ];
		current_cord[ 0 ][ 0 ] = start_x + 1;
		if( new_top_disk == 0 ){
		    current_cord[ 0 ][ 1 ] = 3;
		}
		else{
		    current_cord[ 0 ][ 1 ] = A_MID - ( disk_size[ new_top_disk - 1]/2 ); 
		}
		current_cord[ 0 ][ 2 ] = new_top_disk; 
		switch( tower_end ){
		    case 1:
			end_x = current_cord[ 1 ][ 0 ] - 1;
			end_y = start_y + 24;
			current_cord[ 1 ][ 0 ] = end_x;
			current_cord[ 1 ][ 1 ] = end_y;
			current_cord[ 1 ][ 2 ] = disk_no;
			break;
		    case 2:
			end_x = current_cord[ 2 ][ 0 ] - 1;
			end_y = start_y + 24*2;
			current_cord[ 2 ][ 0 ] = end_x;
			current_cord[ 2 ][ 1 ] = end_y;
			current_cord[ 2 ][ 2 ] = disk_no;
			break;
		}
		shiftAndPrint( start_x, start_y, end_x, end_y, disk_no );
		break;
	    // if start tower is B
	    case 1:
		start_x = current_cord[ 1 ][ 0 ];
		start_y = current_cord[ 1 ][ 1 ];
		disk_no = current_cord[ 1 ][ 2 ];
		current_cord[ 1 ][ 0 ] = start_x + 1;
		if( new_top_disk == 0 ){
		    current_cord[ 1 ][ 1 ] = 27;
		}
		else{
		    current_cord[ 1 ][ 1 ] = B_MID - ( disk_size[ new_top_disk - 1]/2 ); 
		}
		current_cord[ 1 ][ 2 ] = new_top_disk; 

		    switch( tower_end ){
			case 0:
			    end_x = current_cord[ 0 ][ 0 ] - 1;
			    end_y = start_y - 24;
			    current_cord[ 0 ][ 0 ] = end_x;
			    current_cord[ 0 ][ 1 ] = end_y;
			    current_cord[ 0 ][ 2 ] = disk_no;
			    break;
			case 2:
			    end_x = current_cord[ 2 ][ 0 ] - 1;
			    end_y = start_y + 24;
			    current_cord[ 2 ][ 0 ] = end_x;
			    current_cord[ 2 ][ 1 ] = end_y;
			    current_cord[ 2 ][ 2 ] = disk_no;
			    break;
		    }
		shiftAndPrint( start_x, start_y, end_x, end_y, disk_no );
		break;
	    // if start tower is C
	    case 2:
		start_x = current_cord[ 2 ][ 0 ];
		start_y = current_cord[ 2 ][ 1 ];
		disk_no = current_cord[ 2 ][ 2 ];
		current_cord[ 2 ][ 0 ] = start_x + 1;
		if( new_top_disk == 0 ){
		    current_cord[ 2 ][ 1 ] = 51;
		}
		else{
		    current_cord[ 2 ][ 1 ] = C_MID - ( disk_size[ new_top_disk - 1]/2 ); 
		}
		current_cord[ 2 ][ 2 ] = new_top_disk; 

		    switch( tower_end ){
			case 0:
			    end_x = current_cord[ 0 ][ 0 ] - 1;
			    end_y = start_y - 24*2;
			    current_cord[ 0 ][ 0 ] = end_x;
			    current_cord[ 0 ][ 1 ] = end_y;
			    current_cord[ 0 ][ 2 ] = disk_no;
			    break;
			case 1:
			    end_x = current_cord[ 1 ][ 0 ] - 1;
			    end_y = start_y - 24;
			    current_cord[ 1 ][ 0 ] = end_x;
			    current_cord[ 1 ][ 1 ] = end_y;
			    current_cord[ 1 ][ 2 ] = disk_no;
			    break;
		    }
		shiftAndPrint( start_x, start_y, end_x, end_y, disk_no );
		break;
	}

    }

}


/**
  * Class containing the tower of hanoi recursion.
  * @author      Karan Bhagat
  */

class Shift{
    public static TowerStack tower_a;// Object of the stack class for tower A
    public static TowerStack tower_b;// Object of the stack class for tower B
    public static TowerStack tower_c;//Object of the stack class for tower C
    public static Visual v_print;   // Object of visual class to animated this method.
    public static int disk_cnt;// total no. of the disks.
    public static int print_method;// method of priting prefered. 0 - standard 1- animated
    
    
/**
  * This method fill tower A with total no of disks.
  * 
  * @param	n total no. of disks
  *
  */
    public static void fillTowerA( int n ){
	for( int i = n; i > 0; i-- ){
	    tower_a.push( i );
	}
    }
    

/**
  * This method set the printing method for the output.
  * 
  * @param	print	method prefered for printing output.
  */
    public static void setPrintMethod( int print ){
	print_method = print;
    }


/**
  * Printing tower with standard homework format.
  * 
  * @param	strt_tower  starting tower containing disk currently.
  * @param	end_tower   destination tower.
  */
    public static void printTower(int strt_tower, int end_tower ){
	String prnt_strng = "";

	System.out.println("Move disk from pole "+strt_tower+" to pole "+end_tower );
	System.out.println();
	for( int i = disk_cnt-1; i >= 0; i-- ){
	    prnt_strng = tower_a.elementAt( i ) + " " + tower_b.elementAt( i ) + " " + tower_c.elementAt( i );
	    System.out.println( prnt_strng );
	}
	System.out.println("------");
	System.out.println("0 "+"1 "+"2 ");
	System.out.println();
	System.out.println();
    
    }

/**
  * This method shift the disks from Tower A to tower C recursively.
  * 
  * @param	n	no. of disks
  * @param	poleStart   tower A passed as 0
  * @param      poleOver    tower B passed as 1
  * @param	poleEnd	    tower C passed as 2
  *
  */
    public static void move( int n, String poleStart, String poleOver, String poleEnd ){

	if( n == 1 ){
	    int strt_tower = Integer.parseInt( poleStart );// Converting tower number to integer
	    int end_tower = Integer.parseInt( poleEnd );// Converting tower number to integer
	    int temp_disk = 0;
	    // Poping out of current tower and pushing to destination tower on getting 1 disk as input.
	    switch( strt_tower ){
		case 0:
		    temp_disk = tower_a.pop();
		    break;
		case 1:
		    temp_disk = tower_b.pop();
		    break;
		case 2:
		    temp_disk = tower_c.pop();
		    break;    
	    }
	    
	    switch( end_tower ){
		case 0:
		    tower_a.push( temp_disk );
		    break;
		case 1:
		    tower_b.push( temp_disk );
		    break;
		case 2:
		    tower_c.push( temp_disk );
		    break;    
	    }
	    //Printing with standard format
	    if( print_method == 0 ){
		printTower( strt_tower, end_tower);
	    }
	    //Printing the hanoi animation.
	    else{
		if( strt_tower == 0 ){
		    v_print.moveDisk( strt_tower, end_tower, tower_a.getTopDisk() );
		}
		else if( strt_tower == 1 ){
		    v_print.moveDisk( strt_tower, end_tower, tower_b.getTopDisk() );
		}
		else{
		    v_print.moveDisk( strt_tower, end_tower, tower_c.getTopDisk() );
		}
	    }
	    return;   
	}
	// exception if 0 disk is input by the user.
	else if( n == 0 ){
	    System.out.println( "No Disk To Move" );
	}
	else{
	    move( n-1, poleStart, poleEnd, poleOver );// shifting n-1 disk to tower B
	    move( 1, poleStart, poleOver, poleEnd );// shifting last disk of tower A to tower C
	    move( n-1, poleOver, poleStart, poleEnd );// shifting n-1 disk from B to C
	}
    }
}


/**
  * This is main class which actually start the program
  * @author      Karan Bhagat
  */
class Hanoi{
    

/**
  * Main method
  * 
  * @param	args	    contain command line arguments if any.
  *
  */
    public static void main( String[] args ){
	int no_of_disks = 0; // will contain total no. of disk inputted by the user.
	
	//0 : print standard
	//1 : print animated
	int print_method = 0;// contains method of printing 0 : homework formate 1: animated
	System.out.println("Enter Print Method:");
	System.out.println("0 : Print Homework Format.");
	System.out.println("1 : Print Animated.(USE ONLY IF DISKS LESS THAN 10)");

	Scanner in = new Scanner(System.in);// taking input
	//converting args input to integer.
	try{
	    
	    no_of_disks = Integer.parseInt( args[0] );
	    print_method = Integer.parseInt( in.nextLine() );
	    if( print_method != 0 &&  print_method != 1 ){
		System.out.println(" Enter valid print_method.");
		return;
	    }

	}catch( NumberFormatException e ){
	    System.out.println(" invalid inputs ");
	}
	// clearing the screen and taking cursor to the home.
	String ANSI_CLS = "\u001b[2J";
	final String ANSI_HOME = "\u001b[H";
	System.out.print(ANSI_CLS + ANSI_HOME);
	System.out.flush();
	
	// intializing static stack objects in the Shift class
	Shift.tower_a = new TowerStack( no_of_disks );
	Shift.tower_b = new TowerStack( no_of_disks );
	Shift.tower_c = new TowerStack( no_of_disks );
	if( print_method == 1 ){
	    Shift.v_print = new Visual( no_of_disks );
	}
	Shift.setPrintMethod( print_method );
	Shift.disk_cnt = no_of_disks;
	Shift.fillTowerA( no_of_disks );

	Shift.move( no_of_disks, "0", "1", "2" );// recursive method.
	

	//Visual v = new Visual( no_of_disks );
	//v.makeTowers();
    }

}
