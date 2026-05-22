public class AVLTreeInventory {
    private ProductNode root;

    // 1. ميثود مساعدة للحصول على ارتفاع الـ Node بأمان
    private int getHeight(ProductNode node) {
        return (node == null) ? 0 : node.height;
    }

    // 2. ميثود حساب معامل التوازن (Balance Factor)
    private int getBalance(ProductNode node) {
        return (node == null) ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    // 3. الدوران جهة اليمين (Right Rotation) للموازنة
    private ProductNode rightRotate(ProductNode y) {
        ProductNode x = y.left;
        ProductNode T2 = x.right;

// إجراء الدوران
        x.right = y;
        y.left = T2;

// تحديث الارتفاعات
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x; // الـ Node الجديدة في الأعلى
    }

    // 4. الدوران جهة اليسار (Left Rotation) للموازنة
    private ProductNode leftRotate(ProductNode x) {
        ProductNode y = x.right;
        ProductNode T2 = y.left;

// إجراء الدوران
        y.left = x;
        x.right = T2;

// تحديث الارتفاعات
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y; // الـ Node الجديدة في الأعلى
    }

    // 5. ميثود إضافة منتج جديد تلقائياً مع الموازنة الذكية
    public void insert(int productId, String name, double price) {
        root = insertRec(root, productId, name, price);
    }

    private ProductNode insertRec(ProductNode node, int productId, String name, double price) {
// أ) الإضافة التقليدية في أشجار البحث الثنائية
        if (node == null) {
            return new ProductNode(productId, name, price);
        }

        if (productId < node.productId) {
            node.left = insertRec(node.left, productId, name, price);
        } else if (productId > node.productId) {
            node.right = insertRec(node.right, productId, name, price);
        } else {
            return node; // الرقم التسلسلي موجود مسبقاً، لا نكرره
        }

// ب) تحديث ارتفاع الـ Node الحالية
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

// ج) التحقق من التوازن وإجراء الـ Rotations إذا لزم الأمر
        int balance = getBalance(node);

// حالة Left Left
        if (balance > 1 && productId < node.left.productId) {
            return rightRotate(node);
        }

// حالة Right Right
        if (balance < -1 && productId > node.right.productId) {
            return leftRotate(node);
        }

// حالة Left Right
        if (balance > 1 && productId > node.left.productId) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

// حالة Right Left
        if (balance < -1 && productId < node.right.productId) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 6. البحث السريع الخارق O(log n)
    public ProductNode search(int productId) {
        return searchRec(root, productId);
    }

    private ProductNode searchRec(ProductNode node, int productId) {
        if (node == null || node.productId == productId) {
            return node;
        }
        if (productId < node.productId) {
            return searchRec(node.left, productId);
        }
        return searchRec(node.right, productId);
    }

    // 7. ميثود طباعة المخزن مرتباً بالـ ID (In-order Traversal)
    public void displayInventory() {
        displayRec(root);
    }

    private void displayRec(ProductNode node) {
        if (node != null) {
            displayRec(node.left);
            System.out.println("ID: " + node.productId + " | Name: " + node.name + " | Price: $" + node.price);
            displayRec(node.right);
        }
    }




}

