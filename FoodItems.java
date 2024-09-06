import java.sql.*;
import java.util.Scanner;


public class FoodItems {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food","root","Sai@01Mysql");
		Scanner sc = new Scanner(System.in);
        int choice;
        do {
        	  
        	System.out.println("\nSelect an option:\n1. Create Cuisin\n2. Create Food Item \n3. View All Cuisins \n4. View All Food Items \n5. Update Cuisin \n6. Update Food Item \n7. Delete Cuisin \n8. Delete Food Item \n9. Display Food Items by Cuisin Name \n10. Exit");  
        	System.out.print("Enter your choice: ");
            choice = sc.nextInt(); 
            
            switch (choice) {
            case 1:
                createCuisine(con, sc);
                break;
            case 2:
                createFoodItem(con, sc);
                break;
            case 3:
                readCuisines(con);
                break;
            case 4:
                readFoodItems(con);
                break;
            case 5:
                updateCuisine(con, sc);
                break;
            case 6:
                updateFoodItem(con, sc);
                break;
            case 7:
                deleteCuisine(con, sc);
                break;
            case 8:
                deleteFoodItem(con, sc);
                break;
            case 9:
                displayFoodItemsByCuisinName(con, sc); 
                break;
            case 10:
            	dbConClose(con);
                System.out.println("Exit");
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
        }
        while(choice != 10);
        
        
        
	}
	
	 public static void createCuisine(Connection con, Scanner sc) throws SQLException {
	        System.out.print("Enter Cuisin ID: ");
	        int cuisineId = sc.nextInt();
	        System.out.print("Enter Cuisin Name: ");
	        sc.nextLine(); 
	        String cuisineName = sc.nextLine();
	        System.out.print("Enter Country: ");
	        String country = sc.nextLine();

	        String query = "INSERT INTO cuisins (cuisin_id, cuisin_name, country) VALUES (?, ?, ?)";
	         PreparedStatement stmt = con.prepareStatement(query); 
	         stmt.setInt(1, cuisineId);
	         stmt.setString(2, cuisineName);
	         stmt.setString(3, country);
	         stmt.executeUpdate();
	         System.out.println("Cuisine inserted successfully.");
	        
	    }
	 
	 public static void createFoodItem(Connection con, Scanner sc) throws SQLException {
	        System.out.print("Enter Food ID: ");
	        int foodId = sc.nextInt();
	        System.out.print("Enter Food Name: ");
	        sc.nextLine(); 
	        String foodName = sc.nextLine();
	        System.out.print("Enter Food Type (Vegetarian/Non-Vegetarian): ");
	        String foodType = sc.nextLine();
	        System.out.print("Enter Cuisine ID for the Food: ");
	        int cuisineId = sc.nextInt();

	        String query = "INSERT INTO food_items (food_id, food_name, food_type, cuisin_id) VALUES (?, ?, ?, ?)";
	        PreparedStatement stmt = con.prepareStatement(query); 
	        stmt.setInt(1, foodId);
	        stmt.setString(2, foodName);
	        stmt.setString(3, foodType);
	        stmt.setInt(4, cuisineId);
	        stmt.executeUpdate();
	        System.out.println("Food item inserted successfully.");
	        
	    }
	 
	 public static void readCuisines(Connection con) throws SQLException {
	        String query = "SELECT * FROM cuisins";
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query); 
	        System.out.println("Cuisins:");
	        while (rs.next()) {
	             System.out.println("Cuisin ID: " + rs.getInt("cuisin_id") + ", Name: " + rs.getString("cuisin_name") + ", Country: " + rs.getString("country"));
	        }
	        
	    }
	 
	 public static void readFoodItems(Connection con) throws SQLException {
	        String query = "SELECT * FROM food_items";
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query); 
	        System.out.println("Food Items:");
	        while (rs.next()) {
	             System.out.println("Food ID: " + rs.getInt("food_id") + ", Name: " + rs.getString("food_name") + ", Type: " + rs.getString("food_type") + ", Cuisin ID: " + rs.getInt("cuisin_id"));
	        }
	        
	    }
	 
	  public static void updateCuisine(Connection con, Scanner sc) throws SQLException {
	        System.out.print("Enter Cuisin ID to update: ");
	        int cuisinId = sc.nextInt();
	        System.out.print("Enter New Country: ");
	        sc.nextLine(); 
	        String newCountry = sc.nextLine();

	        String query = "UPDATE cuisins SET country = ? WHERE cuisin_id = ?";
	        PreparedStatement stmt = con.prepareStatement(query); 
	            stmt.setString(1, newCountry);
	            stmt.setInt(2, cuisinId);
	            int rowsAffected = stmt.executeUpdate();
	            System.out.println("Updated cuisine country for ID " + cuisinId);
	        
	    }
	  
	  public static void updateFoodItem(Connection con, Scanner sc) throws SQLException {
	        System.out.print("Enter Food ID to update: ");
	        int foodId = sc.nextInt();
	        System.out.print("Enter New Food Type (Vegetarian/Non-Vegetarian): ");
	        sc.nextLine(); 
	        String newFoodType = sc.nextLine();

	        String query = "UPDATE food_items SET food_type = ? WHERE food_id = ?";
	        PreparedStatement stmt = con.prepareStatement(query); 
	            stmt.setString(1, newFoodType);
	            stmt.setInt(2, foodId);
	            int rowsAffected = stmt.executeUpdate();
	            System.out.println("Updated food type for ID " + foodId);
	        
	    }
	  
	  public static void deleteFoodItem(Connection con, Scanner sc) throws SQLException {
	        System.out.print("Enter Food ID to delete: ");
	        int foodId = sc.nextInt();

	        String query = "DELETE FROM food_items WHERE food_id = ?";
	        PreparedStatement stmt = con.prepareStatement(query); 
	            stmt.setInt(1, foodId);
	            int rowsAffected = stmt.executeUpdate();
	            System.out.println("Deleted food item with ID " + foodId);
	        
	    }
	  
	  public static void deleteCuisine(Connection con, Scanner sc) throws SQLException {
	        System.out.print("Enter Cuisine ID to delete: ");
	        int cuisinId = sc.nextInt();

	        String query = "DELETE FROM cuisins WHERE cuisin_id = ?";
	        PreparedStatement stmt = con.prepareStatement(query); 
	            stmt.setInt(1, cuisinId);
	            int rowsAffected = stmt.executeUpdate();
	            System.out.println("Deleted cuisin with ID " + cuisinId);
	        
	    }
	  
	
	  public static void displayFoodItemsByCuisinName(Connection con, Scanner sc) throws SQLException {
	      System.out.print("Enter Cuisin Name to view food items: ");
	      sc.nextLine(); 
	      String cuisineName = sc.nextLine();

	      String query = "SELECT fi.food_id, fi.food_name, fi.food_type FROM food_items fi JOIN cuisins c ON fi.cuisin_id = c.cuisin_id WHERE c.cuisin_name = ?";
	      
	      PreparedStatement stmt = con.prepareStatement(query); 
	          stmt.setString(1, cuisineName);
	          ResultSet rs = stmt.executeQuery();

	          System.out.println("Food Items for Cuisine '" + cuisineName + "':");
	          boolean hasResults = false;
	          while (rs.next()) {
	              hasResults = true;
	              System.out.println("Food ID: " + rs.getInt("food_id") + ", Name: " + rs.getString("food_name") + ", Type: " + rs.getString("food_type"));
	          }

	          if (!hasResults) {
	              System.out.println("No food items found for the given cuisin name.");
	          }
	      
	  }

	  
	  public static void dbConClose(Connection con)throws SQLException {
		  
		  con.close();
	  }

}
