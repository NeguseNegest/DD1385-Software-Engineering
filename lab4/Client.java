
package lab4;

public class Client {
    public static void main(String[] args) {
        Component toothpaste = new leaf("Toothpaste", 200);
        Component shampoo = new leaf("Shampoo", 350);
 
        Component toiletryBag = new Composite("Toiletry Bag", 500);
        Component suitcase = new Composite("Suitcase", 3000);

        toiletryBag.add(toothpaste);
        toiletryBag.add(shampoo);

        suitcase.add(toiletryBag);
        System.out.println(suitcase.toString()); 
        System.out.println(suitcase.getWeight());

       
    }}
