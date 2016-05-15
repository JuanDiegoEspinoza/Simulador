package Estructuras;
import simulador.Proceso;


/**
 * Esta clase consiste en realizar una implementacion de la clase cola (Queue) en forma de Generics
 *
 * @author juandiegoes
 *
 * @param <Proceso>
 */

public class Queue<Proceso>{

	//Atributos
    private Node<Proceso> front;
    private Node<Proceso> rear;
    private int size;

	//Clase Node
    public class Node <Proceso>{

		private Node <Proceso> next;
		private Proceso element;

		public Node() {
			next = null;
			element = null;
		}

		public Node(Proceso element) {
			this.next = null;
			this.element = element;
		}

		public Node(Proceso element, Node next) {
			this.element = element;
			this.next = next;
		}

		public Proceso getElement() {
			return this.element;
		}

		public void setElement(Proceso element) {
			this.element = element;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getNext() {
			return next;
		}

	}// Cierre de la clase Nodo

    public Queue() {
        this.front = new Node<>();
        this.rear = this.front;
        this.size = 0;
    }

    public boolean isEmpty() {
            if (size == 0) {
                return false;
            }
            return true;
        }

    public void enqueue(Proceso element) {

        this.rear.setNext(new Node<Proceso>(element, null));
        this.rear = rear.getNext();
        this.size++;
    }

    public Proceso dequeue() {

        if (size >= 0) {
            Proceso temp = (Proceso) this.front.getNext().getElement();
            Node<Proceso> nTemp = this.front.getNext();
            this.front.setNext(nTemp.getNext());

            if (this.rear == nTemp) {
                    this.rear = this.front;
            }

            this.size--;
            return (Proceso) temp;
        }
        return null;
    }// Cierre de dequeue

    public Proceso first() {
            if (this.size <= 0) {
                    System.out.println("Error, la lista esta vacia");
                    return null;
            }
            Proceso element = (Proceso) front.getNext().getElement();
            return element;
    }

    public int size() {
            // ProcesoODO Auto-generated method stub
            return this.size;
    }

    public void clear() {
            this.front = this.rear = new Node<>();
            size = -1;
    }
}// Cierre de la clase
