package ie.gmit.sw;

import java.util.Scanner;

public class Menu {
	private String file1;
	private String file2;
	private int shingleSize;
	
	public Menu() {
		super();
	}

	public void show() throws InterruptedException {
		// TODO Auto-generated method stub
		
		Scanner console=new Scanner(System.in);
		
		System.out.println("Compare your documents");
				
		// Get name of file
		System.out.print("\nEnter File Name 1: ");
		file1 = console.nextLine();
		
		System.out.print("\nEnter File Name 2: ");
		file2 = console.nextLine();

		new Launcher(file1,file2, 0);
		
}
	}

	 
