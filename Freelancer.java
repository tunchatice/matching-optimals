import java.util.ArrayList;

public class Freelancer {
    private String freelancerID;
    private int cancelCount;
    private int completedCount;
    private int price;
    private String serviceType;
    private int T; private int C; private int R; private int E; private  int A;
    private boolean available;
    private boolean burnout;
    private int monthlyCanceled;
    private int monthlyCompleted;
    private double averageRating;
    private boolean pendingChange;
    private String pendingServiceType;
    private int pendingPrice;
    private boolean permanentlyBanned;
    private String currentCustomer;
    int index;
    static final ArrayList<String> services = new ArrayList<String>() {{
        add("paint");
        add("web_dev");
        add("graphic_design");
        add("data_entry");
        add("tutoring");
        add("cleaning");
        add("writing");
        add("photography");
        add("plumbing");
        add("electrical");
    }};
    Freelancer(String ID,String serviceName,int basePrice, int T,int C, int R, int E, int A){
        this.freelancerID=ID;
        this.available=true;
        this.price=basePrice;
        this.T=T;
        this.C=C;
        this.R=R;
        this.E=E;
        this.A=A;
        this.completedCount=1;
        this.averageRating=5.0;
        this.cancelCount=0;
        this.serviceType=serviceName;
        this.burnout=false;
        this.monthlyCanceled=0;
        this.monthlyCompleted=0;
        this.pendingChange=false;
        this.pendingServiceType=null;
        this.pendingPrice=0;
        this.permanentlyBanned=false;
        this.currentCustomer=null;
        this.index=-1;
    }


    public int getT() {
        return T;
    }

    public int getC() {
        return C;
    }
    public int getR() {
        return R;
    }

    public int getE() {
        return E;
    }


    public int getA() {
        return A;
    }

    public void setT(int t) {
        T = t;}

    public void setC(int c) {
        C = c;}

    public void setR(int r) {
        R = r;}

    public void setE(int e) {
        E = e;}

    public void setA(int a) {
        A = a;}

    public String getFreelancerID() {
        return freelancerID;}

    public int getCancelCount() {
        return cancelCount;
    }
    public int getCompletedCount() {
        return completedCount;
    }
    public int getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isBurnout() {
        return burnout;
    }

    public boolean isPendingChange() {
        return pendingChange;
    }

    public boolean isPermanentlyBanned() {
        return permanentlyBanned;
    }

    public String getCurrentCustomer() {
        return currentCustomer;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getMonthlyCanceled() {
        return monthlyCanceled;
    }

    public String getPendingServiceType() {
        return pendingServiceType;
    }

    public int getPendingPrice() {
        return pendingPrice;
    }

    public int getMonthlyCompleted() {
        return monthlyCompleted;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public void setBurnout(boolean burnout) {
        this.burnout = burnout;
    }

    public void incrementMonthlyCompleted() {
        this.monthlyCompleted += 1;
    }
    public void incrementMonthlyCanceled() {
        this.monthlyCanceled += 1;
    }

    public void incrementCompletedCount() {
        this.completedCount += 1;
    }

    public void incrementCancelCount() {
        this.cancelCount += 1;
    }

    public void setCurrentCustomer(String currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setMonthlyCanceled(int monthlyCanceled) {
        this.monthlyCanceled = monthlyCanceled;
    }

    public void setMonthlyCompleted(int monthlyCompleted) {
        this.monthlyCompleted = monthlyCompleted;}

    public void setPendingChange(boolean pendingChange) {
        this.pendingChange = pendingChange;
    }

    public void setPendingPrice(int pendingPrice) {
        this.pendingPrice = pendingPrice;
    }

    public void setPendingServiceType(String pendingServiceType) {
        this.pendingServiceType = pendingServiceType;
    }

    public void setPermanentlyBanned(boolean permanentlyBanned) {
        this.permanentlyBanned = permanentlyBanned;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int compositeScore( ) {
        int score;
        double ws=0.55; double wr=0.25; double wl=0.20;
        int ST; int SC; int SR; int SE; int SA;  int Si;
        int dotProduct;
        double burnoutPenalty;
        double skillScore;
        double ratingScore=averageRating/5.0;
        if(burnout){burnoutPenalty=0.45;}
        else{burnoutPenalty=0.0;}
        double reliabilityScore;
        if (completedCount-1+cancelCount==0){
            reliabilityScore=1.0;}
        else{
            reliabilityScore=1.0-((double) cancelCount/ (completedCount-1+cancelCount));
        }

        if(serviceType.equals("paint")){
             ST = 70;  SC = 60;  SR = 50;  SE = 85; SA = 90;  Si=ST+SC+SR+SE+SA;
             dotProduct = (T*ST)+(C*SC)+(R*SR)+(E*SE)+(A*SA);
        }
        else if(serviceType.equals("web_dev")){
            ST = 95; SC = 75; SR = 85; SE = 80; SA = 90; Si=ST+SC+SR+SE+SA;
            dotProduct = (T*ST)+(C*SC)+(R*SR)+(E*SE)+(A*SA);
        }
        else if(serviceType.equals("graphic_design")){
            ST = 75;  SC =85 ;  SR =95 ;  SE =70; SA =85;  Si=ST+SC+SR+SE+SA;
            dotProduct = (T*ST)+(C*SC)+(R*SR)+(E*SE)+(A*SA);
            ;
        }
        else if (serviceType.equals("data_entry")){
            ST = 50;  SC =50 ;  SR =30 ;  SE =95 ; SA = 95;  Si=ST+SC+SR+SE+SA;
            dotProduct = (T*ST)+(C*SC)+(R*SR)+(E*SE)+(A*SA);
        }
        else if(serviceType.equals("tutoring")){
            ST = 80;  SC =95 ;  SR =70 ;  SE =90 ; SA =75 ; Si=ST+SC+SR+SE+SA;
            dotProduct = (T*ST)+(C*SC)+(R*SR)+(E*SE)+(A*SA);
        }
        else if(serviceType.equals("cleaning")){
            ST = 40;  SC =60 ;  SR =40 ;  SE = 90; SA =85 ;  Si=ST+SC+SR+SE+SA;
            dotProduct = (T*ST)+(C*SC)+(R*SR)+(E*SE)+(A*SA);
        }
        else if(serviceType.equals("writing")){
            ST = 70;  SC =85 ;  SR =90 ;  SE =80 ; SA =95 ;  Si=ST+SC+SR+SE+SA;
            dotProduct = (T*ST)+(C*SC)+(R*SR)+(E*SE)+(A*SA);
        }
        else if(serviceType.equals("photography")){
            ST = 85;  SC = 80;  SR = 90;  SE =75 ; SA =90 ;  Si=ST+SC+SR+SE+SA;
            dotProduct = (T*ST)+(C*SC)+(R*SR)+(E*SE)+(A*SA);
        }
        else if(serviceType.equals("plumbing")){
            ST =85 ;  SC =65 ;  SR =60 ;  SE = 90; SA =85 ;  Si=ST+SC+SR+SE+SA;
            dotProduct = (T*ST)+(C*SC)+(R*SR)+(E*SE)+(A*SA);
        }
        else {
            ST =90 ;  SC =65 ;  SR =70 ;  SE =95 ; SA =95 ;  Si=ST+SC+SR+SE+SA;
            dotProduct = (T*ST)+(C*SC)+(R*SR)+(E*SE)+(A*SA);
        }
        skillScore=dotProduct/((double) 100*Si);
        score= (int) Math.floor(10000*(ws*skillScore+wr*ratingScore+wl*reliabilityScore-burnoutPenalty));
        return score;
    }


}
