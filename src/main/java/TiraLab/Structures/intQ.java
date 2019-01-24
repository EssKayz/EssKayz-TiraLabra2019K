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
     * @param capacity the maximum size of the queue, after which it will start to remove elements from the end of the queue when adding more then it can hold
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

    public int getContentSum() {
        int sum = 0;
        for (int i = 0; i < capacity; i++) {
            sum += array[i];
        }
        return sum;
    }

    public boolean isFull(intQ queue) {
        return (queue.size == queue.capacity);
    }

    public boolean isEmpty(intQ queue) {
        return (queue.size == 0);
    }

    // Method to add an item to the queue.  
    // It changes last and size 
    public void enqueue(int item) {
        if (isFull(this)) {
            dequeue();
        }
        this.rear = (this.rear + 1) % this.capacity;
        this.array[this.rear] = item;
        this.size = this.size + 1;
    }

    public int dequeue() {
        if (isEmpty(this)) {
            return Integer.MIN_VALUE;
        }

        int item = this.array[this.front];
        this.front = (this.front + 1) % this.capacity;
        this.size = this.size - 1;
        return item;
    }

}
