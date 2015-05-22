package prop.domain;

import prop.ErrorString;
import prop.PropException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * User set
 * @author  joaquim.motger
 */

public class UserSet {

    private TernarySearchTree<User> users;
    private static final String delimiter = "\n\n";


    /**
     * <code>UsersSet</code> class constructor
     */
    public UserSet() {
        users = new TernarySearchTree<>();
    }

    /**
     * Getter method of the <code>users</code>
     * @return      users
     */
    public ArrayList<User> getUsers() {
        return users.getList();
    }

    /**
     * Getter method of <code>users</code> size
     * @return      users size
     */
    public int getSize() {
        return users.getSize();
    }

    /**
     * Add <code>user</code> to <code>users</code>
     * @param  user     new <code>user</code> to add
     * @throws  Exception
     */
    public void addUser(User user) throws PropException {
        String name = user.getName();
        if (!contains(name)) {
            users.put(name,user);
        } else throw new PropException(ErrorString.EXISTING_USER);
    }

    /**
     * Returns true if the user set contains the user with name <code>name</code>
     * @param name     the name of the user
     * @return          true if present,
     *                  false if not present
     */
    public boolean contains(String name) {
        return users.contains(name);
    }

    /**
     * Getter method of the <code>User</code> with name <code>name</code>
     * @param   name    <code>user</code> name
     * @return  user    the user; null if not found
     */
    public User getUserByName(String name) throws PropException{
        if (contains(name)) return users.get(name);
        else throw new PropException(ErrorString.UNEXISTING_USER);
    }

    public ArrayList<User> findUsers(String prefix) {
        return users.matchPrefix(prefix);
    }

    /**
     * Remove <code>user</code> with name <code>name</code> from <code>users</code>
     * @params  name    <code>user</code> name
     * @throws  Exception
     */
    public void removeUser (String name) throws Exception {
        if (contains(name)) {
            users.remove(name);
        }
        else throw new Exception(ErrorString.UNEXISTING_USER);
    }

    /**
     * Get the <code>UserSet</code> in String format
     * @return      a <code>String</code> with the <code>UserSet</code> in the specified format
     */
    public String toString() {
        String s = "";
        for (User user : users.getList()) {
            s += user.toString() + delimiter;
        }
        return s;
    }

    /**
     * Get the value of the String <code>s</code> that contains a UserSet in the specified format
     * @param s     the String that contains the UserSet
     * @return      a UserSet
     * @throws      Exception
     */
    public static UserSet valueOf(String s, ListController lc, SongController sc) throws Exception {
        if (s == null) throw new Exception(ErrorString.NULL);
        else {
            String[] users = s.split(Pattern.quote(delimiter));
            UserSet us = new UserSet();
            for (String r : users) {
                us.addUser(User.valueOf(r, lc, sc));
            }
            return us;
        }
    }
    /*
    public Iterator<User> iterator() {
        return new userIterator();
    }
    
    public class userIterator implements Iterator<User> {

        private int i;
        
        public userIterator() {
            i=0;
        }
        
        @Override
        public boolean hasNext() {
            return i<(users.size()-1);
        }

        @Override
        public User next() {
            ++i;
            return users.get(i-1);
        }

        @Override
        public void remove() {
            users.remove(i);
        }
    }*/
}
