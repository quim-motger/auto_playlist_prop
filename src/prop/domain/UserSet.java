package prop.domain;

import java.util.ArrayList;

/**
 * User set
 * @author  joaquim.motger
 */

public class UserSet {

    private ArrayList<User> users;
    private int size;


    /**
     * <b>UsersSet</b> class constructor
     */
    public UserSet() {
        size = 0;
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
     * Getter method of <b>size</b>
     * @return      size
     */
    public int getSize() {
        return size;
    }

    /**
     * Add <b>user</b> to <b>users</b>
     * @params  user  new <b>user</b> to add
     * @return  i   <b>user</b> position with same name; -1 if name available
     */
    public int addUser(User user) {
        int i = getUserPos(user.getName());
        if (i == -1) {
            users.add(user);
            ++size;
        }
        return i;
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
     * @return  user    the user removed; null if not found
     */
    public User removeUser (String name) {
        int i = getUserPos(name);
        if (i != -1) return users.remove(i);
        else return null;
    }

    /**
     * Getter method of the <b>position</b> of <b>User</b> with requested <b>name</b>
     * @params name  user <b>name</b>
     * @return      position  user position in <b>users</b>; -1 if not found
     */
    private int getUserPos(String name) {
        int i = 0;
        boolean found = false;
        while(!found && i < size) {
            User user = users.get(i);
            if (user.getName().equals(name)) found = true;
            else ++i;
        }
        if (found) return i;
        else return -1;
    }
}
