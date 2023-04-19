package sg.edu.nus.iss;

import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {

        String dirPath = args[0];
        //use File class to check if directory exists
        //if directory doesn't exist, use mkdir to create.
        File newDirectory = new File(dirPath);
        if (newDirectory.exists()){
            System.out.println("Directory " + newDirectory + " already exists");
        }else{
            newDirectory.mkdir();
        }

        System.out.println("Welcome to my Shopping Cart!");

        //List collection to store the cart items of login user
        List<String> cartItems = new ArrayList<String>();

        Console con = System.console();
        String input = "";
        
        //used to keep track of current login user
        //this is also used as the filename to store the user cart items
        String loginuser="";

        //exit while loop if keyboard 'input' is "quit"
        while (!input.equals("quit")){
            input=con.readLine("What do you want to do?");
            
            if (input.startsWith("login")){
                Scanner scan = new Scanner(input.substring(5));
                
                while (scan.hasNext()){
                    loginuser = scan.next();
                }
                
                //check if the file <loginuser> exists, else create a new file 
                // else(maybe) override
                File loginFile = new File(dirPath + File.separator +loginuser);
                if (loginFile.exists()){
                    System.out.println("File " + loginuser + " already exists");
                }else{
                    //login File is the directory Path "dirPath" joined with "loginuser"
                    loginFile.createNewFile();
                }
            }
            if (input.startsWith("users")){
                File directoryPath = new File(dirPath);
                String[] directoryListing = directoryPath.list();

                System.out.println("List of files and directories in the specific folder " + dirPath);
                for (String file: directoryListing){
                    System.out.println(file);
                }
            }

            if (input.startsWith("add")){
                input = input.replace(',',' ');

                // 1.use FileWriter & PrintWriter to write to a <loginuser> file
                // instantiate the objects 
                FileWriter fw = new FileWriter(dirPath + File.separator +loginuser, false);
                PrintWriter pw = new PrintWriter(fw);

                String currentScanString = "";
                Scanner inputScanner = new Scanner(input.substring(4));
                while(inputScanner.hasNext()){
                    currentScanString=inputScanner.next();
                    cartItems.add(currentScanString);
                    
                    //2. write to file using PW
                    pw.write("\n" + currentScanString);
                    
                }

                //flush and close FW and PW objects after
                pw.flush();
                pw.close();
                fw.close();
            }

        }

    }
}
