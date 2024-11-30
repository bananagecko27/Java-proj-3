package main.java;

/** Driver class to execute an experiment with {@code BasicLinkedList}. */
public class Main {

  public static void main(String[] args) {
    BasicLinkedList<Integer> list1 = new BasicLinkedList<>();

    list1.add(0, 10);
    list1.add(40);
    list1.add(1, 30);
    list1.add(1, 20);
    System.out.println(list1);

    BasicLinkedList<Integer> list2 = new BasicLinkedList<>();
    list2.add(20);
    System.out.println("The size of List 1 is " + list1.size());
    System.out.println("The size of List 2 is " + list2.size());

    list2 = new BasicLinkedList<Integer>(list1);
    System.out.println("List 1 is copied to list 2 and 2 now contains :");
    System.out.println(list2);

    System.out.println("List 1 is equal to List 2: " + list1.equals(list2));

    list1.add(4, 50);
    if (list1.indexOf(40) > 0) {
      System.out.println("List 1 contains 40");
    } else {
      System.out.println("List 1 does not contain 40");
    }

    if (list1.indexOf(90) > 0) {
      System.out.println("List 1 contains 90");
    } else {
      System.out.println("List 1 does not contain 90 ");
    }

    list1.add(5, 60);
    System.out.println("Value in the third position is " + list1.get(2));
    System.out.println(list1);

    list1.delete(5);
    list1.delete(0);
    System.out.println(list1);

    list1.delete(1);
    System.out.println(list1);

    list1.remove(80);
    list1.remove(40);
    System.out.println(list1);
  }
}
