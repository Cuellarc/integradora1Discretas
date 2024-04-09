import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue {
    private List<Integer> heap;

    public PriorityQueue() {
        heap = new ArrayList<>();
    }

    // Método para añadir un elemento a la cola de prioridad
    public void enqueue(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    // Método para eliminar y devolver el elemento con mayor prioridad de la cola
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola de prioridad está vacía");
        }
        int maxValue = peek();
        Collections.swap(heap, 0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        heapifyDown(0);
        return maxValue;
    }

    // Método para obtener el elemento con mayor prioridad sin eliminarlo
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola de prioridad está vacía");
        }
        return heap.get(0);
    }

    // Método para verificar si la cola de prioridad está vacía
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Método para mantener la propiedad de montículo hacia arriba
    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && heap.get(index) > heap.get(parentIndex)) {
            Collections.swap(heap, index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    // Método para mantener la propiedad de montículo hacia abajo
    private void heapifyDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int largestIndex = index;

        if (leftChildIndex < heap.size() && heap.get(leftChildIndex) > heap.get(largestIndex)) {
            largestIndex = leftChildIndex;
        }
        if (rightChildIndex < heap.size() && heap.get(rightChildIndex) > heap.get(largestIndex)) {
            largestIndex = rightChildIndex;
        }

        if (largestIndex != index) {
            Collections.swap(heap, index, largestIndex);
            heapifyDown(largestIndex);
        }
    }
}
