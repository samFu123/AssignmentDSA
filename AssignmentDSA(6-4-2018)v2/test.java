
/**
 * assigment
 * ITP4510 Data Structures and Algorithms
 * 2017-18 Sem2
 * 
 * @Name       :Fu Ka Hing
 * @Class      :IT114105/1C
 * @Student ID :170309843
 * @Date       :5-April-2018
 * 
 * DECLARATION
 * I understand the meaning of academic dishonesty, in particular plagiarism, copyright infringement 
 * and collusion. I am aware of the consequences if found to be involved in these misconducts.
 * I hereby declare that the work submitted for the "IT4510 Data Structures & Algorithms" is 
 * authentic record of my own work.
 */
import java.util.Random;
import java.util.Scanner;
public class test {
    public static void main( String[] args ) {

        Queue waitLine = new LinkedQueue(); 
        String ch;
        int maxWaiting=0; //save the Maximum Waiting Line Length
        int servedcustomer = 0; // save the customer served
        int countWait=0; // count  the waiting customer
        System.out.println("--------SETUP SIMULATION ENVIROMENT------------");
        int simuLength;         //save imulength
        System.out.print("Input simulation length (min) :" );   

        simuLength = NoZeroInput("Input simulation length (min) :" );
        int maxTellers; //save counternum
        System.out.print("Input number of counter :" ); 
        maxTellers = NoZeroInput("Input number of counter :" );
        int[] counter = new int[maxTellers];
        int maxTime; //save maxtime
        System.out.print("Input maximun srving time for a customer :" );    
        maxTime = NoZeroInput("Input maximun srving time for a customer :" );
        int seed; 
        System.out.print("Input seed number :" );   
        seed = canZeroInput("Input seed number :" );
        System.out.println();   
        System.out.println("--------START SIMULATION------------");
        int currentTime = 0; //save currentTime
        int servingTime;  //save servingTime
        if (seed ==  0) {        
            while ( currentTime < simuLength){ //when currentTime is low then simuLength keep run

                currentTime++; //currentTime+1
                System.out.print("A customer with serving time :");            
                servingTime = canZeroInput("A customer with serving time :");    
                while(servingTime > maxTime){
                    System.out.println("The serving time must lower then  "+maxTime);  
                    System.out.print("A customer with serving time :");   
                    servingTime = canZeroInput("A customer with serving time :");
                }
                int countFull = 0;

                for(int i = 0; i < maxTellers; i++){     // Check whether has customer finished the servicing and exited from the teller.
                    if(currentTime== counter[i])
                        counter[i] = 0;
                    if   (counter[i] == 0)         //count the  counter is full
                        countFull++;
                }

                if(waitLine.size() != 0) {         //Let customers enter counter when there not have other customers
                    if (countFull !=0) {
                        for(int i = 0; i < maxTellers; i++){   
                            if   (counter[i] == 0 && waitLine.size() != 0)   {
                                counter[i]=(((int) waitLine.dequeue())+currentTime);
                                servedcustomer++;
                            }
                        }
                    }
                }
                int checkCounterFull=0; //check the counter is full
                for(int i = 0; i < maxTellers; i++){       //Let customers enter counter  If counter is empty
                    if (servingTime == 0)    // Without customers
                        break;
                    if   (counter[i] != 0)   //Counter has other customers
                        checkCounterFull++;
                    if   (counter[i] == 0)    {  //Let customers enter counter
                        counter[i] = servingTime+currentTime;
                        servedcustomer++;
                        break;
                    }
                }
                if  (checkCounterFull==maxTellers) {    //Put in the queue if all the tellers are full
                    waitLine.enqueue(servingTime);                  
                }
                if (maxWaiting<=waitLine.size()) //save the maxWaiting
                    maxWaiting = waitLine.size();    
                ch = "R"+currentTime +"  ";
                System.out.printf("%-3s",ch);              //print the currentTime
                for(int i = 0; i <maxTellers; i++){             //print all the counter
                    ch = "Teller_"+(i+1)+"["+counter[i]+"]";
                    System.out.printf("%14s",ch);   
                }            
                int[] queueSave = new int[waitLine.size()];     
                ch = "Waiting Queue[";                //print Waiting Queue
                int queuesize = waitLine.size();
                for(int i = 0; i <  queuesize; i++)      {
                    queueSave[i] = (int) waitLine.dequeue();
                    ch = ch +" "+ queueSave[i];
                }       

                for(int i = 0; i <  queueSave.length; i++)       {          
                    waitLine.enqueue(queueSave[i]);   
                }           
                ch = ch + " ]";
                System.out.printf("%14s",ch);
                System.out.println();
                countWait = countWait+waitLine.size();              
            } 
        }
        else{       
            int[] seedArray = Random(seed,simuLength,maxTime);
            while ( currentTime < simuLength){ //when currentTime is low then simuLength keep run
                currentTime++;  //currentTime+1                
                int count = 0;

                for(int i = 0; i < maxTellers; i++){ // Check whether has customer finished the servicing and exited from the teller.
                    if(currentTime== counter[i])
                        counter[i] = 0;
                    if   (counter[i] == 0)                      
                        count++;
                }

                if(waitLine.size() != 0) { //check have customer  in the queue
                    if (count !=0) {     // Put in the teller if a teller is available                         
                        for(int i = 0; i < maxTellers; i++){   
                            if   (counter[i] == 0 && waitLine.size() != 0)    {
                                counter[i]=(((int) waitLine.dequeue())+currentTime);
                                servedcustomer++;
                            }
                        }
                    }
                }
                int checkCounterFull=0;//check the counter is full
                for(int i = 0; i < maxTellers; i++){       //Let customers enter counter  If counter is empty
                    if (seedArray[currentTime-1] == 0) // Without customers
                        break;
                    if   (counter[i] != 0)    //Counter has other customers
                        checkCounterFull++;
                    if   (counter[i] == 0)    {  //Let customers enter counter
                        counter[i] = seedArray[currentTime-1]+currentTime;
                        servedcustomer++;
                        break;
                    }
                }
                if  (checkCounterFull==maxTellers) { //Put in the queue if all the tellers are full
                    waitLine.enqueue(seedArray[currentTime-1]);                         
                }
                if (maxWaiting<waitLine.size())  //save the maxWaiting
                    maxWaiting = waitLine.size();
                ch = "R"+currentTime +"  ";
                System.out.printf("%-5s",ch);   //print the currentTime
                for(int i = 0; i <maxTellers; i++){
                    ch = "Teller_"+(i+1)+"["+counter[i]+"]";    //print all the counter
                    System.out.printf("%-15s",ch);                 
                }                

                int[] queueSave = new int[waitLine.size()];
                ch = "Waiting Queue[";            //print Waiting Queue
                int queuesize = waitLine.size();
                for(int i = 0; i <  queuesize; i++)      {
                    queueSave[i] = (int) waitLine.dequeue();
                    ch = ch +" "+ queueSave[i];
                }       

                for(int i = 0; i <  queueSave.length; i++)       {          
                    waitLine.enqueue(queueSave[i]);   
                }           
                ch = ch + " ]";
                System.out.printf("%-15s",ch);
                System.out.println();
                countWait = countWait+waitLine.size();         

            } 
        }
        System.out.println();        // Display the current status for end of this minute
        System.out.println("--------END OF SIMULATION------------");
        System.out.println("Total min simulated : "+simuLength+" minutes");        
        System.out.println("Number of Tellers: "+ maxTellers);
        System.out.println("Number of customer served: "+ servedcustomer+" costomers");
        System.out.println("Average Waiting Line Length: "+ ((double)countWait/simuLength)+" costomers");
        System.out.println("Maximum Waiting Line Length "+ maxWaiting+" customer");
        System.out.println("-------------------------------------");
    }

    private static int canZeroInput(String string) {  
        int i = 0 ;
        boolean right = false;
        while(right == false) {         //keep input if input not correct
            try {
                Scanner input = new Scanner(System.in);
                i= Integer.parseInt(input.next());       
                if (i < 0)
                    throw new NegativeNumberException();          
                right = true;
            }
            catch (NegativeNumberException e) {     //catch the  number negative
                System.out.println("You must input positive number");
                System.out.println("please enter again");
                System.out.print(string);
            }
            catch (NumberFormatException e) {   //catch the number format error
                System.out.println("You must input number!");
                System.out.println("please enter again");
                System.out.print(string);
            }
            catch (ArithmeticException  e) {    //catch other Exception
                System.out.println("You must input correct number!");
                System.out.println("please enter again");
                System.out.print(string);
            }
        }      
        return i;   
    }

    private static int NoZeroInput(String string)
    {
        int i = 0 ;
        boolean right = false;
        while(right == false) {         //keep input if input not correct
            try {
                Scanner input = new Scanner(System.in);
                i= Integer.parseInt(input.next());
                if (i == 0)
                    throw new DivideByZeroException();
                if (i < 0)
                    throw new NegativeNumberException();

                right = true;
            } 
            catch (NegativeNumberException e) { //catch the  number negative
                System.out.println("You must input positive number");
                System.out.println("please enter again");
                System.out.print(string);
            }
            catch (DivideByZeroException e) { //catch the  number zero
                System.out.println("Denominator cannot be ZERO");
                System.out.println("please enter again");
                System.out.print(string);
            }
            catch (NumberFormatException e) { //catch the number format error
                System.out.println("You must input number!");
                System.out.println("please enter again");
                System.out.print(string);
            }
            catch (ArithmeticException  e) { //catch other Exception
                System.out.println("You must input correct number!");
                System.out.println("please enter again");
                System.out.print(string);
            }
        }      
        return i;   

    } 

    private static int[] Random(int seed,int simuLength,int maxTime) {       
        int seedArray[] = new int [simuLength]; //save random number
        Random rnd = new Random();   
        rnd.setSeed(seed); 
        for (int i=0; i<simuLength; i++) {     //save all random number to the array
            seedArray[i]= rnd.nextInt(maxTime+1);
        }
        return seedArray; //return the random number
    }

}
