package utils.network;

import java.util.Arrays;
import java.util.NoSuchElementException;
import utils.exceptions.DoNotExecution;
import utils.exceptions.InvalidArguments;

class Dheap {
  private final int childrenNum;
  private int heapNum;
  private final int[] heap;
  private final int[] heapIndexes;

  Dheap(final int childrenNum, final int maxHeapNum) {
    this.childrenNum = childrenNum;
    heapNum = 0;
    heap = new int[maxHeapNum];
    heapIndexes = new int[maxHeapNum];

    Arrays.fill(heap, -1);
    Arrays.fill(heapIndexes, -1);
  }

  void add(final int heapVal) {
    heap[heapNum] = heapVal;
    heapIndexes[heapVal] = heapNum;
    heapNum++;
  }

  void shiftUp(final int heapVal, final double[] weight)
      throws InvalidArguments, DoNotExecution {
    if (heapVal < 0) {
      throw new InvalidArguments("heapVal is less than 0");
    }

    if (heapVal >= heap.length) {
      throw new InvalidArguments(
          String.format(
              "heapVal is more than or equal to heapNum (heapVal = %d, heapNum = %d)",
              heapVal, heapNum));
    }

    int child = heapIndexes[heapVal];

    if (child >= heapNum) {
      throw new DoNotExecution(
          String.format(
              "heapIndex is more than or equal to heap num (heapIndex = %d, heap num = %d)",
              child, heapNum));
    }

    if (weight == null) {
      throw new InvalidArguments("weight is null");
    }

    while (child > 0) {
      int parent = (child - 1) / childrenNum;
      int parentVertexIndex = heap[parent];
      int childVertexIndex = heap[child];

      if (weight[childVertexIndex] < weight[parentVertexIndex]) {
        swapHeapIndexes(childVertexIndex, parentVertexIndex);
        swapHeap(child, parent);
        child = parent;
      } else {
        break;
      }
    }
  }

  void shiftDown(final double[] weight) throws InvalidArguments {
    if (weight == null) {
      throw new InvalidArguments("weight is null");
    }

    if (heapNum == 0) {
      return;
    }

    // if depth of d-heap is equal to 1, sort leaves.
    // reason using select sort is following.
    // - easy to implements
    // - not too late because #leaves is few
    if (heapNum <= childrenNum + 1) {
      for (int i = 1; i < heapNum - 1; i++) {
        int minHeapIndex = i + 1;
        int minHeapVertexIndex = heap[minHeapIndex];
        for (int j = i + 1; j < heapNum; j++) {
          if (weight[minHeapVertexIndex] > weight[heap[j]]) {
            minHeapIndex = j;
            minHeapVertexIndex = heap[j];
          }
        }

        if (weight[heap[i]] > weight[minHeapVertexIndex]) {
          swapHeapIndexes(heap[i], minHeapVertexIndex);
          swapHeap(i, minHeapIndex);
        }
      }
    }

    int parent = 0;
    int parentVertexIndex = heap[parent];

    while (parent * childrenNum + 1 < heapNum) {
      int minWeightChild = parent * childrenNum + 1;
      int minWeightChildVertexIndex = heap[minWeightChild];

      int child = minWeightChild + 1;
      int childVertexIndex = heap[child];

      while (child - (parent * childrenNum + 1) < childrenNum && child < heapNum) {
        if (weight[childVertexIndex] < weight[minWeightChildVertexIndex]) {
          minWeightChild = child;
          minWeightChildVertexIndex = childVertexIndex;
        }

        childVertexIndex = heap[++child];
      }

      if (weight[minWeightChildVertexIndex] < weight[parentVertexIndex]) {
        swapHeapIndexes(minWeightChildVertexIndex, parentVertexIndex);
        swapHeap(minWeightChild, parent);
        parent = minWeightChild;
      } else {
        break;
      }
    }
  }

  private void swapHeap(final int heapIndex1, final int heapIndex2) {
    int heapVal = heap[heapIndex1];
    heap[heapIndex1] = heap[heapIndex2];
    heap[heapIndex2] = heapVal;
  }

  private void swapHeapIndexes(final int heapVal1, final int heapVal2) {
    int heapIndex = heapIndexes[heapVal1];
    heapIndexes[heapVal1] = heapIndexes[heapVal2];
    heapIndexes[heapVal2] = heapIndex;
  }

  int getFirst() throws NoSuchElementException {
    if (heapNum == 0) {
      throw new NoSuchElementException("will pop heap first element when heap num is 0");
    }

    int root = heap[0];
    heap[0] = heap[--heapNum];
    heapIndexes[heap[0]] = 0;

    return root;
  }

  int getHeapNum() {
    return heapNum;
  }

  void setHeapNumTo1() {
    this.heapNum = 1;
  }

  void setFirstHeap(final int val) {
    heap[0] = val;
  }

  void setHeapIndexesTo0(final int index) {
    heapIndexes[index] = 0;
  }

  int getChildrenNum() {
    return childrenNum;
  }

  int[] getHeap() {
    return heap;
  }

  int[] getHeapIndexes() {
    return heapIndexes;
  }
}
