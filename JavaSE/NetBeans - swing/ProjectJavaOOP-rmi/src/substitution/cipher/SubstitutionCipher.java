
package substitution.cipher;

public abstract class SubstitutionCipher {

    public static String encript(String num){
        if(num != null) {
            
            num = new StringBuilder(num).reverse().toString();
            
            int[] arr = createArrIntFromString(num);
            
            for(int index = 0; index < arr.length ; ++index ) {
                if(index % 2 == 0) {
                    arr[index] = arr[index] + 5;
                }
                else {
                    arr[index] = arr[index] + 4;
                }
                arr[index] = (arr[index] >= 10 ) ?arr[index]%10 :arr[index];
           
            }
            
            return createStringFromArrInt(arr);
        }
        
        return null;
    }
    
    public static String decript( String num ) {
        if(num != null ) {
            
            num = new StringBuilder(num).reverse().toString();
            
            int[] arr = createArrIntFromString(num);
            
            for(int index = 0 ; index < arr.length ; ++index ) {
                
                if(index % 2 == 0 )
                    arr[index] = (arr[index] >= 5)?arr[index] - 5:arr[index] + 5;
                else
                    arr[index] = (arr[index] >= 4)?arr[index] - 4:(arr[index]+10) - 4;
                
            }
            return createStringFromArrInt(arr);
        }
        return null;
    }
    
    private static int[] createArrIntFromString(String stringForArr){
        
        if( stringForArr != null ) {
            
            int arr[] = new int[stringForArr.length()];
            
            for(int index = 0 ; index < stringForArr.length() ; ++ index) {
                arr[index] = Character.getNumericValue(stringForArr.charAt(index));
            }
            
            return arr;
        }
        return null;
    }
    
    private static String createStringFromArrInt(int[] arr ){
        String returnString = "";
        
        for(int i = 0 ; i < arr.length ; i++ ) {
            returnString += arr[i];
        }
        
        return returnString;
    }
    
}

