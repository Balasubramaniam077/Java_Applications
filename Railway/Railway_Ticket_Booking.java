import java.util.*;
class train_booking {
    private static final String train_name = "Chennai Super Fast Express";
    private static final String train_num = "ECE0710";
    private static final String train_stops[] = {"Coimbatore","Tirupur","Erode","Salem","Kanchipuram","Chennai"};
    private static int user_id =100;
    private static int index = 0 ,tic_no=50,can_ind=0;
    private static ArrayList <waiters> wait_app = new ArrayList<waiters>();
    private static ArrayList <users> user_list = new ArrayList<users>();
    private static ArrayList <waiters> book_list = new ArrayList<waiters>();
    private static int seats[][] = new int[3][6];
    private static Scanner scan;

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        userList();
        book_home();
    }

    private static void book_home() {
        System.out.println("\t\t\t\t--------------Welcome to Konkan Train Booking Application----------");
        System.out.println("1.Admin Login");
        System.out.println("2.User Login");
        System.out.println("3.Exit");
        int n = scan.nextInt();
        switch(n){
            case 1:
                admin();
                break;
            case 2:
                user();
                break;
            case 3:
                System.exit(0);
        }
    }

    private static void admin() {
        System.out.println("\t\t\t\t-----------Welcome Chennai central Admim-----------");
        System.out.print("Enter Admin Id:");
        int admin_id = scan.nextInt();
        System.out.print("Enter Admin Password:");
        int admin_pass = scan.nextInt();
        
        if(admin_id==1 && admin_pass==1234){
            System.out.println("Train Name : "+train_name);
            System.out.println("Train Number : "+train_num);
            System.out.printf("%-15s || %-15s || %-15s ||%-15s || %-15s || %-15s \n","Stop - 1","Stop - 2",
            "Stop - 3","Stop - 4","Stop - 5","Stop - 6");           
            System.out.printf("%-15s || %-15s || %-15s || %-15s|| %-15s || %-15s \n",train_stops[0],train_stops[1],
            train_stops[2],train_stops[3],train_stops[4],train_stops[5]);
            System.out.println("Booking Details for Chennai SuperFast Express");
            System.out.println();
            System.out.println("\t\t\t-------Seats Details-------");
            System.out.printf("  %-10s ||  %-10s || %-10s ||  %-10s || %-10s ||  %-10s ||\n",train_stops[0],
            train_stops[1],
            train_stops[2],train_stops[3],train_stops[4],train_stops[5]);
            for(int l=0;l<3;l++){
                for(int h=0;h<6;h++){
                    // System.out.print(seats[l][h]+" ");
                    System.out.printf("  %-13s",seats[l][h]);
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
            System.out.println("\t\t\t---------Booking List---------");
            System.out.printf("%-20s || %-20s || %-20s || %-20s || \n","User _Id","Departure Station","Arrival Station","Ticket Number");
            for(int i=0;i<book_list.size();i++){
                System.out.printf("%-20s || %-20s || %-20s || %-20s || \n",book_list.get(i).user_id,train_stops[book_list.get(i).stop],
                train_stops[book_list.get(i).arr],book_list.get(i).tic_no);
            }
            System.out.println();
            System.out.println();
            System.out.println("\t\t\t---------Waiting List---------");
            System.out.printf("%-20s || %-20s || %-20s || %-20s || \n","User _Id","Departure Station","Arrival Station","Ticket Number");
            for(int i=0;i<wait_app.size();i++){
                System.out.printf("%-20s || %-20s || %-20s || %-20s || \n",wait_app.get(i).user_id,train_stops[wait_app.get(i).stop],
                train_stops[wait_app.get(i).arr],wait_app.get(i).tic_no);
            }
            System.out.println("\t\t\tPress enter to continue...");
            scan.nextLine();
            String s = scan.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            book_home();
        }

        else{
            System.out.println("Invalid Admin Credentials... please try again...:)");
            admin();
        }
    }

    private static void user() {
        System.out.println("\t\t\t\t--------Welcome Users--------");
        System.out.println("1.Log In");
        System.out.println("2.Sign up");
        System.out.println("3.Exit");
        System.out.print("Enter your choice:");
        int m =scan.nextInt();
        switch (m) {
            case 1:
                user_login();
                break;
            case 2:
                user_signup();
                break;
            case 3:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                book_home();
            default:
                book_home();
                break;
        }
    }

    private static void user_signup() {
        System.out.print("Enter User Name:");
        String u_name = scan.next();
        System.out.print("Enter User Password:");
        String u_pass=scan.next();
        System.out.print("Enter User Contact Number:");
        String u_contact=scan.next();
        System.out.print("Enter user inital Wallet Amount:");
        double u_amt = scan.nextDouble();
        user_list.add(new users(u_name,u_pass,u_contact,u_amt,user_id));
        System.out.println("Your user Id is "+user_id);
        System.out.println("Remember for log in credencials!!!");
        user_id++;
        System.out.println("User Created Successully....:)");
        System.out.println("\t\t\tPress enter to continue...");
        scan.nextLine();
        String s = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        user();
    }

    
    private static void user_login() {
        System.out.print("Enter User Id:");
        int u_id=scan.nextInt();
        System.out.print("Enter User Password:");
        String u_pass = scan.next();
        if(check(user_list,u_id,u_pass)){
            orgUser();
        }
        else{
            System.out.println("Please Enter valid user credencials....:(");
            System.out.println("\t\t\tPress enter to continue...");
            scan.nextLine();
            String s = scan.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            user_login();
        }
    }
    
    private static boolean check(ArrayList<users> user_list2, int u_id, String u_pass) {
        for(int i=0;i<user_list.size();i++){
            if(user_list.get(i).user_id==u_id && user_list.get(i).u_pass.equals(u_pass)){
                index = i;
                return true;
            }
        }
        return false;
    }

    private static void  orgUser(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Welcome "+user_list.get(index).u_name+" User...");
        System.out.println("1.Book Ticket");
        System.out.println("2.Booking Details && Cancel Ticket");
        System.out.println("3.View Train Details");
        System.out.println("4.IRTC Wallet");
        System.out.println("5.Exit");
        System.out.println("Enter Your Choice");
        int opt = scan.nextInt();
        switch (opt) {
            case 1:
                book_ticket();
                break;
            case 2:
                cancel_ticket();
                break;
            case 3:
                train_details();
                break;
            case 4:
                wallet();
                break;
            case 5:
                user();
            default:
                break;
            }
        }

    private static void book_ticket() {
        System.out.print("Enter the Number of Bookings : ");
        int no = scan.nextInt();
        for(int i=0;i<no;i++){
            int stop=0,arr=0;
            int check =0;
            System.out.print("Enter the Departure station:");
            String dep_st = scan.next();
            System.out.print("Enter the Arraival station : ");
            String arr_st = scan.next();
            for(int j=0;j<6;j++){
                if(train_stops[j].equals(dep_st)){
                    stop=j;
                    check++;
                }
                if(train_stops[j].equals(arr_st)){
                    arr=j;
                    check++;
                }
            }
            if(check==2){
                confirm_seat(no,stop,arr);
            }
            else{
                System.out.println("Please enter valid Stations....");
            }
            
        }
        System.out.println("\t\t\tPress enter to continue...");
        scan.nextLine();
        String s = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgUser();

    }
    private static void confirm_seat(int no,int stop, int arr) {
        int count=0;
        boolean flag=true;        
        lb1: for(int j=0;j<3;j++){
                int sum=0;
                lab2:for(int k=stop;k<arr;k++){
                        sum+=seats[j][k];
                    }
                    if(sum==0){
                        double curr_amt = (arr-stop)*50;
                        user_list.get(index).u_amt-=curr_amt;

                        System.out.println(" Seat Allowcated for User "+user_list.get(index).u_name + " Your Seat Number is "+j +
                        " Your Ticket Number is:"+tic_no );

                        System.out.println("Amount for this ticket is "+curr_amt +" Deducted from Konkan Wallet");
                        flag=false;                
                        for(int i=stop;i<arr;i++)
                        {
                            seats[j][i]=tic_no;
                        }
                        book_list.add(new waiters(user_list.get(index).user_id, stop, arr, tic_no));
                        tic_no++;
                        System.out.printf("  %-15s||   %-15s ||  %-15s ||  %-15s||  %-15s ||    %-15s ||\n",train_stops[0],
                        train_stops[1],
                        train_stops[2],train_stops[3],train_stops[4],train_stops[5]);
                        for(int l=0;l<3;l++){
                            for(int h=0;h<6;h++){
                                // System.out.print(seats[l][h]+" ");
                                System.out.printf(" %-20s",seats[l][h]);
                            }
                            System.out.println();
                        }                        
                        break lb1;
                    }          
        }
        if(flag==true){
            int in_it=0;
            if(wait_app.size()<=5){            
                wait_app.add(new waiters(user_list.get(index).user_id,stop,arr,0));
                System.out.println("You are in Waiting List....");
            }
            else{
                System.out.println("Sorry No Tickets Available...:(");
            }
        }
        count++;
        if(count==no){
            System.out.println("\t\t\tPress enter to continue...");
            scan.nextLine();
            String s = scan.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            orgUser();
        }        
    }


    private static void wallet() {
        System.out.println("Your Konkan Wallet is "+user_list.get(index).u_amt);
        System.out.println("\t\t\tPress enter to continue...");
        scan.nextLine();
        String s = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgUser();
    }

    private static void train_details() {
        System.out.println("Available Train : "+train_name);
        System.out.println("Train Number : "+train_num);
        System.out.printf("%-15s || %-15s || %-15s ||%-15s || %-15s || %-15s \n","Stop - 1","Stop - 2",
        "Stop - 3","Stop - 4","Stop - 5","Stop - 6");           
        System.out.printf("%-15s || %-15s || %-15s || %-15s|| %-15s || %-15s \n",train_stops[0],train_stops[1],
        train_stops[2],train_stops[3],train_stops[4],train_stops[5]);
        System.out.println("\t\t\tPress enter to continue...");
        scan.nextLine();
        String s = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgUser();
    }

    private static void cancel_ticket() {
        int ch=0,cancel_count=0;
        System.out.println("\t\t\t-----------Your booking History-----------");
        System.out.printf("%-20s || %-20s || %-20s || %-20s || \n","User _Id","Departure Station","Arrival Station","Ticket Number");
            
        for(int i=0;i<book_list.size();i++){
            if(user_list.get(index).user_id==book_list.get(i).user_id){
                System.out.printf("%-20s || %-20s || %-20s || %-20s || \n",book_list.get(i).user_id,train_stops[book_list.get(i).stop],
                train_stops[book_list.get(i).arr],book_list.get(i).tic_no);
                ch=1;
            }
        }
        if(ch==0){
            System.out.println("-------No Booking History Available...-------");
        }

        System.out.println("Press 1 to cancel Ticket or press any key to back..... ");   
        String s = scan.next();
        if(s.equals("1")){
            System.out.print("Enter the Ticket Number to be Cancel : ");
            int temp_tic=scan.nextInt();
            for(int i=0;i<book_list.size();i++){
                if(book_list.get(i).user_id==user_list.get(index).user_id && book_list.get(i).tic_no==temp_tic){
                    can_ind=i;
                    cancel(temp_tic);
                    cancel_count++;
                }
            }
            if(cancel_count==0){
                System.out.println("Enter Valid Ticket Number...");
                System.out.print("\033[H\033[2J");
                System.out.flush();
                orgUser();
            }
            System.out.print("\033[H\033[2J");
                System.out.flush();
                orgUser();
        }

        else{
            System.out.println("Press Enter to back ");
            scan.nextLine();
            String a=scan.nextLine();
            System.out.print("\033[H\033[2J");
                System.out.flush();
                orgUser();
        }
    }        

    private static void cancel(int temp_tic) {
        for(int i=0;i<3;i++){
            for(int j=0;j<6;j++){
                if(seats[i][j]==temp_tic){
                    seats[i][j]=0;
                }
            }
        }
        System.out.println("Ticket cancelled Succesfully...Refund will made Soon.");
        int temp_ind=0;
    lab3:for(int i=0;i<wait_app.size();i++){
            if(book_list.get(can_ind).stop <=wait_app.get(i).stop && book_list.get(can_ind).arr >=wait_app.get(i).arr ){
                lb1: for(int j=0;j<3;j++){                    
                    int sum=0;    
                    lab2:for(int k=wait_app.get(i).stop;k<wait_app.get(i).arr;k++){
                            sum+=seats[j][k];
                        }
                        if(sum==0){
                            int add_ind=0;
                            double amt=0;
                            tic_no++;
                            book_list.add(new waiters(wait_app.get(i).user_id, wait_app.get(i).stop,wait_app.get(i).arr, tic_no));
                            amt=(wait_app.get(i).arr-wait_app.get(i).stop)*50;

                            for(int m=0;m<user_list.size();m++){
                                if(user_list.get(m).user_id==wait_app.get(i).user_id){
                                    add_ind=m;
                                }
                            }
                            user_list.get(add_ind).u_amt-=amt;

                            for(int l=wait_app.get(i).stop;l<wait_app.get(i).arr;l++){
                                seats[j][l]=tic_no;
                            }                           
                            // temp_ind=i;
                            book_list.remove(can_ind);
                            wait_app.remove(i);
                            break lab3;
                        }          
                    }
                }
            }
        
        scan.nextLine();
        String s12 = scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        orgUser();
    }

    private static void userList(){
        user_list.add(new users("Bala", "1234", "9876543210",10000, 93));
        user_list.add(new users("Chandhru", "5678", "9123456780",9000, 92));
        user_list.add(new users("Aravind", "9012", "8901234567",3000, 90));
    }

}

class users extends train_booking{
    String u_name,u_pass,u_contact;
    double u_amt;
    int user_id;
    users(String u_name,String u_pass,String u_contact,double u_amt,int user_id){
        this.u_name=u_name;
        this.u_pass=u_pass;
        this.u_contact=u_contact;
        this.u_amt=u_amt;
        this.user_id=user_id;
    }
}

class waiters extends train_booking{
    int stop ,arr,user_id,tic_no;
    waiters(int user_id,int stop,int arr,int tic_no){
        this.user_id=user_id;
        this.stop=stop;
        this.arr=arr;
        this.tic_no=tic_no;
    }
}