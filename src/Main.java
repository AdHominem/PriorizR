import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Issue findTelephone = new Issue("Find Telephone");
        Issue dialNumber = new Issue("Dial Number");
        dialNumber.requires(findTelephone);
        Issue speak = new Issue("Speak");
        speak.requires(dialNumber);
        Task callMary = new Task("Call Mary", Arrays.asList(findTelephone, dialNumber, speak));
        System.out.println(callMary.toString());



    }
}
