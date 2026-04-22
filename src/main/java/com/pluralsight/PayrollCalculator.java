package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class PayrollCalculator {

    //declared all my reusable variables here
    //so I can use them in the try block and not have to declare them again
    static Scanner userInput = new Scanner(System.in);
    static int lineNumber = 0;
    static String line;
    static int employeeId;
    static String name;
    static double hoursWorked;
    static double payRate;
    static String readFileName;
    static String writeFileName;


    public static void main(String[] args) {

        System.out.println("Welcome to the Payroll Calculator!");
        System.out.println("-----------------------------\n");


        System.out.print("Enter the name of the file you want to access: ");
        readFileName = userInput.nextLine();

        System.out.print("Enter the name of the file you want to write to: ");
        writeFileName = userInput.nextLine();

        //read the employee data from the file and create an employee object for each line of data
        //try-catch block to handle any errors that may occur
        try {

            //create a file reader object to read the file from the resources folder
            FileReader fileReader = new FileReader("src/main/resources/" + readFileName);

            //create a buffered reader object to read the file line by line
            BufferedReader bufReader = new BufferedReader(fileReader);

            //create a FileWriter object
            //This one overwrites the previous data
            FileWriter flWriter = new FileWriter("src/main/resources/" + writeFileName, true);
            //This one appends(Adds more) the data to the file
            //instead of overwriting it

            //Create a BufferedWriter object
            //BufferedWriter is a wrapper class for FileWriter
            BufferedWriter bufWriter = new BufferedWriter(flWriter);


            //read the file line-by-line
            //Header for the Data Structure: ID|Name|Gross Pay
            System.out.println("\nEmployee Data: ID|Name|Gross Pay\n");

            while ((line = bufReader.readLine()) != null) {

                // Skip first line
                lineNumber++;

                if (lineNumber == 1) {
                    continue;
                }
                //process the rest of the lines

                //split the line into an array of strings using the pipe character as the delimiter
                String[] employeeData = line.split("\\|");
                //System.out.println( line );

                //assign the values from the array to the variables
                employeeId = Integer.parseInt(employeeData[0]);
                name = employeeData[1];
                hoursWorked = Double.parseDouble(employeeData[2]);
                payRate = Double.parseDouble(employeeData[3]);


                //print out the employee data for debugging purposes
                //System.out.println(Arrays.toString(employeeData));

                //create an employee from the employee class pass the data to the constructor
                EmployeeData employee = new EmployeeData(employeeId, name, hoursWorked, payRate);
//                System.out.println("ID: " + employee.getEmployeeId()
//                        + ", Name: " + employee.getName()
//                        + ", Hours Worked: " + employee.getHoursWorked()
//                        + ", Pay Rate: $" + employee.getPayRate()
//                        + ", Gross Pay: $" + employee.getGrossPay());

                //call gross pay method on the employee to print out their gross pay
                System.out.printf("ID: %s - Name: %s, Gross Pay: %.2f\n"
                        , employee.getEmployeeId(), employee.getName(), employee.getGrossPay());

                //you are done son!!!
                //write the employee data to the file
                //bufWriter.write(employee.getEmployeeId() + "|" + employee.getName() + "|" + employee.getHoursWorked() + "|" + employee.getPayRate() + "\n");

                String myDataToBeSaved = String.format("%d|%s|%.2f\n",
                        employee.getEmployeeId(),
                        employee.getName(),
                        employee.getGrossPay());
                bufWriter.write(myDataToBeSaved);


            }

            bufReader.close();
            fileReader.close();
            bufWriter.close();
            flWriter.close();
            System.out.println("Done!");


        } catch (java.io.FileNotFoundException e) {
            System.out.println("The file you are trying to read does not exist. Please check the file name and try again.");
        } catch (Exception e) {
            System.out.println("An error occurred while reading the file data.");
        }

    }

}

