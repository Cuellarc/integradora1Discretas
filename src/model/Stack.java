public class Stack {
    private int maxSize; // Tamaño máximo de la pila
    private int[] stackArray; // Arreglo para almacenar los elementos de la pila
    private int top; // Índice del elemento en la cima de la pila

    // Constructor para inicializar la pila con un tamaño máximo dado
    public Stack(int size) {
        this.maxSize = size;
        this.stackArray = new int[maxSize];
        this.top = -1; // La pila está vacía al principio
    }

    // Método para añadir un elemento a la pila (push)
    public void push(int value) {
        if (isFull()) {
            System.out.println("La pila está llena. No se puede añadir más elementos.");
        } else {
            top++;
            stackArray[top] = value;
        }
    }

    // Método para eliminar y devolver el elemento de la cima de la pila (pop)
    public int pop() {
        if (isEmpty()) {
            System.out.println("La pila está vacía. No se puede eliminar ningún elemento.");
            return -1; // Valor de retorno por defecto para indicar que la pila está vacía
        } else {
            int value = stackArray[top];
            top--;
            return value;
        }
    }

    // Método para verificar si la pila está vacía
    public boolean isEmpty() {
        return (top == -1);
    }

    // Método para verificar si la pila está llena
    public boolean isFull() {
        return (top == maxSize - 1);
    }

    // Método para ver el elemento en la cima de la pila sin eliminarlo
    public int peek() {
        if (isEmpty()) {
            System.out.println("La pila está vacía.");
            return -1; // Valor de retorno por defecto para indicar que la pila está vacía
        } else {
            return stackArray[top];
        }
    }

    // Método para obtener el tamaño actual de la pila
    public int size() {
        return top + 1;
    }
}
