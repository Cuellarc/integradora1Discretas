public class Queue {
    private int maxSize; // Tamaño máximo de la cola
    private int[] queueArray; // Arreglo para almacenar los elementos de la cola
    private int front; // Índice del primer elemento en la cola
    private int rear; // Índice del último elemento en la cola
    private int nItems; // Número de elementos en la cola

    // Constructor para inicializar la cola con un tamaño máximo dado
    public Queue(int size) {
        this.maxSize = size;
        this.queueArray = new int[maxSize];
        this.front = 0;
        this.rear = -1; // La cola está vacía al principio
        this.nItems = 0;
    }

    // Método para añadir un elemento al final de la cola (enqueue)
    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("La cola está llena. No se puede añadir más elementos.");
        } else {
            if (rear == maxSize - 1) {
                rear = -1; // Volver al inicio si llegamos al final del arreglo
            }
            rear++;
            queueArray[rear] = value;
            nItems++;
        }
    }

    // Método para eliminar y devolver el elemento al principio de la cola (dequeue)
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("La cola está vacía. No se puede eliminar ningún elemento.");
            return -1; // Valor de retorno por defecto para indicar que la cola está vacía
        } else {
            int temp = queueArray[front];
            front++;
            if (front == maxSize) {
                front = 0; // Volver al inicio si llegamos al final del arreglo
            }
            nItems--;
            return temp;
        }
    }

    // Método para verificar si la cola está vacía
    public boolean isEmpty() {
        return (nItems == 0);
    }

    // Método para verificar si la cola está llena
    public boolean isFull() {
        return (nItems == maxSize);
    }

    // Método para obtener el tamaño actual de la cola
    public int size() {
        return nItems;
    }
}
