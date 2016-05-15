package Estructuras;

public class Array {
	
	private Object[] elements;
	private int max;
	private int size;
	private int pos;
	
	public Array (int max) {
		this.elements = new Object[max];
		this.max = max;
		this.size = 0;
		this.pos = 0;		
	}
	
	/*
	 * Los elementos se almacenan en posiciones contiguas, por lo tanto para insertar en medio de
	 * elementos deben moverse los elementos para crear un espacio.
	 * Al insertar en la posición i, deben moverse n-1 elementos.
	 */
	public void insert(Object element) {
		if (this.size == this.max){
			System.out.println("List is full");
			return;
		}
		
		for (int i = this.size; i > this.pos; i--){
			this.elements[i] = this.elements[i - 1];
		}
		
		this.elements[this.pos] = element;
		this.size++;
	}
	
	public void append(Object element){
		if (this.size == this.max){
			System.out.println("List is full");
			return;
		}
		
		this.elements[this.size] = element;
		this.size++;
	}
	
	public void remove(){
		if (this.size == 0){
			System.out.println("List is empty");
			return;
		}
		
		if (this.pos < 0 || this.pos >= this.size) {
			System.out.println("Index out of bounds");
			return;
		}
		
		for (int i = this.pos; i < this.size -1; i++){
			this.elements[i] = this.elements[i + i];
		}
		
		this.size--;
	}
	
	public void clear() {
		this.elements = new Object[this.max];
		this.size = 0;
		this.pos = 0;
		
	}
	
	public Object getElement(){
		if (this.pos < 0 || this.pos >= this.size) {
			System.out.println("Index out of bounds");
			return null;
		}
		
		return this.elements[this.pos];
	}
	
	public void goToStart(){
		this.pos = 0;
	}
	
	public void goToEnd(){
		this.pos = this.size - 1;
	}
	
	public void goToPos(int pos){
		if (pos < 0 || pos >= this.size) {
			System.out.println("Index out of bounds");
			return;
		}
		this.pos = pos;
	}
	
	public void previous() {
		if (this.pos > 0) {
			this.pos--;
		}
	}
	
	public void next(){
		if (this.pos < this.size) {
			pos++;
		}
	}
	
	public int getPos() {
		return this.pos;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public String toString(){
		String result = "";
		for(int i = 0; i < this.size; i++){
			result+= " " + this.elements[i].toString();
		}
		return result;
	}
	
	public static void main(String[] args) {
		Array list = new Array(10);
		list.append(5);
		list.insert(7);
		System.out.println(list.toString());
		list.goToStart();
		list.insert(8);
		System.out.println(list.toString());
		list.next();
		list.remove();
		System.out.println(list.toString());
		list.insert(10);
		System.out.println(list.toString());
		
		System.out.println("Recorrer la lista ***");
		//recorrer la lista
		for (list.goToStart(); list.getPos() < list.getSize(); list.next()){
			System.out.println("Elemento #" + list.getPos() + ", valor: " + list.getElement());
		}
	}
}