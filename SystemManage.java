import java.util.ArrayList;

public class SystemManage {
    FreelancerTable hashF=new FreelancerTable(149993);
    CustomerTable hashC= new CustomerTable(149993);
    ArrayList<String> changeTier=new ArrayList<>();

    PriorityQueue queue_paint=new PriorityQueue();
    PriorityQueue queue_web_dev=new PriorityQueue();
    PriorityQueue graphic_design=new PriorityQueue();
    PriorityQueue data_entry=new PriorityQueue();
    PriorityQueue tutoring=new PriorityQueue();
    PriorityQueue cleaning=new PriorityQueue();
    PriorityQueue writing=new PriorityQueue();
    PriorityQueue photography=new PriorityQueue();
    PriorityQueue plumbing=new PriorityQueue();
    PriorityQueue electrical=new PriorityQueue();


    public boolean employFreelancer(String c, String f){
        if (hashF.getFreelancer(f)==null||hashC.getCustomer(c)==null){return false;}
        else if (hashF.getFreelancer(f).isAvailable()==false||
                hashC.getCustomer(c).blockedList.contains(f)||
                hashF.getFreelancer(f).isPermanentlyBanned()) {return false;}

        remove_from_heap(hashF.getFreelancer(f));

        hashF.getFreelancer(f).setAvailable(false);
        hashF.getFreelancer(f).setCurrentCustomer(c);
        hashC.getCustomer(c).currentEmployees.add(f);
        hashC.getCustomer(c).incrementEmploymentCustCount();
        return true;}
    public Customer completeANdRate(String f,int rating){
        Freelancer freelancer= hashF.getFreelancer(f);
        if(freelancer==null){return null;}
        if(rating<0||rating>5){return null;}
        int n= (freelancer.getCancelCount()+ freelancer.getCompletedCount());
        if(freelancer.isAvailable()||freelancer.isPermanentlyBanned()) {return null;}
        else{
            double avgRate=(((freelancer.getAverageRating()*n)+rating)/(n+1));
            freelancer.setAverageRating(avgRate);
            if (rating>=4){
                String service=freelancer.getServiceType();
                if(service.equals("paint")){
                    if(freelancer.getA()<100){
                        if (freelancer.getA()+2>100){freelancer.setA(100);}
                        else{
                        freelancer.setA(freelancer.getA()+2);}
                    }

                    if(freelancer.getE()<100){
                        freelancer.setE(freelancer.getE()+1);
                    }
                    if(freelancer.getT()<100){
                        freelancer.setT(freelancer.getT()+1);
                    }
                }
                else if(service.equals("web_dev")){
                    if(freelancer.getT()<100){
                        if (freelancer.getT()+2>100){freelancer.setT(100);}
                        else{
                            freelancer.setT(freelancer.getT()+2);}
                    }

                    if(freelancer.getA()<100){
                        freelancer.setA(freelancer.getA()+1);
                    }
                    if(freelancer.getR()<100){
                        freelancer.setR(freelancer.getR()+1);
                    }
                }
                else if(service.equals("graphic_design")){
                    if(freelancer.getR()<100){
                        if (freelancer.getR()+2>100){freelancer.setR(100);}
                        else{
                            freelancer.setR(freelancer.getR()+2);}
                    }

                    if(freelancer.getC()<100){
                        freelancer.setC(freelancer.getC()+1);
                    }
                    if(freelancer.getA()<100){
                        freelancer.setA(freelancer.getA()+1);
                    }
                }
                else if(service.equals("data_entry")){
                    if(freelancer.getE()<100){
                        if (freelancer.getE()+2>100){freelancer.setE(100);}
                        else{
                            freelancer.setE(freelancer.getE()+2);}
                    }

                    if(freelancer.getA()<100){
                        freelancer.setA(freelancer.getA()+1);
                    }
                    if(freelancer.getT()<100){
                        freelancer.setT(freelancer.getT()+1);
                    }
                }
                else if(service.equals("tutoring")){
                    if(freelancer.getC()<100){
                        if (freelancer.getC()+2>100){freelancer.setC(100);}
                        else{
                            freelancer.setC(freelancer.getC()+2);}
                    }

                    if(freelancer.getE()<100){
                        freelancer.setE(freelancer.getE()+1);
                    }
                    if(freelancer.getT()<100){
                        freelancer.setT(freelancer.getT()+1);
                    }
                }
                else if(service.equals("cleaning")){
                    if(freelancer.getE()<100){
                        if (freelancer.getE()+2>100){freelancer.setE(100);}
                        else{
                            freelancer.setE(freelancer.getE()+2);}
                    }

                    if(freelancer.getA()<100){
                        freelancer.setA(freelancer.getA()+1);
                    }
                    if(freelancer.getC()<100){
                        freelancer.setC(freelancer.getC()+1);
                    }
                }
                else if(service.equals("writing")){
                    if(freelancer.getA()<100){
                        if (freelancer.getA()+2>100){freelancer.setA(100);}
                        else{
                            freelancer.setA(freelancer.getA()+2);}
                    }

                    if(freelancer.getR()<100){
                        freelancer.setR(freelancer.getR()+1);
                    }
                    if(freelancer.getC()<100){
                        freelancer.setC(freelancer.getC()+1);
                    }
                }
                else if(service.equals("photography")){
                    if(freelancer.getR()<100){
                        if (freelancer.getR()+2>100){freelancer.setR(100);}
                        else{
                            freelancer.setR(freelancer.getR()+2);}
                    }

                    if(freelancer.getA()<100){
                        freelancer.setA(freelancer.getA()+1);
                    }
                    if(freelancer.getT()<100){
                        freelancer.setT(freelancer.getT()+1);
                    }
                }
                else if(service.equals("plumbing")){
                    if(freelancer.getE()<100){
                        if (freelancer.getE()+2>100){freelancer.setE(100);}
                        else{
                            freelancer.setE(freelancer.getE()+2);}
                    }

                    if(freelancer.getA()<100){
                        freelancer.setA(freelancer.getA()+1);
                    }
                    if(freelancer.getT()<100){
                        freelancer.setT(freelancer.getT()+1);
                    }
                }
                else{
                    if(freelancer.getE()<100){
                        if (freelancer.getE()+2>100){freelancer.setE(100);}
                        else{
                            freelancer.setE(freelancer.getE()+2);}
                    }

                    if(freelancer.getA()<100){
                        freelancer.setA(freelancer.getA()+1);
                    }
                    if(freelancer.getT()<100){
                        freelancer.setT(freelancer.getT()+1);
                    }
                }

            }
            freelancer.setAvailable(true);
            freelancer.incrementCompletedCount();
            freelancer.setMonthlyCompleted(freelancer.getMonthlyCompleted()+1);
            Customer c=hashC.getCustomer(freelancer.getCurrentCustomer());
            c.setTotalCustCount(c.getTotalCustCount()+1);
            int payment = (int) Math.floor(freelancer.getPrice() * (1 - c.getSubsidy()));
            String initial=c.decideTier(c.getTotalValidCount());
            c.setTotalSpent(c.getTotalSpent() + payment);
            String last=c.decideTier(c.getTotalValidCount());
            //if tier will change add to the list
            if(!(initial.equals(last))&&!(changeTier.contains(c.getID()))){changeTier.add(c.getID());}
            c.currentEmployees.remove(freelancer.getFreelancerID());
            freelancer.setCurrentCustomer(null);
            insert_heap(freelancer);
            return c;
        }
    }

    public Freelancer register_freelancer(String freelancerID, String serviceName,int basePrice,int T,int C,int R,int E,int A){
        Freelancer freeL=hashF.getFreelancer(freelancerID);
        if (freeL!=null){return null;}
        if (Freelancer.services.contains(serviceName)==false){return null;}
        if (basePrice<0||T<0||T>100||C<0||C>100||R<0||R>100||E<0||E>100||A<0||A>100){return null;}
        Freelancer f=new Freelancer(freelancerID,serviceName,basePrice,T,C,R,E,A);
        boolean successful= hashF.insert(f);
        if(successful){
            insert_heap(f);
            return f;}
        return null;
    }

    public Customer register_customer(String customerID){
        Customer customer=hashC.getCustomer(customerID);
        if (customer!=null){return null;}
        Customer c=new Customer(customerID);
        boolean successful= hashC.insert(c);
        if(successful){return c;}
        return null;

    }
    public String cancel_by_freelancer(String freelancerID) {
        Freelancer freeL = hashF.getFreelancer(freelancerID);
        if (freeL == null) {
            return null;
        }
        if (freeL.isAvailable()) {
            return null;
        }
        Customer c = hashC.getCustomer(freeL.getCurrentCustomer());
        c.currentEmployees.remove(freelancerID);
        //gets a 0 rate
        int n = (freeL.getCancelCount() + freeL.getCompletedCount());
        double avgRate = ((freeL.getAverageRating() * n)) / (n + 1);
        freeL.setAverageRating(avgRate);
        //skill degradation
        int T = freeL.getT();
        int C = freeL.getC();
        int R = freeL.getR();
        int E = freeL.getE();
        int A = freeL.getA();

        if (T - 3 < 0) {
            freeL.setT(0);
        } else {
            freeL.setT(T - 3);
        }

        if (C - 3 < 0) {
            freeL.setC(0);
        } else {
            freeL.setC(C - 3);
        }

        if (R - 3 < 0) {
            freeL.setR(0);
        } else {
            freeL.setR(R - 3);
        }

        if (E - 3 < 0) {
            freeL.setE(0);
        } else {
            freeL.setE(E - 3);
        }

        if (A - 3 < 0) {
            freeL.setA(0);
        } else {
            freeL.setA(A - 3);
        }
        freeL.setCurrentCustomer(null);
        freeL.setAvailable(true);
        freeL.incrementMonthlyCanceled();
        freeL.incrementCancelCount();
        if (freeL.getMonthlyCanceled() >= 5) {
            freeL.setPermanentlyBanned(true);
        }
        if (!freeL.isPermanentlyBanned()) {
            insert_heap(freeL);
        }
        return c.getID();
    }


    public boolean cancel_by_customer(String customerID,String freelancerID){
        Customer c=hashC.getCustomer(customerID);
        Freelancer f=hashF.getFreelancer(freelancerID);
        if(c==null||f==null){return false;}
        if(c.currentEmployees.contains(freelancerID)==false){return false;}
        f.setAvailable(true);
        f.setCurrentCustomer(null);
        c.currentEmployees.remove(freelancerID);
        String initialTier = c.decideTier(c.getTotalValidCount());
        c.incrementCancelCustCount();
        String newTier = c.decideTier(c.getTotalValidCount());
        if(!initialTier.equals(newTier) && !changeTier.contains(c.getID())){
            changeTier.add(c.getID());
        }

        insert_heap(f);
        return true;
    }
    public boolean blacklist(String customerID,String freelancerID){
        Customer c=hashC.getCustomer(customerID);
        Freelancer f=hashF.getFreelancer(freelancerID);
        if(c==null||f==null){return false;}
        //if(c.currentEmployees.contains(freelancerID)){return false;}
        if (c.blockedList.contains(freelancerID)){return false;}
        c.blockedList.add(freelancerID);
        return true;}

    public boolean unblacklist(String customerID,String freelancerID){
        Customer c=hashC.getCustomer(customerID);
        Freelancer f=hashF.getFreelancer(freelancerID);
        if(c==null||f==null){return false;}
        if (!c.blockedList.contains(freelancerID)){return false;}
        c.blockedList.remove(freelancerID);
        return true;
    }
    public Freelancer query_freelancer (String freelancerID){
        Freelancer f=hashF.getFreelancer(freelancerID);
        if(f==null){return null;}
        return f;
    }

    public Customer query_customer(String customerID){
        Customer customer=hashC.getCustomer(customerID);
        if (customer==null){return null;}
        return customer;
    }

    public Freelancer change_service(String freelancerID,String serviceName,int Price){
        Freelancer f=hashF.getFreelancer(freelancerID);
            if(f==null){return null;}
        if(Freelancer.services.contains(serviceName)==false||Price<0){return null;}
        f.setPendingPrice(Price);
        f.setPendingServiceType(serviceName);
        f.setPendingChange(true);
        return f;}
    public String simulate_month(){

        for (int i=0;i<hashF.size;i++){
            if(hashF.freelancerTable[i]!=null){
                for(int e=0;e<hashF.freelancerTable[i].size();e++){
                    Freelancer freelancer=hashF.freelancerTable[i].get(e);
                    boolean is_removed=false;
                    if(freelancer.isPendingChange()){
                        remove_from_heap(freelancer);
                        is_removed=true;
                        freelancer.setServiceType(freelancer.getPendingServiceType());
                        freelancer.setPrice(freelancer.getPendingPrice());
                        freelancer.setPendingServiceType(null);
                        freelancer.setPendingPrice(Integer.MIN_VALUE);
                        freelancer.setPendingChange(false);
                    }
                    if(freelancer.isBurnout()){

                        if(freelancer.getMonthlyCompleted()<=2){
                            is_removed=true;
                            remove_from_heap(freelancer);
                            freelancer.setBurnout(false);
                        }
                    }
                    else{
                        if(freelancer.getMonthlyCompleted()>=5){
                            is_removed=true;
                            remove_from_heap(freelancer);
                            freelancer.setBurnout(true);}
                    }
                    if(is_removed&&!freelancer.isPermanentlyBanned()){
                        insert_heap(freelancer);
                    }
                    freelancer.setMonthlyCanceled(0);
                    freelancer.setMonthlyCompleted(0);
                }
            }
        }
        if(changeTier!=null){

            for (int i=0;i<changeTier.size();i++){
                Customer customer=hashC.getCustomer(changeTier.get(i));
                customer.setLoyaltyTier(customer.decideTier(customer.getTotalValidCount()));
                customer.setSubsidy(customer.getLoyaltyTier());
            }
            changeTier=new ArrayList<>();
        }
        return "month complete";

    }

    public void insert_heap(Freelancer f){
        switch (f.getServiceType()){
        case "paint":
            queue_paint.insert(f);
            break;
        case "web_dev":
            queue_web_dev.insert(f);
            break;
        case "graphic_design":
            graphic_design.insert(f);
            break;
        case "data_entry":
            data_entry.insert(f);
            break;
        case "tutoring":
            tutoring.insert(f);
            break;
        case "cleaning":
            cleaning.insert(f);
            break;
        case "writing":
            writing.insert(f);
            break;
        case "photography":
            photography.insert(f);
            break;
        case "plumbing":
            plumbing.insert(f);
            break;
        case "electrical":
            electrical.insert(f);
            break;
    }}


    private PriorityQueue getQueueFor(String serviceName) {
        switch (serviceName) {
            case "paint":        return queue_paint;
            case "web_dev":      return queue_web_dev;
            case "graphic_design": return graphic_design;
            case "data_entry":   return data_entry;
            case "tutoring":     return tutoring;
            case "cleaning":     return cleaning;
            case "writing":      return writing;
            case "photography":  return photography;
            case "plumbing":     return plumbing;
            case "electrical":   return electrical;
            default:             return null;
        }
    }

    private ArrayList<Freelancer> getTopKForCustomer(Customer c, PriorityQueue q, int k) {

        ArrayList<Freelancer> tmp          = new ArrayList<>(); // potential ones
        ArrayList<Freelancer> tmpBlacklist = new ArrayList<>(); // blacklist of the customer

        // finding top-k //or less than k
        while (tmp.size() < k && q.size > 0) {
            Freelancer best = q.deleteMax();  // remove from heap

            if (c.blockedList.contains(best.getFreelancerID())) {
                // we can not use
                tmpBlacklist.add(best);
            } else {
                // potential
                tmp.add(best);
            }
        }

        // reinsert blacklist
        for (Freelancer f : tmpBlacklist) {
            q.insert(f);
        }

        // if no available return  an empty arraylist
        if (tmp.isEmpty()) {
            return tmp;
        }

        // reinsert tmp
        for (int i = 0; i < tmp.size(); i++) {
            q.insert(tmp.get(i));
        }

        return tmp; //return the arraylist
    }


    public ArrayList<Freelancer> request_job(String customerID, String serviceName, int topK) {
        Customer customer = hashC.getCustomer(customerID);
        if (customer == null || !(Freelancer.services.contains(serviceName))) {
            return null;
        }
        PriorityQueue q = getQueueFor(serviceName);
        ArrayList<Freelancer> topKForCustomer = getTopKForCustomer(customer, q, topK);
        if (topKForCustomer.isEmpty()) {
            return topKForCustomer;
        }
        else {
            employFreelancer(customerID,topKForCustomer.get(0).getFreelancerID());
            return topKForCustomer;
        }
    }

    public void remove_from_heap(Freelancer freeL){
        int ind=freeL.index;
        switch (freeL.getServiceType()){
            case "paint":
                queue_paint.deleteSpesific(ind);
                break;
            case "web_dev":
                queue_web_dev.deleteSpesific(ind);
                break;
            case "graphic_design":
                graphic_design.deleteSpesific(ind);
                break;
            case "data_entry":
                data_entry.deleteSpesific(ind);
                break;
            case "tutoring":
                tutoring.deleteSpesific(ind);
                break;
            case "cleaning":
                cleaning.deleteSpesific(ind);
                break;
            case "writing":
                writing.deleteSpesific(ind);
                break;
            case "photography":
                photography.deleteSpesific(ind);
                break;
            case "plumbing":
                plumbing.deleteSpesific(ind);
                break;
            case "electrical":
                electrical.deleteSpesific(ind);
                break;
        }
    }

    public Freelancer update_skill(String freelancerID, int T,int C,int R,int E,int A){
        Freelancer freelancer=hashF.getFreelancer(freelancerID);
        if (freelancer==null||T<0||T>100||C<0||C>100||R<0|R>100||E<0||E>100||A<0||A>100) {
            return null;}
        remove_from_heap(freelancer);
        freelancer.setT(T);
        freelancer.setA(A);
        freelancer.setC(C);
        freelancer.setR(R);
        freelancer.setE(E);
        insert_heap(freelancer);
        return freelancer;
    }
    //public Freelancer findkMax




    }

//    update_skill



