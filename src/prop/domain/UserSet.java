package prop.domain;

import prop.ErrorString;
import prop.PropException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * User set
 * @author  joaquim.motger
 */

public class UserSet implements Iterable<User>{

    private ArrayList<User> users;
    private static final String delimiter = "\n\n";


    /**
     * <code>UsersSet</code> class constructor
     */
    public UserSet() {
        users = new ArrayList<>();
    }

    /**
     * Getter method of the <code>users</code>
     * @return      users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Getter method of <code>users</code> size
     * @return      users size
     */
    public int getSize() {
        return users.size();
    }

    /**
     * Add <code>user</code> to <code>users</code>
     * @param  user     new <code>user</code> to add
     * @throws  Exception
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
     * Getter method of the <code>User</code> with name <code>name</code>
     * @param   name    <code>user</code> name
     * @return  user    the user; null if not found
     */
    public User getUserByName(String name) throws PropException{
        int i = getUserPos(name);
        if (i != -1) return users.get(i);
        else throw new PropException(ErrorString.NULL);
    }

    /**
     * Remove <code>user</code> with name <code>name</code> from <code>users</code>
     * @params  name    <code>user</code> name
     * @throws  Exception
     */
    public void removeUser (String name) throws Exception {
        int i = getUserPos(name);
        if (i != -1) {
            users.remove(i);
        }
        else throw new Exception(ErrorString.UNEXISTING_USER);
    }

    /**
     * Getter method of the <code>position</code> of <code>User</code> with requested <code>name</code>
     * @params name  user <code>name</code>
     * @return      position  user position in <code>users</code>; -1 if not found
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

    /**
     * Get the <code>UserSet</code> in String format
     * @return      a <code>String</code> with the <code>UserSet</code> in the specified format
     */
    public String toString() {
        String s = "";
        int i;
        for (i = 0; i < users.size()-1; ++i) {
            s += users.get(i).toString() + delimiter;
        }
        if (!users.isEmpty()) s += users.get(i).toString();
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
    }
}
