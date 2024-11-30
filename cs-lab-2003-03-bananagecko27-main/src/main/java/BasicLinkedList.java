package main.java;

import java.util.Iterator;

/**
 * Reference-based implementation of the List ADT. This implementation is intended to
 * mirror (but not use) {@code java.util.LinkedList}.
 */
public class BasicLinkedList<E> implements BasicListInterface<E> {

  private Node<E> head; // the first node in the linked list
  private int size = 0; // the number of elements stored in the list

  /** Constructs an empty list. */
  public BasicLinkedList() {
    this.head = null;
  }

  /**
   * Constructs a list that is a deep-copy of the input list with shallow copies of the
   * elements from the input list.
   * @param list the list to be copied
   */
  public BasicLinkedList(BasicLinkedList<E> list) {
    if (list.isEmpty()) {
      return;
    }
    Iterator<E> iter = list.iterator();
    this.head = new Node<E>(iter.next(), null);
    Node<E> curr = this.head;
    while (iter.hasNext()) {
      curr.next = new Node<E>(iter.next(), null);
      curr = curr.next;
    }
    this.size = list.size;
  }

  /**
   * Appends the input element to the end of this list.
   * @param elt the element to be appended to the end of this list
   * @return {@code true} if the input element was appended to this list
   */
  public boolean add(E elt) {
    this.add(this.size, elt);
    return true;
  }

  /**
   * Inserts the input element at the input position in this list.
   * @param index the index at which the input element is to be inserted
   * @param elt the element to be inserted
   * @throws IndexOutOfBoundsException if index is out of range
   *   {@code (index < 0 || size() < index)}
   */
  public void add(int index, E elt) throws IndexOutOfBoundsException {
    this.assertBounds(index, this.size);
    // TODO: implement add(int, E)
    if(index == 0){
      Node <E> node = new Node <E>(elt, head);
      head = node;
    }
    else{
    Node <E> prevNode = getNode(index-1);
    Node <E> node = new Node <E>(elt, prevNode.next);
    prevNode.next = node;
    }
    
    size++;
    //throw new UnsupportedOperationException("add(int, E) not yet implemented");
  }

  /**
   * Appends all elements in the input list to the end of this list.
   * @param list the list of elements to append
   */
  public void addAll(BasicListInterface<? extends E> list) {
    // TODO: implement addAll(BasicListInterface)
    Iterator<? extends E> iter = list.iterator();
    if(this.isEmpty()){
      this.head = new Node<E>(iter.next(), null);
      this.size++;
    }
    Node<E> tail = this.getNode(this.size-1);
    for(E elt: list){
      tail.next = new Node<E>(elt, null);
      tail = tail.next;
      this.size++;
    }
  }

  /** Removes all elements from this list. */
  public void clear() {
    this.head = null;
    this.size = 0;
  }

  /**
   * Returns {@code true} if the input element is in this list.
   * @param elt the element being checked
   * @return {@code true} if the input element is present in this list
   */
  public boolean contains(E elt) {
    return this.indexOf(elt) >= 0;
  }

  /**
   * Deletes the element at the input position in this list and returns it.
   * @param index the index to delete
   * @return the element that was deleted
   * @throws IndexOutOfBoundsException if index is out of range
   *   {@code (index < 0 || size() <= index)}
   */
  public E delete(int index) throws IndexOutOfBoundsException {
    this.assertBounds(index, this.size - 1);
    // TODO: implement delete(int)
    if(index == 0){
      E past = head.item;
      head = head.next;
      size--;
      return past;
    }
    Node <E> deleted = getNode(index);
    Node <E> prevNode = getNode(index-1);
    prevNode.next = deleted.next;
    size--;
    return deleted.item;
    //throw new UnsupportedOperationException("delete(int) not yet implemented");
  }

  /**
   * Returns {@code true} if the input list contains equivalent elements in the same positions
   * as this list.
   * @param list the list of elements to check
   * @return {@code true} if the input list is equivalent to this list
   */
  public boolean equals(BasicListInterface<E> list) {
    if (this.size != list.size()) {
      return false;
    }
    Iterator<E> iter = list.iterator();
    for (E elt : this) {
      if (!iter.next().equals(elt)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns the element at the input position in this list.
   * @param index the index of element to return
   * @return the element at the input position in this list
   * @throws IndexOutOfBoundsException if index is out of range
   *   {@code (index < 0 || size() <= index)}
   */
  public E get(int index) throws IndexOutOfBoundsException {
    this.assertBounds(index, this.size - 1);
    // TODO: implement get(int)
    return getNode(index).item;
  }

  /**
   * Returns the index of the first occurrence of the input element in this list. If the
   * element is not present, the method returns {@code -1}.
   * @param elt the element whose index is being looked for
   * @return either the index of the first occurrence of the input element in this list, or
   *   {@code -1} if the element is not contained in this list
   */
  public int indexOf(E elt) {
    //TODO: implement indexOf(E)
    Node<E> curr = this.head;
    for (int i=0; i<size; i++){
      if(curr.item.equals(elt)){
        return i;
      }
      else{
        curr = curr.next;
      }
    }
    return -1;
    //throw new UnsupportedOperationException("indexOf(E) not yet implemented");
  }

  /**
   * Returns {@code true} if this list contains no elements.
   * @return {@code true} if this list contains no elements
   */
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Returns an iterator for the elements of this list.
   * @return an iterator for elements of this list
   */
  public Iterator<E> iterator() {
    return new BasicLinkedListIterator();
  }

  /**
   * Removes the first occurrence of the input element from this list if it is present.
   * @param elt the element to remove
   * @return {@code true} if an occurrence of the input element is removed
   */
  public boolean remove(E elt) {
    int index = this.indexOf(elt);
    if (index == -1) {
      return false;
    }
    this.delete(index);
    return true;
  }

  /**
   * Replaces the element in this list at the input index with the input element.
   * @param index the index of the element to be replaced
   * @param elt the element to be stored in the input position in this list
   * @return the element previously at the input position in this list
   * @throws IndexOutOfBoundsException if index is out of range
   *   {@code (index < 0 || size() <= index)}
   */
  public E replace(int index, E elt) throws IndexOutOfBoundsException {
    this.assertBounds(index, this.size - 1);
    // TODO: implement replace(int, E)
    if(index == 0){
      E rep = head.item;
      head.item = elt;
      return rep;
    }

    Node <E> prevNode = getNode(index-1);
    Node <E> replaced = getNode(index);
    Node <E> newNode = new Node <E>(elt, replaced.next);
    prevNode.next = newNode;
    newNode.next = replaced.next;
    
    return replaced.item;
    //throw new UnsupportedOperationException("replace(int, E) not yet implemented");
  }

  /**
   * Returns the number of elements in this list.
   * @return the number of elements in this list
   */
  public int size() {
    return this.size;
  }

  /**
   * Returns a String representation of this list. The following are examples of a standard
   * representation of a list of numbers, (e.g., "[]", "[10]", "[10, 20, 30]").
   * @return the String representation of this list
   */
  public String toString() {
    // TODO: implement toString()
    if (head==null){
      return "[]";
    }
    else{
      String rep = "[" + head.item;
      Node <E> curr = head.next;
      while(curr != null){
        rep += ", " +curr.item;
        curr = curr.next;
      }
      rep += "]";
      return rep;
    }
    //throw new UnsupportedOperationException("toString() not yet implemented.");
  }

  private Node <E> getNode(int idx){
    //TODO: implement getNode()
    Node <E> curr = head;
    for (int i=0; i<idx; i++){
      curr = curr.next;
    }
    return curr;
  }


  /**
   * Asserts that the input index is within the provided bounds (inclusive).
   * @param index the index being checked
   * @param bound the upper bound for the index (inclusive)
   * @throws IndexOutOfBoundsException if the index is out of bounds
   *     {@code index < 0 || bound < index}
   */
  private void assertBounds(int index, int bound) {
    if (index < 0 || bound < index) {
      throw new IndexOutOfBoundsException(
          String.format("Index %d out of bounds for list size %d", index, this.size));
    }
  }

  /**
   * A Node for the implementation of {@code BasicLinkedList}. The node
   * contains only an item and a reference to the following Node.
   */
  private static class Node<E> {

    E item; // object contained in the Node
    Node<E> next; // reference to the following node

    /**
     * Constructs a node that contains the specified item and a reference to the specified
     * next node, such that this -> next.
     * @param item the item encapsulated in the node
     * @param next the successor node of this node
     */
    public Node(E item, Node<E> next) {
      this.item = item;
      this.next = next;
    }
  }

  /** An iterator for {@code BasicLinkedList}. */
  private class BasicLinkedListIterator implements Iterator<E> {

    private Node<E> cursor; // marks where the iterator is in the list

    /**
     * Constructs an iterator for a {@code BasicLinkedList} that starts at the specified head node.
     * @param head the head node of a linked list
     */
    public BasicLinkedListIterator() {
      this.cursor = BasicLinkedList.this.head;
    }

    /**
     * Returns {@code true} if this list has more elements to iterate over.
     * @return {@code true} if cursor has not reached the end of the list
     */
    public boolean hasNext() {
      return this.cursor != null;
    }

    /**
     * Returns the next element in this list.
     * @return the next element in this list
     */
    public E next() {
      if (this.cursor == null) {
        return null;
      }
      ;
      E item = this.cursor.item;
      this.cursor = this.cursor.next;
      return item;
    }
  }
}
