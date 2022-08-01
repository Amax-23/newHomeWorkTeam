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
        String[] discount = new String[products.length]; //указываем конкретные продукты по акции
        discount[0] = products[0];
        discount[2] = products[2];
        System.out.println("!!!АКЦИЯ!!! 3шт по цене 2-х!");
        for (String s : discount) {
            if (s == null) {
                continue;
            }
            System.out.println("--- " + s + " ---");
        }
        int sumProduct = 0;
        int[] sumOneProduct = new int[products.length];
        int[] countOneProduct = new int[products.length];//добавил массив количество товара в корзине
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
            countOneProduct[productNumber - 1] += productCount; //добавил сюда подсчет кол-ва товара
        }
        for (int i = 0; i < countOneProduct.length; i++) {//этот блок проверяет на дисконт
            if (countOneProduct[i] % 3 == 0 && discount[i] != null &&
                    countOneProduct[i] != 0 && countOneProduct[i] <= 3) {
                sumOneProduct[i] -= prices[i];
            } else if (countOneProduct[i] > 3 && discount[i] != null && countOneProduct[i] != 0) {
                sumOneProduct[i] = sumOneProduct[i] - prices[i] * (countOneProduct[i] / 3);
            }
        }
        System.out.println("Ваша корзина:");
        for (int i = 0; i < sumOneProduct.length; i++) {
            if (sumOneProduct[i] != 0) {
                System.out.println(products[i] + " " + countOneProduct[i] +
                        " шт " + prices[i] + " руб/шт " + sumOneProduct[i] +
                        " руб в сумме");// здесь заменил на количество, т.к. до дисконта считал сумма/цена за шт
                sumProduct += sumOneProduct[i];
            }
        }
        System.out.println("Итого: " + sumProduct);
    }
}