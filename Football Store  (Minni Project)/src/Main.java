import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//public class Main {
//    public static void main(String[] args) {
//        String[] productName = {"👟 Shoes", "⚽ Football", "🧦 Socks", "👕 Jersey", "🦿 Guards", "🎒 Kit"};
//        int []prices = {2400,550,120,1200,450,900};
//        int discount = 37;
//        System.out.println("\uD83D\uDECD\uFE0F Below is the item list: ");
//
//        for(int i=0; i<prices.length; i++){
//            System.out.println((i+1)+")"+productName[i]+": ₹"+prices[i]);
//        }
//
//        System.out.print("\uD83D\uDD22 Enter the product number here: ");
//        Scanner sc = new Scanner(System.in);
//        int productNumber = sc.nextInt();
//        System.out.println("✅ You selected "+productName[productNumber-1]+" having price: ₹"+prices[productNumber-1]);
//        System.out.println("\uD83C\uDF81 We also have a great discount is "+discount+"%");
//
//        int originalPrice = prices[productNumber - 1];
//        double discountAmount = originalPrice * discount / 100.0;
//        double finalPrice = originalPrice - discountAmount;
//
//        System.out.println("\uD83E\uDDFE You save ₹"+(int)discountAmount);
//        System.out.println("\uD83D\uDCB0 Your bill is: ₹"+(int)finalPrice);
//
//        System.out.println("\uD83D\uDE4F Have a great day.");
//    }
//}







//import java.util.*;
//
//public class Main {
//    static String[] products = {"👟 Shoes", "⚽ Football", "🧦 Socks", "👕 Jersey", "🦿 Guards", "🎒 Kit"};
//    static int[] prices = {2400, 550, 120, 1200, 450, 900};
//    static int[] stock = {10, 10, 15, 8, 5, 6};
//    static List<String> cart = new ArrayList<>();
//    static List<Integer> cartQty = new ArrayList<>();
//    static List<Integer> cartPrice = new ArrayList<>();
//    static final int FLAT_DISCOUNT = 56;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("\n🛒 Welcome to the Football Store!\nProduct List:");
//            for (int i = 0; i < products.length; i++) {
//                System.out.println((i + 1) + ") " + products[i] + " - ₹" + prices[i] + " [Stock: " + stock[i] + "]");
////                System.out.printf("%d) %-10s ₹%-6d [Stock: %d]%n", i+1, products[i], prices[i], stock[i]);
//
//            }
//
//            System.out.print("\nEnter product number to add to cart (0 to checkout): ");
//            int choice = sc.nextInt();
//            if (choice == 0) break;
//            if (choice < 1 || choice > products.length || stock[choice - 1] == 0) {
//                System.out.println("⚠️ Invalid choice or out of stock!");
//                continue;
//            }
//
//            System.out.print("Enter quantity: ");
//            int qty = sc.nextInt();
//            if (qty <= 0 || qty > stock[choice - 1]) {
//                System.out.println("❌ Invalid quantity!");
//                continue;
//            }
//
//            cart.add(products[choice - 1]);
//            cartQty.add(qty);
//            cartPrice.add(prices[choice - 1]);
//            stock[choice - 1] -= qty;
//            System.out.println("✅ Added to cart!");
//        }
//
//        // Checkout
//        if (cart.isEmpty()) {
//            System.out.println("🚫 Cart is empty. Goodbye!");
//            return;
//        }
//
//        System.out.println("\n🧾 Order Receipt:");
//        System.out.println("---------------------------------------------");
//        double total = 0;
//        for (int i = 0; i < cart.size(); i++) {
//            int quantity = cartQty.get(i);
//            int unitPrice = cartPrice.get(i);
//            double lineTotal = quantity * unitPrice;
//            total += lineTotal;
//            System.out.printf("%-12s x %2d = ₹%.2f%n", cart.get(i), quantity, lineTotal);
//        }
//
//        System.out.println("---------------------------------------------");
//        System.out.printf("Total Before Discount: ₹%.2f%n", total);
//
//        double discountAmount = total * FLAT_DISCOUNT / 100.0;
//        double finalAmount = total - discountAmount;
//
//        System.out.printf("Discount Applied (%d%%): ₹%.2f%n", FLAT_DISCOUNT, discountAmount);
//        System.out.printf("💳 Final Amount: ₹%.2f%n", finalAmount);
//
//        // Payment simulation
//        System.out.println("\n💰 Choose Payment Method:");
//        System.out.println("1) UPI\n2) Credit/Debit Card\n3) Cash on Delivery");
//        int payment = sc.nextInt();
//        switch (payment) {
//            case 1 -> System.out.println("✅ Payment via UPI successful!");
//            case 2 -> System.out.println("✅ Card payment successful!");
//            case 3 -> System.out.println("🚚 Please keep exact change ready.");
//            default -> System.out.println("⚠️ Invalid payment option.");
//        }
//
//        System.out.println("\n🙏 Thank you for shopping with us!");
//    }
//}




import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class Main {
    static String[] products = {"👟 Shoes", "⚽ Football", "🧦 Socks", "👕 Jersey", "🦿 Guards", "🎒 Kit"};
    static String[] productDesc = {"Running shoes", "Standard football", "Cotton socks", "Team jersey", "Leg guards", "Full kit bag"};
    static int[] prices = {2400, 550, 120, 1200, 450, 900};
    static int[] stock = {10, 10, 15, 8, 5, 6};
    static Map<String, Integer> cart = new LinkedHashMap<>();
    static Map<String, Integer> cartQty = new LinkedHashMap<>();
    static final int DISCOUNT_PERCENT = 56;
    static final double GST_RATE = 0.18;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // User info
        System.out.print("\n👤 Enter your name: ");
        String userName = sc.nextLine();
        System.out.print("📧 Enter your email: ");
        String userEmail = sc.nextLine();

        while (true) {
            System.out.println("\n🛒 Welcome to the Football Store!\nProduct List:");
            for (int i = 0; i < products.length; i++) {
                System.out.printf("%d) %-10s - ₹%-6d [%s] [Stock: %d]%n", (i + 1), products[i], prices[i], productDesc[i], stock[i]);
            }

            System.out.print("\nEnter product number to add to cart (0 to checkout): ");
            int choice = sc.nextInt();
            if (choice == 0) break;
            if (choice < 1 || choice > products.length || stock[choice - 1] == 0) {
                System.out.println("⚠️ Invalid choice or out of stock!");
                continue;
            }

            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();
            if (qty <= 0 || qty > stock[choice - 1]) {
                System.out.println("❌ Invalid quantity!");
                continue;
            }

            String prod = products[choice - 1];
            cart.put(prod, prices[choice - 1]);
            cartQty.put(prod, cartQty.getOrDefault(prod, 0) + qty);
            stock[choice - 1] -= qty;
            System.out.println("✅ Added to cart!");
        }

        // Checkout
        if (cart.isEmpty()) {
            System.out.println("🚫 Cart is empty. Goodbye!");
            return;
        }

        // Timestamp
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Billing
        StringBuilder receipt = new StringBuilder();
        receipt.append("\n🧾 Order Receipt").append("\n---------------------------------------------\n");
        receipt.append("Customer: ").append(userName).append(" | Email: ").append(userEmail).append("\n");
        receipt.append("Time: ").append(time).append("\n");

        double total = 0;
        for (String item : cart.keySet()) {
            int qty = cartQty.get(item);
            int price = cart.get(item);
            double lineTotal = qty * price;
            total += lineTotal;
            receipt.append(String.format("%-12s x %2d = ₹%.2f\n", item, qty, lineTotal));
        }

        double gst = total * GST_RATE;
        double subtotalWithGst = total + gst;
        double discount = subtotalWithGst * DISCOUNT_PERCENT / 100.0;
        double finalAmount = subtotalWithGst - discount;

        receipt.append("---------------------------------------------\n");
        receipt.append(String.format("Subtotal: ₹%.2f\n", total));
        receipt.append(String.format("GST (18%%): ₹%.2f\n", gst));
        receipt.append(String.format("Total + GST: ₹%.2f\n", subtotalWithGst));
        receipt.append(String.format("Discount (%d%%): ₹%.2f\n", DISCOUNT_PERCENT, discount));
        receipt.append(String.format("💳 Final Amount: ₹%.2f\n", finalAmount));

        System.out.println(receipt);

        // Payment
        System.out.println("\n💰 Choose Payment Method:");
        System.out.println("1) UPI\n2) Credit/Debit Card\n3) Cash on Delivery");
        int payment = sc.nextInt();
        switch (payment) {
            case 1 -> System.out.println("✅ Payment via UPI successful!");
            case 2 -> System.out.println("✅ Card payment successful!");
            case 3 -> System.out.println("🚚 Please keep exact change ready.");
            default -> System.out.println("⚠️ Invalid payment option.");
        }

        System.out.println("\n🙏 Thank you for shopping with us!");

        // Save to file
        try (FileWriter fw = new FileWriter("order_history.txt", true)) {
            fw.write(receipt.toString());
            fw.write("\n\n");
        } catch (IOException e) {
            System.out.println("❌ Error saving order history.");
        }
    }
}
