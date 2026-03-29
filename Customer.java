import java.util.ArrayList;

public class Customer {
    private String ID;
    String loyaltyTier;
    private int totalSpent;
    private double subsidy;
    private int cancelCustCount;
    private int totalCustCount;
    private int employmentCustCount;
    private int freelancerCustCount;
    ArrayList<String> blockedList;
    ArrayList<String> currentEmployees;
    Customer(String ID){
        this.ID=ID;
        this.subsidy=0;
        this.loyaltyTier="BRONZE";
        this.totalSpent=0;
        this.cancelCustCount=0;
        this.totalCustCount=0;
        this.blockedList=new ArrayList<>();
        this.currentEmployees=new ArrayList<>();
        this.freelancerCustCount=0;
    }
    public String getID() {
        return ID;}

    public int getFreelancerCustCount() {
        return freelancerCustCount;
    }

    public int getEmploymentCustCount() {
        return employmentCustCount;
    }

    public int getCancelCustCount() {
        return cancelCustCount;
    }

    public double getSubsidy() {
        return subsidy;
    }

    public int getTotalValidCount() {
        return totalSpent-(250*cancelCustCount);
    }

    public void incrementTotalCustCount(){
        this.totalCustCount+=1;}


    public void incrementCancelCustCount(){
        this.cancelCustCount+=1;}


    public void incrementEmploymentCustCount() {
        this.employmentCustCount+=1;
    }


    public int getTotalCustCount() {
        return totalCustCount;
    }

    public int getTotalSpent() {
        return totalSpent;}

    public String getLoyaltyTier() {
        return loyaltyTier;
    }


    public void setCancelCustCount(int cancelCustCount) {
        this.cancelCustCount = cancelCustCount;
    }

    public void setTotalSpent(int totalSpent) {
        this.totalSpent = totalSpent;
    }

    public void setLoyaltyTier(String loyaltyTier) {
        this.loyaltyTier = loyaltyTier;
    }

    public void setTotalCustCount(int totalCustCount) {
        this.totalCustCount = totalCustCount;
    }
    public String decideTier(int validCount){
        if (validCount<500){return "BRONZE";}
        else if (500<=validCount&&validCount<2000){return "SILVER";}
        else if (2000<=validCount&&validCount<5000){return "GOLD";}
        else if (validCount>=5000){return "PLATINUM";}
        else{return null;}
    }

    public void setSubsidy(String loyaltyTier) {
        if (loyaltyTier.equals("BRONZE")){this.subsidy = 0;}
        else if (loyaltyTier.equals("SILVER")){this.subsidy = 0.05;}
        else if (loyaltyTier.equals("GOLD")){this.subsidy = 0.10;}
        else if(loyaltyTier.equals("PLATINUM")){this.subsidy = 0.15;}
    }
}
