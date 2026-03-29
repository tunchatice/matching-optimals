import java.util.ArrayList;

public class CustomerTable {
    ArrayList<Customer>[] customerTable;
    int size;
    CustomerTable(int size) {
        this.size=size;
        customerTable = new ArrayList[size];
    }
    public long hashC(String id) {
        long hash = 0;
        long base = 31;

        for (int i = 0; i < id.length(); i++) {
            hash = (hash * base + id.charAt(i)) % size;
        }

        return hash;
    }
    public int findIndex(int code, String ID) {
        if (customerTable[code] != null) {
            boolean isIn = false;
            int ind = 0;
            for (int i = 0; i < customerTable[code].size(); i++) {
                if (customerTable[code].get(i).getID().equals(ID)) {
                    ind = i;
                    isIn = true;
                }
            }
            if (isIn) {
                return ind;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public boolean insert(Customer c) {
        int code = (int) hashC(c.getID());
        int ind = findIndex(code,c.getID());
        if (ind == -1 && customerTable[code] != null) {
            customerTable[code].add(c);
            return true;
        } else if (ind == -1 && customerTable[code] == null) {
            customerTable[code] = new ArrayList<>();
            customerTable[code].add(c);
            return true;
        } else return false;
    }

    public boolean remove(Customer c) {
        int code = (int) hashC(c.getID());
        int ind = findIndex(code,c.getID());
        if (ind == -1) {
            return false;
        } else {
            customerTable[code].remove(ind);
            return true;
        }
    }


    public Customer getCustomer(String ID) {
        int code = (int) hashC(ID);
        int ind = findIndex(code,ID);
        if (ind == -1) {
            return null;
        } else {
            return customerTable[code].get(ind);
        }
    }
}

