package edu.cpp.cs.cs241.prog_assgnment_1;

import java.util.Scanner;
/*Main class also acts as UI. Prompts user for information and prints program welcome statement*/
public class Main {
	  
	public static void main(String[] args)	{
	
        int function;
        String name;
        int priority;
        boolean notDone = true;
        
        NodeHeap<Customer> list = new NodeHeap();
        Scanner sc = new Scanner(System.in);
       
        introProgram();
        
        //must enter the appropriate input type when prompted. 
        while(notDone)
        {
            System.out.print("KEY: (1) to add a customer (2) to remove the first customer on the list " +
        "(3) to print out the list in order (4) to exit program \nEnter: ");
            function = sc.nextInt();
            sc.nextLine();
            
            switch (function)
            {
                case 1:
                    System.out.print("Enter a Customer name: ");
                    name = sc.nextLine();
           
                    System.out.print("Enter priority number: ");
                    priority = sc.nextInt();
                    sc.nextLine();
                
                    Customer newCustomer = new Customer(name, priority);
                    list.add(newCustomer);
                    break;
                    
                case 2:
                    Customer currentCustomer = list.remove();
                    
                    if (currentCustomer != null){
                        System.out.println(currentCustomer);
                    }    
                    else{
                        System.out.println("No Customers on the list\n");
                    }    
                    break;
                    
                case 3:
                    Customer[] heapName = new Customer[0];
                    heapName = list.toArray(heapName);
                    
                    System.out.println("Heap in array form: ");
                    for (int i = 0; i < heapName.length; ++i) {
                    	System.out.println(heapName[i]);
                    }
                    break;
                      
                case 4:
                	System.out.printf("\nExiting program\n");
                	notDone = false;
                	break;
                    
                default:
                    System.out.printf("Input unavailable\n\n");
            }
        }
	}  
	public static void introProgram(){
    	System.out.printf("This program allows a restauranteer to add customers to a wait list.\nWhen you add a customer, " +
    			"add their Name, and the priority of the customer (priority value: 1 - 7)\n");
    	System.out.printf("Priority values are:\n1. VIP\n2. Advance Call\n3. Seniors\n4. Veterans\n5. Group of 4 or more\n6. Families with children\n"
    			+ "7. Everyone else\n\n");
    }
}
