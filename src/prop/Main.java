package prop;

import prop.domain.Gender;

import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Rosa!");

        Calendar dob = Calendar.getInstance();
        dob.set(1995, 2, 6);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
            age--;
        } else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
                && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }

        System.out.println(Gender.FEMALE.toString());
    }
}
