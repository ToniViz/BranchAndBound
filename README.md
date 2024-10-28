Pastry Shop Problem Solver

This project provides a solution to the Pastry Shop Problem using the Branch and Bound algorithm. It is implemented in Java and allows the user to input data through either the console or a text file.

Problem Description: 

The Pastry Shop Problem involves assigning pastry orders to pastry-cooks in a way that minimizes the overall cost. Each pastry-cook has a specific cost associated with making each type of pastry, and the goal is to find the optimal assignment of pastry-cooks to orders.

Input Data: 
The input can be provided via a text file or through standard input if the file is not specified. The input data consists of:

First line: Integer n indicating the number of pastry-cooks in the shop.

Second line: Integer m indicating the number of different types of pastries offered.

Third line: Sequence of orders with n values separated by a hyphen (-), where each value indicates the type of pastry for each order.

Next n lines: A cost matrix C[1..n,1..m] where the value c_ij represents the cost of pastry-cook i making pastry j. Values in the cost matrix are separated by a space.

Example:

For a pastry shop with 5 pastry-cooks and 3 types of pastries, receiving 5 orders of types [1-1-3-2-1], the input file would look like:

5

3

1-1-3-2-1

2 5 3

5 3 2

6 4 9

6 3 8

7 5 8

Implementation Details: 

The solution uses a Branch and Bound algorithm with min-heaps and a custom data structure called Node. Each Node stores the following information:

int[] pasteleros: An array representing the assignment of pastry-cooks.

boolean[] asignados: Indicates whether a pastry-cook has already been assigned an order.

int ultimoPedido: The last processed order.

int costeTotal: The total cost of the current assignment.

int estimacionOptimista: An optimistic estimate of the cost for this assignment.


Execution: 

To execute the program, use the following syntax:

bash

java pasteleria [-t][-h] [input_file] [output_file]

or

bash

java -jar pasteleria.jar [-t][-h] [input_file] [output_file]


Arguments: 

-t: Traces each step of the algorithm, showing the application of the Branch and Bound technique.

-h: Displays help information and command syntax.

input_file: The name of the file containing the input data (orders and cost matrix).

output_file: The name of the file where the output will be saved. If this file already exists, an error is thrown. If this argument is missing, the program prints the results to the console.


Example Command: 

bash

java -jar pasteleria.jar -t example_input.txt result_output.txt

This command will read the input from example_input.txt, trace each step of the algorithm, and save the results to result_output.txt.

Requirements: 
Java SE Development Kit (JDK 8 or higher) installed.

How It Works: 
The program attempts to find an optimal solution by branching on different order assignments and pruning suboptimal solutions based on the cost estimates. The use of min-heaps ensures efficient management of nodes during the algorithm's execution.

Author:

Developed by Anthony Viscaino.

Feel free to reach out if you have any questions or suggestions for improvements!
