
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
