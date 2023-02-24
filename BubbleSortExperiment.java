/*
This program compares the performance between the bubble sort algorithm
and a modified bubble sort algorithm which attempts to shorten the number of cycles
required to run the bubble sort algorithm

The theory being tested is that by executing multiple comparisons and swaps in a single
cycle rather than doing one comparison and swap per cycle will reduce the cycles need to 
execute the program and reduce as well the amount of time needed to run the sorting algorithm

Results:
The modified bubble sort's Cycles were reduced significantly. Time to run was faster a few times but not most of the time.

*/


import java.util.Random;



public class BubbleSortExperiment {

    static int arr[]; /* This will be the array in which the original bubble sort will be tested */
    static int arr2[];/* This will be the array in which the modified bubble sort will be tested */
    static int temp = 0;/* This is the temp variable needed for the swap in the bubble sort */
    static int CycleCounter1=0;/* This will count the number of cycles in the original bubble sort */
    static int CycleCounter2=0;/* THis will count the number of cycles in the modified bubble sort */
    public static void main(String[] args) {
        arr2 = new int[10000];/* Initializing the array and determining it's size */
        arr = new int[10000];/* Initializing the array and determining it's size */
        long tStart=0;/* Gets the first ping of system time in order to measure how long the method tested took to end */
        long tEnd=0;/* Gets the last ping of system time in order to measure how long the method tested took to end */
        long tFinal=0;/* Used to calculate the total time by subracting tStart from tEnd */
        double elapsedSeconds=0;/* */      
        int RandomNumber=0;/* Used to store randomly generated numbers for the arrays */
         float Cycleimprovement =0;/* Used to calculate the percentage of improvement in the number of cycles of the modified bubble sort compared to the original bubble sort*/
        Random rd = new Random(); /* Used to generate random numbers */    
        for (int i = 0; i < arr.length; i++) {
            RandomNumber=rd.nextInt();
            arr[i] = RandomNumber;
            arr2[i] = RandomNumber;
        }//end of for 
        
        //calling the original bubble sort
         tStart = System.currentTimeMillis();
        OriginalBubbleSort();
         tEnd = System.currentTimeMillis();
         tFinal = tEnd - tStart;
         elapsedSeconds = tFinal / 1000.0;
        System.out.println("Time for Original BubbleSort " + elapsedSeconds);
        
        
        //calling the modified bubble sort
        tStart = System.currentTimeMillis();
        ModifiedBubbleSort();
        tEnd = System.currentTimeMillis();
        tFinal = tEnd - tStart;
        elapsedSeconds = tFinal / 1000.0;
        System.out.println("Time for New BubbleSort " + elapsedSeconds);
        Cycleimprovement =100-((float)CycleCounter2/(float)CycleCounter1)*100;
        System.out.println("Total cycle improvement equals= "+Cycleimprovement+"%");

    }//end of main

    
    
    static void OriginalBubbleSort() {
        int ArrayLength = arr2.length;
        int cycleCounter = 0;
        for (int i = 0; i < ArrayLength - 1; i++) {
            for (int j = 0; j < ArrayLength - i - 1; j++) {
                if (arr2[j] > arr2[j + 1]) {
                    temp = arr2[j];
                    arr2[j] = arr2[j + 1];
                    arr2[j + 1] = temp;
                }
                cycleCounter++;
            }
            cycleCounter++;
        }
        System.out.println("CycleCounter Old BubbleSort=" + cycleCounter);
        CycleCounter1=cycleCounter;
    }//end of method

    
    /*
    The following method swap(int) exchanges the elements in the array
    
    */
    static void swap(int i2) {
        temp = arr[i2];
        arr[i2] = arr[i2 + 1];
        arr[i2 + 1] = temp;
    }//end of method

    
    /* The following method compares the two elements in the array, and determines whether to swap them
    * @param int i: the i variable of the for of the ModifiedBubbleSort() method
    * @param int i2:  the i2 variable of the for of the ModifiedBubbleSort() method
    * @param int ArrayLength: the variable representing the ArrayLength
    * @param int x: The number of times the method will be called
    *
    * @return int i2: return the value of i2
    */
    static int SwapControl(int i, int i2, int ArrayLength, int TimesCalled) {
        if (arr[i2] > arr[i2 + 1]) {
            swap(i2);
            i2++;
        }//end of if
        else {
            if (i2 + TimesCalled < ArrayLength - i - 1) {
                i2++;
            }//end of if
        }//end of else
        return i2;
    }

    /*
    The modified bubble sort, works as follows:
    the modified bubble sort will execute 8 comparisons and swaps for 8 continous elements 
    in every cycle.
    If it can't carry out 8 comparisons and swaps because it is nearing the end of the array
    it will carry out 7 then 6 and so on.
    
    */
    
    static void ModifiedBubbleSort() {

        int ArrayLength = arr.length;
        int i2 = 0;
        int cycleCounter = 0;

        for (int i = 0; i < ArrayLength - 1; i++) {
            i2 = 0;
            while (i2 < ArrayLength - i - 1) {
                if (i2 + 7 < ArrayLength - i - 1) {
                    if (arr[i2] > arr[i2 + 1]) {

                        i2 = SwapControl(i, i2, ArrayLength, 7);
                        i2 = SwapControl(i, i2, ArrayLength, 7);
                        i2 = SwapControl(i, i2, ArrayLength, 7);
                        i2 = SwapControl(i, i2, ArrayLength, 7);
                        i2 = SwapControl(i, i2, ArrayLength, 7);
                        i2 = SwapControl(i, i2, ArrayLength, 7);
                        i2 = SwapControl(i, i2, ArrayLength, 7);
                        i2 = SwapControl(i, i2, ArrayLength, 7);
                    }//end of if
                    else {
                        i2++;
                    }
                }//end of if     
                else {

                    if (i2 + 6 < ArrayLength - i - 1) {
                        if (arr[i2] > arr[i2 + 1]) {

                            i2 = SwapControl(i, i2, ArrayLength, 6);
                            i2 = SwapControl(i, i2, ArrayLength, 6);
                            i2 = SwapControl(i, i2, ArrayLength, 6);
                            i2 = SwapControl(i, i2, ArrayLength, 6);
                            i2 = SwapControl(i, i2, ArrayLength, 6);
                            i2 = SwapControl(i, i2, ArrayLength, 6);
                            i2 = SwapControl(i, i2, ArrayLength, 6);
                        }//end of if
                        else {
                            i2++;
                        }
                    }//end of if     
                    else {
                        if (i2 + 5 < ArrayLength - i - 1) {
                            if (arr[i2] > arr[i2 + 1]) {

                                i2 = SwapControl(i, i2, ArrayLength, 5);
                                i2 = SwapControl(i, i2, ArrayLength, 5);
                                i2 = SwapControl(i, i2, ArrayLength, 5);
                                i2 = SwapControl(i, i2, ArrayLength, 5);
                                i2 = SwapControl(i, i2, ArrayLength, 5);
                                i2 = SwapControl(i, i2, ArrayLength, 5);
                            }//end of if
                            else {
                                i2++;
                            }
                        }//end of if            
                        else {
                            if (i2 + 4 < ArrayLength - i - 1) {
                                if (arr[i2] > arr[i2 + 1]) {

                                    i2 = SwapControl(i, i2, ArrayLength, 4);
                                    i2 = SwapControl(i, i2, ArrayLength, 4);
                                    i2 = SwapControl(i, i2, ArrayLength, 4);
                                    i2 = SwapControl(i, i2, ArrayLength, 4);
                                    i2 = SwapControl(i, i2, ArrayLength, 4);
                                }//end of if
                                else {
                                    i2++;
                                }
                            }//end of if            
                            else {
                                if (i2 + 3 < ArrayLength - i - 1) {
                                    if (arr[i2] > arr[i2 + 1]) {

                                        i2 = SwapControl(i, i2, ArrayLength, 3);
                                        i2 = SwapControl(i, i2, ArrayLength, 3);
                                        i2 = SwapControl(i, i2, ArrayLength, 3);
                                        i2 = SwapControl(i, i2, ArrayLength, 3);

                                    }//end of if
                                    else {
                                        i2++;
                                    }
                                }//end of if
                                else {
                                    if (i2 + 2 < ArrayLength - i - 1) {
                                        if (arr[i2] > arr[i2 + 1]) {

                                            i2 = SwapControl(i, i2, ArrayLength, 2);
                                            i2 = SwapControl(i, i2, ArrayLength, 2);
                                            i2 = SwapControl(i, i2, ArrayLength, 2);

                                        }//end of if
                                        else {
                                            i2++;
                                        }
                                    }//end of if
                                    else {
                                        if (i2 + 1 < ArrayLength - i - 1) {
                                            if (arr[i2] > arr[i2 + 1]) {

                                                i2 = SwapControl(i, i2, ArrayLength, 1);
                                                i2 = SwapControl(i, i2, ArrayLength, 1);

                                            }//end of if
                                            else {
                                                i2++;
                                            }
                                        }//end of if
                                        else {
                                            if (i2 + 0 < ArrayLength - i - 1) {
                                                if (arr[i2] > arr[i2 + 1]) {

                                                    i2 = SwapControl(i, i2, ArrayLength, 0);

                                                }//end of if
                                                else {
                                                    i2++;
                                                }//end of else
                                            }//end of if
                                        }//end of else
                                    }//end of else
                                }//end of else
                            }//end of else
                        }//end of else
                    }//end of else
                }//end of else
                cycleCounter++;

            }//end of for
            cycleCounter++;
        }//end of for
        CycleCounter2=cycleCounter;
        System.out.println("CycleCounter New BubbleSort=" + cycleCounter);
    }//end of method

}//end of class
