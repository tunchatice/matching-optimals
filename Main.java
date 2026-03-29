import java.io.*;
import java.util.Locale;
import java.util.Locale;
import java.util.ArrayList;

/**
 * Main entry point for GigMatch Pro platform.
 */
public class Main {
    static SystemManage  system = new SystemManage();
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        if (args.length != 2) {
            System.err.println("Usage: java Main <input_file> <output_file>");
            System.exit(1);
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                processCommand(line, writer);
            }

        } catch (IOException e) {
            System.err.println("Error reading/writing files: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void processCommand(String command, BufferedWriter writer)
            throws IOException {

        String[] parts = command.split("\\s+");
        String operation = parts[0];

        try {
            String result = "";

            switch (operation) {
                case "register_customer":
                    Customer c=system.register_customer(parts[1]);
                    if(c==null){
                        result+="Some error occurred in register_customer.";
                    }
                    else{
                        result+="registered customer "+c.getID();
                    }
                    // Format: register_customer customerID
                    break;

                case "register_freelancer":
                    // Format: register_freelancer freelancerID serviceName basePrice T C R E A
                    Freelancer f=system.register_freelancer(parts[1],parts[2],Integer.parseInt(parts[3]),Integer.parseInt(parts[4]),Integer.parseInt(parts[5]),Integer.parseInt(parts[6]),Integer.parseInt(parts[7]),Integer.parseInt(parts[8]));
                    if(f==null){
                        result+="Some error occurred in register_freelancer.";
                    }
                    else{
                        result+="registered freelancer "+f.getFreelancerID();
                    }
                    break;

                case "request_job":
                    // Format: request_job customerID serviceName topK
                    ArrayList<Freelancer> resultlst=system.request_job(parts[1],parts[2],Integer.parseInt(parts[3]));
                    if (resultlst==null){
                        result+="Some error occurred in request_job.";}
                    else if (resultlst.size()==0){
                        result+="no freelancers available";
                    }
                    else{
                        result+="available freelancers for " +parts[2]+ " (top " +parts[3]+"):";
                        for (int i=0; i<resultlst.size();i++){
                            Freelancer a=resultlst.get(i);
                            result+="\n"+resultlst.get(i).getFreelancerID()+" - composite: "+a.compositeScore()+", price: "+a.getPrice()+", rating: "+String.format("%.1f", a.getAverageRating());
                        }
                        result+="\nauto-employed best freelancer: " +resultlst.get(0).getFreelancerID()+ " for customer "+ parts[1];
                    }
                    break;

                case "employ_freelancer":
                    // Format: employ_freelancer customerID freelancerID
                    boolean employ= system.employFreelancer(parts[1],parts[2]);
                    if (employ){
                        result+=parts[1]+" employed "+parts[2]+" for "+system.hashF.getFreelancer(parts[2]).getServiceType();
                    }
                    else{
                        result+="Some error occurred in employ.";}
                    break;

                case "complete_and_rate":
                    Customer customer1=system.completeANdRate(parts[1],Integer.parseInt(parts[2]));
                    if(customer1==null){
                        result+="Some error occurred in complete_and_rate.";
                    }
                    else{
                        result+=parts[1]+" completed job for "+customer1.getID()+" with rating "+parts[2];
                    }
                    // Format: complete_and_rate freelancerID rating
                    break;

                case "cancel_by_freelancer":
                    // Format: cancel_by_freelancer freelancerID
                    String customer2= system.cancel_by_freelancer(parts[1]);
                    if (customer2==null){
                        result+="Some error occurred in cancel_by_freelancer.";
                    }
                    else{
                        result+="cancelled by freelancer: "+parts[1]+" cancelled "+customer2;
                        if(system.hashF.getFreelancer(parts[1]).isPermanentlyBanned()){
                            result+="\n";
                            result+= "platform banned freelancer: "+ parts[1];}
                    }


                    break;

                case "cancel_by_customer":
                    // Format: cancel_by_customer customerID freelancerID
                    boolean cancelCus= system.cancel_by_customer(parts[1],parts[2]);
                    if(!cancelCus){
                        result+="Some error occurred in cancel_by_customer.";
                    }
                    else{
                        result+="cancelled by customer: "+parts[1] +" cancelled "+parts[2];}
                    break;

                case "blacklist":
                    boolean blacklist_result=system.blacklist(parts[1],parts[2]);
                    if(!blacklist_result){
                        result+="Some error occurred in blacklist.";}
                    else{
                        result+=parts[1]+" blacklisted "+parts[2];
                    }
                    // Format: blacklist customerID freelancerID
                    break;

                case "unblacklist":
                    // Format: unblacklist customerID freelancerID
                    boolean unblacklist_result=system.unblacklist(parts[1],parts[2]);
                    if(!unblacklist_result){
                        result+="Some error occurred in unblacklist.";}
                    else{
                        result+=parts[1]+" unblacklisted "+parts[2];
                    }
                    break;

                case "change_service":
                    Freelancer n=system.change_service(parts[1],parts[2],Integer.parseInt(parts[3]));
                    if(n==null){
                        result+="Some error occurred in change_service.";
                    }
                    else{
                        result+="service change for "+ n.getFreelancerID()+" queued from "+n.getServiceType()+" to "+n.getPendingServiceType();
                    }
                    // Format: change_service freelancerID newService newPrice
                    break;

                case "simulate_month":
                    result+= system.simulate_month();
                    // Format: simulate_month
                    break;

                case "query_freelancer":
                    // Format: query_freelancer freelancerID
                    Freelancer freelancer=system.query_freelancer(parts[1]);
                    if(freelancer==null){
                        result+="Some error occurred in query_freelancer.";
                    }
                    else{
                        String available;
                        if(freelancer.isAvailable()){available="yes";}
                        else{available="no";}

                        String burnout;
                        if(freelancer.isBurnout()){burnout="yes";}
                        else{burnout="no";}

                        result+=freelancer.getFreelancerID()+": "+freelancer.getServiceType()+", "  +"price: "+freelancer.getPrice()+", "+
                                "rating: "+String.format("%.1f", freelancer.getAverageRating())+", " +"completed: "+(freelancer.getCompletedCount()-1)+", " +
                                "cancelled: "+freelancer.getCancelCount()+", "+
                                "skills: (" +freelancer.getT()+","+freelancer.getC()+","+freelancer.getR()+","+freelancer.getE()+","+freelancer.getA()+"), "+"available: "+available+", "+"burnout: "+burnout;
                    }
                    break;

                case "query_customer":
                    // Format: query_customer customerID
                    Customer customer = system.query_customer(parts[1]);
                    if (customer == null) {
                        result += "Some error occurred in query_customer.";
                    } else {

                        // loyalty tier string
                        String tier = customer.getLoyaltyTier();
                        // "BRONZE", "SILVER", "GOLD", "PLATINUM"
                        if (customer.getID().equals("cust880")){
                            System.out.println(customer.getCancelCustCount()+" "+ customer.getTotalCustCount()+" "+customer.getTotalSpent()+" "+ customer.getTotalValidCount()+" "+customer.decideTier(customer.getTotalValidCount()));
                        }
                        int totalSpent = customer.getTotalSpent();
                        int blacklistedCount = customer.blockedList.size();
                        int totalEmploymentCount = customer.getEmploymentCustCount();

                        result +=
                                customer.getID() + ": " +
                                        "total spent: $" + totalSpent + ", " +
                                        "loyalty tier: " + tier + ", " +
                                        "blacklisted freelancer count: " + blacklistedCount + ", " +
                                        "total employment count: " + totalEmploymentCount;
                    }
                    break;

                case "update_skill":
                    // Format: update_skill freelancerID T C R E A
                    Freelancer l=system.update_skill(parts[1],Integer.parseInt(parts[2]),Integer.parseInt(parts[3]),Integer.parseInt(parts[4]),Integer.parseInt(parts[5]),Integer.parseInt(parts[6]));
                    if(l==null){
                        result+="Some error occurred in update_skill.";
                    }
                    else{
                        result+="updated skills of "+l.getFreelancerID()+" for "+l.getServiceType();
                    }
                    break;

                default:
                    result = "Unknown command: " + operation;
            }

            writer.write(result);
            writer.newLine();

        } catch (Exception e) {
            writer.write("Error processing command: " + command);
            writer.newLine();
        }
    }
}
