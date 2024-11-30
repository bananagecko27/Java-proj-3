package main.java;

import java.util.Iterator;

/** Interface for the List ADT. */
public interface BasicListInterface<E> extends Iterable<E> {

  /**
   * Appends the input element to the end of this list.
   * @param elt the element to be appended to the end of this list
   * @return {@code true} if the input element was appended to this list
   */
  public boolean add(E elt);

  /**
   * Inserts the input element at the input position in this list. Shifts the element
   * currently at that position (if any) and any subsequent elements to the right (adds one to
   * their indices).
   * @param index the index at which the input element is to be inserted
   * @param elt the element to be inserted
   * @throws IndexOutOfBoundsException if index is out of range
   *   {@code (index < 0 || size() < index)}
   */
  public void add(int index, E elt) throws IndexOutOfBoundsException;

  /**
   * Appends all elements in the input list to the end of this list.
   * @param list the list of elements to append
   */
  public void addAll(BasicListInterface<? extends E> list);

  /** Removes all elements from this list. */
  public void clear();

  /**
   * Returns {@code true} if the input element is in this list.
   * @param elt the element being checked
   * @return {@code true} if the input element is present in this list
   */
  public boolean contains(E elt);

  /**
   * Deletes the element at the input position in this list and returns it. Shifts any subsequent
   * elements to the left (subtracts one from their indices).
   * @param index the index to delete
   * @return the element that was deleted
   * @throws IndexOutOfBoundsException if index is out of range
   *   {@code (index < 0 || size() <= index)}
   */
  public E delete(int index) throws IndexOutOfBoundsException;

  /**
   * Returns {@code true} if the input list contains equivalent elements in the same positions
   * as this list.
   * @param list the list of elements to check
   * @return {@code true} if the input list is equivalent to this list
   */
  public boolean equals(BasicListInterface<E> list);

  /**
   * Returns the element at the input position in this list.
   * @param index the index of element to return
   * @return the element at the input position in this list
   * @throws IndexOutOfBoundsException if index is out of range
   *   {@code (index < 0 || size() <= index)}
   */
  public E get(int index) throws IndexOutOfBoundsException;

  /**
   * Returns the index of the first occurrence of the input element in this list. If the
   * element is not present, the method returns {@code -1}.
   * @param elt the element whose index is being looked for
   * @return either the index of the first occurrence of the input element in this list, or
   *   {@code -1} if the element is not contained in this list
   */
  public int indexOf(E elt);

  /**
   * Returns {@code true} if this list contains no elements.
   * @return {@code true} if this list contains no elements
   */
  public boolean isEmpty();

  /**
   * Returns an iterator for the elements of this list.
   * @return an iterator for elements of this list
   */
  public Iterator<E> iterator();

  /**
   * Removes the first occurrence of the input element from this list if it is present. Shifts
   * any subsequent elements to the left (subtracts one from their indices).
   * @param elt the element to remove
   * @return {@code true} if an occurrence of the input element is removed
   */
  public boolean remove(E elt);

  /**
   * Replaces the element in this list at the input index with the input element.
   * @param index the index of the element to be replaced
   * @param elt the element to be stored in the input position in this list
   * @return the element previously at the input position in this list
   * @throws IndexOutOfBoundsException if index is out of range
   *   {@code (index < 0 || size() <= index)}
   */
  public E replace(int index, E elt) throws IndexOutOfBoundsException;

  /**
   * Returns the number of elements in this list.
   * @return the number of elements in this list
   */
  public int size();
}
