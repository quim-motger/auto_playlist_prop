package prop.domain;

import java.util.ArrayList;

/**
 * User set
 * @author  joaquim.motger
 */

public class UserSet {

    private ArrayList<User> users;


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
     * Add <b>user</b> to <b>users</b>
     * @params  user  new <b>user</b> to add
     * @return      true if added; false if User already exists
     */
    public boolean addUser(User user) {
        int i = getUserPos(user.getName());
        if (i == -1) {
            users.add(user);
            return true;
        }
        return false;
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
     * @return      true if removed; false if user doesn't exist
     */
    public boolean removeUser (String name) {
        int i = getUserPos(name);
        if (i != -1) {
            users.remove(i);
            return true;
        }
        return false;
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
}
