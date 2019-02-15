/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.Structures;

/**
 *
 * @author ColdFish
 */
public class intQ {

    int front, rear, size;
    int capacity;
    int array[];

    /**
     * A queue implementation using Arrays
     *
     * @param capacity the maximum size of the queue, after which it will start
     * to remove elements from the end of the queue when adding more then it can
     * hold
     */
    public intQ(int capacity) {
        this.capacity = capacity;
        front = this.size = 0;
        rear = capacity - 1;
        array = new int[this.capacity];

        for (int i = 0; i <= capacity; i++) {
            enqueue(1);
        }
    }

    /**
     * Get the sum of the integers stored in the queue
     *
     * @return total sum of array content
     */
    public int getContentSum() {
        int sum = 0;
        for (int i = 0; i < capacity; i++) {
            sum += array[i];
        }
        return sum;
    }

    /**
     * Check if the array is full
     *
     * @return true if full, false if not full
     */
    public boolean isFull() {
        return (this.size == this.capacity);
    }

    /**
     * Check if the array is empty
     *
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /**
     * Method to add an item to the queue.
     * If the queue overflows, the last item is removed automatically
     * @param item the integer to be added to the queue
     */
    public void enqueue(int item) {
        if (isFull()) {
            dequeue();
        }
        this.rear = (this.rear + 1) % this.capacity;
        this.array[this.rear] = item;
        this.size = this.size + 1;
    }

    /**
     * Remove last element of queue
     * @return the value of the integer removed
     */
    public int dequeue() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }

        int item = this.array[this.front];
        this.front = (this.front + 1) % this.capacity;
        this.size = this.size - 1;
        return item;
    }

}
