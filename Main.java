import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AVLTreeInventory inventory = new AVLTreeInventory();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== نظام إدارة المخازن الذكي (AVL Tree) ===");
// 1. إدخال المنتجات ديناميكياً من قبل المستخدم
        System.out.print("كم عدد المنتجات التي تريد إضافتها للمخزن؟ ");
        int numberOfProducts = scanner.nextInt();

        for (int i = 1; i <= numberOfProducts; i++) {
            System.out.println("\n--- إدخال بيانات المنتج رقم " + i + " ---");
            System.out.print("أدخل الرقم التسلسلي (Product ID): ");
            int id = scanner.nextInt();
            scanner.nextLine(); // لتنظيف السطر بعد قراءة الأرقام

            System.out.print("أدخل اسم المنتج: ");
            String name = scanner.nextLine();
            System.out.print("أدخل سعر المنتج: ");
            double price = scanner.nextDouble();



// إضافة المنتج للشجرة مع الموازنة التلقائية
            inventory.insert(id,name, price);
        }

// 2. عرض المخزن مرتباً تلقائياً بناءً على الـ ID
        System.out.println("\n--- طباعة المخزن كاملاً (مرتب تلقائياً بواسطة AVL Tree) ---");
        inventory.displayInventory();

// 3. جعل المستخدم يبحث عن أي منتج يريده
        System.out.println("\n=== نظام البحث السريع O(log n) ===");
        char continueSearch;

        do {
            System.out.print("أدخل رقم المنتج (ID) الذي تريد البحث عنه فوراً: ");
            int searchId = scanner.nextInt();

            long startTime = System.nanoTime(); // حساب وقت بداية البحث بدقة
            ProductNode found = inventory.search(searchId);
            long endTime = System.nanoTime(); // حساب وقت النهاية

            if (found != null) {
                System.out.println("✅ تم العثور على المنتج في " + (endTime - startTime) + " نانوشانية!");
                System.out.println("-> الاسم: " + found.name + " | السعر: $" + found.price);
            } else {
                System.out.println("❌ عذراً، المنتج ذو الرقم " + searchId + " غير موجود بالمخزن.");
            }

            System.out.print("\nهل تريد البحث عن منتج آخر؟ (y/n): ");
            continueSearch = scanner.next().charAt(0);
        } while (continueSearch == 'y' || continueSearch == 'Y');

        System.out.println("\nشكرًا لاستخدامك نظام الفلترة الذكي! بالتوفيق يا مهندسة علا. ✨");
        scanner.close();
    }
}
