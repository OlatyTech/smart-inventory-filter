public class ProductNode {
    int productId; // الرقم التسلسلي للمنتج (هو الـ Key للبحث)
    String name; // اسم المنتج
    double price; // سعر المنتج

    int height; // الارتفاع (مهم جداً لحساب توازن شجرة AVL)
    ProductNode left; // الابن الأيسر
    ProductNode right; // الابن الأيمن

    // الـ Constructor لإنشاء منتج جديد
    public ProductNode(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.height = 1; // أي منتج جديد يبدأ بارتفاع 1
    }
}
