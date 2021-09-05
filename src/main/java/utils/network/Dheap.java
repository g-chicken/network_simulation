package utils.network;

import java.util.Arrays;
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

  void add(final int vertexIndex) {
    heap[heapNum] = vertexIndex;
    heapIndexes[vertexIndex] = heapNum;
    heapNum++;
  }

  void shiftUp(final int vertexIndex, final double[] weight) throws InvalidArguments {
    int heapIndex = heapIndexes[vertexIndex];

    if (heapIndex >= heapNum) {
      throw new InvalidArguments(
          String.format(
              "heapIndex is more than or equal to heap num (heapIndex = %d, heap num = %d)",
              heapIndex, heapNum));
    }

    if (weight == null) {
      throw new InvalidArguments("weight is null");
    }

    int child = heapIndex;

    while (child > 0) {
      int parent = (child - 1) / childrenNum;
      int parentVertexIndex = heap[parent];
      int childVertexIndex = heap[child];

      if (weight[childVertexIndex] < weight[parentVertexIndex]) {
        heapIndexes[parentVertexIndex] = childVertexIndex;
        heapIndexes[childVertexIndex] = parentVertexIndex;

        swap(child, parent);
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

    heap[0] = heap[heapNum - 1];
    heapNum--;
    if (heapNum <= 1) {
      return;
    }

    int parent = 0;
    int parentVertexIndex = heap[parent];

    while (parent * childrenNum + 1 < heapNum) {
      int minWeightChild = parent * childrenNum + 1;
      int minWeightChildVertexIndex = heap[minWeightChild];

      int child = minWeightChild + 1;
      int childVertexIndex = heap[child];

      while (child - (parent * childrenNum + 1) < childrenNum && child < heapNum) {
        if (weight[childVertexIndex] > weight[minWeightChildVertexIndex]) {
          minWeightChild = child;
          minWeightChildVertexIndex = heap[minWeightChild];
        }

        child++;
      }

      if (weight[minWeightChildVertexIndex] < weight[parentVertexIndex]) {
        heapIndexes[parentVertexIndex] = minWeightChildVertexIndex;
        heapIndexes[minWeightChildVertexIndex] = parentVertexIndex;

        swap(minWeightChild, parent);

        parent = minWeightChild;
      } else {
        break;
      }
    }
  }

  private void swap(final int child, final int parent) {
    int vertexIndex = heap[parent];
    heap[child] = heap[parent];
    heap[parent] = vertexIndex;
  }

  int pop() throws DoNotExecution {
    if (heapNum == 0) {
      throw new DoNotExecution("will pop heap first element when heap num is 0");
    }

    int root = heap[0];
    heap[0] = heap[heapNum - 1];
    heapNum--;

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
}
