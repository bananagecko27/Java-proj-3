package test.java;

/**
 * DO NOT DISTRIBUTE.
 *
 * This code is intended to support the education of students associated with the Tandy School of
 * Computer Science at the University of Tulsa. It is not intended for distribution and should
 * remain within private repositories that belong to Tandy faculty, students, and/or alumni.
 */
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.hamcrest.junit.MatcherAssume.assumeThat;

import java.util.Iterator;
import main.java.BasicLinkedList;
import main.java.BasicListInterface;
import org.junit.Test;
import test.java.TUGrader.Deps;
import test.java.TUGrader.DisplayName;
import test.java.TUGrader.TestGroup;

/**
 * This class provides a set of unit tests for the {@code BasicLinkedList} class
 * using the JUnit testing framework and the Java Reflection API.
 */
public class BasicLinkedListTest {

  @Test
  @TestGroup("default")
  @DisplayName("BasicLinkedList() should construct an empty list")
  @Deps({"isEmpty()", "size()"})
  public void testConstructEmptyList() {
    assertThat(new BasicLinkedList<Integer>().isEmpty(), is(true));
    assertThat(new BasicLinkedList<Integer>().size(), is(equalTo(0)));
  }

  @Test
  @TestGroup("constructors")
  @DisplayName("BasicLinkedList(BasicLinkedList) should clone the input list")
  @Deps({"add(int, E)", "size()"})
  public void testConstructCopyOfList() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    assumeThat(list.size(), is(equalTo(1)));
    assertThat(new BasicLinkedList<>(list).size(), is(equalTo(1)));
  }

  @Test
  @TestGroup("constructors")
  @DisplayName("BasicLinkedList(BasicLinkedList) should use distinct nodes when cloning a list")
  @Deps({"add(int, E)", "size()"})
  public void testConstructCopyOfListWithDistinctNodes() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    assumeThat(list.size(), is(equalTo(1)));
    BasicLinkedList<Integer> clone = new BasicLinkedList<>(list);
    assumeThat(clone.size(), is(equalTo(1)));
    list.add(1, 20);
    assertThat(list.size(), is(equalTo(2)));
    assertThat(clone.size(), is(equalTo(1)));
  }

  @Test
  @TestGroup("constructors")
  @DisplayName(
      "BasicLinkedList(BasicLinkedList) should use shallow copies of individual elements when"
          + " cloning a list")
  @Deps({"add(int, E)", "get(int)"})
  public void testConstructCopyOfListWithShallowCopiesOfElements() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    Integer elt = 10;
    list.add(0, elt);
    assumeThat(list.get(0), is(sameInstance(elt)));
    BasicLinkedList<Integer> clone = new BasicLinkedList<>(list);
    assertThat(clone.get(0), is(sameInstance(elt)));
  }

  @Test
  @TestGroup("constructors")
  @DisplayName("BasicLinkedList(BasicLinkedList) should not alter the input list")
  @Deps({"add(int, E)", "get(int)", "size()"})
  public void testConstructorDoesNotAlterInputListWhenCopying() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    Integer elt = 10;
    list.add(0, elt);
    assumeThat(list.size(), is(equalTo(1)));
    assumeThat(list.get(0), is(sameInstance(elt)));
    new BasicLinkedList<>(list);
    assertThat(list.size(), is(equalTo(1)));
    assertThat(list.get(0), is(sameInstance(elt)));
  }

  @Test
  @TestGroup("append")
  @DisplayName("add(E) should append the input element to the end of the list and increment size")
  @Deps({"get(int)", "size()"})
  public void testAddAppendsElementToTheEndOfTheListAndIncrementsSize() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    assumeThat(list.size(), is(equalTo(0)));
    Integer e1 = 10;
    list.add(e1);
    assertThat(list.size(), is(equalTo(1)));
    assertThat(list.get(list.size() - 1), is(sameInstance(e1)));
    Integer e2 = 20;
    list.add(e2);
    assertThat(list.size(), is(equalTo(2)));
    assertThat(list.get(list.size() - 1), is(sameInstance(e2)));
    Integer e3 = 30;
    list.add(e3);
    assertThat(list.size(), is(equalTo(3)));
    assertThat(list.get(list.size() - 1), is(sameInstance(e3)));
  }

  @Test
  @TestGroup("append")
  @DisplayName("add(E) should return true after successfully appending the input element")
  public void testAddReturnsTrueAfterAppending() {
    assertThat(new BasicLinkedList<>().add(10), is(true));
  }

  @Test
  @TestGroup("insert")
  @DisplayName("add(int, E) should insert the input element at the input position")
  @Deps({"get(int)", "size()"})
  public void testAddInsertsElementAtIndexAndIncrementsSize() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    assumeThat(list.size(), is(equalTo(0)));
    Integer e1 = 20;
    list.add(0, e1);
    assertThat(list.size(), is(equalTo(1)));
    assertThat(list.get(0), is(sameInstance(e1)));
    Integer e2 = 40;
    list.add(1, e2);
    assertThat(list.size(), is(equalTo(2)));
    assertThat(list.get(1), is(sameInstance(e2)));
    Integer e3 = 30;
    list.add(1, e3);
    assertThat(list.size(), is(equalTo(3)));
    assertThat(list.get(1), is(sameInstance(e3)));
    assertThat(list.get(2), is(sameInstance(e2)));
    Integer e4 = 10;
    list.add(0, e4);
    assertThat(list.size(), is(equalTo(4)));
    assertThat(list.get(0), is(sameInstance(e4)));
    assertThat(list.get(1), is(sameInstance(e1)));
    assertThat(list.get(2), is(sameInstance(e3)));
    assertThat(list.get(3), is(sameInstance(e2)));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  @TestGroup("insert")
  @DisplayName("add(int, E) should throw an IndexOutOfBoundsException if index < 0")
  public void testAddThrowsExceptionWhenIndexLessThanZero() {
    new BasicLinkedList<>().add(-1, 10);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  @TestGroup("insert")
  @DisplayName("add(int, E) should throw an IndexOutOfBoundsException if index > size")
  public void testAddThrowsExceptionWhenIndexGreaterThanSize() {
    new BasicLinkedList<>().add(1, 10);
  }

  @Test
  @TestGroup("addAll")
  @DisplayName("addAll(BasicListInterface) should append the input list to the end and update size")
  @Deps({"add(int, E)", "get(int)", "size()"})
  public void testAddAllAppendsAllElementsToTheEndOfTheListAndUpdatesSize() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    assumeThat(list.size(), is(equalTo(0)));
    Integer e1 = 10;
    list.add(0, e1);
    assumeThat(list.size(), is(equalTo(1)));
    assumeThat(list.get(0), is(sameInstance(e1)));
    BasicListInterface<Integer> other = new BasicLinkedList<>();
    Integer e2 = 20;
    other.add(0, e2);
    Integer e3 = 30;
    other.add(1, e3);
    Integer e4 = 40;
    other.add(2, e4);
    assumeThat(other.size(), is(equalTo(3)));
    assumeThat(other.get(0), is(sameInstance(e2)));
    assumeThat(other.get(1), is(sameInstance(e3)));
    assumeThat(other.get(2), is(sameInstance(e4)));
    list.addAll(other);
    assertThat(list.size(), is(equalTo(4)));
    assertThat(list.get(0), is(sameInstance(e1)));
    assertThat(list.get(1), is(sameInstance(e2)));
    assertThat(list.get(2), is(sameInstance(e3)));
    assertThat(list.get(3), is(sameInstance(e4)));
  }

  @Test
  @TestGroup("addAll")
  @DisplayName("addAll(BasicListInterface) should not mutate the input list")
  @Deps({"add(int, E", "get(int)", "size()"})
  public void testAddAllDoesNotChangeTheInputList() {
    BasicListInterface<Integer> list = new BasicLinkedList<>();
    Integer e1 = 10;
    list.add(0, e1);
    Integer e2 = 20;
    list.add(1, e2);
    assumeThat(list.size(), is(equalTo(2)));
    assumeThat(list.get(0), is(sameInstance(e1)));
    assumeThat(list.get(1), is(sameInstance(e2)));
    new BasicLinkedList<>().addAll(list);
    assertThat(list.size(), is(equalTo(2)));
    assertThat(list.get(0), is(sameInstance(e1)));
    assertThat(list.get(1), is(sameInstance(e2)));
  }

  @Test
  @TestGroup("clear")
  @DisplayName("clear() should empty the list")
  @Deps({"add(int, E)", "isEmpty()"})
  public void testClearReducesListToEmpty() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    assumeThat(list.isEmpty(), is(false));
    list.clear();
    assertThat(list.isEmpty(), is(true));
  }

  @Test
  @TestGroup("contains")
  @DisplayName(
      "contains(E) should return true if the input element has an equivalent element in the list"
          + " (using .equals)")
  @Deps("add(int, E)")
  public void testContainsReturnsTrueIfElementIsInList() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    assertThat(list.contains(10), is(true));
  }

  @Test
  @TestGroup("contains")
  @DisplayName(
      "contains(E) should return false if the input element does not have an equivalent element in"
          + " the list (using .equals)")
  public void testContainsReturnsFalseIfElementIsNotInList() {
    assertThat(new BasicLinkedList<>().contains(10), is(false));
  }

  @Test
  @TestGroup("delete")
  @DisplayName("delete(int) should remove the element at the input index and decrement size")
  @Deps({"add(int, E)", "get(int)", "size()"})
  public void testDeleteRemovesElementAtIndexFromListAndDecrementsSize() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    Integer e1 = 10;
    list.add(0, e1);
    Integer e2 = 20;
    list.add(1, e2);
    Integer e3 = 30;
    list.add(2, e3);
    Integer e4 = 40;
    list.add(3, e4);
    assumeThat(list.size(), is(equalTo(4)));
    assumeThat(list.get(0), is(sameInstance(e1)));
    assumeThat(list.get(1), is(sameInstance(e2)));
    assumeThat(list.get(2), is(sameInstance(e3)));
    assumeThat(list.get(3), is(sameInstance(e4)));
    list.delete(3);
    assertThat(list.size(), is(equalTo(3)));
    assertThat(list.get(0), is(sameInstance(e1)));
    assertThat(list.get(1), is(sameInstance(e2)));
    assertThat(list.get(2), is(sameInstance(e3)));
    list.delete(1);
    assertThat(list.size(), is(equalTo(2)));
    assertThat(list.get(0), is(sameInstance(e1)));
    assertThat(list.get(1), is(sameInstance(e3)));
    list.delete(0);
    assertThat(list.size(), is(equalTo(1)));
    assertThat(list.get(0), is(sameInstance(e3)));
    list.delete(0);
    assertThat(list.size(), is(equalTo(0)));
  }

  @Test
  @TestGroup("delete")
  @DisplayName("delete(int) should return the removed element")
  @Deps({"add(int, E)", "get(int)"})
  public void testDeleteReturnsRemovedElement() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    Integer e1 = 10;
    list.add(0, e1);
    Integer e2 = 20;
    list.add(1, e2);
    Integer e3 = 30;
    list.add(2, e3);
    Integer e4 = 40;
    list.add(3, e4);
    assertThat(list.delete(0), is(sameInstance(e1)));
    assertThat(list.delete(0), is(sameInstance(e2)));
    assertThat(list.delete(0), is(sameInstance(e3)));
    assertThat(list.delete(0), is(sameInstance(e4)));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  @TestGroup("delete")
  @DisplayName("delete(int) should throw an IndexOutOfBoundsException if index < 0")
  public void testDeleteThrowsExceptionWhenIndexLessThanZero() {
    new BasicLinkedList<>().delete(-1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  @TestGroup("delete")
  @DisplayName("delete(int) should throw an IndexOutOfBoundsException if index > size")
  public void testDeleteThrowsExceptionWhenIndexGreaterThanSize() {
    new BasicLinkedList<>().delete(1);
  }

  @Test
  @TestGroup("equals")
  @DisplayName("equals(BasicListInterface) should return true when comparing empty lists")
  public void testEqualsShouldReturnTrueWhenComparingEmptyLists() {
    assertThat(new BasicLinkedList<>().equals(new BasicLinkedList<>()), is(true));
  }

  @Test
  @TestGroup("equals")
  @DisplayName(
      "equals(BasicListInterface) should return false when comparing a non-empty list and an empty"
          + " list")
  @Deps({"add(int, E)", "isEmpty()"})
  public void testEqualsShouldReturnFalseWhenComparingNonEmptyListAndEmptyList() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    assumeThat(list.isEmpty(), is(false));
    assertThat(list.equals(new BasicLinkedList<>()), is(false));
    assertThat(new BasicLinkedList<>().equals(list), is(false));
  }

  @Test
  @TestGroup("equals")
  @DisplayName("equals(BasicListInterface) should return true when comparing a list to its copies")
  @Deps({"BasicLinkedList(BasicListInterface)", "add(int, E)"})
  public void testEqualsShouldReturnTrueWhenComparingAListToItsCopies() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    assertThat(list.equals(new BasicLinkedList<>(list)), is(true));
    assertThat(new BasicLinkedList<>(list).equals(list), is(true));
    BasicLinkedList<Integer> copy = list;
    assertThat(list.equals(copy), is(true));
    assertThat(copy.equals(list), is(true));
  }

  @Test
  @TestGroup("equals")
  @DisplayName(
      "equals(BasicListInterface) should return true when comparing lists with equivalent elements"
          + " in the same positions")
  @Deps("add(int, E)")
  public void testEqualsShouldReturnTrueWhenComparingEquivalentLists() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    list.add(1, 20);
    list.add(2, 30);
    BasicLinkedList<Integer> other = new BasicLinkedList<>();
    other.add(0, 10);
    other.add(1, 20);
    other.add(2, 30);
    assertThat(list.equals(other), is(true));
    assertThat(other.equals(list), is(true));
  }

  @Test
  @TestGroup("equals")
  @DisplayName(
      "equals(BasicListInterface) should return false when comparing lists with equivalent elements"
          + " in the different positions")
  @Deps("add(int, E)")
  public void testEqualsShouldReturnFalseWhenComparingNonEquivalentLists() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    list.add(1, 20);
    list.add(2, 30);
    BasicLinkedList<Integer> other = new BasicLinkedList<>();
    other.add(0, 30);
    other.add(1, 20);
    other.add(2, 10);
    assertThat(list.equals(other), is(false));
    assertThat(other.equals(list), is(false));
  }

  @Test
  @TestGroup("equals")
  @DisplayName(
      "equals(BasicListInterface) should return false when comparing lists with different sizes")
  @Deps({"add(int, E)", "size()"})
  public void testEqualsShouldReturnFalseWhenComparingListsWithDifferentSizes() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    list.add(1, 20);
    list.add(2, 30);
    BasicLinkedList<Integer> other = new BasicLinkedList<>();
    other.add(0, 10);
    other.add(1, 20);
    assumeThat(list.size(), is(not(equalTo(other.size()))));
    assertThat(list.equals(other), is(false));
    assertThat(other.equals(list), is(false));
  }

  @Test
  @TestGroup("get")
  @DisplayName("get(int) should return the element at the input index")
  @Deps("add(int, E)")
  public void testGetReturnsElementAtIndex() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    Integer e1 = 10;
    list.add(0, e1);
    assumeThat(list.size(), is(equalTo(1)));
    assertThat(list.get(0), is(sameInstance(e1)));
    Integer e2 = 20;
    list.add(1, e2);
    assumeThat(list.size(), is(equalTo(2)));
    assertThat(list.get(1), is(sameInstance(e2)));
    Integer e3 = 30;
    list.add(2, e3);
    assumeThat(list.size(), is(equalTo(3)));
    assertThat(list.get(2), is(sameInstance(e3)));
  }

  @Test
  @TestGroup("get")
  @DisplayName("get(int) should not modify the list")
  @Deps({"add(int, E)", "size()"})
  public void testGetDoesNotModifyList() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    Integer e1 = 10;
    list.add(0, e1);
    assumeThat(list.size(), is(equalTo(1)));
    assertThat(list.get(0), is(sameInstance(e1)));
    assertThat(list.size(), is(equalTo(1)));
    assertThat(list.get(0), is(sameInstance(e1)));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  @TestGroup("get")
  @DisplayName("get(int) should throw an IndexOutOfBoundsException if index < 0")
  public void testGetThrowsExceptionWhenIndexLessThanZero() {
    new BasicLinkedList<>().get(-1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  @TestGroup("get")
  @DisplayName("get(int) should throw an IndexOutOfBoundsException if index > size")
  public void testGetThrowsExceptionWhenIndexGreaterThanSize() {
    new BasicLinkedList<>().get(1);
  }

  @Test
  @TestGroup("indexOf")
  @DisplayName("indexOf(E) should return the index of an equivalent element")
  @Deps("add(int, E)")
  public void testIndexOfReturnsIndexOfEquivalentElement() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    assertThat(list.indexOf(10), is(equalTo(0)));
    list.add(1, 20);
    assertThat(list.indexOf(20), is(equalTo(1)));
    list.add(2, 30);
    assertThat(list.indexOf(30), is(equalTo(2)));
  }

  @Test
  @TestGroup("indexOf")
  @DisplayName("indexOf(E) should return the index of the first occurence of an equivalent element")
  @Deps("add(int, E)")
  public void testIndexOfReturnsIndexOfFirstOccurrenceOfEquivalentElement() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    assertThat(list.indexOf(10), is(equalTo(0)));
    list.add(1, 10);
    assertThat(list.indexOf(10), is(equalTo(0)));
    list.add(2, 10);
    assertThat(list.indexOf(10), is(equalTo(0)));
  }

  @Test
  @TestGroup("indexOf")
  @DisplayName("indexOf(E) should return the -1 if an equivalent element is not in the list")
  public void testIndexOfReturnsNegativeOneIfElementIsNotInList() {
    assertThat(new BasicLinkedList<>().indexOf(10), is(equalTo(-1)));
  }

  @Test
  @TestGroup("getters")
  @DisplayName("isEmpty() should return true if the list is empty (has no elements)")
  public void testIsEmptyReturnsTrueWhenListIsEmpty() {
    assertThat(new BasicLinkedList<>().isEmpty(), is(true));
  }

  @Test
  @TestGroup("getters")
  @DisplayName("isEmpty() should return false if the list is not empty (has elements)")
  @Deps("add(int, E)")
  public void testIsEmptyReturnsFalseWhenListIsNotEmpty() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    assertThat(list.isEmpty(), is(false));
  }

  @Test
  @TestGroup("iterator")
  @DisplayName("iterator() should return an iterator with no next element for an empty list")
  public void testIteratorHasNoNextForEmptyList() {
    assertThat(new BasicLinkedList<>().iterator().hasNext(), is(false));
  }

  @Test
  @TestGroup("iterator")
  @DisplayName("iterator() should return an iterator that iterates over the elements of the list")
  @Deps("add(int, E)")
  public void testIteratorIteratesOverElementsOfList() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    Integer e1 = 10;
    list.add(0, e1);
    Integer e2 = 20;
    list.add(1, e2);
    Integer e3 = 30;
    list.add(2, e3);
    Iterator<Integer> iter = list.iterator();
    assertThat(iter.hasNext(), is(true));
    assertThat(iter.next(), is(sameInstance(e1)));
    assertThat(iter.hasNext(), is(true));
    assertThat(iter.next(), is(sameInstance(e2)));
    assertThat(iter.hasNext(), is(true));
    assertThat(iter.next(), is(sameInstance(e3)));
    assertThat(iter.hasNext(), is(false));
  }

  @Test
  @TestGroup("remove")
  @DisplayName("remove(E) should remove the input element from the list and decrement size")
  @Deps({"add(int, E)", "contains(E)", "size()"})
  public void testRemoveRemovesElementWhenPresentAndDecrementSize() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    list.add(1, 20);
    list.add(2, 30);
    assumeThat(list.size(), is(equalTo(3)));
    assumeThat(list.contains(10), is(true));
    assumeThat(list.contains(20), is(true));
    assumeThat(list.contains(30), is(true));
    list.remove(10);
    assertThat(list.size(), is(equalTo(2)));
    assertThat(list.contains(10), is(false));
    list.remove(20);
    assertThat(list.size(), is(equalTo(1)));
    assertThat(list.contains(20), is(false));
    list.remove(30);
    assertThat(list.size(), is(equalTo(0)));
    assertThat(list.contains(30), is(false));
  }

  @Test
  @TestGroup("remove")
  @DisplayName(
      "remove(E) should remove the first occurrence of an equivalent element from the list")
  @Deps({"add(int, E)", "get(int)", "size()"})
  public void testRemoveRemovesFirstOccurrenceOfElement() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    Integer e1 = 10;
    list.add(0, e1);
    Integer e2 = 10;
    list.add(1, e2);
    Integer e3 = 10;
    list.add(2, e3);
    assumeThat(list.size(), is(equalTo(3)));
    assumeThat(list.get(0), is(sameInstance(e1)));
    assumeThat(list.get(1), is(sameInstance(e2)));
    assumeThat(list.get(2), is(sameInstance(e3)));
    assumeThat(10, is(equalTo(e1)));
    assumeThat(e1, is(equalTo(e2)));
    assumeThat(e2, is(equalTo(e3)));
    list.remove(10);
    assertThat(list.size(), is(equalTo(2)));
    assertThat(list.get(0), is(sameInstance(e2)));
    assertThat(list.get(1), is(sameInstance(e3)));
  }

  @Test
  @TestGroup("remove")
  @DisplayName("remove(E) should return true after removing the element")
  @Deps({"add(int, E)", "size()"})
  public void testRemoveReturnsTrueAfterRemovingTheElement() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    assumeThat(list.size(), is(equalTo(1)));
    assertThat(list.remove(10), is(true));
    assertThat(list.size(), is(equalTo(0)));
  }

  @Test
  @TestGroup("remove")
  @DisplayName("remove(E) should not modify the list if the input element is not in the list")
  @Deps({"add(int, E)", "size()"})
  public void testRemoveDoesNotModifyTheListWhenElementIsNotContained() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    assumeThat(list.size(), is(equalTo(1)));
    list.remove(20);
    assertThat(list.size(), is(equalTo(1)));
  }

  @Test
  @TestGroup("remove")
  @DisplayName("remove(E) should return false if the input element is not in the list")
  @Deps("add(int, E)")
  public void testRemoveReturnsFalseWhenElementIsNotInList() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    assertThat(list.remove(20), is(false));
  }

  @Test
  @TestGroup("replace")
  @DisplayName(
      "replace(int, E) should replace the element at the input position with the input element")
  @Deps({"add(int, E)", "get(int)"})
  public void testReplaceReplacesElementAtPosition() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    Integer e1 = 10;
    list.add(0, e1);
    assumeThat(list.get(0), is(sameInstance(e1)));
    Integer e2 = 20;
    list.replace(0, e2);
    assertThat(list.get(0), is(sameInstance(e2)));
  }

  @Test
  @TestGroup("replace")
  @DisplayName("replace(int, E) should return the replaced element")
  @Deps({"add(int, E)", "get(int)"})
  public void testReplaceReturnsReplacedElement() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    Integer e1 = 10;
    list.add(0, e1);
    assumeThat(list.get(0), is(sameInstance(e1)));
    assertThat(list.replace(0, 20), is(sameInstance(e1)));
  }

  @Test
  @TestGroup("replace")
  @DisplayName("replace(int, E) should not change the list size")
  @Deps({"add(int, E)", "get(int)"})
  public void testReplaceDoesNotModifyTheListSize() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    Integer e1 = 10;
    list.add(0, e1);
    assumeThat(list.size(), is(equalTo(1)));
    list.replace(0, 20);
    assertThat(list.size(), is(equalTo(1)));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  @TestGroup("replace")
  @DisplayName("replace(int, E) should throw an IndexOutOfBoundsException if index < 0")
  public void testReplaceThrowsExceptionWhenIndexLessThanZero() {
    new BasicLinkedList<>().replace(-1, 10);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  @TestGroup("replace")
  @DisplayName("replace(int, E) should throw an IndexOutOfBoundsException if index > size")
  public void testReplaceThrowsExceptionWhenIndexGreaterThanSize() {
    new BasicLinkedList<>().replace(1, 10);
  }

  @Test
  @TestGroup("getters")
  @DisplayName("size() should return 0 for an empty list")
  public void testSizeReturnsZeroForEmptyList() {
    assertThat(new BasicLinkedList<>().size(), is(equalTo(0)));
  }

  @Test
  @TestGroup("getters")
  @DisplayName("size() should return 0 for an empty list")
  @Deps({"add(int, E)", "delete(int)"})
  public void testSizeReturnsSizeOfList() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    assumeThat(list.size(), is(equalTo(0)));
    list.add(0, 10);
    assertThat(list.size(), is(equalTo(1)));
    list.delete(0);
    assertThat(list.size(), is(equalTo(0)));
  }

  @Test
  @TestGroup("toString")
  @DisplayName("toString() should return [] for an empty list")
  public void testToStringRepresentsEmptyList() {
    assertThat(new BasicLinkedList<>().toString(), is(equalTo("[]")));
  }

  @Test
  @TestGroup("toString")
  @DisplayName("toString() should return [<elt>] for a list with a single element elt")
  @Deps("add(int, E)")
  public void testToStringRepresentsSingleElement() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    assertThat(list.toString(), is(equalTo("[10]")));
  }

  @Test
  @TestGroup("toString")
  @DisplayName("toString() should return [<e1>, <e2>, ..., <eN>] for a list with N elements")
  @Deps("add(int, E)")
  public void testToStringRepresentsNElements() {
    BasicLinkedList<Integer> list = new BasicLinkedList<>();
    list.add(0, 10);
    list.add(1, 20);
    list.add(2, 30);
    assertThat(list.toString(), is(equalTo("[10, 20, 30]")));
  }
}
