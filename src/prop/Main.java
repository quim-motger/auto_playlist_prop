package prop;

import prop.domain.UserController;

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

        UserController cont = new UserController();
        cont.addUser("test","test",3343434,"ES");
        System.out.println(cont.obtainUser("test"));
    }
}
