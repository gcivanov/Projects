/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomgenerator.random;

import java.util.Random;

public class MyRandom {
    
    public static int[] generateRandomQuestion(int numOfAllQuestions, int num) {
        int min = 0;
        int max = numOfAllQuestions -1 ;
        Random rand = new Random();
        int[] returnVal = new int[num];
        
    //        returnVal[0] = rand.nextInt((max - min) + 1) + min;
            returnVal[0] = min + (int)(Math.random() * ((max - min) + 1));

            for(int i = 1 ; i < returnVal.length ; ++i) {
    //            returnVal[i] = rand.nextInt((max - min) + 1) + min;
                returnVal[i] = min + (int)(Math.random() * ((max - min) + 1));

                for(int j = 0 ; j < i ; j++) {
                    if(returnVal[i] == returnVal[j]) {
                        returnVal[i] = rand.nextInt((max - min) + 1) + min;
                        j=-1;
                    }
                }
            }
        
        
        
        return returnVal;
    }
    
}
