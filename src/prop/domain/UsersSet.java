package prop.domain;

import java.util.ArrayList;

/**
 * Users set
 * @author joaquim.motger
 * @version 1.0
 */

public class UsersSet {

    private ArrayList<User> users;
    private int size;


    /**
     * <b>UsersSet</b> class creator
     */
    public UsersSet() {
        size = 0;
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
     * Getter method of the <b>position</b> of <b>User</b> with requested <b>name</b>
     * @params name  user <b>name</b>
     * @return      position  user position in <b>users</b>; -1 if not found
     */
    public int getUserPos(String name) {
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
     * Remove <b>user</b> in position <b>i</b> from <b>users</b>
     * @params  i   <b>user</b> position in <b>users</b>
     */
    public void removeUser (int i) {
        if (i >= 0 && i < size) {
            users.remove(i);
            --size;
        }
    }
}
