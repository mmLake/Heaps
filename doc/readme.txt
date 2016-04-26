Maya Lake
CS 241, Assignment 1
25 April 2016

Description: 
Part 1: instantiating a sorted heap using nodes and again using arrays
Part 2: creating a method that prompts user to input customer name and priority integer. Uses a node heap to instantiate a list of Customers. user can add a customer to the node heap or remove the first customer on the list. 

Approach: Started with the constructors. Created the node and Customer class. Then wrote the add and remove method since that is the bulk of the assignment. 
Then created the UI so that I could test my add and remove methods without the program breaking. Finally finished with the array methods so that I could print the list of Customers and make sure that the list of customers are correctly sorted.

Conclusion:
	Because my algorithm for the add and remove method is linear, the runtime for finding the location of the last node is linear as well. Since the add and remove method also call the siftUp and siftDown methods, the runtime for those methods are taken into account as well. Overall the runtime for my project adheres to the maximum runtime of nLogn given to us in the assignment.

	Unfortunately I was unable to create an algorithm that worked for both the add and remove method. While the algorithms are similar, I had to tweak it so that it would apply to each of the necessary applications for the different methods. 

Upon reflection I also feel as though I have an unnecessary amount of cases I take into account for both the add and remove method. The purpose of the additional cases were to account for special cases and also so that runtime would be faster since the location of the last node is given when a heap is complete. However perhaps the code would be cleaner had taken out the extra cases inside both add and remove methods. 