import java.util.ArrayList;

public class FreelancerTable {
    ArrayList<Freelancer>[] freelancerTable;
    int size;

    FreelancerTable(int size) {
        this.size = size;
        freelancerTable = new ArrayList[size];
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
        if (freelancerTable[code] != null) {
            boolean isIn = false;
            int ind = 0;
            for (int i = 0; i < freelancerTable[code].size(); i++) {
                if (freelancerTable[code].get(i).getFreelancerID().equals(ID)) {
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



    public boolean insert(Freelancer f) {
        int code = (int) hashC(f.getFreelancerID());
        int ind = findIndex(code,f.getFreelancerID());
        if (ind == -1 && freelancerTable[code] != null) {
            freelancerTable[code].add(f);
            return true;
        } else if (ind == -1 && freelancerTable[code] == null) {
            freelancerTable[code] = new ArrayList<>();
            freelancerTable[code].add(f);
            return true;
        } else return false;
    }

    public boolean remove(Freelancer f) {
        int code = (int) hashC(f.getFreelancerID());
        int ind = findIndex(code,f.getFreelancerID());
        if (ind == -1) {
            return false;
        } else {
            freelancerTable[code].remove(ind);
            return true;
        }
    }


    public Freelancer getFreelancer(String ID) {
        int code = (int) hashC(ID);
        int ind = findIndex(code,ID);
        if (ind == -1) {
            return null;
        } else {
            return freelancerTable[code].get(ind);
        }
    }
}

