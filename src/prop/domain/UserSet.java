package prop.domain;

import prop.ErrorString;

import java.util.ArrayList;

/**
 * User set
 * @author  joaquim.motger
 */

public class UserSet {

    private ArrayList<User> users;
    private static final char delimiter = '\n';


    /**
     * <b>UsersSet</b> class constructor
     */
    public UserSet() {
        users = new ArrayList<User>();
    }

    /**
     * Getter method of <b>users</b>
     * @return      users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Getter method of <b>users</b> size
     * @return      users size
     */
    public int getSize() {
        return users.size();
    }

    /**
     * Add <b>user</b> to <b>users</b>
     * @params  user  new <b>user</b> to add
     */
    public void addUser(User user) throws Exception {
        if (user!=null) {
            int i = getUserPos(user.getName());
            if (i == -1) {
                users.add(user);
            } else throw new Exception(ErrorString.EXISTING_USER);
        }
        else throw new Exception(ErrorString.NULL);
    }

    /**
     * Getter method of the <b>User</b> with name <b>name</b>
     * @params  name    <b>user</b> name
     * @return  user    the user; null if not found
     */
    public User getUserByName(String name) {
        int i = getUserPos(name);
        if (i != -1) return users.get(i);
        else return null;
    }

    /**
     * Remove <b>user</b> with name <b>name</b> from <b>users</b>
     * @params  name    <b>user</b> name
     */
    public void removeUser (String name) throws Exception {
        int i = getUserPos(name);
        if (i != -1) {
            users.remove(i);
        }
        else throw new Exception(ErrorString.UNEXISTING_USER);
    }

    /**
     * Getter method of the <b>position</b> of <b>User</b> with requested <b>name</b>
     * @params name  user <b>name</b>
     * @return      position  user position in <b>users</b>; -1 if not found
     */
    private int getUserPos(String name) {
        int i = 0;
        boolean found = false;
        while(!found && i < users.size()) {
            User user = users.get(i);
            if (user.getName().equals(name)) found = true;
            else ++i;
        }
        if (found) return i;
        else return -1;
    }

    public String toString() {
        String s = "";
        int i;
        for (i = 0; i < users.size()-1; ++i) {
            s += users.get(i).toString() + delimiter;
        }
        s += users.get(i);
        return s;
    }

    public static UserSet valueOf(String s) throws Exception {
        String[] users = s.split(String.valueOf(delimiter));
        UserSet us = new UserSet();
        for (String r : users) {
            //us.addUser(User.valueOf(r));
        }
        return us;
    }
}
