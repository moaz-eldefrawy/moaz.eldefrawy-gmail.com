package eg.edu.alexu.csd.datastructure.stack.cs23.Classes;

public class DoubleLinkedList {
    Node head;
    Node tail;
    int numOfElements;
    public DoubleLinkedList() {
        head = null;
        tail = null;
        numOfElements = 0;
    }

    public class Node
    {
        Object data;
        Node next;
        Node prev;
        Node()
        {
            data = null;
            next = null;
            prev = null;
        }

        Node(Object t)
        {
            data = t;
            next = null;
            prev = null;
        }
    }

    public void add(int index, Object element) {
        if(head == null && index == 0)
        {
            head = new Node(element);
            tail = head;
            numOfElements++;
        }
        else if(index == 0)
        {
            Node x = new Node(element);
            x.next = head;
            head = x;
            numOfElements++;
        }
        else if(index == numOfElements)
            add(element);
        else if(index > 0 && index < numOfElements)
        {
            Node x = head;
            int i = 0;
            while(x.next != null && i < index-1)
            {
                x = x.next;
                i++;
            }

            Node n = new Node(element);
            n.next = x.next;
            x.next.prev = n;
            x.next = n;
            n.prev = x;

            numOfElements++;
        }else
            System.out.println("Index Out of range");
    }

    public void add(Object element) {
        if(head == null)
        {
            head = new Node(element);
            tail = head;
        }
        else
        {
            Node x = new Node(element);
            tail.next = x;
            x.prev = tail;
            tail = x;
        }
        numOfElements++;
    }

    public Object get(int index) {
        Node x = head;
        int i = 0;

        if(x == null || index < 0 || index >= numOfElements)
        {
            System.out.println("Cant get out of bounds");
            return null;
        }
        while(x.next != null && i < index)
        {
            x = x.next;
            i++;
        }
        return x.data;
    }

    public void set(int index, Object element) {
        Node x = head;
        int i = 0;

        if(x == null || index < 0 || index >= numOfElements)
        {
            System.out.println("Cant set out of bounds");
            return;
        }
        while(x.next != null && i < index)
        {
            x = x.next;
            i++;
        }

        x.data = element;
    }

    public void clear() {
		/*
		Node x = head;
		if(head == null)
			return;
		do
		{
			Node tmp = x.next;
			x = null;
			x = tmp;
		}while(x != null);
		*/
        head = null;
        tail = null;
        numOfElements = 0;
    }

    public boolean isEmpty() {
        return numOfElements == 0;
    }

    public void remove(int index) {
        Node x = head;
        int i = 0;

        if(x == null || index < 0 || index >= numOfElements)
        {
            System.out.println("Out of ranges");
            return;
        }
        else if(index == 0)
        {
            if(head == tail)
            {
                head = null;
                tail = null;
            }else
            {
                head = x.next;
                head.prev = null;
            }
        }else if(index == numOfElements-1)
        {
            tail = tail.prev;
            tail.next = null;
        }
        else
        {
            while(x.next != null && i < index)
            {
                x = x.next;
                i++;
            }


            Node tmp = x.prev;
            tmp.next = x.next;
            x.next.prev = tmp;
        }
        numOfElements--;
    }

    public int size() {
        return numOfElements;
    }



    public boolean contains(Object o) {
        if(head == null)
            return false;
        Node x = head;
        do {
            if(x.data.equals(o))
                return true;
            x = x.next;
        }while(x != null);
        return false;
    }

    public void traverse()
    {
        Node x = head;
        if(head == null)
        {
            System.out.println("No Elements!");
            return;
        }

        do
        {
            System.out.print(x.data + " --> ");
            x = x.next;
        }while(x != null);
        System.out.println();
    }

    public static void out(Object x)
    {
        System.out.println(x);
    }



}
