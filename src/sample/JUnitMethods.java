package sample;

import java.util.ArrayList;
import java.util.List;

public class JUnitMethods {
    private List<String> stringArray = new ArrayList<String>();

    public void addToList(String eintrag){
        stringArray.add(eintrag);
    }

    public int findInList(String eintrag){
        if(stringArray.indexOf(eintrag) != -1){
          return stringArray.indexOf(eintrag);
        }else{
            return 99;
        }
       /* for(String string : stringArray){
            if(string.equals(eintrag)){

            }
        }*/
    }

    public void clearStringArray() {
        this.stringArray.clear();
    }
}
