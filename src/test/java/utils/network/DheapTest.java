package utils.network;

import org.junit.jupiter.api.Test;
import utils.exceptions.DoNotExecution;
import utils.exceptions.InvalidArguments;

import static org.junit.jupiter.api.Assertions.*;

class DheapTest {
  @Test
  void addAndShiftUp() {
    // 2-heap
    {
      Dheap dheap = new Dheap(2, 6);
      double[] weight = new double[] {5.0, 2.0, 1.0, 4.0, 0.0, 3.0};

      // initialization
      assertEquals(2, dheap.getChildrenNum());
      assertEquals(0, dheap.getHeapNum());

      // add + shift up
      int[] heap = new int[] {0, -1, -1, -1, -1, -1};
      int[] heapIndex = new int[] {0, -1, -1, -1, -1, -1};
      dheap.add(0);
      assertEquals(2, dheap.getChildrenNum());
      assertEquals(1, dheap.getHeapNum());
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftUp(0, weight);
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // add + shift up
      heap = new int[] {0, 1, -1, -1, -1, -1};
      heapIndex = new int[] {0, 1, -1, -1, -1, -1};
      dheap.add(1);
      assertEquals(2, dheap.getChildrenNum());
      assertEquals(2, dheap.getHeapNum());
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftUp(1, weight);
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {1, 0, -1, -1, -1, -1};
      heapIndex = new int[] {1, 0, -1, -1, -1, -1};
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // add + shift up
      heap = new int[] {1, 0, 2, -1, -1, -1};
      heapIndex = new int[] {1, 0, 2, -1, -1, -1};
      dheap.add(2);
      assertEquals(2, dheap.getChildrenNum());
      assertEquals(3, dheap.getHeapNum());
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftUp(2, weight);
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {2, 0, 1, -1, -1, -1};
      heapIndex = new int[] {1, 2, 0, -1, -1, -1};
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // add + shift up
      heap = new int[] {2, 0, 1, 3, -1, -1};
      heapIndex = new int[] {1, 2, 0, 3, -1, -1};
      dheap.add(3);
      assertEquals(2, dheap.getChildrenNum());
      assertEquals(4, dheap.getHeapNum());
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftUp(3, weight);
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {2, 3, 1, 0, -1, -1};
      heapIndex = new int[] {3, 2, 0, 1, -1, -1};
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // add + shift up
      heap = new int[] {2, 3, 1, 0, 4, -1};
      heapIndex = new int[] {3, 2, 0, 1, 4, -1};
      dheap.add(4);
      assertEquals(2, dheap.getChildrenNum());
      assertEquals(5, dheap.getHeapNum());
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftUp(4, weight);
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {4, 2, 1, 0, 3, -1};
      heapIndex = new int[] {3, 2, 1, 4, 0, -1};
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // add + shift up
      heap = new int[] {4, 2, 1, 0, 3, 5};
      heapIndex = new int[] {3, 2, 1, 4, 0, 5};
      dheap.add(5);
      assertEquals(2, dheap.getChildrenNum());
      assertEquals(6, dheap.getHeapNum());
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftUp(5, weight);
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {4, 2, 1, 0, 3, 5};
      heapIndex = new int[] {3, 2, 1, 4, 0, 5};
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }
    }

    // 3-heap
    {
      Dheap dheap = new Dheap(3, 8);
      double[] weight = new double[] {5.0, 2.0, 7.0, 4.0, 1.0, 3.0, 6.0, 0.0};

      // initialization
      assertEquals(3, dheap.getChildrenNum());
      assertEquals(0, dheap.getHeapNum());

      // add + shift up
      int[] heap = new int[] {0, -1, -1, -1, -1, -1, -1, -1};
      int[] heapIndex = new int[] {0, -1, -1, -1, -1, -1, -1, -1};
      dheap.add(0);
      assertEquals(3, dheap.getChildrenNum());
      assertEquals(1, dheap.getHeapNum());
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftUp(0, weight);
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // add + shift up
      heap = new int[] {0, 1, -1, -1, -1, -1, -1, -1};
      heapIndex = new int[] {0, 1, -1, -1, -1, -1, -1, -1};
      dheap.add(1);
      assertEquals(3, dheap.getChildrenNum());
      assertEquals(2, dheap.getHeapNum());
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftUp(1, weight);
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {1, 0, -1, -1, -1, -1, -1, -1};
      heapIndex = new int[] {1, 0, -1, -1, -1, -1, -1, -1};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // add + shift up
      heap = new int[] {1, 0, 2, -1, -1, -1, -1, -1};
      heapIndex = new int[] {1, 0, 2, -1, -1, -1, -1, -1};
      dheap.add(2);
      assertEquals(3, dheap.getChildrenNum());
      assertEquals(3, dheap.getHeapNum());
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftUp(2, weight);
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {1, 0, 2, -1, -1, -1, -1, -1};
      heapIndex = new int[] {1, 0, 2, -1, -1, -1, -1, -1};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // add + shift up
      heap = new int[] {1, 0, 2, 3, -1, -1, -1, -1};
      heapIndex = new int[] {1, 0, 2, 3, -1, -1, -1, -1};
      dheap.add(3);
      assertEquals(3, dheap.getChildrenNum());
      assertEquals(4, dheap.getHeapNum());
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftUp(3, weight);
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {1, 0, 2, 3, -1, -1, -1, -1};
      heapIndex = new int[] {1, 0, 2, 3, -1, -1, -1, -1};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // add + shift up
      heap = new int[] {1, 0, 2, 3, 4, -1, -1, -1};
      heapIndex = new int[] {1, 0, 2, 3, 4, -1, -1, -1};
      dheap.add(4);
      assertEquals(3, dheap.getChildrenNum());
      assertEquals(5, dheap.getHeapNum());
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftUp(4, weight);
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {4, 1, 2, 3, 0, -1, -1, -1};
      heapIndex = new int[] {4, 1, 2, 3, 0, -1, -1, -1};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // add + shift up
      heap = new int[] {4, 1, 2, 3, 0, 5, -1, -1};
      heapIndex = new int[] {4, 1, 2, 3, 0, 5, -1, -1};
      dheap.add(5);
      assertEquals(3, dheap.getChildrenNum());
      assertEquals(6, dheap.getHeapNum());
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftUp(5, weight);
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {4, 1, 2, 3, 0, 5, -1, -1};
      heapIndex = new int[] {4, 1, 2, 3, 0, 5, -1, -1};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // add + shift up
      heap = new int[] {4, 1, 2, 3, 0, 5, 6, -1};
      heapIndex = new int[] {4, 1, 2, 3, 0, 5, 6, -1};
      dheap.add(6);
      assertEquals(3, dheap.getChildrenNum());
      assertEquals(7, dheap.getHeapNum());
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftUp(6, weight);
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {4, 1, 2, 3, 0, 5, 6, -1};
      heapIndex = new int[] {4, 1, 2, 3, 0, 5, 6, -1};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // add + shift up
      heap = new int[] {4, 1, 2, 3, 0, 5, 6, 7};
      heapIndex = new int[] {4, 1, 2, 3, 0, 5, 6, 7};
      dheap.add(7);
      assertEquals(3, dheap.getChildrenNum());
      assertEquals(8, dheap.getHeapNum());
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftUp(7, weight);
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {7, 1, 4, 3, 0, 5, 6, 2};
      heapIndex = new int[] {4, 1, 7, 3, 2, 5, 6, 0};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }
    }

    // exception
    {
      Dheap dheap = new Dheap(2, 3);

      try {
        dheap.shiftUp(100, null);
      } catch (InvalidArguments e) {
        assertEquals(
            "utils.exceptions.InvalidArguments: Invalid Argument : vertexIndex is more than or equal to heapNum (vertexIndex = 100, heapNum = 0)",
            e.toString());
      } catch (DoNotExecution e) {
        assertEquals("", e.toString());
      }

      try {
        dheap.shiftUp(-1, null);
      } catch (InvalidArguments e) {
        assertEquals(
            "utils.exceptions.InvalidArguments: Invalid Argument : vertexIndex is less than 0",
            e.toString());
      } catch (DoNotExecution e) {
        assertEquals("", e.toString());
      }

      dheap.add(0);
      try {
        dheap.shiftUp(0, null);
      } catch (InvalidArguments e) {
        assertEquals(
            "utils.exceptions.InvalidArguments: Invalid Argument : weight is null", e.toString());
      } catch (DoNotExecution e) {
        assertEquals("", e.toString());
      }
    }
  }

  @Test
  void shiftDown() {
    // 2-heap
    {
      Dheap dheap = new Dheap(2, 6);
      double[] weight = new double[] {5.0, 2.0, 1.0, 4.0, 0.0, 3.0};
      for (int i = 0; i < 6; i++) {
        dheap.add(i);
        try {
          dheap.shiftUp(i, weight);
        } catch (InvalidArguments | DoNotExecution e) {
          assertEquals("", e.toString());
        }
      }

      // pop and shift down
      assertEquals(4, dheap.pop());
      assertEquals(5, dheap.getHeapNum());
      assertEquals(2, dheap.getChildrenNum());
      int[] heap = new int[] {5, 2, 1, 0, 3, 5};
      int[] heapIndex = new int[] {3, 2, 1, 4, 0, 0};
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftDown(weight);
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {2, 5, 1, 0, 3, 5};
      heapIndex = new int[] {3, 2, 0, 4, 0, 1};
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // pop and shift down
      assertEquals(2, dheap.pop());
      assertEquals(4, dheap.getHeapNum());
      assertEquals(2, dheap.getChildrenNum());
      heap = new int[] {3, 5, 1, 0, 3, 5};
      heapIndex = new int[] {3, 2, 0, 0, 0, 1};
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftDown(weight);
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {1, 5, 3, 0, 3, 5};
      heapIndex = new int[] {3, 0, 0, 2, 0, 1};
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // pop and shift down
      assertEquals(1, dheap.pop());
      assertEquals(3, dheap.getHeapNum());
      assertEquals(2, dheap.getChildrenNum());
      heap = new int[] {0, 5, 3, 0, 3, 5};
      heapIndex = new int[] {0, 0, 0, 2, 0, 1};
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftDown(weight);
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {5, 0, 3, 0, 3, 5};
      heapIndex = new int[] {1, 0, 0, 2, 0, 0};
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // pop and shift down
      assertEquals(5, dheap.pop());
      assertEquals(2, dheap.getHeapNum());
      assertEquals(2, dheap.getChildrenNum());
      heap = new int[] {3, 0, 3, 0, 3, 5};
      heapIndex = new int[] {1, 0, 0, 0, 0, 0};
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftDown(weight);
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {3, 0, 3, 0, 3, 5};
      heapIndex = new int[] {1, 0, 0, 0, 0, 0};
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // pop and shift down
      assertEquals(3, dheap.pop());
      assertEquals(1, dheap.getHeapNum());
      assertEquals(2, dheap.getChildrenNum());
      heap = new int[] {0, 0, 3, 0, 3, 5};
      heapIndex = new int[] {0, 0, 0, 0, 0, 0};
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftDown(weight);
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {0, 0, 3, 0, 3, 5};
      heapIndex = new int[] {0, 0, 0, 0, 0, 0};
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // pop and shift down
      assertEquals(0, dheap.pop());
      assertEquals(0, dheap.getHeapNum());
      assertEquals(2, dheap.getChildrenNum());
      heap = new int[] {0, 0, 3, 0, 3, 5};
      heapIndex = new int[] {0, 0, 0, 0, 0, 0};
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftDown(weight);
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {0, 0, 3, 0, 3, 5};
      heapIndex = new int[] {0, 0, 0, 0, 0, 0};
      assertEquals(6, dheap.getHeap().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(6, dheap.getHeapIndexes().length);
      for (int i = 0; i < 6; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }
    }

    // 3-heap
    {
      Dheap dheap = new Dheap(3, 8);
      double[] weight = new double[] {5.0, 2.0, 7.0, 4.0, 1.0, 3.0, 6.0, 0.0};
      for (int i = 0; i < 8; i++) {
        dheap.add(i);
        try {
          dheap.shiftUp(i, weight);
        } catch (InvalidArguments | DoNotExecution e) {
          assertEquals("", e.toString());
        }
      }

      // pop and shift down
      assertEquals(7, dheap.pop());
      assertEquals(7, dheap.getHeapNum());
      assertEquals(3, dheap.getChildrenNum());
      int[] heap = new int[] {2, 1, 4, 3, 0, 5, 6, 2};
      int[] heapIndex = new int[] {4, 1, 0, 3, 2, 5, 6, 0};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftDown(weight);
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {4, 1, 2, 3, 0, 5, 6, 2};
      heapIndex = new int[] {4, 1, 2, 3, 0, 5, 6, 0};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // pop and shift down
      assertEquals(4, dheap.pop());
      assertEquals(6, dheap.getHeapNum());
      assertEquals(3, dheap.getChildrenNum());
      heap = new int[] {6, 1, 2, 3, 0, 5, 6, 2};
      heapIndex = new int[] {4, 1, 2, 3, 0, 5, 0, 0};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftDown(weight);
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {1, 5, 2, 3, 0, 6, 6, 2};
      heapIndex = new int[] {4, 0, 2, 3, 0, 1, 5, 0};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 1; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // pop and shift down
      assertEquals(1, dheap.pop());
      assertEquals(5, dheap.getHeapNum());
      assertEquals(3, dheap.getChildrenNum());
      heap = new int[] {6, 5, 2, 3, 0, 6, 6, 2};
      heapIndex = new int[] {4, 0, 2, 3, 0, 1, 0, 0};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftDown(weight);
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {5, 0, 2, 3, 6, 6, 6, 2};
      heapIndex = new int[] {1, 0, 2, 3, 0, 0, 4, 0};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // pop and shift down
      assertEquals(5, dheap.pop());
      assertEquals(4, dheap.getHeapNum());
      assertEquals(3, dheap.getChildrenNum());
      heap = new int[] {6, 0, 2, 3, 6, 6, 6, 2};
      heapIndex = new int[] {1, 0, 2, 3, 0, 0, 0, 0};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftDown(weight);
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {3, 6, 0, 2, 6, 6, 6, 2};
      heapIndex = new int[] {2, 0, 3, 0, 0, 0, 1, 0};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // pop and shift down
      assertEquals(3, dheap.pop());
      assertEquals(3, dheap.getHeapNum());
      assertEquals(3, dheap.getChildrenNum());
      heap = new int[] {2, 6, 0, 2, 6, 6, 6, 2};
      heapIndex = new int[] {2, 0, 0, 0, 0, 0, 1, 0};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftDown(weight);
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {0, 2, 6, 2, 6, 6, 6, 2};
      heapIndex = new int[] {0, 0, 1, 0, 0, 0, 2, 0};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // pop and shift down
      assertEquals(0, dheap.pop());
      assertEquals(2, dheap.getHeapNum());
      assertEquals(3, dheap.getChildrenNum());
      heap = new int[] {6, 2, 6, 2, 6, 6, 6, 2};
      heapIndex = new int[] {0, 0, 1, 0, 0, 0, 0, 0};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftDown(weight);
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {6, 2, 6, 2, 6, 6, 6, 2};
      heapIndex = new int[] {0, 0, 1, 0, 0, 0, 0, 0};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // pop and shift down
      assertEquals(6, dheap.pop());
      assertEquals(1, dheap.getHeapNum());
      assertEquals(3, dheap.getChildrenNum());
      heap = new int[] {2, 2, 6, 2, 6, 6, 6, 2};
      heapIndex = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftDown(weight);
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {2, 2, 6, 2, 6, 6, 6, 2};
      heapIndex = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      // pop and shift down
      assertEquals(2, dheap.pop());
      assertEquals(0, dheap.getHeapNum());
      assertEquals(3, dheap.getChildrenNum());
      heap = new int[] {2, 2, 6, 2, 6, 6, 6, 2};
      heapIndex = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }

      try {
        dheap.shiftDown(weight);
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
      heap = new int[] {2, 2, 6, 2, 6, 6, 6, 2};
      heapIndex = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
      assertEquals(8, dheap.getHeap().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heap[i], dheap.getHeap()[i]);
      }
      assertEquals(8, dheap.getHeapIndexes().length);
      for (int i = 0; i < 8; i++) {
        assertEquals(heapIndex[i], dheap.getHeapIndexes()[i]);
      }
    }

    // exception
    {
      Dheap dheap = new Dheap(10, 10);
      try {
        dheap.shiftDown(null);
      } catch (InvalidArguments e) {
        assertEquals(
            "utils.exceptions.InvalidArguments: Invalid Argument : weight is null", e.toString());
      }
    }
  }
}
