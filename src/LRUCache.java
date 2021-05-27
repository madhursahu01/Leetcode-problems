import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/lru-cache/
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
Follow up:
Could you do get and put in O(1) time complexity?
 */
public class LRUCache {
    DoublyLinkedListNode head;
    DoublyLinkedListNode tail;
    Map<Integer, DoublyLinkedListNode> map;
    int capacity;
    int size;
    LRUCache(int capacity)
    {
        this.capacity = capacity;
        this.size = 0;
        this.map = new HashMap();
        this.head = new DoublyLinkedListNode();
        this.tail = new DoublyLinkedListNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get( int key )
    {
        DoublyLinkedListNode node = map.get(key);
        if(node == null)
        {
            return -1;
        }
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value)
    {
        DoublyLinkedListNode node = map.get(key);
        if(node == null)
        {
            node = new DoublyLinkedListNode(key,value);
            if(size >= capacity)
            {
                DoublyLinkedListNode removed = popTail();
                if(removed != null)
                {
                    size--;
                    map.remove(removed.key);
                }
            }
            addNode(node);
            size++;
            map.put(key,node);
        }
        else
        {
            node.val = value;
            moveToHead(node);
        }
        return;
    }

    class DoublyLinkedListNode {

        int key;
        int val;
        DoublyLinkedListNode next;
        DoublyLinkedListNode prev;

        public DoublyLinkedListNode()
        {

        }

        public DoublyLinkedListNode(int key, int value)
        {
            this.key = key;
            this.val = value;
        }
    }

    private void addNode(DoublyLinkedListNode node)
    {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;

    }

    private void removeNode(DoublyLinkedListNode node)
    {
        if(head == tail)
        {
            return;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    private void moveToHead( DoublyLinkedListNode node)
    {
        if(head == tail)
        {
            return;
        }
        removeNode(node);
        addNode(node);
    }

    public DoublyLinkedListNode popTail()
    {
        if(head == tail)
        {
            return null;
        }
        DoublyLinkedListNode removed = tail.prev;
        removeNode(removed);
        return removed;
    }
}
