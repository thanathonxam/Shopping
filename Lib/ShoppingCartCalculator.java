import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * เขียน Javadoc ที่นี่เพื่ออธิบายกฎการทำงานและกรณีพิเศษ:
     * - จะทำอย่างไรถ้า items เป็น null หรือ empty?
     * - จะทำอย่างไรถ้า CartItem มี price หรือ quantity ติดลบ?
     * - กฎส่วนลด BOGO (ซื้อ 1 แถม 1)
     * - กฎส่วนลด BULK (ซื้อ >= 6 ชิ้น ลด 10%)
     * @param items รายการสินค้าในตะกร้า
     * @return ราคารวมหลังคำนวณส่วนลดแล้ว
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
         // ไม่มีสินค้าหรือตะกร้าว่างเปล่า
        if (items == null || items.isEmpty()) 
            return 0.0;
        // มีสินค้าตั้งแต่ 1 ขึ้นไป
            double total = 0.0;
        for(int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            double itemTotal = 0.0;
            String sku = item.sku();
        if (item.quantity() <= 0 || item.price() <= 0) {
                continue; // ข้ามสินค้าที่ไม่ถูกต้อง
            }
            switch (sku) {
                case "BOGO": { // ซื้อ 1 แถม 1 
                    int paidQty = (item.quantity() / 2) + (item.quantity() % 2);
                    itemTotal = paidQty * item.price();
                    break;
                }
                case "BULK": { // ซื้อ >= 6 ชิ้น ลด 10%
                    if (item.quantity() >= 6) {
                        itemTotal = (item.quantity() * item.price()) * 0.9;
                    } else {
                        itemTotal = item.quantity() * item.price();
                    }
                    break;
                }
                default: {
                    itemTotal = item.quantity() * item.price();
                }
            }
            
            total += itemTotal; 
        } 
        return total; 

    }
}