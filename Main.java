import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final int MAX_COUNT_PRODUCT = 5;
        final int MIN_COUNT_PRODUCT = 0;
        Scanner scanner = new Scanner(System.in);
        String[] products = {"МОЛОКО", "КЕФИР", "БАТОН", "ХЛЕБ"};
        int[] prices = {55, 44, 33, 38};
        System.out.println("Список возможных товаров для покупок:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }
        int sumProduct = 0;
        int[] sumOneProduct = new int[products.length];
        while (true) {
            int productNumber = 0;
            int productCount = 0;
            System.out.println("Выберите товар и количество (не более " + MAX_COUNT_PRODUCT + ") или введите 'end':");
            String input = scanner.nextLine();
            if (input.equals("end")) {
                break;
            }
            try {
                String[] userChoice = input.split(" ");
                productNumber = Integer.parseInt(userChoice[0]);
                productCount = Integer.parseInt(userChoice[1]);
                if (userChoice.length != 2) {
                    System.out.println("Вы ввели неккоректные данные!");
                    continue;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("ОШИБКА: Вы ввели неверные данные!");
                continue;
            } catch (NumberFormatException d) {
                System.out.println("ОШИБКА: Вы ввели неверные данные!");
                continue;
            }
            if (productNumber <= 0 | productNumber > products.length) {
                System.out.println("Такого товара нет!");
                continue;
            }
            if (productCount < MIN_COUNT_PRODUCT || productCount >= MAX_COUNT_PRODUCT) {
                System.out.println("Вы выбрали недопустимое количество продукта!");
                continue;
            }
            int currentPrice = prices[productNumber - 1];
            sumOneProduct[productNumber - 1] += productCount * currentPrice;
        }
        System.out.println("Ваша корзина:");
        for (int i = 0; i < sumOneProduct.length; i++) {
            if (sumOneProduct[i] != 0) {
                System.out.println(products[i] + " " + sumOneProduct[i] / prices[i] +
                        " шт " + prices[i] + " руб/шт " + sumOneProduct[i] + " руб в сумме");
                sumProduct += sumOneProduct[i];
            }
        }
        System.out.println("Итого: " + sumProduct);
    }
}