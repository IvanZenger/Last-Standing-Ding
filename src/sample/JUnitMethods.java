package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Die folgenden Methode
 */
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
    }

	public List<String> getStringArray() {
		return stringArray;
	}
}
